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

package au.edu.anu.metadatastores.store.people;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import au.edu.anu.metadatastores.store.misc.Subject;

/**
 * <p>Person<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Class to hold and expose information about people</p>
 * 
 * @author Genevieve Turner
 *
 */
@XmlRootElement(name="person")
public class Person {
	public static final String UID = "uid";
	public static final String GIVEN_NAME = "given-name";
	public static final String SURNAME = "surname";
	public static final String DISPLAY_NAME = "display-name";
	public static final String ARIES_ID = "aries-id";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";
	public static final String FAX = "fax";
	public static final String JOB_TITLE = "job-title";
	public static final String PREFERRED_NAME = "preferred-name";
	public static final String STAFF_TYPE = "staff-type";
	public static final String ORGANISATIONAL_UNIT = "organisational-unit";
	public static final String NLA_ID = "nla-id";
	public static final String COUNTRY = "country";
	public static final String INSTITUTION = "institution";
	public static final String DESCRIPTION = "description";
	public static final String FOR_SUBJECT = "for-subject";
	
	private String extId_;
	private String uid_;
	private String givenName_;
	private String surname_;
	private String displayName_;
	private String ariesId_;
	private String email_;
	private List<String> phoneNumbers_ = new ArrayList<String>();
	private List<String> faxNumbers_ = new ArrayList<String>();
	private String jobTitle_;
	private String preferredName_;
	private String staffType_;
	private String organisationalUnit_;
	private String nlaId_;
	private String country_;
	private String institution_;
	private String description_;
	private List<Subject> anzforSubjects_ = new ArrayList<Subject>();
	
	/**
	 * Constructor
	 */
	public Person() {
	}

	/**
	 * Get the persons external id
	 * 
	 * @return  The external id
	 */
	@XmlTransient
	public String getExtId() {
		return extId_;
	}
	
	/**
	 * Set the persons external id
	 * 
	 * @param extId The external id
	 */
	public void setExtId(String extId) {
		this.extId_ = extId;
	}

	/**
	 * Get the persons university id
	 * 
	 * @return The university id
	 */
	@XmlElement(name=UID)
	public String getUid() {
		return uid_;
	}
	
	/**
	 * Set the persons university id
	 * 
	 * @param uid The university id
	 */
	public void setUid(String uid) {
		this.uid_ = uid;
	}
	
	/**
	 * Get the persons given name
	 * 
	 * @return The given name
	 */
	@XmlElement(name=GIVEN_NAME)
	public String getGivenName() {
		return givenName_;
	}
	
	/**
	 * Set the persons given name
	 * 
	 * @param givenName The given name
	 */
	public void setGivenName(String givenName) {
		this.givenName_ = givenName;
	}

	/**
	 * Get the persons surname
	 * 
	 * @return The surname
	 */
	@XmlElement(name=SURNAME)
	public String getSurname() {
		return surname_;
	}
	
	/**
	 * Set the persons surname
	 * 
	 * @param surname The surname
	 */
	public void setSurname(String surname) {
		this.surname_ = surname;
	}
	
	/**
	 * Get the persons display name
	 * 
	 * @return The display name
	 */
	@XmlElement(name=DISPLAY_NAME)
	public String getDisplayName() {
		return displayName_;
	}
	
	/**
	 * Set the persons display name
	 * 
	 * @param displayName The display name
	 */
	public void setDisplayName(String displayName) {
		this.displayName_ = displayName;
	}
	
	/**
	 * Get the persons aries id
	 * 
	 * @return The aries id
	 */
	@XmlElement(name=ARIES_ID)
	public String getAriesId() {
		return ariesId_;
	}
	
	/**
	 * Set the persons aries id
	 * 
	 * @param ariesId  The aries id
	 */
	public void setAriesId(String ariesId) {
		this.ariesId_ = ariesId;
	}

	/**
	 * Get the persons email address
	 * 
	 * @return The email address
	 */
	@XmlElement(name=EMAIL)
	public String getEmail() {
		return email_;
	}
	
	/**
	 * Set the persons email address
	 * 
	 * @param email The email address
	 */
	public void setEmail(String email) {
		this.email_ = email;
	}
	
