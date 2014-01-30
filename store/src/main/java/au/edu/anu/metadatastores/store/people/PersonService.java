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

package au.edu.anu.metadatastores.store.people;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.NamingException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.annotations.ItemTraitParser;
import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;
import au.edu.anu.metadatastores.ldap.LdapPerson;
import au.edu.anu.metadatastores.services.aries.ANUStaff;
import au.edu.anu.metadatastores.services.aries.AriesService;
import au.edu.anu.metadatastores.services.aries.ExternalStaff;
import au.edu.anu.metadatastores.services.ldap.LdapService;
import au.edu.anu.metadatastores.services.store.StoreHibernateUtil;
import au.edu.anu.metadatastores.store.misc.AbstractItemService;
import au.edu.anu.metadatastores.store.misc.Subject;

/**
 * <p>PersonService<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Service class to retrieve and save information about people</p>
 * 
 * @author Genevieve Turner
 *
 */
public class PersonService extends AbstractItemService {
	static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
	
	private static PersonService singleton_;
	LdapService ldapService_ = LdapService.getSingleton();
	AriesService ariesService_ = AriesService.getSingleton();
	
	/**
	 * Main class that takes user ids and finds information about the person
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		PersonService personService = PersonService.getSingleton();
		for (int i = 0; i < args.length; i++) {
			System.out.println("Argument " + i + ": " + args[i]);
			if (args[i].startsWith("u") || args[i].startsWith("a") || args[i].startsWith("f")) {
				Person person = personService.fetchPersonInformation(args[i]);
				System.out.println("Name: " + person.getGivenName() + " " + person.getSurname());
				personService.savePerson(person);
			}
		}
		System.out.println("Updates Complete");
	}
	
	/**
	 * Constructor class
	 */
	private PersonService() {
		
	}
	
	/**
	 * Get the singleton instance
	 * 
	 * @return Get the singleton object
	 */
	public static PersonService getSingleton() {
		if (singleton_ == null) {
			singleton_ = new PersonService();
		}
		return singleton_;
	}
	
	/**
	 * Fetch the information about the person with the given university id
	 * 
	 * @param uid The university id of the person to retrieve information for
	 * @return The person information
	 */
	public Person fetchPersonInformation(String uid) {
		Person person = new Person();
		LdapPerson ldapPerson = null;
		person.setUid(uid);
		person.setExtId(uid);
		
		try {
			ldapPerson = ldapService_.getANUPartyLdapInfo(uid);
			if (ldapPerson != null) {
				person.setGivenName(ldapPerson.getGivenName());
				person.setSurname(ldapPerson.getSurname());
				person.setEmail(ldapPerson.getEmail());
				
				person.setPhoneNumbers(getArrayList(ldapPerson.getPhone()));
				person.setFaxNumbers(getArrayList(ldapPerson.getFax()));
				person.setJobTitle(ldapPerson.getJobTitle());
				person.setOrganisationalUnit(ldapPerson.getOrganisationalUnit());
				person.setPreferredName(ldapPerson.getPreferredName());
				person.setDisplayName(ldapPerson.getDisplayName());
				person.setStaffType(ldapPerson.getStaffType());
				person.setIsActive(Boolean.TRUE.toString());
			}
			else {
				person.setIsActive(Boolean.FALSE.toString());
			}
		}
		catch (NamingException e) {
			LOGGER.error("Error retrieving information from ldap", e);
		}
		
		ANUStaff staff = ariesService_.getStaffInformation(uid);
		if (staff != null) {
			if (ldapPerson == null) {
				person.setGivenName(staff.getGivenName());
				person.setSurname(staff.getSurname());
				person.setEmail(staff.getEmail());
			}
			person.setAriesId(Integer.valueOf(staff.getAriesId()).toString());
			
			if (staff.getForSubject1() != null) {
				Subject subject = new Subject(staff.getForSubject1().getCode(), staff.getForSubject1().getDescription(), staff.getForSubject1().getPercentage());
				person.getAnzforSubjects().add(subject);
			}
			
			if (staff.getForSubject2() != null) {
				Subject subject = new Subject(staff.getForSubject2().getCode(), staff.getForSubject2().getDescription(), staff.getForSubject2().getPercentage());
				person.getAnzforSubjects().add(subject);
			}
			
			if (staff.getForSubject3() != null) {
				Subject subject = new Subject(staff.getForSubject3().getCode(), staff.getForSubject3().getDescription(), staff.getForSubject3().getPercentage());
				person.getAnzforSubjects().add(subject);
			}
			
			if (staff.getDepartmentName() != null) {
				person.setOrganisationalUnit(staff.getDepartmentName());
			}
		}
		
		return person;
	}
	
