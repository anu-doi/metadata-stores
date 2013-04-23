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

/**
 * PersonItem
 * 
 * The Australian National University
 * 
 * The item for people
 * 
 * @author Genevieve Turner
 *
 */
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
	
	/**
	 * Get the university ids
	 * 
	 * @return The university id
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.UNIVERSITY_ID + "'")
	public List<ItemAttribute> getUids() {
		return uids;
	}

	/**
	 * Set the university ids
	 * 
	 * @param uids The university id
	 */
	public void setUids(List<ItemAttribute> uids) {
		this.uids = uids;
	}

	/**
	 * Get the given names
	 * 
	 * @return The given names
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.GIVEN_NAME + "'")
	public List<ItemAttribute> getGivenNames() {
		return givenNames;
	}
	
	/**
	 * Set the given names
	 * 
	 * @param givenNames The given names
	 */
	public void setGivenNames(List<ItemAttribute> givenNames) {
		this.givenNames = givenNames;
	}
	
	/**
	 * Get the surnames
	 * 
	 * @return The surnames
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.SURNAME + "'")
	public List<ItemAttribute> getSurnames() {
		return surnames;
	}
	
	/**
	 * Set the surnames
	 * 
	 * @param surnames The surnames
	 */
	public void setSurnames(List<ItemAttribute> surnames) {
		this.surnames = surnames;
	}

	/**
	 * Get the display names
	 * 
	 * @return The display names
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.DISPLAY_NAME + "'")
	public List<ItemAttribute> getDisplayNames() {
		return displayNames;
	}

	/**
	 * Set the display names
	 * 
	 * @param displayNames The display names
	 */
	public void setDisplayNames(List<ItemAttribute> displayNames) {
		this.displayNames = displayNames;
	}

	/**
	 * Get the aries ids
	 * 
	 * @return The aries ids
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.ARIES_ID + "'")
	public List<ItemAttribute> getAriesIds() {
		return ariesIds;
	}

	/**
	 * Set the aries ids
	 * 
	 * @param ariesIds The aries ids
	 */
	public void setAriesIds(List<ItemAttribute> ariesIds) {
		this.ariesIds = ariesIds;
	}

	/**
	 * Get the email addresses
	 * 
	 * @return The email addresses
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.EMAIL + "'")
	public List<ItemAttribute> getEmailAddresses() {
		return emailAddresses;
	}

	/**
	 * Set the email addresses
	 * 
	 * @param emailAddresses The email addresses
	 */
	public void setEmailAddresses(List<ItemAttribute> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	/**
	 * Get the phone numbers
	 * 
	 * @return The phone numbers
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.PHONE + "'")
	public List<ItemAttribute> getPhoneNumbers() {
		return phoneNumbers;
	}

	/**
	 * Set the phone numbers
	 * 
	 * @param phoneNumbers The phone numbers
	 */
	public void setPhoneNumbers(List<ItemAttribute> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	/**
	 * Get the fax numbers
	 * 
	 * @return The fax numbers
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.FAX + "'")
	public List<ItemAttribute> getFaxNumbers() {
		return faxNumbers;
	}

	/**
	 * Set the fax numbers
	 * 
	 * @param faxNumbers The fax numbers
	 */
	public void setFaxNumbers(List<ItemAttribute> faxNumbers) {
		this.faxNumbers = faxNumbers;
	}

	/**
	 * Get the job titles
	 * 
	 * @return The job titles
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.JOB_TITLE + "'")
	public List<ItemAttribute> getJobTitles() {
		return jobTitles;
	}

	/**
	 * Set the job titles
	 * 
	 * @param jobTitles The job titles
	 */
	public void setJobTitles(List<ItemAttribute> jobTitles) {
		this.jobTitles = jobTitles;
	}

	/**
	 * Get the preferred names
	 * 
	 * @return The preferred names
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.PREFERRED_NAME + "'")
	public List<ItemAttribute> getPreferredNames() {
		return preferredNames;
	}

	/**
	 * Set the preferred name
	 * 
	 * @param preferredNames The preferred names
	 */
	public void setPreferredNames(List<ItemAttribute> preferredNames) {
		this.preferredNames = preferredNames;
	}

	/**
	 * Get the staff type
	 * 
	 * @return The staff type
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.STAFF_TYPE + "'")
	public List<ItemAttribute> getStaffType() {
		return staffType;
	}

	/**
	 * Set the staff type
	 * 
	 * @param staffType The staff type
	 */
	public void setStaffType(List<ItemAttribute> staffType) {
		this.staffType = staffType;
	}

	/**
	 * Get the organisational units
	 * 
	 * @return The organisational units
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.ORGANISATIONAL_UNIT + "'")
	public List<ItemAttribute> getOrganisationalUnits() {
		return organisationalUnits;
	}

	/**
	 * Set the organisational units
	 * 
	 * @param organisationalUnits The organisational units
	 */
	public void setOrganisationalUnits(List<ItemAttribute> organisationalUnits) {
		this.organisationalUnits = organisationalUnits;
	}

	/**
	 * Get the national library identifiers
	 * 
	 * @return The national library identifiers
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.NLA_ID + "'")
	public List<ItemAttribute> getNlaIds() {
		return nlaIds;
	}

	/**
	 * Set the national library identifiers
	 * 
	 * @param nlaIds The national library identifiers
	 */
	public void setNlaIds(List<ItemAttribute> nlaIds) {
		this.nlaIds = nlaIds;
	}

	/**
	 * Get the common names
	 * 
	 * @return The common names
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.COMMON_NAME + "'")
	public List<ItemAttribute> getCommonNames() {
		return commonNames;
	}

	/**
	 * Set the common names
	 * 
	 * @param commonNames The common names
	 */
	public void setCommonNames(List<ItemAttribute> commonNames) {
		this.commonNames = commonNames;
	}

	/**
	 * Get the fields of research
	 * 
	 * @return The fields of research
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.FOR_SUBJECT + "'")
	public List<ItemAttribute> getAnzforSubjects() {
		return anzforSubjects;
	}

	/**
	 * Set the fields of research
	 * 
	 * @param anzforSubjects The fields of research
	 */
	public void setAnzforSubjects(List<ItemAttribute> anzforSubjects) {
		this.anzforSubjects = anzforSubjects;
	}

	/**
	 * Get the countries
	 * 
	 * @return The countries
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.COUNTRY + "'")
	public List<ItemAttribute> getCountries() {
		return countries;
	}

	/**
	 * Set the countries
	 * 
	 * @param countries The countries
	 */
	public void setCountries(List<ItemAttribute> countries) {
		this.countries = countries;
	}

	/**
	 * Set the institution
	 * 
	 * @return The institution
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.INSTITUTION + "'")
	public List<ItemAttribute> getInstitutions() {
		return institutions;
	}

	/**
	 * Set the institutions
	 * 
	 * @param institutions The institutions
	 */
	public void setInstitutions(List<ItemAttribute> institutions) {
		this.institutions = institutions;
	}

	/**
	 * Get the descriptions
	 * 
	 * @return The descipritons
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.DESCRIPTION + "'")
	public List<ItemAttribute> getDescriptions() {
		return descriptions;
	}

	/**
	 * Set the descriptions
	 * 
	 * @param descriptions The descriptions
	 */
	public void setDescriptions(List<ItemAttribute> descriptions) {
		this.descriptions = descriptions;
	}
	
}
