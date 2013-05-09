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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>ContractsGrantsStatus<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Entity class for the <i>contracts_grants_status</i> table</p>
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 */
@Entity
@Table(name = "contracts_grants_status")
public class ContractsGrantsStatus implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String chrContractCode;
	private String chrContractStatusCode;
	private String chrStatus;
	private String chrCreatedByName;
	private String chrCreatedByCode;
	private String chrComments;

	/**
	 * Constructor
	 */
	public ContractsGrantsStatus() {
	}

	/**
	 * Constructor
	 * 
	 * @param chrContractCode The contract code
	 */
	public ContractsGrantsStatus(String chrContractCode) {
		this.chrContractCode = chrContractCode;
	}

	/**
	 * Constructor
	 * 
	 * @param chrContractCode The contract code
	 * @param chrContractStatusCode The contract status code
	 * @param chrStatus The status
	 * @param chrCreatedByName The title, initial and surname of the user who created the status record
	 * @param chrCreatedByCode The university id of the user who created the status record
	 * @param chrComments Comments for the status record
	 */
	public ContractsGrantsStatus(String chrContractCode,
			String chrContractStatusCode, String chrStatus,
			String chrCreatedByName, String chrCreatedByCode, String chrComments) {
		this.chrContractCode = chrContractCode;
		this.chrContractStatusCode = chrContractStatusCode;
		this.chrStatus = chrStatus;
		this.chrCreatedByName = chrCreatedByName;
		this.chrCreatedByCode = chrCreatedByCode;
		this.chrComments = chrComments;
	}

	/**
	 * Get the contract code
	 * 
	 * @return The contract code
	 */
	@Id
	@Column(name = "chrContractCode", unique = true, nullable = false, length = 45)
	public String getChrContractCode() {
		return this.chrContractCode;
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
	 * Get the contract status code
	 * 
	 * @return The status code
	 */
	@Column(name = "chrContractStatusCode", length = 45)
	public String getChrContractStatusCode() {
		return this.chrContractStatusCode;
	}

	/**
	 * Set the contract status code
	 * 
	 * @param chrContractStatusCode The status code
	 */
	public void setChrContractStatusCode(String chrContractStatusCode) {
		this.chrContractStatusCode = chrContractStatusCode;
	}

	/**
	 * Get the status
	 * 
	 * @return the status
	 */
	@Column(name = "chrStatus", length = 45)
	public String getChrStatus() {
		return this.chrStatus;
	}

	/**
	 * Set the status
	 * @param chrStatus the status
	 */
	public void setChrStatus(String chrStatus) {
		this.chrStatus = chrStatus;
	}

	/**
	 * Get the title, initial, and surname of the user who created the status
	 * 
	 * @return The name of the user who created the status
	 */
	@Column(name = "chrCreatedByName", length = 45)
	public String getChrCreatedByName() {
		return this.chrCreatedByName;
	}

	/**
	 * Set the name of the user who created the status
	 * 
	 * @param chrCreatedByName The name of the user who created the status
	 */
	public void setChrCreatedByName(String chrCreatedByName) {
		this.chrCreatedByName = chrCreatedByName;
	}

	/**
	 * Get the university id of the user who created the status
	 * 
	 * @return The university id
	 */
	@Column(name = "chrCreatedByCode", length = 45)
	public String getChrCreatedByCode() {
		return this.chrCreatedByCode;
	}

	/**
	 * Set the university id of the user who created the status
	 * 
	 * @param chrCreatedByCode The university id
	 */
	public void setChrCreatedByCode(String chrCreatedByCode) {
		this.chrCreatedByCode = chrCreatedByCode;
	}

	/**
	 * Get the status comments
	 * 
	 * @return The comments
	 */
	@Column(name = "chrComments", length = 45)
	public String getChrComments() {
		return this.chrComments;
	}

	/**
	 * Set the status comments
	 * 
	 * @param chrComments The comments
	 */
	public void setChrComments(String chrComments) {
		this.chrComments = chrComments;
	}

}
