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
package au.edu.anu.metadatastores.services.people;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

import au.edu.anu.metadatastores.store.people.Person;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;

/**
 * <p>PersonJAXBContextResolver<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Context resolver for JSON</p>
 * 
 * @author Genevieve Turner
 *
 */
@Provider
public class PersonJAXBContextResolver implements ContextResolver<JAXBContext> {
	private JAXBContext context;
	private Class<?>[] types = { Person.class };
	
	/**
	 * Constructor
	 * 
	 * @throws Exception
	 */
	public PersonJAXBContextResolver() throws Exception {
		this.context = new JSONJAXBContext(JSONConfiguration.natural().build(), types);
	}
	
	/**
	 * @see javax.ws.rs.ext.ContextResolver#getContext(java.lang.Class)
	 */
	@Override
	public JAXBContext getContext(Class<?> objectType) {
		for (Class<?> c : types) {
			if (c.equals(objectType)) {
				return context;
			}
		}
		return null;
	}

}