	/**
	 * Get the persons phone numbers
	 * 
	 * @return The phone numbers
	 */
	@XmlElement(name=PHONE)
	public List<String> getPhoneNumbers() {
		return phoneNumbers_;
	}
	
	/**
	 * Set the persons phone numbers
	 * 
	 * @param phoneNumbers The phone numbers
	 */
	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers_ = phoneNumbers;
	}
	
	/**
	 * Get the persons fax numbers
	 * 
	 * @return The fax numbers
	 */
	@XmlElement(name=FAX)
	public List<String> getFaxNumbers() {
		return faxNumbers_;
	}
	
	/**
	 * Set the persons fax numbers
	 * 
	 * @param faxNumbers The fax numbers
	 */
	public void setFaxNumbers(List<String> faxNumbers) {
		this.faxNumbers_ = faxNumbers;
	}
	
	/**
	 * Get the persons job title
	 * 
	 * @return The job title
	 */
	@XmlElement(name=JOB_TITLE)
	public String getJobTitle() {
		return jobTitle_;
	}
	
	/**
	 * Set the persons job title
	 * 
	 * @param jobTitle The job title
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle_ = jobTitle;
	}
	
	/**
	 * Get the persons preferred name
	 * 
	 * @return The preferred name
	 */
	@XmlElement(name=PREFERRED_NAME)
	public String getPreferredName() {
		return preferredName_;
	}
	
	/**
	 * Set the persons preferred name
	 * 
	 * @param preferredName The preferred name
	 */
	public void setPreferredName(String preferredName) {
		this.preferredName_ = preferredName;
	}
	
	/**
	 * Get the persons staff type
	 * 
	 * @return The staff type
	 */
	@XmlElement(name=STAFF_TYPE)
	public String getStaffType() {
		return staffType_;
	}
	
	/**
	 * Set the persons staff type
	 * 
	 * @param staffType The staff type
	 */
	public void setStaffType(String staffType) {
		this.staffType_ = staffType;
	}
	
	/**
	 * Get the persons organisational unit
	 * 
	 * @return The organisational unit
	 */
	@XmlElement(name=ORGANISATIONAL_UNIT)
	public String getOrganisationalUnit() {
		return organisationalUnit_;
	}
	
	/**
	 * Set the persons organisational unit
	 * 
	 * @param organisationalUnit The organisational unit
	 */
	public void setOrganisationalUnit(String organisationalUnit) {
		this.organisationalUnit_ = organisationalUnit;
	}
	
	/**
	 * Get the persons national library identifier
	 * 
	 * @return The national library identifier
	 */
	@XmlElement(name=NLA_ID)
	public String getNlaId() {
		return nlaId_;
	}

	/**
	 * Set the persons national library identifier
	 * 
	 * @param nlaId The national library identifier
	 */
	public void setNlaId(String nlaId) {
		this.nlaId_ = nlaId;
	}

	/**
	 * Get the persons country
	 * 
	 * @return The country
	 */
	@XmlElement(name=COUNTRY)
	public String getCountry() {
		return country_;
	}
	
	/**
	 * Set the persons country
	 * 
	 * @param country The country
	 */
	public void setCountry(String country) {
		this.country_ = country;
	}
	
	/**
	 * Get the persons institution
	 * 
	 * @return The institution
	 */
	@XmlElement(name=INSTITUTION)
	public String getInstitution() {
		return institution_;
	}

	/**
	 * Set the persons institution
	 * 
	 * @param institution The institution
	 */
	public void setInstitution(String institution) {
		this.institution_ = institution;
	}

	/**
	 * Get the persons description
	 * 
	 * @return The description
	 */
	@XmlElement(name=DESCRIPTION)
	public String getDescription() {
		return description_;
	}

	/**
	 * Set the persons description
	 * 
	 * @param description The description
	 */
	public void setDescription(String description) {
		this.description_ = description;
	}

	/**
	 * Get the persons fields of research
	 * 
	 * @return The fields of research
	 */
	@XmlElement(name=FOR_SUBJECT)
	public List<Subject> getAnzforSubjects() {
		return anzforSubjects_;
	}

	/**
	 * Set the persons field of research
	 * 
	 * @param anzforSubjects The fields of research
	 */
	public void setAnzforSubjects(List<Subject> anzforSubjects) {
		this.anzforSubjects_ = anzforSubjects;
	}
}
