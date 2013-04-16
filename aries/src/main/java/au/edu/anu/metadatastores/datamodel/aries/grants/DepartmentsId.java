package au.edu.anu.metadatastores.datamodel.aries.grants;

// Generated 10/01/2013 2:39:04 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DepartmentsId generated by hbm2java
 */
@Embeddable
public class DepartmentsId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String chrTier3name;
	private String chrTier1code;
	private String chrTier2code;
	private String chrTier3code;
	private String chrCollegeCode;

	public DepartmentsId() {
	}

	public DepartmentsId(String chrTier3name, String chrTier1code,
			String chrTier2code, String chrTier3code, String chrCollegeCode) {
		this.chrTier3name = chrTier3name;
		this.chrTier1code = chrTier1code;
		this.chrTier2code = chrTier2code;
		this.chrTier3code = chrTier3code;
		this.chrCollegeCode = chrCollegeCode;
	}

	@Column(name = "chrTier3Name", nullable = false, length = 55)
	public String getChrTier3name() {
		return this.chrTier3name;
	}

	public void setChrTier3name(String chrTier3name) {
		this.chrTier3name = chrTier3name;
	}

	@Column(name = "chrTier1Code", nullable = false, length = 45)
	public String getChrTier1code() {
		return this.chrTier1code;
	}

	public void setChrTier1code(String chrTier1code) {
		this.chrTier1code = chrTier1code;
	}

	@Column(name = "chrTier2Code", nullable = false, length = 55)
	public String getChrTier2code() {
		return this.chrTier2code;
	}

	public void setChrTier2code(String chrTier2code) {
		this.chrTier2code = chrTier2code;
	}

	@Column(name = "chrTier3Code", nullable = false, length = 45)
	public String getChrTier3code() {
		return this.chrTier3code;
	}

	public void setChrTier3code(String chrTier3code) {
		this.chrTier3code = chrTier3code;
	}

	@Column(name = "chrCollegeCode", nullable = false, length = 45)
	public String getChrCollegeCode() {
		return this.chrCollegeCode;
	}

	public void setChrCollegeCode(String chrCollegeCode) {
		this.chrCollegeCode = chrCollegeCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DepartmentsId))
			return false;
		DepartmentsId castOther = (DepartmentsId) other;

		return ((this.getChrTier3name() == castOther.getChrTier3name()) || (this
				.getChrTier3name() != null
				&& castOther.getChrTier3name() != null && this
				.getChrTier3name().equals(castOther.getChrTier3name())))
				&& ((this.getChrTier1code() == castOther.getChrTier1code()) || (this
						.getChrTier1code() != null
						&& castOther.getChrTier1code() != null && this
						.getChrTier1code().equals(castOther.getChrTier1code())))
				&& ((this.getChrTier2code() == castOther.getChrTier2code()) || (this
						.getChrTier2code() != null
						&& castOther.getChrTier2code() != null && this
						.getChrTier2code().equals(castOther.getChrTier2code())))
				&& ((this.getChrTier3code() == castOther.getChrTier3code()) || (this
						.getChrTier3code() != null
						&& castOther.getChrTier3code() != null && this
						.getChrTier3code().equals(castOther.getChrTier3code())))
				&& ((this.getChrCollegeCode() == castOther.getChrCollegeCode()) || (this
						.getChrCollegeCode() != null
						&& castOther.getChrCollegeCode() != null && this
						.getChrCollegeCode().equals(
								castOther.getChrCollegeCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getChrTier3name() == null ? 0 : this.getChrTier3name()
						.hashCode());
		result = 37
				* result
				+ (getChrTier1code() == null ? 0 : this.getChrTier1code()
						.hashCode());
		result = 37
				* result
				+ (getChrTier2code() == null ? 0 : this.getChrTier2code()
						.hashCode());
		result = 37
				* result
				+ (getChrTier3code() == null ? 0 : this.getChrTier3code()
						.hashCode());
		result = 37
				* result
				+ (getChrCollegeCode() == null ? 0 : this.getChrCollegeCode()
						.hashCode());
		return result;
	}

}
