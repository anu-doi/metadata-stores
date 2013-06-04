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

package au.edu.anu.metadatastores.store.dublincore;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.xml.bind.JAXBException;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.annotations.ItemTraitParser;
import au.edu.anu.metadatastores.store.dublincore.xml.DublinCore;
import au.edu.anu.metadatastores.store.misc.AbstractItemService;

/**
 * <p>DublinCoreService<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Abstract Service for classes Item classes that are coming from the dublin core format</p>
 * 
 * @author Genevieve Turner
 *
 */
public abstract class DublinCoreService extends AbstractItemService {
	static final Logger LOGGER = LoggerFactory.getLogger(DublinCoreService.class);
	
	/**
	 * Process the harvested content
	 */
	public abstract void processHarvestContent();
	
	/**
	 * Process the dublin core record
	 * 
	 * @param item The item to update
	 * @param dublinCore The dublin core of the record
	 * @param session The current sesion
	 * @param lastModified The last modified date
	 * @throws JAXBException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	protected void processRecord(DublinCoreItem item, DublinCore dublinCore, Session session, Date lastModified) 
			throws JAXBException, InvocationTargetException, IllegalAccessException {
		if (dublinCore.getTitles().size() > 0) {
			item.setTitle(dublinCore.getTitles().get(0));
		}
		
		ItemTraitParser parser = new ItemTraitParser();
		Item newItem = null;
		newItem = parser.getItem(dublinCore, lastModified);
		
		updateAttributesFromItem(item, newItem, session, lastModified);
		
		setRelations(item, dublinCore, session);
		setReverseRelations(item, dublinCore, session);
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
