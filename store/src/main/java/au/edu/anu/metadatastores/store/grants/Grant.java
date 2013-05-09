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
@XmlRootElement(name="grant")
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
	private List<Subject> anzforSubjects = new ArrayList<Subject>();
	
	/**
	 * Get the contract code
	 * 
	 * @return The contract code
	 */
	@XmlElement(name="contract-code")
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
	@XmlElement(name="title")
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
	@XmlElement(name="first-investigator")
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
	@XmlElement(name="start-date")
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
	@XmlElement(name="end-date")
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
	@XmlElement(name="status")
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
	@XmlElement(name="funds-provider")
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
	@XmlElement(name="reference-number")
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
	 * Get the fields of research
	 * 
	 * @return The fields of research
	 */
	@XmlElement(name="for-subject")
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
