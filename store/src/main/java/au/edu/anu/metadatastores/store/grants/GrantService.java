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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.HistItemAttribute;
import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.ItemAttribute;
import au.edu.anu.metadatastores.datamodel.store.ItemRelation;
import au.edu.anu.metadatastores.datamodel.store.ItemRelationId;
import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;
import au.edu.anu.metadatastores.services.aries.ANUActivity;
import au.edu.anu.metadatastores.services.aries.AriesService;
import au.edu.anu.metadatastores.services.store.StoreHibernateUtil;
import au.edu.anu.metadatastores.store.misc.AbstractItemService;
import au.edu.anu.metadatastores.store.misc.Subject;
import au.edu.anu.metadatastores.store.people.Person;
import au.edu.anu.metadatastores.store.people.PersonItem;
import au.edu.anu.metadatastores.store.people.PersonService;

/**
 * GrantService
 * 
 * The Australian National University
 * 
 * Service class to retrieve and update grant information
 * 
 * @author Genevieve Turner
 *
 */
public class GrantService extends AbstractItemService {
	static final Logger LOGGER = LoggerFactory.getLogger(GrantService.class);
	
	private static GrantService singleton_;
	private AriesService ariesService_ = AriesService.getSingleton();
	private PersonService personService_ = PersonService.getSingleton();
	
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
	 * @return
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
			
