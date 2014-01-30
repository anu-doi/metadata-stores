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
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.HistItemAttribute;
import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.ItemAttribute;

/**
 * <p>AbstractItemService<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Abstract Service class that contains methods for the service classes that mould items</p>
 * 
 * @author Genevieve Turner
 *
 */
public abstract class AbstractItemService {
	static final Logger LOGGER = LoggerFactory.getLogger(AbstractItemService.class);
	
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
	 * Get a single item attribute value
	 * 
	 * @param item The item to retrieve the value from
	 * @param field The field to retrieve
	 * @return The value
	 */
	public String getSingleAttributeValue(Item item, String field) {
		String value = null;
		for (ItemAttribute attr : item.getItemAttributes()) {
			if (field.equals(attr.getAttrType())) {
				value = attr.getAttrValue();
				break;
			}
		}
		return value;
	}
	
	/**
	 * Update the item with the new values if they have changed
	 * 
	 * @param oldItem The currently saved item
	 * @param newItem The item with the updated information
	 * @param session The current session
	 * @param lastModified The last modified date
	 */
	protected void updateAttributesFromItem(Item oldItem, Item newItem, Session session, Date lastModified) {
		List<ItemAttribute> newAttrs = new ArrayList<ItemAttribute>();
		List<ItemAttribute> removeAttrs = new ArrayList<ItemAttribute>();
		
		if (newItem.getTitle() != null) {
			oldItem.setTitle(newItem.getTitle());
		}
		
		Map<String, List<ItemAttribute>> oldItemMap = getAttributeMap(oldItem.getItemAttributes());
		Map<String, List<ItemAttribute>> newItemMap = getAttributeMap(newItem.getItemAttributes());
		
		List<ItemAttribute> foundNewList;
		
		Set<String> oldKeySet = oldItemMap.keySet();
		Set<String> newKeySet = newItemMap.keySet();

		for (String key : oldKeySet) {
			List<ItemAttribute> oldValues = oldItemMap.get(key);
			List<ItemAttribute> newValues = newItemMap.get(key);
			foundNewList = new ArrayList<ItemAttribute>();
			boolean foundNewAttr = false;
			if (newValues != null) {
				for (ItemAttribute oldAttr : oldValues) {
					for (ItemAttribute newAttr : newValues) {
						if (oldAttr.getAttrValue().equals(newAttr.getAttrValue())) {
							foundNewAttr = true;
							foundNewList.add(newAttr);
							checkSubAttributes(oldItem, oldAttr, newAttr, removeAttrs);
							break;
						}
					}
					if (!foundNewAttr) {
						if (oldValues.size() == 1 && newValues.size() == 1) {
							ItemAttribute newAttr = newValues.get(0);
							foundNewList.add(newAttr);
							addAttributeHistory(oldItem, oldAttr, lastModified);
							
							oldAttr.setAttrValue(newAttr.getAttrValue());
							oldAttr.setLastModified(newAttr.getLastModified());
							oldAttr.setUserUpdated(newAttr.getUserUpdated());
						}
						else {
							if (oldAttr.getItemAttribute() == null) {
								removeAttrs.add(oldAttr);
							}
						}
					}
					foundNewAttr = false;
				}
				newValues.removeAll(foundNewList);
				if (newValues.size() > 0) {
					newAttrs.addAll(newValues);
				}
				
			}
			else {
				for (ItemAttribute attr : oldValues) {
					if (attr.getItemAttribute() == null) {
						removeAttrs.add(attr);
					}
				}
			}
		}
		
		newKeySet.removeAll(oldKeySet);
		for (String key : newKeySet) {
			newAttrs.addAll(newItemMap.get(key));
		}
		
		for (ItemAttribute attr : removeAttrs) {
			//TODO figure out when to know that user updated fields should be deleted.  User updated information should be able to be dleted...
			LOGGER.info("Remove: {}, {}", attr.getAttrType(), attr.getAttrValue());
			if (!Boolean.TRUE.equals(attr.getUserUpdated())) {
				LOGGER.info("Removing Value");
				addAttributeHistory(oldItem, attr, lastModified);
				if (attr.getItemAttributes() != null && attr.getItemAttributes().size() > 0) {
					for (ItemAttribute subAttr : attr.getItemAttributes()) {
						oldItem.getItemAttributes().remove(subAttr);
						session.delete(subAttr);
					}
				}
				oldItem.getItemAttributes().remove(attr);
				session.delete(attr);
			}
		}
		
		oldItem.getItemAttributes().addAll(getNewAttributes(oldItem, null, newAttrs));
	}
	
