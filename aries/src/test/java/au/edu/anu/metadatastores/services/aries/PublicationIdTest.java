package au.edu.anu.metadatastores.services.aries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublicationIdTest {
	static final Logger LOGGER = LoggerFactory.getLogger(PublicationIdTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void journalTest() {
		PublicationId publicationService = PublicationId.getSingleton();
		
		Publication pub = publicationService.getSinglePublication("f1234xPUB100");
		assertNotNull("No publication found", pub);
		assertEquals("Unexpected Aries id","f1234xPUB100", pub.getAriesId());
		assertEquals("Unexpected publication title", "Testing Methods", pub.getPublicationTitle());
		assertEquals("Unexpected publication name", "Journal of Everything", pub.getPublicationName());
		assertEquals("Unexpected publication date", "2012", pub.getPublicationDate());
		assertEquals("Unexpected publication type", "Journal", pub.getPublicationType());
		assertEquals("Unexpected publication category", "C1: journal article meeting HERDC requirements", pub.getPublicationCategory());
		assertEquals("Unexpected ISSN","1234-5678", pub.getISSN());
		assertNull("Unexpected ISBN", pub.getISBN());
		Subject subject1 = pub.getForSubject1();
		assertNotNull("Unexpected field of research subject", subject1);
		assertEquals("Unexpected field of research code", "111706", subject1.getCode());
		assertEquals("Unexpected field of research description", "Epidemiology", subject1.getDescription());
		assertEquals("Unexpected percentage", "50", subject1.getPercentage());
		
		Subject subject2 = pub.getForSubject2();
		assertNotNull("Unexpected field of research subject", subject2);
		assertEquals("Unexpected field of research code", "111714", subject2.getCode());
		assertEquals("Unexpected field of research description", "Mental Health", subject2.getDescription());
		assertEquals("Unexpected percentage", "30", subject2.getPercentage());
		
		Subject subject3 = pub.getForSubject3();
		assertNotNull("Unexpected field of research subject", subject3);
		assertEquals("Unexpected field of research code", "110311", subject3.getCode());
		assertEquals("Unexpected field of research description", "Medical Genetics (excl. Cancer Genetics)", subject3.getDescription());
		assertEquals("Unexpected percentage", "20", subject3.getPercentage());
		
		String[] authors = pub.getAuthors();
		assertNotNull("Unexpected authors", authors);
		assertEquals("Unexpected number of authors", 1, authors.length);
		assertEquals("Unexpected author value", "t1234567", authors[0]);
		
	}
	
	@Test
	public void bookTest() {
		PublicationId publicationService = PublicationId.getSingleton();
		
		Publication pub = publicationService.getSinglePublication("f1234xPUB101");
		assertNotNull("No publication found", pub);
		assertEquals("Unexpected Aries id","f1234xPUB101", pub.getAriesId());
		assertEquals("Unexpected publication title", "Test Driven Development", pub.getPublicationTitle());
		assertEquals("Unexpected publication name", "Methodologies for Unit Testing", pub.getPublicationName());
		assertEquals("Unexpected publication date", "2012", pub.getPublicationDate());
		assertEquals("Unexpected publication type", "Book Chapter", pub.getPublicationType());
		assertEquals("Unexpected publication category", "B1: chapter meeting HERDC requirements", pub.getPublicationCategory());
		assertNull("Unexpected ISSN", pub.getISSN());
		assertEquals("Unexpected ISBN","978-1234-5678", pub.getISBN());
		Subject subject1 = pub.getForSubject1();
		assertNotNull("Unexpected field of research subject", subject1);
		assertEquals("Unexpected field of research code", "111706", subject1.getCode());
		assertEquals("Unexpected field of research description", "Epidemiology", subject1.getDescription());
		assertEquals("Unexpected percentage", "40", subject1.getPercentage());
		
		Subject subject2 = pub.getForSubject2();
		assertNotNull("Unexpected field of research subject", subject2);
		assertEquals("Unexpected field of research code", "111714", subject2.getCode());
		assertEquals("Unexpected field of research description", "Mental Health", subject2.getDescription());
		assertEquals("Unexpected percentage", "35", subject2.getPercentage());
		
		Subject subject3 = pub.getForSubject3();
		assertNotNull("Unexpected field of research subject", subject3);
		assertEquals("Unexpected field of research code", "110311", subject3.getCode());
		assertEquals("Unexpected field of research description", "Medical Genetics (excl. Cancer Genetics)", subject3.getDescription());
		assertEquals("Unexpected percentage", "25", subject3.getPercentage());
		
		String[] authors = pub.getAuthors();
		assertNotNull("Unexpected authors", authors);
		assertEquals("Unexpected number of authors", 1, authors.length);
		assertEquals("Unexpected author value", "t1234567", authors[0]);
	}
	
	@Test
	public void conferenceTest() {

		PublicationId publicationService = PublicationId.getSingleton();
		
		Publication pub = publicationService.getSinglePublication("f1234xPUB102");
		assertNotNull("No publication found", pub);
		assertEquals("Unexpected Aries id","f1234xPUB102", pub.getAriesId());
		assertEquals("Unexpected publication title", "Examining Software Testing", pub.getPublicationTitle());
		assertEquals("Unexpected publication name", "16th Annual Conference of Software Engineers", pub.getPublicationName());
		assertEquals("Unexpected publication date", "2011", pub.getPublicationDate());
		assertEquals("Unexpected publication type", "Conference", pub.getPublicationType());
		assertEquals("Unexpected publication category", "E1: conference paper meeting HERDC requirements", pub.getPublicationCategory());
		assertNull("Unexpected ISSN", pub.getISSN());
		assertEquals("Unexpected ISBN", "978-1234-5679", pub.getISBN());
		Subject subject1 = pub.getForSubject1();
		assertNotNull("Unexpected field of research subject", subject1);
		assertEquals("Unexpected field of research code", "111706", subject1.getCode());
		assertEquals("Unexpected field of research description", "Epidemiology", subject1.getDescription());
		assertEquals("Unexpected percentage", "70", subject1.getPercentage());
		
		Subject subject2 = pub.getForSubject2();
		assertNotNull("Unexpected field of research subject", subject2);
		assertEquals("Unexpected field of research code", "111714", subject2.getCode());
		assertEquals("Unexpected field of research description", "Mental Health", subject2.getDescription());
		assertEquals("Unexpected percentage", "15", subject2.getPercentage());
		
		Subject subject3 = pub.getForSubject3();
		assertNotNull("Unexpected field of research subject", subject3);
		assertEquals("Unexpected field of research code", "110311", subject3.getCode());
		assertEquals("Unexpected field of research description", "Medical Genetics (excl. Cancer Genetics)", subject3.getDescription());
		assertEquals("Unexpected percentage", "15", subject3.getPercentage());
		
		String[] authors = pub.getAuthors();
		assertNotNull("Unexpected authors", authors);
		assertEquals("Unexpected number of authors", 1, authors.length);
		assertEquals("Unexpected author value", "t1234567", authors[0]);
	}

	@Test
	public void publicationsByYearTest() {
		PublicationId publicationService = PublicationId.getSingleton();
		LOGGER.info("Begin Retrieving Publications");
		Publication[] publications = publicationService.getPublicationsForYear("2012");
		assertNotNull("Unexepcted publications", publications);
		assertEquals("Unexpected number of publications", 2, publications.length);
		boolean unexpectedValue = false;
		for (Publication pub : publications) {
			if (!"f1234xPUB100".equals(pub.getAriesId()) && !"f1234xPUB101".equals(pub.getAriesId())) {
				unexpectedValue = true;
			}
		}
		assertFalse("Unexpected publication", unexpectedValue);
	}

}
