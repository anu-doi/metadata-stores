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
package au.edu.anu.metadatastores.store.epress;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>EpressServiceTest<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p></p>
 * 
 * @author Genevieve Turner
 *
 */
public class EpressServiceTest {
	static final Logger LOGGER = LoggerFactory.getLogger(EpressServiceTest.class);
	
	//@Ignore
	@Test
	public void loadTest() {
		EpressService service = EpressService.getSingleton();
		try {
			List<Epress> epressRecords = service.fetchEpressRecords("C:/WorkSpace/epress/records.xml");
			service.saveEpressRecords(epressRecords);
		}
		catch (Exception e) {
			LOGGER.error("Exception with fetching records", e);
			fail("Exception");
		}
	}

	@Ignore
	@Test
	public void getTest() {
		EpressService service = EpressService.getSingleton();
		Epress epress = service.getEpress("9781921313431");
		LOGGER.info("{}, {}", epress.getExtId(), epress.getTitle());
		LOGGER.info("Content: {}", epress.getContent());
	}
}
