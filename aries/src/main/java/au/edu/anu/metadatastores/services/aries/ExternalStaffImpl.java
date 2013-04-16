package au.edu.anu.metadatastores.services.aries;

public class ExternalStaffImpl implements ExternalStaff {
	private String ariesStaffId;
	private String givenName;
	private String surname;
	private String country;
	private String institution;

	public String getAriesStaffId() {
		return ariesStaffId;
	}

	public void setAriesStaffId(String ariesStaffId) {
		this.ariesStaffId = ariesStaffId;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

}
