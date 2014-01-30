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

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.services.store.commands.CommandUtil;
import au.edu.anu.metadatastores.services.store.commands.StoreCommand;
import au.edu.anu.metadatastores.services.store.commands.StoreCommandException;
import au.edu.anu.metadatastores.store.datacommons.DataCommonsService;
import au.edu.anu.metadatastores.store.dublincore.xml.DublinCore;
import au.edu.anu.metadatastores.store.grants.Grant;
import au.edu.anu.metadatastores.store.grants.GrantService;
import au.edu.anu.metadatastores.store.misc.Relation;
import au.edu.anu.metadatastores.store.misc.RelationService;
import au.edu.anu.metadatastores.store.people.Person;
import au.edu.anu.metadatastores.store.people.PersonService;
import au.edu.anu.metadatastores.store.publication.Publication;
import au.edu.anu.metadatastores.store.publication.PublicationService;

/**
 * <p>StoreService<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Service class that provides information from the store</p>
 * 
 * @author Genevieve Turner
 *
 */
public class StoreService {
	static final Logger LOGGER = LoggerFactory.getLogger(StoreService.class);
	private static StoreService singleton_;
	private PersonService personService_ = PersonService.getSingleton();
	private PublicationService publicationService_ = PublicationService.getSingleton();
	private GrantService grantService_ = GrantService.getSingleton();
	private RelationService relationService_ = RelationService.getSingleton();
	private DataCommonsService dataCommonsService_ = DataCommonsService.getSingleton();
	//private EpressService epressService_ = EpressService.getSingleton();
	
	public static void main(String[] args) {
		StoreCommand command = new StoreCommand();
		CmdLineParser parser = new CmdLineParser(command);
		try {
			parser.parseArgument(args);
			if (command.getCmd() != null) {
				command.getCmd().execute();
			}
			else {
				parser.printSingleLineUsage(System.out);
				System.out.println("");
				parser.printUsage(System.out);
			}
		}
		catch (CmdLineException e) {
			System.err.println(e.getMessage());
			printCommandExecution(args);
			System.err.println("No command arguments found please use -h for options");
		}
		catch (StoreCommandException e) {
			printCommandExecution(args);
			System.err.println("No command arguments found please use -h for options");
		}
	}
	
	public static void printCommandExecution(String[] args) {
		System.err.print("Executed Command: " + CommandUtil.STORE + " ");
		for (int i = 0; i < args.length; i++) {
			System.err.print(args[i] + " ");
		}
		System.err.println("");
	}
	
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
		Person person = personService_.getPerson(uid);
		if (person == null) {
			person = personService_.fetchPersonInformation(uid);
			if (person != null) {
				personService_.savePerson(person);
				List<Publication> publications = publicationService_.fetchPublicationsForUid(uid);
				publicationService_.savePublications(publications);
				List<Grant> grants = grantService_.fetchGrantsForPerson(uid);
				grantService_.saveGrants(grants);
			}
		}
		
		return person;
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
	
	/**
	 * Get the grants with the values in the attributes
	 * 
	 * @param attributes The attributes to search on
	 * @return The list of grants
	 */
	public List<Grant> getGrantsByAttributes(Map<String, String> attributes) {
		return grantService_.queryGrantsByAttributes(attributes);
	}
	
	/**
	 * Get the grant with the given id
	 * 
	 * @param grantId The grant id
	 * @return The grant
	 */
	public Grant getGrant(String grantId) {
		return grantService_.getGrant(grantId);
	}
	
	public DublinCore getDataCommonsObject(String dcId) {
		return dataCommonsService_.getDataCommonsObject(dcId);
	}
	
	/**
	 * Get the relations for the record
	 * 
	 * @param iid The item id of the object to get relations for
	 * @return The list of relations for the item id
	 */
	public List<Relation> getRelations(Long iid) {
		return relationService_.getRelatedItems(iid);
	}
	
	public List<Person> getCurrentAriesPeople() {
		return personService_.getCurrentAriesPeople();
	}
}
