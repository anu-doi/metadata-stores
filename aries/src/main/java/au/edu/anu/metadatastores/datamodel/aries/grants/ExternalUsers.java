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
 * ExternalUsers
 * 
 * The Australian National University
 * 
 * Entity class for the externalUsers table
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "externalUsers")
public class ExternalUsers {
	private String chrCode;
	private String chrFirstname;
	private String chrSurname;
	private String chrCountry;
	private String chrInstitutionName;
	
	/**
	 * Constructor
	 */
	public ExternalUsers() {
		
	}
	
	/**
	 * Constructor
	 * 
	 * @param chrCode Aries external staff code
	 */
	public ExternalUsers(String chrCode) {
		this.chrCode = chrCode;
	}
	
	/**
	 * Constructor
	 * 
	 * @param chrCode Aries external staff code
	 * @param chrFirstname The first name of the person
	 * @param chrSurname The surname of the person
	 * @param chrCountry The country the person works in
	 * @param chrInstitutionName The institution the person is affiliated with
	 */
	public ExternalUsers(String chrCode, String chrFirstname, String chrSurname, String chrCountry, String chrInstitutionName) {
		this.chrCode = chrCode;
		this.chrFirstname = chrFirstname;
		this.chrSurname = chrSurname;
		this.chrCountry = chrCountry;
		this.chrInstitutionName = chrInstitutionName;
	}
	
	/**
	 * Get the Aries external staff code
	 * 
	 * @return The staff code
	 */
	@Id
	@Column(name="chrCode", unique = true, nullable = false)
	public String getChrCode() {
		return chrCode;
	}
	
	/**
	 * Set the aries external staff code
	 * 
	 * @param chrCode The staff code
	 */
	public void setChrCode(String chrCode) {
		this.chrCode = chrCode;
	}
	
	/**
	 * Get the person's first name
	 * 
	 * @return The first name
	 */
	@Column(name="chrFirstname")
	public String getChrFirstname() {
		return chrFirstname;
	}
	
	/**
	 * Set the person's first name
	 * 
	 * @param chrFirstname The first name
	 */
	public void setChrFirstname(String chrFirstname) {
		this.chrFirstname = chrFirstname;
	}
	
	/**
	 * Get the person's surname
	 * 
	 * @return The surname
	 */
	@Column(name="chrSurname")
	public String getChrSurname() {
		return chrSurname;
	}
	
	/**
	 * Set the person's surname
	 * 
	 * @param chrSurname The surname
	 */
	public void setChrSurname(String chrSurname) {
		this.chrSurname = chrSurname;
	}
	
	/**
	 * Get the person's country
	 * 
	 * @return The country
	 */
	@Column(name="chrCountry")
	public String getChrCountry() {
		return chrCountry;
	}
	
	/**
	 * Set the person's country
	 * 
	 * @param chrCountry The country
	 */
	public void setChrCountry(String chrCountry) {
		this.chrCountry = chrCountry;
	}
	
	/**
	 * Get the person's institutional affiliation
	 * 
	 * @return The name of the institution
	 */
	@Column(name="chrInstitutionName")
	public String getChrInstitutionName() {
		return chrInstitutionName;
	}
	
	/**
	 * Set the person's institutional affiliation
	 * 
	 * @param chrInstitutionName The name of the institution
	 */
	public void setChrInstitutionName(String chrInstitutionName) {
		this.chrInstitutionName = chrInstitutionName;
	}
}
