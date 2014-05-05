package au.edu.anu.metadatastores.store.publication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.store.misc.Subject;
import au.edu.anu.metadatastores.store.people.Person;

public class PublicationServiceTest {
	static final Logger LOGGER = LoggerFactory.getLogger(PublicationServiceTest.class);

	@Ignore
	@Test
	public void test() {
		PublicationService publicationService = PublicationService.getSingleton();
		//List<Publication> publications = publicationService.fetchPublicationsForUid("u4464261");
		//List<Publication> publications = publicationService.fetchPublicationsForUid("a117796");
		//has error
		List<Publication> publications = publicationService.fetchPublicationsForUid("u3171954");
		//List<Publication> publications = publicationService.fetchPublicationsForUid("u9708405");
		
		for (Publication publication : publications) {
			LOGGER.info("Publication Title: {}, Name: {}, Type: {}, Year: {}, Aries ID: {}", new Object[] {publication.getTitle(), publication.getPublicationName(), publication.getType(), publication.getYear(), publication.getAriesId()});
			LOGGER.info("Authors: {}", publication.getAuthors().toString());
			for (Subject subject : publication.getAnzforSubjects()) {
				LOGGER.info("Subject Code: {}, Percent: {}", new Object[] {subject.getCode(), subject.getPercentage()});
			}
		}
	//	fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void saveTest() {
		PublicationService publicationService = PublicationService.getSingleton();
		Publication publication = new Publication();
		publication.setAriesId("t12345676xcon101");
		publication.setTitle("Test Publication Updated");
		publication.setPublicationName("Journal of Testing");
		publication.setType("Journal");
		publication.setYear("2013");
		
		List<String> uids = new ArrayList<String>();
		uids.add("u5125986");
		uids.add("t1234568");
		uids.add("t1234569");
		uids.add("t1234567");
		uids.add("t1234567");
		uids.add("u4034284");
		uids.add("E40140");
		publication.setAuthors(createPeopleFromUids(uids));
		
		Subject subject = new Subject("20", null, "30%");
		publication.getAnzforSubjects().add(subject);
		
		publicationService.savePublication(publication);
		LOGGER.info("Done");
	}
	
	private List<Person> createPeopleFromUids(List<String> uids) {
		List<Person> people = new ArrayList<Person>();
		Person person = null;
		for (String uid : uids) {
			person = new Person();
			person.setExtId(uid);
			people.add(person);
		}
		return people;
	}

	@Ignore
	@Test
	public void fetchAndSaveTest() {
		PublicationService publicationService = PublicationService.getSingleton();
	//	List<Publication> publications = publicationService.fetchPublicationsForUid("u3171954");
		List<Publication> publications = publicationService.fetchPublicationsForUid("u4507277");
		//List<Publication> publications = publicationService.fetchPublicationsForUid("u4464261");
		//List<Publication> publications = publicationService.fetchPublicationsForUid("u9708405");
		for (Publication publication : publications) {
			LOGGER.info("Aries Id: {}, Title: {}", publication.getAriesId(), publication.getTitle());
			publicationService.savePublication(publication);
		}
		System.out.println("Done");
	}

	@Ignore
	@Test
	public void updateSingleAriesPublication() {
		PublicationService publicationService = PublicationService.getSingleton();
		Publication publication = publicationService.fetchPublication("f5625xPUB410");
		LOGGER.info("Aries Id: {}, Title: {}, Type: {}", new Object[]{publication.getAriesId(), publication.getTitle(), publication.getType()});
		LOGGER.info("Authors: {}", publication.getAuthors().toString());
		publicationService.savePublication(publication);
	}

	@Ignore
	@Test
	public void singlePublicationTest() {
		PublicationService publicationService = PublicationService.getSingleton();
		Publication publication = publicationService.getPublicationByAriesId("f5625xPUB410");
		printPublication(publication);
	}
	
	private void printPublication(Publication publication) {
		LOGGER.info("ARIES ID: {}, First Author: {}, Category: {}", new Object[] {publication.getAriesId(), publication.getFirstAuthor(), publication.getCategory()});
		LOGGER.info("ISBN: {}, ISSN: {}", new Object[] {publication.getISBN(), publication.getISSN()});
		LOGGER.info("Title: {}, Name: {}", new Object[] {publication.getTitle(), publication.getPublicationName()});
		for (Subject subject : publication.getAnzforSubjects()) {
			LOGGER.info("Code: {}, Value: {}, Percent: {}", new Object[]{subject.getCode(), subject.getValue(), subject.getPercentage()});
		}
	}

	@Ignore
	@Test
	public void testfetchPublication() {
		PublicationService publicationService = PublicationService.getSingleton();
		Publication publication = publicationService.fetchPublication("u9204672xPUB430");
		LOGGER.info("Aries Id: {}, Title: {}, Type: {}", new Object[] {publication.getAriesId(), publication.getTitle(), publication.getType()});
		for (Person author : publication.getAuthors()) {
			LOGGER.info("Author: {} {}", author.getGivenName(), author.getSurname());
		}
	}

	@Ignore
	@Test
	public void testGetPersonsPublications() {
		PublicationService publicationService = PublicationService.getSingleton();
		List<Publication> publications = publicationService.getPersonsPublications("u9708405");
		//List<Publication> publications = publicationService.getPersonsPublications("u4464261");
		for (Publication pub : publications) {
			LOGGER.info("Publication: {}", pub.getTitle());
		}
		System.out.println("Done");
	}

