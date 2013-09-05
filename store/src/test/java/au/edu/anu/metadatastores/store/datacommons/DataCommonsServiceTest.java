package au.edu.anu.metadatastores.store.datacommons;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.store.dublincore.xml.DublinCore;

public class DataCommonsServiceTest {
	static final Logger LOGGER = LoggerFactory.getLogger(DataCommonsServiceTest.class);
	
	DataCommonsService dcService_;

	@Before
	public void setUp() throws Exception {
		dcService_ = DataCommonsService.getSingleton();
	}
	
	@Ignore
	@Test
	public void test() {
		//DataCommonsService dcservice = DataCommonsService.getSingleton();
		//try {
		dcService_.processHarvestContent();
		/*}
		catch (Exception e) {
			LOGGER.error("Exception processing content", e);
		}*/
		//fail("Not yet implemented");
		LOGGER.info("Done");
	}
	
	@Test
	public void getByIdTest() {
		DublinCore dcObject = dcService_.getDataCommonsObject("oai:test:1577");
		for (String title : dcObject.getTitles()) {
			LOGGER.info("Title: {}", title);
		}
		for (String identifier : dcObject.getIdentifiers()) {
			LOGGER.info("Identifier: {}", identifier);
		}
		for (String coverage : dcObject.getCoverage()) {
			LOGGER.info("Coverage: {}", coverage);
		}
		for (String creator : dcObject.getCreators()) {
			LOGGER.info("Creator: {}", creator);
		}
		for (String date : dcObject.getDates()) {
			LOGGER.info("Date: {}", date);
		}
		for (String description : dcObject.getDescriptions()) {
			LOGGER.info("Description: {}", description);
		}
	}

}
