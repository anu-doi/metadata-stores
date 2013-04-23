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
 * ExternalStaff
 * 
 * The Australian National University
 * 
 * Interface for classes that hold external staff information
 * 
 * @author Genevieve Turner
 *
 */
public interface ExternalStaff {
	/**
	 * Get the external staff aries id
	 * 
	 * @return The aries id
	 */
	public String getAriesStaffId();
	
	/**
	 * Set the external staff aries id
	 * 
	 * @param ariesStaffId The aries id
	 */
	public void setAriesStaffId(String ariesStaffId);
	
	/**
	 * Get the external staff members given name
	 * 
	 * @return The given name
	 */
	public String getGivenName();
	
	/**
	 * Set the external staff members given name
	 * 
	 * @param givenName The given name
	 */
	public void setGivenName(String givenName);
	
	/**
	 * Get the external staff membmers surname
	 * 
	 * @return The surname
	 */
	public String getSurname();
	
	/**
	 * Set the external staff members surname
	 * 
	 * @param surname The surname
	 */
	public void setSurname(String surname);
	
	/**
	 * Get the country the external staff member resides in
	 * 
	 * @return The name of the country
	 */
	public String getCountry();
	
	/**
	 * Set the country the external staff member resides in
	 * 
	 * @param country The name of the country
	 */
	public void setCountry(String country);
	
	/**
	 * Get the institution the staff member is affiliated with
	 * 
	 * @return The institution
	 */
	public String getInstitution();
	
	/**
	 * Set the institution the staff memeber is affiliated with
	 * 
	 * @param institution The institution
	 */
	public void setInstitution(String institution);
}
