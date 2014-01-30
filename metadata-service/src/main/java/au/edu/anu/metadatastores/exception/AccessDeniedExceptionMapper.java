package au.edu.anu.metadatastores.exception;

import java.net.URI;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.mvc.Viewable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

/*
 * <p>AccessDeniedExceptionMapper</p>
 *
 * <p>The Australian National University</p>
 *
 * <p>This class maps springs AccessDeniedException to an access denied response.</p>
 * <p>N.B. If this class does not exist then instead of an appropriate access denied message
 * a 500 response will be returned.  This will occur as Jersey intercepts and writes the response
 * prior to Spring attempting to handle the exception</p>
 *
 * @author Genevieve Turner
 *
 */
@Provider
public class AccessDeniedExceptionMapper implements ExceptionMapper<AccessDeniedException> {
	static final Logger LOGGER = LoggerFactory.getLogger(AccessDeniedExceptionMapper.class);
	
	@Context
	private HttpHeaders headers;
	
	@Context
	private ServletContext context;

	@Override
	public Response toResponse(AccessDeniedException exception) {
		LOGGER.debug("In toResponse");
		Response resp;
		List<MediaType> acceptableMediaTypes = headers.getAcceptableMediaTypes();
		
		LOGGER.warn("User {} requested a resource to which they do not have access: {}", SecurityContextHolder.getContext().getAuthentication().getName(), exception.getMessage());
		
		boolean isLoggedIn = false;
		try {
			if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
					!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
				isLoggedIn = true;
			}
			
			if (acceptableMediaTypes.contains(MediaType.TEXT_HTML_TYPE)) {
				if (isLoggedIn) {
					resp = Response.status(Status.UNAUTHORIZED).entity(new Viewable("/jsp/access_denied.jsp")).build();
				}
				else {
					String uri = context.getContextPath() + "/login";
					resp = Response.seeOther(new URI(uri)).build();
				}
			}
			else {
				resp = Response.status(Status.UNAUTHORIZED).entity(exception.getMessage()).build();
			}
			return resp;
		}
		catch (Exception e) {
			LOGGER.error("Exception generating access denied response", e);
			return Response.serverError().entity("Exception generating access denied response").build();
		}
	}
}
