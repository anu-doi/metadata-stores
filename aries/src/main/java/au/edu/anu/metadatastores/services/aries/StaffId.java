/*******************************************************************************
 * Australian National University Metadata Stores
 * Copyright (C) 2013  The Australian National University
 * 
 * This file is part of Australian National University Metadata Stores.
 * 
 * Australian National University Metadata Stores is free software: you
 * can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package au.edu.anu.metadatastores.services.aries;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.aries.grants.ContractsGrantsInvestigators;
import au.edu.anu.metadatastores.datamodel.aries.grants.ContractsGrantsMain;
import au.edu.anu.metadatastores.datamodel.aries.grants.Departments;
import au.edu.anu.metadatastores.datamodel.aries.grants.ExternalUsers;
import au.edu.anu.metadatastores.datamodel.aries.grants.Useraccounts;
import au.edu.anu.metadatastores.datamodel.aries.publications.ResearchOutputsData1;
import au.edu.anu.metadatastores.datamodel.aries.publications.ResearchOutputsDataAuthors;

/**
 * <p>StaffId<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Class to retrieve associated staff information</p>
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
public class StaffId {
	static final Logger LOGGER = LoggerFactory.getLogger(StaffId.class);
	private static StaffId singleton_;
	
	/**
	 * Constructor
	 */
	private StaffId() {
		
	}
	
	/**
	 * Get the StaffId singleton
	 * 
	 * @return The StaffId object
	 */
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
		try {
			Query query = session.createQuery("from ContractsGrantsInvestigators where chrStaffNumber = :staffId");
			query.setParameter("staffId", staffId);
			
			@SuppressWarnings("unchecked")
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
			
			return activityResults.toArray(new ANUActivity[0]);
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Get the publications that the staff member has been involved with
	 * 
	 * @param staffId The staff id to find publications for
	 * @return A list of publications
	 */
	public Publication[] getPublications(String staffId) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		try {
			Query query = session.createQuery("from ResearchOutputsDataAuthors where lower(chrStaffNumber) = :staffId");
			query.setParameter("staffId", staffId);
			
			@SuppressWarnings("unchecked")
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
			
			return publications.toArray(new Publication[0]);
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Get the information about the staff member
	 * 
	 * @param staffId The staff member to get information about
	 * @return The staff members information
	 */
	public ANUStaff getStaffInformation(String staffId) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		try {
			Query query = session.createQuery("from Useraccounts where lower(chrStaffNumber) = :staffId");
			query.setParameter("staffId", staffId.toLowerCase());
			
			Useraccounts userAccount = (Useraccounts)query.uniqueResult();
			
			if (userAccount == null) {
				return null;
			}
			ANUStaff staff = setStaffInformation(userAccount, session);
			
			return staff;
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Get external staff members information
	 * 
	 * @param staffId The id of the person to get staff information from
	 * @return The external staff member information
	 */
	public ExternalStaff getExternalStaffInformation(String staffId) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		try {
			Query query = session.createQuery("from ExternalUsers where lower(chrCode) = :staffId");
			query.setParameter("staffId", staffId);
			
			ExternalUsers externalUser = (ExternalUsers) query.uniqueResult();
			if (externalUser == null) {
				return null;
			}
			ExternalStaff externalStaff = setExternalStaffInformation(externalUser);
			
			return externalStaff;
		}
		finally {
			session.close();
		}
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
		try {
			Query query = session.createQuery("from Useraccounts where lower(chrFirstname) = :firstname and lower(chrSurname) = :surname");
			query.setParameter("firstname", givenName);
			query.setParameter("surname", surname);
	
			@SuppressWarnings("unchecked")
			List<Useraccounts> users = query.list();
			List<ANUStaff> anuStaff = new ArrayList<ANUStaff>();
			ANUStaff staff = null;
			for (Useraccounts user : users) {
				staff = setStaffInformation(user, session);
				anuStaff.add(staff);
			}
			
			return anuStaff.toArray(new ANUStaff[0]);
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Find staff by their surname
	 * 
	 * @param surname The surname of the people to find
	 * @return Staff members with the given surname
	 */
	public ANUStaff[] findStaffBySurname(String surname) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		try {
			Query query = session.createQuery("from Useraccounts where lower(chrSurname) = :surname");
			query.setParameter("surname", surname.toLowerCase());
	
			@SuppressWarnings("unchecked")
			List<Useraccounts> users = query.list();
			List<ANUStaff> anuStaff = new ArrayList<ANUStaff>();
			ANUStaff staff = null;
			for (Useraccounts user : users) {
				staff = setStaffInformation(user, session);
				anuStaff.add(staff);
			}
			
			return anuStaff.toArray(new ANUStaff[0]);
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Find staff by their given name
	 * 
	 * @param givenName The given name of the people to find
	 * @return Staff members with the provided given name
	 */
	public ANUStaff[] findStaffByGivenName(String givenName) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		try {
			Query query = session.createQuery("from Useraccounts where lower(chrFirstname) = :firstname");
			query.setParameter("firstname", givenName.toLowerCase());
	
			@SuppressWarnings("unchecked")
			List<Useraccounts> users = query.list();
			List<ANUStaff> anuStaff = new ArrayList<ANUStaff>();
			ANUStaff staff = null;
			for (Useraccounts user : users) {
				staff = setStaffInformation(user, session);
				anuStaff.add(staff);
			}
			
			return anuStaff.toArray(new ANUStaff[0]);
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Populates the ANUStaff object with the staff information from aries
	 * 
	 * @param userAccount The useraccount
	 * @param session The hibernate session object
	 * @return The ANUStaff information
	 */
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
		
		if (userAccount.getForCodes1() != null) {
			Subject forSubject = new FORSubjectImpl(userAccount.getForCodes1().getChrForObjectiveCode(),userAccount.getForCodes1().getChrForDescription(),userAccount.getChrForpercentage1());
			staff.setForSubject1(forSubject);
		}
		if (userAccount.getForCodes2() != null) {
			Subject forSubject = new FORSubjectImpl(userAccount.getForCodes2().getChrForObjectiveCode(),userAccount.getForCodes2().getChrForDescription(),userAccount.getChrForpercentage2());
			staff.setForSubject2(forSubject);
		}
		if (userAccount.getForCodes3() != null) {
			Subject forSubject = new FORSubjectImpl(userAccount.getForCodes3().getChrForObjectiveCode(),userAccount.getForCodes3().getChrForDescription(),userAccount.getChrForpercentage3());
			staff.setForSubject3(forSubject);
		}
		
		Query departmentQuery = session.createQuery("from Departments where chrTier3Code = :departmentId");
		departmentQuery.setParameter("departmentId", userAccount.getChrDepartmentCode());
		
		Departments departmentRes = (Departments) departmentQuery.uniqueResult();
		if (departmentRes != null) {
			Departments department = (Departments) departmentRes;
			staff.setDepartmentName(department.getId().getChrTier3name());
		}
		
		return staff;
	}
	
	/**
	 * Find external staff by their name
	 * 
	 * @param surname The surname of the person to find
	 * @param givenName The given name of the person to find
	 * @return A list of external staff members who match the provided name
	 */
	public ExternalStaff[] findExternalStaff(String surname, String givenName) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		try {
			List<ExternalStaff> externalStaff = new ArrayList<ExternalStaff>();
			Query query = session.createQuery("from ExternalUsers where lower(chrFirstname) = :firstname and lower(chrSurname) = :surname");
			query.setParameter("firstname", givenName);
			query.setParameter("surname", surname);
	
			@SuppressWarnings("unchecked")
			List<ExternalUsers> users = query.list();
	
			ExternalStaff staff = null;
			for (ExternalUsers user : users) {
				staff = setExternalStaffInformation(user);
				externalStaff.add(staff);
			}
			
			return externalStaff.toArray(new ExternalStaff[0]);
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Find external staff by their surname
	 * 
	 * @param surname The surname to search on
	 * @return The external staff members with the provided surname
	 */
	public ExternalStaff[] findExternalStaffBySurname(String surname) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		try {
			List<ExternalStaff> externalStaff = new ArrayList<ExternalStaff>();
			Query query = session.createQuery("from ExternalUsers where lower(chrSurname) = :surname");
			query.setParameter("surname", surname.toLowerCase());
	
			@SuppressWarnings("unchecked")
			List<ExternalUsers> users = query.list();
	
			ExternalStaff staff = null;
			for (ExternalUsers user : users) {
				staff = setExternalStaffInformation(user);
				externalStaff.add(staff);
			}
			
			return externalStaff.toArray(new ExternalStaff[0]);
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Find external staff by their given name
	 * 
	 * @param givenName The given name to search on
	 * @return The external staff members with the provided given name
	 */
	public ExternalStaff[] findExternalStaffByGivenName(String givenName) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		try {
			List<ExternalStaff> externalStaff = new ArrayList<ExternalStaff>();
			Query query = session.createQuery("from ExternalUsers where lower(chrFirstname) = :firstname");
			query.setParameter("firstname", givenName.toLowerCase());
	
			@SuppressWarnings("unchecked")
			List<ExternalUsers> users = query.list();
	
			ExternalStaff staff = null;
			for (ExternalUsers user : users) {
				staff = setExternalStaffInformation(user);
				externalStaff.add(staff);
			}
			
			return externalStaff.toArray(new ExternalStaff[0]);
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Set the external staff information
	 * 
	 * @param externalUser The external user information
	 * @return The ExternalStaff object with its information populated
	 */
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
