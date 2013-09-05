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
 * 
 * <p>ResearchOutputsConferences<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Entity class for <i>research_outputs_conference</i> table</p>
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "research_outputs_conferences")
public class ResearchOutputsConferences implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer intConferenceCode;
	private String chrConferenceName;
	private String chrYear;
	private String chrISBN;
	private Set<ResearchOutputsData1> researchOutputsData1s = new HashSet<ResearchOutputsData1>(0);

	/**
	 * Constructor
	 */
	public ResearchOutputsConferences() {
	}

	/**
	 * Constructor
	 * 
	 * @param intConferenceCode The conference code
	 */
	public ResearchOutputsConferences(Integer intConferenceCode) {
		this.intConferenceCode = intConferenceCode;
	}

	/**
	 * Constructor
	 * 
	 * @param intConferenceCode THe conference code
	 * @param chrConferenceName The conference name
	 * @param chrYear The conference year
	 * @param researchOutputsData1s The research output information
	 */
	public ResearchOutputsConferences(Integer intConferenceCode,
			String chrConferenceName, String chrYear, Set<ResearchOutputsData1> researchOutputsData1s) {
		this.intConferenceCode = intConferenceCode;
		this.chrConferenceName = chrConferenceName;
		this.chrYear = chrYear;
		this.researchOutputsData1s = researchOutputsData1s;
	}

	/**
	 * Get the conference code
	 * 
	 * @return The conference code
	 */
	@Id
	@Column(name = "intConferenceCode", unique = true, nullable = false, length = 45)
	public Integer getIntConferenceCode() {
		return this.intConferenceCode;
	}

	/**
	 * Set the conference code
	 * 
	 * @param intConferenceCode The conference code
	 */
	public void setIntConferenceCode(Integer intConferenceCode) {
		this.intConferenceCode = intConferenceCode;
	}

	/**
	 * Get the conference name
	 * 
	 * @return The conference name
	 */
	@Column(name = "chrConferenceName", length = 45)
	public String getChrConferenceName() {
		return this.chrConferenceName;
	}

	/**
	 * Set the conference name
	 * 
	 * @param chrConferenceName The conference name
	 */
	public void setChrConferenceName(String chrConferenceName) {
		this.chrConferenceName = chrConferenceName;
	}

	/**
	 * Get the conference year
	 * 
	 * @return The conference year
	 */
	@Column(name = "chrYear", length = 45)
	public String getChrYear() {
		return this.chrYear;
	}

	/**
	 * Set the conference year
	 * 
	 * @param chrYear The conference year
	 */
	public void setChrYear(String chrYear) {
		this.chrYear = chrYear;
	}
	
	/**
	 * Get the conferences publications ISBN
	 * 
	 * @return The isbn
	 */
	@Column(name = "chrISBN", length = 45)
	public String getChrISBN() {
		return chrISBN;
	}

	/**
	 * Set the conferencese publications ISBN
	 * 
	 * @param chrISBN The isbn
	 */
	public void setChrISBN(String chrISBN) {
		this.chrISBN = chrISBN;
	}

	/**
	 * Get the research output information
	 * 
	 * @return The research output information
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "researchOutputsConferences")
	public Set<ResearchOutputsData1> getResearchOutputsData1s() {
		return this.researchOutputsData1s;
	}

	/**
	 * Set the research output information
	 * 
	 * @param researchOutputsData1s The research output information
	 */
	public void setResearchOutputsData1s(Set<ResearchOutputsData1> researchOutputsData1s) {
		this.researchOutputsData1s = researchOutputsData1s;
	}

}
