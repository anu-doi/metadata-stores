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

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>LdapConnection<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Connection classs that searches ldap</p>
 * 
 * @author Genevieve Turner
 *
 */
public class LdapConnection {
	static final Logger LOGGER = LoggerFactory.getLogger(LdapConnection.class);
	
	private static LdapConnection singleton_;
	
	/**
	 * Constructor
	 */
	private LdapConnection () {
	}
	
	/**
	 * Retrieves the LdapConnection
	 * 
	 * @return the LdapConnection
	 */
	public static synchronized LdapConnection getSingleton() {
		if (singleton_ == null) {
			singleton_ = new LdapConnection();
		}
		return singleton_;
	}
	
	/**
	 * Denies allowing the clone method
	 */
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	/**
	 * Searches LDAP given the specified criteria.
	 * 
	 * @param connectionData The connection data to search with
	 * @param query The query to execute
	 * @param returnFields The fields to return
	 * @return An array of attributes that have been returned
	 * @throws NamingException
	 */
	public Attributes[] search(LdapConnectionData connectionData, String query, String[] returnFields)
			throws NamingException {
		return search(connectionData, query, returnFields, 100000, 0, SearchControls.SUBTREE_SCOPE);
	}

	/**
	 * Searches LDAP given the specified criteria.
	 * 
	 * @param connectionData The connection data to search with
	 * @param query The query to execute
	 * @param returnFields The fields to return
	 * @param timeout The period of time until a timeout occurs
	 * @param countLimit The maximum number of rows to return (note this does not limit how many rows are returned, rather it throws an exception if this limit is exceeded)
	 * @param scope The scope for the search
	 * @return An array of attributes that have been returned
	 * @throws NamingException
	 */
	public Attributes[] search(LdapConnectionData connectionData, String query, String[] returnFields, int timeout, int countLimit, int scope)
			throws NamingException {

		DirContext dirContext = new InitialDirContext(connectionData.getConnectionProperties());
		SearchControls searchControls = new SearchControls();
		searchControls.setReturningAttributes(returnFields);
		//Initial Test Purposes
		searchControls.setCountLimit(countLimit);
		searchControls.setTimeLimit(timeout);
		searchControls.setSearchScope(scope);
		
		NamingEnumeration<SearchResult> results = dirContext.search(connectionData.getBaseDN(), query, searchControls);
		dirContext.close();
		SearchResult singleResult = null;
		List<Attributes> attributesList = new ArrayList<Attributes>();
		while (results.hasMore()) {
			singleResult = results.next();
			Attributes attributes = singleResult.getAttributes();
			attributesList.add(attributes);
		}
		
		return attributesList.toArray(new Attributes[0]);
	}
}
