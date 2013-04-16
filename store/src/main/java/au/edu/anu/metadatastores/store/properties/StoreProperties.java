package au.edu.anu.metadatastores.store.properties;

import java.util.Properties;

import au.edu.anu.metadatastores.util.properties.PropertyLoader;

/**
 * StoreProperties
 * 
 * The Australian National University
 * 
 * Retries properties for the store application
 * 
 * @author Genevieve Turner
 *
 */
public class StoreProperties {
	private static Properties properties_ = PropertyLoader.loadProperties("store.properties");
	
	/**
	 * Get the property with the given name
	 * 
	 * @param property The name of the property to get
	 * @return The value of the property
	 */
	public static String getProperty(String property) {
		return properties_.getProperty(property);
	}
	
	/**
	 * Reload the properties
	 */
	public static void reloadProperties() {
		properties_ = PropertyLoader.loadProperties("store.properties");
	}
}
