package au.edu.anu.metadatastores.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import au.edu.anu.metadatastores.datamodel.store.SystemType;
import au.edu.anu.metadatastores.store.search.ItemDTO;

/**
 * <p>PermissionServiceImpl</p>
 *
 * <p>The Australian National University</p>
 *
 * <p>Implementation class for checking/filtering permissions for the logged in user</p>
 *
 * @author Genevieve Turner
 *
 */
@Service("permissionServiceImpl")
public class PermissionServiceImpl implements PermissionService {
	static final Logger LOGGER = LoggerFactory.getLogger(PermissionServiceImpl.class);
	
	public void checkGrant() {
		LOGGER.debug("Has Grant Permissions");
	}

	public void checkPublication() {
		LOGGER.debug("Has Publication Permissions");
	}

	public void checkPerson() {
		LOGGER.debug("Has Person Permissions");
	}
	
	public void checkDigitalCollection() {
		LOGGER.debug("Has Digital Collection Permissions");
	}
	
	public void checkDataCommonsObject() {
		LOGGER.debug("Has Data Commons Permissions");
	}
	
	public void checkEpress() {
		LOGGER.debug("Has E Press Permissions");
	}
	
	public List<ItemDTO> filterItems(List<ItemDTO> items) {
		LOGGER.debug("Filter the list of Item objects");
		return items;
	}
	
	public List<SystemType> filterSystemTypes(List<SystemType> systemTypes) {
		LOGGER.debug("Filter the list of system types");
		return systemTypes;
	}
}
