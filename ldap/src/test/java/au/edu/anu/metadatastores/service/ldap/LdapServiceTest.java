package au.edu.anu.metadatastores.service.ldap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.naming.NamingException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.ldap.LdapAttribute;
import au.edu.anu.metadatastores.services.ldap.LdapService;

public class LdapServiceTest {
	static final Logger LOGGER = LoggerFactory.getLogger(LdapServiceTest.class);

	@Test
	public void test() {
		LdapService ldapService = LdapService.getSingleton();
		
		try {
			String[] uids = ldapService.searchForSimilarNames("Turner", "Genevieve");
			assertNotNull("There are no results from search for similar names", uids);
			LOGGER.info("Number: {}, UIDS: {}", uids.length, arrayToString(uids));
			String[] possibleUIDs = ldapService.searchForPossibleNames("Turner", "Genevieve");
			assertNotNull("There are no results from search for possible names", possibleUIDs);
			LOGGER.info("Number: {}, Potential UIDS: {}", possibleUIDs.length, arrayToString(possibleUIDs));
			String surname = ldapService.getANUPartyLdapInfo("u5125986", LdapAttribute.SURNAME);
			assertNotNull("There is no surname for the given uid", surname);
			assertEquals("The surname was not the expected result", "Turner", surname);
			//LOGGER.info("Surname: {}", surname);
			String[] firstnames = ldapService.getANUPartyLdapInfo(new String[] {"u5125986","a357820"}, LdapAttribute.FIRSTNAME);
			assertNotNull("There are no given names for the list of uids", firstnames);
			assertTrue("There is not the correct number of records", firstnames.length == 2);
			LOGGER.info("Number: {}, First names: {}", firstnames, arrayToString(firstnames));
		}
		catch (NamingException e) {
			LOGGER.error("Exception processing", e);
			fail("Naming Exception Occured");
		}
	}
	
	private String arrayToString(String[] values) {
		StringBuilder sb = new StringBuilder();
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if (i > 0) {
					sb.append(", ");
				}
				sb.append(values[i]);
			}
		}
		
		return sb.toString();
	}

}
