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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import au.edu.anu.metadatastores.datamodel.store.annotations.ItemAttributeTrait;
import au.edu.anu.metadatastores.datamodel.store.annotations.ItemTrait;
import au.edu.anu.metadatastores.datamodel.store.annotations.TraitType;
import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;
import au.edu.anu.metadatastores.rdf.annotation.RDFSubject;
import au.edu.anu.metadatastores.rdf.annotation.RDFType;
import au.edu.anu.metadatastores.rdf.annotation.RDFUri;
import au.edu.anu.metadatastores.rdf.namespace.StoreNS;
import au.edu.anu.metadatastores.store.misc.Subject;
import au.edu.anu.metadatastores.store.people.Person;

/**
 * <p>Grant<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Class to hold and expose grant information</p>
 * 
 * @author Genevieve Turner
 *
 */
@ItemTrait(extId="contractCode", title="title")
@XmlRootElement(name="grant")
@RDFType("GRANT")
public class Grant {
	private String contractCode;
	private String title;
	private Person firstInvestigator;
	private List<Person> associatedPeople = new ArrayList<Person>();
	private String startDate;
	private String endDate;
	private String status;
	private String fundsProvider;
	private String referenceNumber;
	private String description;
	private List<Subject> anzforSubjects = new ArrayList<Subject>();
	
	/**
	 * Get the contract code
	 * 
	 * @return The contract code
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.CONTRACT_CODE, traitType=TraitType.STRING)
	@XmlElement(name="contract-code")
	@RDFUri(uri=StoreNS.CONTRACT_CODE)
	@RDFSubject
	public String getContractCode() {
		return contractCode;
	}
	
	/**
	 * Set the contract code
	 * 
	 * @param contractCode The contract code
	 */
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	
	/**
	 * Get the title
	 * 
	 * @return The title
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.TITLE, traitType=TraitType.STRING)
	@XmlElement(name="title")
	@RDFUri(uri=StoreNS.TITLE)
	@RDFSubject
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title
	 * 
	 * @param title The title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Get the first investigator
	 * 
	 * @return The first investigator
	 */
	//@ItemAttributeTrait(attrType=StoreAttributes.F)
	//TODO figure out how to place the uid of the person for this field
	@XmlElement(name="first-investigator")
	//TODO
	public Person getFirstInvestigator() {
		return firstInvestigator;
	}

	/**
	 * Set the first investigator
	 * 
	 * @param firstInvestigator The first investigator
	 */
	public void setFirstInvestigator(Person firstInvestigator) {
		this.firstInvestigator = firstInvestigator;
	}

	/**
	 * Get the people associated with the grant
	 * 
	 * @return The people associated with the grant
	 */
	@XmlElement(name="associate")
	//TODO
	public List<Person> getAssociatedPeople() {
		return associatedPeople;
	}
	
	/**
	 * Set the people associated with the grant
	 * 
	 * @param associatedPeople The people associated with the grant
	 */
	public void setAssociatedPeople(List<Person> associatedPeople) {
		this.associatedPeople = associatedPeople;
	}
	
	/**
	 * Get the grant start date
	 * 
	 * @return THe start date
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.START_DATE, traitType=TraitType.STRING)
	@XmlElement(name="start-date")
	@RDFUri(uri=StoreNS.START_DATE)
	@RDFSubject
	public String getStartDate() {
		return startDate;
	}
	
	/**
	 * Set the grant start date
	 * 
	 * @param startDate The start date
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Get the grant end date
	 * 
	 * @return The end date
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.END_DATE, traitType=TraitType.STRING)
	@XmlElement(name="end-date")
	@RDFUri(uri=StoreNS.END_DATE)
	@RDFSubject
	public String getEndDate() {
		return endDate;
	}
	
	/**
	 * SEt the grant end date
	 * 
	 * @param endDate The end date
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * Get the grant status
	 * 
	 * @return The status
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.STATUS, traitType=TraitType.STRING)
	@XmlElement(name="status")
	@RDFUri(uri=StoreNS.STATUS)
	@RDFSubject
	public String getStatus() {
		return status;
	}

	/**
	 * Set the grant status
	 * 
	 * @param status The status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Get the funds provider
	 * 
	 * @return The funds provider
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.FUNDS_PROVIDER, traitType=TraitType.STRING)
	@XmlElement(name="funds-provider")
	@RDFUri(uri=StoreNS.FUNDS_PROVIDER)
	@RDFSubject
	public String getFundsProvider() {
		return fundsProvider;
	}

	/**
	 * Set the funds provider
	 * 
	 * @param fundsProvider The funds provider
	 */
	public void setFundsProvider(String fundsProvider) {
		this.fundsProvider = fundsProvider;
	}

	/**
	 * Get the reference number
	 * 
	 * @return The reference number
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.REFERENCE_NUMBER, traitType=TraitType.STRING)
	@XmlElement(name="reference-number")
	@RDFUri(uri=StoreNS.REFERENCE_NUMBER)
	@RDFSubject
	public String getReferenceNumber() {
		return referenceNumber;
	}

	/**
	 * Set the reference number
	 * 
	 * @param referenceNumber The reference number
	 */
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	/**
	 * Get the grant description
	 * 
	 * @return The description
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.DESCRIPTION, traitType=TraitType.STRING)
	@XmlElement(name="description")
	@RDFUri(uri=StoreNS.DESCRIPTION)
	@RDFSubject
	public String getDescription() {
		return description;
	}

	/**
	 * Set the grant description
	 * 
	 * @param description The description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the fields of research
	 * 
	 * @return The fields of research
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.FOR_SUBJECT, traitType=TraitType.SUBJECT_LIST)
	@XmlElement(name="for-subject")
	@RDFUri(uri=StoreNS.SUBJECT)
	@RDFSubject
	public List<Subject> getAnzforSubjects() {
		return anzforSubjects;
	}

	/**
	 * Set the fields of research
	 * 
	 * @param anzforSubjects The fields of research
	 */
	public void setAnzforSubjects(List<Subject> anzforSubjects) {
		this.anzforSubjects = anzforSubjects;
	}
}
