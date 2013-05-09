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

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

/**
 * <p>LdapPerson<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Class that stores information about a person that has been retrieved from ldap</p>
 * 
 * @author Genevieve Turner
 *
 */
public class LdapPerson {
	private String uid;
	private String givenName;
	private String surname;
	private String[] commonNames;
	private String displayName;
	private String email;
	private String[] phone;
	private String[] fax;
	private String jobTitle;
	private String preferredName;
	private String staffType;
	private String organisationalUnit;
	
	/**
	 * Returns a list of attributes
	 * 
	 * @return The attributes used in ldap
	 */
	public static String[] getAttributes() {
		return new String[] {LdapAttribute.UID, LdapAttribute.FIRSTNAME, LdapAttribute.SURNAME, LdapAttribute.COMMON_NAME
				, "displayName", LdapAttribute.EMAIL, LdapAttribute.PHONE, LdapAttribute.FAX, "ANUPhoneTitle"
				, "ANUPreferredName", "anustafftype", "ou"};
	}
	
	/**
	 * Constructor
	 */
	public LdapPerson() {
		
	}
	
	/**
	 * Create the ldap person with the given attributes
	 * 
	 * @param attributes The list of attributes to associate with information in the ldap person
	 * @throws NamingException
	 */
	public LdapPerson(Attributes attributes) throws NamingException {
		if (attributes.get(LdapAttribute.UID) != null && attributes.get(LdapAttribute.UID).size() > 0) {
			setUid((String)attributes.get(LdapAttribute.UID).get(0));
		}
		if (attributes.get(LdapAttribute.FIRSTNAME) != null && attributes.get(LdapAttribute.FIRSTNAME).size() > 0) {
			setGivenName((String)attributes.get(LdapAttribute.FIRSTNAME).get(0));
		}
		if (attributes.get(LdapAttribute.SURNAME) != null && attributes.get(LdapAttribute.SURNAME).size() > 0) {
			setSurname((String)attributes.get(LdapAttribute.SURNAME).get(0));
		}
		if (attributes.get(LdapAttribute.COMMON_NAME) != null && attributes.get(LdapAttribute.COMMON_NAME).size() > 0) {
			String[] commonNames = new String[attributes.get(LdapAttribute.COMMON_NAME).size()];
			
			Attribute attribute = attributes.get(LdapAttribute.COMMON_NAME);
			for (int i = 0;i < commonNames.length; i++) {
				commonNames[i] = (String) attribute.get(i);
			}
			setCommonNames(commonNames);
		}
		if (attributes.get("displayName") != null && attributes.get("displayName").size() > 0) {
			setDisplayName((String)attributes.get("displayName").get(0));
		}
		if (attributes.get(LdapAttribute.EMAIL) != null && attributes.get(LdapAttribute.EMAIL).size() > 0) {
			setEmail((String)attributes.get(LdapAttribute.EMAIL).get(0));
		}
		if (attributes.get(LdapAttribute.PHONE) != null && attributes.get(LdapAttribute.PHONE).size() > 0) {
			String[] phoneNumbers = new String[attributes.get(LdapAttribute.PHONE).size()];
			Attribute attribute = attributes.get(LdapAttribute.PHONE);
			for (int i = 0;i < phoneNumbers.length; i++) {
				phoneNumbers[i] = (String) attribute.get(i);
			}
			setPhone(phoneNumbers);
		}
		if (attributes.get(LdapAttribute.FAX) != null && attributes.get(LdapAttribute.FAX).size() > 0) {
			String[] faxNumbers = new String[attributes.get(LdapAttribute.FAX).size()];
			Attribute attribute = attributes.get(LdapAttribute.FAX);
			for (int i = 0;i < faxNumbers.length; i++) {
				faxNumbers[i] = (String) attribute.get(i);
			}
			setFax(faxNumbers);
		}
		if (attributes.get("ANUPhoneTitle") != null && attributes.get("ANUPhoneTitle").size() > 0) {
			setJobTitle((String)attributes.get("ANUPhoneTitle").get(0));
		}
		if (attributes.get("ANUPreferredName") != null && attributes.get("ANUPreferredName").size() > 0) {
			setPreferredName((String)attributes.get("ANUPreferredName").get(0));
		}
		if (attributes.get("anustafftype") != null && attributes.get("anustafftype").size() > 0) {
			setStaffType((String)attributes.get("anustafftype").get(0));
		}
		if (attributes.get("ou") != null && attributes.get("ou").size() > 0) {
			setOrganisationalUnit((String)attributes.get("ou").get(0));
		}
	}
	
	/**
	 * Get the university id of the person
	 * 
	 * @return The university id
	 */
	public String getUid() {
		return uid;
	}
	
	/**
	 * Set the university of the person
	 * 
	 * @param uid The university id
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	/**
	 * Get the given name of the person
	 * 
	 * @return The given name
	 */
	public String getGivenName() {
		return givenName;
	}
	
	/**
	 * Set the given name of the person
	 * 
	 * @param givenName The given name
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	/**
	 * Get the surname of the person
	 * 
	 * @return The surname
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Set the surname
	 * 
	 * @param surname The surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**
	 * Get the common names
	 * 
	 * @return The common names
	 */
	public String[] getCommonNames() {
		return commonNames;
	}
	
	/**
	 * Set the common names
	 * 
	 * @param commonNames The common names
	 */
	public void setCommonNames(String[] commonNames) {
		this.commonNames = commonNames;
	}
	
	/**
	 * Get the display name
	 * 
	 * @return The display name
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	/**
	 * Set the display name
	 * 
	 * @param displayName The display name
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	/**
	 * Get the email address
	 * 
	 * @return The email address
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Set the email address
	 * 
	 * @param email The email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Get the phone numbers
	 * 
	 * @return The phone numbers
	 */
	public String[] getPhone() {
		return phone;
	}
	
	/**
	 * Set the phone numbers
	 * 
	 * @param phone The phone numbers
	 */
	public void setPhone(String[] phone) {
		this.phone = phone;
	}
	
	/**
	 * Get the fax numbers
	 * 
	 * @return The fax numbers
	 */
	public String[] getFax() {
		return fax;
	}
	
	/**
	 * Set the fax numbers
	 * 
	 * @param fax The fax numbers
	 */
	public void setFax(String[] fax) {
		this.fax = fax;
	}
	
	/**
	 * Get the job title
	 * 
	 * @return The job title
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	
	/**
	 * Set the job title
	 * 
	 * @param jobTitle The job title
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	/**
	 * Get the preferred name
	 * 
	 * @return The preferred name
	 */
	public String getPreferredName() {
		return preferredName;
	}
	
	/**
	 * Set the preferred name
	 * 
	 * @param preferredName The preferred name
	 */
	public void setPreferredName(String preferredName) {
		this.preferredName = preferredName;
	}
	
	/**
	 * Get the staff type
	 * 
	 * @return The staff type
	 */
	public String getStaffType() {
		return staffType;
	}
	
	/**
	 * Set the staff type
	 * 
	 * @param staffType The staff type
	 */
	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}
	
	/**
	 * Get the organisational unit
	 * 
	 * @return The organisational unit
	 */
	public String getOrganisationalUnit() {
		return organisationalUnit;
	}
	
	/**
	 * Set the organisational unit
	 * 
	 * @param organisationalUnit The organisational unit
	 */
	public void setOrganisationalUnit(String organisationalUnit) {
		this.organisationalUnit = organisationalUnit;
	}
}
