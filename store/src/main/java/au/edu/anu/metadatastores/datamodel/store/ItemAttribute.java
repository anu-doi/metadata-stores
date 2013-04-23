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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * ItemAttribute
 * 
 * The Australian National University
 * 
 * Entity class for the item_attribute table
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "item_attribute", schema = "public")
public class ItemAttribute implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long aid;
	private Item item;
	private ItemAttribute itemAttribute;
	private String attrType;
	private String attrValue;
	private Boolean userUpdated;
	private Date lastModified;
	private Set<ItemAttribute> itemAttributes = new HashSet<ItemAttribute>(0);

	/**
	 * Constructor
	 */
	public ItemAttribute() {
	}
	
	/**
	 * Constructor
	 * 
	 * @param item The item associated with the attribute
	 * @param attrType The attribute type
	 * @param attrValue The attribute value
	 * @param lastModified The last modified date
	 */
	public ItemAttribute(Item item, String attrType, String attrValue, Date lastModified) {
		this.item = item;
		this.attrType = attrType;
		this.attrValue = attrValue;
		this.lastModified = lastModified;
	}

	/**
	 * Constructor
	 * 
	 * @param aid The attribute id
	 * @param item The item associated with the attribute
	 * @param attrType The attribute date
	 * @param attrValue The attribute value
	 */
	public ItemAttribute(Long aid, Item item, String attrType, String attrValue) {
		this.aid = aid;
		this.item = item;
		this.attrType = attrType;
		this.attrValue = attrValue;
	}

	/**
	 * Constructor
	 * 
	 * @param aid The attribute id
	 * @param item The item associated with the attribute
	 * @param itemAttribute The parent item attribute
	 * @param attrType The attribute type
	 * @param attrValue The attribute value
	 * @param userUpdated Indicator of whether it is user updated
	 * @param lastModified The last modified date
	 * @param itemAttributes Child item attributes
	 */
	public ItemAttribute(Long aid, Item item, ItemAttribute itemAttribute,
			String attrType, String attrValue, Boolean userUpdated,
			Date lastModified, Set<ItemAttribute> itemAttributes) {
		this.aid = aid;
		this.item = item;
		this.itemAttribute = itemAttribute;
		this.attrType = attrType;
		this.attrValue = attrValue;
		this.userUpdated = userUpdated;
		this.lastModified = lastModified;
		this.itemAttributes = itemAttributes;
	}

	/**
	 * Get the attribute id
	 * 
	 * @return The attribute id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "aid", unique = true, nullable = false)
	public Long getAid() {
		return this.aid;
	}

	/**
	 * Set the attribute id
	 * 
	 * @param aid The attribute id
	 */
	public void setAid(Long aid) {
		this.aid = aid;
	}

	/**
	 * Get the associated item
	 * 
	 * @return The item
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iid", nullable = false)
	public Item getItem() {
		return this.item;
	}

	/**
	 * Set the associated item
	 * 
	 * @param item The item
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * Get the parent attribute
	 * 
	 * @return The parent attribute
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_aid")
	public ItemAttribute getItemAttribute() {
		return this.itemAttribute;
	}

	/**
	 * Set the parent attribute
	 * 
	 * @param itemAttribute The parent attribute
	 */
	public void setItemAttribute(ItemAttribute itemAttribute) {
		this.itemAttribute = itemAttribute;
	}

	/**
	 * Get the attribute type
	 * 
	 * @return The type
	 */
	@Column(name = "attr_type", nullable = false, length = 40)
	public String getAttrType() {
		return this.attrType;
	}

	/**
	 * Set the attribute type
	 * 
	 * @param attrType The type
	 */
	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	/**
	 * Get the attribute value
	 * 
	 * @return The value
	 */
	@Column(name = "attr_value", nullable = false)
	public String getAttrValue() {
		return this.attrValue;
	}

	/**
	 * Set the attribute value
	 * 
	 * @param attrValue The value
	 */
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	/**
	 * Get the indicator of whether the attribute is user updated
	 * 
	 * @return Whether it is user updated
	 */
	@Column(name = "user_updated")
	public Boolean getUserUpdated() {
		return this.userUpdated;
	}

	/**
	 * Set the indicator of whether the attribute is user updated
	 * 
	 * @param userUpdated Whether it is user updated
	 */
	public void setUserUpdated(Boolean userUpdated) {
		this.userUpdated = userUpdated;
	}

	/**
	 * Get the last modified date
	 * 
	 * @return  The last modified date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified", length = 35)
	public Date getLastModified() {
		return this.lastModified;
	}

	/**
	 * Set the last modified date
	 * 
	 * @param lastModified The last modified date
	 */
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	/**
	 * Get the item attributes
	 * 
	 * @return The item attributes
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "itemAttribute")
	@Cascade(CascadeType.ALL)
	public Set<ItemAttribute> getItemAttributes() {
		return this.itemAttributes;
	}

	/**
	 * Set the item attributes
	 * 
	 * @param itemAttributes The item attributes
	 */
	public void setItemAttributes(Set<ItemAttribute> itemAttributes) {
		this.itemAttributes = itemAttributes;
	}

}
