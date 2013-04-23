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
 * ANUStaffImpl
 * 
 * The Australian National University
 * 
 * Implementation class for anu staff members
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
public class ANUStaffImpl implements ANUStaff {
	private int ariesId;
	private String uniId;
	private String title;
	private String givenName;
	private String surname;
	private String fax;
	private String phone;
	private String email;
	private String forCode1;
	private String forCode1Percent;
	private String forCode2;
	private String forCode2Percent;
	private String forCode3;
	private String forCode3Percent;
	private String departmentName;
	
	/**
	 * Get the aries id
	 * 
	 * @return The aries id
	 */
	public int getAriesId() {
		return ariesId;
	}

	/**
	 * Set the aries id
	 * 
	 * @param ariesId The aries id
	 */
	public void setAriesId(int ariesId) {
		this.ariesId = ariesId;
	}

	/**
	 * Get the staff members university id
	 * 
	 * @return The university id
	 */
	public String getUniId() {
		return uniId;
	}

	/**
	 * Set the staff members university id
	 * 
	 * @param uniId The university id
	 */
	public void setUniId(String uniId) {
		this.uniId = uniId;
	}

	/**
	 * Get the title of the staff member
	 * 
	 * @return The title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title of the staff member
	 * 
	 * @param title The title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the given name of the staff member
	 * 
	 * @return The given name
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * Set the given name of the staff member
	 * 
	 * @param givenName The given name
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * Get the surname of the staff member
	 * 
	 * @return The surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Set the surname of the staff member
	 * 
	 * @param surname The surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Get the staff members fax number
	 * 
	 * @return The fax number 
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * Set the staff members fax number
	 * 
	 * @param fax The fax number
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * Get the staff members phone number
	 * 
	 * @return The phone number
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Set the staff members phone number
	 * 
	 * @param phone The phone number
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Get the staff members email address
	 * 
	 * @return The email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the staff members email address
	 * 
	 * @param email The email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the first field of research
	 * 
	 * @return  The percentage
	 */
	public String getFORCode1() {
		return forCode1;
	}

	/**
	 * Set the first field of research
	 * 
	 * @param FORCode The field of research
	 */
	public void setFORCode1(String FORCode) {
		this.forCode1 = FORCode;
	}

	/**
	 * Get the first field of research percentage
	 * 
	 * @return The percentage
	 */
	public String getFORPercentage1() {
		return forCode1Percent;
	}

	/**
	 * Set the first field of research percentage
	 * 
	 * @param percentage The percentage
	 */
	public void setFORPercentage1(String percentage) {
		this.forCode1Percent = percentage;
	}

	/**
	 * Get the second field of research
	 * 
	 * @return The field of research
	 */
	public String getFORCode2() {
		return forCode2;
	}

	/**
	 * Set the second field of research
	 * 
	 * @param FORCode The field of research
	 */
	public void setFORCode2(String FORCode) {
		this.forCode2 = FORCode;
	}

	/**
	 * Get the second field of research percentage
	 * 
	 * @return The percentage
	 */
	public String getFORPercentage2() {
		return forCode2Percent;
	}

	/**
	 * Set the second field of research percentage
	 * 
	 * @param percentage The percentage
	 */
	public void setFORPercentage2(String percentage) {
		this.forCode2Percent = percentage;
	}

	/**
	 * Get the third field of research
	 * 
	 * @return The field of research
	 */
	public String getFORCode3() {
		return forCode3;
	}

	/**
	 * Set the third field of research
	 * 
	 * @param FORCode The field of research
	 */
	public void setFORCode3(String FORCode) {
		this.forCode3 = FORCode;
	}

	/**
	 * Get the third field of research percentage
	 * 
	 * @return The percentage
	 */
	public String getFORPercentage3() {
		return forCode3Percent;
	}

	/**
	 * Set the third field of research percentage
	 * 
	 * @param percentage The percentage
	 */
	public void setFORPercentage3(String percentage) {
		this.forCode3Percent = percentage;
	}

	/**
	 * Get the department of the staff member
	 * 
	 * @return The department name
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * Set the department of the staff member
	 * 
	 * @param departmentName The department name
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