			grants.add(grant);
		}
		
		return grants;
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
	 * Save the grant information
	 * 
	 * @param grant The grant to save
	 * @param userUpdated Indicator of whether it is user updated or system updated
	 * @return The GrantItem that has been created/updated
	 */
	public GrantItem saveGrant(Grant grant, Boolean userUpdated) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		session.enableFilter("attributes");
		session.beginTransaction();
		
		Query query = session.createQuery("from GrantItem where extId = :extId");
		query.setParameter("extId", grant.getContractCode());
		
		GrantItem item =(GrantItem) query.uniqueResult();
		
		if (item == null) {
			item = new GrantItem();
			item.setExtId(grant.getContractCode());
			item.setTitle(grant.getTitle());
			session.save(item);
		}
		else {
			item.setTitle(grant.getTitle());
		}
		
		Date lastModified = new Date();
		LOGGER.debug("Number of item attriubtes before: {}", item.getItemAttributes().size());
		
		setSingleAttribute(item, item.getContractCodes(), grant.getContractCode(), StoreAttributes.CONTRACT_CODE, session, lastModified, userUpdated);
		setSingleAttribute(item, item.getGrantTitles(), grant.getTitle(), StoreAttributes.TITLE, session, lastModified, userUpdated);
		setSingleAttribute(item, item.getStartDates(), grant.getStartDate(), StoreAttributes.START_DATE, session, lastModified, userUpdated);
		setSingleAttribute(item, item.getEndDates(), grant.getEndDate(), StoreAttributes.END_DATE, session, lastModified, userUpdated);
		setSingleAttribute(item, item.getStatus(), grant.getStatus(), StoreAttributes.STATUS, session, lastModified, userUpdated);
		setSingleAttribute(item, item.getFundsProviders(), grant.getFundsProvider(), StoreAttributes.FUNDS_PROVIDER, session, lastModified, userUpdated);
		setSingleAttribute(item, item.getReferenceNumbers(), grant.getReferenceNumber(), StoreAttributes.REFERENCE_NUMBER, session, lastModified, userUpdated);
		
		if (grant.getFirstInvestigator() != null) {
			setSingleAttribute(item, item.getFirstInvestigatorIds(), grant.getFirstInvestigator().getExtId(), StoreAttributes.FIRST_INVESTIGATOR_ID, session, lastModified, userUpdated);
		}
		
		setForSubjectsForSave(item, item.getAnzforSubjects(), grant.getAnzforSubjects(), session, lastModified, userUpdated);
		
		associatePeople(item, grant, session);
		
		LOGGER.debug("Number of Item Attributes after: {}", item.getItemAttributes().size());
		
		try {
			item = (GrantItem) session.merge(item);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			LOGGER.error("Error Merging Item {}", item.getIid(), e);
			LOGGER.info("Error with item: {}, Title: {}, System: {}, Ext Id: {}", item.getIid(), item.getTitle(), item.getExtSystem(), item.getExtId());
			for (ItemAttribute attr : item.getItemAttributes()) {
				LOGGER.info("AID: {}, IID: {}, Type: {}, Value: {}", new Object[] {attr.getAid(), attr.getItem().getIid(), attr.getAttrType(), attr.getAttrValue()});
			}
			for (HistItemAttribute attr : item.getHistItemAttributes()) {
				LOGGER.info("AID: {}, Date: {}, Type: {}, Value: {}", new Object[] {attr.getId().getAid(), attr.getId().getHistDatetime(), attr.getAttrType(), attr.getAttrValue()});
			}
		}
		
		session.close();
		
		return item;
	}
	
	/**
	 * Add the people involved with the grant
	 * 
	 * @param item The grant item to add people to
	 * @param grant The grant that holds the people who need to be associated
	 * @param session The session
	 */
	private void associatePeople(GrantItem item, Grant grant, Session session) {
		//TODO add the removing of people who are no longer associated
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
					relation = new ItemRelation();
					relation.setId(relationId);
					item.getItemRelationsForIid().add(relation);
				}
			}
			else {
				LOGGER.error("Error finding and saving person with an id of: {}", person.getExtId());
			}
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
		session.enableFilter("attributes");
		
		Query query = session.createQuery("FROM GrantItem WHERE extId = :extId");
		query.setParameter("extId", grantId);
		
		GrantItem item = (GrantItem) query.uniqueResult();
		Grant grant = getGrant(item);
		
		session.close();
		
		return grant;
	}
	
	/**
	 * Return the Grant from the given GrantItem
	 * 
	 * @param item The grant item to process
	 * @return The grant
	 */
	public Grant getGrant(GrantItem item) {
		Grant grant = new Grant();
		String contractCode = getSingleAttributeValue(item.getContractCodes());
		grant.setContractCode(contractCode);

		String title = getSingleAttributeValue(item.getGrantTitles());
		grant.setTitle(title);
		
		String startDate = getSingleAttributeValue(item.getStartDates());
		grant.setStartDate(startDate);
		
		String endDate = getSingleAttributeValue(item.getEndDates());
		grant.setEndDate(endDate);
		
		String status = getSingleAttributeValue(item.getStatus());
		grant.setStatus(status);
		
		String firstInvestigatorId = getSingleAttributeValue(item.getFirstInvestigatorIds());
		Person firstInvestigator = personService_.getBasicPerson(firstInvestigatorId);
		grant.setFirstInvestigator(firstInvestigator);
		
		String fundsProvider = getSingleAttributeValue(item.getFundsProviders());
		grant.setFundsProvider(fundsProvider);
		
		String referenceNumber = getSingleAttributeValue(item.getReferenceNumbers());
		grant.setReferenceNumber(referenceNumber);
		
		for (ItemAttribute attr : item.getAnzforSubjects()) {
			Subject subject = new Subject();
			for (ItemAttribute subjectAttr : attr.getItemAttributes()) {
				if (checkIfAttribute(subjectAttr, StoreAttributes.FOR_CODE)) {
					subject.setCode(subjectAttr.getAttrValue());
				}
				else if (checkIfAttribute(subjectAttr, StoreAttributes.FOR_PERCENT)) {
					subject.setPercentage(subjectAttr.getAttrValue());
				}
				else if (checkIfAttribute(subjectAttr, StoreAttributes.FOR_VALUE)) {
					subject.setValue(subjectAttr.getAttrValue());
				}
			}
			grant.getAnzforSubjects().add(subject);
		}
		
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
	 * Check if the attribute is the given type
	 * 
	 * @param attribute The attribute to check the type of
	 * @param type The type to check
	 * @return Whether the attribute is of that type
	 */
	private boolean checkIfAttribute(ItemAttribute attribute, String type) {
		return attribute.getAttrType().equals(type);
	}
	
	/**
	 * Retrieves a list of grants associated with the person with the given id
	 * 
	 * @param staffId The staff id of the person to retrieve grants for
	 * @return The list of grants
	 */
	public List<Grant> getGrantsForPerson(String staffId) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		session.enableFilter("attributes");
		
		Query query = session.createQuery("SELECT grant FROM GrantItem grant, PersonItem person join person.itemRelationsForRelatedIid personRelation WHERE personRelation.itemByIid = grant and person.extId = :staffId");
		query.setParameter("staffId", staffId);
		List<GrantItem> grantItems = query.list();
		List<Grant> grants = new ArrayList<Grant>();
		Grant grant = null;
		for (GrantItem grantItem : grantItems) {
			grant = getGrant(grantItem);
			if (grant != null) {
				grants.add(grant);
			}
		}
		
		session.close();
		
		return grants;
	}
}
