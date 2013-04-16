CREATE SEQUENCE person_seq START 1;

alter table item add column deleted boolean;
alter table item_attribute add column last_modified timestamp  with time zone;

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