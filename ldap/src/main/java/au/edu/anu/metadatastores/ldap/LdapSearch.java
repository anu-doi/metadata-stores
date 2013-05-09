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

package au.edu.anu.metadatastores.ldap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.util.properties.PropertyLoader;

/**
 * <p>LdapSearch<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Class that generates and executes search methods for LdapService</p>
 * 
 * @author Genevieve Turner
 *
 */
public class LdapSearch {
	static final Logger LOGGER = LoggerFactory.getLogger(LdapSearch.class);
	private static LdapSearch singleton_;
	
	private Properties properties_;
	private static LdapConnectionData baseConnectionData_;
	private static LdapConnectionData peopleConnectionData_;
	private static LdapConnection ldapConnection_ = LdapConnection.getSingleton();
	
	/**
	 * Constructor
	 */
	private LdapSearch() {
		properties_ = PropertyLoader.loadProperties("ldap.properties");
		
		if (properties_ == null) {
			LOGGER.error("No ldap property file found.");
		}
		baseConnectionData_ = new LdapConnectionData();
		baseConnectionData_.setURL(properties_.getProperty("ldap.uri"));
		baseConnectionData_.setBaseDN(properties_.getProperty("ldap.baseDn"));
		
		peopleConnectionData_ = new LdapConnectionData();
		peopleConnectionData_.setURL(properties_.getProperty("ldap.uri"));
		peopleConnectionData_.setBaseDN(properties_.getProperty("ldap.peopleDn"));
	}
	
	/**
	 * Returns the singleton LdapSearch object
	 * 
	 * @return The LdapSearch object
	 */
	public static synchronized LdapSearch getSingleton() {
		if (singleton_ == null) {
			singleton_ = new LdapSearch();
		}
		return singleton_;
	}
	
	/**
	 * Search for people for whom their name is approximately the same as that which has been given 
	 * 
	 * @param surname Surname of the people
	 * @param givenName Given name of the people
	 * @return The uid's of the people found
	 * @throws NamingException
	 */
	public String[] searchNames(String surname, String givenName) throws NamingException {
		LdapQuery query = new LdapQuery();
		if (surname != null && surname.length() > 0) {
			query.addApproximateAttribute(LdapAttribute.SURNAME, surname);
		}
		if (givenName != null && givenName.length() > 0) {
			query.addApproximateAttribute(LdapAttribute.FIRSTNAME, givenName);
		}
		
		String[] returnFields = {LdapAttribute.UID};
		List<String> combinedResults = new ArrayList<String>();
		Attributes[] results = ldapConnection_.search(peopleConnectionData_, query.toString(), returnFields);
		combineResults(results, combinedResults);
		
		if (combinedResults.size() == 0) {
			query.clear();
			if (surname != null && surname.length() > 0 && givenName != null && givenName.length() > 0) {
				query.addAttribute(LdapAttribute.COMMON_NAME, givenName + " " + surname);
			}
			results = ldapConnection_.search(peopleConnectionData_, query.toString(), returnFields);
			combineResults(results, combinedResults);
		}
		return combinedResults.toArray(new String[0]);
	}

	/**
	 * Search for people for whom their name is similar to that which has been given 
	 * 
	 * @param surname Surname of the people
	 * @param givenName Given name of the people
	 * @return The uid's of the people found
	 * @throws NamingException
	 */
	public String[] searchSimilarNames(String surname, String givenName) throws NamingException {
		String[] returnFields = {LdapAttribute.UID};
	
		LdapQuery query = new LdapQuery();
		boolean hasGivenName = false;
		boolean hasSurname = false;
		
		if (givenName != null && givenName.length() > 0) {
			hasGivenName = true;
		}
		
		if (surname != null && surname.length() > 0) {
			hasSurname = true;
		}
		
		if (!hasGivenName && !hasSurname) {
			return new String[0];
		}
		
		if (hasGivenName) {
			query.addApproximateAttribute(LdapAttribute.FIRSTNAME, givenName);
		}
		if (hasSurname) {
			query.addApproximateAttribute(LdapAttribute.SURNAME, surname);
		}
		List<String> combinedResults = new ArrayList<String>();
		LOGGER.debug("Query: {}", query.toString());
		Attributes[] results = ldapConnection_.search(peopleConnectionData_, query.toString(), returnFields);
		combineResults(results, combinedResults);
		
		query.clear();
		if (hasGivenName) {
			query.addApproximateAttribute(LdapAttribute.FIRSTNAME, givenName);
		}
		if (hasSurname) {
			query.addPartialAttribute(LdapAttribute.SURNAME, surname);
		}
		LOGGER.debug("Query: {}", query.toString());
		results = ldapConnection_.search(peopleConnectionData_, query.toString(), returnFields);
		combineResults(results, combinedResults);
		
		query.clear();
		if (hasGivenName) {
			query.addPartialAttribute(LdapAttribute.FIRSTNAME, givenName);
		}
		if (hasSurname) {
			query.addApproximateAttribute(LdapAttribute.SURNAME, surname);
		}
		LOGGER.debug("Query: {}", query.toString());
		results = ldapConnection_.search(peopleConnectionData_, query.toString(), returnFields);
		combineResults(results, combinedResults);
		
		query.clear();
		if (hasGivenName) {
			query.addPartialAttribute(LdapAttribute.FIRSTNAME, givenName);
		}
		if (hasSurname) {
			query.addPartialAttribute(LdapAttribute.SURNAME, surname);
		}
		LOGGER.debug("Query: {}", query.toString());
		results = ldapConnection_.search(peopleConnectionData_, query.toString(), returnFields);
		combineResults(results, combinedResults);
		
		return combinedResults.toArray(new String[0]);
	}
	
