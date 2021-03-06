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
package au.edu.anu.metadatastores.rdf.annotation;

/**
 * <p>RDFDefaultTriple</p>
 *
 * <p>The Australian National University</p>
 *
 * <p>Annotation for creating a default triple with the value of the predicate and object</p>
 *
 * @author Genevieve Turner
 *
 */
public @interface RDFDefaultTriple {
	/**
	 * The string representation of predicate for the default object
	 * 
	 * @return The predicate
	 */
	String predicate();
	
	/**
	 * The value of the object for the default triple
	 * 
	 * @return The object
	 */
	String object();
}
