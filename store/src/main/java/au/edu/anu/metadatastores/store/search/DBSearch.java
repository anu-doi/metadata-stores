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

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.services.store.StoreHibernateUtil;

public class DBSearch implements Search {
	static final Logger LOGGER = LoggerFactory.getLogger(DBSearch.class);
	
	private static DBSearch singleton_;

	private DBSearch() {
	}
	
	/**
	 * Get the DBSearch object
	 * 
	 * @return The DBSearch object
	 */
	public static DBSearch getSingleton() {
		if (singleton_ == null) {
			singleton_ = new DBSearch();
		}
		
		return singleton_;
	}
	
	@Override
	public List<ItemDTO> search(String value) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		session.enableFilter("attributes");
		try {
			List<ItemDTO> results = executeQueryWithParameters(session, getFirstQueryString(), value);
			List<ItemDTO> items = new ArrayList<ItemDTO>(results);
			
			results = executeQueryWithParameters(session, getSecondQueryString(), value);
			items.addAll(results);
			return items;
		}
		finally {
			session.close();
		}
	}

	@Override
	public List<ItemDTO> search(String system, List<SearchTerm> searchTerms) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			List<String> parameters = new ArrayList<String>();
			
			List<ItemDTO> queryVals = executeQueryWithParameters(session, getFirstQueryString(system, searchTerms, parameters), parameters);
			List<ItemDTO> items = new ArrayList<ItemDTO>(queryVals);
			
			//If the system is a person this query is unnecessary as we will have already retrieved the people
			if (!"PERSON".equals(system)) {
				parameters.clear();
				queryVals = executeQueryWithParameters(session, getSecondQueryString(system, searchTerms, parameters), parameters);
				items.addAll(queryVals);
			}

			List<ItemDTO> itemList = new ArrayList<ItemDTO>(items);
			return itemList;
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Execute the query 
	 * 
	 * @param session The hibernate session
	 * @param queryString The query string
	 * @param queryValue The value to search for
	 * @return
	 */
	private List<ItemDTO> executeQueryWithParameters(Session session, String queryString, String queryValue) {
		Query query = session.createQuery(queryString);
		query.setParameter("attrValue", "%" + queryValue.toLowerCase() + "%");
		@SuppressWarnings("unchecked")
		List<ItemDTO> items = query.list();
		return items;
	}

	/**
	 * Generate the first query string for searching metadata stores by a value (searches for objects with that value)
	 * 
	 * @return The query string
	 */
	private String getFirstQueryString() {
		StringBuilder queryBuilder = new StringBuilder();

		queryBuilder.append("SELECT new au.edu.anu.metadatastores.store.search.ItemDTO(item.iid, item.title, sysType.title, item.extId) ");
		
		queryBuilder.append("FROM Item item, SystemType sysType ");
		queryBuilder.append("WHERE sysType.extSystem = item.extSystem ");
		queryBuilder.append("AND EXISTS (SELECT 1 ");
		queryBuilder.append("FROM item.itemAttributes ia ");
		queryBuilder.append("WHERE lower(ia.attrValue) like :attrValue ) ");
		
		return queryBuilder.toString();
	}

	/**
	 * Generate the second query string for searching metadata stores by a value (searches for associated people with that value)
	 * 
	 * @return The query string
	 */
	private String getSecondQueryString() {
		StringBuilder queryBuilder = new StringBuilder();

		queryBuilder.append("SELECT new au.edu.anu.metadatastores.store.search.ItemDTO(item.iid, item.title, sysType.title, item.extId) ");
		queryBuilder.append("FROM Item item, SystemType sysType, ItemRelation ir, Item item2 ");
		queryBuilder.append("WHERE sysType.extSystem = item.extSystem ");
		queryBuilder.append("AND item2.extSystem = 'PERSON'");
		queryBuilder.append("AND EXISTS (SELECT 1 ");
		queryBuilder.append("FROM item2.itemAttributes ia ");
		queryBuilder.append("WHERE lower(ia.attrValue) like :attrValue ) ");
		queryBuilder.append("AND ir.itemByRelatedIid = item2 ");
		queryBuilder.append("AND ir.itemByIid = item");
		
		return queryBuilder.toString();
	}
	
	/**
	 * Execute the query with the given parameters
	 * 
	 * @param session The hibernate session
	 * @param queryString The query string
	 * @param parameters The parameters
	 * @return The list of items resulting from the query
	 */
	private List<ItemDTO> executeQueryWithParameters(Session session, String queryString, List<String> parameters) {
		Query query = session.createQuery(queryString);
		for (int i = 0; i < parameters.size(); i++) {
			query.setParameter(i, parameters.get(i));
		}
		@SuppressWarnings("unchecked")
		List<ItemDTO> items = query.list();
		return items;
	}
	
	/**
	 * Get the first query string that finds the fields with the values in it
	 * 
	 * @param system The source system
	 * @param searchTerms The search terms
	 * @param parameters The list of parameters to populate
	 * @return The query string
	 */
	private String getFirstQueryString(String system, List<SearchTerm> searchTerms, List<String> parameters) {
		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT new au.edu.anu.metadatastores.store.search.ItemDTO(item.iid, item.title, sysType.title, item.extId) ");
		queryString.append("FROM Item item, SystemType sysType ");
		queryString.append("WHERE sysType.extSystem = item.extSystem ");
		
		if (system != null && system.length() > 0) {
			queryString.append("AND item.extSystem = ? ");
			parameters.add(system);
		}
		for (SearchTerm term : searchTerms) {
			queryString.append("AND EXISTS (SELECT ia FROM item.itemAttributes ia WHERE ");
			if (term.getField() != null && term.getField().length() > 0) {
				queryString.append(" ia.attrType = ? AND ");
				parameters.add(term.getField());
			}
			queryString.append("lower(ia.attrValue) like ? ");
			queryString.append(")");
			parameters.add("%" + term.getValue().toLowerCase() + "%");
		}
		
		return queryString.toString();
	}
	
	/**
	 * Get the second query string that fields the people with the values in it
	 * 
	 * @param system The source system
	 * @param searchTerms The search terms
	 * @param parameters The parameters
	 * @return The query string
	 */
	private String getSecondQueryString(String system, List<SearchTerm> searchTerms, List<String> parameters) {
		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT new au.edu.anu.metadatastores.store.search.ItemDTO(item.iid, item.title, sysType.title, item.extId) ");
		queryString.append("FROM Item item, SystemType sysType, ItemRelation ir, Item item2 ");
		queryString.append("WHERE sysType.extSystem = item.extSystem ");
		queryString.append("AND item2.extSystem = 'PERSON' ");
		if (system != null && system.length() > 0) {
			queryString.append(" AND item.extSystem = ?");
			parameters.add(system);
		}
		for (SearchTerm term : searchTerms) {
			queryString.append(" AND EXISTS (SELECT ia FROM item2.itemAttributes ia WHERE ");
			if (term.getField() != null && term.getField().length() > 0) {
				queryString.append(" ia.attrType = ? AND ");
				parameters.add(term.getField());
			}
			queryString.append(" lower(ia.attrValue) like ? ");
			queryString.append(")");
			parameters.add("%" + term.getValue().toLowerCase() + "%");
		}
		queryString.append("AND ir.itemByRelatedIid = item2 ");
		queryString.append("AND ir.itemByIid = item");

		return queryString.toString();
	}
	

}
