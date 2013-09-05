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
 * <p>ResearchOutputsLevel2<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Entity class for the <i>research_outputs_level_2</i> table</p>
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "research_outputs_level_2")
public class ResearchOutputsLevel2 implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String chrOutput3Code;
	private String chrOutput2Description;
	private Set<ResearchOutputsData1> researchOutputsData1s = new HashSet<ResearchOutputsData1>(0);

	/**
	 * Get the publication category code
	 * 
	 * @return The category code
	 */
	@Id
	@Column(name = "chrOutput3Code", unique = true, nullable = false, length = 45)
	public String getChrOutput3Code() {
		return chrOutput3Code;
	}
	
	/**
	 * Set the publication category code
	 * 
	 * @param chrOutput3Code The category code
	 */
	public void setChrOutput3Code(String chrOutput3Code) {
		this.chrOutput3Code = chrOutput3Code;
	}
	
	/**
	 * Get the category description
	 * 
	 * @return The category description
	 */
	@Column(name = "chrOutput2Description", length = 45)
	public String getChrOutput2Description() {
		return chrOutput2Description;
	}
	
	/**
	 * Set the category descritpion
	 * 
	 * @param chrOutput2Description The category description
	 */
	public void setChrOutput2Description(String chrOutput2Description) {
		this.chrOutput2Description = chrOutput2Description;
	}

	/**
	 * Get the research output information
	 * 
	 * @return the research output information
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "researchOutputsConferences")
	public Set<ResearchOutputsData1> getResearchOutputsData1s() {
		return researchOutputsData1s;
	}

	/**
	 * Set the research output information
	 * 
	 * @param researchOutputsData1s The research output information
	 */
	public void setResearchOutputsData1s(
			Set<ResearchOutputsData1> researchOutputsData1s) {
		this.researchOutputsData1s = researchOutputsData1s;
	}
}
