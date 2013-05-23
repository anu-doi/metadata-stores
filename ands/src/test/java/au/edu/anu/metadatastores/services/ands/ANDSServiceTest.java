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
package au.edu.anu.metadatastores.services.ands;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>ANDSServiceTest<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p></p>
 * 
 * @author Genevieve Turner
 *
 */
public class ANDSServiceTest {
	static final Logger LOGGER = LoggerFactory.getLogger(ANDSServiceTest.class);
	
	@Test
	public void test() {
		ANDSService andsService = ANDSService.getSingleton();
		String key = "http://purl.org/au-research/grants/arc/DP0663459";
		String description = andsService.getActivityDescription(key);
		LOGGER.info("Description for: {}, Value: {}", key, description);
		
		key = "http://purl.org/au-research/grants/arc/DP0663458";
		description = andsService.getActivityDescription(key);
		LOGGER.info("Description for: {}, Value: {}", key, description);
		
	}
}
