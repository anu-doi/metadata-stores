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

package au.edu.anu.metadatastores.datamodel.harvester;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * HarvestContent
 * 
 * The Australian National University
 * 
 * Entity class for the harvest_content table
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "harvest_content")
public class HarvestContent implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long hid;
	private String identifier;
	private String system;
	private String content;

	/**
	 * Constructor
	 */
	public HarvestContent() {
	}

	/**
	 * Constructor
	 * 
	 * @param hid The harvest id
	 * @param identifier The identifier of the harvested content
	 * @param system The harvest system
	 * @param content The content that was harvested
	 */
	public HarvestContent(Long hid, String identifier, String system,
			String content) {
		this.hid = hid;
		this.identifier = identifier;
		this.system = system;
		this.content = content;
	}

	/**
	 * Get the harvest id
	 * 
	 * @return The harvest id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hid", unique = true, nullable = false)
	public Long getHid() {
		return this.hid;
	}

	/**
	 * Set the harvest id
	 * 
	 * @param hid The harvest id
	 */
	public void setHid(Long hid) {
		this.hid = hid;
	}

	/**
	 * Get the oai-pmh identifier of the harvested object
	 * 
	 * @return The identifier
	 */
	@Column(name = "identifier", nullable = false)
	public String getIdentifier() {
		return this.identifier;
	}

	/**
	 * Set the oai-pmh identifier of the harvested content
	 * 
	 * @param identifier The identifier
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * Get the system that was harvested
	 * 
	 * @return The harvested system
	 */
	@Column(name = "system", nullable = false, length = 20)
	public String getSystem() {
		return this.system;
	}

	/**
	 * Set the system that was harvested
	 * 
	 * @param system The harvested system
	 */
	public void setSystem(String system) {
		this.system = system;
	}

	/**
	 * Get the content that was harvested
	 * 
	 * @return The harvested content
	 */
	@Column(name = "content", nullable = false)
	public String getContent() {
		return this.content;
	}

	/**
	 * Set the content that was harvested
	 * 
	 * @param content The harvested content
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
