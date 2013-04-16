package au.edu.anu.metadatastores.store.publication;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.ItemAttribute;
import au.edu.anu.metadatastores.datamodel.store.ItemRelation;
import au.edu.anu.metadatastores.datamodel.store.ItemRelationId;
import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;
import au.edu.anu.metadatastores.services.aries.AriesService;
import au.edu.anu.metadatastores.services.store.StoreHibernateUtil;
import au.edu.anu.metadatastores.store.misc.AbstractItemService;
import au.edu.anu.metadatastores.store.misc.Subject;
import au.edu.anu.metadatastores.store.people.Person;
import au.edu.anu.metadatastores.store.people.PersonItem;
import au.edu.anu.metadatastores.store.people.PersonService;
import au.edu.anu.metadatastores.store.properties.StoreProperties;

public class PublicationService extends AbstractItemService {
	static final Logger LOGGER = LoggerFactory.getLogger(PublicationService.class);
	
	private static PublicationService singleton_;
	
	PersonService personService = PersonService.getSingleton();
	AriesService ariesService_ = AriesService.getSingleton();
	
	public static void main(String[] args) {
		PublicationService publicationService = PublicationService.getSingleton();
		for (int i = 0; i < args.length; i++) {
			System.out.println("Argument " + i + ": " + args[i]);
			if (args[i].startsWith("u") || args[i].startsWith("a") || args[i].startsWith("f")) {
				List<Publication> publications = publicationService.fetchPublicationsForUid(args[i]);
				for (Publication publication : publications) {
					publicationService.savePublication(publication);
				}
			}
		}
		System.out.println("Updates Complete");
	}
	
	private PublicationService() {
		
	}
	
	public static PublicationService getSingleton() {
		if (singleton_ == null) {
			singleton_ = new PublicationService();
		}
		return singleton_;
	}
	
	public List<Publication> fetchPublicationsForUid(String staffId) {
		List<Publication> publications = new ArrayList<Publication>();
		Publication publication = null;
		
		au.edu.anu.metadatastores.services.aries.Publication[] ariesPublications = ariesService_.getPublications(staffId);
		
		for (au.edu.anu.metadatastores.services.aries.Publication pub : ariesPublications) {
			publication = ariesPublicationToPublication(pub);
			if (publication != null) {
				publications.add(publication);
			}
		}
		
		return publications;
	}
	
	private Publication ariesPublicationToPublication(au.edu.anu.metadatastores.services.aries.Publication ariesPublication) {
		Publication publication = new Publication();
		//LOGGER.info("ID: {}, Title: {}", ariesPublication.getAriesId(), ariesPublication.getPublicationTitle());
		publication.setAriesId(ariesPublication.getAriesId());
		publication.setTitle(ariesPublication.getPublicationTitle());
		publication.setPublicationName(ariesPublication.getPublicationName());
		publication.setType(ariesPublication.getPublicationType());
		publication.setYear(ariesPublication.getPublicationDate());
		publication.setCategory(ariesPublication.getPublicationCategory());
		publication.setISBN(ariesPublication.getISBN());
		publication.setISSN(ariesPublication.getISSN());
		//LOGGER.info("Publication ID: {}", publication.getAriesId());
		
		Person author = null;
		for (String id : ariesPublication.getAuthors()) {
			author = new Person();
			author.setUid(id);
			publication.getAuthors().add(author);
		}
		
		if (ariesPublication.getForSubject1() != null) {
			Subject subject = new Subject(ariesPublication.getForSubject1().getCode(), ariesPublication.getForSubject1().getDescription(), ariesPublication.getForSubject1().getPercentage());
			publication.getAnzforSubjects().add(subject);
		}
		if (ariesPublication.getForSubject2() != null) {
			Subject subject = new Subject(ariesPublication.getForSubject2().getCode(), ariesPublication.getForSubject2().getDescription(), ariesPublication.getForSubject2().getPercentage());
			publication.getAnzforSubjects().add(subject);
		}
		if (ariesPublication.getForSubject3() != null) {
			Subject subject = new Subject(ariesPublication.getForSubject3().getCode(), ariesPublication.getForSubject3().getDescription(), ariesPublication.getForSubject3().getPercentage());
			publication.getAnzforSubjects().add(subject);
		}
		
		return publication;
	}
	
	public Publication fetchPublication(String ariesId) {
		au.edu.anu.metadatastores.services.aries.Publication ariesPublication = ariesService_.getSinglePublication(ariesId);
		Publication publication = ariesPublicationToPublication(ariesPublication);
		
		return publication;
	}
	
