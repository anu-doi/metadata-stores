package au.edu.anu.metadatastores.store.publication;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublicationServiceTest {
	static final Logger LOGGER = LoggerFactory.getLogger(PublicationServiceTest.class);
	/*
	@Test
	public void test() {
		PublicationService publicationService = PublicationService.getSingleton();
		//List<Publication> publications = publicationService.fetchPublicationsForUid("u4464261");
		//List<Publication> publications = publicationService.fetchPublicationsForUid("a117796");
		//has error
		List<Publication> publications = publicationService.fetchPublicationsForUid("u3171954");
		//List<Publication> publications = publicationService.fetchPublicationsForUid("u9708405");
		
		for (Publication publication : publications) {
			LOGGER.info("Publication Title: {}, Name: {}, Type: {}, Year: {}, Aries ID: {}", new Object[] {publication.getTitle(), publication.fetchPublicationName(), publication.getType(), publication.getYear(), publication.getAriesId()});
			LOGGER.info("Authors: {}", publication.getAuthors().toString());
			for (Subject subject : publication.getAnzforSubjects()) {
				LOGGER.info("Subject Code: {}, Percent: {}", new Object[] {subject.getCode(), subject.getPercentage()});
			}
		}
	//	fail("Not yet implemented");
	}*/
/*
	@Test
	public void saveTest() {
		PublicationService publicationService = PublicationService.getSingleton();
		Publication publication = new Publication();
		publication.setAriesId("t12345676xcon101");
		publication.setTitle("Test Publication Updated");
		publication.setPublicationName("Journal of Testing");
		publication.setType("Journal");
		publication.setYear("2013");
		
		publication.getAuthors().add("u5125986");
		publication.getAuthors().add("t1234568");
		publication.getAuthors().add("t1234569");
		publication.getAuthors().add("t1234567");
		publication.getAuthors().add("t1234567");
		publication.getAuthors().add("u4034284");
		publication.getAuthors().add("E40140");
		
		Subject subject = new Subject("20", null, "30%");
		publication.getAnzforSubjects().add(subject);
		
		publicationService.savePublication(publication);
		LOGGER.info("Done");
	}
	*/
	/*@Test
	public void saveTest() {
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
	}*/
	

	
	/*@Test
	public void updateSingleAriesPublication() {
		PublicationService publicationService = PublicationService.getSingleton();
		Publication publication = publicationService.fetchPublication("f5625xPUB410");
		LOGGER.info("Aries Id: {}, Title: {}, Type: {}", new Object[]{publication.getAriesId(), publication.getTitle(), publication.getType()});
		LOGGER.info("Authors: {}", publication.getAuthors().toString());
		publicationService.savePublication(publication);
	}*/
	/*
	@Test
	public void testfetchPublication() {
		PublicationService publicationService = PublicationService.getSingleton();
		Publication publication = publicationService.fetchPublicationByAriesId("u9204672xPUB430");
		LOGGER.info("Aries Id: {}, Title: {}, Type: {}", new Object[] {publication.getAriesId(), publication.getTitle(), publication.getType()});
		for (Person author : publication.getAuthors()) {
			LOGGER.info("Author: {} {}", author.getGivenName(), author.getSurname());
		}
	}*/
/*	@Test
	public void testGetPersonsPublications() {
		PublicationService publicationService = PublicationService.getSingleton();
		List<Publication> publications = publicationService.getPersonsPublications("u9708405");
		//List<Publication> publications = publicationService.getPersonsPublications("u4464261");
		for (Publication pub : publications) {
			LOGGER.info("Publication: {}", pub.getTitle());
		}
		System.out.println("Done");
	}*/
	
	@Test
	public void testGetPublicationsByYear() {
		PublicationService publicationService = PublicationService.getSingleton();
		Date startDate = new Date();
		List<Publication> publications = publicationService.getPublicationsByYear("1997");
		
		for (Publication pub : publications) {
			LOGGER.info("Publication: {}, {}, Year: {}, Number of Authors: {}", new Object[] {pub.getAriesId(), pub.getTitle(), pub.getYear(), pub.getAuthors().size()});
		}
		Date endDate = new Date();
		long difference = endDate.getTime() - startDate.getTime();
		double seconds = (difference / 1000);
		double minutes = seconds / 60;
		LOGGER.info("Number of Records: {}, Time Taken Millis: {}, Seconds: {}, Minutes: {}", new Object[] {publications.size(), difference, seconds, minutes});
	}
	/*@Test
	public void updatePublicationsByYear() {
		LOGGER.info("Starting Publications Update");
		Date startDate = new Date();
		PublicationService publicationService = PublicationService.getSingleton();
		publicationService.updatePublicationsByYear("2011");
		Date endDate = new Date();
		long difference = endDate.getTime() - startDate.getTime();
		LOGGER.info("Publications Updated, {}", difference);
	}*/
}
