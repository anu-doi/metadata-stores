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

import javax.annotation.Resource;
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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import au.edu.anu.metadatastores.datamodel.store.AttributeType;

/**
 * <p>SearchResource<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Resource for searching i.e. which types and attributes can you search on and the search pages</p>
 * 
 * @author Genevieve Turner
 *
 */
@Component
@Scope("request")
@Path("/search")
@Template
public class SearchResource {
	static final Logger LOGGER = LoggerFactory.getLogger(SearchResource.class);
	
	@Resource(name="searchOptions")
	SearchOptions searchOptions;
	
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
		LOGGER.debug("In getSearchPage");
		LOGGER.info("Search Value: {}, Number of Rows: {}, Page: {}", value, rows, page);
		Map<String, Object> model = searchOptions.search(value, getOffset(rows, page), rows);
		
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
	public Viewable advancedSearch(@QueryParam("search-val[]") List<String> values, @QueryParam("system") String system, @QueryParam("field[]") List<String> fields, @QueryParam("rows") int rows, @QueryParam("page") int page) {
		LOGGER.debug("In advancedSearch");
		LOGGER.info("Search Value: {}, fields, Number of Rows: {}, Page: {}", values, fields, rows, page);
		Map<String, Object> model = searchOptions.advancedSearch(values, system, fields, getOffset(rows, page), rows);
		LOGGER.debug("Performed search and returning model");
		return new Viewable("advanced_search.jsp", model);
	}
	
	/**
	 * Calculate the offset start point
	 * @param rows The number of rows per page
	 * @param page The page number
	 * @return The offset
	 */
	private int getOffset(int rows, int page) {
		int offset = rows * page;
		return offset;
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
		LOGGER.debug("In getAttributeTypes");
		List<AttributeType> attributeTypes = searchOptions.getAttributeTypes(system);
		
		GenericEntity<List<AttributeType>> entity = new GenericEntity<List<AttributeType>>(attributeTypes){};
		return Response.ok(entity).build();
	}
}
