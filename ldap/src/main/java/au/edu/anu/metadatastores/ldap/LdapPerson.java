package au.edu.anu.metadatastores.ldap;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

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
	
	public static String[] getAttributes() {
		return new String[] {LdapAttribute.UID, LdapAttribute.FIRSTNAME, LdapAttribute.SURNAME, LdapAttribute.COMMON_NAME
				, "displayName", LdapAttribute.EMAIL, LdapAttribute.PHONE, LdapAttribute.FAX, "ANUPhoneTitle"
				, "ANUPreferredName", "anustafftype", "ou"};
	}
	
	public LdapPerson() {
		
	}
	
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

	/*	
	 * 
	 * 
		return new String[] {LdapAttribute.UID, LdapAttribute.FIRSTNAME, LdapAttribute.SURNAME, LdapAttribute.COMMON_NAME
				, "displayName", LdapAttribute.EMAIL, LdapAttribute.PHONE, LdapAttribute.FAX, "ANUPhoneTitle"
				, "ANUPreferredName", "anustafftype", "ou"};
	 	private String givenName;
		private String lastName;
		private String commonName;
		private String displayName;
		private String email;
		private String[] phoneNumber;
		private String[] faxNumber;
		private String jobTitle;
		private String preferredName;
		private String staffType;
		private String organisationalUnit; */
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String[] getCommonNames() {
		return commonNames;
	}
	public void setCommonNames(String[] commonNames) {
		this.commonNames = commonNames;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getPhone() {
		return phone;
	}
	public void setPhone(String[] phone) {
		this.phone = phone;
	}
	public String[] getFax() {
		return fax;
	}
	public void setFax(String[] fax) {
		this.fax = fax;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getPreferredName() {
		return preferredName;
	}
	public void setPreferredName(String preferredName) {
		this.preferredName = preferredName;
	}
	public String getStaffType() {
		return staffType;
	}
	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}
	public String getOrganisationalUnit() {
		return organisationalUnit;
	}
	public void setOrganisationalUnit(String organisationalUnit) {
		this.organisationalUnit = organisationalUnit;
	}
}
