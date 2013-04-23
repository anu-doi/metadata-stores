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

package au.edu.anu.metadatastores.datamodel.store;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RelationMapping
 * 
 * The Australian National University
 * 
 * Entity class for the relation_mapping table
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="relation_mapping")
public class RelationMapping {
	private Long id;
	private String code;
	private String description;
	private String reverse;
	
	/**
	 * Get the id
	 * 
	 * @return The id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	/**
	 * Set the id
	 * 
	 * @param id The id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Get the relation code
	 * 
	 * @return The code
	 */
	@Column(name="code")
	public String getCode() {
		return code;
	}
	
	/**
	 * Set the relation code
	 * 
	 * @param code The code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Set the relation description
	 * 
	 * @return The description
	 */
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set the relation description
	 * 
	 * @param description THe description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the reverse relation code
	 * 
	 * @return The reverse relation code
	 */
	@Column(name="reverse")
	public String getReverse() {
		return reverse;
	}
	
	/**
	 * Set the reverse relation code
	 * 
	 * @param reverse The reverse relation code
	 */
	public void setReverse(String reverse) {
		this.reverse = reverse;
	}
}
