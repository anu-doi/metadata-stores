DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS harvest_content;

CREATE TABLE location (
	lid					bigserial		not null
	, system			varchar(20)		not null
	, url				varchar(255)	not null
	, metadata_prefix	varchar(255)	not null
	, last_harvest_date	timestamp with time zone
	, harvest_freq		bigint
	, orig_harvest_date	timestamp with time zone
	, PRIMARY KEY (lid)
);

CREATE TABLE harvest_content (
	hid			bigserial			not null
	, identifier	varchar(255)	not null
	, system		varchar(20)		not null
	, content		text			not null
	, PRIMARY KEY (hid)
);