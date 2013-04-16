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

@Path("/publication")
public class PublicationResource {
	static final Logger LOGGER = LoggerFactory.getLogger(PublicationResource.class);
	
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
