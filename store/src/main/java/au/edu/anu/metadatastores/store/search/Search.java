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

import java.util.List;

public interface Search {
	/**
	 * Search all fields for the given value
	 * 
	 * @param value The value to search for
	 * @return The found values 
	 */
	public List<ItemDTO> search(String value);
	
	/**
	 * Search the given type for the values
	 * 
	 * @param system The system to filter items by
	 * @param searchTerms The values and their associated fields to search for
	 * @return The found values
	 */
	public List<ItemDTO> search(String system, List<SearchTerm> searchTerms);
}
