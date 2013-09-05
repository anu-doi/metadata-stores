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

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.SystemType;

/**
 * <p>ItemServiceTest<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p></p>
 * 
 * @author Genevieve Turner
 *
 */
public class SearchServiceTest {
	static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceTest.class);
	
	private SearchService itemService_;
	
	@Before
	public void setUp() {
		itemService_ = SearchService.getSingleton();
	}
	
	@Ignore
	@Test
	public void basicSearchTest() {
		//List<ItemDTO> items = itemService_.queryItems("Contemporary");
		List<ItemDTO> items = itemService_.queryItems("Louise");
		
		for (ItemDTO item : items) {
			LOGGER.info("Id: {}, System: {}, Ext Id: {}, Title: {}", item.getId(), item.getExtSystem(), item.getExtId(), item.getTitle());
		}
		LOGGER.info("Number of Results: {}", items.size());
	}
	
	@Ignore
	@Test
	public void systemTypesTest() {
		List<SystemType> systemTypes = itemService_.getSystemTypes();
		
		for (SystemType systemType : systemTypes) {
			LOGGER.info("{}, {}, {}", systemType.getExtSystem(), systemType.getTitle(), systemType.getDescription());
		}
	}

	@Ignore
	@Test
	public void itemByIdTest() {
		Object object = itemService_.getItemById(new Long(3603));
		LOGGER.info("Object Class: {}", object.getClass().getName());
	}
	
	@Ignore
	@Test
	public void advancedSearch2Test() {
		List<SearchTerm> searchTerms = new ArrayList<SearchTerm>();
		searchTerms.add(new SearchTerm(null, "Louise"));
		//searchTerms.add(new SearchTerm("GIVEN_NAME", "Louise"));
		//searchTerms.add(new SearchTerm("SURNAME", "Tierney"));
		
		List<ItemDTO> items = itemService_.queryItems("PERSON", searchTerms);
		//List<ItemDTO> items = itemService_.queryItems("PUBLICATION", searchTerms);
		//List<ItemDTO> items = itemService_.queryItems(null, searchTerms);
		printItems(items);
	}
	
	private void printItems(List<ItemDTO> items) {
		for (ItemDTO item : items) {
			LOGGER.info("Id: {}, System: {}, Ext Id: {}, Title: {}", item.getId(), item.getExtSystem(), item.getExtId(), item.getTitle());
		}
		LOGGER.info("Number of Results: {}", items.size());
	}
	
	@Test
	public void getRelationsTest() {
		//List<ItemDTO> items = itemService_.getRelations(new Long(2798), null);
		//List<ItemDTO> items = itemService_.getRelations(new Long(2798), "PUBLICATION");
		//List<ItemDTO> items = itemService_.getRelations(new Long(1072), null);
		List<ItemDTO> items = itemService_.getRelations(new Long(1072), "PERSON");
		printItems(items);
	}
}
