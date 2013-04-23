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

package au.edu.anu.metadatastores.services.store;

import java.util.List;
import java.util.Map;

import au.edu.anu.metadatastores.store.grants.Grant;
import au.edu.anu.metadatastores.store.grants.GrantService;
import au.edu.anu.metadatastores.store.people.Person;
import au.edu.anu.metadatastores.store.people.PersonService;
import au.edu.anu.metadatastores.store.publication.Publication;
import au.edu.anu.metadatastores.store.publication.PublicationService;

/**
 * StoreService
 * 
 * The Australian National University
 * 
 * Service class that provides information from the store
 * 
 * @author Genevieve Turner
 *
 */
public class StoreService {
	private static StoreService singleton_;
	private PersonService personService_ = PersonService.getSingleton();
	private PublicationService publicationService_ = PublicationService.getSingleton();
	private GrantService grantService_ = GrantService.getSingleton();
	
	/**
	 * Constructor
	 */
	private StoreService() {
		
	}
	
	/**
	 * Get the singleton object for the store
	 * 
	 * @return The StoreService
	 */
	public static synchronized StoreService getSingleton() {
		if (singleton_ == null) {
			singleton_ = new StoreService();
		}
		return singleton_;
	}
	
	/**
	 * Get information about a person
	 * 
	 * @param uid The university id of the person
	 * @return The person information
	 */
	public Person getPersonInformation(String uid) {
		return personService_.getPerson(uid);
	}
	
	/**
	 * Get the publications a person is associated with
	 * 
	 * @param uid The university id of the person
	 * @return The persons publications
	 */
	public List<Publication> getPersonPublications(String uid) {
		return publicationService_.getPersonsPublications(uid);
	}
	
	/**
	 * Get the grants a person is associated with
	 * 
	 * @param uid The university id of the person
	 * @return The persons grants
	 */
	public List<Grant> getPersonGrants(String uid) {
		return grantService_.getGrantsForPerson(uid); 
	}
	
	/**
	 * Update information about a person
	 * 
	 * @param uid The university id of the person to update
	 * @param values The values to update
	 * @return The updated person
	 */
	public Person updatePerson(String uid, Map<String, List<String>> values) {
		return personService_.updateSomeAttributes(uid, values);
	}
	
	/**
	 * Get the publications by year
	 * 
	 * @param year The year of publication
	 * @return The list of publications for that year
	 */
	public List<Publication> getPublicationsByYear(String year) {
		return publicationService_.getPublicationsByYear(year);
	}
	
	/**
	 * Get people with values in the attributes
	 * 
	 * @param attributes The attributes to search on
	 * @return The list of people
	 */
	public List<Person> getPersonByAttributes(Map<String, String> attributes) {
		return personService_.queryPersonByAttributes(attributes);
	}
}
