package au.edu.anu.metadatastores.ldap;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to generate ldap queries
 * @author u5125986
 *
 */
public class LdapQuery {
	static final Logger LOGGER = LoggerFactory.getLogger(LdapQuery.class);
	private Map<String, String> attributeMap;
	private Map<String, String> approximateMap;
	private Map<String, String> partialMap;
	
	/**
	 * 
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
	 * 
	 */
	public String toString() {
		return getQuery();
	}
}
