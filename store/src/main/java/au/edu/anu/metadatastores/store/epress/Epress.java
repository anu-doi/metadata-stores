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
package au.edu.anu.metadatastores.store.epress;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import au.edu.anu.metadatastores.datamodel.store.annotations.ItemAttributeTrait;
import au.edu.anu.metadatastores.datamodel.store.annotations.ItemTrait;
import au.edu.anu.metadatastores.datamodel.store.annotations.TraitType;
import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;
import au.edu.anu.metadatastores.rdf.annotation.RDFSubject;
import au.edu.anu.metadatastores.rdf.annotation.RDFType;
import au.edu.anu.metadatastores.rdf.annotation.RDFUri;
import au.edu.anu.metadatastores.rdf.namespace.StoreNS;

/**
 * <p>Epress<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p></p>
 * 
 * @author Genevieve Turner
 *
 */
@ItemTrait(extId="extId", title="title")
@XmlRootElement(name="epress")
@RDFType("EPRESS")
public class Epress {
	private String extId;
	private List<String> ISBNs = new ArrayList<String>();
	private List<String> ISSNs = new ArrayList<String>();
	private String title;
	private String authors;
	private List<String> series = new ArrayList<String>();
	private List<String> content = new ArrayList<String>();
	private List<String> descriptions = new ArrayList<String>();
	private List<String> seriesDescriptions = new ArrayList<String>();
	private List<String> citationURLs = new ArrayList<String>();
	
	/**
	 * Constructor
	 */
	public Epress() {
		
	}

	/**
	 * Get the ext id
	 * 
	 * @return The ext id
	 */
	@XmlTransient
	public String getExtId() {
		return extId;
	}

	/**
	 * Set the ext id
	 * 
	 * @param extId The ext id
	 */
	public void setExtId(String extId) {
		this.extId = extId;
	}

	/**
	 * Get the ISBNs
	 * 
	 * @return The ISBNs
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.ISBN, traitType=TraitType.STRING_LIST)
	@XmlElement(name="isbn")
	@RDFUri(uri=StoreNS.ISBN)
	@RDFSubject
	public List<String> getISBNs() {
		return ISBNs;
	}

	/**
	 * Set the ISBNs
	 * 
	 * @param iSBN The ISBNs
	 */
	public void setISBNs(List<String> iSBNs) {
		ISBNs = iSBNs;
	}

	/**
	 * Get the ISSNs
	 * 
	 * @return The ISSNs
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.ISSN, traitType=TraitType.STRING_LIST)
	@XmlElement(name="issn")
	@RDFUri(uri=StoreNS.ISSN)
	@RDFSubject
	public List<String> getISSNs() {
		return ISSNs;
	}

	/**
	 * Set the ISSN
	 * 
	 * @param iSSN The ISSN
	 */
	public void setISSNs(List<String> iSSNs) {
		ISSNs = iSSNs;
	}

	/**
	 * Get the title
	 * 
	 * @return The title
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.TITLE, traitType=TraitType.STRING)
	@XmlElement(name="title")
	@RDFUri(uri=StoreNS.TITLE)
	@RDFSubject
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title
	 * 
	 * @param title The title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the authors/editors etc.
	 * 
	 * @return The authors
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.AUTHORS, traitType=TraitType.STRING)
	@XmlElement(name="authors")
	@RDFUri(uri=StoreNS.AUTHORS)
	@RDFSubject
	public String getAuthors() {
		return authors;
	}

	/**
	 * Set the authors/editors etc.
	 * 
	 * @param authors The authors
	 */
	public void setAuthors(String authors) {
		this.authors = authors;
	}

	/**
	 * Get the series
	 * 
	 * @return The series
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.SERIES, traitType=TraitType.STRING_LIST)
	@XmlElement(name="series")
	@RDFUri(uri=StoreNS.SERIES)
	@RDFSubject
	public List<String> getSeries() {
		return series;
	}

	/**
	 * Set the series
	 * 
	 * @param series The series
	 */
	public void setSeries(List<String> series) {
		this.series = series;
	}

	/**
	 * Get the content
	 * 
	 * @return The content
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.CONTENT, traitType=TraitType.STRING_LIST)
	@XmlElement(name="content")
	@RDFUri(uri=StoreNS.CONTENT)
	@RDFSubject
	public List<String> getContent() {
		return content;
	}

	/**
	 * Set the content
	 * 
	 * @param content The content
	 */
	public void setContent(List<String> content) {
		this.content = content;
	}

	/**
	 * Get the descriptions
	 * 
	 * @return The descriptions
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.DESCRIPTION, traitType=TraitType.STRING_LIST)
	@XmlElement(name="description")
	@RDFUri(uri=StoreNS.DESCRIPTION)
	@RDFSubject
	public List<String> getDescriptions() {
		return descriptions;
	}

	/**
	 * Set the descriptions
	 * 
	 * @param descriptions The descriptions
	 */
	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

	/**
	 * Get the series description
	 * 
	 * @return The series description
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.SERIES_DESCRIPTION, traitType=TraitType.STRING_LIST)
	@XmlElement(name="series-description")
	@RDFUri(uri=StoreNS.SERIES_DESCRIPTION)
	@RDFSubject
	public List<String> getSeriesDescriptions() {
		return seriesDescriptions;
	}

	/**
	 * Set the series description
	 * 
	 * @param seriesDescriptions The series description
	 */
	public void setSeriesDescriptions(List<String> seriesDescriptions) {
		this.seriesDescriptions = seriesDescriptions;
	}

	/**
	 * Get the citation URLs
	 * 
	 * @return The citation URLs
	 */
	@ItemAttributeTrait(attrType=StoreAttributes.CITATION_URL, traitType=TraitType.STRING_LIST)
	@XmlElement(name="citation-url")
	@RDFUri(uri=StoreNS.CITATION_URL)
	@RDFSubject
	public List<String> getCitationURLs() {
		return citationURLs;
	}

	/**
	 * Set the citation URLs
	 * 
	 * @param citationURLs The citation URLs
	 */
	public void setCitationURLs(List<String> citationURLs) {
		this.citationURLs = citationURLs;
	}
}
