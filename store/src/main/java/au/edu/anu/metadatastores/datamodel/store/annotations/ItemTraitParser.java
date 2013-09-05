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
package au.edu.anu.metadatastores.datamodel.store.annotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.ItemAttribute;
import au.edu.anu.metadatastores.store.misc.Subject;

/**
 * <p>ItemPropertiesParser<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Turns appropriately annotated objects into an Item and vice versa</p>
 * 
 * @author Genevieve Turner
 *
 */
public class ItemTraitParser {
	static final Logger LOGGER = LoggerFactory.getLogger(ItemTraitParser.class);
	
	/**
	 * Generates an Item for saving from an object
	 * 
	 * @param object The object to generate an Item against
	 * @param lastModified The last modified date
	 * @return The item generated from the object
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public Item getItem(Object object, Date lastModified) throws InvocationTargetException, IllegalAccessException {
		Method[] methods = object.getClass().getMethods();
		Item item = new Item();
		if (object.getClass().isAnnotationPresent(ItemTrait.class)) {
			ItemTrait itemTrait = object.getClass().getAnnotation(ItemTrait.class);
			String extId = itemTrait.extId();
			String title = itemTrait.title();
			try {
				if (extId != null && extId.length() > 0) {
					String extIdValue = getFirstFieldValue(object, extId);
					item.setExtId(extIdValue);
					LOGGER.info("Ext Id: {}", extIdValue);
				}
				if (title != null && title.length() > 0) {
					String titleValue = getFirstFieldValue(object, title);
					item.setTitle(titleValue);
					LOGGER.info("Title: {}", titleValue);
				}
			}
			catch (NoSuchFieldException e) {
				LOGGER.error("Could not find field", e);
			}
		}
		for (Method method : methods) {
			if (method.isAnnotationPresent(ItemAttributeTrait.class)) {
				ItemAttributeTrait trait = method.getAnnotation(ItemAttributeTrait.class);
				String attrType = trait.attrType();
				Object value = method.invoke(object);
				TraitType traitType = trait.traitType();
				if (value != null) {
					switch(traitType) {
					case STRING:
						String strValue = (String) value;
						if (strValue.length() > 0) {
							item.getItemAttributes().add(new ItemAttribute(item, attrType, strValue, lastModified));
						}
						break;
					case STRING_LIST:
						@SuppressWarnings("unchecked")
						Collection<String> collection = (Collection<String>) value;
						for (String collValue : collection) {
							item.getItemAttributes().add(new ItemAttribute(item, attrType, collValue, lastModified));
						}
						break;
					case SUBJECT_LIST:
						@SuppressWarnings("unchecked")
						Collection<Subject> subjects = (Collection<Subject>) value;
						for (Subject subject : subjects) {
							ItemAttribute subjAttr = new ItemAttribute(item, attrType, subject.getCode(), lastModified);
							setItemAttributeChildren(item, subjAttr, subject, lastModified);
							item.getItemAttributes().add(subjAttr);
						}
						break;
					case RELATION_LIST:
						@SuppressWarnings("unchecked")
						Collection<String> relations = (Collection<String>) value;
						fillItemAttributeRelations(item, relations, method, lastModified);
					}
				}
			}
		}
		
		return item;
	}
	
	/**
	 * Get the value of the field. If the field is a collection then it will return the first value found.
	 * 
	 * @param object The object to retrieve the field from
	 * @param fieldName The field name
	 * @return The value
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	private String getFirstFieldValue(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
		Field field = object.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		Object fieldObject = field.get(object);
		
		String returnValue = null;
		if (String.class.isAssignableFrom(fieldObject.getClass())) {
			returnValue = fieldObject.toString();
		}
		else if (Collection.class.isAssignableFrom(fieldObject.getClass())) {
			@SuppressWarnings("rawtypes")
			Collection collection = (Collection) fieldObject;
			@SuppressWarnings("rawtypes")
			Iterator it = collection.iterator();
			if (it.hasNext()) {
				Object firstObject = it.next();
				returnValue = firstObject.toString();
			}
		}
		else {
			LOGGER.info("Unknown isAssignableFrom field type");
			returnValue = fieldObject.toString();
		}
		return returnValue;
	}
	
	/**
	 * For relation fields generate the appropriate extra child attributes
	 * 
	 * @param item The item to add the attributes to
	 * @param relations The collection of relations to generate ItemAttributes for
	 * @param method The method
	 * @param lastModified The last modified date
	 */
	private void fillItemAttributeRelations(Item item, Collection<String> relations, Method method, Date lastModified) {
		ItemAttribute attr = null;

		ItemAttributeTrait trait = method.getAnnotation(ItemAttributeTrait.class);
		RelationTrait relationTrait = null;
		String delimiter = null;
		String[] partTypes = null;
		if (method.isAnnotationPresent(RelationTrait.class)) {
			relationTrait = method.getAnnotation(RelationTrait.class);
			delimiter = relationTrait.delimiter();
			partTypes = relationTrait.partTypes();
		}
		
		for (String value : relations) {
			trait.attrType();
			attr = new ItemAttribute(item, trait.attrType(), value, lastModified);
			if (relationTrait != null) {
				String[] parts = getRelationParts(value, delimiter);
				for (int i = 0; i < partTypes.length && i < parts.length; i++) {
					ItemAttribute subAttr = new ItemAttribute(item, partTypes[i], parts[i], lastModified);
					subAttr.setItemAttribute(attr);
					attr.getItemAttributes().add(subAttr);
				}
			}
			item.getItemAttributes().add(attr);
		}
	}
	
