package au.edu.anu.metadatastores.services.aries;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import au.edu.anu.metadatastores.datamodel.aries.publications.ResearchOutputsData1;

public class OutputId {
	private static OutputId singleton_;
	
	private OutputId() {
		
	}
	
	public static synchronized OutputId getSingleton() {
		if (singleton_ == null) {
			singleton_ = new OutputId();
		}
		return singleton_;
	}
	
	/*public String[] getInvestigatorsUniID(String contractId) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("from ContractsGrantsInvestigators where chrContractCode = :contractId");
		query.setParameter("contractId", contractId);
		List<ContractsGrantsInvestigators> investigators = query.list();
		List<String> staffIds = new ArrayList<String>();
		
		for (ContractsGrantsInvestigators investigator : investigators) {
			if (investigator != null && investigator.getId().getChrStaffNumber() != null) {
				staffIds.add(investigator.getId().getChrStaffNumber());
			}
		}
		
		transaction.commit();
		session.flush();
		session.close();
		
		return staffIds.toArray(new String[0]);
	}*/
	//TODO implement getANUActivityIDsforByParty
	/*
	public String[] getANUActivityIDsByParty(String name) {
		
	}*/
	
	public String[] getAllOutput6Codes() {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		List<ResearchOutputsData1> researchOutputs = session.createQuery("from ResearchOutputsData1").list();
		
		List<String> outputCodes = new ArrayList<String>();
		
		for (ResearchOutputsData1 researchOutput : researchOutputs) {
			if (researchOutput != null && researchOutput.getChrOutput6code() != null && !outputCodes.contains(researchOutput.getChrOutput6code())) {
				outputCodes.add(researchOutput.getChrOutput6code());
			}
		}
		
		transaction.commit();
		session.flush();
		session.close();
		
		return outputCodes.toArray(new String[0]);
	}
}
