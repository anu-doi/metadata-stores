drop table if exists attribute_types;

create table attribute_types (
	attr_type		varchar(40)		not null
	,title			varchar(255)	not null
	,description	text
);

insert into attribute_types values ('UNIVERSITY_ID','University Identifier',null);
insert into attribute_types values ('GIVEN_NAME','Given Name',null);
insert into attribute_types values ('SURNAME', 'Surname',null);
insert into attribute_types values ('DISPLAY_NAME','Display Name',null);
insert into attribute_types values ('ARIES_ID','Aries ID',null);
insert into attribute_types values ('EMAIL','Email Address',null);
insert into attribute_types values ('PHONE','Phone Number',null);
insert into attribute_types values ('FAX','Fax Number',null);
insert into attribute_types values ('JOB_TITLE','Job Title',null);
insert into attribute_types values ('PREFERRED_NAME','Preferred Name',null);
insert into attribute_types values ('STAFF_TYPE','Staff Type',null);
insert into attribute_types values ('ORGANISATIONAL_UNIT','Organisational Unit',null);
insert into attribute_types values ('NLA_ID','National Library of Australia Identifier',null);
insert into attribute_types values ('FOR_SUBJECT','Field of Research Subject',null);
insert into attribute_types values ('FOR_CODE','Field of Research Code',null);
insert into attribute_types values ('FOR_VALUE','Field of Research Description',null);
insert into attribute_types values ('FOR_PERCENT','Field of Research Percentage',null);
insert into attribute_types values ('COMMON_NAME','Common Name',null);
insert into attribute_types values ('COUNTRY','Country',null);
insert into attribute_types values ('INSTITUTION','Institution',null);
insert into attribute_types values ('TYPE','Type',null);
insert into attribute_types values ('TITLE','Title',null);
insert into attribute_types values ('YEAR','Year',null);
insert into attribute_types values ('FIRST_AUTHOR_ID','First Author ID',null);
insert into attribute_types values ('PUBLICATION_NAME','Publication Name',null);
insert into attribute_types values ('CATEGORY','Category',null);
insert into attribute_types values ('ISBN','ISBN',null);
insert into attribute_types values ('ISSN','ISSN',null);
insert into attribute_types values ('CREATOR','Creator',null);
insert into attribute_types values ('SUBJECT','Subject',null);
insert into attribute_types values ('DESCRIPTION','Description',null);
insert into attribute_types values ('PUBLISHER','Publisher',null);
insert into attribute_types values ('CONTRIBUTOR','Contributor',null);
insert into attribute_types values ('DATE','Date',null);
insert into attribute_types values ('FORMAT','Format',null);
insert into attribute_types values ('IDENTIFIER','Identifier',null);
insert into attribute_types values ('SOURCE','Source',null);
insert into attribute_types values ('LANGUAGE','Language',null);
insert into attribute_types values ('RELATION','Relation',null);
insert into attribute_types values ('RELATION_TYPE','Relation Type',null);
insert into attribute_types values ('RELATION_VALUE','Relation Value',null);
insert into attribute_types values ('COVERAGE','Coverage',null);
insert into attribute_types values ('RIGHTS','Rights',null);
insert into attribute_types values ('CONTRACT_CODE','Contract Code',null);
insert into attribute_types values ('START_DATE','Start Date',null);
insert into attribute_types values ('END_DATE','End Date',null);
insert into attribute_types values ('FIRST_INVESTIGATOR_ID','First Investigator ID',null);
insert into attribute_types values ('FUNDS_PROVIDER','Funds Provider',null);
insert into attribute_types values ('REF_NUMBER','Grant Reference Number',null);
insert into attribute_types values ('STATUS','Status',null);