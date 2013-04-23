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

package au.edu.anu.metadatastores.services.aries;

/**
 * PublicationImpl
 * 
 * The Australian National University
 * 
 * Implementation class for publication information
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
public class PublicationImpl implements Publication {
	private String ariesId;
	private String[] authors;
	private String publicationTitle;
	private String publicationName;
	private String publicationType;
	private String publicationDate;
	private String publicationCategory;
	private String publicationWebsite;
	private Subject forSubject1;
	private Subject forSubject2;
	private Subject forSubject3;
	private String isbn;
	private String issn;
	
	/**
	 * Get the publications aries id
	 * 
	 * @return The aries id
	 */
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
	 * Get the university/external reference numbers of the publications authors
	 * 
	 * @return The authors
	 */
	public String[] getAuthors() {
		return authors;
	}

	/**
	 * Set the university/external reference numbers of the publications authors
	 * 
	 * @param author The authors
	 */
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	/**
	 * Get the title of the publication
	 * 
	 * @return The title of the publication
	 */
	public String getPublicationTitle() {
		return publicationTitle;
	}

	/**
	 * Set the title of the publication
	 * 
	 * @param publicationTitle
	 */
	public void setPublicationTitle(String publicationTitle) {
		this.publicationTitle = publicationTitle;
	}

	/**
	 * Get the name of the journal, conference or book in which the publication occurred
	 * 
	 * @return The name of the publication
	 */
	public String getPublicationName() {
		return publicationName;
	}

	/**
	 * Set the name of the journal, conference, or book in which the publication occurred
	 * 
	 * @param publicationName
	 */
	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}

	/**
	 * Get the publication type, i.e. if it is a Journal, Conference or Book
	 * 
	 * @return The publication type
	 */
	public String getPublicationType() {
		return publicationType;
	}

	/**
	 * Set the publication type, i.e. if it is a Journal, Conference or Book
	 * 
	 * @param publicationType The publication type
	 */
	public void setPublicationType(String publicationType) {
		this.publicationType = publicationType;
	}

	/**
	 * Get the date of publication
	 * 
	 * @return The publication date
	 */
	public String getPublicationDate() {
		return publicationDate;
	}

	/**
	 * Set the date of publication
	 * 
	 * @param publicationDate The publication date
	 */
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	/**
	 * Get the publication category
	 * 
	 * @return The publication category
	 */
	public String getPublicationCategory() {
		return publicationCategory;
	}

	/**
	 * Set the publication category
	 * 
	 * @param publicationCategory The publication category
	 */
	public void setPublicationCategory(String publicationCategory) {
		this.publicationCategory = publicationCategory;
	}

	/**
	 * Get the publication website
	 * 
	 * @return The publication website
	 */
	public String getPublicationWebsite() {
		return publicationWebsite;
	}

	/**
	 * Set the publication website
	 * 
	 * @param publicationWebsite The publication website
	 */
	public void setPublicationWebsite(String publicationWebsite) {
		this.publicationWebsite = publicationWebsite;
	}

	/**
	 * Get the first field of research subject
	 * 
	 * @return The field of research subject
	 */
	public Subject getForSubject1() {
		return forSubject1;
	}

	/**
	 * Set the first field of research subject
	 * 
	 * @param forSubject1 The field of research subject
	 */
	public void setForSubject1(Subject forSubject1) {
		this.forSubject1 = forSubject1;
	}

	/**
	 * Get the second field of research subject
	 * 
	 * @return The  field of research subject
	 */
	public Subject getForSubject2() {
		return forSubject2;
	}

	/**
	 * Set the second field of research subject
	 * 
	 * @param forSubject2 The field of research subject
	 */
	public void setForSubject2(Subject forSubject2) {
		this.forSubject2 = forSubject2;
	}

	/**
	 * Get the third field of research subject
	 * 
	 * @return  The field of research subject
	 */
	public Subject getForSubject3() {
		return forSubject3;
	}

	/**
	 * Set the third field of  research subject
	 * 
	 * @param forSubject3 The field of  research subject
	 */
	public void setForSubject3(Subject forSubject3) {
		this.forSubject3 = forSubject3;
	}

	/**
	 * Get the publication ISSN
	 * 
	 * @return The ISSN
	 */
	public String getISSN() {
		return issn;
	}

	/**
	 * Set the publication ISSN
	 * 
	 * @param issn The ISSN
	 */
	public void setISSN(String issn) {
		this.issn = issn;
	}

	/**
	 * Get the publication ISBN
	 * 
	 * @return The ISBN
	 */
	public String getISBN() {
		return isbn;
	}

	/**
	 * Set the publication ISBN
	 * 
	 * @param isbn The ISBN
	 */
	public void setISBN(String isbn) {
		this.isbn = isbn;
	}
}
