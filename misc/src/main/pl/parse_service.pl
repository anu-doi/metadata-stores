#!/bin/perl
#
# *******************************************************************************
# Australian National University Metadata Stores
# Copyright (C) 2013  The Australian National University
# 
# This file is part of Australian National University Metadata Stores.
# 
# Australian National University Metadata Stores is free software: you
# can redistribute it and/or modify it under the terms of the GNU
# General Public License as published by the Free Software Foundation,
# either version 3 of the License, or (at your option) any later
# version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
# *******************************************************************************


use XML::Simple;
use Data::Dumper;
use LWP::Simple;
use LWP::UserAgent;
use DBI;

$yearToParse = $ARGV[0];

$argSize = @ARGV;

if ($argSize != 1) {
	print "usage:\n";
	print "\tparse_service.pl year\n";
	print "example:\n";
	print "\tparse_service.pl 2009\n";
	exit 1;
}

my $url = "http://localhost:8180/services/rest/publication/year/$yearToParse";
my $connStr = "DBI:Pg:dbname=mapappdb;host=localhost";
my $connUser = "mapappuser";
my $connPwd = "mapapppassword";
#my $dbh = DBI->connect("DBI:Pg:dbname=mapappdb;host=dc7-dev2.anu.edu.au;port=9432;","mapappuser","mapapppassword",{'RaiseError' => 1});

$xml = new XML::Simple;

my $ua = LWP::UserAgent->new();
$ua->default_header('Accept' => "application/xml");
print "Retrieving data from URL: ", $url, "\n";
#my $content = get $url or die "Unable to get $url\n";
my $response = $ua->get($url);

if (!$response->is_success()) {
	die $response->status_line();
}

print "Parsing XML Data";
$data = $xml->XMLin($response->decoded_content());

my @authors = ();
my @extAuthors = ();
my @institution = ();
my @forsubjects = ();

my %year_inst = ();

foreach $pub (@{$data->{publication}}) {
	@authors = ();
	@extAuthors = ();
	@institution = ();
	@fordivs = ();
	if (ref($pub->{author}) eq 'ARRAY') {
		foreach $author (@{$pub->{author}}) {
			if ($author->{"institution"}) {
				push(@extAuthors, $author);
			}
			else {
				push(@authors, $author);
			}
		}
		$size = @extAuthors;
		if ($size > 0) {
			foreach $extAuth (@extAuthors) {
				$response = checkIfInArray($extAuth->{institution}, @institution);
				if ($response eq false) {
					push(@institution, $extAuth->{institution});
				}
			}
			$size = @institution;
			
			$year = $pub->{"publication-year"};
			foreach $inst (@institution) {
				$year_inst{$year}{$inst}{count} = $year_inst{$year}{$inst}{count} + 1;
				foreach $author (@authors) {
					$authorName = '';
					if ($author->{'display-name'}) {
						$authorName = $author->{'display-name'};
					}
					else {
						$authorName = "$author->{'given-name'} $author->{'surname'}";
					}
					$year_inst{$year}{$inst}{authors}{$authorName}{count} = $year_inst{$year}{$inst}{authors}{$authorName}{count} + 1;
					
					foreach $extAuth (@extAuthors) {
						$extAuthName = $extAuth->{'display-name'};
						$year_inst{$year}{$inst}{authors}{$authorName}{ext}{$extAuthName}{count} = $year_inst{$year}{$inst}{authors}{$authorName}{ext}{$extAuthName}{count} + 1;
					}
				}
				if (ref($pub->{"for-subject"}) eq 'ARRAY') {
					foreach $forid (@{$pub->{"for-subject"}}) {
						$fordiv = substr($forid->{code},0,2);
						if (checkIfInArray($fordiv, @fordivs) eq false) {
							push(@fordivs, $fordiv);
						}
					}
					foreach $fordiv (@fordivs) {
						$year_inst{$year}{$inst}{subject}{$fordiv}{count} = $year_inst{$year}{$inst}{subject}{$fordiv}{count} + 1;
					}
				}
				elsif ($pub->{"for-subject"}) {
					$fordiv = substr($pub->{"for-subject"}->{code},0,2);
					$year_inst{$year}{$inst}{subject}{$fordiv}{count} = $year_inst{$year}{$inst}{subject}{$fordiv}{count} + 1;
				}
			}
			
			foreach $extAuth (@extAuthors) {
				$extAuthName = "$extAuth->{'given-name'} $extAuth->{'surname'}";
				$inst = $extAuth->{institution};
				$year_inst{$year}{$inst}{ext}{$extAuthName}{count} = $year_inst{$year}{$inst}{ext}{$extAuthName}{count} + 1;
			}
			
			
		}
	}
}

