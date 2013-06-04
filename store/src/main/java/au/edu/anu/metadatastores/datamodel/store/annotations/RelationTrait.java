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
package au.edu.anu.metadatastores.datamodel.store.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>RelationTrait<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Assigns values to split apart a relation and create ItemAttributes from the relation</p>
 * 
 * @author Genevieve Turner
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RelationTrait {
	/**
	 * The the relation part types.  The part types will be the attribute types that are stored
	 * in the database
	 * 
	 * @see au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes
	 * 
	 * @return
	 */
	String[] partTypes();
	
	/**
	 * The string of fields to split the relation apart
	 * 
	 * @return The delimiter
	 */
	String delimiter();
}
