package au.edu.anu.metadatastores.datamodel.store;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.*;

import au.edu.anu.metadatastores.services.store.StoreHibernateUtil;

public class ItemTest {
	static final Logger LOGGER = LoggerFactory.getLogger(ItemTest.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		//Query query = session.createQuery("from Item");
		Query query = session.createQuery("from Item where extId = :extId and extSystem = :extSystem");
		query.setParameter("extId", "t1234567");
		query.setParameter("extSystem", "Person");
		List<Item> items = query.list();
		
		assertTrue(items.size() == 1);
		
		for (Item item : items) {
			assertEquals(1, item.getIid().longValue());
			assertEquals("Test Person", item.getTitle());
			LOGGER.info("ID: {}, Ext ID: {}, Ext System: {}", new Object[] {item.getIid(), item.getExtId(), item.getExtSystem()});
			for (ItemAttribute attribute : item.getItemAttributes()) {
				LOGGER.info("Type: {}, Value: {}", attribute.getAttrType(), attribute.getAttrValue());
			}
		}
		
		transaction.commit();
		session.flush();
		session.close();
		
		LOGGER.info("Test Complete");
		//fail("Not yet implemented");
	}

}
