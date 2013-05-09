/*******************************************************************************
 * Australian National University Metadata Stores
 * Copyright (C) 2013  The Australian National University
 * 
 * This file is part of Australian National University Metadata Stores.
 * 
 * Australian National University Metadata Stores is free software: you
 * can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package au.edu.anu.metadatastores.store.datacommons;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.harvester.HarvestContent;
import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.ItemAttribute;
import au.edu.anu.metadatastores.datamodel.store.ItemRelation;
import au.edu.anu.metadatastores.datamodel.store.ItemRelationId;
import au.edu.anu.metadatastores.datamodel.store.RelationMapping;
import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;
import au.edu.anu.metadatastores.harvester.HarvestContentService;
import au.edu.anu.metadatastores.services.store.StoreHibernateUtil;
import au.edu.anu.metadatastores.store.dublincore.DublinCoreItem;
import au.edu.anu.metadatastores.store.dublincore.DublinCoreService;
import au.edu.anu.metadatastores.store.dublincore.xml.DublinCore;
import au.edu.anu.metadatastores.store.misc.Mappings;
import au.edu.anu.metadatastores.store.properties.StoreProperties;

/**
 * DataCommonsService
 * 
 * The Australian National University
 * 
 * Service to provide updates to Data Commons records
 * 
 * @author Genevieve Turner
 *
 */
/**
 * <p>DataCommonsService<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Service to provide updates to Data Commons records</p>
 * 
 * @author Genevieve Turner
 *
 */
public class DataCommonsService extends DublinCoreService {
	static final Logger LOGGER = LoggerFactory.getLogger(DataCommonsService.class);
	
	private static DataCommonsService singleton_;
	private static final String extSystem = "DATA_COMMONS";
	HarvestContentService contentService_ = HarvestContentService.getSingleton();
	
	/**
	 * Constructor class for the DataCommonsSevice
	 */
	private DataCommonsService() {
		
	}
	
	/**
	 * Returns a singleton instance for the DataCommonsservice
	 * 
	 * @return the DataCommonsService
	 */
	public static DataCommonsService getSingleton() {
		if (singleton_ == null) {
			singleton_ = new DataCommonsService();
		}
		return singleton_;
	}
	
	/**
	 * Process the content that has been harvested for data commons
	 */
	public void processHarvestContent() {

		String datacommonsLocation = StoreProperties.getProperty("harvest.location.datacommons");
		
		HarvestContent content = contentService_.getNextHarvestContent(datacommonsLocation);
		while (content != null) {
			LOGGER.debug("ID: {}, Identifier: {}, Content: {}", new Object[] {content.getHid(), content.getIdentifier(), content.getContent()});
			if ("deleted".equals(content.getContent())) {
				processDeleted(content);
			}
			else {
				processRecord(content);
			}
			contentService_.deleteHarvestContent(content);
			content = contentService_.getNextHarvestContent(datacommonsLocation);
		}
	}
	
	/**
	 * Process records that have the deleted status from oai-pmh
	 * 
	 * @param content The harvested record to process
	 */
	private void processDeleted(HarvestContent content) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("FROM DataCommonsItem WHERE extSystem = :extSystem AND extId = :extId");
		query.setParameter("extSystem", extSystem);
		query.setParameter("extId", content.getIdentifier());
		
		DataCommonsItem item = (DataCommonsItem) query.uniqueResult();
		
		if (item != null) {
			item.setDeleted(Boolean.TRUE);
			session.merge(item);
		}
		else {
			LOGGER.debug("No record to be deleted: {}", content.getIdentifier());
		}
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Process a record that has not been deleted
	 * 
	 * @param content The harvested record to process
	 */
	private void processRecord(HarvestContent content) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.enableFilter("attributes");
		
		Query query = session.createQuery("FROM DataCommonsItem WHERE extSystem = :extSystem AND extId = :extId");
		query.setParameter("extSystem", extSystem);
		query.setParameter("extId", content.getIdentifier());
		
		DataCommonsItem item = (DataCommonsItem) query.uniqueResult();
		if (item == null) {
			item = new DataCommonsItem();
			item.setExtSystem(extSystem);
			item.setExtId(content.getIdentifier());
			session.save(item);
		}
		
