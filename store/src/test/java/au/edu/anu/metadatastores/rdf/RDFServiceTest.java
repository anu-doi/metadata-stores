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
package au.edu.anu.metadatastores.rdf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.store.search.ItemDTO;
import au.edu.anu.metadatastores.store.search.SearchTerm;

public class RDFServiceTest {
	static final Logger LOGGER = LoggerFactory.getLogger(RDFServiceTest.class);
	RDFService service = RDFService.getSingleton();
	
	@Ignore
	@Test
	public void updateAllTest() {
		Date startDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		LOGGER.info("Update Started At: {}", sdf.format(startDate));
		service.updateAll();
		Date endDate = new Date();
		long diff = endDate.getTime() - startDate.getTime();
		double sec = diff / 1000;
		double min = sec / 60;
		LOGGER.info("Update Finished At: {}", sdf.format(endDate));
		LOGGER.info("Time taken to update in: Milliseconds - {}, Seconds - {}, Minutes - {}", diff, sec, min);
	}
	
	//@Ignore
	@Test
	public void updateSystemTest() {
		Date startDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		LOGGER.info("Update Started At: {}", sdf.format(startDate));
		//service.updateFromSpecifiedSystem("GRANT");;
		//service.updateFromSpecifiedSystem("DIGITAL_COLLECTIONS");;
		service.updateFromSpecifiedSystem("DATA_COMMONS");
		Date endDate = new Date();
		long diff = endDate.getTime() - startDate.getTime();
		double sec = diff / 1000;
		double min = sec / 60;
		LOGGER.info("Update Finished At: {}", sdf.format(endDate));
		LOGGER.info("Time taken to update in: Milliseconds - {}, Seconds - {}, Minutes - {}", diff, sec, min);
	}
	
	@Ignore
	@Test
	public void updateAfterDateTime() {
		Date startDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		LOGGER.info("Update Started At: {}", sdf.format(startDate));
		Calendar modifiedDate = Calendar.getInstance();
		modifiedDate.set(2013, Calendar.OCTOBER, 29);
		
		service.updateModifiedAfterDate(modifiedDate.getTime());
		
		Date endDate = new Date();
		long diff = endDate.getTime() - startDate.getTime();
		double sec = diff / 1000;
		double min = sec / 60;
		LOGGER.info("Update Finished At: {}", sdf.format(endDate));
		LOGGER.info("Time taken to update in: Milliseconds - {}, Seconds - {}, Minutes - {}", diff, sec, min);
	}

	@Ignore
	@Test
	public void updateSystemTypes() {
		service.updateSystemTypes();
		LOGGER.info("Update System Types Complete");
	}
	
	@Ignore
	@Test
	public void query() {
		//List<ItemDTO> items = service.search("drink");
		List<ItemDTO> items = service.search("louise");
		printItems(items);
		LOGGER.info("Query Complete");
	}
	
	@Ignore
	@Test
	public void query2() {
		//List<ItemDTO> items = service.search("drink");
		List<ItemDTO> items = service.search("111706");
		printItems(items);
		LOGGER.info("Query Complete");
	}
	
	@Ignore
	@Test
	public void queryDuplicates() {
		List<SearchTerm> searchTerms = new ArrayList<SearchTerm>();
		SearchTerm term = new SearchTerm("TITLE", "health");
		searchTerms.add(term);
		term = new SearchTerm("YEAR", "2010");
		searchTerms.add(term);
		
		//List<ItemDTO> items = service.search("GRANT", searchTerms);
		//List<ItemDTO> items = service.search("PUBLICATION", searchTerms);
		List<ItemDTO> items = service.search("PUBLICATION", searchTerms);
		//List<ItemDTO> items = service.search("drink");
		//List<ItemDTO> items = service.search("louise");
		printItems(items);
		LOGGER.info("Query Complete");
	}
	
	@Ignore
	@Test
	public void searchByDefaultSets() {
		List<SearchTerm> searchTerms = new ArrayList<SearchTerm>();
		SearchTerm term = new SearchTerm("EMAIL", "9");
		searchTerms.add(term);
		
		List<ItemDTO> items = service.search("PERSON", searchTerms);
		printItems(items);
		LOGGER.info("Query Complete");
	}

	@Ignore
	@Test
	public void searchBySets() {
		List<SearchTerm> searchTerms = new ArrayList<SearchTerm>();
		SearchTerm term = new SearchTerm("GIVEN_NAME", "ri");
		searchTerms.add(term);
		List<ItemDTO> items = service.search("PERSON", searchTerms);
		printItems(items);
		LOGGER.info("Query Complete");
	}

	@Ignore
	@Test
	public void searchSubject() {
		//TODO make this work
		List<SearchTerm> searchTerms = new ArrayList<SearchTerm>();
		SearchTerm term = new SearchTerm("FOR_CODE", "111706");
		searchTerms.add(term);
		
		List<ItemDTO> items = service.search("PERSON", searchTerms);
		printItems(items);
		LOGGER.info("Query Complete");
	}
	
	@Ignore
	@Test
	public void queryAll() {
		List<SearchTerm> searchTerms = new ArrayList<SearchTerm>();
		//SearchTerm term = new SearchTerm("TITLE", "role");
		SearchTerm term = new SearchTerm(null, "role");
		searchTerms.add(term);
		term = new SearchTerm(null, "louise");
		searchTerms.add(term);
		
		List<ItemDTO> items = service.search("PUBLICATION", searchTerms);
		printItems(items);
		LOGGER.info("Query Complete");
	}
	
	@Ignore
	@Test
	public void queryAllNoType() {
		List<SearchTerm> searchTerms = new ArrayList<SearchTerm>();
		//SearchTerm term = new SearchTerm("TITLE", "role");
		SearchTerm term = new SearchTerm(null, "louise");
		searchTerms.add(term);
		term = new SearchTerm(null, "assoc");
		searchTerms.add(term);
		
		List<ItemDTO> items = service.search(null, searchTerms);
		printItems(items);
		LOGGER.info("Query Complete");
	}
	
	private void printItems(List<ItemDTO> items) {
		if (items != null) {
			for (ItemDTO item : items) {
				LOGGER.info("{}, {}, {}", new Object[] {item.getId(), item.getExtSystem(), item.getTitle()});
			}
		}
		else {
			LOGGER.info("No Results Found");
		}
	}
}
