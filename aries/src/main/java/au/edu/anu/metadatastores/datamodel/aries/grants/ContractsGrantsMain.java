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

package au.edu.anu.metadatastores.datamodel.aries.grants;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * ContractsGrantsMain
 * 
 * The Australian National University
 * 
 * Entity class for the contract_grants_main table
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "contracts_grants_main")
public class ContractsGrantsMain implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String chrContractCode;
	private String chrShortTitle;
	private String chrPrimaryFundsProvider;
	private String chrSchemeRef;
	private String chrGrantStartDate;
	private String chrCompletionDate;
	private String chrStatus;
	private ForCodes forCodes1;
	private String chrForpercentage1;
	private ForCodes forCodes2;
	private String chrForpercentage2;
	private ForCodes forCodes3;
	private String chrForpercentage3;
	private List<ContractsGrantsInvestigators> contractsGrantsInvestigators = new ArrayList<ContractsGrantsInvestigators>();

	/**
	 * Constructor
	 */
	public ContractsGrantsMain() {
	}
	
	/**
	 * Get the contract code
	 * 
	 * @return The contract code
	 */
	@Id
	@Column(name = "chrContractCode")
	public String getChrContractCode() {
		return chrContractCode;
	}

	/**
	 * Set the contract code
	 * 
	 * @param chrContractCode The contract code
	 */
	public void setChrContractCode(String chrContractCode) {
		this.chrContractCode = chrContractCode;
	}

	/**
	 * Get the grant application title
	 * 
	 * @return The grant application title
	 */
	@Column(name = "chrShortTitle")
	public String getChrShortTitle() {
		return chrShortTitle;
	}

	/**
	 * Set the grant application title
	 * 
	 * @param chrShortTitle The grant application title
	 */
	public void setChrShortTitle(String chrShortTitle) {
		this.chrShortTitle = chrShortTitle;
	}

	/**
	 * Get the primary funds provider
	 * 
	 * @return The primary funds provider
	 */
	@Column(name = "chrPrimaryFundsProvider")
	public String getChrPrimaryFundsProvider() {
		return chrPrimaryFundsProvider;
	}

	/**
	 * Set the primary funds provider
	 * 
	 * @param chrPrimaryFundsProvider The primary funds provider
	 */
	public void setChrPrimaryFundsProvider(String chrPrimaryFundsProvider) {
		this.chrPrimaryFundsProvider = chrPrimaryFundsProvider;
	}

	/**
	 * Get the funds provider reference number
	 * 
	 * @return The reference number
	 */
	@Column(name = "chrSchemeRef")
	public String getChrSchemeRef() {
		return chrSchemeRef;
	}

	/**
	 * Set the funds provider reference number
	 * 
	 * @param chrSchemeRef The reference number
	 */
	public void setChrSchemeRef(String chrSchemeRef) {
		this.chrSchemeRef = chrSchemeRef;
	}

	/**
	 * Get the grant start date
	 * 
	 * @return The grant start date
	 */
	@Column(name = "chrGrantStartDate")
	public String getChrGrantStartDate() {
		return chrGrantStartDate;
	}

	/**
	 * Set the grant start date
	 * 
	 * @param chrGrantStartDate The grant start date
	 */
	public void setChrGrantStartDate(String chrGrantStartDate) {
		this.chrGrantStartDate = chrGrantStartDate;
	}

	/**
	 * Set the grant end date
	 * 
	 * @return The grant end date
	 */
	@Column(name = "chrCompletionDate")
	public String getChrCompletionDate() {
		return chrCompletionDate;
	}

	/**
	 * Set the grant end date
	 * 
	 * @param chrCompletionDate The grant end date
	 */
	public void setChrCompletionDate(String chrCompletionDate) {
		this.chrCompletionDate = chrCompletionDate;
	}
	
	/**
	 * Get the grant status e.g. 'DRAFT'
	 * 
	 * @return The grant status
	 */
	@Column(name = "chrStatus")
	public String getChrStatus() {
		return chrStatus;
	}

	/**
	 * Set the grant status
	 * 
	 * @param chrStatus The grant status
	 */
	public void setChrStatus(String chrStatus) {
		this.chrStatus = chrStatus;
	}

	/**
	 * Get the percentage of the first ANZ Field of Research
	 * 
	 * @return The percentage
	 */
	@Column(name = "chrFORpercentage1")
	public String getChrForpercentage1() {
		return chrForpercentage1;
	}

	/**
	 * Set the percentage of the first ANZ Field of Research
	 * 
	 * @param chrForpercentage1 The percentage
	 */
	public void setChrForpercentage1(String chrForpercentage1) {
		this.chrForpercentage1 = chrForpercentage1;
	}
	
	/**
	 * Get the percentage of the second ANZ Field of Research
	 * 
	 * @return The percentage
	 */
	@Column(name = "chrFORpercentage2")
	public String getChrForpercentage2() {
		return chrForpercentage2;
	}

	/**
	 * Set the percentage of the second ANZ Field of Research
	 * 
	 * @param chrForpercentage2 The percentage
	 */
	public void setChrForpercentage2(String chrForpercentage2) {
		this.chrForpercentage2 = chrForpercentage2;
	}
	
	/**
	 * Get the percentage of the third ANZ Field of Research
	 * 
	 * @return The percentage
	 */
	@Column(name = "chrFORpercentage3")
	public String getChrForpercentage3() {
		return chrForpercentage3;
	}

	/**
	 * Set the percentage of the third ANZ Field of Research
	 * 
	 * @param chrForpercentage3 The percentage
	 */
	public void setChrForpercentage3(String chrForpercentage3) {
		this.chrForpercentage3 = chrForpercentage3;
	}

	/**
	 * Get the investigators involved in the contract
	 * 
	 * @return The investigators
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contractsGrantsMain")
	public List<ContractsGrantsInvestigators> getContractsGrantsInvestigators() {
		return this.contractsGrantsInvestigators;
	}
	
	/**
	 * Set the investigators involved in the contract
	 * 
	 * @param contractsGrantsInvestigators The investigators
	 */
	public void setContractsGrantsInvestigators(List<ContractsGrantsInvestigators> contractsGrantsInvestigators) {
		this.contractsGrantsInvestigators = contractsGrantsInvestigators;
	}
	
	/**
	 * Get the first ANZ Field of Research code
	 * 
	 * @return The field of research
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chrFORcode1")
	@NotFound(action=NotFoundAction.IGNORE)
	public ForCodes getForCodes1() {
		return forCodes1;
	}

	/**
	 * Set the first ANZ Field of Research code
	 * 
	 * @param forCodes1 The field of research
	 */
	public void setForCodes1(ForCodes forCodes1) {
		this.forCodes1 = forCodes1;
	}

	/**
	 * Get the second ANZ Field of Research code
	 * 
	 * @return The field of research
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chrFORcode2")
	@NotFound(action=NotFoundAction.IGNORE)
	public ForCodes getForCodes2() {
		return forCodes2;
	}

	/**
	 * Set the second ANZ Field of Research code
	 * @param forCodes2
	 */
	public void setForCodes2(ForCodes forCodes2) {
		this.forCodes2 = forCodes2;
	}

	/**
	 * Get the third ANZ Field of Research code
	 * 
	 * @return The field of research
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chrFORcode3")
	@NotFound(action=NotFoundAction.IGNORE)
	public ForCodes getForCodes3() {
		return forCodes3;
	}

	/**
	 * Set the third ANZ Field of Research code
	 * 
	 * @param forCodes3 The field of research
	 */
	public void setForCodes3(ForCodes forCodes3) {
		this.forCodes3 = forCodes3;
	}
}