	public List<Publication> fetchPublicationsByYear(String year) {
		au.edu.anu.metadatastores.services.aries.Publication[] ariesPublications = ariesService_.getPublicationsForYear(year);
		List<Publication> publications = new ArrayList<Publication>();
		Publication pub = null;
		for (au.edu.anu.metadatastores.services.aries.Publication ariesPub : ariesPublications) {
			pub = ariesPublicationToPublication(ariesPub);
			publications.add(pub);
		}
		
		return publications;
	}
	
	public void updatePublicationsByYear(String year) {
		List<Publication> publications = fetchPublicationsByYear(year);
		for (Publication pub : publications) {
			savePublication(pub);
		}
	}
	
	public PublicationItem savePublication(Publication publication) {
		return savePublication(publication, Boolean.FALSE);
	}
	
	public PublicationItem savePublication(Publication publication, Boolean userUpdated) {

		//TODO update this as needed with other systems bring in publications
		if (publication.getTitle() == null || publication.getTitle().trim().length() == 0) {
			return null;
		}
		
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.enableFilter("attributes");
		
		//TODO note this may need to be updated if we retrieve publications from other systems without an aries id
		Query query = session.createQuery("SELECT pi FROM PublicationItem as pi inner join pi.itemAttributes as pia WHERE pia.attrType = :attrType and pia.attrValue = :attrValue");
		query.setParameter("attrType", StoreAttributes.ARIES_ID);
		query.setParameter("attrValue", publication.getAriesId());
		
		PublicationItem item = (PublicationItem) query.uniqueResult();
		
		if (item == null) {
			item = new PublicationItem();
			Query idQuery = session.createSQLQuery("SELECT nextval('publication_seq')");
			BigInteger id = (BigInteger) idQuery.uniqueResult();
			item.setExtId("p" + id.toString());
			item.setTitle(publication.getTitle());
			item = (PublicationItem) session.merge(item);
		}
		else if (publication.getTitle() != null && publication.getTitle().trim().length() > 0) {
			item.setTitle(publication.getTitle());
		}
		Date lastModified = new Date();
		
		setSingleAttribute(item, item.getAriesIds(), publication.getAriesId(), StoreAttributes.ARIES_ID, session, lastModified, userUpdated);
		setSingleAttribute(item, item.getTypes(), publication.getType(), StoreAttributes.TYPE, session, lastModified, userUpdated);
		setSingleAttribute(item, item.getPublicationTitles(), publication.getTitle(), StoreAttributes.TITLE, session, lastModified, userUpdated);
		setSingleAttribute(item, item.getYears(), publication.getYear(), StoreAttributes.YEAR, session, lastModified, userUpdated);
		setSingleAttribute(item, item.getPublicationNames(), publication.getPublicationName(), StoreAttributes.PUBLICATION_NAME, session, lastModified, userUpdated);
		setSingleAttribute(item, item.getCategories(), publication.getCategory(), StoreAttributes.CATEGORY, session, lastModified, userUpdated);
		setSingleAttribute(item, item.getIsbns(), publication.getISBN(), StoreAttributes.ISBN, session, lastModified, userUpdated);
		setSingleAttribute(item, item.getIssns(), publication.getISSN(), StoreAttributes.ISSN, session, lastModified, userUpdated);
		setForSubjectsForSave(item, item.getAnzforSubjects(), publication.getAnzforSubjects(), session, lastModified, userUpdated);
		
		//TODO remove people who are no longer related
		Item personItem = null;
		ItemRelation itemRelation = null;
		ItemRelationId id = null;
		List<Item> peopleItems = new ArrayList<Item>();
		for (Person person : publication.getAuthors()) {
			personItem = personService.getPersonItem(person.getUid());
			if (personItem != null) {
				peopleItems.add(personItem);
			}
			else {
				LOGGER.error("No person found to add relation for id: {}", person.getUid());
			}
		}
		boolean hasPerson = false;
		for (Item item2 : peopleItems) {
			for (ItemRelation relation : item.getItemRelationsForIid()) {
				if (relation.getId().getRelatedIid().equals(item2.getIid())) {
					hasPerson = true;
					break;
				}
			}
			if (!hasPerson) {
				itemRelation = new ItemRelation();
				id = new ItemRelationId(item.getIid(), StoreProperties.getProperty("publication.author.type"), item2.getIid());
				itemRelation.setId(id);
				item.getItemRelationsForIid().add(itemRelation);
			}
			hasPerson = false;
		}
		
		item = (PublicationItem) session.merge(item);
		
		session.getTransaction().commit();
		session.close();
		return item;
	}
	
