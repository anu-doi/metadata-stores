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

package au.edu.anu.metadatastores.store.digitalcollections;

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.harvester.HarvestContent;
import au.edu.anu.metadatastores.datamodel.store.ItemRelation;
import au.edu.anu.metadatastores.datamodel.store.ItemRelationId;
import au.edu.anu.metadatastores.datamodel.store.PotentialRelation;
import au.edu.anu.metadatastores.datamodel.store.PotentialRelationId;
import au.edu.anu.metadatastores.harvester.HarvestContentService;
import au.edu.anu.metadatastores.services.ldap.LdapService;
import au.edu.anu.metadatastores.services.store.StoreHibernateUtil;
import au.edu.anu.metadatastores.store.dublincore.DublinCoreItem;
import au.edu.anu.metadatastores.store.dublincore.DublinCoreService;
import au.edu.anu.metadatastores.store.dublincore.xml.DublinCore;
import au.edu.anu.metadatastores.store.people.PersonItem;
import au.edu.anu.metadatastores.store.people.PersonService;
import au.edu.anu.metadatastores.store.properties.StoreProperties;
import au.edu.anu.metadatastores.store.publication.PublicationItem;
import au.edu.anu.metadatastores.store.publication.PublicationService;

/**
 * <p>DigitalCollectionsService<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Service class to process harvested digital collections records</p>
 * 
 * @author Genevieve Turner
 *
 */
public class DigitalCollectionsService extends DublinCoreService {
	static final Logger LOGGER = LoggerFactory.getLogger(DigitalCollectionsService.class);
	
	private static DigitalCollectionsService singleton_;
	private static final String extSystem_ = "DIGITAL_COLLECTIONS";
	HarvestContentService contentService_ = HarvestContentService.getSingleton();
	LdapService ldapService_ = LdapService.getSingleton();
	PersonService personService_ = PersonService.getSingleton();
	PublicationService publicationService_ = PublicationService.getSingleton();
	
	/**
	 * Constructor
	 */
	private DigitalCollectionsService() {
		
	}
	
	/**
	 * Get the singleton instance of DigitalCollectionsService
	 * 
	 * @return The DigitalCollectionsService object
	 */
	public static DigitalCollectionsService getSingleton() {
		if (singleton_ == null) {
			singleton_ = new DigitalCollectionsService();
		}
		return singleton_;
	}
	
	/**
	 * Process the content
	 */
	public void processHarvestContent() {
		String digitalCollectionsLocation = StoreProperties.getProperty("harvest.location.digitalcollections");

		HarvestContent content = contentService_.getNextHarvestContent(digitalCollectionsLocation);
		
		while (content != null) {
			LOGGER.debug("ID: {}, Identifier: {}, Content: {}", new Object[] {content.getHid(), content.getIdentifier(), content.getContent()});
			if ("deleted".equals(content.getContent())) {
				processDeleted(content);
			}
			else {
				processRecord(content);
			}
			contentService_.deleteHarvestContent(content);
			content = contentService_.getNextHarvestContent(digitalCollectionsLocation);
		}
	}

	/**
	 * Set the status of the record to deleted
	 * 
	 * @param content The stub of the content to delete
	 */
	private void processDeleted(HarvestContent content) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("FROM DigitalCollectionsItem WHERE extSystem = :extSystem AND extId = :extId");
		query.setParameter("extSystem", extSystem_);
		query.setParameter("extId", content.getIdentifier());
		
		DigitalCollectionsItem item = (DigitalCollectionsItem) query.uniqueResult();
		
		if (item != null) {
			item.setDeleted(Boolean.TRUE);
			session.merge(item);
		}
		else {
			LOGGER.debug("No record to be deleted: {}", content.getIdentifier());
		}
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Process the record
	 * 
	 * @param content The harvested content
	 */
	private void processRecord(HarvestContent content) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.enableFilter("attributes");
		LOGGER.debug("Identifier: {}", content.getIdentifier());
		
		Query query = session.createQuery("FROM DigitalCollectionsItem WHERE extSystem = :extSystem AND extId = :extId");
		query.setParameter("extSystem", extSystem_);
		query.setParameter("extId", content.getIdentifier());
		
