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

package au.edu.anu.metadatastores.store.misc;

import org.hibernate.Query;
import org.hibernate.Session;

import au.edu.anu.metadatastores.datamodel.store.RelationMapping;
import au.edu.anu.metadatastores.services.store.StoreHibernateUtil;

/**
 * Mappings
 * 
 * The Australian National University
 * 
 * Get the relation mapping via the description
 * 
 * @author Genevieve Turner
 *
 */
public class Mappings {
	public static RelationMapping getMappingByDescription(String description) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		
		Query query = session.createQuery("FROM RelationMapping WHERE description = :description");
		query.setParameter("description", description);
		
		RelationMapping mapping = (RelationMapping) query.uniqueResult();
		
		session.close();
		
		return mapping;
	}
}
