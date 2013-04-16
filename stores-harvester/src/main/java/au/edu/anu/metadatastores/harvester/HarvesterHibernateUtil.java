package au.edu.anu.metadatastores.harvester;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HarvesterHibernateUtil {
	static final Logger LOGGER = LoggerFactory.getLogger(HarvesterHibernateUtil.class);
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure("/harvester.cfg.xml").buildSessionFactory();
		}
		catch (Exception e) {
			LOGGER.error("Initial SessionFactory creation failed.", e);
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
