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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.annotations.ItemAttributeTrait;
import au.edu.anu.metadatastores.datamodel.store.annotations.ItemTrait;
import au.edu.anu.metadatastores.datamodel.store.annotations.TraitType;
import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;
import au.edu.anu.metadatastores.rdf.annotation.RDFDefaultTriple;
import au.edu.anu.metadatastores.rdf.annotation.RDFSet;
import au.edu.anu.metadatastores.rdf.annotation.RDFSetWithDefault;
import au.edu.anu.metadatastores.rdf.annotation.RDFSets;
import au.edu.anu.metadatastores.rdf.annotation.RDFSubject;
import au.edu.anu.metadatastores.rdf.annotation.RDFType;
import au.edu.anu.metadatastores.rdf.annotation.RDFUri;
import au.edu.anu.metadatastores.rdf.namespace.RDFNS;
import au.edu.anu.metadatastores.rdf.namespace.StoreNS;
import au.edu.anu.metadatastores.rdf.namespace.VCardNS;
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
@RDFSets(
		sets={
				@RDFSet(uri=VCardNS.N,fields={"givenName", "surname"}),
				@RDFSet(uri=VCardNS.ORG, fields={"institution"}),
				@RDFSet(uri=VCardNS.ADR, fields={"country"})
		},
		setsWithDefaults={
				@RDFSetWithDefault(uri=VCardNS.TEL,field="phoneNumbers",defaults={
						@RDFDefaultTriple(predicate=RDFNS.TYPE,object=VCardNS.uri + "voice")
						,@RDFDefaultTriple(predicate=RDFNS.TYPE,object=VCardNS.uri + "work")}),
				@RDFSetWithDefault(uri=VCardNS.TEL,field="faxNumbers",defaults={
						@RDFDefaultTriple(predicate=RDFNS.TYPE,object=VCardNS.uri + "fax")
						,@RDFDefaultTriple(predicate=RDFNS.TYPE,object=VCardNS.uri + "work")}),
				@RDFSetWithDefault(uri=VCardNS.EMAIL,field="email",defaults={
						@RDFDefaultTriple(predicate=RDFNS.TYPE, object=VCardNS.uri + "internet")}),
				@RDFSetWithDefault(uri=VCardNS.ORG,field="organisationalUnit", defaults={
						@RDFDefaultTriple(predicate=VCardNS.Orgname, object="The Australian National University")
				})
		}
)
@RDFType("PERSON")
@ItemTrait(extId="extId")
public class Person {
	static final Logger LOGGER = LoggerFactory.getLogger(Person.class);

	public static final String HASHED_EXT_ID = "external-identifier";
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
	public static final String ORCID = "orcid";
	public static final String IS_ACTIVIE = "is-active";
	
	private String extId_;
	private String hashedExtId_;
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
	private String orcid_;
	private String isActive_;
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
	
