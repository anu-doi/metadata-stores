package au.edu.anu.metadatastores.store.dublincore;

import java.util.Date;

import javax.xml.bind.JAXBException;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;
import au.edu.anu.metadatastores.store.dublincore.xml.DublinCore;
import au.edu.anu.metadatastores.store.misc.AbstractItemService;

public abstract class DublinCoreService extends AbstractItemService {
	static final Logger LOGGER = LoggerFactory.getLogger(DublinCoreService.class);
	
	public abstract void processHarvestContent();
	
	/**
	 * Process the dublin core record
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current sesion
	 * @param lastModified The last modified date
	 * @throws JAXBException
	 */
	protected void processRecord(DublinCoreItem item, DublinCore dublinCore, Session session, Date lastModified) throws JAXBException {
		if (dublinCore.getTitles().size() > 0) {
			item.setTitle(dublinCore.getTitles().get(0));
		}
		
		setItemTitles(item, dublinCore, session, lastModified);
		setItemCreators(item, dublinCore, session, lastModified);
		setItemSubjects(item, dublinCore, session, lastModified);
		setItemDescriptions(item, dublinCore, session, lastModified);
		setItemPublishers(item, dublinCore, session, lastModified);
		setItemContributors(item, dublinCore, session, lastModified);
		setItemDates(item, dublinCore, session, lastModified);
		setItemTypes(item, dublinCore, session, lastModified);
		setItemFormats(item, dublinCore, session, lastModified);
		setItemIdentifiers(item, dublinCore, session, lastModified);
		setItemSources(item, dublinCore, session, lastModified);
		setItemLanguages(item, dublinCore, session, lastModified);
		setItemRelations(item, dublinCore, session, lastModified);
		setItemCoverage(item, dublinCore, session, lastModified);
		setItemRights(item, dublinCore, session, lastModified);
		setRelations(item, dublinCore, session);
		setReverseRelations(item, dublinCore, session);
	}
	
	/**
	 * Set the titles from the dublin core record provided
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current active hibernate session
	 * @param lastModified The last modified date
	 */
	protected void setItemTitles(DublinCoreItem item, DublinCore dublinCore, Session session, Date lastModified) {
		setMultipleAttribute(item, item.getTitles(), dublinCore.getTitles(), StoreAttributes.TITLE, session, lastModified, Boolean.FALSE);
	}
	
	/**
	 * Set the creators from the dublin core record provided
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current active hibernate session
	 * @param lastModified The last modified date
	 */
	protected void setItemCreators(DublinCoreItem item, DublinCore dublinCore, Session session, Date lastModified) {
		setMultipleAttribute(item, item.getCreators(), dublinCore.getCreators(), StoreAttributes.CREATOR, session, lastModified, Boolean.FALSE);
	}
	
	/**
	 * Set the subjects from the dublin core record provided
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current active hibernate session
	 * @param lastModified The last modified date
	 */
	protected void setItemSubjects(DublinCoreItem item, DublinCore dublinCore, Session session, Date lastModified) {
		setMultipleAttribute(item, item.getSubjects(), dublinCore.getSubjects(), StoreAttributes.SUBJECT, session, lastModified, Boolean.FALSE);
	}
	
	/**
	 * Set the descriptions from the dublin core record provided
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current active hibernate session
	 * @param lastModified The last modified date
	 */
	protected void setItemDescriptions(DublinCoreItem item, DublinCore dublinCore, Session session, Date lastModified) {
		setMultipleAttribute(item, item.getDescriptions(), dublinCore.getDescriptions(), StoreAttributes.DESCRIPTION, session, lastModified, Boolean.FALSE);
	}
	
	/**
	 * Set the publishers from the dublin core record provided
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current active hibernate session
	 * @param lastModified The last modified date
	 */
	protected void setItemPublishers(DublinCoreItem item, DublinCore dublinCore, Session session, Date lastModified) {
		setMultipleAttribute(item, item.getPublishers(), dublinCore.getPublishers(), StoreAttributes.PUBLISHER, session, lastModified, Boolean.FALSE);
	}
	
	/**
	 * Set the contributors from the dublin core record provided
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current active hibernate session
	 * @param lastModified The last modified date
	 */
	protected void setItemContributors(DublinCoreItem item, DublinCore dublinCore, Session session, Date lastModified) {
		setMultipleAttribute(item, item.getContributors(), dublinCore.getContributors(), StoreAttributes.CONTRIBUTOR, session, lastModified, Boolean.FALSE);
	}
	
