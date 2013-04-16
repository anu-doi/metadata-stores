package au.edu.anu.metadatastores.services.aries;

public class ANUActivityImpl implements ANUActivity {
	private String anuActivityId, firstInvestigatorId;
	private String activityTitle;
	private String[] investigators;
	private String startDate;
	private String endDate;
	private String status;
	private Subject forSubject1;
	private Subject forSubject2;
	private Subject forSubject3;
	private String fundsProvider;
	private String schemeReference;
	
	/**
	 * Get the activity id
	 * 
	 * @return The activity id
	 */
	public String getActivityId() {
		return anuActivityId;
	}

	/**
	 * Set the activity id
	 * 
	 * @param anuActivityId The activity id
	 */
	public void setActivityId(String anuActivityId) {
		this.anuActivityId = anuActivityId;
	}
	
	public String getActivityTitle() {
		return activityTitle;
	}
	
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public String getFirstInvestigatorId() {
		return firstInvestigatorId;
	}
	
	public void setFirstInvestigatorId(String firstInvestigatorId) {
		this.firstInvestigatorId = firstInvestigatorId;
	}

	public String[] getInvestigators() {
		return investigators;
	}

	public void setInvestigators(String[] investigators) {
		this.investigators = investigators;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Subject getForSubject1() {
		return forSubject1;
	}

	public void setForSubject1(Subject forSubject1) {
		this.forSubject1 = forSubject1;
	}

	public Subject getForSubject2() {
		return forSubject2;
	}

	public void setForSubject2(Subject forSubject2) {
		this.forSubject2 = forSubject2;
	}

	public Subject getForSubject3() {
		return forSubject3;
	}

	public void setForSubject3(Subject forSubject3) {
		this.forSubject3 = forSubject3;
	}

	public String getFundsProvider() {
		return fundsProvider;
	}

	public void setFundsProvider(String fundsProvider) {
		this.fundsProvider = fundsProvider;
	}

	public String getSchemeReference() {
		return schemeReference;
	}

	public void setSchemeReference(String schemeReference) {
		this.schemeReference = schemeReference;
	}
}
