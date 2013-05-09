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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.ItemRelation;
import au.edu.anu.metadatastores.datamodel.store.ItemRelationId;
import au.edu.anu.metadatastores.datamodel.store.PotentialRelation;
import au.edu.anu.metadatastores.datamodel.store.PotentialRelationId;
import au.edu.anu.metadatastores.datamodel.store.RelationMapping;
import au.edu.anu.metadatastores.services.store.StoreHibernateUtil;

/**
 * <p>RelationService<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Service class for updating relationships</p>
 * 
 * @author Genevieve Turner
 *
 */
public class RelationService {
	static final Logger LOGGER = LoggerFactory.getLogger(RelationService.class);
	
	private static RelationService singleton_;
	
	/**
	 * Constructor
	 */
	private RelationService() {
		
	}
	
	/**
	 * Get the singleton RelationService instance
	 * 
	 * @return The RelationService
	 */
	public static RelationService getSingleton() {
		if (singleton_ == null) {
			singleton_ = new RelationService();
		}
		return singleton_;
	}
	
	/**
	 * Get the potential relations
	 * 
	 * @return The potential relations
	 */
	public List<Relation> getPotentialRelations() {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		
		Query query = session.createQuery("SELECT pr FROM PotentialRelation pr WHERE pr.requireCheck = true");
		List<PotentialRelation> potentialRelations = query.list();
		
		List<Relation> relations = convertToRelations(potentialRelations);
		
		session.close();
		
		return relations;
	}
	
	/**
	 * Get the potential relations associated with a particular staff member
	 * 
	 * @param staffId The staff id
	 * @return The relations associated with the given person
	 */
	public List<Relation> getPotentialRelations(String staffId) {
		//TODO implement this
		
		return null;
	}
	
	/**
	 * Set the potential relations into the relation class
	 * 
	 * @param potentialRelations The list of potential relations to set into the convert relations
	 * @return The list of potential relations
	 */
	public List<Relation> convertToRelations(List<PotentialRelation> potentialRelations) {
		List<Relation> relations = new ArrayList<Relation>();
		
		Relation relation = null;
		for (PotentialRelation potentialRelation : potentialRelations) {
			relation = new Relation(potentialRelation.getId().getIid(), potentialRelation.getId().getRelationValue(), potentialRelation.getId().getRelatedIid());
			relation.setRelatedItemTitle(potentialRelation.getItemByIid().getTitle());
			relation.setItemTitle(potentialRelation.getItemByIid().getTitle());
			relation.setRelatedItemTitle(potentialRelation.getItemByRelatedIid().getTitle());
			relations.add(relation);
		}
		
		return relations;
	}
	
	/**
	 * Updates the relation with a confirmation or denial of the relationship
	 * 
	 * @param relation The relation to confirm or deny
	 * @param isRelation Whether the relation is one or not
	 */
	public void confirmOrDenyRelation(Relation relation, Boolean isRelation) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			PotentialRelationId id = new PotentialRelationId(relation.getIid(), relation.getRelationValue(), relation.getRelatedIid());
			
			PotentialRelation potentialRelation = (PotentialRelation)session.get(PotentialRelation.class, id);
			
			if (potentialRelation == null) {
				potentialRelation = new PotentialRelation();
				potentialRelation.setId(id);
			}
			
			potentialRelation.setRequireCheck(Boolean.FALSE);
			potentialRelation.setIslink(isRelation);
			
			session.merge(potentialRelation);
			
			ItemRelationId itemRelationId = new ItemRelationId(id.getIid(), id.getRelationValue(), id.getRelatedIid());
			
			ItemRelation itemRelation = (ItemRelation) session.get(ItemRelation.class, itemRelationId);
			
			if (isRelation == Boolean.TRUE) {
				if (itemRelation == null) {
					itemRelation = new ItemRelation();
					itemRelation.setId(itemRelationId);
					itemRelation.setUserUpdated(Boolean.TRUE);
					session.save(itemRelation);
				}
				else {
					itemRelation.setUserUpdated(Boolean.TRUE);
					session.merge(itemRelation);
				}
			}
			else {
				if (itemRelation != null) {
					session.delete(itemRelation);
				}
			}
			
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Get the relations for the item with the given id
	 * 
	 * @param iid The item id
	 * @return The list of relations
	 */
	public List<Relation> getRelatedItems(Long iid) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		
		// Get the direct relations
		Query query = session.createQuery("select i, ir.itemByRelatedIid, rm from Item i join i.itemRelationsForIid ir, RelationMapping rm where i.iid = :id and ir.id.relationValue = rm.code");
		query.setParameter("id", iid);
		
		List<Object[]> results = query.list();
		
		Set<Relation> relations = new HashSet<Relation>();
		Relation relation = null;
		
		for (Object[] result : results) {
			Item item = (Item) result[0];
			Item relatedItem = (Item) result[1];
			RelationMapping mapping = (RelationMapping) result[2];
			relation = new Relation(item.getIid(), mapping.getDescription(), relatedItem.getIid());
			relation.setItemTitle(item.getTitle());
			relation.setRelatedItemTitle(relatedItem.getTitle());
			relations.add(relation);
		}
		
		// Get the reverse relations
		Query reverseQuery = session.createQuery("select i, ir.itemByIid, rrm from Item i join i.itemRelationsForRelatedIid ir, RelationMapping rm, RelationMapping rrm where i.iid = :id and ir.id.relationValue = rm.code and rm.reverse = rrm.code");
		reverseQuery.setParameter("id", iid);
		List<Object[]> results2 = reverseQuery.list();
		
		for (Object[] result : results2) {
			Item item = (Item) result[0];
			Item relatedItem = (Item) result[1];
			RelationMapping mapping = (RelationMapping) result[2];
			relation = new Relation(item.getIid(), mapping.getDescription(), relatedItem.getIid());
			relation.setItemTitle(item.getTitle());
			relation.setRelatedItemTitle(relatedItem.getTitle());
			relations.add(relation);
		}
		LOGGER.debug("Number of relations: {}", relations.size());
		
		session.close();
		return new ArrayList<Relation>(relations);
	}
	
	/**
	 * Get the item for the given item id
	 * 
	 * @param iid The item id
	 * @return The item
	 */
	public Item getItem(Long iid) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		session.enableFetchProfile("item-with-attributes");
		
		Item item = (Item) session.get(Item.class, iid);
		
		session.close();
		return item;
	}
}
