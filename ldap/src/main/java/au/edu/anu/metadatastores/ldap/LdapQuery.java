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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>LdapQuery<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Class to generate ldap queries</p>
 * 
 * @author Genevieve Turner
 *
 */
public class LdapQuery {
	static final Logger LOGGER = LoggerFactory.getLogger(LdapQuery.class);
	private Map<String, String> attributeMap;
	private Map<String, String> approximateMap;
	private Map<String, String> partialMap;
	
	/**
	 * Constructor
	 */
	public LdapQuery() {
		attributeMap = new HashMap<String, String>();
		approximateMap = new HashMap<String, String>();
		partialMap = new HashMap<String, String>();
	}
	
	/**
	 * Clear the current query
	 */
	public void clear() {
		attributeMap.clear();
		approximateMap.clear();
		partialMap.clear();
	}
	
	/**
	 * Add an attribute to query.  This method adds to attribute to be queried in ldap with '='
	 * 
	 * @param attribute The attribute to query
	 * @param value The value of the attribute to query
	 */
	public void addAttribute(String attribute, String value) {
		attributeMap.put(attribute, value);
	}
	
	/**
	 * Add an attribute to the query that will be approximately searched.  This method replicates the functionality
	 * of '~='.
	 * 
	 * @param attribute The attribute to query
	 * @param value The value of the attribute to query
	 */
	public void addApproximateAttribute(String attribute, String value) {
		approximateMap.put(attribute, value);
	}
	
	/**
	 * Add an attribute to the query that indicates it is part of the attribute.  It will repilicate the query
	 * function of '*value*'.
	 * 
	 * @param attribute The attribute to query
	 * @param value The value of the attribute to query
	 */
	public void addPartialAttribute(String attribute, String value) {
		partialMap.put(attribute, "*" + value + "*");
	}
	
	/**
	 * Get the query as a string
	 * 
	 * @return The query
	 */
	public String getQuery() {
		StringBuilder query = new StringBuilder();
		if (attributeMap.size() > 1 || approximateMap.size() > 1 || partialMap.size() > 1) {
			query.append("(&");
		}
		String filter = mapToString(attributeMap, "=");
		query.append(filter);
		filter = mapToString(approximateMap, "~=");
		query.append(filter);
		filter = mapToString(partialMap, "=");
		query.append(filter);

		if (attributeMap.size() > 1 || approximateMap.size() > 1 || partialMap.size() > 1) {
			query.append(")");
		}
		LOGGER.info("Query is: {}", query.toString());
		
		return query.toString();
	}
	
	/**
	 * Produce a String out of the given map.
	 * 
	 * @param map The map to generate as a string
	 * @param delimeter The delimiter to use in the string generation
	 * @return The normalised string
	 */
	private String mapToString(Map<String, String> map, String delimeter) {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String> entry : map.entrySet()) {
			sb.append("(");
			sb.append(entry.getKey());
			sb.append(delimeter);
			sb.append(entry.getValue());
			sb.append(")");
		}
		return sb.toString();
	}
	
	/**
	 * Overrides the toString method returning the same information as getQuery
	 * 
	 * @return The string representation of this object
	 */
	public String toString() {
		return getQuery();
	}
}
