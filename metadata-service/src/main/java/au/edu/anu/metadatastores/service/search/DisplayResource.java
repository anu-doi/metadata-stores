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
package au.edu.anu.metadatastores.service.search;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Template;
import org.glassfish.jersey.server.mvc.Viewable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import au.edu.anu.metadatastores.store.search.ItemDTO;

/**
 * <p>DisplayResource<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Class to configures the display of objects</p>
 * 
 * @author Genevieve Turner
 *
 */
@Component
@Scope("request")
@Path("/display")
@Template
public class DisplayResource {
	static final Logger LOGGER = LoggerFactory.getLogger(DisplayResource.class);
	
	@Resource(name="displayPage")
	DisplayPage displayPage;
	
	/**
	 * Display the object that is obtained from the given id
	 * 
	 * @param id The id in the path
	 * @return The response
	 */
	@Path("/{id}")
	@GET
	@Produces(MediaType.TEXT_HTML + ";charset=utf-8")
	public Response displayObject(@PathParam("id") Long id, @Context HttpServletResponse response) throws Exception {
		Viewable viewable = displayPage.getPage(id);
		return Response.ok(viewable).encoding("utf-8").build();
	}
	
	/**
	 * Get the objects relations
	 * 
	 * @param id The id of the object to get the relations for
	 * @return The response with relations
	 */
	@Path("/{id}/relation")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRelations(@PathParam("id") Long id, @QueryParam("system") String system) {
		List<ItemDTO> items = new SearchOptions().getRelations(id, system);
		
		GenericEntity<List<ItemDTO>> entity = new GenericEntity<List<ItemDTO>>(items){};
		return Response.ok(entity).build();
	}
}
