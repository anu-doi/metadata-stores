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
package au.edu.anu.metadatastores.service.search;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.glassfish.jersey.server.mvc.Viewable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import au.edu.anu.metadatastores.security.PermissionService;
import au.edu.anu.metadatastores.store.datacommons.DataCommonsObject;
import au.edu.anu.metadatastores.store.digitalcollections.DigitalCollection;
import au.edu.anu.metadatastores.store.dublincore.xml.DublinCore;
import au.edu.anu.metadatastores.store.epress.Epress;
import au.edu.anu.metadatastores.store.grants.Grant;
import au.edu.anu.metadatastores.store.people.Person;
import au.edu.anu.metadatastores.store.publication.Publication;
import au.edu.anu.metadatastores.store.search.SearchService;

/**
 * <p>DisplayPage<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Generates the viewables to return the page in</p>
 * 
 * @author Genevieve Turner
 *
 */
@Service("displayPage")
public class DisplayPage {
	static final Logger LOGGER = LoggerFactory.getLogger(DisplayPage.class);
	
	SearchService itemService_ = SearchService.getSingleton();
	
	//@Inject
	@Resource(name="permissionServiceImpl")
	PermissionService permissionService;
	
	/**
	 * Get the page from the id.
	 * 
	 * @param id
	 * @return
	 */
	public Viewable getPage(Long id) throws Exception {
		// Get the object associated with the id
		Object object = itemService_.getItemById(id);
		// Get the viewable for the object
		return getPage(object);
	}
	
	/**
	 * Get the page
	 * 
	 * @param obj The object
	 * @return The viewable
	 */
	public Viewable getPage(Object obj) throws Exception {
		for (Method method: DisplayPage.class.getMethods()) {
			if (method.getName().equals("getPage") && method.getParameterTypes()[0] == obj.getClass()) {
				try {
					Viewable returnObj = (Viewable) method.invoke(this, obj);
					return returnObj;
				}
				catch (InvocationTargetException e) {
					if (e.getCause() instanceof AccessDeniedException) {
						throw new AccessDeniedException(e.getCause().getMessage());
					}
					else {
						LOGGER.info("Error invoking method");
						throw e;
					}
				}
			}
		}

		throw new RuntimeException("Cannot get the page for the class " + obj.getClass().getName());
	}
	
	/**
	 * Get the viewable for a Grant object
	 * 
	 * @param grant The grant to create a web page for
	 * @return The viewable
	 */
	public Viewable getPage(Grant grant) throws AccessDeniedException {
		permissionService.checkGrant();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("grant", grant);
		
		return new Viewable("grant.jsp", model);
	}
	
	/**
	 * Get the viewable for a Publication object
	 * 
	 * @param publication The publication to create a web page for
	 * @return The viewable
	 */
	public Viewable getPage(Publication publication) throws AccessDeniedException {
		permissionService.checkPublication();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("publication", publication);
		
		return new Viewable("publication.jsp", model);
	}
	
	/**
	 * Get the viewable for a Person object
	 * 
	 * @param person The person to create a web page for
	 * @return The viewable
	 */
	public Viewable getPage(Person person) throws AccessDeniedException {
		permissionService.checkPerson();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("person", person);
		
		return new Viewable("person.jsp", model);
	}
	
	/**
	 * Get the viewable for a DublinCore object
	 * 
	 * @param dublinCore The dublin core object to create a web page for
	 * @return The viewable
	 */
	public Viewable getPage(DublinCore dublinCore) throws AccessDeniedException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("dublinCore", dublinCore);
		
		return new Viewable("dublin_core.jsp", model);
	}
	
	/**
	 * Get the viewable for a Data Commons object
	 * 
	 * @param dataCommons The Data Commons object to create a web page for
	 * @return The viewable
	 * @throws AccessDeniedException
	 */
	public Viewable getPage(DataCommonsObject dataCommons) throws AccessDeniedException {
		permissionService.checkDataCommonsObject();
		return getPage((DublinCore) dataCommons);
	}
	
	/**
	 * Get the viewable for the Digital Collections object
	 * 
	 * @param digitalCollection The Digital Collections object to create a web page for
	 * @return The viewable
	 * @throws AccessDeniedException
	 */
	public Viewable getPage(DigitalCollection digitalCollection) throws AccessDeniedException {
		permissionService.checkDigitalCollection();
		return getPage((DublinCore) digitalCollection);
	}
	
	/**
	 * Get the viewable for an E Press record
	 * 
	 * @param epress The E Press object to create a web page for
	 * @return The viewable
	 */
	public Viewable getPage(Epress epress) throws AccessDeniedException {
		permissionService.checkEpress();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("epress", epress);
		
		return new Viewable("epress.jsp", model);
	}
}
