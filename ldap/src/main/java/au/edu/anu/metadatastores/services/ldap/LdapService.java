/*******************************************************************************
 * Australian National University Metadata Stores
 * Copyright (C) 2013  The Australian National University
 * 
 * This file is part of Australian National University Metadata Stores.
 * 
 * Australian National University Metadata Stores is free software: you
 * can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package au.edu.anu.metadatastores.services.ldap;

import java.io.IOException;

import javax.naming.NamingException;

import au.edu.anu.metadatastores.ldap.LdapPerson;
import au.edu.anu.metadatastores.ldap.LdapSearch;

/**
 * <p>LdapService<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Service class that provides information from ldap to external parties</p>
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
public class LdapService {
	private static LdapService singleton_;
	private static LdapSearch ldapSearch_ = LdapSearch.getSingleton();
	
	/**
	 * Constructor
	 */
	private LdapService() {
		
	}
	
	/**
	 * Get the singleton for LdapService
	 * 
	 * @return The LdapService
	 */
	public static synchronized LdapService getSingleton() {
		if (singleton_ == null) {
			singleton_ = new LdapService();
		}
		return singleton_;
	}
	
	/**
	 * Search for names that are similar to the one given
	 * 
	 * @param surname The surname to search on
	 * @param givenName The given name to search on
	 * @return A list of uids with a similar name to that given
	 * @throws NamingException
	 */
	public String[] searchForSimilarNames(String surname, String givenName) throws NamingException {
		return ldapSearch_.searchNames(surname, givenName);
	}
	
	/**
	 * Search for names that are similar or partial matches given the surname and given name
	 * 
	 * @param surname The surname to search on
	 * @param givenName The given name to search on
	 * @return A list of uids with a similar name to that given
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
	 * @param uniID The university id to retrieve information for
	 * @param ldapAttr The attribute to get
	 * @return The attribute value
	 * @throws NamingException
	 */
	public String getANUPartyLdapInfo(String uniID, String ldapAttr) throws NamingException {
		return ldapSearch_.getUserAttributeValue(uniID, ldapAttr);
	}
	
	/**
	 * Retrieves information in the ldap attribute for the given university id's
	 * 
	 * @param uniIDs The university ids to retrieve information for
	 * @param ldapAttr The attribute to retrieve
	 * @return The array attribute values
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
	
	/**
	 * Get information about a person given the specified univeresity id
	 * 
	 * @param uniID The university id
	 * @return The information about the person associated with the universeity id
	 * @throws NamingException
	 */
	public LdapPerson getANUPartyLdapInfo(String uniID) throws NamingException {
		return ldapSearch_.getUserAttributes(uniID);
	}
}
