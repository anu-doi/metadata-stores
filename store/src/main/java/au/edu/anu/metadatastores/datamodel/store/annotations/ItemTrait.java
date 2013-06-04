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
 * <p>ItemProperties<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>This annotation is used in the transformation of an object to an Item. It can indicate which fields are associated
 * with with the appropriate fields in an Item</p>
 * 
 * @author Genevieve Turner
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ItemTrait {
	/**
	 * Indicates which field should be used to 
	 * 
	 * @return The extId field
	 */
	String extId() default "";
	
	/**
	 * Indicates which field should be used to find the Items title from
	 * 
	 * @return The title field
	 */
	String title() default "";
}