	/**
	 * Transforms a String array to a String array list
	 * 
	 * @param values The array of values 
	 * @return The list of values
	 */
	private List<String> getArrayList(String[] values) {
		List<String> listValues = new ArrayList<String>();
		if (values != null) {
			for (String value : values) {
				listValues.add(value);
			}
		}
		
		return listValues;
	}
	
	/**
	 * Get the person information
	 * 
	 * @param uid The id of the person to find
	 * @return The person item information
	 */
	public PersonItem getPersonItem(String uid) {
		PersonItem personItem = queryPersonByUid(uid);
		if (personItem == null) {
			personItem = createPersonForAries(uid);
		}
		return personItem;
	}
	
	/**
	 * Find the people by name from the database
	 * 
	 * @param givenName The given name of the person
	 * @param surname The surname of the person
	 * @return A list of people with similar names
	 */
	public List<PersonItem> getPersonItemByName(String givenName, String surname) {
		List<PersonItem> people = queryPeopleByName(givenName, surname);
		if (people == null || people.size() == 0) {
			LOGGER.info("No people found. Searching other sources");
			people = getPeopleByName(givenName, surname);
		}
		return people;
	}
	
	/**
	 * Find people by name. This method first checks ldap, then aries database.
	 * 
	 * @param givenName The given name of the person
	 * @param surname The surname of the person
	 * @return A list of people with similar names
	 */
	private List<PersonItem> getPeopleByName(String givenName, String surname) {
		List<PersonItem> people = new ArrayList<PersonItem>();
		Person person = null;
		PersonItem personItem = null;
		try {
			String[] uniIds = ldapService_.searchForSimilarNames(surname, givenName);
			for (int i = 0; i < uniIds.length; i++) {
				person = fetchPersonInformation(uniIds[i]);
				personItem = savePerson(person);
				if (personItem != null) {
					people.add(personItem);
				}
			}
		}
		catch (NamingException e) {
			LOGGER.error("Error querying ldap", e);
		}
		
		if (people.size() == 0) {
			ANUStaff[] anuStaff = ariesService_.findANUStaffByName(surname, givenName);
			
			if (anuStaff == null || anuStaff.length == 0) {
				ExternalStaff[] externalStaff = ariesService_.findExternalStaffByName(surname, givenName);
				if (externalStaff == null || externalStaff.length == 0) {
					LOGGER.debug("Not found in external staff");
					person = createBasicPerson(givenName, surname);
					personItem = savePerson(person);
					if (personItem != null) {
						people.add(personItem);
					}
				}
				else {
					for (ExternalStaff staff : externalStaff) {
						person = setAriesExternalStaff(staff);
						personItem = savePerson(person);
						if (personItem != null) {
							people.add(personItem);
						}
					}
				}
			}
			else {
				for (ANUStaff staff : anuStaff) {
					person = setAriesANUStaff(staff);
					personItem = savePerson(person);
					if (personItem != null) {
						people.add(personItem);
					}
				}
			}
		}
		
		return people;
	}
	
