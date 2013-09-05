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
package au.edu.anu.metadatastores.store.epress;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.annotations.ItemTraitParser;
import au.edu.anu.metadatastores.services.store.StoreHibernateUtil;
import au.edu.anu.metadatastores.store.exception.StoreException;
import au.edu.anu.metadatastores.store.misc.AbstractItemService;

/**
 * <p>EpressService<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p></p>
 * 
 * @author Genevieve Turner
 *
 */
public class EpressService extends AbstractItemService {
	static final Logger LOGGER = LoggerFactory.getLogger(EpressService.class);
	
	private static EpressService epressService_;
	
	public static EpressService getSingleton() {
		if (epressService_ == null) {
			epressService_ = new EpressService();
		}
		return epressService_;
	}
	
	protected EpressService() {
		
	}
	
	/**
	 * Process the E Press records
	 * 
	 * @param filename The name of the file to fetch records for
	 * @throws StoreException
	 */
	public List<Epress> fetchEpressRecords(String filename) throws StoreException {
		File file = new File(filename);
		return fetchEpressRecords(file);
	}
	
	/**
	 * Process the E Press records
	 * 
	 * @param file The name of the file to fetch records for
	 * @throws StoreException
	 */
	public List<Epress> fetchEpressRecords(File file) throws StoreException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Record.class);
			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Record records = (Record) jaxbUnmarshaller.unmarshal(file);
			for (Epress epress : records.getEpress()) {
				LOGGER.info("Title: {}", epress.getTitle());
				if (epress.getISBNs() != null && epress.getISBNs().size() > 0) {
					epress.setExtId(epress.getISBNs().get(0));
				}
				else if (epress.getISSNs() != null && epress.getISSNs().size() > 0) {
					epress.setExtId(epress.getISSNs().get(0));
				}
				else {
					//TODO should we update the rest of the records if there is a problem with the E Press record?
					throw new StoreException("Exception finding extId for record (i.e. no ISBN or ISSN): " + epress.getTitle());
				}
			}
			return records.getEpress();
		}
		catch (JAXBException e) {
			LOGGER.error("Exception processing {}", file.getAbsoluteFile(), e);
		}
		return null;
	}
	
	/**
	 * Save the E Press records
	 * 
	 * @param epressRecords The E Press records to save
	 * @return The items that were saved
	 */
	public List<EpressItem> saveEpressRecords(List<Epress> epressRecords) {
		List<EpressItem> epressItems = new ArrayList<EpressItem>();
		EpressItem epressItem = null;
		
		for (Epress epress : epressRecords) {
			epressItem = saveEpress(epress);
			epressItems.add(epressItem);
		}
		
		return epressItems;
	}
	
	/**
	 * Save a single E Press record
	 * 
	 * @param epress The E Press record t osave
	 * @return The E Press record item
	 */
	public EpressItem saveEpress(Epress epress) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			
			Query query = session.createQuery("FROM EpressItem WHERE extId = :extId");
			query.setParameter("extId", epress.getExtId());
			EpressItem item = (EpressItem) query.uniqueResult();

			Date lastModified = new Date();
			
			ItemTraitParser parser = new ItemTraitParser();
			Item newItem = null;
			newItem = parser.getItem(epress, lastModified);
			
			if (item == null) {
				item = new EpressItem();
				if (newItem.getExtId() == null) {
					return null;
				}
				item.setExtId(newItem.getExtId());
				session.save(item);
			}
			
			updateAttributesFromItem(item, newItem, session, lastModified);
			
			session.getTransaction().commit();
			return item;
		}
		catch(IllegalAccessException e) {
			LOGGER.error("Exception accessing field when trying to get an e press item", e);
		}
		catch (InvocationTargetException e) {
			LOGGER.error("Exception invoking method when trying to get an e press item", e);
		}
		finally {
			session.close();
		}
		
		return null;
	}
	
	/**
	 * Get the E Press record by its ext id (N.B. this is the ISBN or ISSN)
	 * 
	 * @param epressId The external id
	 * @return The E Press record that was found
	 */
	public Epress getEpress(String epressId) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		session.enableFilter("attributes");
		try {
			Query query = session.createQuery("FROM EpressItem WHERE extId = :extId");
			query.setParameter("extId", epressId);
			
			EpressItem item = (EpressItem) query.uniqueResult();
			Epress epress = getEpress(item);
			return epress;
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Get the E Press record object from the item
	 * 
	 * @param item The E Press record item
	 * @return The E Press record object
	 */
	public Epress getEpress(EpressItem item) {
		ItemTraitParser traitParser = new ItemTraitParser();
		try {
			Epress epress = (Epress) traitParser.getItemObject(item, Epress.class);
			return epress;
		}
		catch (Exception e) {
			LOGGER.error("Exception retrieving epress record", e);
		}
		
		return null;
	}
}
