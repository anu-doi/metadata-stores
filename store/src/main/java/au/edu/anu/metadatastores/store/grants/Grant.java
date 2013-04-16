package au.edu.anu.metadatastores.store.grants;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import au.edu.anu.metadatastores.store.misc.Subject;
import au.edu.anu.metadatastores.store.people.Person;

@XmlRootElement(name="grant")
public class Grant {
	private String contractCode;
	private String title;
	private Person firstInvestigator;
	private List<Person> associatedPeople = new ArrayList<Person>();
	private String startDate;
	private String endDate;
	private String status;
	private String fundsProvider;
	private String referenceNumber;
	private List<Subject> anzforSubjects = new ArrayList<Subject>();
	
	@XmlElement(name="contract-code")
	public String getContractCode() {
		return contractCode;
	}
	
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	
	@XmlElement(name="title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@XmlElement(name="first-investigator")
	public Person getFirstInvestigator() {
		return firstInvestigator;
	}

	public void setFirstInvestigator(Person firstInvestigator) {
		this.firstInvestigator = firstInvestigator;
	}

	@XmlElement(name="associate")
	public List<Person> getAssociatedPeople() {
		return associatedPeople;
	}
	
	public void setAssociatedPeople(List<Person> associatedPeople) {
		this.associatedPeople = associatedPeople;
	}
	
	@XmlElement(name="start-date")
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	@XmlElement(name="end-date")
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@XmlElement(name="status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@XmlElement(name="funds-provider")
	public String getFundsProvider() {
		return fundsProvider;
	}

	public void setFundsProvider(String fundsProvider) {
		this.fundsProvider = fundsProvider;
	}

	@XmlElement(name="reference-number")
	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	@XmlElement(name="for-subject")
	public List<Subject> getAnzforSubjects() {
		return anzforSubjects;
	}

	public void setAnzforSubjects(List<Subject> anzforSubjects) {
		this.anzforSubjects = anzforSubjects;
	}
}
