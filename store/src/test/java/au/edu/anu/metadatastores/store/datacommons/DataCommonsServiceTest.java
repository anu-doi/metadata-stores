package au.edu.anu.metadatastores.store.datacommons;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataCommonsServiceTest {
	static final Logger LOGGER = LoggerFactory.getLogger(DataCommonsServiceTest.class);
	
	@Test
	public void test() {
		DataCommonsService dcservice = DataCommonsService.getSingleton();
		//try {
			dcservice.processHarvestContent();
		/*}
		catch (Exception e) {
			LOGGER.error("Exception processing content", e);
		}*/
		//fail("Not yet implemented");
		LOGGER.info("Done");
	}

}
