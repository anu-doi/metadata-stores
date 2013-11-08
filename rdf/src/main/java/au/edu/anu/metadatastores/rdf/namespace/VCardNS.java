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
package au.edu.anu.metadatastores.rdf.namespace;

/**
 * <p>VCardNS</p>
 *
 * <p>The Australian National University</p>
 *
 * <p>This class has been created because annotations can't take the 'Property' value of Jena's VCARD.</p>
 *
 * @author Genevieve Turner
 */
public class VCardNS {
	public static final String uri = "http://www.w3.org/2001/vcard-rdf/3.0#";
	public static final String Street = uri + "Street";
	public static final String AGENT = uri + "AGENT";
	public static final String SOURCE = uri + "SOURCE";
	public static final String LOGO = uri + "LOGO";
	public static final String BDAY = uri + "LOGO";
	public static final String REV = uri + "REV";
	public static final String SORT_STRING = uri + "SORT-STRING";
	public static final String Orgname = uri + "Orgname";
	public static final String CATEGORIES = uri + "CATEGORIES";
	public static final String N = uri + "N";
	public static final String Pcode = uri + "Pcode";
	public static final String Prefix = uri + "Prefix";
	public static final String PHOTO = uri + "PHOTO";
	public static final String FN = uri + "FN";
	public static final String ORG = uri + "ORG";
	public static final String Suffix = uri + "Suffix";
	public static final String CLASS = uri + "CLASS";
	public static final String ADR = uri + "ADR";
	public static final String Region = uri + "Region";
	public static final String GEO = uri + "GEO";
	public static final String Extadd = uri + "Extadd";
	public static final String GROUP = uri + "GROUP";
	public static final String EMAIL = uri + "EMAIL";
	public static final String UID = uri + "UID";
	public static final String Family = uri + "Family";
	public static final String TZ = uri + "TZ";
	public static final String NAME = uri + "NAME";
	public static final String Orgunit = uri + "Orgunit";
	public static final String Country = uri + "Country";
	public static final String SOUND = uri + "SOUND";
	public static final String TITLE = uri + "TITLE";
	public static final String NOTE = uri + "NOTE";
	public static final String MAILER = uri + "MAILER";
	public static final String Other = uri + "Other";
	public static final String Locality = uri + "Locality";
	public static final String Pobox = uri + "Pobox";
	public static final String KEY = uri + "KEY";
	public static final String PRODID = uri + "PRODID";
	public static final String Given = uri + "Given";
	public static final String LABEL = uri + "LABEL";
	public static final String TEL = uri + "TEL";
	public static final String NICKNAME = uri + "NICKNAME";
	public static final String ROLE = uri + "ROLE";
}
