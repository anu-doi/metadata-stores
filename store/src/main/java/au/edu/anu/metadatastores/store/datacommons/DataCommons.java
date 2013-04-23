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

package au.edu.anu.metadatastores.store.datacommons;

import java.util.ArrayList;
import java.util.List;

/**
 * DataCommons
 * 
 * The Australian National University
 * 
 * The data commons object
 * 
 * @author Genevieve Turner
 *
 */
public class DataCommons {
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
	private List<String> relations = new ArrayList<String>();
	private List<String> coverage = new ArrayList<String>();
	private List<String> rights = new ArrayList<String>();
	private List<String> audiences = new ArrayList<String>();
	private List<String> provenances = new ArrayList<String>();
	private List<String> rightsHolders = new ArrayList<String>();
	private List<String> instructionalMethods = new ArrayList<String>();
	private List<String> accrualMethods = new ArrayList<String>();
	private List<String> accrualPeriodicity = new ArrayList<String>();
	private List<String> accrualPolicies = new ArrayList<String>();
	
	/**
	 * Get the titles
	 * 
	 * @return The titles
	 */
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
	 * Get the descriptions
	 * 
	 * @return The descriptions
	 */
	public List<String> getDescriptions() {
		return descriptions;
	}
	
	/**
	 * Set the descriptions
	 * 
	 * @param descriptions The descriptions
	 */
	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}
	
	/**
	 * Get the publishers
	 * 
	 * @return The publishers
	 */
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
	 * @return Set the dates
	 */
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
	 * Get the identifiers
	 * 
	 * @return The identifiers
	 */
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
	public List<String> getSources() {
		return sources;
	}
	
	/**
	 * Set the sources
	 * 
	 * @param sources The sources
	 */
	public void setSources(List<String> sources) {
		this.sources = sources;
	}
	
	/**
	 * Get the relations
	 * 
	 * @return The relations
	 */
	public List<String> getRelations() {
		return relations;
	}
	
	/**
	 * Set the relations
	 * 
	 * @param relations The realtions
	 */
	public void setRelations(List<String> relations) {
		this.relations = relations;
	}
	
	/**
	 * Get the coverage
	 * 
	 * @return The coverage
	 */
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
	
	/**
	 * Get the audiences
	 * 
	 * @return The audiences
	 */
	public List<String> getAudiences() {
		return audiences;
	}
	
	/**
	 * Set the audiences
	 * 
	 * @param audiences The audiences
	 */
	public void setAudiences(List<String> audiences) {
		this.audiences = audiences;
	}
	
	/**
	 * Get the provenances
	 * 
	 * @return The provenances
	 */
	public List<String> getProvenances() {
		return provenances;
	}
	
	/**
	 * Set the provenances
	 * 
	 * @param provenances The provenances
	 */
	public void setProvenances(List<String> provenances) {
		this.provenances = provenances;
	}
	
	/**
	 * Get the rights holder
	 * 
	 * @return The rights holder
	 */
	public List<String> getRightsHolders() {
		return rightsHolders;
	}
	
	/**
	 * Set the rights holder
	 * 
	 * @param rightsHolders The rights holder
	 */
	public void setRightsHolders(List<String> rightsHolders) {
		this.rightsHolders = rightsHolders;
	}
	
	/**
	 * Get the instructional methods
	 * 
	 * @return The instructional methods
	 */
	public List<String> getInstructionalMethods() {
		return instructionalMethods;
	}
	
	/**
	 * Set the instructional methods
	 * 
	 * @param instructionalMethods The instructional methods
	 */
	public void setInstructionalMethods(List<String> instructionalMethods) {
		this.instructionalMethods = instructionalMethods;
	}
	
	/**
	 * Get the accural methods
	 * 
	 * @return The accrual methods
	 */
	public List<String> getAccrualMethods() {
		return accrualMethods;
	}
	
	/**
	 * Set the accural methods
	 * 
	 * @param accrualMethods The accrual methods
	 */
	public void setAccrualMethods(List<String> accrualMethods) {
		this.accrualMethods = accrualMethods;
	}
	
	/**
	 * Get the accrual periodicity
	 * 
	 * @return The accrual periodicity
	 */
	public List<String> getAccrualPeriodicity() {
		return accrualPeriodicity;
	}
	
	/**
	 * Set the accrual periodicity
	 * 
	 * @param accrualPeriodicity The accrual periodicity
	 */
	public void setAccrualPeriodicity(List<String> accrualPeriodicity) {
		this.accrualPeriodicity = accrualPeriodicity;
	}
	
	/**
	 * Set the accrual policies
	 * 
	 * @return The accrual policies
	 */
	public List<String> getAccrualPolicies() {
		return accrualPolicies;
	}
	
	/**
	 * Set the accrual policies
	 * 
	 * @param accrualPolicies The accrual policies
	 */
	public void setAccrualPolicies(List<String> accrualPolicies) {
		this.accrualPolicies = accrualPolicies;
	}
}
