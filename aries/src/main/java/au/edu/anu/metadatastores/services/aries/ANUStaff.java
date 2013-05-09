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
 * <p>ANUStaff<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Interface for ANU staff members</p>
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
public interface ANUStaff {
	/**
	 * Get the aries id
	 * 
	 * @return The aries id
	 */
	public int getAriesId();
	
	/**
	 * Set the aries id
	 * 
	 * @param ariesId The aries id
	 */
	public void setAriesId(int ariesId);
	
	/**
	 * Get the staff members university id
	 * 
	 * @return The university id
	 */
	public String getUniId();
	
	/**
	 * Set the staff members university id
	 * 
	 * @param uniId The university id
	 */
	public void setUniId(String uniId);
	
	/**
	 * Get the title of the staff member
	 * 
	 * @return The title
	 */
	public String getTitle();
	
	/**
	 * Set the title of the staff member
	 * 
	 * @param title The title
	 */
	public void setTitle(String title);
	
	/**
	 * Get the given name of the staff member
	 * 
	 * @return The given name
	 */
	public String getGivenName();
	
	/**
	 * Set the given name of the staff member
	 * 
	 * @param givenName The given name
	 */
	public void setGivenName(String givenName);
	
	/**
	 * Get the surname of the staff member
	 * 
	 * @return The surname
	 */
	public String getSurname();
	
	/**
	 * Set the surname of the staff member
	 * 
	 * @param surname The surname
	 */
	public void setSurname(String surname);
	
	/**
	 * Get the staff members fax number
	 * 
	 * @return The fax number 
	 */
	public String getFax();
	
	/**
	 * Set the staff members fax number
	 * 
	 * @param fax The fax number
	 */
	public void setFax(String fax);
	
	/**
	 * Get the staff members phone number
	 * 
	 * @return The phone number
	 */
	public String getPhone();
	
	/**
	 * Set the staff members phone number
	 * 
	 * @param phone The phone number
	 */
	public void setPhone(String phone);
	
	/**
	 * Get the staff members email address
	 * 
	 * @return The email address
	 */
	public String getEmail();
	
	/**
	 * Set the staff members email address
	 * 
	 * @param email The email address
	 */
	public void setEmail(String email);
	
	/**
	 * Get the first field of research
	 * 
	 * @return  The percentage
	 */
	public String getFORCode1();
	
	/**
	 * Set the first field of research
	 * 
	 * @param FORCode The field of research
	 */
	public void setFORCode1(String FORCode);
	
	/**
	 * Get the first field of research percentage
	 * 
	 * @return The percentage
	 */
	public String getFORPercentage1();
	
	/**
	 * Set the first field of research percentage
	 * 
	 * @param percentage The percentage
	 */
	public void setFORPercentage1(String percentage);
	
	/**
	 * Get the second field of research
	 * 
	 * @return The field of research
	 */
	public String getFORCode2();
	
	/**
	 * Set the second field of research
	 * 
	 * @param FORCode The field of research
	 */
	public void setFORCode2(String FORCode);
	
	/**
	 * Get the second field of research percentage
	 * 
	 * @return The percentage
	 */
	public String getFORPercentage2();
	
	/**
	 * Set the second field of research percentage
	 * 
	 * @param percentage The percentage
	 */
	public void setFORPercentage2(String percentage);
	
	/**
	 * Get the third field of research
	 * 
	 * @return The field of research
	 */
	public String getFORCode3();
	
	/**
	 * Set the third field of research
	 * 
	 * @param FORCode The field of research
	 */
	public void setFORCode3(String FORCode);
	
	/**
	 * Get the third field of research percentage
	 * 
	 * @return The percentage
	 */
	public String getFORPercentage3();
	
	/**
	 * Set the third field of research percentage
	 * 
	 * @param percentage The percentage
	 */
	public void setFORPercentage3(String percentage);
	
	/**
	 * Get the department of the staff member
	 * 
	 * @return The department name
	 */
	public String getDepartmentName();
	
	/**
	 * Set the department of the staff member
	 * 
	 * @param departmentName The department name
	 */
	public void setDepartmentName(String departmentName);
}
