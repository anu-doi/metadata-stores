drop table if exists item_relation;
drop table if exists item_attribute;
drop table if exists item;
drop table if exists relation_mapping;
drop table if exists potential_relation;

create table item (
	iid				bigserial 		not null
	, title			text
	, ext_system	varchar(50)		not null
	, ext_id		varchar(255)	not null
	, deleted		boolean
	, primary key (iid)
	, unique (ext_system, ext_id)
);

create table item_attribute (
	aid				bigserial	not null
	, iid			bigint		not null
	, attr_type		varchar(40)	not null
	, attr_value	text		not null
	, parent_aid	bigint		null
	, user_updated	boolean		null
	, primary key (aid)
	, constraint fk_item_attribute_1 foreign key (iid) references item(iid)
	, constraint fk_item_attribute_2 foreign key (parent_aid) references item_attribute(aid) on delete cascade
);

create table item_relation (
	iid					bigint			not null
	, relation_value	varchar(255)	not null
	, related_iid		bigint			not null
	, user_updated		boolean
	, primary key (iid, relation_value, related_iid)
	, constraint fk_relation_attribute_1 foreign key (iid) references item(iid) on delete cascade
	, constraint fk_relation_attribute_2 foreign key (related_iid) references item(iid) on delete cascade
);

CREATE TABLE relation_mapping (
	id bigserial NOT NULL
	, code character varying(60) NOT NULL
	, description text NOT NULL
	, reverse character varying(60)
	, PRIMARY KEY (id )
);

create table potential_relation (
	iid					bigint			not null
	, relation_value	varchar(255)	not null
	, related_iid		bigint			not null
	, require_check		boolean			not null
	, is_link			boolean
	, primary key (iid, relation_value, related_iid)
	, constraint fk_potential_relation_1 foreign key (iid) references item(iid) on delete cascade
	, constraint fk_potential_relation_2 foreign key (related_iid) references item(iid) on delete cascade
);