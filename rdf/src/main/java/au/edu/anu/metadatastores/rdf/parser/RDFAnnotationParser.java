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
package au.edu.anu.metadatastores.rdf.parser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.rdf.annotation.RDFDefaultTriple;
import au.edu.anu.metadatastores.rdf.annotation.RDFSet;
import au.edu.anu.metadatastores.rdf.annotation.RDFSetWithDefault;
import au.edu.anu.metadatastores.rdf.annotation.RDFSets;
import au.edu.anu.metadatastores.rdf.annotation.RDFSubject;
import au.edu.anu.metadatastores.rdf.annotation.RDFType;
import au.edu.anu.metadatastores.rdf.annotation.RDFUri;
import au.edu.anu.metadatastores.rdf.exception.RDFException;
import au.edu.anu.metadatastores.rdf.namespace.RDFNS;
import au.edu.anu.metadatastores.util.properties.PropertyLoader;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;


public class RDFAnnotationParser {
	static final Logger LOGGER = LoggerFactory.getLogger(RDFAnnotationParser.class);

	private static final Properties properties_ = PropertyLoader.loadProperties("rdf.properties");
	
	/**
	 * Generate the resource from the provided object
	 * 
	 * @param id The id of the resource
	 * @param object The object to get the fields for
	 * @param model The model object
	 * @return The generated resource
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws RDFException
	 */
	public Resource getResource(String id, Object object, Model model) throws RDFException {
		Resource resource = null;
		
		Class<?> clazz = object.getClass();
		
		if (id != null && id.trim().length() > 0) {
			resource = model.createResource(properties_.getProperty("rdf.object.uri") + id);
		}
		else {
			throw new RDFException("No id for resource");
		}
		
		if (clazz.isAnnotationPresent(RDFType.class)) {
			RDFType type = clazz.getAnnotation(RDFType.class);
			Resource typeResource = model.createResource(properties_.getProperty("rdf.object.uri") + type.value());
			resource.addProperty(model.createProperty(RDFNS.TYPE), typeResource);
		}
		else {
			LOGGER.info("For some reason there is no type?");
		}
		
		addResourceProperties(model, resource, object);
		return resource;
	}
	
	public Resource getResource(String id, String system, Object object, Model model) throws RDFException {
		Resource resource = createResource(model, id);
		
		if (system != null) {
			Resource typeResource = model.createResource(properties_.getProperty("rdf.object.uri") + system);
			resource.addProperty(model.createProperty(RDFNS.TYPE), typeResource);
		}

		addResourceProperties(model, resource, object);
		
		return resource;
	}
	
	private void addResourceProperties(Model model, Resource resource, Object object) throws RDFException {
		Class<?> clazz = object.getClass();

		if (clazz.isAnnotationPresent(RDFSets.class)) {
			RDFSets sets = clazz.getAnnotation(RDFSets.class);
			for (RDFSet set : sets.sets()) {
				processSet(model, resource, set, object);
			}
			for (RDFSetWithDefault set : sets.setsWithDefaults()) {
				processSetWithDefault(model, resource, set, object);
			}
		}
		else if (clazz.isAnnotationPresent(RDFSet.class)) {
			RDFSet set = clazz.getAnnotation(RDFSet.class);
			processSet(model, resource, set, object);
		}
		
		addProperties(model, resource, object);
		
	}
	
	private Resource createResource(Model model, String id) throws RDFException {
		if (id != null && id.trim().length() > 0) {
			return model.createResource(properties_.getProperty("rdf.object.uri") + id);
		}
		else {
			throw new RDFException("No id for resource");
		}
	}
		
	/**
	 * Add the field properties to the resource
	 * 
	 * @param model The model object
	 * @param resource The resource to add properties to
	 * @param object The object to add properties to the resource from
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	private void addProperties(Model model, Resource resource, Object object) 
			throws RDFException {
			//throws InvocationTargetException, IllegalAccessException {
		Method[] methods = object.getClass().getMethods();
		
		for (Method method : methods) {
			if (method.isAnnotationPresent(RDFSubject.class) && method.isAnnotationPresent(RDFUri.class)) {
				RDFUri uri = method.getAnnotation(RDFUri.class);
				try {
					Object methodObj = method.invoke(object);
					if (methodObj != null) {
						if (methodObj instanceof String) {
							addProperty(model, resource, uri, methodObj);
						}
						else if (methodObj instanceof Collection) {
							Collection<?> collObj = (Collection<?>) methodObj;
							addProperties(model, resource, uri, collObj);
						}
						else {
							//TODO
							LOGGER.info("Is not a string, {}", methodObj.getClass().getName());
						}
					}
				}
				catch (InvocationTargetException e) {
					throw new RDFException(e);
				}
				catch (IllegalAccessException e) {
					throw new RDFException(e);
				}
			}
			else {
				//LOGGER.info("Method does not have annotation. {}", method.getName());
			}
		}
	}
	
	/**
	 * Add the field properties to the resource
	 * 
	 * @param model The model object
	 * @param resource The resource to add properties to
	 * @param uri The uri to add the properties under
	 * @param coll The collection to add to the resources properties
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	private void addProperties(Model model, Resource resource, RDFUri uri, Collection<?> coll) 
			throws RDFException {
			//throws InvocationTargetException, IllegalAccessException {
		for (Object object : coll) {
			if (object instanceof String) {
				addProperty(model, resource, uri, object);
			}
			else {
				Resource unknownResource = model.createResource();
				addProperties(model, unknownResource, object);
				resource.addProperty(model.createProperty(uri.uri()), unknownResource);
			}
		}
	}
	
	/**
	 * Add a single property to the resource
	 * 
	 * @param model The model object
	 * @param resource The resource to add a property to
	 * @param uri The uri to add the property under
	 * @param object The object to add
	 */
	private void addProperty(Model model, Resource resource, RDFUri uri, Object object) {
		String value = (String) object;
		resource.addProperty(model.createProperty(uri.uri()), value);
	}
	
