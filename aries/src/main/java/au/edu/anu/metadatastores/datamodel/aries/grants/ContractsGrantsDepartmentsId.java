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
import javax.persistence.Embeddable;

/**
 * ContractsGrantsDepartmentsId
 * 
 * The Australian National University
 * 
 * Id class for the ContractsGrantsDepartments entity
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
@Embeddable
public class ContractsGrantsDepartmentsId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String chrPrimary;
	private String chrDepartmentCode;
	private String chrContractDepartmentCode;
	private String chrContractCode;

	/**
	 * Constructor
	 */
	public ContractsGrantsDepartmentsId() {
	}

	/**
	 * Constructor
	 * 
	 * @param chrPrimary Indicates whether this is the primary department
	 * @param chrDepartmentCode The department code
	 * @param chrContractDepartmentCode The contract department code
	 * @param chrContractCode The contract code
	 */
	public ContractsGrantsDepartmentsId(String chrPrimary,
			String chrDepartmentCode, String chrContractDepartmentCode,
			String chrContractCode) {
		this.chrPrimary = chrPrimary;
		this.chrDepartmentCode = chrDepartmentCode;
		this.chrContractDepartmentCode = chrContractDepartmentCode;
		this.chrContractCode = chrContractCode;
	}

	/**
	 * Get the whether it is the primary department
	 * 
	 * @return Whether it is the primary department
	 */
	@Column(name = "chrPrimary", length = 45)
	public String getChrPrimary() {
		return this.chrPrimary;
	}

	/**
	 * Set whether it is the primary department
	 * 
	 * @param chrPrimary Indicator for whether it is the primary department
	 */
	public void setChrPrimary(String chrPrimary) {
		this.chrPrimary = chrPrimary;
	}

	/**
	 * Get the department code
	 * 
	 * @return The department code
	 */
	@Column(name = "chrDepartmentCode", length = 45)
	public String getChrDepartmentCode() {
		return this.chrDepartmentCode;
	}

	/**
	 * Set the department code
	 * 
	 * @param chrDepartmentCode The department code
	 */
	public void setChrDepartmentCode(String chrDepartmentCode) {
		this.chrDepartmentCode = chrDepartmentCode;
	}

	/**
	 * Get the contract department code
	 * 
	 * @return The contract department code
	 */
	@Column(name = "chrContractDepartmentCode", length = 45)
	public String getChrContractDepartmentCode() {
		return this.chrContractDepartmentCode;
	}

	/**
	 * Set the contract department code
	 * 
	 * @param chrContractDepartmentCode The contract department code
	 */
	public void setChrContractDepartmentCode(String chrContractDepartmentCode) {
		this.chrContractDepartmentCode = chrContractDepartmentCode;
	}

	/**
	 * Get the contract department code
	 * 
	 * @return The contract department code
	 */
	@Column(name = "chrContractCode", length = 45)
	public String getChrContractCode() {
		return this.chrContractCode;
	}

	/**
	 * Get the contract code
	 * 
	 * @param chrContractCode The contract code
	 */
	public void setChrContractCode(String chrContractCode) {
		this.chrContractCode = chrContractCode;
	}

	/**
	 * Equals comparator override
	 * 
	 * @param other The object to compare against
	 */
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ContractsGrantsDepartmentsId))
			return false;
		ContractsGrantsDepartmentsId castOther = (ContractsGrantsDepartmentsId) other;

		return ((this.getChrPrimary() == castOther.getChrPrimary()) || (this
				.getChrPrimary() != null && castOther.getChrPrimary() != null && this
				.getChrPrimary().equals(castOther.getChrPrimary())))
				&& ((this.getChrDepartmentCode() == castOther
						.getChrDepartmentCode()) || (this
						.getChrDepartmentCode() != null
						&& castOther.getChrDepartmentCode() != null && this
						.getChrDepartmentCode().equals(
								castOther.getChrDepartmentCode())))
				&& ((this.getChrContractDepartmentCode() == castOther
						.getChrContractDepartmentCode()) || (this
						.getChrContractDepartmentCode() != null
						&& castOther.getChrContractDepartmentCode() != null && this
						.getChrContractDepartmentCode().equals(
								castOther.getChrContractDepartmentCode())))
				&& ((this.getChrContractCode() == castOther
						.getChrContractCode()) || (this.getChrContractCode() != null
						&& castOther.getChrContractCode() != null && this
						.getChrContractCode().equals(
								castOther.getChrContractCode())));
	}

	/**
	 * hashCode override
	 */
	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getChrPrimary() == null ? 0 : this.getChrPrimary()
						.hashCode());
		result = 37
				* result
				+ (getChrDepartmentCode() == null ? 0 : this
						.getChrDepartmentCode().hashCode());
		result = 37
				* result
				+ (getChrContractDepartmentCode() == null ? 0 : this
						.getChrContractDepartmentCode().hashCode());
		result = 37
				* result
				+ (getChrContractCode() == null ? 0 : this.getChrContractCode()
						.hashCode());
		return result;
	}

}
