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

// Generated 29/01/2013 4:37:31 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * <p>Useraccounts<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Entity class for the <i>useraccounts</i> table</p>
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "useraccounts")
public class Useraccounts implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private int intSystemCounter;
	private String chrStaffNumber;
	private String chrTitle;
	private String chrFirstname;
	private String chrSurname;
	private String chrFax;
	private String chrTelephone;
	private String chrEmail;
	//private String chrForcode1;
	private ForCodes forCodes1;
	private String chrForpercentage1;
	//private String chrForcode2;
	private ForCodes forCodes2;
	private String chrForpercentage2;
	//private String chrForcode3;
	private ForCodes forCodes3;
	private String chrForpercentage3;
	private String chrDepartmentCode;

	/**
	 * Constructor
	 */
	public Useraccounts() {
	}
	
	/**
	 * Constructor
	 * 
	 * @param intSystemCounter The system counter for user accounts
	 */
	public Useraccounts(int intSystemCounter) {
		this.intSystemCounter = intSystemCounter;
	}
	
	/**
	 * Constructor
	 * 
	 * @param intSystemCounter The system counter for user accounts
	 * @param chrStaffNumber The university id of the user
	 * @param chrTitle The title of the user
	 * @param chrFirstname The first name of the user
	 * @param chrSurname The surname of the user
	 * @param chrFax The fax number of the user
	 * @param chrTelephone The phone number of the user
	 * @param chrEmail The email of the user
	 * @param forCodes1 The first field of research code
	 * @param chrForpercentage1 The percentage of the first field of research
	 * @param forCodes2 The second field of research code
	 * @param chrForpercentage2 The percentage of the second field of research
	 * @param forCodes3 The third field of research code
	 * @param chrForpercentage3 The percentage of the third field of research
	 * @param chrDepartmentCode The users department code
	 */
	public Useraccounts(int intSystemCounter, String chrStaffNumber,
			String chrTitle, String chrFirstname, String chrSurname,
			String chrFax, String chrTelephone, String chrEmail,
			ForCodes forCodes1, String chrForpercentage1, ForCodes forCodes2,
			String chrForpercentage2, ForCodes forCodes3,
			String chrForpercentage3, String chrDepartmentCode) {
		this.intSystemCounter = intSystemCounter;
		this.chrStaffNumber = chrStaffNumber;
		this.chrTitle = chrTitle;
		this.chrFirstname = chrFirstname;
		this.chrSurname = chrSurname;
		this.chrFax = chrFax;
		this.chrTelephone = chrTelephone;
		this.chrEmail = chrEmail;
		//this.chrForcode1 = chrForcode1;
		this.forCodes1 = forCodes1;
		this.chrForpercentage1 = chrForpercentage1;
		//this.chrForcode2 = chrForcode2;
		this.forCodes2 = forCodes2;
		this.chrForpercentage2 = chrForpercentage2;
		//this.chrForcode3 = chrForcode3;
		this.forCodes3 = forCodes3;
		this.chrForpercentage3 = chrForpercentage3;
		this.chrDepartmentCode = chrDepartmentCode;
	}

	/**
	 * Get the system counter
	 * 
	 * @return The aries id
	 */
	@Id
	@Column(name = "intSystemCounter", unique = true, nullable = false)
	public int getIntSystemCounter() {
		return this.intSystemCounter;
	}

	/**
	 * Set the system counter
	 * 
	 * @param intSystemCounter The aries id
	 */
	public void setIntSystemCounter(int intSystemCounter) {
		this.intSystemCounter = intSystemCounter;
	}

	/**
	 * Get the users university id
	 * 
	 * @return the university id
	 */
	@Column(name = "chrStaffNumber", length = 45)
	public String getChrStaffNumber() {
		return this.chrStaffNumber;
	}

	/**
	 * Set the users university id
	 * 
	 * @param chrStaffNumber The university id
	 */
	public void setChrStaffNumber(String chrStaffNumber) {
		this.chrStaffNumber = chrStaffNumber;
	}

	/**
	 * Get the users title (e.g. Dr, Mr, Mrs)
	 * 
	 * @return The title
	 */
	@Column(name = "chrTitle", length = 45)
	public String getChrTitle() {
		return this.chrTitle;
	}

	/**
	 * Set the users title (e.g. Dr, Mr, Mrs)
	 * 
	 * @param chrTitle The title
	 */
	public void setChrTitle(String chrTitle) {
		this.chrTitle = chrTitle;
	}

	/**
	 * Get the users first name
	 * 
	 * @return the first name
	 */
	@Column(name = "chrFirstname", length = 45)
	public String getChrFirstname() {
		return this.chrFirstname;
	}

	/**
	 * Set the users first name
	 * 
	 * @param chrFirstname The first name
	 */
	public void setChrFirstname(String chrFirstname) {
		this.chrFirstname = chrFirstname;
	}

	/**
	 * Get the users surname
	 * 
	 * @return The surname
	 */
	@Column(name = "chrSurname", length = 45)
	public String getChrSurname() {
		return this.chrSurname;
	}

	/**
	 * Set the users surname
	 * 
	 * @param chrSurname The surname
	 */
	public void setChrSurname(String chrSurname) {
		this.chrSurname = chrSurname;
	}

	/**
	 * Get the users fax number
	 * 
	 * @return The users fax number
	 */
	@Column(name = "chrFax", length = 45)
	public String getChrFax() {
		return this.chrFax;
	}

	/**
	 * Set the users fax number
	 * 
	 * @param chrFax The fax number
	 */
	public void setChrFax(String chrFax) {
		this.chrFax = chrFax;
	}

	/**
	 * Get the users phone number
	 * 
	 * @return The phone number
	 */
	@Column(name = "chrTelephone", length = 45)
	public String getChrTelephone() {
		return this.chrTelephone;
	}

	/**
	 * Set the users phone number
	 * 
	 * @param chrTelephone The phone number
	 */
	public void setChrTelephone(String chrTelephone) {
		this.chrTelephone = chrTelephone;
	}

	/**
	 * Get the users email address
	 * @return The email address
	 */
	@Column(name = "chrEmail", length = 45)
	public String getChrEmail() {
		return this.chrEmail;
	}

	/**
	 * Set the users email address
	 * 
	 * @param chrEmail The email address
	 */
	public void setChrEmail(String chrEmail) {
		this.chrEmail = chrEmail;
	}
	
	/**
	 * Get the first ANZ Field of Research code
	 * 
	 * @return The field of research
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chrFORcode1")
	@NotFound(action=NotFoundAction.IGNORE)
	public ForCodes getForCodes1() {
		return forCodes1;
	}

	/**
	 * Set the first ANZ Field of Research code
	 * 
	 * @param forCodes1 The field of research
	 */
	public void setForCodes1(ForCodes forCodes1) {
		this.forCodes1 = forCodes1;
	}

	/**
	 * Get the users first field of research percentage
	 * 
	 * @return The field of research
	 */
	@Column(name = "chrFORpercentage1", length = 45)
	public String getChrForpercentage1() {
		return this.chrForpercentage1;
	}

	/**
	 * Set the users first field of research percentage
	 * 
	 * @param chrForpercentage1 The field of research percentage
	 */
	public void setChrForpercentage1(String chrForpercentage1) {
		this.chrForpercentage1 = chrForpercentage1;
	}

	/**
	 * Get the second ANZ Field of Research code
	 * 
	 * @return The field of research
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chrFORcode2")
	@NotFound(action=NotFoundAction.IGNORE)
	public ForCodes getForCodes2() {
		return forCodes2;
	}

	/**
	 * Set the second ANZ Field of Research code
	 * @param forCodes2
	 */
	public void setForCodes2(ForCodes forCodes2) {
		this.forCodes2 = forCodes2;
	}

	/**
	 * Set the second field of research percentage
	 * 
	 * @return The field of research percentage
	 */
	@Column(name = "chrFORpercentage2", length = 45)
	public String getChrForpercentage2() {
		return this.chrForpercentage2;
	}

	/**
	 * Set the second field of research percentage
	 * 
	 * @param chrForpercentage2 The field of research percentage
	 */
	public void setChrForpercentage2(String chrForpercentage2) {
		this.chrForpercentage2 = chrForpercentage2;
	}

	/**
	 * Get the third ANZ Field of Research code
	 * 
	 * @return The field of research
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chrFORcode3")
	@NotFound(action=NotFoundAction.IGNORE)
	public ForCodes getForCodes3() {
		return forCodes3;
	}

	/**
	 * Set the third ANZ Field of Research code
	 * 
	 * @param forCodes3 The field of research
	 */
	public void setForCodes3(ForCodes forCodes3) {
		this.forCodes3 = forCodes3;
	}

	/**
	 * Get the third field of research percentage
	 * 
	 * @return The field of research percentage
	 */
	@Column(name = "chrFORpercentage3", length = 45)
	public String getChrForpercentage3() {
		return this.chrForpercentage3;
	}

	/**
	 * Set the third field of research percentage
	 * 
	 * @param chrForpercentage3 The field of research percentage
	 */
	public void setChrForpercentage3(String chrForpercentage3) {
		this.chrForpercentage3 = chrForpercentage3;
	}

	/**
	 * Get the users department code
	 * 
	 * @return The department code
	 */
	@Column(name = "chrDepartmentCode", length = 45)
	public String getChrDepartmentCode() {
		return this.chrDepartmentCode;
	}

	/**
	 * Set the users department code
	 * 
	 * @param chrDepartmentCode The department code
	 */
	public void setChrDepartmentCode(String chrDepartmentCode) {
		this.chrDepartmentCode = chrDepartmentCode;
	}

}
