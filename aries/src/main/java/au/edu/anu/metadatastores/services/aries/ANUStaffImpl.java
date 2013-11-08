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
 * <p>ANUStaffImpl<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Implementation class for anu staff members</p>
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
	private Subject forSubject1;
	private Subject forSubject2;
	private Subject forSubject3;
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
	 * Get the first field of research subject
	 * 
	 * @return The field of research
	 */
	public Subject getForSubject1() {
		return forSubject1;
	}

	/**
	 * Set the first field of research subject
	 * 
	 * @param forSubject1 The field of research
	 */
	public void setForSubject1(Subject forSubject1) {
		this.forSubject1 = forSubject1;
	}

	/**
	 * Get the second field of research
	 * 
	 * @return The field of research
	 */
	public Subject getForSubject2() {
		return forSubject2;
	}

	/**
	 * Set the second field of research
	 * 
	 * @param forSubject2 The field of research
	 */
	public void setForSubject2(Subject forSubject2) {
		this.forSubject2 = forSubject2;
	}

	/**
	 * Get the third field of research
	 * 
	 * @return The field of research
	 */
	public Subject getForSubject3() {
		return forSubject3;
	}

	/**
	 * Set the third field of  research
	 * 
	 * @param forSubject3 The field of research
	 */
	public void setForSubject3(Subject forSubject3) {
		this.forSubject3 = forSubject3;
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
