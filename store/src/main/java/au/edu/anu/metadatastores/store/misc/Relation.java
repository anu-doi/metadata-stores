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

package au.edu.anu.metadatastores.store.misc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>Relation<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Class that contains relations</p>
 * 
 * @author Genevieve Turner
 *
 */
@XmlRootElement(name="relation")
public class Relation {
	private Long iid;
	private String itemTitle;
	private String relationValue;
	private Long relatedIid;
	private String relatedItemTitle;

	/**
	 * Constructor
	 */
	public Relation() {
		
	}
	
	/**
	 * Constructor
	 * 
	 * @param iid The item id
	 * @param relationValue The relationship value
	 * @param relatedIid The related item id
	 */
	public Relation(Long iid, String relationValue, Long relatedIid) {
		this.iid = iid;
		this.relationValue = relationValue;
		this.relatedIid = relatedIid;
	}
	
	public Relation(Long iid, String itemTitle, String relationValue, Long relatedIid, String relatedItemTitle) {
		this.iid = iid;
		this.itemTitle = itemTitle;
		this.relationValue = relationValue;
		this.relatedIid = relatedIid;
		this.relatedItemTitle = relatedItemTitle;
	}
	
	/**
	 * Get the item id
	 * 
	 * @return The item id
	 */
	@XmlElement(name="iid")
	public Long getIid() {
		return iid;
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
	 * @return The item title
	 */
	@XmlElement(name="title")
	public String getItemTitle() {
		return itemTitle;
	}

	/**
	 * Set the item title
	 * 
	 * @param itemTitle The item title
	 */
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	
	/**
	 * Get the relation value
	 * 
	 * @return The relation value
	 */
	@XmlElement(name="relation-value")
	public String getRelationValue() {
		return relationValue;
	}
	
	/**
	 * Set the relation value
	 * 
	 * @param relationValue  The relation value
	 */
	public void setRelationValue(String relationValue) {
		this.relationValue = relationValue;
	}
	
	/**
	 * Get the related item id
	 * 
	 * @return The related item id
	 */
	@XmlElement(name="related-iid")
	public Long getRelatedIid() {
		return relatedIid;
	}
	
	/**
	 * Set the related item id
	 * 
	 * @param relatedIid The related item id
	 */
	public void setRelatedIid(Long relatedIid) {
		this.relatedIid = relatedIid;
	}

	/**
	 * Get the related item title
	 * 
	 * @return The related item title
	 */
	@XmlElement(name="related-title")
	public String getRelatedItemTitle() {
		return relatedItemTitle;
	}

	/**
	 * Set the related item title
	 * 
	 * @param relatedItemTitle The related item title
	 */
	public void setRelatedItemTitle(String relatedItemTitle) {
		this.relatedItemTitle = relatedItemTitle;
	}
	
	/**
	 * Override
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (!(other instanceof Relation)) {
			return false;
		}
		Relation castOther = (Relation) other;
		
		return (
				(this.getIid() == castOther.getIid() || (this.getIid() != null && this.getIid().equals(castOther.getIid())))
				&& (this.getRelatedIid() == castOther.getRelatedIid() || (this.getRelatedIid() != null && (this.getRelatedIid().equals(castOther.getRelatedIid()))))
				&& (this.getRelationValue() == castOther.getRelationValue() || (this.getRelationValue() != null && this.getRelationValue().equals(castOther.getRelationValue())))
				&& (this.getItemTitle() == castOther.getItemTitle() || (this.getItemTitle() != null && this.getItemTitle().equals(castOther.getItemTitle())))
				&& (this.getRelatedItemTitle() == castOther.getRelatedItemTitle() || (this.getRelatedItemTitle() != null && this.getRelatedItemTitle().equals(castOther.getRelatedItemTitle())))
				);
	}
	
	public int hashCode() {
		int result = 17;
		
		result = 37 * result + (int) this.getIid().intValue();
		result = 37 + result + (int) this.getRelatedIid().intValue();
		result = 37 + result + (getRelationValue() == null ? 0 : this.getRelationValue().hashCode());
		result = 37 + result + (getItemTitle() == null ? 0 : this.getItemTitle().hashCode());
		result = 37 + result + (getRelatedItemTitle() == null ? 0 : this.getRelatedItemTitle().hashCode());
		
		return result;
	}
}
