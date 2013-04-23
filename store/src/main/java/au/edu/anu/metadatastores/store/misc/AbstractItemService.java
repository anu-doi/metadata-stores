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

package au.edu.anu.metadatastores.store.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.HistItemAttribute;
import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.ItemAttribute;
import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;

/**
 * AbstractItemService
 * 
 * The Australian National University
 * 
 * Abstract Service class that contains methods for the service classes that mould items
 * 
 * @author Genevieve Turner
 *
 */
public abstract class AbstractItemService {
	static final Logger LOGGER = LoggerFactory.getLogger(AbstractItemService.class);

	/**
	 * Set a single attribute
	 * 
	 * @param item The item to add attributes to
	 * @param attributes The list of attributes currently in this field
	 * @param value The value to update
	 * @param attrType The attribute type
	 * @param session The session
	 * @param lastModified The last modified date to set
	 */
	protected void setSingleAttribute(Item item, List<ItemAttribute> attributes, String value, String attrType, Session session, Date lastModified, Boolean userUpdated) {
		if (attributes.size() > 0) {
			ItemAttribute attr = attributes.get(0);
			if (!attr.getAttrValue().equals(value)) {
				if (value != null) {
					addAttributeHistory(item, attr, lastModified);
					attr.setAttrValue(value);
					attr.setLastModified(lastModified);
					attr.setUserUpdated(userUpdated);
				}
				else if (attr.getUserUpdated() == null || attr.getUserUpdated() == Boolean.FALSE) {
					addAttributeHistory(item, attr, lastModified);
					item.getItemAttributes().remove(attr);
					session.delete(attr);
				}
			}
		}
		else if (value != null && value.trim().length() > 0) {
			ItemAttribute attr = new ItemAttribute(item, attrType, value, lastModified);
			attr.setUserUpdated(userUpdated);
			item.getItemAttributes().add(attr);
		}
	}
	
	/**
	 * Set attributes for a list of values
	 * 
	 * @param item The item to add attributes to
	 * @param attributes The list of attributes currently in this field
	 * @param value The values to set
	 * @param attrType The attribute type
	 * @param session The session
	 * @param lastModified The last modified date to set
	 */
	protected void setMultipleAttribute(Item item, List<ItemAttribute> attributes, List<String> values, String attrType, Session session, Date lastModified, Boolean userUpdated) {
		List<ItemAttribute> removeItems = new ArrayList<ItemAttribute>();
		List<String> addItems = new ArrayList<String>();
		compareValues(values, attributes, removeItems, addItems);
		LOGGER.debug("Remove Items Size: {}, Add Items Size: {}", removeItems.size(), addItems.size());
		updateAttributes(item, removeItems, addItems, session, attrType, lastModified, userUpdated);
	}

	/**
	 * Set attributes for a list of values
	 * 
	 * @param item The item to add attributes to
	 * @param attributes The list of attributes currently in this field
	 * @param value The array of values to set
	 * @param attrType The attribute type
	 * @param session The session
	 * @param lastModified The last modified date to set
	 */
	protected void setMultipleAttribute(Item item, List<ItemAttribute> attributes, String[] values, String attrType, Session session, Date lastModified, Boolean userUpdated) {
		setMultipleAttribute(item, attributes, Arrays.asList(values), attrType, session, lastModified, userUpdated);
	}
	
	/**
	 * Compares items and adds the item to the add/remove lists if they should be added or deleted
	 * 
	 * @param values The list of dublin core values to compare
	 * @param attributes The list of attributes to compare against
	 * @param removeItems The list of items to remove
	 * @param addItems The list of items to add
	 */
	protected void compareValues(List<String> values, List<ItemAttribute> attributes, List<ItemAttribute> removeItems, List<String> addItems) {
		String value = null;
		boolean[] hasValue = new boolean[values.size()];
		boolean found = false;
		
		for (ItemAttribute attr : attributes) {
			value = attr.getAttrValue();
			for (int i = 0; i < values.size(); i++) {
				LOGGER.debug("Value: x{}x, Verify Value: x{}x", value.trim(), values.get(i).trim());
				if (value.trim().equals(values.get(i).trim())) {
					LOGGER.debug("Found Item: {}", value);
					hasValue[i] = true;
					found = true;
					//Unfortunately we can't break here in case there is a duplicate value as it would keep trying to add that value again in successive attempts
				}
			}
			if (!found && (values.size() > 0 || (attr.getUserUpdated() == null || attr.getUserUpdated() == Boolean.FALSE))) {
				removeItems.add(attr);
			}
			found = false;
		}
		for (int i = 0; i < values.size(); i++) {
			if (!hasValue[i]) {
				addItems.add(values.get(i).trim());
			}
		}
	}
	
