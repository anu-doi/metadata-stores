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

// Generated 10/01/2013 4:27:05 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import au.edu.anu.metadatastores.datamodel.aries.grants.ForCodes;

/**
 * ResearchOutputsData1
 * 
 * The Australian National University
 * 
 * Entity class for the research_outputs_data1 table
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "research_outputs_data1")
public class ResearchOutputsData1 implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String chrOutput6code;
	private ResearchOutputsConferences researchOutputsConferences;
	private ResearchOutputsJournals researchOutputsJournals;
	private ResearchOutputsBooks researchOutputsBooks;
	private ResearchOutputsLevel2 researchOutputsLevel2;
	private String chrPublicationTitle;
	private String chrReportingYear;
	private String chrFirstNamedAuthor;
	private ForCodes forCodes1;
	private String chrForpercentage1;
	private ForCodes forCodes2;
	private String chrForpercentage2;
	private ForCodes forCodes3;
	private String chrForpercentage3;
	private String chrISSN;
	private String chrISBN;
	private Set<ResearchOutputsDataAuthors> researchOutputsDataAuthorses = new HashSet<ResearchOutputsDataAuthors>(0);

	/**
	 * Constructor
	 */
	public ResearchOutputsData1() {
	}

	/**
	 * Constructor
	 * 
	 * @param chrOutput6code The output 6 code
	 */
	public ResearchOutputsData1(String chrOutput6code) {
		this.chrOutput6code = chrOutput6code;
	}

	/**
	 * Constructor
	 * 
	 * @param chrOutput6code The output 6 code
	 * @param researchOutputsConferences The output conference
	 * @param researchOutputsJournals The output journal
	 * @param chrPublicationTitle The publication title
	 * @param chrFirstNamedAuthor The first named author id
	 * @param researchOutputsDataAuthorses The authors of the publication
	 */
	public ResearchOutputsData1(String chrOutput6code,
			ResearchOutputsConferences researchOutputsConferences,
			ResearchOutputsJournals researchOutputsJournals,
			String chrPublicationTitle, String chrFirstNamedAuthor,
			Set<ResearchOutputsDataAuthors> researchOutputsDataAuthorses) {
		this.chrOutput6code = chrOutput6code;
		this.researchOutputsConferences = researchOutputsConferences;
		this.researchOutputsJournals = researchOutputsJournals;
		this.chrPublicationTitle = chrPublicationTitle;
		this.chrFirstNamedAuthor = chrFirstNamedAuthor;
		this.researchOutputsDataAuthorses = researchOutputsDataAuthorses;
	}

	/**
	 * Get the output 6 code
	 * 
	 * @return The output 6 code
	 */
	@Id
	@Column(name = "chrOutput6Code", unique = true, nullable = false, length = 45)
	public String getChrOutput6code() {
		return this.chrOutput6code;
	}

	/**
	 * Set the output 6 code
	 * 
	 * @param chrOutput6code The output 6 code
	 */
	public void setChrOutput6code(String chrOutput6code) {
		this.chrOutput6code = chrOutput6code;
	}

	/**
	 * Get the year of the publication
	 * 
	 * @return The publication year
	 */
	@Column(name="chrReportingYear", length = 45)
	public String getChrReportingYear() {
		return chrReportingYear;
	}

	/**
	 * Set the year of publication
	 * 
	 * @param chrReportingYear The publication year
	 */
	public void setChrReportingYear(String chrReportingYear) {
		this.chrReportingYear = chrReportingYear;
	}

	/**
	 * Get the output conference
	 * 
	 * @return The conference
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "intConferenceCode")
	public ResearchOutputsConferences getResearchOutputsConferences() {
		return this.researchOutputsConferences;
	}

	/**
	 * Set the output conference
	 * 
	 * @param researchOutputsConferences The output conference
	 */
	public void setResearchOutputsConferences(
			ResearchOutputsConferences researchOutputsConferences) {
		this.researchOutputsConferences = researchOutputsConferences;
	}

	/**
	 * Get the output journal
	 * 
	 * @return The output journal
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "intJournalCode")
	public ResearchOutputsJournals getResearchOutputsJournals() {
		return this.researchOutputsJournals;
	}

	/**
	 * Set the output journal
	 * 
	 * @param researchOutputsJournals Set the output journal
	 */
	public void setResearchOutputsJournals(
			ResearchOutputsJournals researchOutputsJournals) {
		this.researchOutputsJournals = researchOutputsJournals;
	}

	/**
	 * Get the output book
	 * 
	 * @return The output book
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "intBookCode")
	public ResearchOutputsBooks getResearchOutputsBooks() {
		return this.researchOutputsBooks;
	}

	/**
	 * Set the output book
	 * 
	 * @param researchOutputsBooks The output book
	 */
	public void setResearchOutputsBooks(ResearchOutputsBooks researchOutputsBooks) {
		this.researchOutputsBooks = researchOutputsBooks;
	}

	/**
	 * Get the publication category
	 * 
	 * @return The publication category
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chrOutput3Code")
	public ResearchOutputsLevel2 getResearchOutputsLevel2() {
		return this.researchOutputsLevel2;
	}

	/**
	 * Set the publication category
	 * 
	 * @param researchOutputsLevel2 The publication category
	 */
	public void setResearchOutputsLevel2(ResearchOutputsLevel2 researchOutputsLevel2) {
		this.researchOutputsLevel2 = researchOutputsLevel2;
	}

	/**
	 * Get the publication title
	 * 
	 * @return The publication title
	 */
	@Column(name = "chrPublicationTitle", length = 45)
	public String getChrPublicationTitle() {
		return this.chrPublicationTitle;
	}

	/**
	 * Set the publication title
	 * 
	 * @param chrPublicationTitle The publication title
	 */
	public void setChrPublicationTitle(String chrPublicationTitle) {
		this.chrPublicationTitle = chrPublicationTitle;
	}

	/**
	 * Get the first named author
	 * 
	 * @return The first named author
	 */
	@Column(name = "chrFirstNamedAuthor", length = 45)
	public String getChrFirstNamedAuthor() {
		return this.chrFirstNamedAuthor;
	}

	/**
	 * Set the first named author
	 * 
	 * @param chrFirstNamedAuthor The first naemd author
	 */
	public void setChrFirstNamedAuthor(String chrFirstNamedAuthor) {
		this.chrFirstNamedAuthor = chrFirstNamedAuthor;
	}

	/**
	 * Get the first field of research percentage
	 * 
	 * @return The percentage
	 */
	@Column(name = "chrFORpercentage1", length = 45)
	public String getChrForpercentage1() {
		return this.chrForpercentage1;
	}

	/**
	 * Set the first field of research percentage
	 * 
	 * @param chrForpercentage1 The percentage
	 */
	public void setChrForpercentage1(String chrForpercentage1) {
		this.chrForpercentage1 = chrForpercentage1;
	}

	/**
	 * Get the second field of research percentage
	 * 
	 * @return The percentage
	 */
	@Column(name = "chrFORpercentage2", length = 45)
	public String getChrForpercentage2() {
		return this.chrForpercentage2;
	}

	/**
	 * Set the second field of research percentage
	 * 
	 * @param chrForpercentage2 The percentage
	 */
	public void setChrForpercentage2(String chrForpercentage2) {
		this.chrForpercentage2 = chrForpercentage2;
	}

	/**
	 * Get the third field of research percentage
	 * 
	 * @return The percentage
	 */
	@Column(name = "chrFORpercentage3", length = 45)
	public String getChrForpercentage3() {
		return this.chrForpercentage3;
	}

	/**
	 * Set the third field of research percentage
	 * 
	 * @param chrForpercentage3 The percentage
	 */
	public void setChrForpercentage3(String chrForpercentage3) {
		this.chrForpercentage3 = chrForpercentage3;
	}

	/**
	 * Get the ISSN
	 * 
	 * @return The ISSN
	 */
	@Column(name = "chrISSN", length = 45)
	public String getChrISSN() {
		return chrISSN;
	}

	/**
	 * Set the ISSN
	 * 
	 * @param chrISSN The ISSN
	 */
	public void setChrISSN(String chrISSN) {
		this.chrISSN = chrISSN;
	}

	/**
	 * Get the ISBN
	 * 
	 * @return The ISBN
	 */
	@Column(name = "chrISBN", length = 45)
	public String getChrISBN() {
		return chrISBN;
	}

	/**
	 * Set the ISBN
	 * 
	 * @param chrISBN The ISBN
	 */
	public void setChrISBN(String chrISBN) {
		this.chrISBN = chrISBN;
	}

	/**
	 * Get the output authors
	 * 
	 * @return The output authors
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "researchOutputsData1")
	public Set<ResearchOutputsDataAuthors> getResearchOutputsDataAuthorses() {
		return this.researchOutputsDataAuthorses;
	}

	/**
	 * Set the output authors
	 * 
	 * @param researchOutputsDataAuthorses The output authors
	 */
	public void setResearchOutputsDataAuthorses(Set<ResearchOutputsDataAuthors> researchOutputsDataAuthorses) {
		this.researchOutputsDataAuthorses = researchOutputsDataAuthorses;
	}

	/**
	 * Get the first field of research
	 * 
	 * @return The field of research
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chrFORcode1")
	@NotFound(action=NotFoundAction.IGNORE)
	public ForCodes getForCodes1() {
		return forCodes1;
	}

	/**
	 * Set the first field of research
	 * 
	 * @param forCodes1 The field of research
	 */
	public void setForCodes1(ForCodes forCodes1) {
		this.forCodes1 = forCodes1;
	}

	/**
	 * Get the second field of research
	 * 
	 * @return The field of research
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chrFORcode2")
	@NotFound(action=NotFoundAction.IGNORE)
	public ForCodes getForCodes2() {
		return forCodes2;
	}

	/**
	 * Set the second field of research
	 * 
	 * @param forCodes2 The field of research
	 */
	public void setForCodes2(ForCodes forCodes2) {
		this.forCodes2 = forCodes2;
	}

	/**
	 * Get the third field of research
	 * 
	 * @return The field of research
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chrFORcode3")
	@NotFound(action=NotFoundAction.IGNORE)
	public ForCodes getForCodes3() {
		return forCodes3;
	}

	/**
	 * SEt the third field of research
	 * 
	 * @param forCodes3 The field of research
	 */
	public void setForCodes3(ForCodes forCodes3) {
		this.forCodes3 = forCodes3;
	}

}
