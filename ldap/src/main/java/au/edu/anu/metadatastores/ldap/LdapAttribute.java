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

package au.edu.anu.metadatastores.ldap;

/**
 * Attributes that can be found in ldap
 * @author u5125986
 *
 */
/**
 * LdapAttribute
 * 
 * The Australian National University
 * 
 * Attributes to retrieve from ldap
 * 
 * @author Genevieve Turner
 *
 */
public class LdapAttribute {
	public static final String EMAIL = "mail";
	public static final String PHONE = "telephoneNumber";
	public static final String FAX = "fax";
	public static final String POSTAL_ADDR = "postalAddress";
	public static final String SURNAME = "sn";
	public static final String FIRSTNAME = "givenName";
	public static final String COMMON_NAME = "cn";
	public static final String UID = "uid";
	public static final String OBJECT_CLASS = "objectClass";
}
