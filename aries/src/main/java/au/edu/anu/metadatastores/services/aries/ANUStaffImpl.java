package au.edu.anu.metadatastores.services.aries;

public class ANUStaffImpl implements ANUStaff {
	private int ariesId;
	private String uniId;
	private String title;
	private String givenName;
	private String surname;
	private String fax;
	private String phone;
	private String email;
	private String forCode1;
	private String forCode1Percent;
	private String forCode2;
	private String forCode2Percent;
	private String forCode3;
	private String forCode3Percent;
	private String departmentName;
	

	public int getAriesId() {
		return ariesId;
	}

	public void setAriesId(int ariesId) {
		this.ariesId = ariesId;
	}

	public String getUniId() {
		return uniId;
	}

	public void setUniId(String uniId) {
		this.uniId = uniId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	/*
	private String forCode1;
	private String forCode1Percent;
	private String forCode2;
	private String forCode2Percent;
	private String forCode3;
	private String forCode3Percent;
	 */

	public String getFORCode1() {
		return forCode1;
	}

	public void setFORCode1(String FORCode) {
		this.forCode1 = FORCode;
	}

	public String getFORPercentage1() {
		return forCode1Percent;
	}

	public void setFORPercentage1(String percentage) {
		this.forCode1Percent = percentage;
	}

	public String getFORCode2() {
		return forCode2;
	}

	public void setFORCode2(String FORCode) {
		this.forCode2 = FORCode;
	}

	public String getFORPercentage2() {
		return forCode2Percent;
	}

	public void setFORPercentage2(String percentage) {
		this.forCode2Percent = percentage;
	}

	public String getFORCode3() {
		return forCode3;
	}

	public void setFORCode3(String FORCode) {
		this.forCode3 = FORCode;
	}

	public String getFORPercentage3() {
		return forCode3Percent;
	}

	public void setFORPercentage3(String percentage) {
		this.forCode3Percent = percentage;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
