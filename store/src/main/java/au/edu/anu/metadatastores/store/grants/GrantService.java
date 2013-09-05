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

package au.edu.anu.metadatastores.store.grants;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.HistItemAttribute;
import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.ItemAttribute;
import au.edu.anu.metadatastores.datamodel.store.ItemRelation;
import au.edu.anu.metadatastores.datamodel.store.ItemRelationId;
import au.edu.anu.metadatastores.datamodel.store.annotations.ItemTraitParser;
import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;
import au.edu.anu.metadatastores.services.ands.ANDSService;
import au.edu.anu.metadatastores.services.aries.ANUActivity;
import au.edu.anu.metadatastores.services.aries.AriesService;
import au.edu.anu.metadatastores.services.store.StoreHibernateUtil;
import au.edu.anu.metadatastores.store.misc.AbstractItemService;
import au.edu.anu.metadatastores.store.misc.Subject;
import au.edu.anu.metadatastores.store.people.Person;
import au.edu.anu.metadatastores.store.people.PersonItem;
import au.edu.anu.metadatastores.store.people.PersonService;
import au.edu.anu.metadatastores.store.properties.StoreProperties;

/**
 * <p>GrantService<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Service class to retrieve and update grant information</p>
 * 
 * @author Genevieve Turner
 *
 */
public class GrantService extends AbstractItemService {
	static final Logger LOGGER = LoggerFactory.getLogger(GrantService.class);
	
	private static GrantService singleton_;
	private AriesService ariesService_ = AriesService.getSingleton();
	private PersonService personService_ = PersonService.getSingleton();
	private ANDSService andsService_ = ANDSService.getSingleton();

	private static final String ARC_FUNDS_PROVIDER = StoreProperties.getProperty("arc.grant.title");
	private static final String NHMRC_FUNDS_PROVIDER = StoreProperties.getProperty("nhmrc.grant.title");
	private static final String ARC_PREFIX = StoreProperties.getProperty("arc.prefix");
	private static final String NHMRC_PREFIX = StoreProperties.getProperty("nhmrc.prefix");
	
	/**
	 * Main class to execute grants
	 * 
	 * @param args Arguments
	 */
	public static void main(String[] args) {
		GrantService grantService = GrantService.getSingleton();
		for (int i = 0; i < args.length; i++) {
			System.out.println("Argument " + i + ": " + args[i]);
			if (args[i].startsWith("u") || args[i].startsWith("a") || args[i].startsWith("f")) {
				List<Grant> grants = grantService.fetchGrantsForPerson(args[i]);
				for (Grant grant : grants) {
					grantService.saveGrant(grant);
				}
			}
		}
	}
	
	/**
	 * Constructor
	 */
	private GrantService() {
		
	}
	
	/**
	 * Get the GrantService instance
	 * 
	 * @return  The GrantService
	 */
	public static GrantService getSingleton() {
		if (singleton_ == null) {
			singleton_ = new GrantService();
		}
		return singleton_;
	}
	
	/**
	 * Fetches grant information from the Aries Database
	 * 
	 * @param staffId The id of the staff member to find grants for
	 * @return A list of grants associated with the given staff member
	 */
	public List<Grant> fetchGrantsForPerson(String staffId) {
		ANUActivity[] activities = ariesService_.getContracts(staffId);
		
		List<Grant> grants = new ArrayList<Grant>();
		
		Grant grant = null;
		for (ANUActivity activity : activities) {
			grant = new Grant();
			grant.setContractCode(activity.getActivityId());
			grant.setTitle(activity.getActivityTitle());
			Person firstInvestigator = new Person();
			firstInvestigator.setExtId(activity.getFirstInvestigatorId());
			grant.setFirstInvestigator(firstInvestigator);
			Person person = null;
			List<Person> people = new ArrayList<Person>();
			for (String investigatorId : activity.getInvestigators()) {
				person = new Person();
				person.setExtId(investigatorId);
				people.add(person);
			}
			grant.setAssociatedPeople(people);
			grant.setStartDate(activity.getStartDate());
			grant.setEndDate(activity.getEndDate());
			grant.setStatus(activity.getStatus());
			grant.setFundsProvider(activity.getFundsProvider());
			grant.setReferenceNumber(activity.getSchemeReference());
			
			if (activity.getForSubject1() != null) {
				Subject subject = new Subject(activity.getForSubject1().getCode(), activity.getForSubject1().getDescription(), activity.getForSubject1().getPercentage());
				grant.getAnzforSubjects().add(subject);
			}
			if (activity.getForSubject2() != null) {
				Subject subject = new Subject(activity.getForSubject2().getCode(), activity.getForSubject2().getDescription(), activity.getForSubject2().getPercentage());
				grant.getAnzforSubjects().add(subject);
			}
			if (activity.getForSubject3() != null) {
				Subject subject = new Subject(activity.getForSubject3().getCode(), activity.getForSubject3().getDescription(), activity.getForSubject3().getPercentage());
				grant.getAnzforSubjects().add(subject);
			}
			
			grant.setDescription(getDescription(grant));
			
			grants.add(grant);
		}
		
		return grants;
	}
	
