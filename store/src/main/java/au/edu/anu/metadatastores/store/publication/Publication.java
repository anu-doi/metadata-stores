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

package au.edu.anu.metadatastores.store.publication;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import au.edu.anu.metadatastores.store.misc.Subject;
import au.edu.anu.metadatastores.store.people.Person;

/**
 * <p>Publication<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Class to hold information about publications</p>
 * 
 * @author Genevieve Turner
 *
 */
@XmlRootElement(name="publication")
public class Publication {
	private String ariesId;
	private String type;
	private String title;
	private String year;
	private String firstAuthor;
	private List<Person> authors = new ArrayList<Person>();
	private String publicationName;
	private String category;
	private String ISBN;
	private String ISSN;
	private List<Subject> anzforSubjects = new ArrayList<Subject>();
	
	/**
	 * Get the publications aries id
	 * 
	 * @return The aries id
	 */
	@XmlElement(name="aries-id")
	public String getAriesId() {
		return ariesId;
	}
	
	/**
	 * Set the publications aries id
	 * 
	 * @param ariesId The aries id
	 */
	public void setAriesId(String ariesId) {
		this.ariesId = ariesId;
	}
	
	/**
	 * Get the publication type
	 * 
	 * @return The publication type
	 */
	@XmlElement(name="type")
	public String getType() {
		return type;
	}
	
	/**
	 * Set the publication type
	 * 
	 * @param type The publication type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Get the publication title
	 * 
	 * @return The title
	 */
	@XmlElement(name="title")
	public String getTitle() {
		return title;
	}
	
	/**
	 * Set the publication title
	 * 
	 * @param title The title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the publication year
	 * 
	 * @return The publication year
	 */
	@XmlElement(name="publication-year")
	public String getYear() {
		return year;
	}
	
	/**
	 * Set the publication year
	 * 
	 * @param year The publication year
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * Get the publications first author
	 * 
	 * @return The first author
	 */
	@XmlElement(name="first-author")
	public String getFirstAuthor() {
		return firstAuthor;
	}
	
	/**
	 * Set the publication first author
	 * 
	 * @param firstAuthor The first author
	 */
	public void setFirstAuthor(String firstAuthor) {
		this.firstAuthor = firstAuthor;
	}

	/**
	 * Get the name of the journal/conference/book in which the publication was published
	 * 
	 * @return The publication name The name of the publication
	 */
	@XmlElement(name="publication-name")
	public String getPublicationName() {
		return publicationName;
	}
	
	/**
	 * Set the name of the journal/conference/book in which the publication was published
	 * 
	 * @param publicationName The name of the publication
	 */
	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}

	/**
	 * Get the publications authors
	 * 
	 * @return The authors
	 */
	@XmlElement(name="author")
	public List<Person> getAuthors() {
		return authors;
	}

	/**
	 * Set the publications authors
	 * 
	 * @param authors The authors
	 */
	public void setAuthors(List<Person> authors) {
		this.authors = authors;
	}

	/**
	 * Get the publications category.  The category is used for reporting requirements e.g. whether it is HERDC compliant.
	 * 
	 * @return The category
	 */
	@XmlElement(name="category")
	public String getCategory() {
		return category;
	}

	/**
	 * Set the publications category.  The category is used for reporting requirements e.g. whether it is HERDC compliant.
	 * 
	 * @param category The category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Get the publications ISBN
	 * 
	 * @return The ISBN
	 */
	@XmlElement(name="isbn")
	public String getISBN() {
		return ISBN;
	}

	/**
	 * Set the publications ISBN
	 * 
	 * @param iSBN The ISBN
	 */
	public void setISBN(String iSBN) {
		this.ISBN = iSBN;
	}

	/**
	 * Get the publications ISSN
	 * 
	 * @return The ISSN
	 */
	@XmlElement(name="issn")
	public String getISSN() {
		return ISSN;
	}

	/**
	 * Set the publications ISSN
	 * 
	 * @param iSSN The ISSN
	 */
	public void setISSN(String iSSN) {
		this.ISSN = iSSN;
	}
	
	/**
	 * Get the publications fields of research
	 * 
	 * @return The fields of research
	 */
	@XmlElement(name="for-subject")
	public List<Subject> getAnzforSubjects() {
		return anzforSubjects;
	}
	
	/**
	 * Set the publications fields of research
	 * 
	 * @param anzforSubjects The fields of research
	 */
	public void setAnzforSubjects(List<Subject> anzforSubjects) {
		this.anzforSubjects = anzforSubjects;
	}
}
