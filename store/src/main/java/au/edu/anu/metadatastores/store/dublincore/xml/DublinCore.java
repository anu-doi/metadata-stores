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

package au.edu.anu.metadatastores.store.dublincore.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * DublinCore
 * 
 * The Australian National University
 * 
 * Class to represent dublin core records
 * 
 * @author Genevieve Turner
 *
 */
@XmlRootElement(name="dc", namespace=DublinCoreConstants.OAI_DC)
public class DublinCore {
	private List<String> titles = new ArrayList<String>();
	private List<String> creators = new ArrayList<String>();
	private List<String> subjects = new ArrayList<String>();
	private List<String> descriptions = new ArrayList<String>();
	private List<String> publishers = new ArrayList<String>();
	private List<String> contributors = new ArrayList<String>();
	private List<String> dates = new ArrayList<String>();
	private List<String> types = new ArrayList<String>();
	private List<String> formats = new ArrayList<String>();
	private List<String> identifiers = new ArrayList<String>();
	private List<String> sources = new ArrayList<String>();
	private List<String> languages = new ArrayList<String>();
	private List<String> relations = new ArrayList<String>();
	private List<String> coverage = new ArrayList<String>();
	private List<String> rights = new ArrayList<String>();
	
	/**
	 * Get the titles
	 * 
	 * @return The titles
	 */
	@XmlElement(name="title", namespace=DublinCoreConstants.DC)
	public List<String> getTitles() {
		return titles;
	}
	
	/**
	 * Set the titles
	 * 
	 * @param titles The titles
	 */
	public void setTitles(List<String> titles) {
		this.titles = titles;
	}

	/**
	 * Get the creators
	 * 
	 * @return The creators
	 */
	@XmlElement(name="creator", namespace=DublinCoreConstants.DC)
	public List<String> getCreators() {
		return creators;
	}
	
	/**
	 * Set the creators
	 * 
	 * @param creators The creators
	 */
	public void setCreators(List<String> creators) {
		this.creators = creators;
	}

	/**
	 * Get the subjects
	 * 
	 * @return The subjects
	 */
	@XmlElement(name="subject", namespace=DublinCoreConstants.DC)
	public List<String> getSubjects() {
		return subjects;
	}
	
	/**
	 * Set the subjects
	 * 
	 * @param subjects The subjects
	 */
	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	/**
	 * Get the description
	 * 
	 * @return The description
	 */
	@XmlElement(name="description", namespace=DublinCoreConstants.DC)
	public List<String> getDescriptions() {
		return descriptions;
	}
	
	/**
	 * Set the description
	 * 
	 * @param descriptions The description
	 */
	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

	/**
	 * Get the publishers
	 * 
	 * @return The publishers
	 */
	@XmlElement(name="publisher", namespace=DublinCoreConstants.DC)
	public List<String> getPublishers() {
		return publishers;
	}
	
	/**
	 * Set the publishers
	 * 
	 * @param publishers The publishers
	 */
	public void setPublishers(List<String> publishers) {
		this.publishers = publishers;
	}

	/**
	 * Get the contributors
	 * 
	 * @return The contributors
	 */
	@XmlElement(name="contributor", namespace=DublinCoreConstants.DC)
	public List<String> getContributors() {
		return contributors;
	}
	
	/**
	 * Set the contributors
	 * 
	 * @param contributors The contributors
	 */
	public void setContributors(List<String> contributors) {
		this.contributors = contributors;
	}

	/**
	 * Get the dates
	 * 
	 * @return The dates
	 */
	@XmlElement(name="date", namespace=DublinCoreConstants.DC)
	public List<String> getDates() {
		return dates;
	}
	
	/**
	 * Set the dates
	 * 
	 * @param dates The dates
	 */
	public void setDates(List<String> dates) {
		this.dates = dates;
	}

	/**
	 * Get the types
	 * 
	 * @return The types
	 */
	@XmlElement(name="type", namespace=DublinCoreConstants.DC)
	public List<String> getTypes() {
		return types;
	}
	
	/**
	 * Set the types
	 * 
	 * @param types The types
	 */
	public void setTypes(List<String> types) {
		this.types = types;
	}

	/**
	 * Get the formats
	 * 
	 * @return The formats
	 */
	@XmlElement(name="format", namespace=DublinCoreConstants.DC)
	public List<String> getFormats() {
		return formats;
	}
	
	/**
	 * Set the formats
	 * 
	 * @param formats The formats
	 */
	public void setFormats(List<String> formats) {
		this.formats = formats;
	}

	/**
	 * Get the identifier
	 * 
	 * @return The identifier
	 */
	@XmlElement(name="identifier", namespace=DublinCoreConstants.DC)
	public List<String> getIdentifiers() {
		return identifiers;
	}
	
	/**
	 * Set the identifiers
	 * 
	 * @param identifiers The identifiers
	 */
	public void setIdentifiers(List<String> identifiers) {
		this.identifiers = identifiers;
	}

	/**
	 * Get the sources
	 * 
	 * @return The sources
	 */
	@XmlElement(name="source", namespace=DublinCoreConstants.DC)
	public List<String> getSources() {
		return sources;
	}
	
	/**
	 * Set the sources
	 * 
	 * @param sources The source
	 */
	public void setSources(List<String> sources) {
		this.sources = sources;
	}

	/**
	 * Get the languages
	 * 
	 * @return The languages
	 */
	@XmlElement(name="language", namespace=DublinCoreConstants.DC)
	public List<String> getLanguages() {
		return languages;
	}
	
	/**
	 * Set the languages
	 * 
	 * @param languages
	 */
	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	/**
	 * Get the relations
	 * 
	 * @return The relations
	 */
	@XmlElement(name="relation", namespace=DublinCoreConstants.DC)
	public List<String> getRelations() {
		return relations;
	}
	
	/**
	 * Set the relations
	 * 
	 * @param relations The relations
	 */
	public void setRelations(List<String> relations) {
		this.relations = relations;
	}

	/**
	 * Set the coverage
	 * 
	 * @return The coverage
	 */
	@XmlElement(name="coverage", namespace=DublinCoreConstants.DC)
	public List<String> getCoverage() {
		return coverage;
	}
	
	/**
	 * Set the coverage
	 * 
	 * @param coverage The coverage
	 */
	public void setCoverage(List<String> coverage) {
		this.coverage = coverage;
	}

	/**
	 * Get the rights
	 * 
	 * @return The rights
	 */
	@XmlElement(name="rights", namespace=DublinCoreConstants.DC)
	public List<String> getRights() {
		return rights;
	}
	
	/**
	 * Set the rights
	 * 
	 * @param rights The rights
	 */
	public void setRights(List<String> rights) {
		this.rights = rights;
	}
}
