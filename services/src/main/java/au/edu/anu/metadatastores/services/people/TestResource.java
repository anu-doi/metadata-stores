package au.edu.anu.metadatastores.services.people;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.harvester.Harvest;


@Path("test")
public class TestResource {
	static final Logger LOGGER = LoggerFactory.getLogger(TestResource.class);
	
	@GET
	@Path("harvest")
	@Produces(MediaType.TEXT_PLAIN)
	public Response performHarvest() {
		Harvest harvest = new Harvest();
		try {
			harvest.harvest("DATA_COMMONS");
		}
		catch (Exception e) {
			LOGGER.error("Error harvesting", e);
			return Response.serverError().build();
		}
		
		return Response.ok("Harvested").build();
	}
}
