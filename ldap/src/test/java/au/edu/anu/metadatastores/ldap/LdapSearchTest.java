package au.edu.anu.metadatastores.ldap;

import static org.junit.Assert.fail;

import javax.naming.NamingException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LdapSearchTest {
	static final Logger LOGGER = LoggerFactory.getLogger(LdapSearchTest.class);

	@Test
	public void nameTest() {
		LdapSearch ldapSearch = LdapSearch.getSingleton();
		try {
			String[] uniIds = ldapSearch.searchNames("Turner", "Genevieve");
			printStrings(uniIds);
			uniIds = ldapSearch.searchNames(null, "Genevieve");
			printStrings(uniIds);
		}
		catch (NamingException e) {
			LOGGER.error("Naming Exception", e);
			fail("Naming exception");
		}
		
	}
	
	private void printStrings(String[] values) {
		for (String value : values) {
			LOGGER.info("Value: {}", value);
		}
	}
	
	@Test
	public void idTest() {
		LdapSearch ldapSearch = LdapSearch.getSingleton();
		try {
			String[] names = ldapSearch.searchUniversityId("u5125986");
			printStrings(names);
		}
		catch (NamingException e) {
			LOGGER.error("Naming Exception", e);
			fail("Naming exception");
		}
	}

}
