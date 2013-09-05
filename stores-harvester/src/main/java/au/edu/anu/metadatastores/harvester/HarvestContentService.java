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

package au.edu.anu.metadatastores.harvester;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import au.edu.anu.metadatastores.datamodel.harvester.HarvestContent;
import au.edu.anu.metadatastores.datamodel.harvester.Location;

/**
 * <p>HarvestContentService<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Harvest Content Service, this class processes the harvesting</p>
 * 
 * @author Genevieve Turner
 *
 */
public class HarvestContentService {
	private static HarvestContentService singleton_;
	
	/**
	 * Constructor
	 */
	private HarvestContentService() {
	}
	
	/**
	 * Retrieves the singleton class for the service
	 * 
	 * @return The HarvestContentService object
	 */
	public static HarvestContentService getSingleton() {
		if (singleton_ == null) {
			singleton_ = new HarvestContentService();
		}
		return singleton_;
	}
	
	/**
	 * Get the next row of harvest content to process
	 * 
	 * @param system The system to get the harvest content for
	 * @return The harvest content record
	 */
	public HarvestContent getNextHarvestContent(String system) {
		Session session = HarvesterHibernateUtil.getSessionFactory().openSession();
		try {
			Query query = session.createQuery("FROM HarvestContent WHERE system = :system ORDER BY hid");
			query.setParameter("system", system);
			
			HarvestContent content = (HarvestContent) query.setMaxResults(1).uniqueResult();
			
			return content;
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Delete the harvest content row
	 * 
	 * @param content The harvest content row to delete
	 */
	public void deleteHarvestContent(HarvestContent content) {
		Session session = HarvesterHibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			session.delete(content);
			
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Get the harvest locations
	 * 
	 * @return The harvest locations
	 */
	public List<Location> getHarvestLocations() {
		Session session = HarvesterHibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			List<Location> locations = session.createQuery("FROM Location").list();
			return locations;
		}
		finally {
			session.close();
		}
	}
}
