package au.edu.anu.metadatastores.services.aries;

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

/*	@Test
	public void test() {
		PublicationId publicationService = PublicationId.getSingleton();
		
		//Publication pub = publicationService.getSinglePublication("u9406909xPUB235");
		//Publication pub = publicationService.getSinglePublication("u4353633xPUB104");
		//Publication pub = publicationService.getSinglePublication("f2965xPUB2328");
		Publication pub = publicationService.getSinglePublication("u4637548xPUB16");
		LOGGER.info("Title: {}, Name: {}, Year: {}, ISBN: {}, ISSN: {}", new Object[] {pub.getPublicationTitle(), pub.getPublicationName(), pub.getPublicationDate(), pub.getISBN(), pub.getISSN()});
	}*/
	
	/*@Test
	public void multiplePublicationsTest() {
		PublicationId publicationService = PublicationId.getSingleton();
		
		String[] pubIds = new String[]{"U9905581xPUB15", "u4167262xPUB156", "MigratedxPub11256", "u9204672xPUB415", "u9803255xPUB424", "u9803255xPUB425", "u9803255xPUB449", "u9803255xPUB450", "u9803255xPUB472"};
		Publication pub = null;
		for (String id : pubIds) {
			pub = publicationService.getSinglePublication(id);
			LOGGER.info("{}, {}", pub.getAriesId(), pub.getPublicationTitle());
		}
	}*/
	
	@Test
	public void publicationsByYearTest() {
		PublicationId publicationService = PublicationId.getSingleton();
		LOGGER.info("Begin Retrieving Publications");
		Publication[] publications = publicationService.getPublicationsForYear("1997");
		for (Publication pub : publications) {
			LOGGER.info("ID: {}, Title: {}, Name: {}, Type: {}, Category: {}", new Object[] {pub.getAriesId(), pub.getPublicationTitle(), pub.getPublicationName(), pub.getPublicationType(), pub.getPublicationCategory()});
		}
		LOGGER.info("Retrieval of Publications Finished. Number of Publications: {}", publications.length);
	}

}
