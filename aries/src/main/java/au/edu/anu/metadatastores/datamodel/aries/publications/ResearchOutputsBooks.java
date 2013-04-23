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

package au.edu.anu.metadatastores.datamodel.aries.publications;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ResearchOutputsBooks
 * 
 * The Australian National University
 * 
 * Entity class for research_outputs_books table
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "research_outputs_books")
public class ResearchOutputsBooks {
	private Integer intBookCode;
	private String chrBookName;
	private String chrYear;
	private String chrISBN;
	private Set<ResearchOutputsData1> researchOutputsData1s = new HashSet<ResearchOutputsData1>(0);
	
	/**
	 * Constructor
	 */
	public ResearchOutputsBooks() {
	}
	
	/**
	 * Constructor
	 * 
	 * @param intBookCode The book code
	 */
	public ResearchOutputsBooks(Integer intBookCode) {
		this.intBookCode = intBookCode;
	}

	/**
	 * Constructor
	 * 
	 * @param intBookCode The book code
	 * @param chrBookName The book name
	 * @param chrYear The year of publication
	 * @param researchOutputsData1s The research output information
	 */
	public ResearchOutputsBooks(Integer intBookCode,
			String chrBookName, String chrYear, Set<ResearchOutputsData1> researchOutputsData1s) {
		this.intBookCode = intBookCode;
		this.chrBookName = chrBookName;
		this.chrYear = chrYear;
		this.researchOutputsData1s = researchOutputsData1s;
	}

	/**
	 * Get the book code
	 * 
	 * @return The book code
	 */
	@Id
	@Column(name = "intBookCode", unique = true, nullable = false, length = 45)
	public Integer getIntBookCode() {
		return intBookCode;
	}

	/**
	 * Set the book code
	 * 
	 * @param intBookCode The book code
	 */
	public void setIntBookCode(Integer intBookCode) {
		this.intBookCode = intBookCode;
	}

	/**
	 * Get the book name
	 * 
	 * @return The book name
	 */
	@Column(name = "chrBookName", length = 45)
	public String getChrBookName() {
		return chrBookName;
	}

	/**
	 * Set the book name
	 * 
	 * @param chrBookName The book name
	 */
	public void setChrBookName(String chrBookName) {
		this.chrBookName = chrBookName;
	}

	/**
	 * Get the year of publication
	 * 
	 * @return The year
	 */
	@Column(name = "chrYear", length = 45)
	public String getChrYear() {
		return chrYear;
	}

	/**
	 * Set the year of publication
	 * 
	 * @param chrYear The year
	 */
	public void setChrYear(String chrYear) {
		this.chrYear = chrYear;
	}
	
	/**
	 * Get the books ISBN
	 * 
	 * @return The ISBN
	 */
	@Column(name = "chrISBN", length = 45)
	public String getChrISBN() {
		return chrISBN;
	}

	/**
	 * Set the books ISBN
	 * 
	 * @param chrISBN The isbn
	 */
	public void setChrISBN(String chrISBN) {
		this.chrISBN = chrISBN;
	}

	/**
	 * Get the research output information
	 * 
	 * @return The research output
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "researchOutputsConferences")
	public Set<ResearchOutputsData1> getResearchOutputsData1s() {
		return researchOutputsData1s;
	}

	/**
	 * Set the  research output information
	 * 
	 * @param researchOutputsData1s The research output
	 */
	public void setResearchOutputsData1s(
			Set<ResearchOutputsData1> researchOutputsData1s) {
		this.researchOutputsData1s = researchOutputsData1s;
	}
}
