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
package au.edu.anu.metadatastores.service.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.AttributeType;
import au.edu.anu.metadatastores.datamodel.store.SystemType;
import au.edu.anu.metadatastores.store.search.ItemDTO;
import au.edu.anu.metadatastores.store.search.SearchService;
import au.edu.anu.metadatastores.store.search.SearchTerm;
import au.edu.anu.metadatastores.store.util.ItemResolver;

/**
 * <p>SearchOptions<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p></p>
 * 
 * @author Genevieve Turner
 *
 */
public class SearchOptions {
	static final Logger LOGGER = LoggerFactory.getLogger(SearchOptions.class);
	/**
	 * Search metadata stores for the value
	 * 
	 * @param searchValue The value to search for
	 * @return The model
	 */
	public Map<String, Object> search(String searchValue, int offset, int rows) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("rows", getRows(rows));
		if (searchValue != null && searchValue.length() > 0) {
			SearchService itemService = SearchService.getSingleton();
			List<ItemDTO> items = itemService.queryItems(searchValue);
			model.put("numItems", items.size());
			if (rows > 0) {
				items = items.subList(offset, Math.min(items.size(), offset + rows));
			}
			model.put("items", items);
		}
		
		return model;
	}
	
	private int getRows(int rows) {
		if (rows == 0) {
			//TODO get this value from a properties file
			rows = 10;
		}
		return rows;
	}
	
	/**
	 * Search metadata stores for the values
	 * 
	 * @param values The values
	 * @param system The source system
	 * @param fields The fields
	 * @return The model
	 */
	public Map<String, Object> advancedSearch(String[] values, String system, String[] fields, int offset, int rows) {
		List<SearchTerm> searchTerms = new ArrayList<SearchTerm>();
		for (int i = 0; i < fields.length; i++) {
			if (values[i] != null && values[i].length() > 0) {
				SearchTerm searchTerm = new SearchTerm(fields[i], values[i]);
				searchTerms.add(searchTerm);
			}
		}
		return advancedSearch(system, searchTerms, offset, rows);
	}
	
	/**
	 * Search metadata stores for the values
	 * 
	 * @param values The values
	 * @param system The source system
	 * @param fields The fields
	 * @return
	 */
	public Map<String, Object> advancedSearch(List<String> values, String system, List<String> fields, int offset, int rows) {
		List<SearchTerm> searchTerms = new ArrayList<SearchTerm>();
		for (int i = 0; i < values.size(); i++) {
			if (values.get(i) != null && values.get(i).length() > 0) {
				SearchTerm searchTerm = new SearchTerm(fields.get(i), values.get(i));
				searchTerms.add(searchTerm);
			}
		}
		return advancedSearch(system, searchTerms, offset, rows);
	}

	/**
	 * Search metadata stores for the values
	 * 
	 * @param system The source system
	 * @param searchTerms The search terms defined by their field and value
	 * @return
	 */
	public Map<String, Object> advancedSearch(String system, List<SearchTerm> searchTerms, int offset, int rows) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("rows", getRows(rows));
		SearchService searchService = SearchService.getSingleton();
		
		if (searchTerms != null && searchTerms.size() > 0) {
			List<ItemDTO> items = searchService.queryItems(system, searchTerms);
			model.put("numItems", items.size());
			if (rows > 0) {
				items = items.subList(offset, Math.min(items.size(), offset + rows));
			}
			model.put("items", items);
		}
		
		model.put("systemTypes", getSystemTypes());
		model.put("attrTypes", getAttributeTypes(system));
		
		return model;
	}
	
	/**
	 * Get the system types and sort them
	 * 
	 * @return  The system types
	 */
	public List<SystemType> getSystemTypes() {
		SearchService searchService = SearchService.getSingleton();
		List<SystemType> systemTypes = searchService.getSystemTypes();
		Collections.sort(systemTypes, new Comparator<SystemType>() {
			@Override
			public int compare(SystemType o1, SystemType o2) {
				return o1.getTitle().compareToIgnoreCase(o2.getTitle());
			}
		});
		return systemTypes;
	}
	
	/**
	 * Get the attribute types and sort them
	 * 
	 * @param The id of the source system
	 * @return The attributes for the system
	 */
	public List<AttributeType> getAttributeTypes(String system) {
		SearchService searchService = SearchService.getSingleton();
		List<AttributeType> attributeTypes = null;
		
		Class<?> clazz = ItemResolver.resolveTypeBySystemId(system);
		attributeTypes = searchService.getAttributeTypes(clazz);
		Collections.sort(attributeTypes, new Comparator<AttributeType>() {
			@Override
			public int compare(AttributeType o1, AttributeType o2) {
				return o1.getTitle().compareToIgnoreCase(o2.getTitle());
			}
		});
		return attributeTypes;
	}
	
	/**
	 * Get a list of relations by the id
	 * 
	 * @param id The id of the item to get the relations for
	 * @return The related items
	 */
	//TODO move this!
	public List<ItemDTO> getRelations(Long id, String system) {
		SearchService searchService = SearchService.getSingleton();
		List<ItemDTO> items = searchService.getRelations(id, system);
		Collections.sort(items, new Comparator<ItemDTO>() {
			public int compare(ItemDTO o1, ItemDTO o2) {
				int result = o1.getExtSystem().compareTo(o2.getExtSystem());
				if (result == 0) {
					result = o1.getTitle().compareTo(o2.getTitle());
				}
				
				return result;
			}
		});
		return items;
	}
}