	/**
	 * Combines the attribute results of uid into a list
	 * 
	 * @param attributes The array of attributes to combine
	 * @param results The list to add the items to
	 * @throws NamingException
	 */
	private void combineResults(Attributes[] attributes, List<String> results) throws NamingException {
		for (Attributes attr : attributes) {
			Attribute attribute = attr.get(LdapAttribute.UID);
			if (attribute != null && attribute.size() > 0) {
				if (!results.contains(attribute.get(0))) {
					results.add((String)attribute.get(0));
				}
			}
		}
	}
	
	/**
	 * Find information about the person with the given university id
	 * 
	 * @param uniID The university
	 * @return Attributes of the person associated with the given university id
	 * @throws NamingException
	 */
	public String[] searchUniversityId(String uniID) throws NamingException {
		LdapQuery query = new LdapQuery();
		query.addAttribute(LdapAttribute.UID, uniID);
		String[] returnFields = {LdapAttribute.UID, LdapAttribute.FIRSTNAME, LdapAttribute.SURNAME, LdapAttribute.EMAIL};
		Attributes[] results = ldapConnection_.search(peopleConnectionData_, query.toString(), returnFields);
		String[] names = new String[results.length];
		Attribute surname = null;
		Attribute givenName = null;
		StringBuilder fullName = null;
		
		for (int i = 0; i < results.length; i++) {
			fullName = new StringBuilder();
			
			surname = results[i].get(LdapAttribute.SURNAME);
			givenName = results[i].get(LdapAttribute.FIRSTNAME);
			if (givenName != null && givenName.size() > 0) {
				fullName.append(givenName.get(0));
				fullName.append(" ");
			}
			if (surname != null && surname.size() > 0) {
				fullName.append(surname.get(0));
			}
			names[i] = fullName.toString();
		}
		
		return names;
	}
	
	/**
	 * Find the value of the attribute given the university id
	 * 
	 * @param uniID The university id
	 * @param returnField The field to return
	 * @return The value
	 * @throws NamingException
	 */
	public String getUserAttributeValue(String uniID, String returnField) throws NamingException {
		String[] returnFields = {returnField};
		
		LdapQuery query = new LdapQuery();
		query.addAttribute(LdapAttribute.UID, uniID);
		
		StringBuilder value = new StringBuilder();
		
		Attributes[] results = ldapConnection_.search(peopleConnectionData_, query.toString(), returnFields);
		if (results != null && results.length > 0) {
			Attribute attribute = results[0].get(returnField);
			for (int i = 0; i < attribute.size(); i++) {
				if (i > 1) {
					value.append(";");
				}
				value.append((String) attribute.get(i));
			}
		}
		
		return value.toString();
	}
	
	/**
	 * Get the person information with the given university id
	 * 
	 * @param uniID The university id
	 * @return The information about the person associated with the given id
	 * @throws NamingException
	 */
	public LdapPerson getUserAttributes(String uniID) throws NamingException {
		LdapQuery query = new LdapQuery();
		query.addAttribute("uid", uniID);
		LdapPerson person = null;
		Attributes[] results = ldapConnection_.search(peopleConnectionData_, query.toString(), LdapPerson.getAttributes());
		if (results != null && results.length > 0) {
			person = new LdapPerson(results[0]);
		}
		return person;
	}
	
	/**
	 * Returns all university ids
	 * 
	 * @return An array of university ids
	 * @throws NamingException
	 * @throws IOException
	 */
	public String[] getLdapANUIDs() throws NamingException, IOException {
		String[] returnFields = {LdapAttribute.UID};
		
		LdapQuery query = new LdapQuery();
		query.addAttribute(LdapAttribute.OBJECT_CLASS, "*");
		
		LOGGER.debug("Before find ids");
		Attributes[] results = ldapConnection_.search(peopleConnectionData_, query.toString(), returnFields, 0, 0, SearchControls.ONELEVEL_SCOPE);
		LOGGER.debug("After find ids");
		if (results != null) {
			LOGGER.debug("Number of results: {}", results.length);
		}
		StringBuilder ids = null;
		String[] uniIDs = new String[results.length];
		for (int i = 0; i < results.length && i < 100; i++) {
			Attribute uid = results[i].get(LdapAttribute.UID);
			if (uid != null && uid.size() > 0) {
				ids = new StringBuilder();
				for (int j = 0; j < uid.size(); j++) {
					if (j > 0) {
						ids.append(";");
					}
					ids.append(uid);
				}
				uniIDs[i] = ids.toString();
			}
			else {
				uniIDs[i] = "No uid for this record";
			}
		}
		
		return uniIDs;
	}
}
