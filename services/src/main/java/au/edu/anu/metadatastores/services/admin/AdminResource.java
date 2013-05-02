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

package au.edu.anu.metadatastores.services.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.store.misc.Relation;
import au.edu.anu.metadatastores.store.misc.RelationService;

import com.sun.jersey.api.view.Viewable;

/**
 * AdminResource
 * 
 * The Australian National University
 * 
 * Administrative resource class.  Provides restful resources for administrators
 * 
 * @author Genevieve Turner
 *
 */
@Path("/admin")
public class AdminResource {
	static final Logger LOGGER = LoggerFactory.getLogger(AdminResource.class);
	
	/**
	 * Get the potential relations that have not yet been verified.  These may be all the potential relations, or it may be for a defined user
	 * 
	 * @param uid The uid to filter potential relations by
	 * @return A html page with the potential relations
	 */
	@GET
	@Path("/possible-relation")
	@Produces(MediaType.TEXT_HTML)
	public Response getPotentialRelationsHtml(@QueryParam("uid") String uid) {
		RelationService relationService = RelationService.getSingleton();
		
		List<Relation> relations = relationService.getPotentialRelations();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("relations", relations);
		
		Viewable viewable = new Viewable("/possible_relation.jsp", model);
		
		return Response.ok(viewable).build();
	}
	
	/**
	 * Get the potential relations that have not yet been verified
	 * 
	 * @return XML or JSON that contains the potential relations
	 */
	@GET
	@Path("/possible-relation")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getPotentialRelationsXMLJSON() {
		RelationService relationService = RelationService.getSingleton();
		
		List<Relation> relations = relationService.getPotentialRelations();
		GenericEntity<List<Relation>> entity = new GenericEntity<List<Relation>>(relations){};
		
		return Response.ok(entity).build();
	}
	
	/**
	 * Update the potential relations either confirming or denying the relationship
	 * 
	 * @param request The http request
	 * @param uid The defined user against which there may be potential relations
	 * @param iids The item ids to update the relationships for
	 * @param relationValues The value of the relationship
	 * @param relatedIids The related item ids to update relationships for
	 * @param confirmRelations Confirmation or denial of the relationship
	 * @param multivaluedMap
	 * @return
	 */
	@POST
	@Path("/possible-relation")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateRelation(@Context HttpServletRequest request, @QueryParam("uid") String uid, @FormParam("iid") List<Long> iids, 
			@FormParam("relationValue") List<String> relationValues, @FormParam("relatedIid") List<Long> relatedIids, 
			@FormParam("confirm[]") List<String> confirmRelations, MultivaluedMap<String, String> multivaluedMap) {
		
		Map<String, String[]> map = request.getParameterMap();
		LOGGER.info("Request Keys: {}", map.keySet().toString());
		LOGGER.info("Request attributes: {}", request.getAttributeNames().toString());
		
		LOGGER.info("Keys: {}", multivaluedMap.keySet().toString());
		
		if (confirmRelations == null) {
			LOGGER.info("Confirm is null");
		}
		else {
			LOGGER.info("Number of confirms equals: {}", confirmRelations.size());
		}
		
		RelationService relationService = RelationService.getSingleton();
		Relation relation = null;
		for (int i = 0; i < iids.size(); i++) {
			List<String> confirmVal = multivaluedMap.get("confirm[" + i + "]");
			if (confirmVal != null && confirmVal.size() > 0) {
				LOGGER.info("Confirm Val is a string");
				String confirm = confirmVal.get(0);
				relation = new Relation(iids.get(i), relationValues.get(i), relatedIids.get(i));
				if ("yes".equals(confirm)) {
					relationService.confirmOrDenyRelation(relation, Boolean.TRUE);
				}
				else if ("no".equals(confirm)) {
					relationService.confirmOrDenyRelation(relation, Boolean.FALSE);
				}
			}
			else {
				if (confirmVal == null) {
					LOGGER.info("Confirm val is null");
				}
				else {
					LOGGER.info("Confirm Val size is: {}", confirmVal.size());
				}
			}
		}

		UriBuilder uriBuilder = UriBuilder.fromPath("/admin/possible-relation");
		if (uid != null) {
			uriBuilder = uriBuilder.queryParam("uid", uid);
		}
		return Response.seeOther(uriBuilder.build()).build();
	}
	
	/**
	 * Get a page that shows the relationships for the given item id
	 * 
	 * @param id The id to get the relationship page for
	 * @return The relationship page
	 */
	@GET
	@Path("/relation/{id}")
	@Produces(MediaType.TEXT_HTML)
	public Response getRelationsHtml(@PathParam("id") Long id) {
		LOGGER.info("Id: {}", id);
		RelationService relationService = RelationService.getSingleton();
		List<Relation> relations = relationService.getRelatedItems(id);
		Item item = relationService.getItem(id);
		
		LOGGER.info("Number of Relations: {}", relations.size());
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("relations", relations);
		model.put("item", item);
		
		return Response.ok(new Viewable("/list_relations.jsp", model)).build();
	}
	
	/**
	 * Get JSON/XML
	 * 
	 * @param id The id to get the json/xml for
	 * @return The relationships
	 */
	@GET
	@Path("/relation/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getRelationsJson(@PathParam("id") Long id) {

		RelationService relationService = RelationService.getSingleton();
		List<Relation> relations = relationService.getRelatedItems(id);
		GenericEntity<List<Relation>> entity = new GenericEntity<List<Relation>>(relations){};

		return Response.ok(entity).build();
	}
}
