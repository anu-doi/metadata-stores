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

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.services.aries.AriesHibernateUtil;

/**
 * ForCodesTest
 * 
 * The Australian National University
 * 
 * Test class for the Field of Research codes
 * 
 * @author Genevieve Turner
 *
 */
public class ForCodesTest {
	static final Logger LOGGER = LoggerFactory.getLogger(ForCodesTest.class);

	/**
	 * Queries for the fiels of research in the database and prints them out
	 */
	@Test
	public void test() {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		
		Query query = session.createQuery("FROM ForCodes WHERE chrForObjectiveCode = :code");
		query.setParameter("code", "111706");
		
		ForCodes forSubject = (ForCodes) query.uniqueResult();
		if (forSubject != null) {
			LOGGER.info("Subject: {}, Division: {}, Group: {}, Class: {}", new Object[] {forSubject.getChrForDescription(), forSubject.getChrForDivisionCode()
					, forSubject.getChrForGroupCode(), forSubject.getChrForObjectiveCode()});
		}
		else {
			LOGGER.info("No subject found");
		}
		
		session.close();
		
		//fail("Not yet implemented");
	}

}
