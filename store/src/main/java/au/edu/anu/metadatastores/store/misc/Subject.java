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

import javax.xml.bind.annotation.XmlElement;

import au.edu.anu.metadatastores.datamodel.store.annotations.ItemAttributeTrait;
import au.edu.anu.metadatastores.datamodel.store.annotations.TraitType;
import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;

/**
 * <p>Subject<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Subject class that contains a code, value, and percentage</p>
 * 
 * @author Genevieve Turner
 *
 */
public class Subject {
	public static final String CODE = "code";
	public static final String VALUE = "value";
	public static final String PERCENTAGE = "percentage";
	
	private String code_;
	private String value_;
	private String percentage_;
	
	/**
	 * Constructor
	 */
	public Subject() {
		
	}
	
	/**
	 * Constructor
	 * 
	 * @param code The subject code
	 * @param value The subject value/description
	 * @param percentage The percentage
	 */
	public Subject(String code, String value, String percentage) {
		this.code_ = code;
		this.value_ = value;
		this.percentage_ = percentage;
	}
	
	/**
	 * Get the code
	 * 
	 * @return The code
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.FOR_CODE, traitType=TraitType.STRING)
	@XmlElement(name=CODE)
	public String getCode() {
		return code_;
	}
	
	/**
	 * Set the code
	 * @param code
	 */
	public void setCode(String code) {
		this.code_ = code;
	}
	
	/**
	 * Get the subject value/description
	 * 
	 * @return The value/description
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.FOR_VALUE, traitType=TraitType.STRING)
	@XmlElement(name=VALUE)
	public String getValue() {
		return value_;
	}
	
	/**
	 * Set the subject value/description
	 * 
	 * @param value The value/description
	 */
	public void setValue(String value) {
		this.value_ = value;
	}
	
	/**
	 * Get the percentage value of the subject in regards to the other subjects with the parent item
	 * 
	 * @return The percentage
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.FOR_PERCENT, traitType=TraitType.STRING)
	@XmlElement(name=PERCENTAGE)
	public String getPercentage() {
		return percentage_;
	}
	
	/**
	 * Set the percentage value of the subject in regards to the other subjects with the parent item
	 * 
	 * @param percentage The percentage
	 */
	public void setPercentage(String percentage) {
		this.percentage_ = percentage;
	}
}
