package au.edu.anu.metadatastores.ldap;

import java.util.Hashtable;

import javax.naming.Context;

/**
 * Class that contains information about the ldap connection
 * @author u5125986
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
