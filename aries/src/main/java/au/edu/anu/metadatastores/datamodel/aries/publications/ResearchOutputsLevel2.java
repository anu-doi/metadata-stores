package au.edu.anu.metadatastores.datamodel.aries.publications;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "research_outputs_level_2")
public class ResearchOutputsLevel2 implements java.io.Serializable {
	private String chrOutput3Code;
	private String chrOutput2Description;
	private Set<ResearchOutputsData1> researchOutputsData1s = new HashSet<ResearchOutputsData1>(0);

	@Id
	@Column(name = "chrOutput3Code", unique = true, nullable = false, length = 45)
	public String getChrOutput3Code() {
		return chrOutput3Code;
	}
	
	public void setChrOutput3Code(String chrOutput3Code) {
		this.chrOutput3Code = chrOutput3Code;
	}
	
	@Column(name = "chrOutput2Description", length = 45)
	public String getChrOutput2Description() {
		return chrOutput2Description;
	}
	
	public void setChrOutput2Description(String chrOutput2Description) {
		this.chrOutput2Description = chrOutput2Description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "researchOutputsConferences")
	public Set<ResearchOutputsData1> getResearchOutputsData1s() {
		return researchOutputsData1s;
	}

	public void setResearchOutputsData1s(
			Set<ResearchOutputsData1> researchOutputsData1s) {
		this.researchOutputsData1s = researchOutputsData1s;
	}
}
