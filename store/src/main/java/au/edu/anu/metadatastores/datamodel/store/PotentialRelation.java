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
 * PotentialRelation
 * 
 * The Australian National University
 * 
 * Entity class for the potential_relation table
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "potential_relation", schema = "public")
public class PotentialRelation implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private PotentialRelationId id;
	private Item itemByIid;
	private Item itemByRelatedIid;
	private Boolean requireCheck;
	private Boolean isLink;

	/**
	 * Constructor
	 */
	public PotentialRelation() {
	}

	/**
	 * Constructor
	 * 
	 * @param id The id of the potential relation
	 * @param itemByIid The item
	 * @param itemByRelatedIid The related item
	 * @param requireCheck Indicator for whether a check is still required
	 */
	public PotentialRelation(PotentialRelationId id, Item itemByIid,
			Item itemByRelatedIid, boolean requireCheck) {
		this.id = id;
		this.itemByIid = itemByIid;
		this.itemByRelatedIid = itemByRelatedIid;
		this.requireCheck = requireCheck;
	}

	/**
	 * Constructor
	 * 
	 * @param id The id of the potential relation
	 * @param itemByIid The item
	 * @param itemByRelatedIid The related item
	 * @param requireCheck Indicator for whether a check is still required
	 * @param isLink Indicates whether the item is a link or not
	 */
	public PotentialRelation(PotentialRelationId id, Item itemByIid,
			Item itemByRelatedIid, boolean requireCheck, Boolean isLink) {
		this.id = id;
		this.itemByIid = itemByIid;
		this.itemByRelatedIid = itemByRelatedIid;
		this.requireCheck = requireCheck;
		this.isLink = isLink;
	}

	/**
	 * Get the id
	 * 
	 * @return the id
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "iid", column = @Column(name = "iid", nullable = false)),
			@AttributeOverride(name = "relationValue", column = @Column(name = "relation_value", nullable = false)),
			@AttributeOverride(name = "relatedIid", column = @Column(name = "related_iid", nullable = false)) })
	public PotentialRelationId getId() {
		return this.id;
	}

	/**
	 * Set the id
	 * 
	 * @param id The id
	 */
	public void setId(PotentialRelationId id) {
		this.id = id;
	}

	/**
	 * Get the item
	 * 
	 * @return  The item
	 */
	@ManyToOne(fetch = FetchType.LAZY)
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "related_iid", nullable = false, insertable = false, updatable = false)
	public Item getItemByRelatedIid() {
		return this.itemByRelatedIid;
	}

	/**
	 * Set the item id
	 * 
	 * @param itemByRelatedIid The related item
	 */
	public void setItemByRelatedIid(Item itemByRelatedIid) {
		this.itemByRelatedIid = itemByRelatedIid;
	}

	/**
	 * Get whether verification of whether the item is related or not is still required
	 * 
	 * @return Whether verfication is required
	 */
	@Column(name = "require_check", nullable = false)
	public Boolean getRequireCheck() {
		return this.requireCheck;
	}

	/**
	 * Set whether verification of whether the item is related or not is still required
	 * 
	 * @param requireCheck Whether verification is required
	 */
	public void setRequireCheck(Boolean requireCheck) {
		this.requireCheck = requireCheck;
	}

	/**
	 * Get whether it is a relation or not
	 * 
	 * @return Indicator of whether the items are related
	 */
	@Column(name = "is_link")
	public Boolean getIslink() {
		return this.isLink;
	}

	/**
	 * Set whether it is a relation or not
	 * 
	 * @param isLink Indicator of whether the items are related
	 */
	public void setIslink(Boolean isLink) {
		this.isLink = isLink;
	}

}
