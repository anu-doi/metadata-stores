package au.edu.anu.metadatastores.datamodel.aries.grants;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.services.aries.AriesHibernateUtil;

public class ForCodesTest {
	static final Logger LOGGER = LoggerFactory.getLogger(ForCodesTest.class);

	@Test
	public void test() {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		
		Query query = session.createQuery("FROM ForCodes WHERE chrForObjectiveCode = :code");
		query.setParameter("code", "111706");
		
		ForCodes forSubject = (ForCodes) query.uniqueResult();
		if (forSubject != null) {
			LOGGER.info("Subject: {}, Division: {}, Group: {}, Class: {}", new Object[] {forSubject.getChrForDescription(), forSubject.getChrForDivisionCode()
					, forSubject.getChrForGroupCode(), forSubject.getChrForObjectiveCode()});
		}
		else {
			LOGGER.info("No subject found");
		}
		
		session.close();
		
		//fail("Not yet implemented");
	}

}
