package au.edu.anu.metadatastores.datamodel.aries.publications;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.services.aries.AriesHibernateUtil;

/**
 * ResearchOutputsConferencesTest
 * 
 * The Australian National University
 * 
 * Test class for ResearchOutputsConferencesTest
 * 
 * @author Genevieve Turner
 *
 */
public class ResearchOutputsConferencesTest {
	static final Logger LOGGER = LoggerFactory.getLogger(ResearchOutputsConferencesTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		try {
			Query query =session.createQuery("FROM ResearchOutputsConferences where intConferenceCode = 0");
			
			ResearchOutputsConferences conf = (ResearchOutputsConferences) query.uniqueResult();
			
			if (conf != null) {
				LOGGER.info("Name: {}, ISBN: {}, Year: {}", new Object[] {conf.getChrConferenceName(), conf.getChrISBN(), conf.getChrYear()});
			}
			else {
				LOGGER.info("There is no conference with this id");
			}
		}
		finally {
			session.close();
		}
	}

}
