package au.edu.anu.metadatastores.service.ldap;

import static org.junit.Assert.fail;

import java.io.IOException;

import javax.naming.NamingException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.services.ldap.LdapService;

/**
 * Placed this method in a separate class as it will take a lot longer to execute and thus we may wish to execute it at a different
 * stage.
 * 
 * @author u5125986
 *
 */
public class LdapServiceUIDsTest {
	static final Logger LOGGER = LoggerFactory.getLogger(LdapServiceUIDsTest.class);

	@Test
	public void test() {
		LdapService ldapService = LdapService.getSingleton();
		
		try {
			String[] uniIDs = ldapService.getLdapANUIDs();
			String id = null;
			for (int i = 0; i < uniIDs.length && i < 50; i++) {
				id = uniIDs[i];
				LOGGER.info("UID: {}", id);
			}
		}
		catch (NamingException e) {
			LOGGER.error("NamingException retrieving university ids", e);
			fail("Naming Exception");
		}
		catch (IOException e) {
			LOGGER.error("IOException retrieving university ids", e);
			fail("IOException");
		}
		LOGGER.info("End Test");
	}

}
