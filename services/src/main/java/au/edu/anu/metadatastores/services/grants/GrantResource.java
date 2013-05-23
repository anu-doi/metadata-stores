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
package au.edu.anu.metadatastores.services.grants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;
import au.edu.anu.metadatastores.services.store.StoreService;
import au.edu.anu.metadatastores.store.grants.Grant;

/**
 * <p>GrantResource<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p></p>
 * 
 * @author Genevieve Turner
 *
 */
@Path("/grant")
public class GrantResource {
	static final Logger LOGGER = LoggerFactory.getLogger(GrantResource.class);
	
	/**
	 * Get the grant with the given id
	 * 
	 * @param id The id
	 * @return The grant information
	 */
	@GET
	@Path("/info/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getGrant(@PathParam("id") String id) {
		StoreService storeService = StoreService.getSingleton();
		Grant grant = storeService.getGrant(id);
		return Response.ok(grant).build();
	}
	
	/**
	 * Search for grants with the given information.<br/>
	 * <br/>
	 * Note: Currently only the title attribute is implemented
	 * 
	 * @param title The title to search grants on
	 * @param callback The json callback function name
	 * @return The response of grants
	 */
	@GET
	@Path("/search")
	@Produces({"application/x-javascript", MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getGrantsWithAttributes(@QueryParam("title") String title, @QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
		
		Map<String, String> attributes = new HashMap<String, String>();
		if (title != null && title.trim().length() > 0) {
			attributes.put(StoreAttributes.TITLE, title);
		}
		StoreService storeService = StoreService.getSingleton();
		List<Grant> grants = storeService.getGrantsByAttributes(attributes);
		//List<Grant> grants = new ArrayList<Grant>();
		//TODO find out what the more limiting Access-Control-Allow-Origin should be
		GenericEntity<List<Grant>> entity = new GenericEntity<List<Grant>>(grants){};
		return Response.ok(entity).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET").header("Acess-Control-Allow-Headers", callback).build();
	}
}
