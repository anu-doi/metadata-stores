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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * HistItemAttribute
 * 
 * The Australian National University
 * 
 * Entity class for the hist_item_attribute table.  The History Item Attribute table attempts to keep track of changes that have occurred
 * on a given item.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="hist_item_attribute")
public class HistItemAttribute {
	private HistItemAttributeId id;
	private Item item;
	private Long parentAid;
	private String attrType;
	private String attrValue;
	private Boolean userUpdated;
	private Date lastModified;
	
	/**
	 * Constructor
	 */
	public HistItemAttribute() {
		
	}
	
	/**
	 * Constructor
	 * 
	 * @param attr The ItemAttrbute to create a history row for
	 * @param histDate The date of the history row
	 */
	public HistItemAttribute(ItemAttribute attr, Date histDate) {
		HistItemAttributeId id = new HistItemAttributeId(attr.getAid(), histDate);
		this.id = id;
		this.item = attr.getItem();
		if (attr.getItemAttribute() != null) {
			this.parentAid = attr.getItemAttribute().getAid();
		}
		this.attrType = attr.getAttrType();
		this.attrValue = attr.getAttrValue();
		this.userUpdated = attr.getUserUpdated();
		this.lastModified = attr.getLastModified();
	}

	/**
	 * Get the id
	 * 
	 * @return The id
	 */
	@Id
	public HistItemAttributeId getId() {
		return id;
	}
	
	/**
	 * Set the id
	 * @param id The id
	 */
	public void setId(HistItemAttributeId id) {
		this.id = id;
	}

	/**
	 * Get the item
	 * 
	 * @return The item
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iid", nullable = false)
	public Item getItem() {
		return item;
	}
	
	/**
	 * Set the item
	 * @param item The item
	 */
	public void setItem(Item item) {
		this.item = item;
	}
	
	/**
	 * The parent item attribute id
	 * 
	 * @return The item attribute id
	 */
	@Column(name="parent_aid")
	public Long getParentAid() {
		return parentAid;
	}
	
	/**
	 * Set the parent item attribute id
	 * 
	 * @param parentAid The item attribute id
	 */
	public void setParentAid(Long parentAid) {
		this.parentAid = parentAid;
	}

	/**
	 * Get the attribute type
	 * 
	 * @return The attribute type
	 */
	@Column(name = "attr_type", nullable = false)
	public String getAttrType() {
		return attrType;
	}
	
	/**
	 * Set the attribute type
	 * 
	 * @param attrType The attribute type
	 */
	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	/**
	 * Get the attribute value
	 * 
	 * @return The attribute value
	 */
	@Column(name = "attr_value", nullable = false)
	public String getAttrValue() {
		return attrValue;
	}
	
	/**
	 * Set the attribute value
	 * 
	 * @param attrValue The attribute value
	 */
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	/**
	 * Get the indicator for whether the attribute is user updated
	 * 
	 * @return Indicator of whether hte attribute is user updated
	 */
	@Column(name = "user_updated")
	public Boolean getUserUpdated() {
		return userUpdated;
	}
	
	/**
	 * Set the indicator for whether the attribute is user updated
	 * 
	 * @param userUpdated The attribute for whether the attribute is user updated
	 */
	public void setUserUpdated(Boolean userUpdated) {
		this.userUpdated = userUpdated;
	}

	/**
	 * Get the last modified date
	 * 
	 * @return The last modified date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified", length = 35)
	public Date getLastModified() {
		return lastModified;
	}
	
	/**
	 * Set the last modified date
	 * 
	 * @param lastModified The last modified date
	 */
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
}