	/**
	 * Add properties from a set
	 * 
	 * @param model The model object
	 * @param parentResource The parent resource to add the set resource to
	 * @param set The set
	 * @param object The object that contains the values of properties to add
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	private void processSet(Model model, Resource parentResource, RDFSet set, Object object) 
			throws RDFException {
			//throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Resource resource = model.createResource();
		Class<?> clazz = object.getClass();
		try {
			for (String field : set.fields()) {
				String methodName = "get" + field.substring(0,1).toUpperCase() + field.substring(1);
				Method method = clazz.getMethod(methodName);
				if (method.isAnnotationPresent(RDFUri.class)) {
					RDFUri uri = method.getAnnotation(RDFUri.class);
					Object value = method.invoke(object);
					if (value instanceof String) {
						resource.addProperty(model.createProperty(uri.uri()), value.toString());
					}
					else if (value instanceof Collection) {
						Collection<?> coll = (Collection<?>) value;
						for (Object o : coll) {
							resource.addProperty(model.createProperty(uri.uri()), o.toString());
						}
					}
				}
				else {
					LOGGER.error("No URI declared for method: {}", methodName);
				}
			}
		}
		catch (NoSuchMethodException e) {
			throw new RDFException(e);
		}
		catch (InvocationTargetException e) {
			throw new RDFException(e);
		}
		catch (IllegalAccessException e) {
			throw new RDFException(e);
		}
		parentResource.addProperty(model.createProperty(set.uri()), resource);
	}
	
	/**
	 * Add properties from a set that contains default values
	 * 
	 * @param model The model object
	 * @param parentResource The parent resource to add the set resource to
	 * @param set The set
	 * @param object The object that contains the values of properties to add
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	private void processSetWithDefault(Model model, Resource parentResource, RDFSetWithDefault set, Object object) 
			throws RDFException {
			//throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		String field = set.field();
		String methodName = "get" + field.substring(0,1).toUpperCase() + field.substring(1);
		Class<?> clazz = object.getClass();
		try {
			Method method = clazz.getMethod(methodName);
			if (method.isAnnotationPresent(RDFUri.class)) {
				RDFUri uri = method.getAnnotation(RDFUri.class);
				Object value = method.invoke(object);
				if (value instanceof String) {
					Resource resource = model.createResource();
					resource.addProperty(model.createProperty(uri.uri()), value.toString());
					for (RDFDefaultTriple triple : set.defaults()) {
						resource.addProperty(model.createProperty(triple.predicate()), triple.object());
					}
					parentResource.addProperty(model.createProperty(set.uri()), resource);
				}
				else if (value instanceof Collection) {
					Collection<?> coll = (Collection<?>) value;
					for (Object o : coll) {
						Resource resource = model.createResource();
						resource.addProperty(model.createProperty(uri.uri()), o.toString());
						for (RDFDefaultTriple triple : set.defaults()) {
							resource.addProperty(model.createProperty(triple.predicate()), triple.object());
						}
						parentResource.addProperty(model.createProperty(set.uri()), resource);
					}
				}
			}
		}
		catch (NoSuchMethodException e) {
			throw new RDFException(e);
		}
		catch (InvocationTargetException e) {
			throw new RDFException(e);
		}
		catch (IllegalAccessException e) {
			throw new RDFException(e);
		}
	}
	
	/**
	 * 
	 * @param resource
	 */
	/*private void removeStatements(Resource resource) {
		StmtIterator stmtIt = resource.listProperties();
		while (stmtIt.hasNext()) {
			Statement stmt = stmtIt.next();
			RDFNode node = stmt.getObject();
			
			if (node.isResource() && node.asResource().isAnon()) {
				removeStatements(node.asResource());
			}
		}
		resource.removeProperties();
	}*/
}
