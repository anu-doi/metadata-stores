package au.edu.anu.metadatastores.services.aries;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AriesHibernateUtil {
	static final Logger LOGGER = LoggerFactory.getLogger(AriesHibernateUtil.class);
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	/**
	 * Create the session factory
	 * 
	 * @return The session factory
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			//TODO figure out what to set for the ServiceRegistry
			return new Configuration().configure("/aries.cfg.xml").buildSessionFactory();
		}
		catch (Exception e) {
			LOGGER.error("Initial SessionFactory creation failed.", e);
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 * Retrieve the session factory
	 * 
	 * @return The session factory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
