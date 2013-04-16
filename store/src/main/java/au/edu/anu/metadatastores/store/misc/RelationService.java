package au.edu.anu.metadatastores.store.misc;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import au.edu.anu.metadatastores.datamodel.store.ItemRelation;
import au.edu.anu.metadatastores.datamodel.store.ItemRelationId;
import au.edu.anu.metadatastores.datamodel.store.PotentialRelation;
import au.edu.anu.metadatastores.datamodel.store.PotentialRelationId;
import au.edu.anu.metadatastores.services.store.StoreHibernateUtil;

public class RelationService {
	private static RelationService singleton_;
	
	private RelationService() {
		
	}
	
	public static RelationService getSingleton() {
		if (singleton_ == null) {
			singleton_ = new RelationService();
		}
		return singleton_;
	}
	
	public List<Relation> getPotentialRelations() {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		
		Query query = session.createQuery("SELECT pr FROM PotentialRelation pr WHERE pr.requireCheck = true");
		List<PotentialRelation> potentialRelations = query.list();
		
		List<Relation> relations = convertToRelations(potentialRelations);
		
		session.close();
		
		return relations;
	}
	
	public List<Relation> getPotentialRelations(String staffId) {
		
		
		return null;
	}
	
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
	
	public void confirmOrDenyRelation(Relation relation, Boolean isRelation) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
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
		session.close();
	}
}
