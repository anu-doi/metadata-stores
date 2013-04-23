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
import javax.persistence.Embeddable;

/**
 * PotentialRelationId
 * 
 * The Australian National University
 * 
 * Id class for PotentialRelation
 * 
 * @author Genevieve Turner
 *
 */
@Embeddable
public class PotentialRelationId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Long iid;
	private String relationValue;
	private Long relatedIid;

	/**
	 * Constructor
	 */
	public PotentialRelationId() {
	}

	/**
	 * Constructor
	 * 
	 * @param iid The id of the item
	 * @param relationValue The relationship type
	 * @param relatedIid The related item
	 */
	public PotentialRelationId(Long iid, String relationValue, Long relatedIid) {
		this.iid = iid;
		this.relationValue = relationValue;
		this.relatedIid = relatedIid;
	}

	/**
	 * Get the item id
	 * 
	 * @return The item id
	 */
	@Column(name = "iid", nullable = false)
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
	 * Get the relationship type
	 * 
	 * @return The relationship type
	 */
	@Column(name = "relation_value", nullable = false)
	public String getRelationValue() {
		return this.relationValue;
	}

	/**
	 * Set the relationship type
	 * 
	 * @param relationValue The relationship type
	 */
	public void setRelationValue(String relationValue) {
		this.relationValue = relationValue;
	}

	/**
	 * Get the related item id
	 * 
	 * @return The related item id
	 */
	@Column(name = "related_iid", nullable = false)
	public Long getRelatedIid() {
		return this.relatedIid;
	}

	/**
	 * Set the related item id
	 * 
	 * @param relatedIid The related item id
	 */
	public void setRelatedIid(Long relatedIid) {
		this.relatedIid = relatedIid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PotentialRelationId))
			return false;
		PotentialRelationId castOther = (PotentialRelationId) other;

		return (this.getIid().equals(castOther.getIid()))
				&& ((this.getRelationValue() == castOther.getRelationValue()) || (this
						.getRelationValue() != null
						&& castOther.getRelationValue() != null && this
						.getRelationValue()
						.equals(castOther.getRelationValue())))
				&& (this.getRelatedIid().equals(castOther.getRelatedIid()));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getIid().intValue();
		result = 37
				* result
				+ (getRelationValue() == null ? 0 : this.getRelationValue()
						.hashCode());
		result = 37 * result + (int) this.getRelatedIid().intValue();
		return result;
	}

}
