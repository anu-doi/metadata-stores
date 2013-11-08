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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.SystemType;
import au.edu.anu.metadatastores.store.people.Person;

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
		Person person = (Person) object;
		LOGGER.info("{}, {}", person.getExtId(), person.getDisplayName());
	}
	
	@Ignore
	@Test
	public void advancedSearchTest1() {
		List<SearchTerm> searchTerms = new ArrayList<SearchTerm>();
		searchTerms.add(new SearchTerm(null, "Louise"));
		//searchTerms.add(new SearchTerm("GIVEN_NAME", "Louise"));
		//searchTerms.add(new SearchTerm("SURNAME", "Tierney"));
		
		List<ItemDTO> items = itemService_.queryItems("PERSON", searchTerms);
		//List<ItemDTO> items = itemService_.queryItems("PUBLICATION", searchTerms);
		//List<ItemDTO> items = itemService_.queryItems(null, searchTerms);
		printItems(items);
	}

	@Ignore
	@Test
	public void advancedSearchTest2() {
		List<SearchTerm> searchTerms = new ArrayList<SearchTerm>();
		SearchTerm term = new SearchTerm("TITLE", "health");
		searchTerms.add(term);
		term = new SearchTerm("YEAR", "2010");
		searchTerms.add(term);
		
		List<ItemDTO> items = itemService_.queryItems("PUBLICATION", searchTerms);
		printItems(items);
		LOGGER.info("Query Complete");
		
	}

	@Ignore
	@Test
	public void advancedSearchTest3() {
		List<SearchTerm> searchTerms = new ArrayList<SearchTerm>();
		SearchTerm term = new SearchTerm("EMAIL", "9");
		searchTerms.add(term);
		
		List<ItemDTO> items = itemService_.queryItems("PERSON", searchTerms);
		printItems(items);
		LOGGER.info("Query Complete");
	}

	@Ignore
	@Test
	public void advancedSearchTest4() {
		List<SearchTerm> searchTerms = new ArrayList<SearchTerm>();
		SearchTerm term = new SearchTerm("GIVEN_NAME", "ri");
		searchTerms.add(term);
		List<ItemDTO> items = itemService_.queryItems("PERSON", searchTerms);
		printItems(items);
		LOGGER.info("Query Complete");
	}

	@Ignore
	@Test
	public void advancedSearchTest5() {
		List<SearchTerm> searchTerms = new ArrayList<SearchTerm>();
		SearchTerm term = new SearchTerm("FOR_SUBJECT", "10");
		searchTerms.add(term);
		
		List<ItemDTO> items = itemService_.queryItems("PERSON", searchTerms);
		printItems(items);
		LOGGER.info("Query Complete");
	}

	@Ignore
	@Test
	public void advancedSearchTest6() {
		List<SearchTerm> searchTerms = new ArrayList<SearchTerm>();
		//SearchTerm term = new SearchTerm("TITLE", "role");
		SearchTerm term = new SearchTerm(null, "role");
		searchTerms.add(term);
		term = new SearchTerm(null, "louise");
		searchTerms.add(term);
		
		List<ItemDTO> items = itemService_.queryItems("PUBLICATION", searchTerms);
		printItems(items);
		LOGGER.info("Query Complete");
	}
	
	@Ignore
	@Test
	public void advancedSearchTest7() {
		Date startDate = getStartDate();
		List<SearchTerm> searchTerms = new ArrayList<SearchTerm>();
		SearchTerm term = new SearchTerm("TITLE", "effect");
		searchTerms.add(term);
		
		List<ItemDTO> items = itemService_.queryItems("DIGITAL_COLLECTIONS", searchTerms);
		printItems(items);
		LOGGER.info("Query Complete");
		printCompletion(startDate);
	}
	
	//@Ignore
	@Test
	public void advancedSearchTest8() {
		//LOGGER.info(arg0);
		LOGGER.info("Starting all search for 'some'");
		Date startDate = getStartDate();
		
		List<SearchTerm> searchTerms = new ArrayList<SearchTerm>();
		SearchTerm term = new SearchTerm(null, "some");
		searchTerms.add(term);
		//term = new SearchTerm(null, "chrom");
		//searchTerms.add(term);
		
		List<ItemDTO> items = itemService_.queryItems(null, searchTerms);
		//printItems(items);
		printCompletion(startDate);
	}

	//@Ignore
	@Test
	public void searchTest1() {
		LOGGER.info("Starting basic search for 'some'");
		//LOGGER.info(arg0);
		Date startDate = getStartDate();
		
		List<ItemDTO> items = itemService_.queryItems("some");
		//printItems(items);
		
		printCompletion(startDate);
	}
	
	private Date getStartDate() {
		return new Date();
	}
	
	private void printCompletion(Date startDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		LOGGER.info("Search Started At: {}", sdf.format(startDate));

		Date endDate = new Date();
		long diff = endDate.getTime() - startDate.getTime();
		double sec = diff / 1000;
		double min = sec / 60;
		LOGGER.info("Search Finished At: {}", sdf.format(endDate));
		LOGGER.info("Time taken to update in: Milliseconds - {}, Seconds - {}, Minutes - {}", diff, sec, min);
	}
	
	private void printItems(List<ItemDTO> items) {
		for (ItemDTO item : items) {
			LOGGER.info("Id: {}, System: {}, Ext Id: {}, Title: {}", item.getId(), item.getExtSystem(), item.getExtId(), item.getTitle());
		}
		LOGGER.info("Number of Results: {}", items.size());
	}
	
	@Ignore
	@Test
	public void getRelationsTest() {
		//List<ItemDTO> items = itemService_.getRelations(new Long(2798), null);
		//List<ItemDTO> items = itemService_.getRelations(new Long(2798), "PUBLICATION");
		//List<ItemDTO> items = itemService_.getRelations(new Long(1072), null);
		List<ItemDTO> items = itemService_.getRelations(new Long(1072), "PERSON");
		printItems(items);
	}
}
