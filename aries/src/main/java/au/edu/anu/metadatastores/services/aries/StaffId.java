package au.edu.anu.metadatastores.services.aries;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.aries.grants.ContractsGrantsInvestigators;
import au.edu.anu.metadatastores.datamodel.aries.grants.ContractsGrantsMain;
import au.edu.anu.metadatastores.datamodel.aries.grants.Departments;
import au.edu.anu.metadatastores.datamodel.aries.grants.ExternalUsers;
import au.edu.anu.metadatastores.datamodel.aries.grants.Useraccounts;
import au.edu.anu.metadatastores.datamodel.aries.publications.ResearchOutputsData1;
import au.edu.anu.metadatastores.datamodel.aries.publications.ResearchOutputsDataAuthors;

public class StaffId {
	static final Logger LOGGER = LoggerFactory.getLogger(StaffId.class);
	private static StaffId singleton_;
	
	private StaffId() {
		
	}
	
	public static synchronized StaffId getSingleton() {
		if (singleton_ == null) {
			singleton_ = new StaffId();
		}
		return singleton_;
	}
	
	/**
	 * Get the contracts/grants for the given staff member
	 * 
	 * @param staffId The id of the staff member
	 * @return The activities associated with the staff member
	 */
	public ANUActivity[] getContracts(String staffId) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("from ContractsGrantsInvestigators where chrStaffNumber = :staffId");
		query.setParameter("staffId", staffId);
		
		List<ContractsGrantsInvestigators> results = query.list();
		
		ANUActivityImpl tempActivity = null;
		List<ANUActivity> activityResults = new ArrayList<ANUActivity>();
		
		ContractsGrantsInvestigators investigator = null;
		ContractsGrantsMain contract = null;
		for (int i = 0; i < results.size(); i++) {
			investigator = results.get(i);
			contract = investigator.getContractsGrantsMain();
			
			if (investigator.getChrStaffNumber() != null && investigator.getChrContractInvestigatorCode() != null) {
				tempActivity = new ANUActivityImpl();
				tempActivity.setActivityId(contract.getChrContractCode());
				List<String> invesitgatorIds = new ArrayList<String>();
				for (ContractsGrantsInvestigators inv : contract.getContractsGrantsInvestigators()) {
					invesitgatorIds.add(inv.getChrStaffNumber());
					if ("Yes".equals(inv.getChrPrimary())) {
						tempActivity.setFirstInvestigatorId(inv.getChrStaffNumber());
					}
				}
				tempActivity.setInvestigators(invesitgatorIds.toArray(new String[0]));
				tempActivity.setActivityTitle(contract.getChrShortTitle());
				tempActivity.setStartDate(contract.getChrGrantStartDate());
				tempActivity.setEndDate(contract.getChrCompletionDate());
				tempActivity.setStatus(contract.getChrStatus());
				
				if (contract.getForCodes1() != null) {
					Subject forSubject = new FORSubjectImpl(contract.getForCodes1().getChrForObjectiveCode(),contract.getForCodes1().getChrForDescription(),contract.getChrForpercentage1());
					tempActivity.setForSubject1(forSubject);
				}
				if (contract.getForCodes2() != null) {
					Subject forSubject = new FORSubjectImpl(contract.getForCodes2().getChrForObjectiveCode(),contract.getForCodes2().getChrForDescription(),contract.getChrForpercentage2());
					tempActivity.setForSubject2(forSubject);
				}
				if (contract.getForCodes3() != null) {
					Subject forSubject = new FORSubjectImpl(contract.getForCodes3().getChrForObjectiveCode(),contract.getForCodes3().getChrForDescription(),contract.getChrForpercentage3());
					tempActivity.setForSubject3(forSubject);
				}
				
				tempActivity.setFundsProvider(contract.getChrPrimaryFundsProvider());
				try {
					String schemeRef = new String(contract.getChrSchemeRef().getBytes(), "UTF8");
					//Check for if the schema is null, and if it is a non-breaking space
					//The database character encoding appears to at least sometimes be in Cp1250 (i.e. windows encoding)
					if (schemeRef != null && !"&nbsp;".equals(schemeRef) && (new String(schemeRef.getBytes("Cp1250"), "UTF8").replaceFirst("^[\\xA0]+","").trim().length() > 0)) {
						tempActivity.setSchemeReference(schemeRef);
					}
				}
				catch (UnsupportedEncodingException e) {
					LOGGER.error("Unsupported encoding UTF8");
				}
				
				activityResults.add(tempActivity);
			}
		}
		
