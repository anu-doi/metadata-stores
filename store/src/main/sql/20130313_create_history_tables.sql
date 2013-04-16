--drop table if exists hist_item_attribute;

create table hist_item_attribute (
	aid				bigint					not null
	,hist_datetime	timestamp with time zone	not null
	,iid			bigint					not null
	,attr_type		varchar(40)				not null
	,attr_value		text					not null
	,parent_aid		bigint
	,user_updated	boolean
	,last_modified	timestamp with time zone
	,primary key (aid, hist_datetime)
	, constraint fk_hist_item_attribute_1 foreign key (iid) references item(iid)
);
