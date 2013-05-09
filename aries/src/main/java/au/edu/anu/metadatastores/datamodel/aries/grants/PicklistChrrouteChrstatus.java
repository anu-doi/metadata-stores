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

package au.edu.anu.metadatastores.datamodel.aries.grants;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * <p>PicklistChrrouteChrstatus<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Entity class for <i>picklist_chrroute_chrstatus</i> table</p>
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "picklist_chrroute_chrstatus")
public class PicklistChrrouteChrstatus implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private PicklistChrrouteChrstatusId id;

	/**
	 * Constructor
	 */
	public PicklistChrrouteChrstatus() {
	}

	/**
	 * Constructor
	 * 
	 * @param id The id
	 */
	public PicklistChrrouteChrstatus(PicklistChrrouteChrstatusId id) {
		this.id = id;
	}

	/**
	 * Get the id
	 * 
	 * @return the id
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "chrRoute", column = @Column(name = "chrRoute", length = 45)),
			@AttributeOverride(name = "chrStatus", column = @Column(name = "chrStatus", length = 45)) })
	public PicklistChrrouteChrstatusId getId() {
		return this.id;
	}

	/**
	 * Set the id
	 * 
	 * @param id The id
	 */
	public void setId(PicklistChrrouteChrstatusId id) {
		this.id = id;
	}

}