	/**
	 * Generate a Person from the Aries ANU Staff information
	 * 
	 * @param staff The staff information
	 * @return The Person object
	 */
	private Person setAriesANUStaff(ANUStaff staff) {
		Person person = new Person();
		person.setGivenName(staff.getGivenName());
		person.setSurname(staff.getSurname());
		person.setEmail(staff.getEmail());
		person.setAriesId(Integer.valueOf(staff.getAriesId()).toString());
		
		if (staff.getForSubject1() != null) {
			Subject subject = new Subject(staff.getForSubject1().getCode(), staff.getForSubject1().getDescription(), staff.getForSubject1().getPercentage());
			person.getAnzforSubjects().add(subject);
		}
		
		if (staff.getForSubject2() != null) {
			Subject subject = new Subject(staff.getForSubject2().getCode(), staff.getForSubject2().getDescription(), staff.getForSubject2().getPercentage());
			person.getAnzforSubjects().add(subject);
		}
		
		if (staff.getForSubject3() != null) {
			Subject subject = new Subject(staff.getForSubject3().getCode(), staff.getForSubject3().getDescription(), staff.getForSubject3().getPercentage());
			person.getAnzforSubjects().add(subject);
		}
		
		if (staff.getDepartmentName() != null) {
			person.setOrganisationalUnit(staff.getDepartmentName());
		}
		return person;
	}
	
	/**
	 * Generate a Person from the Aries External Staff information
	 * 
	 * @param staff
	 * @return The external staff member
	 */
	private Person setAriesExternalStaff(ExternalStaff staff) {
		Person person = new Person();
		person.setExtId(staff.getAriesStaffId().toLowerCase());
		person.setGivenName(staff.getGivenName());
		person.setSurname(staff.getSurname());
		person.setStaffType("External");
		person.setAriesId(staff.getAriesStaffId());
		person.setInstitution(staff.getInstitution());
		person.setCountry(staff.getCountry());
		return person;
	}
	
