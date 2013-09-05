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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.AttributeType;
import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.SystemType;
import au.edu.anu.metadatastores.datamodel.store.annotations.ItemTraitParser;
import au.edu.anu.metadatastores.services.store.StoreHibernateUtil;
import au.edu.anu.metadatastores.store.util.ItemResolver;

/**
 * <p>ItemService<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p></p>
 * 
 * @author Genevieve Turner
 *
 */
public class SearchService {
	static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);
	
	private static SearchService itemService_;
	
	/**
	 * Get the singleton object of the search service
	 * 
	 * @return The SearchService
	 */
	public static SearchService getSingleton() {
		if (itemService_ == null) {
			itemService_ = new SearchService();
		}
		return itemService_;
	}
	
	/**
	 * Constructor
	 */
	protected SearchService() {
		
	}
	
	/**
	 * Search the metadata stores for the value
	 * 
	 * @param queryValue The value to search for
	 * @return A list of items associated with the query value
	 */
	public List<ItemDTO> queryItems(String queryValue) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		session.enableFilter("attributes");
		try {
			List<ItemDTO> results = executeQueryWithParameters(session, getFirstQueryString(), queryValue);
			Set<ItemDTO> items = new HashSet<ItemDTO>(results);
			
			results = executeQueryWithParameters(session, getSecondQueryString(), queryValue);
			items.addAll(results);
			
			return getSortedItems(items);
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
	 * Query the metadata stores for the given search terms
	 * 
	 * @param system The source system to search
	 * @param searchTerms A list of fields and values to search
	 * @return The list of item results
	 */
	public List<ItemDTO> queryItems(String system, List<SearchTerm> searchTerms) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			List<String> parameters = new ArrayList<String>();
			
			List<ItemDTO> queryVals = executeQueryWithParameters(session, getFirstQueryString(system, searchTerms, parameters), parameters);
			Set<ItemDTO> items = new HashSet<ItemDTO>(queryVals);
			//If the system is a person this query is unnecessary as we will have already retrieved the people
			if (!"PERSON".equals(system)) {
				parameters.clear();
				queryVals = executeQueryWithParameters(session, getSecondQueryString(system, searchTerms, parameters), parameters);
				items.addAll(queryVals);
			}

			return getSortedItems(items);
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Sort the return results
	 * 
	 * @param items The items to sort
	 * @return The sorted items
	 */
	private List<ItemDTO> getSortedItems(Set<ItemDTO> items) {

		List<ItemDTO> sortedItems = new ArrayList<ItemDTO>(items);
		Collections.sort(sortedItems, new Comparator<ItemDTO>() {
			public int compare(ItemDTO o1, ItemDTO o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		
		return sortedItems;
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
	 * @param system The source ssytem
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
	
	/**
	 * Get the item by id
	 * 
	 * @param id The id
	 * @return The item object
	 */
	public Object getItemById(Long id) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		
		try {
			Object item = session.get(Item.class, id);
			return ItemResolver.resolve(item);
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Get the system types (i.e. the types for Publications, Grants, People, etc.)
	 * 
	 * @return The system types
	 */
	public List<SystemType> getSystemTypes() {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		
		try {
			Query query = session.createQuery("FROM SystemType");
			@SuppressWarnings("unchecked")
			List<SystemType> systemTypes = query.list();
			return systemTypes;
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Get the attribute types (i.e. the types for Title, Given Name, Year, etc.)
	 * 
	 * @return The attribute types
	 */
	public List<AttributeType> getAttributeTypes() {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		
		try {
			Query query = session.createQuery("FROM AttributeType");
			@SuppressWarnings("unchecked")
			List<AttributeType> attributeTypes = query.list();
			return attributeTypes;
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Get the attribute types for the given class
	 * 
	 * @param clazz The class to get the attribute types for
	 * @return The attribute types
	 */
	public List<AttributeType> getAttributeTypes(Class<?> clazz) {
		ItemTraitParser parser = new ItemTraitParser();
		List<AttributeType> attrTypes = getAttributeTypes();
		List<AttributeType> retAttrTypes = new ArrayList<AttributeType>();
		retAttrTypes.add(new AttributeType("","All",null));
		if (clazz != null) {
			List<String> classAttrTypes = parser.getTraitAttributeTypes(clazz);
			
			for (AttributeType attrType : attrTypes) {
				for (String attr : classAttrTypes) {
					if (attr.equals(attrType.getAttrType())) {
						retAttrTypes.add(attrType);
						break;
					}
				}
			}
		}
		else {
			retAttrTypes.addAll(attrTypes);
		}
		
		return retAttrTypes;
	}
	
	/**
	 * Get the relations by the id
	 * 
	 * @param id The id
	 * @param system The system to filter the relation on if it exists
	 * @return The list of related items
	 */
	public List<ItemDTO> getRelations(Long id, String system) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		
		try {
			List<ItemDTO> results = executeRelationQuery(session, getFirstRelationQueryString(system), id, system);
			LOGGER.debug("Number of Results Q1: {}", results.size());

			Set<ItemDTO> items = new HashSet<ItemDTO>(results);
			
			results = executeRelationQuery(session, getSecondRelationQueryString(system), id, system);
			LOGGER.debug("Number of Results Q2: {}", results.size());
			items.addAll(results);
			
			return getSortedItems(items);
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Execute the relationship query
	 * 
	 * @param session The hibernate session
	 * @param queryString The query string
	 * @param id The id of the object
	 * @param system The system to filter the relation on if it exists
	 * @return The related items
	 */
	private List<ItemDTO> executeRelationQuery(Session session, String queryString, Long id, String system) {
		Query query = session.createQuery(queryString.toString());
		query.setParameter("id", id);
		if (system != null && system.length() > 0) {
			query.setParameter("system", system);
		}
		
		@SuppressWarnings("unchecked")
		List<ItemDTO> items = query.list();
		return items;
	}
	
	/**
	 * Create the query string for getting the items by related iid
	 * 
	 * @param system The system to filter the relation on if it exists
	 * @return The query string
	 */
	private String getFirstRelationQueryString(String system) {
		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT new au.edu.anu.metadatastores.store.search.ItemDTO(ir.itemByIid.iid, ir.itemByIid.title, sysType.title, ir.itemByIid.extId) ");
		queryString.append("FROM Item item join item.itemRelationsForRelatedIid ir, SystemType sysType ");
		queryString.append("WHERE item.iid = :id ");
		queryString.append("AND ir.itemByIid.extSystem = sysType.extSystem ");
		if (system != null && system.length() > 0) {
			queryString.append("AND ir.itemByIid.extSystem = :system ");
		}
		return queryString.toString();
	}
	
	/**
	 * Create the query string for getting the relations by iid
	 * 
	 * @param system The system to filter the relation on if it exists
	 * @return The query string
	 */
	private String getSecondRelationQueryString(String system) {
		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT new au.edu.anu.metadatastores.store.search.ItemDTO(ir.itemByRelatedIid.iid, ir.itemByRelatedIid.title, sysType.title, ir.itemByRelatedIid.extId) ");
		queryString.append("FROM Item item join item.itemRelationsForIid ir, SystemType sysType ");
		queryString.append("WHERE item.iid = :id ");
		queryString.append("AND ir.itemByRelatedIid.extSystem = sysType.extSystem ");
		if (system != null && system.length() > 0) {
			queryString.append("AND ir.itemByRelatedIid.extSystem = :system ");
		}
		return queryString.toString();
	}
}
