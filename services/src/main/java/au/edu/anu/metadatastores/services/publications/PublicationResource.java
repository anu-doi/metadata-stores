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

package au.edu.anu.metadatastores.services.publications;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.services.store.StoreService;
import au.edu.anu.metadatastores.store.publication.Publication;

/**
 * <p>PublicationResource<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Resource class to display publications</p>
 * 
 * @author Genevieve Turner
 *
 */
@Path("/publication")
public class PublicationResource {
	static final Logger LOGGER = LoggerFactory.getLogger(PublicationResource.class);
	
	/**
	 * Retrieves the publications for the given year
	 * 
	 * @param year The year of publication
	 * @return The publications that were published in the provided year
	 */
	@GET
	@Path("/year/{year}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response  getPublicationsByYear(@PathParam("year") String year) {
		StoreService storeService = StoreService.getSingleton();
		List<Publication> publications = storeService.getPublicationsByYear(year);
		GenericEntity<List<Publication>> entity = new GenericEntity<List<Publication>>(publications){};
		return Response.ok(entity).build();
	}
}
