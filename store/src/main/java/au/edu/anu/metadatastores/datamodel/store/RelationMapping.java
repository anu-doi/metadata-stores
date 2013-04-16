package au.edu.anu.metadatastores.datamodel.store;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="relation_mapping")
public class RelationMapping {
	private Long id;
	private String code;
	private String description;
	private String reverse;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="code")
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="reverse")
	public String getReverse() {
		return reverse;
	}
	
	public void setReverse(String reverse) {
		this.reverse = reverse;
	}
}
