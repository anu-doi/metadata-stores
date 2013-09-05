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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <p>ResearchOutputsDataAuthors<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Entity class for <i>research_outputs_data_authors</i> table</p>
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "research_outputs_data_authors")
public class ResearchOutputsDataAuthors implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String chrOutputInvestigatorCode;
	private ResearchOutputsData1 researchOutputsData1;
	private String chrStatus;
	private String chrRoleCode;
	//GT Added
	private String chrStaffNumber;
	private String chrOrder;

	/**
	 * Constructor
	 */
	public ResearchOutputsDataAuthors() {
	}

	/**
	 * Constructor
	 * 
	 * @param chrOutputInvestigatorCode The investigators code
	 */
	public ResearchOutputsDataAuthors(String chrOutputInvestigatorCode) {
		this.chrOutputInvestigatorCode = chrOutputInvestigatorCode;
	}

	/**
	 * Constructor
	 * 
	 * @param chrOutputInvestigatorCode The investigators code
	 * @param researchOutputsData1 The research output
	 * @param chrStatus 
	 * @param chrRoleCode
	 */
	public ResearchOutputsDataAuthors(String chrOutputInvestigatorCode,
			ResearchOutputsData1 researchOutputsData1, String chrStatus,
			String chrRoleCode) {
		this.chrOutputInvestigatorCode = chrOutputInvestigatorCode;
		this.researchOutputsData1 = researchOutputsData1;
		this.chrStatus = chrStatus;
		this.chrRoleCode = chrRoleCode;
	}

	/**
	 * Get the investigator code
	 * 
	 * @return The investigator code
	 */
	@Id
	@Column(name = "chrOutputInvestigatorCode", unique = true, nullable = false, length = 45)
	public String getChrOutputInvestigatorCode() {
		return this.chrOutputInvestigatorCode;
	}

	/**
	 * Set the investigator code
	 * 
	 * @param chrOutputInvestigatorCode The investigator code
	 */
	public void setChrOutputInvestigatorCode(String chrOutputInvestigatorCode) {
		this.chrOutputInvestigatorCode = chrOutputInvestigatorCode;
	}

	/**
	 * Get the research output information
	 * 
	 * @return The research output information
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chrOutput6Code")
	public ResearchOutputsData1 getResearchOutputsData1() {
		return this.researchOutputsData1;
	}

	/**
	 * Set the research output information
	 * 
	 * @param researchOutputsData1 The research output information
	 */
	public void setResearchOutputsData1(
			ResearchOutputsData1 researchOutputsData1) {
		this.researchOutputsData1 = researchOutputsData1;
	}

	/**
	 * Get the role of the author within ANU. (i.e. Research staff, Undergraduate student, Postgraduate student, Visitor or External)
	 * 
	 * @return The authors role
	 */
	@Column(name = "chrStatus", length = 45)
	public String getChrStatus() {
		return this.chrStatus;
	}

	/**
	 * Set the role of the author within ANU. (i.e. Research staff, Undergraduate student, Postgraduate student, Visitor or External)
	 * 
	 * @param chrStatus The authors role
	 */
	public void setChrStatus(String chrStatus) {
		this.chrStatus = chrStatus;
	}

	/**
	 * Get the code of the role of the author in regards to ANU (i.e. 7 = Staff, 10 = Student, 11 = Visitor, or blank = External)
	 * 
	 * @return The role code
	 */
	@Column(name = "chrRoleCode", length = 45)
	public String getChrRoleCode() {
		return this.chrRoleCode;
	}

	/**
	 * Set the code of the role of the author in regards to ANU (i.e. 7 = Staff, 10 = Student, 11 = Visitor, or blank = External)
	 * 
	 * @param chrRoleCode The role code
	 */
	public void setChrRoleCode(String chrRoleCode) {
		this.chrRoleCode = chrRoleCode;
	}

	//GT added
	/**
	 * Get the staff number (either the university id or the aries external id)
	 * 
	 * @return The staff number
	 */
	@Column(name = "chrStaffNumber", length = 45)
	public String getChrStaffNumber() {
		return this.chrStaffNumber;
	}
	
	/**
	 * Set the staff number (either the university id or the aries external id)
	 * 
	 * @param chrStaffNumber The staff number
	 */
	public void setchrStaffNumber(String chrStaffNumber) {
		this.chrStaffNumber = chrStaffNumber;
	}
	
	/**
	 * Get the order number of the author.  This indicates in which position this author will be placed in citations.
	 * 
	 * @return The order number of the author
	 */
	@Column(name = "chrOrder", length = 45)
	public String getChrOrder() {
		return this.chrOrder;
	}
	
	/**
	 * Set the order number of the author.  This indicates in which position this author will be placed in citations.
	 * 
	 * @param chrOrder The order number of the author
	 */
	public void setChrOrder(String chrOrder) {
		this.chrOrder = chrOrder;
	}
}