	@Ignore
	@Test
	public void testGetPublicationsByYear() {
		PublicationService publicationService = PublicationService.getSingleton();
		Date startDate = new Date();
		List<Publication> publications = publicationService.getPublicationsByYear("2011");
		
		for (Publication pub : publications) {
			LOGGER.info("Publication: {}, {}, Year: {}, Number of Authors: {}", new Object[] {pub.getAriesId(), pub.getTitle(), pub.getYear(), pub.getAuthors().size()});
		}
		Date endDate = new Date();
		long difference = endDate.getTime() - startDate.getTime();
		double seconds = (difference / 1000);
		double minutes = seconds / 60;
		LOGGER.info("Number of Records: {}, Time Taken Millis: {}, Seconds: {}, Minutes: {}", new Object[] {publications.size(), difference, seconds, minutes});
	}
	
	@Ignore
	@Test
	public void updatePublicationsByYear() {
		LOGGER.info("Starting Publications Update");
		Date startDate = new Date();
		PublicationService publicationService = PublicationService.getSingleton();
		//publicationService.updatePublicationsByYear("2011");
		publicationService.updatePublicationsByYear("1997");
		Date endDate = new Date();
		long difference = endDate.getTime() - startDate.getTime();
		LOGGER.info("Publications Updated, Time Taken: {}", difference);
	}
	
	@Test
	public void createNewPublications() {
		PublicationService publicationService = PublicationService.getSingleton();
		
		Publication publication = new Publication();
		publication.setAriesId("1234567");
		publication.setCategory("C1: journal article meeting HERDC requirements");
		//publication.setISBN(iSBN);
		publication.setISSN("1234-5671");
		publication.setPublicationName("Journal of Everything");
		publication.setTitle("Testing methods for Orcid");
		publication.setType("Journal");
		publication.setYear("2014");

		List<String> uids = new ArrayList<String>();
		uids.add("t1111111");
		uids.add("t1111112");
		uids.add("t1111113");
		publication.setAuthors(createPeopleFromStuff(uids));
		
		Subject subject = new Subject("080306", "Open Software", "100%");
		publication.getAnzforSubjects().add(subject);
		publicationService.savePublication(publication);
		
		publication = new Publication();
		publication.setAriesId("1234568");
		publication.setCategory("C1: journal article meeting HERDC requirements");
		//publication.setISBN(iSBN);
		publication.setISSN("1234-5672");
		publication.setPublicationName("Journal of Something");
		publication.setTitle("Basic testing methods");
		publication.setType("Journal");
		publication.setYear("2014");

		uids = new ArrayList<String>();
		uids.add("t1111111");
		publication.setAuthors(createPeopleFromStuff(uids));
		
		subject = new Subject("080306", "Open Software", "100%");
		publication.getAnzforSubjects().add(subject);
		publicationService.savePublication(publication);
		
		publication = new Publication();
		publication.setAriesId("1234569");
		publication.setCategory("C1: journal article meeting HERDC requirements");
		publication.setISSN("1234-5673");
		publication.setPublicationName("Testing Journal");
		publication.setTitle("Advanced test methods");
		publication.setType("Journal");
		publication.setYear("2014");

		uids = new ArrayList<String>();
		uids.add("t1111112");
		publication.setAuthors(createPeopleFromStuff(uids));
		
		subject = new Subject("080306", "Open Software", "100%");
		publication.getAnzforSubjects().add(subject);
		publicationService.savePublication(publication);
		
		publication = new Publication();
		publication.setAriesId("1234561");
		publication.setCategory("C1: journal article meeting HERDC requirements");
		//publication.setISBN(iSBN);
		publication.setISSN("1234-5674");
		publication.setPublicationName("Journal of Everything");
		publication.setTitle("Further testing methods");
		publication.setType("Journal");
		publication.setYear("2012");

		uids = new ArrayList<String>();
		uids.add("t1111111");
		uids.add("t1111112");
		uids.add("t1111113");
		publication.setAuthors(createPeopleFromStuff(uids));
		
		subject = new Subject("080306", "Open Software", "100%");
		publication.getAnzforSubjects().add(subject);
		publicationService.savePublication(publication);
		
		publication = new Publication();
		publication.setAriesId("1234562");
		publication.setCategory("C1: journal article meeting HERDC requirements");
		//publication.setISBN(iSBN);
		publication.setISSN("1234-5675");
		publication.setPublicationName("Journal of Everything");
		publication.setTitle("Ensuring that updates are made to testing");
		publication.setType("Journal");
		publication.setYear("2013");

		uids = new ArrayList<String>();
		uids.add("t1111111");
		publication.setAuthors(createPeopleFromStuff(uids));
		
		subject = new Subject("080306", "Open Software", "100%");
		publication.getAnzforSubjects().add(subject);
		publicationService.savePublication(publication);
	}
	
	private List<Person> createPeopleFromStuff(List<String> uids) {
		List<Person> people = new ArrayList<Person>();
		Person person = null;
		for (String uid : uids) {
			person = new Person();
			person.setExtId(uid);
			person.setUid(uid);
			people.add(person);
		}
		return people;
	}
}
