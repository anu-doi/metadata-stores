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

// Generated 10/01/2013 2:39:04 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ContractsGrantsInvestigators
 * 
 * The Australian National University
 * 
 * Entity class for the contracts_grants_investigators table
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "contracts_grants_investigators")
public class ContractsGrantsInvestigators implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String chrContractInvestigatorCode;
	private String chrStaffNumber;
	private String chrRoute;
	private String chrPrimary;
	private ContractsGrantsMain contractsGrantsMain;

	/**
	 * Constructor
	 */
	public ContractsGrantsInvestigators() {
	}

	/**
	 * Get the contract investigator code
	 * 
	 * @return The contract investigator code
	 */
	@Id
	@Column(name = "chrContractInvestigatorCode", insertable=false, updatable=false)
	public String getChrContractInvestigatorCode() {
		return this.chrContractInvestigatorCode;
	}

	/**
	 * Set the contract investigator code
	 * 
	 * @param chrContractInvestigatorCode The contract investigator code
	 */
	public void setChrContractInvestigatorCode(
			String chrContractInvestigatorCode) {
		this.chrContractInvestigatorCode = chrContractInvestigatorCode;
	}

	/**
	 * Get the staff number (university id or aries external id)
	 * 
	 * @return The staff number
	 */
	@Column(name = "chrStaffNumber")
	public String getChrStaffNumber() {
		return this.chrStaffNumber;
	}

	/**
	 * Set the staff number (university id or aries external id)
	 * 
	 * @param chrStaffNumber The staff number
	 */
	public void setChrStaffNumber(String chrStaffNumber) {
		this.chrStaffNumber = chrStaffNumber;
	}

	/**
	 * Get what sort of investigator this person is (i.e. External, Internal, Salary or Student)
	 * 
	 * @return The investigator type
	 */
	@Column(name = "chrRoute")
	public String getChrRoute() {
		return this.chrRoute;
	}

	/**
	 * Get what sort of investigator this person is (i.e. External, Internal, Salary or Student)
	 * 
	 * @param chrRoute The investigator type
	 */
	public void setChrRoute(String chrRoute) {
		this.chrRoute = chrRoute;
	}

	/**
	 * Get whether this investigator is the primary investigator
	 * 
	 * @return Whether this investigator is the primary investigator
	 */
	@Column(name="chrPrimary")
	public String getChrPrimary() {
		return chrPrimary;
	}

	/**
	 * Set whether this investigator is the primary investigator
	 * 
	 * @param chrPrimary Whether this investigator is the primary investigator
	 */
	public void setChrPrimary(String chrPrimary) {
		this.chrPrimary = chrPrimary;
	}

	/**
	 * Get the associated contract
	 * 
	 * @return The contract
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chrContractCode")
	public ContractsGrantsMain getContractsGrantsMain() {
		return contractsGrantsMain;
	}

	/**
	 * Set the associated contract
	 * 
	 * @param contractsGrantsMain The contract
	 */
	public void setContractsGrantsMain(ContractsGrantsMain contractsGrantsMain) {
		this.contractsGrantsMain = contractsGrantsMain;
	}
}
