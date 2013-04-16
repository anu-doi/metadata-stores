package au.edu.anu.metadatastores.services.ldap;

import java.io.IOException;

import javax.naming.NamingException;

import au.edu.anu.metadatastores.ldap.LdapPerson;
import au.edu.anu.metadatastores.ldap.LdapSearch;

/**
 * LDAP Service method
 * 
 * @author u5125986
 *
 */
public class LdapService {
	private static LdapService singleton_;
	private static LdapSearch ldapSearch_ = LdapSearch.getSingleton();
	
	private LdapService() {
		
	}
	
	public static synchronized LdapService getSingleton() {
		if (singleton_ == null) {
			singleton_ = new LdapService();
		}
		return singleton_;
	}
	
	/**
	 * Search for names that are similar to the one given
	 * 
	 * @param surname
	 * @param givenName
	 * @return
	 * @throws NamingException
	 */
	public String[] searchForSimilarNames(String surname, String givenName) throws NamingException {
		return ldapSearch_.searchNames(surname, givenName);
	}
	
	/**
	 * Search for names that are similar or partial matches given the surname and given name
	 * 
	 * @param surname
	 * @param givenName
	 * @return
	 * @throws NamingException
	 */
	public String[] searchForPossibleNames(String surname, String givenName) throws NamingException {
		return ldapSearch_.searchSimilarNames(surname, givenName);
	}
	
	/**
	 * Retrieves all the university id's in ldap
	 * 
	 * @return A list of university id's
	 * @throws NamingException
	 * @throws IOException
	 */
	public String[] getLdapANUIDs() throws NamingException, IOException {
		return ldapSearch_.getLdapANUIDs();
	}
	
	/**
	 * Retrieves information about the person for the given university id and ldap attribute
	 * 
	 * @param uniID
	 * @param ldapAttr
	 * @return
	 * @throws NamingException
	 */
	public String getANUPartyLdapInfo(String uniID, String ldapAttr) throws NamingException {
		return ldapSearch_.getUserAttributeValue(uniID, ldapAttr);
	}
	
	/**
	 * Retrieves information in the ldap attribute for the given university id's
	 * 
	 * @param uniIDs
	 * @param ldapAttr
	 * @return
	 * @throws NamingException
	 */
	public String[] getANUPartyLdapInfo(String[] uniIDs, String ldapAttr) throws NamingException {
		String tempResult = null;
		String[] results = new String[uniIDs.length];
		
		for (int i = 0; i < uniIDs.length; i++) {
			tempResult = getANUPartyLdapInfo(uniIDs[i], ldapAttr);
			if (tempResult != null && tempResult.length() > 0) {
				results[i] = tempResult;
			}
			else {
				results[i] = null;
			}
		}
		
		return results;
	}
	
	public LdapPerson getANUPartyLdapInfo(String uniID) throws NamingException {
		//return ldapSearch_.getUserAttributes(uniID);
		return ldapSearch_.getUserAttributes(uniID);
	//	return null;
	}
}
