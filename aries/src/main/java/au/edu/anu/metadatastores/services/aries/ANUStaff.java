package au.edu.anu.metadatastores.services.aries;

public interface ANUStaff {
	public int getAriesId();
	public void setAriesId(int ariesId);
	
	public String getUniId();
	public void setUniId(String uniId);
	
	public String getTitle();
	public void setTitle(String title);
	
	public String getGivenName();
	public void setGivenName(String givenName);
	
	public String getSurname();
	public void setSurname(String surname);
	
	public String getFax();
	public void setFax(String fax);
	
	public String getPhone();
	public void setPhone(String phone);
	
	public String getEmail();
	public void setEmail(String email);
	
	public String getFORCode1();
	public void setFORCode1(String FORCode);
	
	public String getFORPercentage1();
	public void setFORPercentage1(String percentage);
	
	public String getFORCode2();
	public void setFORCode2(String FORCode);
	
	public String getFORPercentage2();
	public void setFORPercentage2(String percentage);
	
	public String getFORCode3();
	public void setFORCode3(String FORCode);
	
	public String getFORPercentage3();
	public void setFORPercentage3(String percentage);
	
	public String getDepartmentName();
	public void setDepartmentName(String departmentName);
}