		//TODO make a dublin core item and process there
		try {
			JAXBContext context = JAXBContext.newInstance(DublinCore.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			DublinCore dublinCore = (DublinCore) unmarshaller.unmarshal(new StringReader(content.getContent()));
			Date lastModified = new Date();
			super.processRecord((DublinCoreItem) item, dublinCore, session, lastModified);
		}
		catch (JAXBException e) {
			LOGGER.error("Exception transforming document");
		}

		//session.flush();
		session.merge(item);
		
		LOGGER.debug("Item Numbers: {}", item.getItemAttributes().size());
		
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Set the relations from the dublin core record provided
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current active hibernate session
	 * @param lastModified The modification date
	 */
	@Override
	protected void setItemRelations(DublinCoreItem item, DublinCore dublinCore, Session session, Date lastModified) {
		setRelationAttributes(item, item.getRelations(), dublinCore.getRelations(), session, StoreAttributes.RELATION, lastModified);
	}
	
	/**
	 * Set relation attributes
	 * 
	 * @param item The item to set attributes for
	 * @param attributes The list of attributes currently recorded against the record
	 * @param values The dublin core values
	 * @param session The current active hibernate session
	 * @param attrType The type of attribute to update
	 * @param lastModified The modification date
	 */
	private void setRelationAttributes(DublinCoreItem item, List<ItemAttribute> attributes, List<String> values, Session session, String attrType, Date lastModified) {
		List<ItemAttribute> removeItems = new ArrayList<ItemAttribute>();
		List<String> addItems = new ArrayList<String>();
		compareValues(values, attributes, removeItems, addItems);
		updateRelationAttributes(item, removeItems, addItems, session, attrType, lastModified);
	}
	
	/**
	 * Update the relation attributes
	 * 
	 * @param item The item to update attributes of
	 * @param removeItems The items to remove
	 * @param addItems The items to add
	 * @param session The current active session
	 * @param attrType The attribute type
	 * @param lastModified The modification date
	 */
	private void updateRelationAttributes(DublinCoreItem item, List<ItemAttribute> removeItems, List<String> addItems, Session session, String attrType, Date lastModified) {
		removeAttributes(item, removeItems, session, lastModified);
		
		ItemAttribute attr = null;
		for (String value : addItems) {
			attr = new ItemAttribute(item, attrType, value, lastModified);
			String[] relationParts = getRelationParts(value);
			if (relationParts[0] != null && relationParts[0].trim().length() > 0) {
				ItemAttribute typeAttr = new ItemAttribute(item, StoreAttributes.RELATION_TYPE, relationParts[0], lastModified);
				typeAttr.setItemAttribute(attr);
				attr.getItemAttributes().add(typeAttr);
			}
			if (relationParts[1] != null && relationParts[1].trim().length() > 0) {
				ItemAttribute valueAttr = new ItemAttribute(item, StoreAttributes.RELATION_VALUE, relationParts[1], lastModified);
				valueAttr.setItemAttribute(attr);
				attr.getItemAttributes().add(valueAttr);
			}
			
			item.getItemAttributes().add(attr);
		}
	}
	
	/**
	 * Set split the relation into relation parts
	 * 
	 * @param relation The relation
	 * @return The parts of the relation
	 */
	private String[] getRelationParts(String relation) {
		String[] values = new String[2];
		int index = relation.indexOf("http");
		LOGGER.debug("Relation: {}, Index: {}", relation, index);
		if (index >= 0) {
			String relationItem = relation.substring(index).trim();
			String relationValue = relation.substring(0, index).trim();
			values[0] = relationValue;
			values[1] = relationItem;
		}
		else {
			values[1] = relation;
		}
		
		return values;
	}
	
	/**
	 * Set the relationships
	 * 
	 * @param item The dublin core item to set the relation for
	 * @param dublinCore the dublin core to set relations for
	 * @param session The hibernate session
	 */
	protected void setRelations(DublinCoreItem item, DublinCore dublinCore, Session session) {
		Iterator<ItemRelation> it = item.getItemRelationsForIid().iterator();
		
		List<ItemRelation> itemRelations = new ArrayList<ItemRelation>();
		
		List<RelationPart> relationParts = getRelationPartsList(dublinCore);
		setIdentifierRelations(item, relationParts, itemRelations, session);
		setNLAIdentifierRelations(item, relationParts, itemRelations, session);
		setARCIdentifierRelations(item, relationParts, itemRelations, session);
		setNHMRCIdentifierRelations(item, relationParts, itemRelations, session);
		
		boolean hasValue = false;
		while (it.hasNext()) {
			ItemRelation relation = it.next();
			
			for (ItemRelation itemRelation : itemRelations) {
				if (relation.getId().equals(itemRelation.getId())) {
					if (Boolean.TRUE.equals(relation.getUserUpdated())) {
						itemRelation.setUserUpdated(Boolean.TRUE);
					}
					hasValue = true;
				}
			}
			if (!hasValue && Boolean.TRUE.equals(relation.getUserUpdated())) {
				itemRelations.add(relation);
			}
			else if (!hasValue) {
				it.remove();
				session.delete(relation);
			}
			
			hasValue = false;
		}
		item.setItemRelationsForIid(new HashSet<ItemRelation>(itemRelations));
	}
	
	/**
	 * Get a list of relationship parts from the relations
	 * 
	 * @param dublinCore The dublin core
	 * @return The relationship parts
	 */
	private List<RelationPart> getRelationPartsList(DublinCore dublinCore) {
		List<RelationPart> relationParts = new ArrayList<RelationPart>();
		
		RelationPart relationPart = null;
		for (String relation : dublinCore.getRelations()) {
			String[] relParts = getRelationParts(relation);
			relationPart = new RelationPart(relParts[1], relParts[0]);
			LOGGER.debug("Relation: {}, {}", relationPart.getValue(), relationPart.getType());
			relationParts.add(relationPart);
		}
		LOGGER.debug("Number of relations: {}", relationParts.size());
		
		return relationParts;
	}
	
	/**
	 * Add relationships based on identifiers in the item
	 * 
	 * @param item The item to add relationships to
	 * @param relationParts The relationship parts
	 * @param itemRelations The existing relationships
	 * @param session The session
	 */
	private void setIdentifierRelations(Item item, List<RelationPart> relationParts, List<ItemRelation> itemRelations, Session session) {
		Query query = session.createQuery("SELECT i FROM Item i join i.itemAttributes ia WHERE ia.attrType = :attrType AND ia.attrValue = :attrValue");
		query.setParameter("attrType", StoreAttributes.IDENTIFIER);
		
		for (RelationPart relationPart : relationParts) {
			query.setParameter("attrValue", relationPart.getValue());
			LOGGER.debug("Item: {}, Value: {}", relationPart.getValue(), relationPart.getType());
			
			List<Item> relItems = query.list();
			
			String relationType = getRelationType(relationPart.getType());
			
			for (Item relItem : relItems) {
				ItemRelationId itemRelationId = new ItemRelationId(item.getIid(), relationType, relItem.getIid());
				ItemRelation itemRelation = new ItemRelation();
				itemRelation.setId(itemRelationId);
				itemRelations.add(itemRelation);
			}
		}
	}
	
	/**
	 * Set the Australian Research Council relationships
	 * 
	 * @param item The item to add the relationship to
	 * @param relationParts The relationship parts
	 * @param itemRelations The existing relationships
	 * @param session The session object
	 */
	private void setARCIdentifierRelations(Item item, List<RelationPart> relationParts, List<ItemRelation> itemRelations, Session session) {
		String arcPrefix = StoreProperties.getProperty("arc.prefix");
		String arcTitle = StoreProperties.getProperty("arc.grant.title");
		
		setGrantIdentifierRelations(item, relationParts, itemRelations, session, arcPrefix, arcTitle);
	}
	
	/**
	 * Set the National Health and Medical Research Council relationships
	 * 
	 * @param item The item to add the relationship to
	 * @param relationParts The relationship parts
	 * @param itemRelations The existing relationships
	 * @param session The session object
	 */
	private void setNHMRCIdentifierRelations(Item item, List<RelationPart> relationParts, List<ItemRelation> itemRelations, Session session) {
		String nhmrcPrefix = StoreProperties.getProperty("nhmrc.prefix");
		String nhmrcTitle = StoreProperties.getProperty("nhmrc.grant.title");
		
		setGrantIdentifierRelations(item, relationParts, itemRelations, session, nhmrcPrefix, nhmrcTitle);
	}
	
	/**
	 * Set the grant identifier relationships
	 * 
	 * @param item The item to add relationships to
	 * @param relationParts The relationship parts
	 * @param session The session
	 * @param fundsPrefix The prefix of the grant
	 * @param fundsProvider The name of the funds provider
	 */
	private void setGrantIdentifierRelations(Item item, List<RelationPart> relationParts, List<ItemRelation> itemRelations, Session session, String fundsPrefix, String fundsProvider) {
		Query query = session.createQuery("SELECT gr from GrantItem gr join gr.itemAttributes fundProv join gr.itemAttributes refNum where fundProv.attrType = :fundType and fundProv.attrValue = :fundProvValue and refNum.attrType = :refType and refNum.attrValue = :refValue");
		query.setParameter("fundType", StoreAttributes.FUNDS_PROVIDER);
		query.setParameter("refType", StoreAttributes.REFERENCE_NUMBER);
		query.setParameter("fundProvValue", fundsProvider);

		int prefixLength = fundsPrefix.length();
		for (RelationPart relationPart : relationParts) {
			LOGGER.debug("Relation: {}, {}", relationPart.getValue(), relationPart.getType());
			if (relationPart.getValue().startsWith(fundsPrefix)) {
				LOGGER.debug("Is a Grant: {}", relationPart.getValue());
				String id = relationPart.getValue().substring(prefixLength);
				query.setParameter("refValue", id);
				List<Item> grants = query.list();
				String relationType = getRelationType(relationPart.getType());
				for (Item grant : grants) {
					LOGGER.debug("ID: {}, Title: {}", grant.getIid(), grant.getTitle());
					ItemRelationId itemRelationId = new ItemRelationId(item.getIid(), relationType, grant.getIid());
					ItemRelation itemRelation = new ItemRelation();
					itemRelation.setId(itemRelationId);
					itemRelations.add(itemRelation);
				}
			}
		}
	}
	
	/**
	 * Set the National Library of Australia relationships
	 * 
	 * @param item The item to add the relationships to
	 * @param relationParts The relationship parts
	 * @param session The session object
	 */
	/**
	 * Set the National Library of Australia relationships
	 * 
	 * @param item The item to add the relationships to
	 * @param relationParts The relationship parts
	 * @param itemRelations The existing relationships
	 * @param session The session object
	 */
	private void setNLAIdentifierRelations(Item item, List<RelationPart> relationParts, List<ItemRelation> itemRelations, Session session) {
		String nlaPrefix = StoreProperties.getProperty("nla.prefix");
		
		Query query = session.createQuery("SELECT p FROM PersonItem p join p.itemAttributes nlaId WHERE nlaId.attrType = :nlaType and nlaId.attrValue = :nlaValue");
		query.setParameter("nlaType", StoreAttributes.NLA_ID);

		for (RelationPart relationPart : relationParts) {
			LOGGER.debug("Relation: {}, {}", relationPart.getValue(), relationPart.getType());
			if (relationPart.getValue().startsWith(nlaPrefix)) {
				LOGGER.debug("Is a nla identifier: {}", relationPart.getValue());
				query.setParameter("nlaValue", relationPart.getValue());
				List<Item> people = query.list();
				String relationType = getRelationType(relationPart.getType());
				for (Item person : people) {
					LOGGER.debug("ID: {}, Title: {}", person.getIid(), person.getTitle());
					ItemRelationId itemRelationId = new ItemRelationId(item.getIid(), relationType, person.getIid());
					ItemRelation itemRelation = new ItemRelation();
					itemRelation.setId(itemRelationId);
					itemRelations.add(itemRelation);
				}
			}
		}
		LOGGER.debug("Item Relation Size: {}", item.getItemRelationsForIid().size());
	}

	/**
	 * Set the reverse relationships
	 * 
	 * @param item The dublin core item to set the relation for
	 * @param dublinCore the dublin core to set relations for
	 * @param session The hibernate session
	 */
	protected void setReverseRelations(DublinCoreItem item, DublinCore dublinCore, Session session2) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM ItemAttribute WHERE attrType = :attrType AND attrValue = :attrValue");
		query.setParameter("attrType", StoreAttributes.RELATION_VALUE);
		dublinCore.getIdentifiers();
		String identifier = null;
		for (String id : dublinCore.getIdentifiers()) {
			if (id.startsWith(StoreProperties.getProperty("relation.id.format.datacommons"))) {
				identifier = id;
				break;
			}
		}
		if (identifier != null) {
			query.setParameter("attrValue", identifier);
			List<ItemAttribute> relatedAttributes = query.list();
			
			LOGGER.debug("Number of reverse relationships: {}", relatedAttributes.size());
			ItemRelationId id = null;
			for (ItemAttribute relatedAttribute : relatedAttributes) {
				String relationText = relatedAttribute.getItemAttribute().getAttrValue();
				
				String[] relationParts = getRelationParts(relationText);
				String relationType = getRelationType(relationParts[0]);
				
				id = new ItemRelationId(relatedAttribute.getItem().getIid(), relationType, item.getIid());
				ItemRelation relation = (ItemRelation) session.get(ItemRelation.class, id);
				if (relation == null) {
					LOGGER.debug("Does not have relation");
					ItemRelation newRelation = new ItemRelation();
					newRelation.setId(id);
					item.getItemRelationsForRelatedIid().add(newRelation);
				}
				else {
					LOGGER.debug("has relation");
				}
			}
		}
		session.close();
	}
	
	/**
	 * Get the type of relationship
	 * 
	 * @param relation The relation to find the relationship type for
	 * @return The relationship type
	 */
	private String getRelationType(String relation) {
		String relationType = null;
		if (relation != null && relation.trim().length() > 0) {
			RelationMapping mapping = Mappings.getMappingByDescription(relation.trim());
			if (mapping == null) {
				relationType = relation.trim();
			}
			else {
				relationType = mapping.getCode();
			}
		}
		return relationType;
	}
}
