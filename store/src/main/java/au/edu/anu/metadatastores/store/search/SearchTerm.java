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
package au.edu.anu.metadatastores.store.search;

/**
 * <p>SearchTerm<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>A Search Term to use when searching the metadata stores.  This specifieds the attribute and value that should be searched</p>
 * 
 * @author Genevieve Turner
 *
 */
public class SearchTerm {
	private String field;
	private String value;
	
	/**
	 * Constructor
	 */
	public SearchTerm() {
		
	}
	
	/**
	 * Constructor
	 * 
	 * @param field The attribute to limit the search on
	 * @param value The value to search for
	 */
	public SearchTerm(String field, String value) {
		this.field = field;
		this.value = value;
	}

	/**
	 * Get the attribute to limit the search on
	 * 
	 * @return The attribute to limit the search on 
	 */
	public String getField() {
		return field;
	}

	/**
	 * Set the attribute to limit the search on
	 * 
	 * @param field The attribute to limit the search on
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * Get the value to search for
	 * 
	 * @return The value to search for
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Set the value to search for
	 * 
	 * @param value The value to search for
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
