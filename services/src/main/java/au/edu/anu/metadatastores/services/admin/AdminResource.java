package au.edu.anu.metadatastores.services.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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

import au.edu.anu.metadatastores.store.misc.Relation;
import au.edu.anu.metadatastores.store.misc.RelationService;

import com.sun.jersey.api.view.Viewable;

@Path("/admin")
public class AdminResource {
	static final Logger LOGGER = LoggerFactory.getLogger(AdminResource.class);
	/*@GET
	@Path("/possible-relation")
	public Response getPotentialRelations() {
		return null;
	}*/
	
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
	
	@GET
	@Path("/possible-relation")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getPotentialRelationsXMLJSON() {
		RelationService relationService = RelationService.getSingleton();
		
		List<Relation> relations = relationService.getPotentialRelations();
		GenericEntity<List<Relation>> entity = new GenericEntity<List<Relation>>(relations){};
		
		return Response.ok(entity).build();
	}
	
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
		
		//resp = Response.created(createdUri).entity(fedoraObject.getObject_id()).build();
		//return Response.seeOther(location);
	}
}
