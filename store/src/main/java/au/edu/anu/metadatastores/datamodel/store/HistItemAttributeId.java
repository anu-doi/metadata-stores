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
import javax.persistence.Embeddable;

/**
 * <p>HistItemAttributeId<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Id class for the HistItemAttribute class</p>
 * 
 * @author Genevieve Turner
 *
 */
@Embeddable
public class HistItemAttributeId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long aid;
	private Date histDatetime;

	/**
	 * Constructor
	 */
	public HistItemAttributeId() {
		
	}
	
	/**
	 * Constructor
	 * 
	 * @param aid The item attribute id
	 * @param histDatetime The date of the history occurence
	 */
	public HistItemAttributeId(Long aid, Date histDatetime) {
		this.aid = aid;
		this.histDatetime = histDatetime;
	}

	/**
	 * Get the item attribute id
	 * 
	 * @return The item attribute id
	 */
	@Column(name="aid")
	public Long getAid() {
		return aid;
	}

	/**
	 * Set the item attribute id
	 * 
	 * @param aid The item attribute id
	 */
	public void setAid(Long aid) {
		this.aid = aid;
	}

	/**
	 * Get the history date/time
	 * 
	 * @return The date/time
	 */
	@Column(name="hist_datetime")
	public Date getHistDatetime() {
		return histDatetime;
	}

	/**
	 * Set the history date/time
	 * 
	 * @param histDatetime The date/time
	 */
	public void setHistDatetime(Date histDatetime) {
		this.histDatetime = histDatetime;
	}
	
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof HistItemAttributeId))
			return false;
		HistItemAttributeId castOther = (HistItemAttributeId) other;
		
		return (this.getAid().equals(castOther.getAid())
				&& ((this.getHistDatetime() == castOther.getHistDatetime()) || (this.getHistDatetime() != null 
					&& this.getHistDatetime().equals(castOther.getHistDatetime()))));
	}
	
	public int hashCode() {
		int result = 17;
		
		result = 37 * result + (int) this.getAid().intValue();
		result = 37 + result
				+ (getHistDatetime() == null ? 0 : this.getHistDatetime().hashCode());
		return result;
	}
}
