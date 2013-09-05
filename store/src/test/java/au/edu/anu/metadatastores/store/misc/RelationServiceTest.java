package au.edu.anu.metadatastores.store.misc;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RelationServiceTest {
	static final Logger LOGGER = LoggerFactory.getLogger(RelationServiceTest.class);
	
	@Ignore
	@Test
	public void confirmDenyRelationTest() {
		Relation id = new Relation(new Long(950), "hasCreator", new Long(1021));
		RelationService relationService = RelationService.getSingleton();
		//Mappings.confirmOrDenyRelation(id, Boolean.TRUE);
		relationService.confirmOrDenyRelation(id, Boolean.FALSE);
		System.out.println("Done");
	}

	@Ignore
	@Test
	public void testPotentialRelations() {
		RelationService relationService = RelationService.getSingleton();
		List<Relation> relations = relationService.getPotentialRelations();
		for (Relation relation : relations) {
			LOGGER.info("Iid: {}, Title: {}, Type: {}, Related Iid: {}, Related Title: {}", new Object[]{relation.getIid(), relation.getItemTitle(), relation.getRelationValue(), relation.getRelatedIid(), relation.getRelatedItemTitle()});
		}
		LOGGER.info("Number of Relations: {}", relations.size());
	}

	//@Ignore
	@Test
	public void  testGetRelations() {
		RelationService relationService = RelationService.getSingleton();
		List<Relation> relations = relationService.getRelatedItems(new Long(54));
		printRelations(relations);
		relations = relationService.getRelatedItems(new Long(129));
		printRelations(relations);
		relations = relationService.getRelatedItems(new Long(947));
		printRelations(relations);
	}
	
	private void printRelations(List<Relation> relations) {
		for (Relation relation : relations) {
			LOGGER.info("{}, {}, {}, {}, {}",new Object[] {relation.getIid(), relation.getRelatedIid(), relation.getRelationValue(), relation.getItemTitle(), relation.getRelatedItemTitle()});
		}
	}
}
