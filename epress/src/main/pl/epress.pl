#!/usr/bin/perl
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
#
# This script converts a binary mark file into an xml file

use MARC::Record;

use MARC::Batch;
use XML::LibXML;
use Encode;
use Encode::Guess;
# qw(encode);

my $batch = MARC::Batch->new('USMARC','anuepress_electronic.mrc');

my $doc = XML::LibXML::Document->new('1.0','utf-8');

my $root = $doc->createElement("records");

while (my $record = $batch->next()) {
	
	@series490 = $record->field('490');
	$series490size = @series490;
	@series440 = $record->field('440');
	$series440size = @series440;
	
	if ($series490size > 0 || $series440size > 0) {
		my $recElem = $doc->createElement("epress");
		
		print "Title: ".$record->title()."\n";
		print "Author: ".$record->author()."\n";
		
		@isbns = $record->field('020');
		@issns = $record->field('022');
		@title_auth = $record->field('245');
		@contents = $record->field('505');
		@descriptions = $record->field('520');
		@seriesdescs = $record->field('830');
		@citationURLs = $record->field('856');
	
		foreach $isbn (@isbns) {
			my $isbnElem = $doc->createElement("isbn");
			my $isbnStr = $isbn->as_string();
			if ($isbnStr =~ /^([0-9Xx]*).*$/) {
				$isbnElem->appendTextNode($1);
				$recElem->appendChild($isbnElem);
			}
		}
		foreach $issn (@issns) {
			my $issnElem = $doc->createElement("issn");
			my $issnStr = $issn->as_string();
			if ($issnStr =~ /^([0-9Xx]*).*$/) {
				$issnElem->appendTextNode($1);
				$recElem->appendChild($issnElem);
			}
		}
		foreach $title (@title_auth) {
			$titleStr = $title->subfield("a");
			$titleB = $title->subfield("b");
			if ($titleB) {
				if ($titleB =~ /(.*)\/$/) {
					$titleB = $1;
				}
				$titleStr .= ": ".$titleB;
			}
			$titleStr =~ s/^\s+//;
			$titleStr =~ s/\s+$//;
			my $titleElem = $doc->createElement("title");
			$titleElem->appendTextNode($titleStr);
			$recElem->appendChild($titleElem);
			
			$authorStr = $title->subfield("c");
			$authorStr =~ s/^\s+//;
			$authorStr =~ s/\s+$//;
			
			$authorElem = $doc->createElement("authors");
			$authorElem->appendTextNode($authorStr);
			$recElem->appendChild($authorElem);
		}
		foreach $series (@series490) {
			my $seriesElem = $doc->createElement("series");
			$seriesElem->appendTextNode($series->as_string());
			$recElem->appendChild($seriesElem);
		}
		foreach $series (@series440) {
			my $seriesElem = $doc->createElement("series");
			$seriesElem->appendTextNode($series->as_string());
			$recElem->appendChild($seriesElem);
		}
		foreach $content (@contents) {
			my $contentElem = $doc->createElement("content");
			$contentText = $content->as_string();
			$contentElem->appendTextNode($contentText);
			$recElem->appendChild($contentElem);
		}
		foreach $description (@desciprtions) {
			my $descElem = $doc->createElement("description");
			$descElem->appendTextNode($description->as_string());
			$recElem->appendChild($descElem);
		}
		foreach $seriesdesc (@seriesdescs) {
			my $seriesdescElem = $doc->createElement("series-description");
			$seriesdescElem->appendTextNode($seriesdesc->as_string());
			$recElem->appendChild($seriesdescElem);
		}
		foreach $citationURL (@citationURLs) {
			my $citElem = $doc->createElement("citation-url");
			$citElem->appendTextNode($citationURL->as_string());
			$recElem->appendChild($citElem);
		}
		
		$root->appendChild($recElem);
	}
}

$doc->setDocumentElement($root);
open (XMLOUT, ">records.xml") || die "cannot open records.txt";
print XMLOUT $doc->toString();
close (XMLOUT);

if (my @warnings = $batch->warnings()) {
	print "\nWarnings were detected!\n", @warnings;
}
