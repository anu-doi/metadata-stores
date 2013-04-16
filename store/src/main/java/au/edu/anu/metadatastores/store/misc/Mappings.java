package au.edu.anu.metadatastores.store.misc;

import org.hibernate.Query;
import org.hibernate.Session;

import au.edu.anu.metadatastores.datamodel.store.ItemRelation;
import au.edu.anu.metadatastores.datamodel.store.ItemRelationId;
import au.edu.anu.metadatastores.datamodel.store.PotentialRelation;
import au.edu.anu.metadatastores.datamodel.store.PotentialRelationId;
import au.edu.anu.metadatastores.datamodel.store.RelationMapping;
import au.edu.anu.metadatastores.services.store.StoreHibernateUtil;

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
