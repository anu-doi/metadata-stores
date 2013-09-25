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
package au.edu.anu.metadatastores.store.search;

/**
 * <p>ItemDTO<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p></p>
 * 
 * @author Genevieve Turner
 *
 */
public class ItemDTO {
	private Long id;
	private String title;
	private String extSystem;
	private String extId;
	
	/**
	 * Constructor
	 */
	public ItemDTO() {
		
	}
	
	/**
	 * Constructor 
	 * 
	 * @param id The id of the item
	 * @param title The title of the item
	 * @param extSystem The external system of the item
	 * @param extId The external id of the item
	 */
	public ItemDTO(Long id, String title, String extSystem, String extId) {
		this.id = id;
		this.title = title;
		this.extSystem = extSystem;
		this.extId = extId;
	}
	
	/**
	 * Get the id
	 * 
	 * @return The id
	 */
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
	 * Get the title
	 * 
	 * @return The title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title
	 * 
	 * @param title The title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the external system
	 * 
	 * @return The external system
	 */
	public String getExtSystem() {
		return extSystem;
	}

	/**
	 * Set the external system
	 * 
	 * @param extSystem The external system
	 */
	public void setExtSystem(String extSystem) {
		this.extSystem = extSystem;
	}

	/**
	 * Get the external id
	 * 
	 * @return The external id
	 */
	public String getExtId() {
		return extId;
	}

	/**
	 * Set the external id
	 * 
	 * @param extId The external id
	 */
	public void setExtId(String extId) {
		this.extId = extId;
	}
	
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ItemDTO)) {
			return false;
		}
		ItemDTO other = (ItemDTO) obj;
		return (
				((this.getId() == other.getId()) || (this.getId() != null && this.getId().equals(other.getId()))) &&
				((this.getExtId() == other.getExtId()) || (this.getExtId() != null && this.getExtId().equals(other.getExtId()))) &&
				((this.getExtSystem() == other.getExtSystem()) || (this.getExtSystem() != null && this.getExtSystem().equals(other.getExtSystem()))) &&
				((this.getTitle() == other.getTitle()) || (this.getTitle() != null && this.getTitle().equals(other.getTitle())))
				);
	}
	
	public int hashCode() {
		int hashCode = 0;
		hashCode = hashCode + 17 * id.hashCode();
		hashCode = hashCode + 17 * title.hashCode();
		hashCode = hashCode + 17 * extSystem.hashCode();
		hashCode = hashCode + 17 * extId.hashCode();
		
		return hashCode;
	}
}