my $dbh = DBI->connect($connStr,$connUser,$connPwd,{'RaiseError' => 1});

my $insth = $dbh->prepare("SELECT institutionid FROM institution WHERE name = ?");
my $insinsth = $dbh->prepare("INSERT INTO institution (name) VALUES (?)");
my $authsth = $dbh->prepare("SELECT authorid FROM anu_author WHERE name = ?");
#my $insauthsth = $dbh->prepare("INSERT INTO anu_author (name) VALUES (?)");
my $extauthsth = $dbh->prepare("SELECT externalid FROM ext_anu_author WHERE name = ?");
my $insextauthsth = $dbh->prepare("INSERT INTO ext_anu_author (name) VALUES (?)");

print "Creating CSV files\n";

open (INSTOUT, '>out_institution_yearcount.csv');
open (ANUCOLLOUT, '>out_anu_collaboration.csv');
open (ANUCOLLERROUT, '>out_anu_collaboration_err.csv');
open (ANUCOLLAUTHOUT, '>out_anu_collaboration_author.csv');
open (ANUCOLLAUTHERROUT, '>out_anu_collaboration_author_err.csv');
open (INSTAUTH, '>out_institution_author.csv');
open (INSTFOR, '>out_institution_for.csv');

my @years = ();

# Generate the CSV files so the data can easily be inserted.
while ( ($year, $yearValue) = each %year_inst) {
	push (@years, $year);
	while (($inst, $instValue) = each $yearValue) {
		$instituteid = getIdIns($insth, $insinsth, 'institutionid', $inst);
		print INSTOUT $year, "|", $instituteid, "|", $instValue->{count}, "\n";
		
		while (($author, $authorValue) = each $instValue->{authors}) {
			$authorid = getId($authsth, 'authorid', $author);
			if ($authorid eq "None") {
				print ANUCOLLERROUT $year, "|", $instituteid, "|", $author, "|", $authorValue->{count},"\n";
			}
			else {
#			print ANUCOLLOUT $year, "|", $inst, "|", $author, "|", $authorValue->{count},"\n";
				print ANUCOLLOUT $year, "|", $instituteid, "|", $authorid, "|", $authorValue->{count},"\n";
			}
			while (($extAuthor, $extAuthorValue) = each $instValue->{ext}) {
#				print ANUCOLLAUTHOUT $year, "|", $inst, "|", $author, "|", $extAuthor, "|", $extAuthorValue->{count},"\n";
				$extauthorid = getIdIns($extauthsth, $insextauthsth, 'externalid', $extAuthor);
				if ($authorid eq "None") {
					print ANUCOLLAUTHERROUT $year, "|", $instituteid, "|", $author, "|", $extauthorid, "|", $extAuthorValue->{count},"\n";
				}
				else {
					print ANUCOLLAUTHOUT $year, "|", $instituteid, "|", $authorid, "|", $extauthorid, "|", $extAuthorValue->{count},"\n";
				}
			}
		}
		while (($extAuthor, $extAuthorValue) = each $instValue->{ext}) {
			$extauthorid = getIdIns($extauthsth, $insextauthsth, 'externalid', $extAuthor);
#			print INSTAUTH $year, "|", $inst, "|", $extAuthor, "|", $extAuthorValue->{count},"\n";
			print INSTAUTH $year, "|", $instituteid, "|", $extauthorid, "|", $extAuthorValue->{count},"\n";
		}
		while (($forDiv, $forDivValue) = each $instValue->{subject}) {
#			print INSTFOR $year, "|", $inst, "|", $forDiv, "|", $forDivValue->{count},"\n";
			print INSTFOR $year, "|", $instituteid, "|", $forDiv, "|", $forDivValue->{count},"\n";
		}
	}
}

