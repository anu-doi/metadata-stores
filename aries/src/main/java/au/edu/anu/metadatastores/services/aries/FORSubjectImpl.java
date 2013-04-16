package au.edu.anu.metadatastores.services.aries;

public class FORSubjectImpl implements Subject {
	private String code;
	private String description;
	private String percentage;
	
	public FORSubjectImpl() {
		
	}
	
	public FORSubjectImpl(String code, String description, String percentage) {
		this.code = code;
		this.description = description;
		this.percentage = percentage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

}