	/**
	 * Get the relation parts
	 * 
	 * @param relation The relation
	 * @param delimiter The delimiter
	 * @return An array of parts
	 */
	private String[] getRelationParts(String relation, String delimiter) {
		List<String> values = new ArrayList<String>();

		int firstIndex = 0;
		int secondIndex = 0;
		secondIndex = relation.indexOf(delimiter);
		String part = null;
		while (secondIndex >= 0) {
			part = relation.substring(firstIndex, secondIndex).trim();
			values.add(part);
			firstIndex = secondIndex;
			secondIndex = relation.indexOf(delimiter, firstIndex + 1);
		}
		if (firstIndex > 0) {
			part = relation.substring(firstIndex).trim();
			values.add(part);
		}		
				
		return values.toArray(new String[0]);
	}
	
	/**
	 * Set the item attribute children
	 * 
	 * @param item The item
	 * @param attr The parent attribute
	 * @param object The object to retrieve information from
	 * @param lastModified The last modified date
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	private void setItemAttributeChildren(Item item, ItemAttribute attr, Object object, Date lastModified)
			throws InvocationTargetException, IllegalAccessException {
		Method[] methods = object.getClass().getMethods();
		
		for (Method method : methods) {
			if (method.isAnnotationPresent(ItemAttributeTrait.class)) {
				ItemAttributeTrait trait = method.getAnnotation(ItemAttributeTrait.class);
				String attrType = trait.attrType();
				Object value = method.invoke(object);
				TraitType traitType = trait.traitType();
				if (value != null) {
					switch(traitType) {
					case STRING:
						String strValue = (String) value;
						if (strValue.length() > 0) {
							ItemAttribute subAttr = new ItemAttribute(item, attrType, strValue, lastModified);
							subAttr.setItemAttribute(attr);
							attr.getItemAttributes().add(subAttr);
						}
						break;
					case STRING_LIST:
						@SuppressWarnings("unchecked")
						Collection<String> collection = (Collection<String>) value;
						for (String collValue : collection) {
							ItemAttribute subAttr = new ItemAttribute(item, attrType, collValue, lastModified);
							subAttr.setItemAttribute(attr);
							attr.getItemAttributes().add(subAttr);
						}
						break;
					case SUBJECT_LIST:
						@SuppressWarnings("unchecked")
						Collection<Subject> subjects = (Collection<Subject>) value;

						for (Subject subject : subjects) {
							ItemAttribute subjAttr = new ItemAttribute(item, attrType, subject.getCode(), lastModified);
							subjAttr.setItemAttribute(attr);
							setItemAttributeChildren(item, subjAttr, subject, lastModified);
							attr.getItemAttributes().add(subjAttr);
						}
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Get the object from the Item
	 * 
	 * @param item The item to generate an object from
	 * @param clazz The object class
	 * @return The populated object
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public Object getItemObject(Item item, Class<?> clazz) 
			throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
		return getItemObject(item, clazz, 1);
	}
	
	/**
	 * Get the object from the Item
	 * 
	 * @param item The item to generate an object from
	 * @param clazz The object class
	 * @param level The level to retrieve
	 * @return The populated object
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public Object getItemObject(Item item, Class<?> clazz, int level)
			throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
		Object object = clazz.newInstance();
		
		processAttributes(item.getItemAttributes(), clazz, level, object);
		
		return object;
	}
	
	/**
	 * Process the attributes
	 * 
	 * @param itemAttributes The collection of item attributes
	 * @param clazz The class
	 * @param level The level to retrieve
	 * @param object The object to populate
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	private void processAttributes(Collection<ItemAttribute> itemAttributes, Class<?> clazz, int level, Object object) 
			throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Method[] methods = clazz.getMethods();
		
		Map<String, Method> methodsMap = new HashMap<String, Method>();
		for (Method method : methods) {
			if (method.isAnnotationPresent(ItemAttributeTrait.class)) {
				ItemAttributeTrait trait = method.getAnnotation(ItemAttributeTrait.class);
				int traitLevel = trait.level();
				if (traitLevel >= level) {
					methodsMap.put(trait.attrType(), method);
				}
			}
		}
		
		for (ItemAttribute attr : itemAttributes) {
			String attrType = attr.getAttrType();
			String attrValue = attr.getAttrValue();
			
			Method method = methodsMap.get(attrType);
			if (method != null) {
				ItemAttributeTrait trait = method.getAnnotation(ItemAttributeTrait.class);
				TraitType traitType = trait.traitType();
				
				switch(traitType) {
				case STRING:
					String methodName = method.getName();
					methodName = "s" + methodName.substring(1);
					Method setMethod = clazz.getMethod(methodName, String.class);
					setMethod.invoke(object, attrValue);
					break;
				case STRING_LIST:
					@SuppressWarnings("unchecked")
					List<String> stringList = (List<String>) method.invoke(object);
					stringList.add(attrValue);
					break;
				case SUBJECT_LIST:
					Collection<ItemAttribute> childAttributes = attr.getItemAttributes();
					Subject subject = new Subject();
					//Default to level 1 so that the system should print the values if it is a subject and the level is higher than 1
					processAttributes(childAttributes, Subject.class, 1, subject);
					@SuppressWarnings("unchecked")
					List<Subject> subjectList = (List<Subject>)method.invoke(object);
					subjectList.add(subject);
					break;
				}
			}
		}
	}
	
	public List<String> getTraitAttributeTypes(Class<?> clazz) {
		List<String> attrTypes = new ArrayList<String>();
		
		for (Method method : clazz.getMethods()) {
			if (method.isAnnotationPresent(ItemAttributeTrait.class)) {
				ItemAttributeTrait trait = method.getAnnotation(ItemAttributeTrait.class);
				attrTypes.add(trait.attrType());
			}
		}
		return attrTypes;
	}
}
