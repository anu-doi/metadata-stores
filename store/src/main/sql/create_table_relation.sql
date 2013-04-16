drop table if exists relation_mapping;

CREATE TABLE relation_mapping (
	id bigserial NOT NULL
	, code character varying(60) NOT NULL
	, description text NOT NULL
	, reverse character varying(60)
	, PRIMARY KEY (id )
);

INSERT INTO relation_mapping (code, description, reverse) VALUES ('isPartOf','Is Part Of','hasPart');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('hasPart','Has Part','isPartOf');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('hasOutput','Has Output','isOutputOf');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isManagedBy','Is Managed By','isManagerOf');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isOwnedBy','Is Owned By','isOwnerOf');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('hasParticipant','Has Participant','isParticipantIn');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('hasAssociationWith','Has Association With','hasAssociationWith');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('describes','Describes','isDescribedBy');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isDescribedBy','Is Described By','describes');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isLocatedIn','Is Located In','isLocationFor');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isLocationFor','Is Location For','isLocatedIn');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isDerivedFrom','Is Derived From','hasDerivedCollection');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('hasDerivedCollection','Has Derived Collection','isDerivedFrom');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('hasCollector','Has Collector','isCollectorOf');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isEnrichedBy','Is Enriched By','enriches');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isOutputOf','Is Output Of','hasOutput');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('supports','Supports','isSupportedBy');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isAvailableThrough','Is Available Through','makesAvailable');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isProducedBy','Is Produced By','produces');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isPresentedBy','Is Presented By','presents');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isOperatedOnBy','Is Operated On By','operatesOn');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('hasValueAddedBy','Has Value Added By','addsValueTo');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('hasMember','Has Member','isMemberOf');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isMemberOf','Is Member Of','hasMember');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isFundedBy','Is Funded By','isFunderOf');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isFunderOf','Is Funder Of','isFundedBy');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('enriches','Enriches','isEnrichedBy');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isCollectorOf','Is Collector Of','hasCollector');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isParticipantIn','Is Participant In','hasParticipant');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isManagerOf','Is Manager Of','isManagedBy');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isOwnerOf','Is Owner Of','isOwnedBy');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isSupportedBy','Is Supported By','supports');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('makesAvailable','Makes Available','isAvailableThrough');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('produces','Produces','isProducedBy');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('presents','Presents','isPresentedBy');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('operatesOn','Operates On','isOperatedOnBy');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('addsValueTo','Adds Value To','hasValueAddedBy');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isSameAs','Is Same As','isSameAs');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('hasPrincipalInvestigator','Has Principal Investigator','isPrincipalInvestigatorOf');
INSERT INTO relation_mapping (code, description, reverse) VALUES ('isPrincipalInvestigatorOf','Is Principal Investigator Of','hasPrincipalInvestigator');