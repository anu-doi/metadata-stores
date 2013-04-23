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

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PicklistChrrouteChrstatusId
 * 
 * The Australian National University
 * 
 * Id class for PicklistChrrouteChrstatus
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
@Embeddable
public class PicklistChrrouteChrstatusId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String chrRoute;
	private String chrStatus;

	/**
	 * Constructor
	 */
	public PicklistChrrouteChrstatusId() {
	}

	/**
	 * Constructor
	 * 
	 * @param chrRoute
	 * @param chrStatus
	 */
	public PicklistChrrouteChrstatusId(String chrRoute, String chrStatus) {
		this.chrRoute = chrRoute;
		this.chrStatus = chrStatus;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "chrRoute", length = 45)
	public String getChrRoute() {
		return this.chrRoute;
	}

	/**
	 * 
	 * @param chrRoute
	 */
	public void setChrRoute(String chrRoute) {
		this.chrRoute = chrRoute;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "chrStatus", length = 45)
	public String getChrStatus() {
		return this.chrStatus;
	}

	/**
	 * 
	 * @param chrStatus
	 */
	public void setChrStatus(String chrStatus) {
		this.chrStatus = chrStatus;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PicklistChrrouteChrstatusId))
			return false;
		PicklistChrrouteChrstatusId castOther = (PicklistChrrouteChrstatusId) other;

		return ((this.getChrRoute() == castOther.getChrRoute()) || (this
				.getChrRoute() != null && castOther.getChrRoute() != null && this
				.getChrRoute().equals(castOther.getChrRoute())))
				&& ((this.getChrStatus() == castOther.getChrStatus()) || (this
						.getChrStatus() != null
						&& castOther.getChrStatus() != null && this
						.getChrStatus().equals(castOther.getChrStatus())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getChrRoute() == null ? 0 : this.getChrRoute().hashCode());
		result = 37 * result
				+ (getChrStatus() == null ? 0 : this.getChrStatus().hashCode());
		return result;
	}

}
