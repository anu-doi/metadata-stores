package au.edu.anu.metadatastores.services.aries;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.aries.grants.ContractsGrantsInvestigators;
import au.edu.anu.metadatastores.datamodel.aries.grants.ContractsGrantsMain;

public class ContractId {
	static final Logger LOGGER = LoggerFactory.getLogger(ContractId.class);
	
	private static ContractId singleton_;
	
	public static synchronized ContractId getSingleton() {
		if (singleton_ == null) {
			singleton_ = new ContractId();
		}
		return singleton_;
	}
	
	/**
	 * Get the university ids of the investigators for the given contract
	 * 
	 * @param contractId The id of the contract to retrieve university ids for
	 * @return The associated university ids
	 */
	public String[] getInvestigatorUniId(String contractId) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("from ContractsGrantsInvestigators where chrContractCode = :contractId");
		query.setParameter("contractId", contractId);
		List<ContractsGrantsInvestigators> investigators = query.list();
		List<String> staffIds = new ArrayList<String>();
		
		for (ContractsGrantsInvestigators investigator : investigators) {
			if (investigator != null) {
				if (investigator.getChrStaffNumber() != null && !staffIds.contains(investigator.getChrStaffNumber())) {
					staffIds.add(investigator.getChrStaffNumber());
				}
			}
		}
		
		transaction.commit();
		session.flush();
		session.close();
		
		return staffIds.toArray(new String[0]);
	}
	
	//TODO getANUActivityIDsByParty
	//public String[] getANUActivityIDsByParty
	
	/**
	 * Get the author id of the 
	 * @param anuActivityId
	 * @return
	 */
	public String getANUActivityAuthorizeID(String anuActivityId) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("from ContractsGrantsMain where chrContractCode = :activityId");
		query.setParameter("activityId", anuActivityId);
		
		List<ContractsGrantsMain> grants = query.list();
		
		String authorId = null;
		if (grants != null && grants.size() > 0) {
			authorId = grants.get(0).getChrSchemeRef();
		}
		
		transaction.commit();
		session.flush();
		session.close();
		
		return authorId;
	}
	
	public String getANUActivityDesc(String anuActivityId) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("from ContractsGrantsMain where chrContractCode = :activityId");
		query.setParameter("activityId", anuActivityId);
		
		List<ContractsGrantsMain> grants = query.list();
		
		String description = null;
		
		if (grants != null && grants.size() > 0) {
			description = grants.get(0).getChrShortTitle();
		}
		
		transaction.commit();
		session.flush();
		session.close();
		
		return description;
	}
	
	public String[] getAllContractInvestigatorCode() {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		List<ContractsGrantsInvestigators> investigators = session.createQuery("from ContractsGrantsInvestigators").list();
		List<String> codes = new ArrayList<String>();
		
		String investigatorCode = null;
		
		for (ContractsGrantsInvestigators investigator : investigators) {
			if (investigator != null) {
				investigatorCode = investigator.getChrContractInvestigatorCode();
				if (investigatorCode != null && !codes.contains(investigatorCode)) {
					codes.add(investigatorCode);
				}
			}
		}
		
		transaction.commit();
		session.flush();
		session.close();
		
		return codes.toArray(new String[0]);
	}
	
	public ANUActivity[] getActivitiesByUniIDs(String[] uniIDs) {
		ANUActivity[] tempActivities = null;
		List<ANUActivity> allActivities = new ArrayList<ANUActivity>();
		
		for (String uniID : uniIDs) {
			tempActivities = getActivitiesByUniID(uniID);
			for (ANUActivity activity : tempActivities) {
				allActivities.add(activity);
			}
		}
		
		return allActivities.toArray(new ANUActivity[0]);
	}
	
	public ANUActivity[] getActivitiesByUniID(String uniID) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		//Rainbow had first query for some reason.  Must investigate at some point
		//Query query = session.createQuery("from ContractsGrantsInvestigators where chrContractInvestigatorCode like '%' || :uniID || '%'");
		Query query = session.createQuery("from ContractsGrantsInvestigators where chrStaffNumber = :uniID");
		query.setParameter("uniID", uniID);
		List<ContractsGrantsInvestigators> investigators = query.list();
		List<ANUActivity> activities = new ArrayList<ANUActivity>();
		
		ANUActivityImpl tempActivity = null;
		String investigatorCode = null;
		
		for (ContractsGrantsInvestigators investigator : investigators) {
			investigatorCode = investigator.getChrContractInvestigatorCode();
			investigatorCode = investigatorCode.substring(0, investigatorCode.indexOf(uniID));
			if (investigatorCode != null) {
				tempActivity = new ANUActivityImpl();
				tempActivity.setActivityId(investigatorCode);
				//TODO flesh this out
			//	tempActivity.setFirstInvestigatorId(uniID);
				activities.add(tempActivity);
			}
		}
		
		transaction.commit();
		session.flush();
		session.close();
		
		return activities.toArray(new ANUActivity[0]);
	}
}
