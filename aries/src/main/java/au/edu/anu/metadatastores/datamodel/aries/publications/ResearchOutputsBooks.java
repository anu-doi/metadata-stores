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
@Table(name = "research_outputs_books")
public class ResearchOutputsBooks {
	private Integer intBookCode;
	private String chrBookName;
	private String chrYear;
	private String chrISBN;
	private Set<ResearchOutputsData1> researchOutputsData1s = new HashSet<ResearchOutputsData1>(0);
	
	public ResearchOutputsBooks() {
	}
	
	public ResearchOutputsBooks(Integer intBookCode) {
		this.intBookCode = intBookCode;
	}

	public ResearchOutputsBooks(Integer intBookCode,
			String chrBookName, String chrYear, Set<ResearchOutputsData1> researchOutputsData1s) {
		this.intBookCode = intBookCode;
		this.chrBookName = chrBookName;
		this.chrYear = chrYear;
		this.researchOutputsData1s = researchOutputsData1s;
	}

	@Id
	@Column(name = "intBookCode", unique = true, nullable = false, length = 45)
	public Integer getIntBookCode() {
		return intBookCode;
	}

	public void setIntBookCode(Integer intBookCode) {
		this.intBookCode = intBookCode;
	}

	@Column(name = "chrBookName", length = 45)
	public String getChrBookName() {
		return chrBookName;
	}

	public void setChrBookName(String chrBookName) {
		this.chrBookName = chrBookName;
	}

	@Column(name = "chrYear", length = 45)
	public String getChrYear() {
		return chrYear;
	}

	public void setChrYear(String chrYear) {
		this.chrYear = chrYear;
	}
	
	@Column(name = "chrISBN", length = 45)
	public String getChrISBN() {
		return chrISBN;
	}

	public void setChrISBN(String chrISBN) {
		this.chrISBN = chrISBN;
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
