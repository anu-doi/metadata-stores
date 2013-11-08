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
import au.edu.anu.metadatastores.rdf.RDFService;
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
	//private static RDFService rdfService_ = RDFService.getSingleton();
	private static Search search_ = RDFService.getSingleton();
	//private static Search search_ = DBSearch.getSingleton();
	
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
		List<ItemDTO> results = search_.search(queryValue);
		//Set<ItemDTO> items = new HashSet<ItemDTO>(results);
		return getSortedItems(results);
	}
	
	/**
	 * Search the metdata stores limiting the results by the system type and given search fields and values
	 * 
	 * @param system The system type
	 * @param searchTerms The search terms (field and value pairs)
	 * @return The list of items found
	 */
	public List<ItemDTO> queryItems(String system, List<SearchTerm> searchTerms) {
		List<ItemDTO> items = search_.search(system, searchTerms);
		return getSortedItems(items);
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
	 * Sort the return results
	 * 
	 * @param items The items to sort
	 * @return The sorted items
	 */
	private List<ItemDTO> getSortedItems(List<ItemDTO> items) {
		Set<ItemDTO> itemsSet = new HashSet<ItemDTO>(items);
		List<ItemDTO> sortedItems = new ArrayList<ItemDTO>(itemsSet);
		Collections.sort(sortedItems, new Comparator<ItemDTO>() {
			public int compare(ItemDTO o1, ItemDTO o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		
		return sortedItems;
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
		List<AttributeType> retAttrTypes = new ArrayList<AttributeType>();
		retAttrTypes.add(new AttributeType("","All",null));
		if (clazz != null) {
			List<AttributeType> attrTypes = getAttributeTypes();
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