		DigitalCollectionsItem item = (DigitalCollectionsItem) query.uniqueResult();
		if (item == null) {
			item = new DigitalCollectionsItem();
			item.setExtSystem(extSystem_);
			item.setExtId(content.getIdentifier());
			session.save(item);
		}
		
		try {
			JAXBContext context = JAXBContext.newInstance(DublinCore.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			DublinCore dublinCore = (DublinCore) unmarshaller.unmarshal(new StringReader(content.getContent()));
			Date lastModified = new Date();
			super.processRecord((DublinCoreItem) item, dublinCore, session, lastModified);
		}
		catch (JAXBException e) {
			LOGGER.error("Exception transforming document", e);
		}
		catch (InvocationTargetException e) {
			LOGGER.error("Error invoking method", e);
		}
		catch (IllegalAccessException e) {
			LOGGER.error("Error accessing method", e);
		}
		
		session.merge(item);
		
		LOGGER.info("Item Numbers: {}", item.getItemAttributes().size());
		
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Set up the relationships or potential relationships.
	 * 
	 * @param item The dublin core item to add relations to
	 * @param dublinCore The dublin core values
	 * @param session The session
	 */
	protected void setRelations(DublinCoreItem item, DublinCore dublinCore, Session session) {
		//TODO figure out how to remove aries relationship?
		//Attempt to match Aries ID's
		Pattern pattern = Pattern.compile("(Migrated|[uaf]\\d*)xPUB\\d*", Pattern.CASE_INSENSITIVE);
		Long ariesPublicationId = null;
		for (String identifier : dublinCore.getIdentifiers()) {
			Matcher matcher = pattern.matcher(identifier);
			if (matcher.matches()) {
				LOGGER.info("Match for: {}", identifier);
				PublicationItem pubItem = publicationService_.getPublicationItem(identifier);
				if (pubItem != null) {
					ariesPublicationId = pubItem.getIid();
					ItemRelationId id = new ItemRelationId(item.getIid(), "isSameAs", pubItem.getIid());
					boolean found = false;
					for (ItemRelation relation : item.getItemRelationsForIid()) {
						if (relation.getId().equals(id)) {
							found = true;
							break;
						}
					}
					if (!found) {
						ItemRelation relation = new ItemRelation();
						relation.setId(id);
						item.getItemRelationsForIid().add(relation);
					}
				}
				else {
					LOGGER.info("Publication item is null");
				}
			}
		}
		// Find the creators!
		Set<PotentialRelation> relations = item.getPotentialRelationsForIid();
		String creator = null;
		for (int i = 0; i < dublinCore.getCreators().size(); i++) {
			creator = dublinCore.getCreators().get(i);
			LOGGER.debug("Creator: {}", creator);
			String[] creatorParts = creator.split(",");
			if (creatorParts.length >= 2) {
				List<PersonItem> people = null;
				if (ariesPublicationId != null) {
					people = publicationService_.searchPublicationPerson(ariesPublicationId, creatorParts[1].trim(), creatorParts[0].trim());
					if (people != null) {
						LOGGER.debug("Number of aries people matched: {}", people.size());
					}
				}
				if (people != null && people.size() == 0) {
					people = personService_.getPersonItemByName(creatorParts[1].trim(), creatorParts[0].trim());
				}
				if (people != null && people.size() > 0) {
					PotentialRelationId prId = null;
					PotentialRelation pr = null;
					boolean found = false;
					for (PersonItem person : people) {
						prId = new PotentialRelationId(item.getIid(), "hasCreator", person.getIid());
						Iterator<PotentialRelation> it = relations.iterator();
						while (it.hasNext()) {
							pr = it.next();
							if (pr.getId().equals(prId)) {
								found = true;
								break;
							}
						}
						if (!found) {
							PotentialRelation newPr = new PotentialRelation();
							newPr.setId(prId);
							newPr.setRequireCheck(Boolean.TRUE);
							item.getPotentialRelationsForIid().add(newPr);
						}
						found = false;
					}
				}
			}
		}
	}
	
	/**
	 * Set the reverse relations.
	 * 
	 * Unused at this point in time for digital collections
	 */
	protected void setReverseRelations(DublinCoreItem item, DublinCore dublinCore, Session session) {
		
	}
}