	/**
	 * Create a stub of a person with just their name and a staff type of 'Unknown'
	 * 
	 * @param givenName The given name of the person
	 * @param surname The surname of the person
	 * @return The created person object
	 */
	private Person createBasicPerson(String givenName, String surname) {
		Person person = new Person();
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			Query idQuery = session.createSQLQuery("SELECT nextval('person_seq')");
			BigInteger id = (BigInteger) idQuery.uniqueResult();
	
			person.setExtId("mu" + id.toString());
			person.setGivenName(givenName);
			person.setSurname(surname);
			person.setStaffType("Unknown");
			
			return person;
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Find a person by a uid
	 * 
	 * @param uid The uid of the person to find
	 * @return Get the Item of the person with the given uid
	 */
	private PersonItem queryPersonByUid(String uid) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			session.enableFilter("attributes");
			
			Query query = session.createQuery("from PersonItem where lower(extId) = :extId");
			query.setParameter("extId", uid.toLowerCase());
			
			PersonItem personItem = (PersonItem) query.uniqueResult();
			
			return personItem;
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Find people by name
	 * 
	 * @param givenName The given name to search on
	 * @param surname The surname to search on
	 * @return The list of people with the name
	 */
	private List<PersonItem> queryPeopleByName(String givenName, String surname) {
		LOGGER.debug("Given Name: {}, Surname: {}", givenName, surname);
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			session.enableFilter("attributes");
			
			Query query = session.createQuery("select pi from PersonItem pi join pi.givenNames gn join pi.surnames sn where lower(gn.attrValue) = :givenName and lower(sn.attrValue) = :surname");
			query.setParameter("givenName", givenName.toLowerCase());
			query.setParameter("surname", surname.toLowerCase());
			
			@SuppressWarnings("unchecked")
			List<PersonItem> people = query.list();
			if (people != null) {
				LOGGER.debug("Number of people found in first query: {}", people.size());
			}
			else {
				LOGGER.debug("No people found in first query");
			}
			
			if (people == null || people.size() == 0) {
				query = session.createQuery("select pi from PersonItem pi join pi.commonNames cn where lower(cn.attrValue) = :commonName");
				query.setParameter("commonName", givenName.toLowerCase() + " " + surname.toLowerCase());
				
				people = query.list();
				if (people != null) {
					LOGGER.debug("Number of people found in second query: {}", people.size());
				}
				else {
					LOGGER.debug("No people found in second query");
				}
			}
			
			return people;
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Find people with the given attributes and attribute values
	 * 
	 * @param attributes The attribute/value pairs to search on
	 * @return A list of people with the given attributes
	 */
	public List<Person> queryPersonByAttributes(Map<String, String> attributes) {
		List<Person> people = new ArrayList<Person>();
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			List<String> parameters = new ArrayList<String>();
	
			StringBuilder fromString = new StringBuilder();
			StringBuilder whereString = new StringBuilder();
			
			fromString.append(" FROM PersonItem pi");
			whereString.append(" WHERE");
			int i = 0;
			for (Entry<String, String> entry : attributes.entrySet()) {
				fromString.append(" LEFT JOIN pi.itemAttributes pia");
				fromString.append(i);
				if (i > 0) {
					whereString.append(" AND");
				}
				whereString.append(" pia");
				whereString.append(i);
				whereString.append(".attrType = ? AND lower(pia");
				whereString.append(i);
				whereString.append(".attrValue) like ?");
				parameters.add(entry.getKey());
				parameters.add(entry.getValue().toLowerCase() + "%");
				
				i++;
			}
			String queryString = "SELECT pi " + fromString.toString() + " " + whereString.toString();
			LOGGER.debug("Query: {}", queryString);
			LOGGER.debug("Number of parameters: {}", parameters.size());
			Query query = session.createQuery(queryString);
			for (i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
			
			@SuppressWarnings("unchecked")
			List<PersonItem> personItems = query.list();
			
			Person person = null;
			for (PersonItem personItem : personItems) {
				person = getPerson(personItem, false);
				people.add(person);
			}
		}
		finally {
			session.close();
		}
		
		return people;
	}
	
	/**
	 * Create a person, fetching their information from aries via ANU people or External People
	 * 
	 * @param uid The unique id of the person to create
	 * @return The PersonItem that has been saved
	 */
	public PersonItem createPersonForAries(String uid) {
		Pattern pattern = Pattern.compile("^[afutAFUT](\\d*)");
		Matcher matcher = pattern.matcher(uid);
		Person person = null;
		if (matcher.find()) {
			uid = uid.toLowerCase();
			person = fetchPersonInformation(uid);
		}
		else {
			uid = uid.toUpperCase();
			person = fetchExternalUser(uid);
		}
		if (person != null) {
			savePerson(person);
		}
		
		return queryPersonByUid(uid);
	}
	
	/**
	 * Fetch information about staff via the external users
	 * 
	 * @param staffId The external id of the collaborating staff member
	 * @return The information about the staff member
	 */
	public Person fetchExternalUser(String staffId) {
		ExternalStaff staff = ariesService_.getExternalStaffInformation(staffId);
		if (staff != null) {
			return setAriesExternalStaff(staff);
		}
		return null;
	}
	
	/**
	 * Save the person's information
	 * 
	 * @param person The person to save
	 * @return The item information for the person
	 */
	public PersonItem savePerson(Person person) {
		return savePerson(person, Boolean.FALSE);
	}
	
	/**
	 * Save the person's information
	 * 
	 * @param person The person to save
	 * @param userUpdated Indicates whether the information is user updated or not
	 * @return The item information for the person
	 */
	public PersonItem savePerson(Person person, Boolean userUpdated) {
		if (person.getExtId() == null) {
			return null;
		}
		
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.enableFilter("attributes");
			
			Query query = session.createQuery("from PersonItem where extId = :extId");
			query.setParameter("extId", person.getExtId());
			
			PersonItem item = (PersonItem) query.uniqueResult();
			String title = person.getFullName();
			
			Date lastModified = new Date();
			ItemTraitParser parser = new ItemTraitParser();
			Item newItem = null;
			
			try {
				newItem = parser.getItem(person, userUpdated, lastModified);
			}
			catch (Exception e) {
				LOGGER.error("Exception transforming person to an item", e);
			}
			
			if (item == null) {
				item = new PersonItem();
				item.setExtId(person.getExtId().toLowerCase());
				item.setTitle(title);
				item = (PersonItem) session.merge(item);
			}
			else {
				item.setTitle(title);
			}
			
			updateAttributesFromItem(item, newItem, session, lastModified);
			
			item = (PersonItem) session.merge(item);
			session.getTransaction().commit();
			return item;
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Retrieve basic information about a person e.g. their name
	 * 
	 * @param uid The unique id of the person to retrieve information about
	 * @return The information about the person
	 */
	public Person getPerson(String uid) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			session.enableFilter("attributes");
			Transaction transaction = session.beginTransaction();
			
			Query query = session.createQuery("from PersonItem where extId = :extId");
			query.setParameter("extId", uid);
			
			PersonItem item = (PersonItem) query.uniqueResult();
			
			Person person = null;
			if (item != null) {
				person = getPerson(item);
			}
			transaction.commit();
			
			return person;
		}
		finally {
			session.close();
		}
	}
	
	public List<Person> getPeople(List<PersonItem> items, boolean showUid) {
		List<Person> people = new ArrayList<Person>();
		for (PersonItem personItem : items) {
			Person person = getPerson(personItem, showUid);
			if (!people.contains(person)) {
				people.add(person);
			}
		}
		
		return people;
	}
	
	/**
	 * Retrieve the basic person information
	 * 
	 * @param uid The uid of the person to retrieve
	 * @return Basic person information such as their name
	 */
	public Person getBasicPerson(String uid) {
		return getBasicPerson(uid, false);
	}
	
	/**
	 * Retrieve basic information about a person e.g. their name
	 * 
	 * @param uid The unique id of the person to retrieve information about
	 * @param extraInfo Indicates whether to retrieve the type, institution, country and organisational unit about the person
	 * @return Information about the given person
	 */
	public Person getBasicPerson(String uid, boolean extraInfo) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			session.enableFilter("attributes");
			Transaction transaction = session.beginTransaction();
			
			Query query = session.createQuery("select pi from PersonItem pi join fetch pi.itemAttributes where pi.extId = :extId");
			query.setParameter("extId", uid);
			
			PersonItem item = (PersonItem) query.uniqueResult();
			Person person = null;
			if (item != null) {
				person = getBasicPerson(item, extraInfo);
			}
			transaction.commit();
			
			return person;
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Get basic information about a person
	 * 
	 * @param item The PersonItem to return a Person object for
	 * @return The person information
	 */
	public Person getBasicPerson(PersonItem item) {
		return getBasicPerson(item, false);
	}
	
	/**
	 * Retrieves a list of people with the given ids
	 * 
	 * @param extIds The ids to retrieve information about people for
	 * @param extraInfo Indicates whether to also retrieve the type, institution, country and organisational unit about the person
	 * @return A list of people containing basic information
	 */
	public List<Person> getBasicPeople(List<String> extIds, boolean extraInfo) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			Query query = session.createQuery("select distinct pi from PersonItem as pi left join fetch pi.itemAttributes where pi.extId in (:extIds)");
			query.setParameterList("extIds", extIds);
			
			@SuppressWarnings("unchecked")
			List<PersonItem> personItems = query.list();
			LOGGER.debug("Number of People Found: {}", personItems.size());
			List<Person> people = new ArrayList<Person>();
			for (PersonItem personItem : personItems) {
				Person person = getBasicPerson(personItem, extraInfo);
				if (!people.contains(person)) {
					people.add(person);
				}
			}
			
			return people;
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Retrieve basic information about a person e.g. their name
	 * 
	 * @param item The PersonItem to return a Person object for
	 * @param extraInfo Indicates whether to also retrieve the type, institution, country and organisational unit about the person
	 * @return The person information
	 */
	public Person getBasicPerson(PersonItem item, boolean extraInfo) {
		Person person = new Person();
		
		int level = 4;
		if (extraInfo) {
			level = 3;
		}
		ItemTraitParser parser = new ItemTraitParser();
		try {
			person = (Person) parser.getItemObject(item, Person.class, level);
		}
		catch (Exception e) {
			LOGGER.error("Exception retrieving information about people", e);
		}
		
		return person;
	}
	
	public Person getPerson(Item item) {
		return getPerson(item, true);
	}
	
	/**
	 * Get the Person object from the given item
	 * 
	 * @param item The Item to return a Person object for
	 * @return The person information
	 */
	public Person getPerson(Item item, boolean showUid) {
		Person person = new Person();
		int level = 2;
		if (showUid) {
			level = 1;
		}
		ItemTraitParser parser = new ItemTraitParser();
		try {
			person = (Person) parser.getItemObject(item, Person.class, level);
			//Set the ext id
			person.setExtId(item.getExtId());
		}
		catch (Exception e) {
			LOGGER.error("Exception retrieving information about people", e);
		}
		
		return person;
	}
	
	/**
	 * Update person attributes 
	 * 
	 * @param id The id of the record to update
	 * @param newValues The values to set
	 * @return The updated person
	 */
	public Person updateSomeAttributes(String id, Map<String, List<String>> newValues) {
		Person person = getPerson(id);
		
		if (person == null) {
			return null;
		}
		
		String value = getValue(person.getGivenName(), Person.GIVEN_NAME, newValues);
		person.setGivenName(value);
		
		value = getValue(person.getSurname(), Person.SURNAME, newValues);
		person.setSurname(value);
		
		value = getValue(person.getDisplayName(), Person.DISPLAY_NAME, newValues);
		person.setDisplayName(value);
		
		value = getValue(person.getAriesId(), Person.ARIES_ID, newValues);
		person.setAriesId(value);
		
		value = getValue(person.getEmail(), Person.EMAIL, newValues);
		person.setEmail(value);
		
		List<String> values = getValues(person.getPhoneNumbers(), Person.PHONE, newValues);
		person.setPhoneNumbers(values);
		
		values = getValues(person.getFaxNumbers(), Person.FAX, newValues);
		person.setFaxNumbers(values);
		
		value = getValue(person.getJobTitle(), Person.JOB_TITLE, newValues);
		person.setJobTitle(value);
		
		value = getValue(person.getPreferredName(), Person.PREFERRED_NAME, newValues);
		person.setPreferredName(value);
		
		value = getValue(person.getStaffType(), Person.STAFF_TYPE, newValues);
		person.setStaffType(value);
		
		value = getValue(person.getOrganisationalUnit(), Person.ORGANISATIONAL_UNIT, newValues);
		person.setOrganisationalUnit(value);
		
		value = getValue(person.getNlaId(), Person.NLA_ID, newValues);
		person.setNlaId(value);
		
		value = getValue(person.getCountry(), Person.COUNTRY, newValues);
		person.setCountry(value);
		
		value = getValue(person.getInstitution(), Person.INSTITUTION, newValues);
		person.setInstitution(value);
		
		value = getValue(person.getDescription(), Person.DESCRIPTION, newValues);
		person.setDescription(value);
		
		List<Subject> subjects = getForSubjects(person.getAnzforSubjects(), newValues);
		person.setAnzforSubjects(subjects);
		
		// Make sure the changes are set as user updated
		savePerson(person, Boolean.TRUE);
		
		return person;
	}
	
	/**
	 * Get the single value
	 * 
	 * @param currentValue The current value
	 * @param key The field to get
	 * @param newValues The map of new values
	 * @return The value
	 */
	private String getValue(String currentValue, String key, Map<String, List<String>> newValues) {
		if (newValues.containsKey(key)) {
			return getFirstValueInList(newValues.get(key));
		}
		else {
			return currentValue;
		}
	}
	
	/**
	 * Retrieves a list of values
	 * 
	 * @param currentValues The current values
	 * @param key The field to get
	 * @param newValues The map of new values
	 * @return The list of values
	 */
	private List<String> getValues(List<String> currentValues, String key, Map<String, List<String>> newValues) {
		if (newValues.containsKey(key)) {
			if (newValues.get(key) == null) {
				return new ArrayList<String>();
			}
			else {
				return newValues.get(key);
			}
		}
		else {
			return currentValues;
		}
	}
	
	/**
	 * Get the first value from the list
	 * 
	 * @param values The list of values
	 * @return The first value in list or null if there are no values in the list
	 */
	private String getFirstValueInList(List<String> values) {
		if (values != null && values.size() > 0) {
			return values.get(0);
		}
		return null;
	}
	
	/**
	 * Get the field of research subjects
	 * 
	 * @param currentSubjects The list of current subjects
	 * @param newValues The new values
	 * @return The list of field of research subjects
	 */
	private List<Subject> getForSubjects(List<Subject> currentSubjects, Map<String, List<String>> newValues) {
		List<Subject> subjects = new ArrayList<Subject>();
		Subject subject = null;
		if (newValues.containsKey(Subject.CODE)) {
			List<String> newCodes = newValues.get(Subject.CODE);
			for (int i = 0; i < newCodes.size(); i++) {
				if (subjects.size() < i + 1) {
					subject = new Subject();
					subjects.add(subject);
				}
				subject = subjects.get(i);
				subject.setCode(newCodes.get(i));
			}
		}
		if (newValues.containsKey(Subject.VALUE)) {
			List<String> newDescriptions = newValues.get(Subject.VALUE);
			for (int i = 0; i < newDescriptions.size(); i++) {
				if (subjects.size() < i + 1) {
					subject = new Subject();
					subjects.add(subject);
				}
				subject = subjects.get(i);
				subject.setValue(newDescriptions.get(i));
			}
		}
		if (newValues.containsKey(Subject.PERCENTAGE)) {
			List<String> newPercentages = newValues.get(Subject.PERCENTAGE);
			for (int i = 0; i < newPercentages.size(); i++) {
				if (subjects.size() < i + 1) {
					subject = new Subject();
					subjects.add(subject);
				}
				subject = subjects.get(i);
				subject.setValue(newPercentages.get(i));
			}
		}
		
		if (subjects.size() > 0) {
			return subjects;
		}
		
		return currentSubjects;
	}
	
	public List<Person> getCurrentAriesPeople() {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			//TODO limit this by current people?
			Query query = session.createQuery("FROM PersonItem item WHERE EXISTS (SELECT 1 FROM item.itemAttributes ia "
					+ "WHERE ia.attrType = :ariesAttrType) "
					+ "AND EXISTS (SELECT 1 FROM item.itemAttributes ia "
					+ "WHERE ia.attrType = :uidAttrType) "
					+ "AND EXISTS (SELECT 1 FROM item.itemAttributes ia "
					+ "WHERE ia.attrType = :activeAttr "
					+ "AND ia.attrValue = :activeValue) ");
			query.setParameter("ariesAttrType", StoreAttributes.ARIES_ID);
			query.setParameter("uidAttrType", StoreAttributes.UNIVERSITY_ID);
			query.setParameter("activeAttr", StoreAttributes.ACTIVE);
			query.setParameter("activeValue", Boolean.TRUE.toString());
			@SuppressWarnings("unchecked")
			List<PersonItem> peopleItems = query.list();
			LOGGER.info("Number of people: {}", peopleItems.size());
			List<Person> people = getPeople(peopleItems.subList(0, Math.min(peopleItems.size(), 100)), true);
			//List<Person> people = getPeople(peopleItems, true);
			return people;
		}
		finally {
			session.close();
		}
	}
}
