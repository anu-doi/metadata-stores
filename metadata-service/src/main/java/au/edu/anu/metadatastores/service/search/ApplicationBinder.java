package au.edu.anu.metadatastores.service.search;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import au.edu.anu.metadatastores.security.PermissionService;
import au.edu.anu.metadatastores.security.PermissionServiceImpl;

/**
 * <p>ApplicationBinder</p>
 *
 * <p>The Australian National University</p>
 *
 * <p>Binds classes for injection.</p>
 *
 * @author Genevieve Turner
 *
 */
public class ApplicationBinder extends AbstractBinder {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(DisplayPage.class).to(DisplayPage.class);
		bind(PermissionServiceImpl.class).to(PermissionService.class);
		bind(SearchOptions.class).to(SearchOptions.class);
	}

}
