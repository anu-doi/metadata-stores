package au.edu.anu.metadatastores.services.aries;

public interface ExternalStaff {
	public String getAriesStaffId();
	public void setAriesStaffId(String ariesStaffId);
	
	public String getGivenName();
	public void setGivenName(String givenName);
	
	public String getSurname();
	public void setSurname(String surname);
	
	public String getCountry();
	public void setCountry(String country);
	
	public String getInstitution();
	public void setInstitution(String institution);
}