	/**
	 * Tries to find an aries record within the database.  If it does not find it it searches the aries database and saves it.
	 * 
	 * @param ariesId The id of the aries record to return an item for
	 * @return The publication item for the given aries id
	 */
	public PublicationItem getPublicationItem(String ariesId) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		session.enableFilter("attributes");
		session.beginTransaction();
		
		Query query = session.createQuery("SELECT pi FROM PublicationItem as pi inner join pi.itemAttributes as pia WHERE pia.attrType = :attrType and pia.attrValue = :attrValue");
		query.setParameter("attrType", StoreAttributes.ARIES_ID);
		query.setParameter("attrValue", ariesId);
		
		PublicationItem item = (PublicationItem) query.uniqueResult();
		if (item != null) {
			return item;
		}
		
		Publication publication = fetchPublication(ariesId);
		item = savePublication(publication);

		session.getTransaction().commit();
		session.close();
		
		return item;
	}
	
	public List<PersonItem> searchPublicationPerson(Long iid, String givenName, String surname) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		session.enableFilter("attributes");
		
		Query query = session.createQuery("SELECT pi FROM ItemRelation ir join ir.itemByRelatedIid pi join pi.givenNames gn join pi.surnames sn WHERE ir.id.iid = :iid AND pi.extSystem = :extSystem AND lower(gn.attrValue) like :givenName and lower(sn.attrValue) = :surname");
		query.setParameter("iid", iid);
		query.setParameter("extSystem", "PERSON");
		query.setParameter("givenName", givenName.toLowerCase() + "%");
		query.setParameter("surname", surname.toLowerCase());
		
		List<PersonItem> people = query.list();
		
		session.close();
		
		return people;
	}
	
	public Publication getPublicationByAriesId(String ariesId) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		session.enableFilter("attributes");
		session.beginTransaction();

		Query query = session.createQuery("SELECT pi FROM PublicationItem as pi inner join pi.itemAttributes as pia WHERE pia.attrType = :attrType and pia.attrValue = :attrValue");
		query.setParameter("attrType", StoreAttributes.ARIES_ID);
		query.setParameter("attrValue", ariesId);
		
		PublicationItem item = (PublicationItem) query.uniqueResult();
		
		Publication publication = null;
		
		if (item != null) {
			publication = getPublication(item);
		}
		
		session.getTransaction().commit();
		session.close();
		
		return publication;
	}
	
	/**
	 * Get the publications by year
	 * 
	 * @param year The year of publication to get
	 * @return The a list of publications that were published in the given year
	 */
	public List<Publication> getPublicationsByYear(String year) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		session.enableFilter("attributes");
		Date startDate = new Date();
		Query query = session.createQuery("SELECT pub FROM PublicationItem pub inner join pub.itemAttributes pubYear WHERE pubYear.attrType = :yearType and pubYear.attrValue = :yearValue");
		query.setParameter("yearType", StoreAttributes.YEAR);
		query.setParameter("yearValue", year);
		
		List<PublicationItem> items = query.list();
		Date endDate = new Date();
		long difference = endDate.getTime() - startDate.getTime();
		LOGGER.info("Time For Query: {}, Number of Records: {}", difference, items.size());
		
		List<Publication> publications = new ArrayList<Publication>();
		Publication publication = null;
		for (PublicationItem item : items) {
			publication = getPublication(item, true);
			publications.add(publication);
		}
		
		session.close();
		
		return publications;
	}
	
	/**
	 * Get the publications associated with the user id
	 * 
	 * @param uid The user id to get publications for
	 * @return A list of publications
	 */
	public List<Publication> getPersonsPublications(String uid) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		session.enableFilter("attributes");
		session.beginTransaction();
		
		Query query = session.createQuery("SELECT pub FROM PersonItem person inner join person.itemRelationsForRelatedIid pirfri inner join pirfri.itemByIid pubItem, PublicationItem pub WHERE person.extId = :extId and pubItem = pub");
		
		query.setParameter("extId", uid);
		
		List<PublicationItem> publicationItems = query.list();
		
		List<Publication> publications = new ArrayList<Publication>();
		for (PublicationItem publicationItem : publicationItems) {
			Publication publication = getPublication(publicationItem);
			publications.add(publication);
		}
		
		LOGGER.info("Number of publications: {}", publications.size());
		
		session.getTransaction().commit();
		session.close();
		
		return publications;
	}
	
	/**
	 * Transform the PublicationItem to a Publication
	 * 
	 * @param item The PublicationItem to transform to a Publication
	 * @return The Publication
	 */
	public Publication getPublication(PublicationItem item) {
		return getPublication(item, false);
	}
	
	/**
	 * Transform the PublicationItem to a Publication
	 * 
	 * @param item The PublicationItem to transform to a Publication
	 * @param extraUserInfo Indicates whether to retrieve extra information about people (i.e. their department, country, and institution)
	 * @return The Publication
	 */
	public Publication getPublication(PublicationItem item, boolean extraUserInfo) {
		Publication publication = new Publication();
		for (ItemAttribute attribute : item.getItemAttributes()) {
			setAttribute(publication, attribute);
		}
		setRelations(publication, item);
		/*for (ItemRelation relation : item.getItemRelationsForIid()) {
			setRelation(publication, relation, extraUserInfo);
		}*/
		return publication;
	}
	
	/**
	 * Set the publication attributes
	 * 
	 * @param publication The publication
	 * @param attribute The attribute
	 */
	private void setAttribute(Publication publication, ItemAttribute attribute) {
		if (checkIfAttribute(attribute, StoreAttributes.ARIES_ID)) {
			publication.setAriesId(attribute.getAttrValue());
		}
		else if (checkIfAttribute(attribute, StoreAttributes.TYPE)) {
			publication.setType(attribute.getAttrValue());
		}
		else if (checkIfAttribute(attribute, StoreAttributes.TITLE)) {
			publication.setTitle(attribute.getAttrValue());
		}
		else if (checkIfAttribute(attribute, StoreAttributes.YEAR)) {
			publication.setYear(attribute.getAttrValue());
		}
		else if (checkIfAttribute(attribute, StoreAttributes.FIRST_AUTHOR_ID)) {
			publication.setFirstAuthor(attribute.getAttrValue());
		}
		else if (checkIfAttribute(attribute, StoreAttributes.PUBLICATION_NAME)) {
			publication.setPublicationName(attribute.getAttrValue());
		}
		else if (checkIfAttribute(attribute, StoreAttributes.CATEGORY)) {
			publication.setCategory(attribute.getAttrValue());
		}
		else if (checkIfAttribute(attribute, StoreAttributes.ISBN)) {
			publication.setISBN(attribute.getAttrValue());
		}
		else if (checkIfAttribute(attribute, StoreAttributes.ISSN)) {
			publication.setISSN(attribute.getAttrValue());
		}
		else if (checkIfAttribute(attribute, StoreAttributes.FOR_SUBJECT)) {
			Set<ItemAttribute> subjectAttrs = attribute.getItemAttributes();
			Subject subject = new Subject();
			Iterator<ItemAttribute> it = subjectAttrs.iterator();
			ItemAttribute attr = null;
			while(it.hasNext()) {
				attr = it.next();
				if (checkIfAttribute(attr, StoreAttributes.FOR_CODE)) {
					subject.setCode(attr.getAttrValue());
				}
				else if (checkIfAttribute(attr, StoreAttributes.FOR_PERCENT)) {
					subject.setPercentage(attr.getAttrValue());
				}
				else if (checkIfAttribute(attr, StoreAttributes.FOR_VALUE)) {
					subject.setValue(attr.getAttrValue());
				}
			}
			publication.getAnzforSubjects().add(subject);
		}
	}
	
	private void setRelations(Publication publication, PublicationItem item) {
		List<String> authorExtIds = new ArrayList<String>();
		//LOGGER.info("Get People");
		for (ItemRelation relation : item.getItemRelationsForIid()) {
			if (relation.getId().getRelationValue().equals(StoreProperties.getProperty("publication.author.type"))){
				authorExtIds.add(relation.getItemByRelatedIid().getExtId());
			}
		}
		if (authorExtIds.size() > 0) {
			List<Person> authors = personService.getBasicPeople(authorExtIds, true);
			publication.setAuthors(authors);
		}
		//LOGGER.info("End Get People");
	}
	
	/**
	 * Set the publication information for the relation
	 * 
	 * @param publication The publication to set relations for
	 * @param relation The relation
	 * @param extraUserInfo 
	 */
	private void setRelation(Publication publication, ItemRelation relation, boolean extraUserInfo) {
		//LOGGER.info("Set Relations for Publications");
		if (relation.getId().getRelationValue().equals(StoreProperties.getProperty("publication.author.type"))){
		//	LOGGER.info("Set Person");
			Person person = personService.getBasicPerson((PersonItem)relation.getItemByRelatedIid(), extraUserInfo);
			publication.getAuthors().add(person);
		//	LOGGER.info("End Set Person");
		}
		//LOGGER.info("End Set Relations for Publications");
	}
	
	/**
	 * Check if the attribute is of the given type
	 * 
	 * @param attribute The attribute to check
	 * @param type The type to check
	 * @return Whether the attribute has the given type
	 */
	private boolean checkIfAttribute(ItemAttribute attribute, String type) {
		return attribute.getAttrType().equals(type);
	}
}
