package au.edu.anu.metadatastores.security;

import java.util.List;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import au.edu.anu.metadatastores.datamodel.store.SystemType;
import au.edu.anu.metadatastores.store.search.ItemDTO;

/**
 * <p>PermissionService</p>
 *
 * <p>The Australian National University</p>
 *
 * <p>Interface to provide limiting of permissions</p>
 *
 * @author Genevieve Turner
 *
 */
public interface PermissionService {
	/**
	 * Check if the user has permissions to do actions with grants
	 */
	@PreAuthorize("hasRole('ROLE_STAFF')")
	public void checkGrant();

	/**
	 * Check if the user has permissions to do actions with publications
	 */
	@PreAuthorize("hasRole('ROLE_STAFF')")
	public void checkPublication();

	/**
	 * Check if the user has permissions to do actions with person
	 */
	@PreAuthorize("hasRole('ROLE_STAFF')")
	public void checkPerson();

	/**
	 * Check if the user has permissions to do actions with digital collections
	 */
	@PreAuthorize("permitAll")
	public void checkDigitalCollection();

	/**
	 * Check if the user has permissions to do actions with data commons
	 */
	@PreAuthorize("permitAll")
	public void checkDataCommonsObject();

	/**
	 * Check if the user has permissions to do actions with epress
	 */
	@PreAuthorize("hasRole('ROLE_STAFF')")
	public void checkEpress();

	/**
	 * Return a list of items for which the user has permissions to view
	 */
	@PostFilter("hasRole('ROLE_STAFF') or filterObject.extSystem == 'Digital Collections' or  filterObject.extSystem == 'Data Commons' ")
	public List<ItemDTO> filterItems(List<ItemDTO> items);

	/**
	 * Return a list of system types for which the user has permissions to view
	 */
	@PostFilter("hasRole('ROLE_STAFF') or filterObject.title == 'Digital Collections' or  filterObject.title == 'Data Commons' ")
	public List<SystemType> filterSystemTypes(List<SystemType> systemTypes);
}
