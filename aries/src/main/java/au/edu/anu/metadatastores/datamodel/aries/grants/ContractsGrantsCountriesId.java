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
 * ContractsGrantsCountriesId
 * 
 * The Australian National University
 * 
 * Class for mapping the id of the contracts_grants_countries
 * 
 * @author Genevieve Turner
 */
@Embeddable
public class ContractsGrantsCountriesId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String chrContractCode;
	private String chrContractCountryCode;
	private String chrCountryName;

	/**
	 * Constructor
	 */
	public ContractsGrantsCountriesId() {
	}

	/**
	 * Constructor
	 * 
	 * @param chrContractCode Contract code for the contract
	 * @param chrContractCountryCode Country code for the contract
	 * @param chrCountryName Country name for the contract
	 */
	public ContractsGrantsCountriesId(String chrContractCode,
			String chrContractCountryCode, String chrCountryName) {
		this.chrContractCode = chrContractCode;
		this.chrContractCountryCode = chrContractCountryCode;
		this.chrCountryName = chrCountryName;
	}

	/**
	 * Get the contract code
	 * 
	 * @return The contract code
	 */
	@Column(name = "chrContractCode", length = 45)
	public String getChrContractCode() {
		return this.chrContractCode;
	}

	/**
	 * Set the contract code
	 * @param chrContractCode The contract code
	 */
	public void setChrContractCode(String chrContractCode) {
		this.chrContractCode = chrContractCode;
	}

	/**
	 * Get the contract country code
	 * 
	 * @return The contract country code
	 */
	@Column(name = "chrContractCountryCode", length = 45)
	public String getChrContractCountryCode() {
		return this.chrContractCountryCode;
	}

	/**
	 * Set the contract country code
	 * 
	 * @param chrContractCountryCode The contract country code
	 */
	public void setChrContractCountryCode(String chrContractCountryCode) {
		this.chrContractCountryCode = chrContractCountryCode;
	}

	/**
	 * Get the country name
	 * 
	 * @return The country name
	 */
	@Column(name = "chrCountryName", length = 45)
	public String getChrCountryName() {
		return this.chrCountryName;
	}

	/**
	 * Set the country name
	 * 
	 * @param chrCountryName The country name
	 */
	public void setChrCountryName(String chrCountryName) {
		this.chrCountryName = chrCountryName;
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
		if (!(other instanceof ContractsGrantsCountriesId))
			return false;
		ContractsGrantsCountriesId castOther = (ContractsGrantsCountriesId) other;

		return ((this.getChrContractCode() == castOther.getChrContractCode()) || (this
				.getChrContractCode() != null
				&& castOther.getChrContractCode() != null && this
				.getChrContractCode().equals(castOther.getChrContractCode())))
				&& ((this.getChrContractCountryCode() == castOther
						.getChrContractCountryCode()) || (this
						.getChrContractCountryCode() != null
						&& castOther.getChrContractCountryCode() != null && this
						.getChrContractCountryCode().equals(
								castOther.getChrContractCountryCode())))
				&& ((this.getChrCountryName() == castOther.getChrCountryName()) || (this
						.getChrCountryName() != null
						&& castOther.getChrCountryName() != null && this
						.getChrCountryName().equals(
								castOther.getChrCountryName())));
	}

	/**
	 * hashCode override
	 */
	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getChrContractCode() == null ? 0 : this.getChrContractCode()
						.hashCode());
		result = 37
				* result
				+ (getChrContractCountryCode() == null ? 0 : this
						.getChrContractCountryCode().hashCode());
		result = 37
				* result
				+ (getChrCountryName() == null ? 0 : this.getChrCountryName()
						.hashCode());
		return result;
	}

}
