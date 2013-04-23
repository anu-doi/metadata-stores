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

package au.edu.anu.metadatastores.store.publication;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Filter;

import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.ItemAttribute;
import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;

/**
 * PublicationItem
 * 
 * The Australian National University
 * 
 * Publication class for Item
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue(value="PUBLICATION")
public class PublicationItem extends Item {
	private static final long serialVersionUID = 1L;
	
	private List<ItemAttribute> ariesIds = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> types = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> titles = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> years = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> publicationNames = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> categories = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> isbns = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> issns = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> anzforSubjects = new ArrayList<ItemAttribute>() ;
	
	/**
	 * Get the aries ids
	 * 
	 * @return The aries ids
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.ARIES_ID + "'")
	public List<ItemAttribute> getAriesIds() {
		return ariesIds;
	}

	/**
	 * Set the aries ids
	 * 
	 * @param ariesIds The aries ids
	 */
	public void setAriesIds(List<ItemAttribute> ariesIds) {
		this.ariesIds = ariesIds;
	}
	
	/**
	 * Get the types
	 * 
	 * @return The types
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.TYPE + "'")
	public List<ItemAttribute> getTypes() {
		return types;
	}

	/**
	 * Set the types
	 * 
	 * @param types The types
	 */
	public void setTypes(List<ItemAttribute> types) {
		this.types = types;
	}

	/**
	 * Get the publication titles
	 * 
	 * @return The titles
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.TITLE + "'")
	public List<ItemAttribute> getPublicationTitles() {
		return titles;
	}

	/**
	 * Set the publication titles
	 * 
	 * @param titles The titles
	 */
	public void setPublicationTitles(List<ItemAttribute> titles) {
		this.titles = titles;
	}

	/**
	 * Get the publication years
	 * 
	 * @return The publication years
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.YEAR + "'")
	public List<ItemAttribute> getYears() {
		return years;
	}

	/**
	 * Set the publication years
	 * 
	 * @param years The publication years
	 */
	public void setYears(List<ItemAttribute> years) {
		this.years = years;
	}

	/**
	 * Get the publication names
	 * 
	 * @return The publication names
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.PUBLICATION_NAME + "'")
	public List<ItemAttribute> getPublicationNames() {
		return publicationNames;
	}

	/**
	 * Set the publication names
	 * 
	 * @param publicationNames The publication names
	 */
	public void setPublicationNames(List<ItemAttribute> publicationNames) {
		this.publicationNames = publicationNames;
	}

	/**
	 * Get the publication categories
	 * 
	 * @return The publication categories
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.CATEGORY + "'")
	public List<ItemAttribute> getCategories() {
		return categories;
	}

	/**
	 * Set the publication categories
	 * 
	 * @param categories The publication categories
	 */
	public void setCategories(List<ItemAttribute> categories) {
		this.categories = categories;
	}

	/**
	 * Get the publication ISBN's
	 * 
	 * @return The ISBN's
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.ISBN + "'")
	public List<ItemAttribute> getIsbns() {
		return isbns;
	}

	/**
	 * Set the publications ISBN's
	 * 
	 * @param isbns The ISBN's
	 */
	public void setIsbns(List<ItemAttribute> isbns) {
		this.isbns = isbns;
	}

	/**
	 * Get the ISSN's
	 * 
	 * @return  The ISSN's
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.ISSN + "'")
	public List<ItemAttribute> getIssns() {
		return issns;
	}

	/**
	 * Set the ISSN's
	 * 
	 * @param issns The ISSN's
	 */
	public void setIssns(List<ItemAttribute> issns) {
		this.issns = issns;
	}

	/**
	 * Get the fields of research
	 * 
	 * @return The fields of research
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.FOR_SUBJECT + "'")
	public List<ItemAttribute> getAnzforSubjects() {
		return anzforSubjects;
	}

	/**
	 * Set the fields of research
	 * 
	 * @param anzforSubjects The fields of research
	 */
	public void setAnzforSubjects(List<ItemAttribute> anzforSubjects) {
		this.anzforSubjects = anzforSubjects;
	}
}
