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

package au.edu.anu.metadatastores.services.aries;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AriesHibernateUtil
 * 
 * The Australian National University
 * 
 * Initialises the Aries hibernate session factory
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
public class AriesHibernateUtil {
	static final Logger LOGGER = LoggerFactory.getLogger(AriesHibernateUtil.class);
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	/**
	 * Create the session factory
	 * 
	 * @return The session factory
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			//TODO figure out what to set for the ServiceRegistry
			return new Configuration().configure("/aries.cfg.xml").buildSessionFactory();
		}
		catch (Exception e) {
			LOGGER.error("Initial SessionFactory creation failed.", e);
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 * Retrieve the session factory
	 * 
	 * @return The session factory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
