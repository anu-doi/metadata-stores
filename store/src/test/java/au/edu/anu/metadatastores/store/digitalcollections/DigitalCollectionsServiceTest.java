package au.edu.anu.metadatastores.store.digitalcollections;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DigitalCollectionsServiceTest {
	static final Logger LOGGER = LoggerFactory.getLogger(DigitalCollectionsServiceTest.class);
	
	@Test
	public void test() {
		DigitalCollectionsService dcService = DigitalCollectionsService.getSingleton();
		
		dcService.processHarvestContent();
		LOGGER.info("Done");
	}
}
