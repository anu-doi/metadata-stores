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
import javax.persistence.Embeddable;


/**
 * FacultyschoolcentreId
 * 
 * The Australian National University
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
@Embeddable
public class FacultyschoolcentreId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String chrTierCode;
	private String chrTier2name;
	private String chrTier2code;
	private String chrDateStatus;
	private String chrAou;
	private String chrFinanceCode;
	private String chrHraou;

	/**
	 * Constructor
	 */
	public FacultyschoolcentreId() {
	}

	/**
	 * Constructor
	 * 
	 * @param chrTierCode 
	 * @param chrTier2name The faculty name
	 * @param chrTier2code The faculty code
	 * @param chrDateStatus Status of finish date (i.e. 'Finish date is on-going' or 'Finish date is not on-going')
	 * @param chrAou The Academic Organisational Unit
	 * @param chrFinanceCode The finance code
	 * @param chrHraou The budget code of the department in ESP HR system
	 */
	public FacultyschoolcentreId(String chrTierCode, String chrTier2name,
			String chrTier2code, String chrDateStatus, String chrAou,
			String chrFinanceCode, String chrHraou) {
		this.chrTierCode = chrTierCode;
		this.chrTier2name = chrTier2name;
		this.chrTier2code = chrTier2code;
		this.chrDateStatus = chrDateStatus;
		this.chrAou = chrAou;
		this.chrFinanceCode = chrFinanceCode;
		this.chrHraou = chrHraou;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "chrTierCode", length = 45)
	public String getChrTierCode() {
		return this.chrTierCode;
	}

	/**
	 * 
	 * @param chrTierCode
	 */
	public void setChrTierCode(String chrTierCode) {
		this.chrTierCode = chrTierCode;
	}

	/**
	 * Get the faculty name
	 * 
	 * @return The faculty name
	 */
	@Column(name = "chrTier2Name", length = 45)
	public String getChrTier2name() {
		return this.chrTier2name;
	}

	/**
	 * Set the faculty name
	 * @param chrTier2name The faculty name
	 */
	public void setChrTier2name(String chrTier2name) {
		this.chrTier2name = chrTier2name;
	}

	/**
	 * Get the faculty code
	 * 
	 * @return The faculty code
	 */
	@Column(name = "chrTier2Code", length = 45)
	public String getChrTier2code() {
		return this.chrTier2code;
	}

	/**
	 * Set the faculty code
	 * 
	 * @param chrTier2code Set the faculty code
	 */
	public void setChrTier2code(String chrTier2code) {
		this.chrTier2code = chrTier2code;
	}

	/**
	 * Get the Status of finish date (i.e. 'Finish date is on-going' or 'Finish date is not on-going')
	 * @return The status
	 */
	@Column(name = "chrDateStatus", length = 45)
	public String getChrDateStatus() {
		return this.chrDateStatus;
	}

	/**
	 * Set the Status of finish date (i.e. 'Finish date is on-going' or 'Finish date is not on-going')
	 * 
	 * @param chrDateStatus The status
	 */
	public void setChrDateStatus(String chrDateStatus) {
		this.chrDateStatus = chrDateStatus;
	}

	/**
	 * Get the Academic Organisational Unit
	 * 
	 * @return The academic unit
	 */
	@Column(name = "chrAOU", length = 45)
	public String getChrAou() {
		return this.chrAou;
	}

	/**
	 * Set the Academic Organisational Unit
	 * 
	 * @param chrAou The academic unit
	 */
	public void setChrAou(String chrAou) {
		this.chrAou = chrAou;
	}

	/**
	 * Get the finance code of the department in ESP Finance system
	 * 
	 * @return Set the finance code
	 */
	@Column(name = "chrFinanceCode", length = 45)
	public String getChrFinanceCode() {
		return this.chrFinanceCode;
	}

	/**
	 * Set the finance code of the department in ESP Finance system
	 * 
	 * @param chrFinanceCode The finance code
	 */
	public void setChrFinanceCode(String chrFinanceCode) {
		this.chrFinanceCode = chrFinanceCode;
	}

	/**
	 * Get the budget code of the department in ESP Finance system
	 * 
	 * @return The budget code
	 */
	@Column(name = "chrHRAOU", length = 45)
	public String getChrHraou() {
		return this.chrHraou;
	}

	/**
	 * Set the budget code of department in ESP Finance system
	 * 
	 * @param chrHraou The budget code
	 */
	public void setChrHraou(String chrHraou) {
		this.chrHraou = chrHraou;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FacultyschoolcentreId))
			return false;
		FacultyschoolcentreId castOther = (FacultyschoolcentreId) other;

		return ((this.getChrTierCode() == castOther.getChrTierCode()) || (this
				.getChrTierCode() != null && castOther.getChrTierCode() != null && this
				.getChrTierCode().equals(castOther.getChrTierCode())))
				&& ((this.getChrTier2name() == castOther.getChrTier2name()) || (this
						.getChrTier2name() != null
						&& castOther.getChrTier2name() != null && this
						.getChrTier2name().equals(castOther.getChrTier2name())))
				&& ((this.getChrTier2code() == castOther.getChrTier2code()) || (this
						.getChrTier2code() != null
						&& castOther.getChrTier2code() != null && this
						.getChrTier2code().equals(castOther.getChrTier2code())))
				&& ((this.getChrDateStatus() == castOther.getChrDateStatus()) || (this
						.getChrDateStatus() != null
						&& castOther.getChrDateStatus() != null && this
						.getChrDateStatus()
						.equals(castOther.getChrDateStatus())))
				&& ((this.getChrAou() == castOther.getChrAou()) || (this
						.getChrAou() != null && castOther.getChrAou() != null && this
						.getChrAou().equals(castOther.getChrAou())))
				&& ((this.getChrFinanceCode() == castOther.getChrFinanceCode()) || (this
						.getChrFinanceCode() != null
						&& castOther.getChrFinanceCode() != null && this
						.getChrFinanceCode().equals(
								castOther.getChrFinanceCode())))
				&& ((this.getChrHraou() == castOther.getChrHraou()) || (this
						.getChrHraou() != null
						&& castOther.getChrHraou() != null && this
						.getChrHraou().equals(castOther.getChrHraou())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getChrTierCode() == null ? 0 : this.getChrTierCode()
						.hashCode());
		result = 37
				* result
				+ (getChrTier2name() == null ? 0 : this.getChrTier2name()
						.hashCode());
		result = 37
				* result
				+ (getChrTier2code() == null ? 0 : this.getChrTier2code()
						.hashCode());
		result = 37
				* result
				+ (getChrDateStatus() == null ? 0 : this.getChrDateStatus()
						.hashCode());
		result = 37 * result
				+ (getChrAou() == null ? 0 : this.getChrAou().hashCode());
		result = 37
				* result
				+ (getChrFinanceCode() == null ? 0 : this.getChrFinanceCode()
						.hashCode());
		result = 37 * result
				+ (getChrHraou() == null ? 0 : this.getChrHraou().hashCode());
		return result;
	}

}
