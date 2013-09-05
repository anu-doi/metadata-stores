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
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>SystemType<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p></p>
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="system_types")
public class SystemType {
	private String extSystem;
	private String title;
	private String description;
	
	/**
	 * Get the ext system code
	 * 
	 * @return The ext system code
	 */
	@Id
	@Column(name="ext_system")
	public String getExtSystem() {
		return extSystem;
	}

	/**
	 * Set the system code
	 * 
	 * @param extSystem The system code
	 */
	public void setExtSystem(String extSystem) {
		this.extSystem = extSystem;
	}

	/**
	 * Get the title for the system
	 * 
	 * @return The title of the system
	 */
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	
	/**
	 * Set the title for the ext system
	 * 
	 * @param title The title of the system
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Get the system description
	 * 
	 * @return The description
	 */
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set the system description
	 * 
	 * @param description The description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
