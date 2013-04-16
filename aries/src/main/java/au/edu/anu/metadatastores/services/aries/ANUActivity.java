package au.edu.anu.metadatastores.services.aries;

public interface ANUActivity {
	public String getActivityId();
	public void setActivityId(String anuActivityId);
	
	public String getActivityTitle();
	public void setActivityTitle(String title);
	
	public String getFirstInvestigatorId();
	public void setFirstInvestigatorId(String id);
	
	public String[] getInvestigators();
	public void setInvestigators(String[] investigators);
	
	public String getStartDate();
	public void setStartDate(String startDate);
	
	public String getEndDate();
	public void setEndDate(String endDate);
	
	public String getStatus();
	public void setStatus(String status);
	
	public Subject getForSubject1();
	public void setForSubject1(Subject forSubject1);

	public Subject getForSubject2();
	public void setForSubject2(Subject forSubject2);
	
	public Subject getForSubject3();
	public void setForSubject3(Subject forSubject3);
	
	public String getFundsProvider();
	public void setFundsProvider(String fundsProvider);
	
	public String getSchemeReference();
	public void setSchemeReference(String schemeReference);
}
