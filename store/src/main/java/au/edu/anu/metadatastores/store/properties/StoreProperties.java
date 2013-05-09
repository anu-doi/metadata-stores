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

package au.edu.anu.metadatastores.store.properties;

import java.util.Properties;

import au.edu.anu.metadatastores.util.properties.PropertyLoader;

/**
 * <p>StoreProperties<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Retries properties for the store application</p>
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
