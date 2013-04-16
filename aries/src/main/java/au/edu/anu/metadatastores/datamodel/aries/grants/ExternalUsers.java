package au.edu.anu.metadatastores.datamodel.aries.grants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "externalUsers")
public class ExternalUsers {
	private String chrCode;
	private String chrFirstname;
	private String chrSurname;
	private String chrCountry;
	private String chrInstitutionName;
	
	public ExternalUsers() {
		
	}
	
	public ExternalUsers(String chrCode) {
		this.chrCode = chrCode;
	}
	
	public ExternalUsers(String chrCode, String chrFirstname, String chrSurname, String chrCountry, String chrInstitutionName) {
		this.chrCode = chrCode;
		this.chrFirstname = chrFirstname;
		this.chrSurname = chrSurname;
		this.chrCountry = chrCountry;
		this.chrInstitutionName = chrInstitutionName;
	}
	
	@Id
	@Column(name="chrCode", unique = true, nullable = false)
	public String getChrCode() {
		return chrCode;
	}
	
	public void setChrCode(String chrCode) {
		this.chrCode = chrCode;
	}
	
	@Column(name="chrFirstname")
	public String getChrFirstname() {
		return chrFirstname;
	}
	
	public void setChrFirstname(String chrFirstname) {
		this.chrFirstname = chrFirstname;
	}
	
	@Column(name="chrSurname")
	public String getChrSurname() {
		return chrSurname;
	}
	
	public void setChrSurname(String chrSurname) {
		this.chrSurname = chrSurname;
	}
	
	@Column(name="chrCountry")
	public String getChrCountry() {
		return chrCountry;
	}
	
	public void setChrCountry(String chrCountry) {
		this.chrCountry = chrCountry;
	}
	
	@Column(name="chrInstitutionName")
	public String getChrInstitutionName() {
		return chrInstitutionName;
	}
	
	public void setChrInstitutionName(String chrInstitutionName) {
		this.chrInstitutionName = chrInstitutionName;
	}
}
