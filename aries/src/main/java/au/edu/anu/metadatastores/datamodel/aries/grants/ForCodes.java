package au.edu.anu.metadatastores.datamodel.aries.grants;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FOR_codes")
public class ForCodes {
	private String chrForDivisionCode;
	private String chrForGroupCode;
	private String chrForObjectiveCode;
	private String chrForDescription;
	
	public String getChrForDivisionCode() {
		return chrForDivisionCode;
	}
	
	public void setChrForDivisionCode(String chrForDivisionCode) {
		this.chrForDivisionCode = chrForDivisionCode;
	}
	
	public String getChrForGroupCode() {
		return chrForGroupCode;
	}
	
	public void setChrForGroupCode(String chrForGroupCode) {
		this.chrForGroupCode = chrForGroupCode;
	}
	
	@Id
	public String getChrForObjectiveCode() {
		return chrForObjectiveCode;
	}
	
	public void setChrForObjectiveCode(String chrForObjectiveCode) {
		this.chrForObjectiveCode = chrForObjectiveCode;
	}
	
	public String getChrForDescription() {
		return chrForDescription;
	}
	
	public void setChrForDescription(String chrForDescription) {
		this.chrForDescription = chrForDescription;
	}
	
	
}
