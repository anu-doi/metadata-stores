package au.edu.anu.metadatastores.store.grants;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Filter;

import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.ItemAttribute;
import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;

@Entity
@DiscriminatorValue(value="GRANT")
public class GrantItem extends Item {
	private static final long serialVersionUID = 1L;
	
	private List<ItemAttribute> contractCodes = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> grantTitles = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> startDates = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> endDates = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> status = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> firstInvestigatorIds = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> fundsProviders = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> referenceNumbers = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> anzforSubjects = new ArrayList<ItemAttribute>() ;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.CONTRACT_CODE + "'")
	public List<ItemAttribute> getContractCodes() {
		return contractCodes;
	}
	
	public void setContractCodes(List<ItemAttribute> contractCodes) {
		this.contractCodes = contractCodes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.TITLE + "'")
	public List<ItemAttribute> getGrantTitles() {
		return grantTitles;
	}
	
	public void setGrantTitles(List<ItemAttribute> grantTitles) {
		this.grantTitles = grantTitles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.START_DATE + "'")
	public List<ItemAttribute> getStartDates() {
		return startDates;
	}
	
	public void setStartDates(List<ItemAttribute> startDates) {
		this.startDates = startDates;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.END_DATE + "'")
	public List<ItemAttribute> getEndDates() {
		return endDates;
	}
	
	public void setEndDates(List<ItemAttribute> endDates) {
		this.endDates = endDates;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.STATUS + "'")
	public List<ItemAttribute> getStatus() {
		return status;
	}

	public void setStatus(List<ItemAttribute> status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.FIRST_INVESTIGATOR_ID + "'")
	public List<ItemAttribute> getFirstInvestigatorIds() {
		return firstInvestigatorIds;
	}
	
	public void setFirstInvestigatorIds(List<ItemAttribute> firstInvestigatorIds) {
		this.firstInvestigatorIds = firstInvestigatorIds;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.FUNDS_PROVIDER + "'")
	public List<ItemAttribute> getFundsProviders() {
		return fundsProviders;
	}

	public void setFundsProviders(List<ItemAttribute> fundsProviders) {
		this.fundsProviders = fundsProviders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.REFERENCE_NUMBER + "'")
	public List<ItemAttribute> getReferenceNumbers() {
		return referenceNumbers;
	}

	public void setReferenceNumbers(List<ItemAttribute> referenceNumbers) {
		this.referenceNumbers = referenceNumbers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.FOR_SUBJECT + "'")
	public List<ItemAttribute> getAnzforSubjects() {
		return anzforSubjects;
	}

	public void setAnzforSubjects(List<ItemAttribute> anzforSubjects) {
		this.anzforSubjects = anzforSubjects;
	}
}
