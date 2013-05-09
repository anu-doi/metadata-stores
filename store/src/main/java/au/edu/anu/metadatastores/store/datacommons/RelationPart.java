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

package au.edu.anu.metadatastores.store.datacommons;

/**
 * <p>RelationPart<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Holds Data Commons relationship parts</p>
 * 
 * @author Genevieve Turner
 *
 */
public class RelationPart {
	String value;
	String type;
	
	/**
	 * Constructor
	 * 
	 * @param value The item to relate to
	 * @param type The relationship type
	 */
	public RelationPart(String value, String type) {
		this.value = value;
		this.type = type;
	}
	
	/**
	 * Get the string representation of the item to relate to
	 * 
	 * @return The item to relate to
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Set the string representation of hte item to relate to
	 * 
	 * @param value The item to relate to
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Get the relationship type
	 * 
	 * @return The relationship type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Set the relationship type
	 * 
	 * @param type The relationship type
	 */
	public void setType(String type) {
		this.type = type;
	}
}
