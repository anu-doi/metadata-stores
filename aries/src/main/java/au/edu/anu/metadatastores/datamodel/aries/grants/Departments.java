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

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>Departments<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Entity class for the <i>departments</i> table</p>
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "departments")
public class Departments implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private DepartmentsId id;
	private String chrSchoolCode;

	/**
	 * Constructor
	 */
	public Departments() {
	}

	/**
	 * Constructor
	 * 
	 * @param id The department id
	 */
	public Departments(DepartmentsId id) {
		this.id = id;
	}

	/**
	 * Constructor
	 * 
	 * @param id The department id
	 * @param chrSchoolCode The department school code
	 */
	public Departments(DepartmentsId id, String chrSchoolCode) {
		this.id = id;
		this.chrSchoolCode = chrSchoolCode;
	}

	/**
	 * Get the id
	 * 
	 * @return The id
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "chrTier3name", column = @Column(name = "chrTier3Name", nullable = false, length = 55)),
			@AttributeOverride(name = "chrTier1code", column = @Column(name = "chrTier1Code", nullable = false, length = 45)),
			@AttributeOverride(name = "chrTier2code", column = @Column(name = "chrTier2Code", nullable = false, length = 55)),
			@AttributeOverride(name = "chrTier3code", column = @Column(name = "chrTier3Code", nullable = false, length = 45)),
			@AttributeOverride(name = "chrCollegeCode", column = @Column(name = "chrCollegeCode", nullable = false, length = 45)) })
	public DepartmentsId getId() {
		return this.id;
	}

	/**
	 * Set the id
	 * 
	 * @param id The id
	 */
	public void setId(DepartmentsId id) {
		this.id = id;
	}

	/**
	 * Get the school code
	 * 
	 * @return The school code
	 */
	@Column(name = "chrSchoolCode", length = 45)
	public String getChrSchoolCode() {
		return this.chrSchoolCode;
	}

	/**
	 * Set the school code
	 * 
	 * @param chrSchoolCode The school code
	 */
	public void setChrSchoolCode(String chrSchoolCode) {
		this.chrSchoolCode = chrSchoolCode;
	}

}
