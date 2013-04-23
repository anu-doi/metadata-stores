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
 * Facultyschoolcentre
 * 
 * The Australian National University
 * 
 * Entity class for the facultyschoolcentre table
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "facultyschoolcentre")
public class Facultyschoolcentre implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private FacultyschoolcentreId id;

	/**
	 * Constructor
	 */
	public Facultyschoolcentre() {
	}

	/**
	 * Constructor
	 * 
	 * @param id The id
	 */
	public Facultyschoolcentre(FacultyschoolcentreId id) {
		this.id = id;
	}

	/**
	 * Get the id
	 * 
	 * @return The id
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "chrTierCode", column = @Column(name = "chrTierCode", length = 45)),
			@AttributeOverride(name = "chrTier2name", column = @Column(name = "chrTier2Name", length = 45)),
			@AttributeOverride(name = "chrTier2code", column = @Column(name = "chrTier2Code", length = 45)),
			@AttributeOverride(name = "chrDateStatus", column = @Column(name = "chrDateStatus", length = 45)),
			@AttributeOverride(name = "chrAou", column = @Column(name = "chrAOU", length = 45)),
			@AttributeOverride(name = "chrFinanceCode", column = @Column(name = "chrFinanceCode", length = 45)),
			@AttributeOverride(name = "chrHraou", column = @Column(name = "chrHRAOU", length = 45)) })
	public FacultyschoolcentreId getId() {
		return this.id;
	}

	/**
	 * Set the id
	 * @param id The id
	 */
	public void setId(FacultyschoolcentreId id) {
		this.id = id;
	}

}
