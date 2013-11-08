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
package au.edu.anu.metadatastores.services.store.commands;

import org.kohsuke.args4j.CmdLineParser;

/**
 * <p>CommandConstant<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p></p>
 * 
 * @author Genevieve Turner
 *
 */
public class CommandUtil {
	public static final String STORE = "store";
	public static final String GRANT = "grant";
	public static final String PUB = "publication";
	public static final String PERSON = "person";
	public static final String EPRESS = "epress";
	public static final String RDF = "rdf";
	
	public static void printUsage(String command, Class<?> clazz) {
		try {
			CmdLineParser parser = new CmdLineParser(clazz.newInstance());
			System.out.print(CommandUtil.STORE + " " + CommandUtil.PUB);
			parser.printSingleLineUsage(System.out);
			System.out.println("");
			parser.printUsage(System.out);
		}
		catch (IllegalAccessException e) {
			System.err.println("Exception accessing class " + clazz.getName() + ". " + e.getMessage());
		}
		catch (InstantiationException e) {
			System.err.println("Exception instantiationg class " + clazz.getName() + ". " + e.getMessage());
		}
	}
}