		transaction.commit();
		session.flush();
		session.close();
		
		return activityResults.toArray(new ANUActivity[0]);
	}
	
	/**
	 * Get the publications that the staff member has been involved with
	 * 
	 * @param staffId The staff id to find publications for
	 * @return A list of publications
	 */
	public Publication[] getPublications(String staffId) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("from ResearchOutputsDataAuthors where lower(chrStaffNumber) = :staffId");
		query.setParameter("staffId", staffId);
		
		List<ResearchOutputsDataAuthors> authors = query.list();
		
		Vector<String> uniqueOutputs = new Vector<String>();
		
		String outputDataCode ="";
		
		ResearchOutputsData1 outputCode = null;
		PublicationImpl tempPublication = null;
		List<Publication> publications = new ArrayList<Publication>();
		
		for (ResearchOutputsDataAuthors author : authors) {
			outputDataCode = author.getResearchOutputsData1().getChrOutput6code();
			if (!uniqueOutputs.contains(outputDataCode)) {
				outputCode = author.getResearchOutputsData1();
				tempPublication = new PublicationImpl();

				// It appears that some rows have a number in an appropriate row to fetch data however there is no data associated with that row
				try {
					if (outputCode.getResearchOutputsJournals() != null) {
						tempPublication.setPublicationType("Journal");
						tempPublication.setPublicationName(outputCode.getResearchOutputsJournals().getChrJournalName());
						tempPublication.setISSN(outputCode.getResearchOutputsJournals().getChrISSN());
					}
					else if (outputCode.getResearchOutputsConferences() != null) {
						tempPublication.setPublicationType("Conference");
						tempPublication.setPublicationName(outputCode.getResearchOutputsConferences().getChrConferenceName());
						tempPublication.setISBN(outputCode.getResearchOutputsConferences().getChrISBN());
					}
					else if (outputCode.getResearchOutputsBooks() != null) {
						tempPublication.setPublicationType("Book Chapter");
						tempPublication.setPublicationName(outputCode.getResearchOutputsBooks().getChrBookName());
						tempPublication.setISBN(outputCode.getResearchOutputsBooks().getChrISBN());
					}
				}
				catch(ObjectNotFoundException e) {
					LOGGER.error("Error retrieving either Journal, Conference or Book Information for record: {}", outputCode.getChrOutput6code());
				}
				tempPublication.setPublicationDate(outputCode.getChrReportingYear());
				tempPublication.setPublicationCategory(outputCode.getResearchOutputsLevel2().getChrOutput2Description());
				List<String> authorsList = new ArrayList<String>();
				
				for (ResearchOutputsDataAuthors auth : outputCode.getResearchOutputsDataAuthorses()) {
					authorsList.add(auth.getChrStaffNumber());
				}
				tempPublication.setAuthors(authorsList.toArray(new String[0]));
				
				tempPublication.setPublicationTitle(outputCode.getChrPublicationTitle());
				
				if (outputCode.getForCodes1() != null) {
					Subject forSubject = new FORSubjectImpl(outputCode.getForCodes1().getChrForObjectiveCode(),outputCode.getForCodes1().getChrForDescription(),outputCode.getChrForpercentage1());
					tempPublication.setForSubject1(forSubject);
				}
				if (outputCode.getForCodes2() != null) {
					Subject forSubject = new FORSubjectImpl(outputCode.getForCodes2().getChrForObjectiveCode(),outputCode.getForCodes2().getChrForDescription(),outputCode.getChrForpercentage2());
					tempPublication.setForSubject2(forSubject);
				}
				if (outputCode.getForCodes3() != null) {
					Subject forSubject = new FORSubjectImpl(outputCode.getForCodes3().getChrForObjectiveCode(),outputCode.getForCodes3().getChrForDescription(),outputCode.getChrForpercentage3());
					tempPublication.setForSubject3(forSubject);
				}
				
				
				tempPublication.setAriesId(outputCode.getChrOutput6code());
				
				publications.add(tempPublication);
			}
		}
		
		transaction.commit();
		session.flush();
		session.close();
		
		return publications.toArray(new Publication[0]);
	}
	
	/**
	 * Get the information about the staff member
	 * 
	 * @param staffId The staff member to get information about
	 * @return The staff members information
	 */
	public ANUStaff getStaffInformation(String staffId) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("from Useraccounts where lower(chrStaffNumber) = :staffId");
		query.setParameter("staffId", staffId.toLowerCase());
		
		Useraccounts userAccount = (Useraccounts)query.uniqueResult();
		
		if (userAccount == null) {
			return null;
		}
		ANUStaff staff = setStaffInformation(userAccount, session);
		
		transaction.commit();
		session.flush();
		session.close();
		
		return staff;
	}
	
	/**
	 * Get external staff members information
	 * 
	 * @param staffId The id of the person to get staff information from
	 * @return The external staff member information
	 */
	public ExternalStaff getExternalStaffInformation(String staffId) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("from ExternalUsers where lower(chrCode) = :staffId");
		query.setParameter("staffId", staffId);
		
		ExternalUsers externalUser = (ExternalUsers) query.uniqueResult();
		if (externalUser == null) {
			return null;
		}
		ExternalStaff externalStaff = setExternalStaffInformation(externalUser);
		transaction.commit();
		session.close();
		return externalStaff;
	}
	
	/**
	 * Find the staff by a name
	 * 
	 * @param surname The surname of the person to find
	 * @param givenName The given name of the person to find
	 * @return An array of staff
	 */
	public ANUStaff[] findStaff(String surname, String givenName) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		
		Query query = session.createQuery("from Useraccounts where lower(chrFirstname) = :firstname and lower(chrSurname) = :surname");
		query.setParameter("firstname", givenName);
		query.setParameter("surname", surname);
		
		List<Useraccounts> users = query.list();
		List<ANUStaff> anuStaff = new ArrayList<ANUStaff>();
		ANUStaff staff = null;
		for (Useraccounts user : users) {
			staff = setStaffInformation(user, session);
			anuStaff.add(staff);
		}
		
		session.close();
		
		return anuStaff.toArray(new ANUStaff[0]);
	}
	
	public ANUStaff[] findStaffBySurname(String surname) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		
		Query query = session.createQuery("from Useraccounts where lower(chrSurname) = :surname");
		query.setParameter("surname", surname);
		
		List<Useraccounts> users = query.list();
		List<ANUStaff> anuStaff = new ArrayList<ANUStaff>();
		ANUStaff staff = null;
		for (Useraccounts user : users) {
			staff = setStaffInformation(user, session);
			anuStaff.add(staff);
		}
		
		session.close();
		
		return anuStaff.toArray(new ANUStaff[0]);
	}
	
	public ANUStaff[] findStaffByGivenName(String givenName) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		
		Query query = session.createQuery("from Useraccounts where lower(chrFirstname) = :firstname");
		query.setParameter("firstname", givenName);
		
		List<Useraccounts> users = query.list();
		List<ANUStaff> anuStaff = new ArrayList<ANUStaff>();
		ANUStaff staff = null;
		for (Useraccounts user : users) {
			staff = setStaffInformation(user, session);
			anuStaff.add(staff);
		}
		
		session.close();
		
		return anuStaff.toArray(new ANUStaff[0]);
	}
	
	private ANUStaff setStaffInformation(Useraccounts userAccount, Session session) {

		ANUStaff staff = new ANUStaffImpl();
		staff.setAriesId(userAccount.getIntSystemCounter());
		staff.setUniId(userAccount.getChrStaffNumber());
		staff.setTitle(userAccount.getChrTitle());
		staff.setGivenName(userAccount.getChrFirstname());
		staff.setSurname(userAccount.getChrSurname());
		staff.setFax(userAccount.getChrFax());
		staff.setPhone(userAccount.getChrTelephone());
		staff.setEmail(userAccount.getChrEmail());
		staff.setFORCode1(userAccount.getChrForcode1());
		staff.setFORPercentage1(userAccount.getChrForpercentage1());
		staff.setFORCode2(userAccount.getChrForcode2());
		staff.setFORPercentage2(userAccount.getChrForpercentage2());
		staff.setFORCode3(userAccount.getChrForcode3());
		staff.setFORPercentage3(userAccount.getChrForpercentage3());

		Query departmentQuery = session.createQuery("from Departments where chrTier3Code = :departmentId");
		departmentQuery.setParameter("departmentId", userAccount.getChrDepartmentCode());
		
		Departments departmentRes = (Departments) departmentQuery.uniqueResult();
		if (departmentRes != null) {
			Departments department = (Departments) departmentRes;
			staff.setDepartmentName(department.getId().getChrTier3name());
		}
		
		return staff;
	}
	
	public ExternalStaff[] findExternalStaff(String surname, String givenName) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		List<ExternalStaff> externalStaff = new ArrayList<ExternalStaff>();
		Query query = session.createQuery("from ExternalUsers where lower(chrFirstname) = :firstname and lower(chrSurname) = :surname");
		query.setParameter("firstname", givenName);
		query.setParameter("surname", surname);
		
		List<ExternalUsers> users = query.list();

		ExternalStaff staff = null;
		for (ExternalUsers user : users) {
			staff = setExternalStaffInformation(user);
			externalStaff.add(staff);
		}
		
		session.close();
		
		return externalStaff.toArray(new ExternalStaff[0]);
	}
	
	public ExternalStaff[] findExternalStaffBySurname(String surname) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		List<ExternalStaff> externalStaff = new ArrayList<ExternalStaff>();
		Query query = session.createQuery("from ExternalUsers where lower(chrSurname) = :surname");
		query.setParameter("surname", surname);
		
		List<ExternalUsers> users = query.list();

		ExternalStaff staff = null;
		for (ExternalUsers user : users) {
			staff = setExternalStaffInformation(user);
			externalStaff.add(staff);
		}
		
		session.close();
		
		return externalStaff.toArray(new ExternalStaff[0]);
	}
	
	public ExternalStaff[] findExternalStaffByGivenName(String givenName) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		List<ExternalStaff> externalStaff = new ArrayList<ExternalStaff>();
		Query query = session.createQuery("from ExternalUsers where lower(chrFirstname) = :firstname");
		query.setParameter("firstname", givenName);
		
		List<ExternalUsers> users = query.list();

		ExternalStaff staff = null;
		for (ExternalUsers user : users) {
			staff = setExternalStaffInformation(user);
			externalStaff.add(staff);
		}
		
		session.close();
		
		return externalStaff.toArray(new ExternalStaff[0]);
	}
	
	public ExternalStaff setExternalStaffInformation(ExternalUsers externalUser) {
		ExternalStaff externalStaff = new ExternalStaffImpl();
		externalStaff.setAriesStaffId(externalUser.getChrCode());
		externalStaff.setGivenName(externalUser.getChrFirstname());
		externalStaff.setSurname(externalUser.getChrSurname());
		externalStaff.setCountry(externalUser.getChrCountry());
		externalStaff.setInstitution(externalUser.getChrInstitutionName());
		return externalStaff;
	}
}
