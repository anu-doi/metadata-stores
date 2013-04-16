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
import javax.naming.ldap.Control;
import javax.naming.ldap.PagedResultsResponseControl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Connection class that searches ldaps
 * @author u5125986
 *
 */
public class LdapConnection {
	static final Logger LOGGER = LoggerFactory.getLogger(LdapConnection.class);

//	private static final int pageSize = 10;
	
	private static LdapConnection singleton_;
	
	
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

	/**
	 * Searches LDAP given the specified criteria.  Recommended for queries where it is expected that a large number 
	 * of results are returned as it will page the results and thus get around limitations of some LDAP servers (such as
	 * Active Directory).
	 * 
	 * @param connectionData
	 * @param query
	 * @param returnFields
	 * @return
	 * @throws NamingException
	 * @throws IOException
	 */
	/*public Attributes[] pagedSearch(LdapConnectionData connectionData, String query, String[] returnFields)
			throws NamingException, IOException {
		LdapContext ldapContext = new InitialLdapContext(connectionData.getConnectionProperties(), null);
		
		SearchControls searchControls = new SearchControls();
		searchControls.setReturningAttributes(returnFields);
		
		byte[] cookie = null;
		Control[] controls = new Control[]{new PagedResultsControl(pageSize, Control.CRITICAL)};
		ldapContext.setRequestControls(controls);
		
		//TODO remove maxLoops and i
		int maxLoops = 5;
		int i = 0;
		List<Attributes> attributes = new ArrayList<Attributes>();
		
		do {
			i++;
			NamingEnumeration<SearchResult> results = ldapContext.search(connectionData.getBaseDN(), query, searchControls);
			while (results != null && results.hasMore()) {
				SearchResult entry = (SearchResult) results.next();
				attributes.add(entry.getAttributes());
			}
			
			cookie = getPagedResultsControlCookie(ldapContext.getResponseControls());
			
			ldapContext.setRequestControls(new Control[] {new PagedResultsControl(pageSize, cookie, Control.CRITICAL)});
		}
		while (cookie != null && i < maxLoops);
		
		return attributes.toArray(new Attributes[0]);
	}*/
	
	/**
	 * Retrieves the cookie from the given controls
	 * 
	 * @param controls Controls generally from the response of a LDAP query
	 * @return The byte array of the cookies
	 */
	private byte[] getPagedResultsControlCookie(Control[] controls) {
		byte[] cookie = null;
		if (controls != null) {
			for (int i = 0; i < controls.length; i++) {
				if (controls[i] instanceof PagedResultsResponseControl) {
					PagedResultsResponseControl prrc = (PagedResultsResponseControl) controls[i];
					cookie = prrc.getCookie();
				}
			}
		}
		return cookie;
	}
}
