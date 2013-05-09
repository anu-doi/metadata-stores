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

package au.edu.anu.metadatastores.services.aries;

/**
 * 
 * <p>ExternalStaffImpl<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>External staff implementation class</p>
 * 
 * @author Genevieve Turner
 *
 */
public class ExternalStaffImpl implements ExternalStaff {
	private String ariesStaffId;
	private String givenName;
	private String surname;
	private String country;
	private String institution;

	/**
	 * Get the external staff aries id
	 * 
	 * @return The aries id
	 */
	public String getAriesStaffId() {
		return ariesStaffId;
	}

	/**
	 * Set the external staff aries id
	 * 
	 * @param ariesStaffId The aries id
	 */
	public void setAriesStaffId(String ariesStaffId) {
		this.ariesStaffId = ariesStaffId;
	}

	/**
	 * Get the external staff members given name
	 * 
	 * @return The given name
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * Set the external staff members given name
	 * 
	 * @param givenName The given name
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * Get the external staff membmers surname
	 * 
	 * @return The surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Set the external staff members surname
	 * 
	 * @param surname The surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Get the country the external staff member resides in
	 * 
	 * @return The name of the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Set the country the external staff member resides in
	 * 
	 * @param country The name of the country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Get the institution the staff member is affiliated with
	 * 
	 * @return The institution
	 */
	public String getInstitution() {
		return institution;
	}

	/**
	 * Set the institution the staff memeber is affiliated with
	 * 
	 * @param institution The institution
	 */
	public void setInstitution(String institution) {
		this.institution = institution;
	}

}
