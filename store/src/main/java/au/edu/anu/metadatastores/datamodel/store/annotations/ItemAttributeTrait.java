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
 * <p>ItemAttributeTrait<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>This is an annotation for transformation of an objects fields in to ItemAttributes to be saved in the databases</p>
 * 
 * @author Genevieve Turner
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ItemAttributeTrait {
	/**
	 * The attribute type to store in the database.
	 * 
	 * @see au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes
	 * 
	 * @return The attribute type
	 */
	String attrType();
	
	/**
	 * The trait type.  This is an indicator of how the field should be processed.
	 * 
	 * @see au.edu.anu.metadatastores.datamodel.store.annotations.TraitType
	 * 
	 * @return The trait type
	 */
	TraitType traitType();
	
	/**
	 * The level for which the word is associated.  The level is used when the transformation is occurring
	 * to determine which fields should be shown.  The lower the number the less likely the field is to be
	 * shown. 
	 * 
	 * @return The level
	 */
	int level() default 1;
}