	/**
	 * Set the dates from the dublin core record provided
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current active hibernate session
	 * @param lastModified The last modified date
	 */
	protected void setItemDates(DublinCoreItem item, DublinCore dublinCore, Session session, Date lastModified) {
		setMultipleAttribute(item, item.getDates(), dublinCore.getDates(), StoreAttributes.DATE, session, lastModified, Boolean.FALSE);
	}
	
	/**
	 * Set the types from the dublin core record provided
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current active hibernate session
	 * @param lastModified The last modified date
	 */
	protected void setItemTypes(DublinCoreItem item, DublinCore dublinCore, Session session, Date lastModified) {
		setMultipleAttribute(item, item.getTypes(), dublinCore.getTypes(), StoreAttributes.TYPE, session, lastModified, Boolean.FALSE);
	}
	
	/**
	 * Set the formats from the dublin core record provided
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current active hibernate session
	 * @param lastModified The last modified date
	 */
	protected void setItemFormats(DublinCoreItem item, DublinCore dublinCore, Session session, Date lastModified) {
		setMultipleAttribute(item, item.getFormats(), dublinCore.getFormats(), StoreAttributes.FORMAT, session, lastModified, Boolean.FALSE);
	}
	
	/**
	 * Set the identifiers from the dublin core record provided
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current active hibernate session
	 * @param lastModified The last modified date
	 */
	protected void setItemIdentifiers(DublinCoreItem item, DublinCore dublinCore, Session session, Date lastModified) {
		setMultipleAttribute(item, item.getIdentifiers(), dublinCore.getIdentifiers(), StoreAttributes.IDENTIFIER, session, lastModified, Boolean.FALSE);
	}
	
	/**
	 * Set the sources from the dublin core record provided
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current active hibernate session
	 * @param lastModified The last modified date
	 */
	protected void setItemSources(DublinCoreItem item, DublinCore dublinCore, Session session, Date lastModified) {
		setMultipleAttribute(item, item.getSources(), dublinCore.getSources(), StoreAttributes.SOURCE, session, lastModified, Boolean.FALSE);
	}
	
	/**
	 * Set the languages from the dublin core record provided
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current active hibernate session
	 * @param lastModified The last modified date
	 */
	protected void setItemLanguages(DublinCoreItem item, DublinCore dublinCore, Session session, Date lastModified) {
		setMultipleAttribute(item, item.getLanguages(), dublinCore.getLanguages(), StoreAttributes.LANGUAGE, session, lastModified, Boolean.FALSE);
	}
	
	/**
	 * Set the relations from the dublin core record provided
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current active hibernate session
	 * @param lastModified The last modified date
	 */
	protected void setItemRelations(DublinCoreItem item, DublinCore dublinCore, Session session, Date lastModified) {
		setMultipleAttribute(item, item.getRelations(), dublinCore.getRelations(), StoreAttributes.RELATION, session, lastModified, Boolean.FALSE);
	}
	
	/**
	 * Set the coverage from the dublin core record provided
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current active hibernate session
	 * @param lastModified The last modified date
	 */
	protected void setItemCoverage(DublinCoreItem item, DublinCore dublinCore, Session session, Date lastModified) {
		setMultipleAttribute(item, item.getCoverage(), dublinCore.getCoverage(), StoreAttributes.COVERAGE, session, lastModified, Boolean.FALSE);
	}
	
	/**
	 * Set the rights from the dublin core record provided
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current active hibernate session
	 * @param lastModified The last modified date
	 */
	protected void setItemRights(DublinCoreItem item, DublinCore dublinCore, Session session, Date lastModified) {
		setMultipleAttribute(item, item.getRights(), dublinCore.getRights(), StoreAttributes.RIGHTS, session, lastModified, Boolean.FALSE);
	}
	
	/**
	 * Set the relations
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current active hibernate session
	 */
	protected abstract void setRelations(DublinCoreItem item, DublinCore dublinCore, Session session);
	
	/**
	 * Set the reverse relations
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current active hibernate session
	 */
	protected abstract void setReverseRelations(DublinCoreItem item, DublinCore dublinCore, Session session);
}
