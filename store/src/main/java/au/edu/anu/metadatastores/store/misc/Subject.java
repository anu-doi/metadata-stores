package au.edu.anu.metadatastores.store.misc;

import javax.xml.bind.annotation.XmlElement;

public class Subject {
	public static final String CODE = "code";
	public static final String VALUE = "value";
	public static final String PERCENTAGE = "percentage";
	
	private String code_;
	private String value_;
	private String percentage_;
	
	public Subject() {
		
	}
	
	public Subject(String code, String value, String percentage) {
		this.code_ = code;
		this.value_ = value;
		this.percentage_ = percentage;
	}
	
	@XmlElement(name=CODE)
	public String getCode() {
		return code_;
	}
	
	public void setCode(String code) {
		this.code_ = code;
	}
	
	@XmlElement(name=VALUE)
	public String getValue() {
		return value_;
	}
	
	public void setValue(String value) {
		this.value_ = value;
	}
	
	@XmlElement(name=PERCENTAGE)
	public String getPercentage() {
		return percentage_;
	}
	
	public void setPercentage(String percentage) {
		this.percentage_ = percentage;
	}
}
