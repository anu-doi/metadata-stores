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
package au.edu.anu.metadatastores.store.util;

import java.lang.reflect.Method;

import au.edu.anu.metadatastores.store.datacommons.DataCommonsItem;
import au.edu.anu.metadatastores.store.datacommons.DataCommonsObject;
import au.edu.anu.metadatastores.store.datacommons.DataCommonsService;
import au.edu.anu.metadatastores.store.digitalcollections.DigitalCollection;
import au.edu.anu.metadatastores.store.digitalcollections.DigitalCollectionsItem;
import au.edu.anu.metadatastores.store.digitalcollections.DigitalCollectionsService;
import au.edu.anu.metadatastores.store.dublincore.xml.DublinCore;
import au.edu.anu.metadatastores.store.epress.Epress;
import au.edu.anu.metadatastores.store.epress.EpressItem;
import au.edu.anu.metadatastores.store.epress.EpressService;
import au.edu.anu.metadatastores.store.grants.Grant;
import au.edu.anu.metadatastores.store.grants.GrantItem;
import au.edu.anu.metadatastores.store.grants.GrantService;
import au.edu.anu.metadatastores.store.people.Person;
import au.edu.anu.metadatastores.store.people.PersonItem;
import au.edu.anu.metadatastores.store.people.PersonService;
import au.edu.anu.metadatastores.store.publication.Publication;
import au.edu.anu.metadatastores.store.publication.PublicationItem;
import au.edu.anu.metadatastores.store.publication.PublicationService;

/**
 * <p>ItemResolver<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p></p>
 * 
 * @author Genevieve Turner
 *
 */
public class ItemResolver {
	/**
	 * Resolve the object type from the item
	 * 
	 * @param obj The item object to resolve
	 * @return the resolved object
	 */
	public static Object resolve(Object obj) {
		for (Method resolver : ItemResolver.class.getMethods()) {
			if (resolver.getName().equals("resolve") && resolver.getParameterTypes()[0] == obj.getClass()) {
				try {
					Object returnObj = resolver.invoke(null, obj);
					return returnObj;
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		throw new RuntimeException("Cannot resolve the class " + obj.getClass().getName());
	}
	
	/**
	 * Get the publication object from the publication item
	 * 
	 * @param item The publication item
	 * @return The publication object
	 */
	public static Object resolve(PublicationItem item) {
		return PublicationService.getSingleton().getPublication(item);
	}
	
	/**
	 * Get the grant object from the grant item
	 * 
	 * @param item The grant item
	 * @return The grant object
	 */
	public static Object resolve(GrantItem item) {
		return GrantService.getSingleton().getGrant(item);
	}
	
	/**
	 * Get the person object from the person item
	 * 
	 * @param item The person item
	 * @return The person
	 */
	public static Object resolve(PersonItem item) {
		return PersonService.getSingleton().getPerson(item);
	}
	
	/**
	 * Get the data commons object from the data commons item
	 * 
	 * @param item The data commons/dublin core object
	 * @return
	 */
	public static Object resolve(DataCommonsItem item) {
		return DataCommonsService.getSingleton().getDataCommonsObject(item);
	}
	
	/**
	 * Get the digital collections object from the digital collections item
	 * 
	 * @param item The digital collections item
	 * @return The digital collections/dublin core object
	 */
	public static Object resolve(DigitalCollectionsItem item) {
		return DigitalCollectionsService.getSingleton().getDigitalCollection(item);
	}
	
	/**
	 * Get the E Press object from the E Press item
	 * 
	 * @param item The E Press item
	 * @return The E Press object
	 */
	public static Object resolve(EpressItem item) {
		return EpressService.getSingleton().getEpress(item);
	}
	
	/**
	 * Resolve the class type with the given string of the ext system
	 * 
	 * @param extSystem The ext system
	 * @return The class type
	 */
	public static Class<?> resolveTypeBySystemId(String extSystem) {
		Class<?> clazz = null;
		if ("GRANT".equals(extSystem)) {
			clazz = Grant.class;
		}
		else if ("PERSON".equals(extSystem)) {
			clazz = Person.class;
		}
		else if ("PUBLICATION".equals(extSystem)) {
			clazz = Publication.class;
		}
		else if ("EPRESS".equals(extSystem)) {
			clazz = Epress.class;
		}
		else if ("DATA_COMMONS".equals(extSystem)) {
			//clazz = DublinCore.class;
			clazz = DataCommonsObject.class;
		}
		else if ("DIGITAL_COLLECTIONS".equals(extSystem)) {
			//clazz = DublinCore.class;
			clazz = DigitalCollection.class;
		}
		return clazz;
	}
}