close(INSTFOR);
close(INSTAUTH);
close(ANUCOLLAUTHOUT);
close(ANUCOLLOUT);
close(INSTOUT);

deleteAndCopyData(@years);

$dbh->disconnect();

# Verify whether a value is in the given array or not
sub checkIfInArray {
	@anArray = $_[1];
	$value = $_[0];
	$arraySize = @anArray;
	foreach $arrayValue (@anArray) {
		if ($value eq $arrayValue) {
			return true;
		}
	}
	return false;
}

# Get an id, if it does not exist return "None"
sub getId {
	$findsth = $_[0];
	$fieldName = $_[1];
	$value = $_[2];
	
	$findsth->bind_param(1, $value);
	$findsth->execute();
	if ($findsth->rows() == 0) {
		print "Cannot find name: $value\n";
		return "None";
	}
	
	$ref = $findsth->fetchrow_hashref();
	$idValue = $ref->{$fieldName};
	$findsth->finish();
	return $idValue;
}

# Get an id, if it does not exist create a new row in the database
sub getIdIns {
	$findsth = $_[0];
	$inssth = $_[1];
	$fieldName = $_[2];
	$value = $_[3];
	
	$findsth->bind_param(1, $value);
	$findsth->execute();
	if ($findsth->rows() == 0) {
		print "Cannot find name: $value\n";
		$inssth->bind_param(1, $value);
		$inssth->execute();
		$inssth->finish();
		$findsth->execute();
	}
	
	$ref = $findsth->fetchrow_hashref();
	$idValue = $ref->{$fieldName};
	$findsth->finish();
	return $idValue;
}

# Process all the files created
sub deleteAndCopyData {
	print "Deleting and Copying Data\n";
	@years = $_[0];
	
	my $delanucoll = "DELETE FROM anu_collaboration WHERE year IN (?)";
	deleteData(@years, $delanucoll);
	my $copyanucoll = "copy anu_collaboration from STDIN with delimiter '|'";
	copyData('out_anu_collaboration.csv',$copyanucoll);
	
	my $delanucollauth = "DELETE FROM anu_collaboration_author WHERE year IN (?)";
	deleteData(@years, $delanucollauth);
	my $copyanucollauth = "copy anu_collaboration_author from STDIN with delimiter '|'";
	copyData('out_anu_collaboration_author.csv',$copyanucollauth);
	
	my $delinstauth = "DELETE FROM institution_author WHERE year IN (?)";
	deleteData(@years, $delinstauth);
	my $copyinstauth = "copy institution_author from STDIN with delimiter '|'";
	copyData('out_institution_author.csv', $copyinstauth);
	
	my $delinstfor = "DELETE FROM institution_for WHERE year IN (?)";
	deleteData(@years, $delinstfor);
	my $copyinstfor = "copy institution_for from STDIN with delimiter '|'";
	copyData('out_institution_for.csv', $copyinstfor);
	
	my $delinstyear = "DELETE FROM institution_yearcount WHERE year IN (?)";
	deleteData(@years, $delinstyear);
	my $copyinstyear = "copy institution_yearcount from STDIN with delimiter '|'";
	copyData('out_institution_yearcount.csv', $copyinstyear);
}

# Delete the current rows in the database for the given years
sub deleteData {
	@years = $_[0];
	$delstr = $_[1];
	
	my $delstmt = $dbh->prepare($delstr);
	$delstmt->bind_param(1, @years);
	$delstmt->execute();
	$delstmt->finish();
}

# Put the data from the csv file into the database
sub copyData {
	$copyfile = $_[0];
	$copystr = $_[1];
	
	open (INPUT, $copyfile) || die "Cannot open file $copyfile\n";
	$dbh->do($copystr);
	while (<INPUT>) {
		$line = $_;
		$dbh->pg_putcopydata($line);
	}
	$dbh->pg_putcopyend();
	close(INPUT);
}
