package au.edu.anu.metadatastores.store.people;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.Filter;

import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.ItemAttribute;
import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;

@Entity
@DiscriminatorValue(value="PERSON")
@FetchProfile(name = "person-with-attributes", fetchOverrides = {
	@FetchProfile.FetchOverride(entity = PersonItem.class, association = "itemAttributes", mode = FetchMode.JOIN)	
})
public class PersonItem extends Item {
	private static final long serialVersionUID = 1L;
	
	private List<ItemAttribute> uids = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> givenNames = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> surnames = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> displayNames = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> ariesIds = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> emailAddresses = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> phoneNumbers = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> faxNumbers = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> jobTitles = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> preferredNames = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> staffType = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> organisationalUnits = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> nlaIds = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> commonNames = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> anzforSubjects = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> countries = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> institutions = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> descriptions = new ArrayList<ItemAttribute>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.UNIVERSITY_ID + "'")
	public List<ItemAttribute> getUids() {
		return uids;
	}

	public void setUids(List<ItemAttribute> uids) {
		this.uids = uids;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.GIVEN_NAME + "'")
	public List<ItemAttribute> getGivenNames() {
		return givenNames;
	}
	
	public void setGivenNames(List<ItemAttribute> givenNames) {
		this.givenNames = givenNames;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.SURNAME + "'")
	public List<ItemAttribute> getSurnames() {
		return surnames;
	}
	
	public void setSurnames(List<ItemAttribute> surnames) {
		this.surnames = surnames;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.DISPLAY_NAME + "'")
	public List<ItemAttribute> getDisplayNames() {
		return displayNames;
	}

	public void setDisplayNames(List<ItemAttribute> displayNames) {
		this.displayNames = displayNames;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.ARIES_ID + "'")
	public List<ItemAttribute> getAriesIds() {
		return ariesIds;
	}

	public void setAriesIds(List<ItemAttribute> ariesIds) {
		this.ariesIds = ariesIds;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.EMAIL + "'")
	public List<ItemAttribute> getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(List<ItemAttribute> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.PHONE + "'")
	public List<ItemAttribute> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<ItemAttribute> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.FAX + "'")
	public List<ItemAttribute> getFaxNumbers() {
		return faxNumbers;
	}

	public void setFaxNumbers(List<ItemAttribute> faxNumbers) {
		this.faxNumbers = faxNumbers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.JOB_TITLE + "'")
	public List<ItemAttribute> getJobTitles() {
		return jobTitles;
	}

	public void setJobTitles(List<ItemAttribute> jobTitles) {
		this.jobTitles = jobTitles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.PREFERRED_NAME + "'")
	public List<ItemAttribute> getPreferredNames() {
		return preferredNames;
	}

	public void setPreferredNames(List<ItemAttribute> preferredNames) {
		this.preferredNames = preferredNames;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.STAFF_TYPE + "'")
	public List<ItemAttribute> getStaffType() {
		return staffType;
	}

	public void setStaffType(List<ItemAttribute> staffType) {
		this.staffType = staffType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.ORGANISATIONAL_UNIT + "'")
	public List<ItemAttribute> getOrganisationalUnits() {
		return organisationalUnits;
	}

	public void setOrganisationalUnits(List<ItemAttribute> organisationalUnits) {
		this.organisationalUnits = organisationalUnits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.NLA_ID + "'")
	public List<ItemAttribute> getNlaIds() {
		return nlaIds;
	}

	public void setNlaIds(List<ItemAttribute> nlaIds) {
		this.nlaIds = nlaIds;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.COMMON_NAME + "'")
	public List<ItemAttribute> getCommonNames() {
		return commonNames;
	}

	public void setCommonNames(List<ItemAttribute> commonNames) {
		this.commonNames = commonNames;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.FOR_SUBJECT + "'")
	public List<ItemAttribute> getAnzforSubjects() {
		return anzforSubjects;
	}

	public void setAnzforSubjects(List<ItemAttribute> anzforSubjects) {
		this.anzforSubjects = anzforSubjects;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.COUNTRY + "'")
	public List<ItemAttribute> getCountries() {
		return countries;
	}

	public void setCountries(List<ItemAttribute> countries) {
		this.countries = countries;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.INSTITUTION + "'")
	public List<ItemAttribute> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(List<ItemAttribute> institutions) {
		this.institutions = institutions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.DESCRIPTION + "'")
	public List<ItemAttribute> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<ItemAttribute> descriptions) {
		this.descriptions = descriptions;
	}
	
}
