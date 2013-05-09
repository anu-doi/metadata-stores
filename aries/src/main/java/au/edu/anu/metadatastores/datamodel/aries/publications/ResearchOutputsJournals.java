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
 * <p>ResearchOutputsJournals<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Entity class for the <i>research_ouptuts_journals</i> table</p>
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "research_outputs_journals")
public class ResearchOutputsJournals implements java.io.Serializable {

	private Integer intJournalCode;
	private String chrJournalName;
	private String chrYear;
	private String chrISSN;
	private Set<ResearchOutputsData1> researchOutputsData1s = new HashSet(0);

	/**
	 * Constructor
	 */
	public ResearchOutputsJournals() {
	}

	/**
	 * Constructor
	 * 
	 * @param intJournalCode The journal code
	 */
	public ResearchOutputsJournals(Integer intJournalCode) {
		this.intJournalCode = intJournalCode;
	}

	/**
	 * Constructor
	 * 
	 * @param intJournalCode The journal code
	 * @param chrJournalName The journal name
	 * @param chrYear The year of publication
	 * @param researchOutputsData1s The research output information
	 */
	public ResearchOutputsJournals(Integer intJournalCode,
			String chrJournalName, String chrYear, Set<ResearchOutputsData1> researchOutputsData1s) {
		this.intJournalCode = intJournalCode;
		this.chrJournalName = chrJournalName;
		this.chrYear = chrYear;
		this.researchOutputsData1s = researchOutputsData1s;
	}

	/**
	 * Get the journal code
	 * 
	 * @return The journal code
	 */
	@Id
	@Column(name = "intJournalCode", unique = true, nullable = false, length = 45)
	public Integer getIntJournalCode() {
		return this.intJournalCode;
	}

	/**
	 * Set the journal code
	 * 
	 * @param intJournalCode The journal code
	 */
	public void setIntJournalCode(Integer intJournalCode) {
		this.intJournalCode = intJournalCode;
	}

	/**
	 * Get the journal name
	 * 
	 * @return The journal name
	 */
	@Column(name = "chrJournalName", length = 45)
	public String getChrJournalName() {
		return this.chrJournalName;
	}

	/**
	 * Set the journal name
	 * 
	 * @param chrJournalName The journal name
	 */
	public void setChrJournalName(String chrJournalName) {
		this.chrJournalName = chrJournalName;
	}

	/**
	 * Get the publication year
	 * 
	 * @return The publication year
	 */
	@Column(name = "chrYear", length = 45)
	public String getChrYear() {
		return this.chrYear;
	}

	/**
	 * Set the publication year
	 * 
	 * @param chrYear The publication year
	 */
	public void setChrYear(String chrYear) {
		this.chrYear = chrYear;
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
	 * Get the research output information
	 * 
	 * @return The research output information
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "researchOutputsJournals")
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
