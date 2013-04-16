package au.edu.anu.metadatastores.store.people;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import au.edu.anu.metadatastores.store.misc.Subject;

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
	
	public Person() {
	}

	@XmlTransient
	public String getExtId() {
		return extId_;
	}
	
	public void setExtId(String extId) {
		this.extId_ = extId;
	}

	@XmlElement(name=UID)
	public String getUid() {
		return uid_;
	}
	
	public void setUid(String uid) {
		this.uid_ = uid;
	}
	
	@XmlElement(name=GIVEN_NAME)
	public String getGivenName() {
		return givenName_;
	}
	
	public void setGivenName(String givenName) {
		this.givenName_ = givenName;
	}

	@XmlElement(name=SURNAME)
	public String getSurname() {
		return surname_;
	}
	
	public void setSurname(String surname) {
		this.surname_ = surname;
	}
	
	@XmlElement(name=DISPLAY_NAME)
	public String getDisplayName() {
		return displayName_;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName_ = displayName;
	}
	
	@XmlElement(name=ARIES_ID)
	public String getAriesId() {
		return ariesId_;
	}
	
	public void setAriesId(String ariesId) {
		this.ariesId_ = ariesId;
	}

	@XmlElement(name=EMAIL)
	public String getEmail() {
		return email_;
	}
	
	public void setEmail(String email) {
		this.email_ = email;
	}
	
	@XmlElement(name=PHONE)
	public List<String> getPhoneNumbers() {
		return phoneNumbers_;
	}
	
	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers_ = phoneNumbers;
	}
	
	@XmlElement(name=FAX)
	public List<String> getFaxNumbers() {
		return faxNumbers_;
	}
	
	public void setFaxNumbers(List<String> faxNumbers) {
		this.faxNumbers_ = faxNumbers;
	}
	
	@XmlElement(name=JOB_TITLE)
	public String getJobTitle() {
		return jobTitle_;
	}
	
	public void setJobTitle(String jobTitle) {
		this.jobTitle_ = jobTitle;
	}
	
	@XmlElement(name=PREFERRED_NAME)
	public String getPreferredName() {
		return preferredName_;
	}
	
	public void setPreferredName(String preferredName) {
		this.preferredName_ = preferredName;
	}
	
	@XmlElement(name=STAFF_TYPE)
	public String getStaffType() {
		return staffType_;
	}
	
	public void setStaffType(String staffType) {
		this.staffType_ = staffType;
	}
	
	@XmlElement(name=ORGANISATIONAL_UNIT)
	public String getOrganisationalUnit() {
		return organisationalUnit_;
	}
	
	public void setOrganisationalUnit(String organisationalUnit) {
		this.organisationalUnit_ = organisationalUnit;
	}
	
	@XmlElement(name=NLA_ID)
	public String getNlaId() {
		return nlaId_;
	}

	public void setNlaId(String nlaId) {
		this.nlaId_ = nlaId;
	}

	@XmlElement(name=COUNTRY)
	public String getCountry() {
		return country_;
	}
	
	public void setCountry(String country) {
		this.country_ = country;
	}
	
	@XmlElement(name=INSTITUTION)
	public String getInstitution() {
		return institution_;
	}

	public void setInstitution(String institution) {
		this.institution_ = institution;
	}

	@XmlElement(name=DESCRIPTION)
	public String getDescription() {
		return description_;
	}

	public void setDescription(String description) {
		this.description_ = description;
	}

	@XmlElement(name=FOR_SUBJECT)
	public List<Subject> getAnzforSubjects() {
		return anzforSubjects_;
	}

	public void setAnzforSubjects(List<Subject> anzforSubjects) {
		this.anzforSubjects_ = anzforSubjects;
	}
}
