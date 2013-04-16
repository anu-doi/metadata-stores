drop table if exists system_types;

create table system_types (
	ext_system	varchar(50)	not null
	,title		varchar(255)	not null
	,description	text
);

insert into system_types values ('DATA_COMMONS','Data Commons',null);
insert into system_types values ('GRANT','Grants',null);
insert into system_types values ('DIGITAL_COLLECTIONS','Digital Collections',null);
insert into system_types values ('PERSON','People',null);
insert into system_types values ('PUBLICATION','Publication',null);
