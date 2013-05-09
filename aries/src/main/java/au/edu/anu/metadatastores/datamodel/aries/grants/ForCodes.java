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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * <p>ForCodes<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Entity class for FOR_codes table</p>
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="FOR_codes")
public class ForCodes {
	private String chrForDivisionCode;
	private String chrForGroupCode;
	private String chrForObjectiveCode;
	private String chrForDescription;
	
	/**
	 * Get the Field of Research Division Code
	 * 
	 * @return The division code
	 */
	public String getChrForDivisionCode() {
		return chrForDivisionCode;
	}
	
	/**
	 * Set the field of research division code
	 * 
	 * @param chrForDivisionCode The division code
	 */
	public void setChrForDivisionCode(String chrForDivisionCode) {
		this.chrForDivisionCode = chrForDivisionCode;
	}
	
	/**
	 * Get the field of research group code
	 * 
	 * @return The group code
	 */
	public String getChrForGroupCode() {
		return chrForGroupCode;
	}
	
	/**
	 * Set the field of research group code
	 * 
	 * @param chrForGroupCode The group code
	 */
	public void setChrForGroupCode(String chrForGroupCode) {
		this.chrForGroupCode = chrForGroupCode;
	}
	
	/**
	 * Get the field of research code
	 * 
	 * @return The field of research code
	 */
	@Id
	public String getChrForObjectiveCode() {
		return chrForObjectiveCode;
	}
	
	/**
	 * Set the field of rsearch code
	 * 
	 * @param chrForObjectiveCode The field of research code
	 */
	public void setChrForObjectiveCode(String chrForObjectiveCode) {
		this.chrForObjectiveCode = chrForObjectiveCode;
	}
	
	/**
	 * Get the field of research description
	 * 
	 * @return The field of research description
	 */
	public String getChrForDescription() {
		return chrForDescription;
	}
	
	/**
	 * Set the field of research description
	 * 
	 * @param chrForDescription The field of research description
	 */
	public void setChrForDescription(String chrForDescription) {
		this.chrForDescription = chrForDescription;
	}
	
	
}