	/**
	 * Set the description.
	 * 
	 * The information for this field is retrieved from ANDS
	 * 
	 * @param grant The grant to retrieve the description for
	 * @return The description
	 */
	private String getDescription(Grant grant) {
		String description = null;
		if (grant.getFundsProvider() != null && grant.getFundsProvider().trim().length() > 0 
				&& grant.getReferenceNumber() != null && grant.getReferenceNumber().trim().length() > 0) {
			if (ARC_FUNDS_PROVIDER.equals(grant.getFundsProvider())) {
				String key = ARC_PREFIX + grant.getReferenceNumber();
				description = andsService_.getActivityDescription(key);
			}
			else if (NHMRC_FUNDS_PROVIDER.equals(grant.getFundsProvider())) {
				String key = NHMRC_PREFIX + grant.getReferenceNumber();
				description = andsService_.getActivityDescription(key);
			}
		}
		
		return description;
	}
	
	/**
	 * Save the given grant and make the appropriate associations
	 * 
	 * @param grant The grant to save
	 * @return The grant item that has been created/updated
	 */
	public GrantItem saveGrant(Grant grant) {
		return saveGrant(grant, Boolean.FALSE);
	}
	
	/**
	 * Save a list of grants
	 * 
	 * @param grants The grants to save
	 * @return THe saved grant items
	 */
	public List<GrantItem> saveGrants(List<Grant> grants) {
		List<GrantItem> grantItems = new ArrayList<GrantItem>();
		GrantItem grantItem = null;
		
		for (Grant grant : grants) {
			grantItem = saveGrant(grant);
			grantItems.add(grantItem);
		}
		
		return grantItems;
	}

