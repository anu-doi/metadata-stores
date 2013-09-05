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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * <p>Item<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Entity class for the <i>item</i> table.  The item table provides the root information about an object.</p>
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "item", uniqueConstraints = @UniqueConstraint(columnNames = {
		"ext_system", "ext_id" }))
@DiscriminatorColumn(name="ext_system")
@FilterDef(name="attributes")
@FetchProfile(name = "item-with-attributes", fetchOverrides = {
		@FetchProfile.FetchOverride(entity = Item.class, association = "itemAttributes", mode = FetchMode.JOIN)	
	})
public class Item implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Long iid;
	private String title;
	private String extSystem;
	private String extId;
	private Boolean deleted;
	private Set<ItemRelation> itemRelationsForIid = new HashSet<ItemRelation>(0);
	private Set<ItemRelation> itemRelationsForRelatedIid = new HashSet<ItemRelation>(0);
	private Set<PotentialRelation> potentialRelationsForRelatedIid = new HashSet<PotentialRelation>(0);
	private Set<PotentialRelation> potentialRelationsForIid = new HashSet<PotentialRelation>(0);
	private Set<ItemAttribute> itemAttributes = new HashSet<ItemAttribute>(0);
	private Set<HistItemAttribute> histItemAttributes = new HashSet<HistItemAttribute>(0);

	/**
	 * Constructor
	 */
	public Item() {
	}

	/**
	 * Constructor
	 * 
	 * @param iid The id of the item
	 * @param extSystem The system from which the item was taken
	 * @param extId The external id
	 */
	public Item(Long iid, String extSystem, String extId) {
		this.iid = iid;
		this.extSystem = extSystem;
		this.extId = extId;
	}

	/**
	 * Constructor 
	 * @param iid The id of the item
	 * @param title The title of the item
	 * @param extSystem The system from which the item was taken
	 * @param extId The external id
	 * @param deleted Whether the item is of deleted status
	 * @param itemRelationsForIid The relations associated with the id
	 * @param itemRelationsForRelatedIid The reverse relations associated with the id
	 * @param potentialRelationsForRelatedIid The reverse potential relations
	 * @param potentialRelationsForIid The potential relations
	 * @param itemAttributes The attributes of the item
	 */
	public Item(Long iid, String title, String extSystem, String extId,
			Boolean deleted, Set<ItemRelation> itemRelationsForIid,
			Set<ItemRelation> itemRelationsForRelatedIid,
			Set<PotentialRelation> potentialRelationsForRelatedIid, Set<PotentialRelation> potentialRelationsForIid,
			Set<ItemAttribute> itemAttributes) {
		this.iid = iid;
		this.title = title;
		this.extSystem = extSystem;
		this.extId = extId;
		this.deleted = deleted;
		this.itemRelationsForIid = itemRelationsForIid;
		this.itemRelationsForRelatedIid = itemRelationsForRelatedIid;
		this.potentialRelationsForRelatedIid = potentialRelationsForRelatedIid;
		this.potentialRelationsForIid = potentialRelationsForIid;
		this.itemAttributes = itemAttributes;
	}

	/**
	 * Get the item id
	 * 
	 * @return The item id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iid", unique = true, nullable = false)
	public Long getIid() {
		return this.iid;
	}

	/**
	 * Set the item id
	 * 
	 * @param iid The item id
	 */
	public void setIid(Long iid) {
		this.iid = iid;
	}

	/**
	 * Get the item title
	 * 
	 * @return The title
	 */
	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	/**
	 * Set the item title
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
	@Column(name = "ext_system", nullable = false, length = 50, insertable=false, updatable=false)
	public String getExtSystem() {
		return this.extSystem;
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
	@Column(name = "ext_id", nullable = false)
	public String getExtId() {
		return this.extId;
	}

	/**
	 * Set the external id
	 * 
	 * @param extId The external id
	 */
	public void setExtId(String extId) {
		this.extId = extId;
	}

	/**
	 * Get indicator for if the item is of the deleted status
	 * 
	 * @return true if deleted
	 */
	@Column(name = "deleted")
	public Boolean getDeleted() {
		return this.deleted;
	}

	/**
	 * Set the indicator if the item is of the deleted status
	 * 
	 * @param deleted true if deleted
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * Get the item relations
	 * 
	 * return The item relations
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "itemByIid")
	@Cascade(CascadeType.ALL)
	@NotFound(action=NotFoundAction.IGNORE)
	public Set<ItemRelation> getItemRelationsForIid() {
		return this.itemRelationsForIid;
	}

	/**
	 * Set the item relations
	 * 
	 * @param itemRelationsForIid The item relations
	 */
	public void setItemRelationsForIid(Set<ItemRelation> itemRelationsForIid) {
		this.itemRelationsForIid = itemRelationsForIid;
	}

	/**
	 * Get the relationships that are held on the related item
	 * 
	 * @return The item reverse relations
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "itemByRelatedIid")
	@Cascade(CascadeType.ALL)
	@NotFound(action=NotFoundAction.IGNORE)
	public Set<ItemRelation> getItemRelationsForRelatedIid() {
		return this.itemRelationsForRelatedIid;
	}

	/**
	 * Set the relationships that are held on the related item
	 * 
	 * @param itemRelationsForRelatedIid The item reverse relations
	 */
	public void setItemRelationsForRelatedIid(Set<ItemRelation> itemRelationsForRelatedIid) {
		this.itemRelationsForRelatedIid = itemRelationsForRelatedIid;
	}

	/**
	 * Get the potential relations that are held on the related item
	 * 
	 * @return The potential relations for the item
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "itemByRelatedIid")
	@Cascade(CascadeType.ALL)
	@NotFound(action=NotFoundAction.IGNORE)
	public Set<PotentialRelation> getPotentialRelationsForRelatedIid() {
		return this.potentialRelationsForRelatedIid;
	}

	/**
	 * Set the potential relations that are held on the related item
	 * 
	 * @param potentialRelationsForRelatedIid THe potential relations for the item
	 */
	public void setPotentialRelationsForRelatedIid(
			Set<PotentialRelation> potentialRelationsForRelatedIid) {
		this.potentialRelationsForRelatedIid = potentialRelationsForRelatedIid;
	}

	/**
	 * Get the potential relations for the item
	 * 
	 * @return The potential relations
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "itemByIid")
	@Cascade(CascadeType.ALL)
	@NotFound(action=NotFoundAction.IGNORE)
	public Set<PotentialRelation> getPotentialRelationsForIid() {
		return this.potentialRelationsForIid;
	}

	/**
	 * Set the potential relations for the item
	 * 
	 * @param potentialRelationsForIid The potential relations
	 */
	public void setPotentialRelationsForIid(Set<PotentialRelation> potentialRelationsForIid) {
		this.potentialRelationsForIid = potentialRelationsForIid;
	}

	/**
	 * Get the item attributes
	 * 
	 * @return The item attributes
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Cascade(CascadeType.ALL)
	@NotFound(action=NotFoundAction.IGNORE)
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

	/**
	 * Get the history item attributes
	 * 
	 * @return The history item attributes
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Cascade(CascadeType.ALL)
	@NotFound(action=NotFoundAction.IGNORE)
	public Set<HistItemAttribute> getHistItemAttributes() {
		return histItemAttributes;
	}

	/**
	 * Set the history item attributes
	 * 
	 * @param histItemAttributes The history item attributes
	 */
	public void setHistItemAttributes(Set<HistItemAttribute> histItemAttributes) {
		this.histItemAttributes = histItemAttributes;
	}

}
