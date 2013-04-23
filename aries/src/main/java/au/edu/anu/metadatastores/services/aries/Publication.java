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
 * Publication
 * 
 * The Australian National University
 * 
 * Class for holding publication information
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
public interface Publication {
	/**
	 * Get the publications aries id
	 * 
	 * @return The aries id
	 */
	public String getAriesId();
	
	/**
	 * Set the publications aries id
	 * 
	 * @param ariesId The aries id
	 */
	public void setAriesId(String ariesId);
	
	/**
	 * Get the university/external reference numbers of the publications authors
	 * 
	 * @return The authors
	 */
	public String[] getAuthors();
	
	/**
	 * Set the university/external reference numbers of the publications authors
	 * 
	 * @param author The authors
	 */
	public void setAuthors(String[] author);
	
	/**
	 * Get the title of the publication
	 * 
	 * @return The title of the publication
	 */
	public String getPublicationTitle();
	
	/**
	 * Set the title of the publication
	 * 
	 * @param publicationTitle
	 */
	public void setPublicationTitle(String publicationTitle);
	
	/**
	 * Get the name of the journal, conference or book in which the publication occurred
	 * 
	 * @return The name of the publication
	 */
	public String getPublicationName();
	
	/**
	 * Set the name of the journal, conference, or book in which the publication occurred
	 * 
	 * @param publicationName
	 */
	public void setPublicationName(String publicationName);
	
	/**
	 * Get the publication type, i.e. if it is a Journal, Conference or Book
	 * 
	 * @return The publication type
	 */
	public String getPublicationType();
	
	/**
	 * Set the publication type, i.e. if it is a Journal, Conference or Book
	 * 
	 * @param publicationType The publication type
	 */
	public void setPublicationType(String publicationType);
	
	/**
	 * Get the date of publication
	 * 
	 * @return The publication date
	 */
	public String getPublicationDate();
	
	/**
	 * Set the date of publication
	 * 
	 * @param publicationDate The publication date
	 */
	public void setPublicationDate(String publicationDate);
	
	/**
	 * Get the publication category
	 * 
	 * @return The publication category
	 */
	public String getPublicationCategory();
	
	/**
	 * Set the publication category
	 * 
	 * @param publicationCategory The publication category
	 */
	public void setPublicationCategory(String publicationCategory);
	
	/**
	 * Get the publication website
	 * 
	 * @return The publication website
	 */
	public String getPublicationWebsite();
	
	/**
	 * Set the publication website
	 * 
	 * @param publicationWebsite The publication website
	 */
	public void setPublicationWebsite(String publicationWebsite);
	
	/**
	 * Get the publication ISSN
	 * 
	 * @return The ISSN
	 */
	public String getISSN();
	
	/**
	 * Set the publication ISSN
	 * 
	 * @param issn The ISSN
	 */
	public void setISSN(String issn);
	
	/**
	 * Get the publication ISBN
	 * 
	 * @return The ISBN
	 */
	public String getISBN();
	
	/**
	 * Set the publication ISBN
	 * 
	 * @param isbn The ISBN
	 */
	public void setISBN(String isbn);
	
	/**
	 * Get the first field of research subject
	 * 
	 * @return The field of research subject
	 */
	public Subject getForSubject1();
	
	/**
	 * Set the first field of research subject
	 * 
	 * @param forSubject1 The field of research subject
	 */
	public void setForSubject1(Subject forSubject1);

	/**
	 * Get the second field of research subject
	 * 
	 * @return The  field of research subject
	 */
	public Subject getForSubject2();
	
	/**
	 * Set the second field of research subject
	 * 
	 * @param forSubject2 The field of research subject
	 */
	public void setForSubject2(Subject forSubject2);
	
	/**
	 * Get the third field of research subject
	 * 
	 * @return  The field of research subject
	 */
	public Subject getForSubject3();
	
	/**
	 * Set the third field of  research subject
	 * 
	 * @param forSubject3 The field of  research subject
	 */
	public void setForSubject3(Subject forSubject3);
}
