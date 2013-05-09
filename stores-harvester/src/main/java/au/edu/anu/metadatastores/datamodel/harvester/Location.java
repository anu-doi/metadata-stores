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

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Location
 * 
 * The Australian National University
 * 
 * 
 * 
 * @author Genevieve Turner
 *
 */
/**
 * <p>Location<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Entity class for the <i>location</i> table</p>
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "location")
public class Location implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Long lid;
	private String system;
	private String url;
	private String metadataPrefix;
	private Date lastHarvestDate;
	private Long harvestFreq;
	private Date origHarvestDate;

	/**
	 * Constructor
	 */
	public Location() {
	}

	/**
	 * Constructor
	 * 
	 * @param lid The location id
	 * @param system The system
	 * @param url The url of the system
	 * @param metadataPrefix The metadata prefix
	 */
	public Location(Long lid, String system, String url, String metadataPrefix) {
		this.lid = lid;
		this.system = system;
		this.url = url;
		this.metadataPrefix = metadataPrefix;
	}

	/**
	 * Constructor
	 * 
	 * @param lid The location id
	 * @param system The system
	 * @param url The url of the system
	 * @param metadataPrefix The metadata prefix
	 * @param lastHarvestDate The harvested date
	 * @param harvestFreq The harvest frequency
	 * @param origHarvestDate The original harvest date
	 */
	public Location(Long lid, String system, String url, String metadataPrefix,
			Date lastHarvestDate, Long harvestFreq, Date origHarvestDate) {
		this.lid = lid;
		this.system = system;
		this.url = url;
		this.metadataPrefix = metadataPrefix;
		this.lastHarvestDate = lastHarvestDate;
		this.harvestFreq = harvestFreq;
		this.origHarvestDate = origHarvestDate;
	}

	/**
	 * Get the location id
	 *  
	 * @return The location id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lid", unique = true, nullable = false)
	public Long getLid() {
		return this.lid;
	}

	/**
	 * Set the location id
	 * 
	 * @param lid the location id
	 */
	public void setLid(Long lid) {
		this.lid = lid;
	}

	/**
	 * Get the location system
	 * 
	 * @return The system
	 */
	@Column(name = "system", nullable = false, length = 20)
	public String getSystem() {
		return this.system;
	}

	/**
	 * Set the location system
	 * 
	 * @param system The system
	 */
	public void setSystem(String system) {
		this.system = system;
	}

	/**
	 * Get the location url
	 * 
	 * @return The url
	 */
	@Column(name = "url", nullable = false)
	public String getUrl() {
		return this.url;
	}

	/**
	 * Set the location url
	 * 
	 * @param url The url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Get the metadata prefix
	 * 
	 * @return The metadata prefix
	 */
	@Column(name = "metadata_prefix", nullable = false)
	public String getMetadataPrefix() {
		return this.metadataPrefix;
	}

	/**
	 * Set the metadata prefix
	 * 
	 * @param metadataPrefix The metadata prefix
	 */
	public void setMetadataPrefix(String metadataPrefix) {
		this.metadataPrefix = metadataPrefix;
	}

	/**
	 * Get the last harvested date
	 * 
	 * @return The last harvested date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_harvest_date", length = 35)
	public Date getLastHarvestDate() {
		return this.lastHarvestDate;
	}

	/**
	 * Set the last harvested date
	 * 
	 * @param lastHarvestDate The last harvested date
	 */
	public void setLastHarvestDate(Date lastHarvestDate) {
		this.lastHarvestDate = lastHarvestDate;
	}

	/**
	 * Get the harvest frequency
	 * 
	 * @return The harvest frequency in milliseconds
	 */
	@Column(name = "harvest_freq")
	public Long getHarvestFreq() {
		return this.harvestFreq;
	}

	/**
	 * Set the harvest frequency
	 * 
	 * @param harvestFreq The harvest frequency in milliseconds
	 */
	public void setHarvestFreq(Long harvestFreq) {
		this.harvestFreq = harvestFreq;
	}

	/**
	 * Get the original harvest date
	 * 
	 * @return The harvest date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "orig_harvest_date", length = 35)
	public Date getOrigHarvestDate() {
		return this.origHarvestDate;
	}

	/**
	 * Set the original harvest date
	 * 
	 * @param origHarvestDate The harvest date
	 */
	public void setOrigHarvestDate(Date origHarvestDate) {
		this.origHarvestDate = origHarvestDate;
	}

}
