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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//TODO example
/**
 * 
 * <p>RDFSetWithDefault</p>
 *
 * <p>The Australian National University</p>
 *
 * <p>A set of defaults associated with a field</p>
 *
 * @author Genevieve Turner
 * @see au.edu.anu.metadatastores.rdf.annotation.RDFSetWithDefault
 *
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RDFSetWithDefault {
	//TODO example
	/**
	 * The uri to attach the parent object with
	 * 
	 * @return The uri
	 */
	String uri();
	
	/**
	 * The field that is surrounded by the defaults
	 * 
	 * @return The field
	 */
	String field();
	
	/**
	 * The default values that surround the field
	 * 
	 * @return The default fields
	 */
	RDFDefaultTriple[] defaults() default {};
}
