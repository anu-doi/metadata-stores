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

import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;

public class StoreNS {
	protected static final String uri = "http://anu.edu.au/metadata-store#";
	protected static final String prefix = "store";
	
	/**
	 * Get the prefix for the store namespace
	 * 
	 * @return The prefix
	 */
	public static String getPrefix() {
		return prefix;
	}
	
	/**
	 * Get the uri for the store namespace
	 * 
	 * @return The uri
	 */
	public static String getURI() {
		return uri;
	}
	
	public static final String TITLE = uri + StoreAttributes.TITLE;
	public static final String SUBJECT = uri + StoreAttributes.SUBJECT;
	public static final String SUBJECT_PERCENT = uri + StoreAttributes.FOR_PERCENT;
	public static final String ISBN = uri + StoreAttributes.ISBN;
	public static final String ISSN = uri + StoreAttributes.ISSN;
	public static final String AUTHORS = uri + StoreAttributes.AUTHORS;
	public static final String SERIES = uri + StoreAttributes.SERIES;
	public static final String CONTENT = uri + StoreAttributes.CONTENT;
	public static final String DESCRIPTION = uri + StoreAttributes.DESCRIPTION;
	public static final String SERIES_DESCRIPTION = uri + StoreAttributes.SERIES_DESCRIPTION;
	public static final String CITATION_URL = uri + StoreAttributes.CITATION_URL;
	public static final String CONTRACT_CODE = uri + StoreAttributes.CONTRACT_CODE;
	public static final String START_DATE = uri + StoreAttributes.START_DATE;
	public static final String END_DATE = uri + StoreAttributes.END_DATE;
	public static final String STATUS = uri + StoreAttributes.STATUS;
	public static final String FUNDS_PROVIDER = uri + StoreAttributes.FUNDS_PROVIDER;
	public static final String REFERENCE_NUMBER = uri + StoreAttributes.REFERENCE_NUMBER;
	public static final String ARIES_ID = uri + StoreAttributes.ARIES_ID;
	public static final String TYPE = uri + StoreAttributes.TYPE;
	public static final String YEAR = uri + StoreAttributes.YEAR;
	public static final String FIRST_AUTHOR_ID = uri + StoreAttributes.FIRST_AUTHOR_ID;
	public static final String PUBLICATION_NAME = uri + StoreAttributes.PUBLICATION_NAME;
	public static final String CATEGORY = uri + StoreAttributes.CATEGORY;
	public static final String CREATOR = uri + StoreAttributes.CREATOR;
	public static final String PUBLISHER = uri + StoreAttributes.PUBLISHER;
	public static final String CONTRIBUTOR = uri + StoreAttributes.CONTRIBUTOR;
	public static final String DATE = uri + StoreAttributes.DATE;
	public static final String FORMAT = uri + StoreAttributes.FORMAT;
	public static final String IDENTIFIER = uri + StoreAttributes.IDENTIFIER;
	public static final String SOURCE = uri + StoreAttributes.SOURCE;
	public static final String LANGUAGE = uri + StoreAttributes.LANGUAGE;
	public static final String RELATION = uri + StoreAttributes.RELATION;
	public static final String COVERAGE = uri + StoreAttributes.COVERAGE;
	public static final String RIGHTS = uri + StoreAttributes.RIGHTS;
}
