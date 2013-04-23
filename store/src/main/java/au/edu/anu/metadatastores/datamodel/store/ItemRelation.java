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

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ItemRelation
 * 
 * The Australian National University
 * 
 * Entity class for the item_relation table
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "item_relation", schema = "public")
public class ItemRelation implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private ItemRelationId id;
	private Item itemByIid;
	private Item itemByRelatedIid;
	private Boolean userUpdated;

	/**
	 * Constructor
	 */
	public ItemRelation() {
	}

	/**
	 * Constructor
	 * 
	 * @param id The item relation id
	 * @param itemByIid The item
	 * @param itemByRelatedIid The related item
	 */
	public ItemRelation(ItemRelationId id, Item itemByIid, Item itemByRelatedIid) {
		this.id = id;
		this.itemByIid = itemByIid;
		this.itemByRelatedIid = itemByRelatedIid;
	}

	/**
	 * Constructor
	 * 
	 * @param id The item relation id
	 * @param itemByIid The item
	 * @param itemByRelatedIid The item relation
	 * @param userUpdated Indicator of whether it is user updated
	 */
	public ItemRelation(ItemRelationId id, Item itemByIid,
			Item itemByRelatedIid, Boolean userUpdated) {
		this.id = id;
		this.itemByIid = itemByIid;
		this.itemByRelatedIid = itemByRelatedIid;
		this.userUpdated = userUpdated;
	}

	/**
	 * Get the relation id
	 * 
	 * @return The id
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "iid", column = @Column(name = "iid", nullable = false)),
			@AttributeOverride(name = "relationValue", column = @Column(name = "relation_value", nullable = false)),
			@AttributeOverride(name = "relatedIid", column = @Column(name = "related_iid", nullable = false)) })
	public ItemRelationId getId() {
		return this.id;
	}

	/**
	 * Set the relation id
	 * 
	 * @param id The id
	 */
	public void setId(ItemRelationId id) {
		this.id = id;
	}

	/**
	 * Get the item
	 * 
	 * @return The item
	 */
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "iid", nullable = false, insertable = false, updatable = false)
	public Item getItemByIid() {
		return this.itemByIid;
	}

	/**
	 * Set the item
	 * 
	 * @param itemByIid The item
	 */
	public void setItemByIid(Item itemByIid) {
		this.itemByIid = itemByIid;
	}

	/**
	 * Get the related item
	 * 
	 * @return The related item
	 */
	@ManyToOne
	@JoinColumn(name = "related_iid", nullable = false, insertable = false, updatable = false)
	public Item getItemByRelatedIid() {
		return this.itemByRelatedIid;
	}

	/**
	 * Set the related item
	 * 
	 * @param itemByRelatedIid The related item
	 */
	public void setItemByRelatedIid(Item itemByRelatedIid) {
		this.itemByRelatedIid = itemByRelatedIid;
	}

	/**
	 * Get the indicator of whether it is user updated
	 * 
	 * @return Whether it is user updated
	 */
	@Column(name = "user_updated")
	public Boolean getUserUpdated() {
		return this.userUpdated;
	}

	/**
	 * Set the indicator of whether it is user updated
	 * 
	 * @param userUpdated Whether it is user updated
	 */
	public void setUserUpdated(Boolean userUpdated) {
		this.userUpdated = userUpdated;
	}

}
