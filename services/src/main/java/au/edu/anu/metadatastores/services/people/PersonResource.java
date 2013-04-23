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

package au.edu.anu.metadatastores.services.people;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;
import au.edu.anu.metadatastores.services.store.StoreService;
import au.edu.anu.metadatastores.store.grants.Grant;
import au.edu.anu.metadatastores.store.people.Person;
import au.edu.anu.metadatastores.store.publication.Publication;

import com.sun.jersey.api.json.JSONWithPadding;

/**
 * PersonResource
 * 
 * The Australian National University
 * 
 * Resource to provide restful services returning information about people.  This includes information about the person, their publications and grants.
 * 
 * @author Genevieve Turner
 *
 */
@Path("/person")
public class PersonResource {
	static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);
	
	/**
	 * Return information about people
	 * 
	 * @param uid The persons university id
	 * @return The information about the person
	 */
	@GET
	@Path("/info/{uid}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getPeopleInformation(@PathParam("uid") String uid) {
		StoreService storeService = StoreService.getSingleton();
		Person person = storeService.getPersonInformation(uid);
		return Response.ok(person).build();
	}
	
	/**
	 * Returns information about a persons publications
	 * 
	 * @param uid The persons university id
	 * @return The publications associated with that person
	 */
	@GET
	@Path("/publications/{uid}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getPersonsPublications(@PathParam("uid") String uid) {
		StoreService storeService = StoreService.getSingleton();
		List<Publication> publications = storeService.getPersonPublications(uid);
		GenericEntity<List<Publication>> entity = new GenericEntity<List<Publication>>(publications){};
		return Response.ok(entity).build();
	}
	
	/**
	 * Returns grant information about a person
	 * 
	 * @param uid The persons university id
	 * @return The grants associated with the given person
	 */
	@GET
	@Path("/grants/{uid}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getPersonsGrants(@PathParam("uid") String uid) {
		StoreService storeService = StoreService.getSingleton();
		List<Grant> grants = storeService.getPersonGrants(uid);
		GenericEntity<List<Grant>> entity = new GenericEntity<List<Grant>>(grants){};
		return Response.ok(entity).build();
	}
	
	/**
	 * Updates information about a person
	 * 
	 * @param uid The persons university id
	 * @param multivaluedMap The values that have been pass through as parameters
	 * @return Updated information about the person
	 */
	@POST
	@Path("/update/{uid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response updatePersonInformation(@PathParam("uid") String uid, MultivaluedMap<String, String> multivaluedMap) {
		StoreService storeService = StoreService.getSingleton();
		
		Map<String, List<String>> aMap = new HashMap<String, List<String>>(multivaluedMap);
		Person person = storeService.updatePerson(uid, aMap);
		
		return Response.ok(person).build();
	}
	
	/**
	 * Retrieves a list of people given the attributes
	 * 
	 * @param givenName The given name to search on
	 * @param surname The surname to search on
	 * @param email The email to search on
	 * @return A list of people that match the people
	 */
	@GET
	@Path("/search")
	@Produces({"application/x-javascript", MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getPeopleWithAttributes(@QueryParam("given-name") String givenName, @QueryParam("surname") String surname
			, @QueryParam("email") String email, @QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
		StoreService storeService = StoreService.getSingleton();
		
		Map<String, String> attributes = new HashMap<String, String>();
		if (givenName != null && givenName.trim().length() > 0) {
			attributes.put(StoreAttributes.GIVEN_NAME, givenName);
		}
		if (surname != null && surname.trim().length() > 0) {
			attributes.put(StoreAttributes.SURNAME, surname);
		}
		if (email != null && email.trim().length() > 0) {
			attributes.put(StoreAttributes.EMAIL, email);
		}
		List<Person> people = storeService.getPersonByAttributes(attributes);
		for (Person person : people) {
			LOGGER.info("{}, {}, {}", new Object[] { person.getEmail(), person.getGivenName(), person.getSurname() });
		}
		
		//TODO find out what the more limiting Access-Control-Allow-Origin should be
		GenericEntity<List<Person>> entity = new GenericEntity<List<Person>>(people){};
		return Response.ok(new JSONWithPadding(entity, callback)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET").header("Access-Control-Allow-Headers", callback).build();
	}
}