	/**
	 * Updates the attributes
	 * 
	 * @param item The item to update attributes of
	 * @param removeItems The items to remove
	 * @param addItems The items to add
	 * @param session The current active session
	 * @param attrType The attribute type
	 */
	protected void updateAttributes(Item item, List<ItemAttribute> removeItems, List<String> addItems, Session session, String attrType, Date lastModified, Boolean userUpdated) {
		removeAttributes(item, removeItems, session, lastModified);
		
		ItemAttribute attr = null;
		for (String value : addItems) {
			attr = new ItemAttribute(item, attrType, value, lastModified);
			attr.setUserUpdated(userUpdated);
			item.getItemAttributes().add(attr);
		}
	}
	
	/**
	 * Removes the attributes from the item
	 * 
	 * @param item The item to remove attributes from
	 * @param removeItems The items to remove
	 * @param session The current active session
	 */
	protected void removeAttributes(Item item, List<ItemAttribute> removeItems, Session session, Date lastModified) {
		for (ItemAttribute removeAttr : removeItems) {
			LOGGER.debug("Removing Item: {}, Aid: {}, Value: {}", new Object[] {item.getIid(), removeAttr.getAid(), removeAttr.getAttrValue()});
			for (ItemAttribute attr : removeAttr.getItemAttributes()) {
				addAttributeHistory(item, attr, lastModified);
				item.getItemAttributes().remove(attr);
				session.delete(attr);
			}
			addAttributeHistory(item, removeAttr, lastModified);
			item.getItemAttributes().remove(removeAttr);
			session.delete(removeAttr);
		}
	}
	
	/**
	 * Set the for subjects to save
	 * 
	 * @param item The item to add subjects to
	 * @param grant The grant information
	 * @param session The current hibernate session
	 * @param lastModified The last modified date to set
	 */
	protected void setForSubjectsForSave(Item item, List<ItemAttribute> attrs, List<Subject> subjects, Session session, Date lastModified, Boolean userUpdated) {
		if (subjects.size() > 0) {
			boolean[] hasSubjects = new boolean[subjects.size()];
			Subject subject = null;
			boolean found = false;
			for (ItemAttribute subjectAttr : attrs) {
				for (int i = 0; i < subjects.size(); i++) {
					subject = subjects.get(i);
					if (subjectAttr.getAttrValue().equals(subject.getCode())) {
						hasSubjects[i] = true;
						found = true;
						Set<ItemAttribute> subAttrs = subjectAttr.getItemAttributes();
						Iterator<ItemAttribute> it = subAttrs.iterator();
						boolean hasCode = false, hasValue = false, hasPercent = false;
						while (it.hasNext()) {
							ItemAttribute subjSubAttr = it.next();
							if (subjSubAttr.getAttrType().equals(StoreAttributes.FOR_CODE)) {
								hasCode = true;
								if (!subjSubAttr.getAttrValue().equals(subject.getCode())) {
									subjSubAttr.setAttrValue(subject.getCode());
									subjSubAttr.setLastModified(lastModified);
									subjSubAttr.setUserUpdated(userUpdated);
									addAttributeHistory(item, subjSubAttr, lastModified);
								}
							}
							else if (subjSubAttr.getAttrType().equals(StoreAttributes.FOR_PERCENT)) {
								hasPercent = true;
								if (!subjSubAttr.getAttrValue().equals(subject.getPercentage())) {
									subjSubAttr.setAttrValue(subject.getPercentage());
									subjSubAttr.setLastModified(lastModified);
									subjSubAttr.setUserUpdated(userUpdated);
									addAttributeHistory(item, subjSubAttr, lastModified);
								}
							}
							else if (subjSubAttr.getAttrType().equals(StoreAttributes.FOR_VALUE)) {
								hasValue = true;
								if (!subjSubAttr.getAttrValue().equals(subject.getValue())) {
									subjSubAttr.setAttrValue(subject.getValue());
									subjSubAttr.setLastModified(lastModified);
									subjSubAttr.setUserUpdated(userUpdated);
									addAttributeHistory(item, subjSubAttr, lastModified);
								}
							}
						}
						if (!hasCode && subject.getCode() != null && subject.getCode().trim().length() > 0) {
							ItemAttribute attr = new ItemAttribute(item, StoreAttributes.FOR_CODE, subject.getCode(), lastModified);
							attr.setItemAttribute(subjectAttr);
							attr.setUserUpdated(userUpdated);
							subjectAttr.getItemAttributes().add(attr);
						}
						if (!hasPercent && subject.getPercentage() != null && subject.getPercentage().trim().length() > 0) {
							ItemAttribute attr = new ItemAttribute(item, StoreAttributes.FOR_PERCENT, subject.getPercentage(), lastModified);
							attr.setItemAttribute(subjectAttr);
							attr.setUserUpdated(userUpdated);
							subjectAttr.getItemAttributes().add(attr);
						}
						if (!hasValue && subject.getValue() != null && subject.getValue().trim().length() > 0) {
							ItemAttribute attr = new ItemAttribute(item, StoreAttributes.FOR_VALUE, subject.getValue(), lastModified);
							attr.setItemAttribute(subjectAttr);
							attr.setUserUpdated(userUpdated);
							subjectAttr.getItemAttributes().add(attr);
						}
					}
				}
				if (!found) {
					addAttributeHistory(item, subjectAttr, lastModified);
					for (ItemAttribute subjAttr : subjectAttr.getItemAttributes()) {
						addAttributeHistory(item, subjAttr, lastModified);
					}
					item.getItemAttributes().remove(subjectAttr);
					item.getItemAttributes().removeAll(subjectAttr.getItemAttributes());
					session.delete(subjectAttr);
				}
			}
			for (int i = 0; i < subjects.size(); i++) {
				if (!hasSubjects[i]) {
					subject = subjects.get(i);
					addForSubjectAttributes(item, subject, lastModified, userUpdated);
				}
			}
		}
		else if (subjects != null && subjects.size() > 0) {
			for (Subject subject : subjects) {
				addForSubjectAttributes(item, subject, lastModified, userUpdated);
			}
		}
	}
	