	@XmlElement(name=HASHED_EXT_ID)
	public String getHashedExtId() {
		if (hashedExtId_ == null && extId_ != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("SHA1");
				md.update(extId_.getBytes("UTF-8"));
				byte[] bytes = md.digest();
				StringBuilder sb = new StringBuilder();
				for (byte b : bytes) {
					sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
				}
				hashedExtId_ = sb.toString();
			}
			catch (NoSuchAlgorithmException e) {
				LOGGER.error("Error hashing extId", e);
			}
			catch (UnsupportedEncodingException e) {
				LOGGER.error("Error hashing extId", e);
			}
		}
		return hashedExtId_;
	}
	
	@RDFUri(uri=VCardNS.FN)
	@RDFSubject
	public String getFullName() {
		String fullName = getGivenName() + " " + getSurname();
		return fullName.trim();
	}

	/**
	 * Get the persons university id
	 * 
	 * @return The university id
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.UNIVERSITY_ID, traitType=TraitType.STRING, level=1)
	@XmlElement(name=UID)
	@RDFUri(uri=VCardNS.UID)
	@RDFSubject
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
	@ItemAttributeTrait(attrType=StoreAttributes.GIVEN_NAME, traitType=TraitType.STRING, level=4)
	@XmlElement(name=GIVEN_NAME)
	@RDFUri(uri=VCardNS.Given)
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
	@ItemAttributeTrait(attrType=StoreAttributes.SURNAME, traitType=TraitType.STRING, level=4)
	@XmlElement(name=SURNAME)
	@RDFUri(uri=VCardNS.Family)
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
	@ItemAttributeTrait(attrType=StoreAttributes.DISPLAY_NAME, traitType=TraitType.STRING, level=3)
	@XmlElement(name=DISPLAY_NAME)
	@RDFUri(uri=VCardNS.NICKNAME)
	@RDFSubject
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
	@ItemAttributeTrait(attrType=StoreAttributes.ARIES_ID, traitType=TraitType.STRING, level=2)
	@XmlElement(name=ARIES_ID)
	//TODO
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
	@ItemAttributeTrait(attrType=StoreAttributes.EMAIL, traitType=TraitType.STRING, level=2)
	@XmlElement(name=EMAIL)
	@RDFUri(uri=RDFNS.VALUE)
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
	@ItemAttributeTrait(attrType=StoreAttributes.PHONE, traitType=TraitType.STRING_LIST, level=2)
	@XmlElement(name=PHONE)
	@RDFUri(uri=RDFNS.VALUE)
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
	@ItemAttributeTrait(attrType=StoreAttributes.FAX, traitType=TraitType.STRING_LIST, level=2)
	@XmlElement(name=FAX)
	@RDFUri(uri=RDFNS.VALUE)
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
	@ItemAttributeTrait(attrType=StoreAttributes.JOB_TITLE, traitType=TraitType.STRING, level=2)
	@XmlElement(name=JOB_TITLE)
	@RDFUri(uri=VCardNS.TITLE)
	@RDFSubject
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
	@ItemAttributeTrait(attrType=StoreAttributes.PREFERRED_NAME, traitType=TraitType.STRING, level=2)
	@XmlElement(name=PREFERRED_NAME)
	//TODO
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
	@ItemAttributeTrait(attrType=StoreAttributes.STAFF_TYPE, traitType=TraitType.STRING, level=3)
	@XmlElement(name=STAFF_TYPE)
	//TODO
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
	@ItemAttributeTrait(attrType=StoreAttributes.ORGANISATIONAL_UNIT, traitType=TraitType.STRING, level=3)
	@XmlElement(name=ORGANISATIONAL_UNIT)
	@RDFUri(uri=VCardNS.Orgunit)
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
	@ItemAttributeTrait(attrType=StoreAttributes.NLA_ID, traitType=TraitType.STRING, level=2)
	@XmlElement(name=NLA_ID)
	//TODO
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
	@ItemAttributeTrait(attrType=StoreAttributes.COUNTRY, traitType=TraitType.STRING, level=3)
	@XmlElement(name=COUNTRY)
	@RDFUri(uri=VCardNS.Country)
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
	@ItemAttributeTrait(attrType=StoreAttributes.INSTITUTION, traitType=TraitType.STRING, level=3)
	@XmlElement(name=INSTITUTION)
	@RDFUri(uri=VCardNS.Orgname)
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
	@ItemAttributeTrait(attrType=StoreAttributes.DESCRIPTION, traitType=TraitType.STRING, level=2)
	@XmlElement(name=DESCRIPTION)
	//TODO
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
	 * Get the persons orcid
	 * 
	 * @return The orcid
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.ORCID, traitType=TraitType.STRING, level=2)
	@XmlElement(name=ORCID)
	public String getOrcid() {
		return orcid_;
	}

	/**
	 * Set the persons orcid
	 * 
	 * @param orcid The orcid
	 */
	public void setOrcid(String orcid) {
		this.orcid_ = orcid;
	}

	/**
	 * Get whether the person is active (i.e. whether they are in LDAP)
	 * @return Whether they are active
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.ACTIVE, traitType=TraitType.STRING, level=2)
	@XmlTransient
	public String getIsActive() {
		return isActive_;
	}

	/**
	 * Set whether the person is active (i.e. whether they are in LDAP)
	 * 
	 * @param isActive Whether they are active
	 */
	public void setIsActive(String isActive) {
		this.isActive_ = isActive;
	}

	/**
	 * Get the persons fields of research
	 * 
	 * @return The fields of research
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.FOR_SUBJECT, traitType=TraitType.SUBJECT_LIST, level=2)
	@XmlElement(name=FOR_SUBJECT)
	@RDFUri(uri=StoreNS.SUBJECT)
	@RDFSubject
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