	/**
	 * Save the grant information
	 * 
	 * @param grant The grant to save
	 * @param userUpdated Indicator of whether it is user updated or system updated
	 * @return The GrantItem that has been created/updated
	 */
	public GrantItem saveGrant(Grant grant, Boolean userUpdated) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		
		GrantItem item = null;
		try {
			session.enableFilter("attributes");
			session.beginTransaction();
			
			Query query = session.createQuery("from GrantItem where extId = :extId");
			query.setParameter("extId", grant.getContractCode());
			
			item =(GrantItem) query.uniqueResult();
		
			Date lastModified = new Date();
			ItemTraitParser parser = new ItemTraitParser();
			Item newItem = null;
			newItem = parser.getItem(grant, lastModified);
			
			if (item == null) {
				item = new GrantItem();
				if (newItem.getExtId() == null) {
					return null;
				}
				item.setExtId(newItem.getExtId());
				session.save(item);
			}
			
			updateAttributesFromItem(item, newItem, session, lastModified);

			LOGGER.debug("Number of item attributes before: {}", item.getItemAttributes().size());
			
			associatePeople(item, grant, session);
			
			LOGGER.debug("Number of Item Attributes after: {}", item.getItemAttributes().size());
			
			item = (GrantItem) session.merge(item);
			session.getTransaction().commit();
		}
		catch(IllegalAccessException e) {
			LOGGER.error("Exception accessing field when trying to get a grant item", e);
		}
		catch (InvocationTargetException e) {
			LOGGER.error("Exception invoking method when trying to get a grant item", e);
		}
		catch (Exception e) {
			if (item == null) {
				LOGGER.error("Exception querying item", e);
			}
			else {
				LOGGER.error("Error Merging Item {}", item.getIid(), e);
				LOGGER.info("Error with item: {}, Title: {}, System: {}, Ext Id: {}", item.getIid(), item.getTitle(), item.getExtSystem(), item.getExtId());
				for (ItemAttribute attr : item.getItemAttributes()) {
					LOGGER.info("AID: {}, IID: {}, Type: {}, Value: {}", new Object[] {attr.getAid(), attr.getItem().getIid(), attr.getAttrType(), attr.getAttrValue()});
				}
				for (HistItemAttribute attr : item.getHistItemAttributes()) {
					LOGGER.info("AID: {}, Date: {}, Type: {}, Value: {}", new Object[] {attr.getId().getAid(), attr.getId().getHistDatetime(), attr.getAttrType(), attr.getAttrValue()});
				}
			}
		}
		finally {
			session.close();
		}
		
		
		return item;
	}
	
	/**
	 * Add the people involved with the grant
	 * 
	 * @param item The grant item to add people to
	 * @param grant The grant that holds the people who need to be associated
	 * @param session The session
	 */
	private void associatePeople(GrantItem item, Grant grant, Session session2) {
		//TODO add the removing of people who are no longer associated
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			LOGGER.debug("Associate People");
			for (Person person : grant.getAssociatedPeople()) {
				PersonItem personItem = personService_.getPersonItem(person.getExtId());
				ItemRelationId relationId = null;
				if (personItem != null) {
					if (grant.getFirstInvestigator() != null && person.getExtId().equals(grant.getFirstInvestigator().getExtId())) {
						relationId = new ItemRelationId(item.getIid(), "hasPrincipalInvestigator", personItem.getIid());
					}
					else {
						relationId = new ItemRelationId(item.getIid(), "hasAssociationWith", personItem.getIid());
					}
					ItemRelation relation = (ItemRelation) session.get(ItemRelation.class, relationId);
					if (relation == null) {
						LOGGER.debug("Adding Relation: {}, {}, {}", relationId.getIid(), relationId.getRelatedIid(), relationId.getRelationValue());
						relation = new ItemRelation();
						relation.setId(relationId);
						item.getItemRelationsForIid().add(relation);
					}
					else {
						LOGGER.debug("Found Relation: {}, {}, {}", relationId.getIid(), relationId.getRelatedIid(), relationId.getRelationValue());
					}
				}
				else {
					LOGGER.error("Error finding and saving person with an id of: {}", person.getExtId());
				}
			}
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Return the Grant with the given id
	 * 
	 * @param grantId The id of the grant to retrieve
	 * @return The grant information
	 */
	public Grant getGrant(String grantId) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			session.enableFilter("attributes");
			
			Query query = session.createQuery("FROM GrantItem WHERE extId = :extId");
			query.setParameter("extId", grantId);
			
			GrantItem item = (GrantItem) query.uniqueResult();
			Grant grant = getGrant(item);
			return grant;
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Return the Grant from the given GrantItem
	 * 
	 * @param item The grant item to process
	 * @return The grant
	 */
	public Grant getGrant(GrantItem item) {
		Grant grant = new Grant();
		
		ItemTraitParser traitParser = new ItemTraitParser();
		try {
			grant = (Grant) traitParser.getItemObject(item, Grant.class);
		}
		catch (Exception e) {
			LOGGER.error("Exception getting grant", e);
		}
		
		String firstInvestigatorId = getSingleAttributeValue(item, StoreAttributes.FIRST_INVESTIGATOR_ID);
		Person firstInvestigator = personService_.getBasicPerson(firstInvestigatorId);
		grant.setFirstInvestigator(firstInvestigator);
		
		LOGGER.debug("Number of subjects?: {}", item.getAnzforSubjects().size());
		
		for (ItemRelation relation : item.getItemRelationsForIid()) {
			Item relatedItem = relation.getItemByRelatedIid();
			if (relatedItem instanceof PersonItem) {
				PersonItem personItem = (PersonItem) relatedItem;
				Person person = personService_.getBasicPerson(personItem);
				grant.getAssociatedPeople().add(person);
			}
		}
		
		return grant;
	}
	
	/**
	 * Retrieves a list of grants associated with the person with the given id
	 * 
	 * @param staffId The staff id of the person to retrieve grants for
	 * @return The list of grants
	 */
	public List<Grant> getGrantsForPerson(String staffId) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			session.enableFilter("attributes");
			
			Query query = session.createQuery("SELECT grant FROM GrantItem grant, PersonItem person join person.itemRelationsForRelatedIid personRelation WHERE personRelation.itemByIid = grant and person.extId = :staffId");
			query.setParameter("staffId", staffId);
			@SuppressWarnings("unchecked")
			List<GrantItem> grantItems = query.list();
			List<Grant> grants = new ArrayList<Grant>();
			Grant grant = null;
			for (GrantItem grantItem : grantItems) {
				grant = getGrant(grantItem);
				if (grant != null) {
					grants.add(grant);
				}
			}
			
			return grants;
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Find grants with the given attributes and attribute values
	 * 
	 * @param attributes The attributes to query on
	 * @return The grants
	 */
	public List<Grant> queryGrantsByAttributes(Map<String, String> attributes) {
		List<Grant> grants = new ArrayList<Grant>();
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		session.enableFilter("attributes");

		try {
			List<String> parameters = new ArrayList<String>();
			
			StringBuilder fromString = new StringBuilder();
			StringBuilder whereString = new StringBuilder();
			
			fromString.append(" FROM GrantItem gi");
			whereString.append(" WHERE");
			int i = 0;
			for (Entry<String, String> entry : attributes.entrySet()) {
				fromString.append(" LEFT JOIN gi.itemAttributes gia");
				fromString.append(i);
				if (i > 0) {
					whereString.append(" AND");
				}
				whereString.append(" gia");
				whereString.append(i);
				whereString.append(".attrType = ? AND lower(gia");
				whereString.append(i);
				whereString.append(".attrValue) like ?");
				parameters.add(entry.getKey());
				parameters.add("%" + entry.getValue().toLowerCase() + "%");
				
				i++;
			}
			String queryString = "SELECT gi " + fromString.toString() + " " + whereString.toString();
			LOGGER.info("Query: {}", queryString);
			LOGGER.info("Number of parameters: {}", parameters.size());
			Query query = session.createQuery(queryString);
			for (i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
			
			@SuppressWarnings("unchecked")
			List<GrantItem> grantItems = query.list();
			Grant grant = null;
			for (GrantItem grantItem : grantItems) {
				grant = getGrant(grantItem);
				grants.add(grant);
			}
		}
		finally {
			session.close();
		}
		
		return grants;
	}
}
