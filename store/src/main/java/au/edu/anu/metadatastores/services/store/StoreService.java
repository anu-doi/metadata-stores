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

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.store.grants.Grant;
import au.edu.anu.metadatastores.store.grants.GrantService;
import au.edu.anu.metadatastores.store.misc.Relation;
import au.edu.anu.metadatastores.store.misc.RelationService;
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
	static final Logger LOGGER = LoggerFactory.getLogger(StoreService.class);
	private static StoreService singleton_;
	private PersonService personService_ = PersonService.getSingleton();
	private PublicationService publicationService_ = PublicationService.getSingleton();
	private GrantService grantService_ = GrantService.getSingleton();
	private RelationService relationService_ = RelationService.getSingleton();
	
	public static void main(String[] args) {
		Options options = getOptions();
		CommandLineParser parser = new BasicParser();
		try {
			CommandLine line = parser.parse(options, args);
			StoreService storeService = StoreService.getSingleton();
			if (line.hasOption("publication")) {
				storeService.performPublicationActions(line);
			}
			else if (line.hasOption("people")) {
				storeService.performPeopleActions(line);
			}
			else if (line.hasOption("grant")) {
				storeService.performGrantActions(line);
			}
			else {
				getUsage(options);
			}
		}
		catch (ParseException e) {
			System.err.println("Parsing arguments failed: " + e.getMessage());
			getUsage(options);
		}
	}
	
	/**
	 * Get the command line options
	 * 
	 * @return The command line options
	 */
	public static Options getOptions() {
		Options options = new Options();
		OptionGroup publicationGroup = new OptionGroup();
		publicationGroup.addOption(new Option("publication",false,"Perform actions with publications"));
		options.addOptionGroup(publicationGroup);
		
		OptionGroup peopleGroup = new OptionGroup();
		peopleGroup.addOption(new Option("people",false,"Perform actions with people"));
		options.addOptionGroup(peopleGroup);
		
		OptionGroup grantGroup = new OptionGroup();
		grantGroup.addOption(new Option("grant", false, "Perform actions with grants"));
		options.addOptionGroup(grantGroup);
		
		options.addOption("u","user",true,"User to find/update information for");
		options.addOption("y","year",true,"Year to update information for");
		
		return options;
	}
	
	/**
	 * Get print the usage command
	 * 
	 * @param options The options for usage
	 */
	public static void getUsage(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("Store", options);
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
	 * Perform command line actions for people
	 * 
	 * @param line The command line arguments
	 */
	public void performPeopleActions(CommandLine line) {
		if (line.hasOption("u")) {
			String[] peopleIds = line.getOptionValues("u");
			for (String uid : peopleIds) {
				Person person = personService_.fetchPersonInformation(uid);
				personService_.savePerson(person);
			}
		}
		else {
			System.out.println("No Options For Person");
		}
	}
	
	/**
	 * Perform command line actions for publications
	 * 
	 * @param line The command line arguments
	 */
	public void performPublicationActions(CommandLine line) {
		if (line.hasOption("u")) {
			String[] peopleIds = line.getOptionValues("u");
			for (String uid : peopleIds) {
				List<Publication> publications = publicationService_.fetchPublicationsForUid(uid);
				publicationService_.savePublications(publications);
			}
		}
		if (line.hasOption("y")) {
			String year = line.getOptionValue("y");
			List<Publication> publications = publicationService_.fetchPublicationsByYear(year);
			publicationService_.savePublications(publications);
		}
		else {
			System.out.println("No Options For Publications");
		}
	}
	
	/**
	 * Perform command line actions for grants
	 * 
	 * @param line The command line arguments
	 */
	public void performGrantActions(CommandLine line) {
		if (line.hasOption("u")) {
			String[] peopleIds = line.getOptionValues("u");
			for (String uid : peopleIds) {
				List<Grant> grants = grantService_.fetchGrantsForPerson(uid);
				grantService_.saveGrants(grants);
			}
		}
		else {
			System.out.println("No Options For Grants");
		}
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
}
