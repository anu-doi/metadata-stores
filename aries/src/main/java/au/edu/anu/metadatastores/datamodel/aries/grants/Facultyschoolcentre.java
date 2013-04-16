package au.edu.anu.metadatastores.datamodel.aries.grants;

// Generated 10/01/2013 2:39:04 PM by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Facultyschoolcentre generated by hbm2java
 */
@Entity
@Table(name = "facultyschoolcentre")
public class Facultyschoolcentre implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private FacultyschoolcentreId id;

	public Facultyschoolcentre() {
	}

	public Facultyschoolcentre(FacultyschoolcentreId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "chrTierCode", column = @Column(name = "chrTierCode", length = 45)),
			@AttributeOverride(name = "chrTier2name", column = @Column(name = "chrTier2Name", length = 45)),
			@AttributeOverride(name = "chrTier2code", column = @Column(name = "chrTier2Code", length = 45)),
			@AttributeOverride(name = "chrDateStatus", column = @Column(name = "chrDateStatus", length = 45)),
			@AttributeOverride(name = "chrAou", column = @Column(name = "chrAOU", length = 45)),
			@AttributeOverride(name = "chrFinanceCode", column = @Column(name = "chrFinanceCode", length = 45)),
			@AttributeOverride(name = "chrHraou", column = @Column(name = "chrHRAOU", length = 45)) })
	public FacultyschoolcentreId getId() {
		return this.id;
	}

	public void setId(FacultyschoolcentreId id) {
		this.id = id;
	}

}
