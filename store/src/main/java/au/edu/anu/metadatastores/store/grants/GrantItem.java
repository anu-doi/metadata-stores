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

package au.edu.anu.metadatastores.store.grants;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Filter;

import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.ItemAttribute;
import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;

/**
 * GrantItem
 * 
 * The Australian National University
 * 
 * Item entity for Grants
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue(value="GRANT")
public class GrantItem extends Item {
	private static final long serialVersionUID = 1L;
	
	private List<ItemAttribute> contractCodes = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> grantTitles = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> startDates = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> endDates = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> status = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> firstInvestigatorIds = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> fundsProviders = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> referenceNumbers = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> anzforSubjects = new ArrayList<ItemAttribute>() ;
	
	/**
	 * Get the contract codes
	 * 
	 * @return The contract codes
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.CONTRACT_CODE + "'")
	public List<ItemAttribute> getContractCodes() {
		return contractCodes;
	}
	
	/**
	 * Set the contract codes
	 * 
	 * @param contractCodes The contract codes
	 */
	public void setContractCodes(List<ItemAttribute> contractCodes) {
		this.contractCodes = contractCodes;
	}

	/**
	 * Get the grant titles
	 * 
	 * @return The grant titles
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.TITLE + "'")
	public List<ItemAttribute> getGrantTitles() {
		return grantTitles;
	}
	
	/**
	 * Set the grant titles
	 * 
	 * @param grantTitles The grant titles
	 */
	public void setGrantTitles(List<ItemAttribute> grantTitles) {
		this.grantTitles = grantTitles;
	}

	/**
	 * Get the grant start dates
	 * 
	 * @return The grant start dates
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.START_DATE + "'")
	public List<ItemAttribute> getStartDates() {
		return startDates;
	}
	
	/**
	 * Set the grant start dates
	 * 
	 * @param startDates The grant start dates
	 */
	public void setStartDates(List<ItemAttribute> startDates) {
		this.startDates = startDates;
	}

	/**
	 * Get the grant end dates
	 * 
	 * @return The grant end dates
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.END_DATE + "'")
	public List<ItemAttribute> getEndDates() {
		return endDates;
	}
	
	/**
	 * Set the grant end dates
	 * 
	 * @param endDates The grant end dates
	 */
	public void setEndDates(List<ItemAttribute> endDates) {
		this.endDates = endDates;
	}
	
	/**
	 * Get the grant status
	 * 
	 * @return The status
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.STATUS + "'")
	public List<ItemAttribute> getStatus() {
		return status;
	}

	/**
	 * Set the grant status
	 * 
	 * @param status The status
	 */
	public void setStatus(List<ItemAttribute> status) {
		this.status = status;
	}

	/**
	 * Get the first investigator id
	 * 
	 * @return The first investigator id
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.FIRST_INVESTIGATOR_ID + "'")
	public List<ItemAttribute> getFirstInvestigatorIds() {
		return firstInvestigatorIds;
	}
	
	/**
	 * Set the first investigator id
	 * 
	 * @param firstInvestigatorIds The first investigator id
	 */
	public void setFirstInvestigatorIds(List<ItemAttribute> firstInvestigatorIds) {
		this.firstInvestigatorIds = firstInvestigatorIds;
	}

	/**
	 * Get the funds providers
	 * 
	 * @return The funds providers
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.FUNDS_PROVIDER + "'")
	public List<ItemAttribute> getFundsProviders() {
		return fundsProviders;
	}

	/**
	 * Set the funds providers
	 * 
	 * @param fundsProviders The funds providers
	 */
	public void setFundsProviders(List<ItemAttribute> fundsProviders) {
		this.fundsProviders = fundsProviders;
	}

	/**
	 * Get the funds provider reference number
	 * 
	 * @return The reference number
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.REFERENCE_NUMBER + "'")
	public List<ItemAttribute> getReferenceNumbers() {
		return referenceNumbers;
	}

	/**
	 * Set the funds provider reference number
	 * 
	 * @param referenceNumbers The reference number
	 */
	public void setReferenceNumbers(List<ItemAttribute> referenceNumbers) {
		this.referenceNumbers = referenceNumbers;
	}

	/**
	 * Get the fields of research subjects
	 * 
	 * @return The fields of research subjects
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.FOR_SUBJECT + "'")
	public List<ItemAttribute> getAnzforSubjects() {
		return anzforSubjects;
	}

	/**
	 * Set the fields of research subjects
	 * 
	 *  @param anzforSubjects The fields of research subjects
	 */
	public void setAnzforSubjects(List<ItemAttribute> anzforSubjects) {
		this.anzforSubjects = anzforSubjects;
	}
}
