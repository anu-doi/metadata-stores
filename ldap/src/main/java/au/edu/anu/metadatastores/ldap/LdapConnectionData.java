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

import java.util.Hashtable;

import javax.naming.Context;

/**
 * LdapConnectionData
 * 
 * The Australian National University
 * 
 * Container for information about LDAP connections
 * 
 * @author Genevieve Turner
 *
 */
public class LdapConnectionData {
	private String url_;
	private String baseDN_;
	
	private static final String DEFAULT_CTX = "com.sun.jndi.ldap.LdapCtxFactory";
	
	/**
	 * Get the ldap connection url
	 * 
	 * @return the connection url
	 */
	public String getURL() {
		return url_;
	}
	
	/**
	 * Set the ldap connection url
	 * 
	 * @param url The ldap connection url
	 */
	public void setURL(String url) {
		if (url.toLowerCase().startsWith("ldaps://")) {
			url_ = url;
		}
		else if (url.toLowerCase().startsWith("ldap://")) {
			url_ = url;
		}
		else {
			url = "ldaps://" + url;
		}
	}
	
	/**
	 * Set the ldap connection url
	 * 
	 * @param host The host of the ldap connection
	 * @param port The port of the ldap connection
	 */
	public void setURL(String host, int port) {
		url_ = "ldaps://" + host + ":" + port;
	}
	
	/**
	 * Get the base distinguished name
	 * 
	 * @return The distinguished name. e.g. 'ou=People o=anu.edu.au'
	 */
	public String getBaseDN() {
		return baseDN_;
	}
	
	/**
	 * Set the base distinguished name
	 * 
	 * @param baseDN The distinguished name. e.g. 'ou=People o=anu.edu.au'
	 */
	public void setBaseDN(String baseDN) {
		this.baseDN_ = baseDN;
	}
	
	/**
	 * Get the connection properties
	 * 
	 * @return The connection properties
	 */
	public Hashtable getConnectionProperties() {
		Hashtable env = new Hashtable();
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, DEFAULT_CTX);
		env.put(Context.PROVIDER_URL, url_);
	//	env.put(key, value)
		return env;
	}
}