	/**
	 * Create the for subject and its child nodes
	 * 
	 * @param item The item to add the subject attributes to
	 * @param subject The subject to add
	 * @param lastModified The last modified date to set
	 */
	private void addForSubjectAttributes(Item item, Subject subject, Date lastModified, Boolean userUpdated) {
		ItemAttribute attr = new ItemAttribute(item, StoreAttributes.FOR_SUBJECT, subject.getCode(), lastModified);
		if (subject.getCode() != null && subject.getCode().trim().length() > 0) {
			ItemAttribute codeAttr = new ItemAttribute(item, StoreAttributes.FOR_CODE, subject.getCode(), lastModified);
			codeAttr.setItemAttribute(attr);
			codeAttr.setUserUpdated(userUpdated);
			attr.getItemAttributes().add(codeAttr);
		}
		if (subject.getValue() != null && subject.getValue().trim().length() > 0) {
			ItemAttribute valueAttr = new ItemAttribute(item, StoreAttributes.FOR_VALUE, subject.getValue(), lastModified);
			valueAttr.setItemAttribute(attr);
			valueAttr.setUserUpdated(userUpdated);
			attr.getItemAttributes().add(valueAttr);
		}
		if (subject.getPercentage() != null && subject.getPercentage().trim().length() > 0) {
			ItemAttribute percentAttr = new ItemAttribute(item, StoreAttributes.FOR_PERCENT, subject.getPercentage(), lastModified);
			percentAttr.setItemAttribute(attr);
			percentAttr.setUserUpdated(userUpdated);
			attr.getItemAttributes().add(percentAttr);
		}
		item.getItemAttributes().add(attr);
	}
	
	/**
	 * Add a history row for the attribute
	 * 
	 * @param item The item to add a history row to
	 * @param attr The attribute that needs to have a history row logged for
	 * @param lastModified The last modified date
	 */
	protected void addAttributeHistory(Item item, ItemAttribute attr, Date lastModified) {
		LOGGER.debug("Add history for: {}, {}, {}, {}", new Object[]{attr.getAid(), attr.getLastModified(), attr.getAttrType(), attr.getAttrValue()});
		HistItemAttribute histAttr = new HistItemAttribute(attr, lastModified);
		item.getHistItemAttributes().add(histAttr);
	}
	
	/**
	 * Get the single value from the attributes
	 * 
	 * @param attrs The attribute to retrieve the first for
	 * @return The value of the attribute
	 */
	public String getSingleAttributeValue(List<ItemAttribute> attrs) {
		if (attrs != null && attrs.size() > 0) {
			return attrs.get(0).getAttrValue();
		}
		return null;
	}
}
