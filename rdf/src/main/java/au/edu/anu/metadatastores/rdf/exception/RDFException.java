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
package au.edu.anu.metadatastores.rdf.exception;

/**
 * <p>RDFException</p>
 *
 * <p>The Australian National University</p>
 *
 * <p>RDF Exception</p>
 *
 * @author Genevieve Turner
 *
 */
public class RDFException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param message The message associated with the exception
	 */
	public RDFException(String message) {
		super(message);
	}
	
	/**
	 * Constructor
	 * 
	 * @param t The exceptions throwable
	 */
	public RDFException(Throwable t) {
		super(t);
	}
	
	/**
	 * Constructor
	 * 
	 * @param message The message associated with the exception
	 * @param t The exceptions throwable
	 */
	public RDFException(String message, Throwable t) {
		super(message, t);
	}
}
