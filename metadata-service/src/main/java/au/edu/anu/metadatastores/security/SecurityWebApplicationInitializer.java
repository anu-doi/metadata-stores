package au.edu.anu.metadatastores.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * <p>SecurityWebApplicationInitializer</p>
 *
 * <p>The Australian National University</p>
 *
 * <p>Class to initialize the security configuration</p>
 *
 * @author Genevieve Turner
 *
 */
public class SecurityWebApplicationInitializer extends
		AbstractSecurityWebApplicationInitializer {
	public SecurityWebApplicationInitializer() {
		super(SecurityConfig.class);
	}
}
