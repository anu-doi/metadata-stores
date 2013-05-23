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
package au.edu.anu.metadatastores.services.report;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.view.Viewable;

/**
 * <p>ReportResource<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Resource class to serve reports</p>
 * 
 * @author Genevieve Turner
 *
 */
@Path("report")
public class ReportResource {
	static final Logger LOGGER = LoggerFactory.getLogger(ReportResource.class);
	
	/**
	 * Get the report page
	 * 
	 * @return The report page
	 */
	@GET
	public Response getReportPage() {
		return Response.ok(new Viewable("/report_collection.jsp")).build();
	}
	
	/**
	 * Get the report
	 * 
	 * @param context The http context
	 * @param request The http request
	 * @return The report
	 */
	@POST
	public Response getReport(@Context HttpServletRequest request, @Context ServletContext context, @FormParam("timePeriod") String timePeriod, @FormParam("endDate") String endDate) {
		LOGGER.info("Time Period: {}, End Date: {}", timePeriod, endDate);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("timePeriod", timePeriod);
		parameters.put("endDate", endDate);
		
		Response response = null;
		ReportGenerator report = new ReportGenerator(context.getRealPath("/"), parameters);
		
		try{ 
			response = report.generateReport("pdf");
		}
		catch (Exception e) {
			LOGGER.error("Exception processing report", e);
			throw new WebApplicationException(Response.status(500).entity("Exception processing report").build());
		}
		
		return response;
	}
	
	/**
	 * Reload reports
	 * 
	 * @param context The http context
	 * @return The response
	 */
	@Path("reload")
	@GET
	public Response reloadReports(@Context ServletContext context) {
		ReportGenerator.reloadReports(context);
		return Response.ok().build();
	}
}