	/**
	 * Get the new attributes to added
	 * 
	 * @param item The item to add the attribute to
	 * @param parentAttribute The parent attribute. null if there is no parent
	 * @param newAttributes The collection of attributes to add
	 * @return
	 */
	private List<ItemAttribute> getNewAttributes(Item item, ItemAttribute parentAttribute, Collection<ItemAttribute> newAttributes) {
		List<ItemAttribute> attributes = new ArrayList<ItemAttribute>();
		
		for (ItemAttribute attr : newAttributes) {
			ItemAttribute newAttr = new ItemAttribute(item, attr.getAttrType(), attr.getAttrValue(), attr.getUserUpdated(), attr.getLastModified());
			newAttr.setItemAttribute(parentAttribute);
			newAttr.setUserUpdated(attr.getUserUpdated());
			if (attr.getItemAttributes().size() > 0) {
				newAttr.getItemAttributes().addAll(getNewAttributes(item, newAttr, attr.getItemAttributes()));
			}
			attributes.add(newAttr);
		}
		
		return attributes;
	}
	
	/**
	 * Get the attribute map
	 * 
	 * @param attributes The collection of attributes
	 * @return A Map of attributes with the attribute type as the key
	 */
	private Map<String, List<ItemAttribute>> getAttributeMap(Collection<ItemAttribute> attributes) {
		Map<String, List<ItemAttribute>> attrMap = new HashMap<String, List<ItemAttribute>>();
		for (ItemAttribute attr : attributes) {
			if (!attrMap.containsKey(attr.getAttrType())) {
				List<ItemAttribute> attrList = new ArrayList<ItemAttribute>();
				attrMap.put(attr.getAttrType(), attrList);
			}
			attrMap.get(attr.getAttrType()).add(attr);
		}
		return attrMap;
	}
	
	/**
	 * Check if sub attribute have been updated/removed
	 * 
	 * @param item The item for which the attributes are associated
	 * @param oldAttr The old attribute
	 * @param newAttr The new attribute
	 * @param removeAttrList The list of attributes to remove
	 */
	private void checkSubAttributes(Item item, ItemAttribute oldAttr, ItemAttribute newAttr, List<ItemAttribute> removeAttrList) {
		if (oldAttr.getItemAttributes().size() > 0) {
			boolean foundSubAttr = false;
			List<ItemAttribute> foundNewSubAttrList = new ArrayList<ItemAttribute>();
			for (ItemAttribute oldSubAttr : oldAttr.getItemAttributes()) {
				for (ItemAttribute newSubAttr : newAttr.getItemAttributes()) {
					if (oldSubAttr.getAttrType().equals(newSubAttr.getAttrType()) && oldSubAttr.getAttrValue().equals(newSubAttr.getAttrValue())) {
						foundSubAttr = true;
						foundNewSubAttrList.add(newSubAttr);
						break;
					}
				}
				if (!foundSubAttr) {
					removeAttrList.add(oldSubAttr);
				}
				foundSubAttr = false;
			}
			oldAttr.getItemAttributes().removeAll(removeAttrList);
			newAttr.getItemAttributes().removeAll(foundNewSubAttrList);
			for (ItemAttribute newSubAttr : newAttr.getItemAttributes()) {
				ItemAttribute attr = new ItemAttribute(item, newSubAttr.getAttrType(), newSubAttr.getAttrValue(), newSubAttr.getUserUpdated(), newSubAttr.getLastModified());
				attr.setItemAttribute(oldAttr);
				oldAttr.getItemAttributes().add(attr);
			}
		}
	}
}
