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
package au.edu.anu.metadatastores.util.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * ExtensionFileFilter
 * 
 * The Australian National University
 * 
 * @author Genevieve Turner
 *
 */
public class ExtensionFileFilter implements FilenameFilter {
	private String extension;
	
	/**
	 * Constructor
	 * 
	 * @param extension The extension name to filter
	 */
	public ExtensionFileFilter(String extension) {
		this.extension = "." + extension;
	}
	
	/**
	 * Accept or reject the file name
	 * 
	 * @param dir The directory of the file to filter
	 * @param name The name of the file to filter
	 * @return True of the filename ends with the extension
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
	 */
	public boolean accept(File dir, String name) {
		return name.endsWith(extension);
	}

}
