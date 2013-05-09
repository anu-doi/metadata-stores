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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.ItemAttribute;
import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;

/**
 * <p>DublinCoreItem<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Class for mapping Dublin Core items</p>
 * 
 * @author Genevieve Turner
 *
 */
@MappedSuperclass
public class DublinCoreItem extends Item {
	private static final long serialVersionUID = 1L;
	
	private List<ItemAttribute> titles = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> creators = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> subjects = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> descriptions = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> publishers = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> contributors = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> dates = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> types = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> formats = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> identifiers = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> sources = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> languages = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> relations = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> coverage = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> rights = new ArrayList<ItemAttribute>();

	/**
	 * Get the titles
	 * 
	 * @return The titles
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.TITLE + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getTitles() {
		return titles;
	}
	
	/**
	 * Set the titles
	 * 
	 * @param titles The titles
	 */
	public void setTitles(List<ItemAttribute> titles) {
		this.titles = titles;
	}

	/**
	 * Get the creators
	 * 
	 * @return The creators
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.CREATOR + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getCreators() {
		return creators;
	}
	
	/**
	 * Set the creators
	 * 
	 * @param creators The creators
	 */
	public void setCreators(List<ItemAttribute> creators) {
		this.creators = creators;
	}

	/**
	 * Get the subjects
	 * 
	 * @return The subjects
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.SUBJECT + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getSubjects() {
		return subjects;
	}
	
	/**
	 * Set the subjects
	 * 
	 * @param subjects The subjects
	 */
	public void setSubjects(List<ItemAttribute> subjects) {
		this.subjects = subjects;
	}

	/**
	 * Get the descriptions
	 * 
	 * @return The descriptions
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.DESCRIPTION + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getDescriptions() {
		return descriptions;
	}
	
	/**
	 * Set the descriptions
	 * 
	 * @param descriptions The descriptions
	 */
	public void setDescriptions(List<ItemAttribute> descriptions) {
		this.descriptions = descriptions;
	}

	/**
	 * Get the publishers
	 * 
	 * @return The publishers
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.PUBLISHER + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getPublishers() {
		return publishers;
	}
	
	/**
	 * Set the publishers
	 * 
	 * @param publishers The publishers
	 */
	public void setPublishers(List<ItemAttribute> publishers) {
		this.publishers = publishers;
	}

	/**
	 * Get the contributors
	 * 
	 * @return The contributors
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.CONTRIBUTOR + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getContributors() {
		return contributors;
	}
	
	/**
	 * Set the contributors
	 * 
	 * @param contributors The contributors
	 */
	public void setContributors(List<ItemAttribute> contributors) {
		this.contributors = contributors;
	}

	/**
	 * Get the dates
	 * 
	 * @return The dates
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.DATE + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getDates() {
		return dates;
	}
	
	/**
	 * Set the dates
	 * 
	 * @param dates The dates
	 */
	public void setDates(List<ItemAttribute> dates) {
		this.dates = dates;
	}

	/**
	 * Get the types
	 * 
	 * @return The types
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.TYPE + "'")
	@NotFound(action=NotFoundAction.IGNORE)
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
	 * Get the formats
	 * 
	 * @return The formats
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.FORMAT + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getFormats() {
		return formats;
	}
	
	/**
	 * Set the formats
	 * 
	 * @param formats The formats
	 */
	public void setFormats(List<ItemAttribute> formats) {
		this.formats = formats;
	}

	/**
	 * Get the identifiers
	 * 
	 * @return The identifiers
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.IDENTIFIER + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getIdentifiers() {
		return identifiers;
	}
	
	/**
	 * Set the identifiers
	 * 
	 * @param identifiers The identifiers
	 */
	public void setIdentifiers(List<ItemAttribute> identifiers) {
		this.identifiers = identifiers;
	}

	/**
	 * Get the sources
	 * 
	 * @return The sources
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.SOURCE + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getSources() {
		return sources;
	}
	
	/**
	 * Set the sources
	 * 
	 * @param sources
	 */
	public void setSources(List<ItemAttribute> sources) {
		this.sources = sources;
	}

	/**
	 * Get the languages
	 * 
	 * @return The languages
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.LANGUAGE + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getLanguages() {
		return languages;
	}

	/**
	 * Set the languages
	 * 
	 * @param languages The languages
	 */
	public void setLanguages(List<ItemAttribute> languages) {
		this.languages = languages;
	}

	/**
	 * Get the relations
	 * 
	 * @return The relations
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.RELATION + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getRelations() {
		return relations;
	}
	
	/**
	 * Set the relations
	 * 
	 * @param relations The relations
	 */
	public void setRelations(List<ItemAttribute> relations) {
		this.relations = relations;
	}

	/**
	 * Get the coverage
	 * 
	 * @return The coverage
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.COVERAGE + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getCoverage() {
		return coverage;
	}
	
	/**
	 * Set the coverage
	 * 
	 * @param coverage The coverage
	 */
	public void setCoverage(List<ItemAttribute> coverage) {
		this.coverage = coverage;
	}

	/**
	 * Get the rights
	 * 
	 * @return The rights
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.RIGHTS + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getRights() {
		return rights;
	}
	
	/**
	 * Set the rights
	 * 
	 * @param rights The rights
	 */
	public void setRights(List<ItemAttribute> rights) {
		this.rights = rights;
	}
}
