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
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Template;
import org.glassfish.jersey.server.mvc.Viewable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.AttributeType;

/**
 * <p>SearchResource<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p></p>
 * 
 * @author Genevieve Turner
 *
 */
@Path("/search")
@Template
public class SearchResource {
	static final Logger LOGGER = LoggerFactory.getLogger(SearchResource.class);
	//TODO add pagination
	/**
	 * Get the basic search page
	 * 
	 * @param value The value to search for
	 * @param rows The number of rows
	 * @param page The page
	 * @return THe response
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable getSearchPage(@QueryParam("search-val") String value, @QueryParam("rows") int rows, @QueryParam("page") int page) {
		Map<String, Object> model = new SearchOptions().search(value);
		
		return new Viewable("index.jsp", model);
	}
	
	/**
	 * Get the advanced search page
	 * 
	 * @param values The values to search for
	 * @param system The system to limit the results to
	 * @param fields The fields to search in
	 * @return The response
	 */
	@GET
	@Path("/advanced")
	@Produces(MediaType.TEXT_HTML)
	public Viewable advancedSearchMultiple(@QueryParam("search-val[]") List<String> values, @QueryParam("system") String system, @QueryParam("field[]") List<String> fields) {
		Map<String, Object> model = new SearchOptions().advancedSearch(values, system, fields);
		
		return new Viewable("advanced_search.jsp", model);
	}
	
	/**
	 * Get the attributes for the given system
	 * 
	 * @param system The system to get attributes for
	 * @return The attributes
	 */
	@GET
	@Path("/attributes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAttributeTypes(@QueryParam("system") String system) {
		List<AttributeType> attributeTypes = new SearchOptions().getAttributeTypes(system);
		
		GenericEntity<List<AttributeType>> entity = new GenericEntity<List<AttributeType>>(attributeTypes){};
		return Response.ok(entity).build();
	}
}
