package au.edu.anu.metadatastores.datamodel.aries.grants;

// Generated 10/01/2013 2:39:04 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ContractsGrantsStatus generated by hbm2java
 */
@Entity
@Table(name = "contracts_grants_status")
public class ContractsGrantsStatus implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String chrContractCode;
	private String chrContractStatusCode;
	private String chrStatus;
	private String chrCreatedByName;
	private String chrCreatedByCode;
	private String chrComments;

	public ContractsGrantsStatus() {
	}

	public ContractsGrantsStatus(String chrContractCode) {
		this.chrContractCode = chrContractCode;
	}

	public ContractsGrantsStatus(String chrContractCode,
			String chrContractStatusCode, String chrStatus,
			String chrCreatedByName, String chrCreatedByCode, String chrComments) {
		this.chrContractCode = chrContractCode;
		this.chrContractStatusCode = chrContractStatusCode;
		this.chrStatus = chrStatus;
		this.chrCreatedByName = chrCreatedByName;
		this.chrCreatedByCode = chrCreatedByCode;
		this.chrComments = chrComments;
	}

	@Id
	@Column(name = "chrContractCode", unique = true, nullable = false, length = 45)
	public String getChrContractCode() {
		return this.chrContractCode;
	}

	public void setChrContractCode(String chrContractCode) {
		this.chrContractCode = chrContractCode;
	}

	@Column(name = "chrContractStatusCode", length = 45)
	public String getChrContractStatusCode() {
		return this.chrContractStatusCode;
	}

	public void setChrContractStatusCode(String chrContractStatusCode) {
		this.chrContractStatusCode = chrContractStatusCode;
	}

	@Column(name = "chrStatus", length = 45)
	public String getChrStatus() {
		return this.chrStatus;
	}

	public void setChrStatus(String chrStatus) {
		this.chrStatus = chrStatus;
	}

	@Column(name = "chrCreatedByName", length = 45)
	public String getChrCreatedByName() {
		return this.chrCreatedByName;
	}

	public void setChrCreatedByName(String chrCreatedByName) {
		this.chrCreatedByName = chrCreatedByName;
	}

	@Column(name = "chrCreatedByCode", length = 45)
	public String getChrCreatedByCode() {
		return this.chrCreatedByCode;
	}

	public void setChrCreatedByCode(String chrCreatedByCode) {
		this.chrCreatedByCode = chrCreatedByCode;
	}

	@Column(name = "chrComments", length = 45)
	public String getChrComments() {
		return this.chrComments;
	}

	public void setChrComments(String chrComments) {
		this.chrComments = chrComments;
	}

}
