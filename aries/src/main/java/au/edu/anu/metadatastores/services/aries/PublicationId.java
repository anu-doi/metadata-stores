package au.edu.anu.metadatastores.services.aries;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.aries.publications.ResearchOutputsData1;
import au.edu.anu.metadatastores.datamodel.aries.publications.ResearchOutputsDataAuthors;

public class PublicationId {
	static final Logger LOGGER = LoggerFactory.getLogger(PublicationId.class);
	
	private static PublicationId singleton_;
	
	private PublicationId() {
		
	}
	
	public static synchronized PublicationId getSingleton() {
		if (singleton_ == null) {
			singleton_ = new PublicationId();
		}
		return singleton_;
	}
	
	/**
	 * Retrieve all the publication codes from aries
	 * 
	 * @return An array of publication codes
	 */
	public String[] getAllPublicationCodes() {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		List<ResearchOutputsData1> researchOutputs = session.createQuery("from ResearchOutputsData1").list();
		List<String> outputCodes = new ArrayList<String>();
		
		for (ResearchOutputsData1 researchOutput : researchOutputs) {
			if (researchOutput != null) {
				outputCodes.add(researchOutput.getChrOutput6code());
			}
		}
		
		transaction.commit();
		session.flush();
		session.close();
		
		return outputCodes.toArray(new String[0]);
	}
	
	/**
	 * Get a single publication with the given aries publication id
	 * 
	 * @param publicationId The publication id
	 * @return The publication
	 */
	public Publication getSinglePublication(String publicationId) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from ResearchOutputsData1 where chrOutput6code = :publicationId");
		query.setParameter("publicationId", publicationId);
		
		ResearchOutputsData1 researchOutput = (ResearchOutputsData1) query.uniqueResult();
		Publication tempPublication = getPublication(researchOutput);
		
		session.getTransaction().commit();
		session.clear();
		
		return tempPublication;
	}
	
	/**
	 * Retrieve all the publications for a given year
	 * 
	 * @param year The year to retrieve publications for
	 * @return The list of publications
	 */
	public Publication[] getPublicationsForYear(String year) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from ResearchOutputsData1 where chrReportingYear = :year");
		query.setParameter("year", year);
		
		Publication tempPublication = null;
		List<Publication> publications = new ArrayList<Publication>();
		
		List<ResearchOutputsData1> researchOutputs = query.list();
		for (ResearchOutputsData1 output : researchOutputs) {
			tempPublication = getPublication(output);
			publications.add(tempPublication);
		}
		
		session.getTransaction().commit();
		session.clear();
		return publications.toArray(new Publication[0]);
	}
	
	/**
	 * Get the publication from the Research Output Data
	 * 
	 * @param output The publication output
	 * @return The publication
	 */
	public Publication getPublication(ResearchOutputsData1 output) {
		Publication tempPublication = new PublicationImpl();
		
		tempPublication.setAriesId(output.getChrOutput6code());
		
		// It appears that some rows have a number in an appropriate row to fetch data however there is no data associated with that row
		try {
			if (output.getResearchOutputsJournals() != null) {
				tempPublication.setPublicationType("Journal");
				tempPublication.setPublicationName(output.getResearchOutputsJournals().getChrJournalName());
				tempPublication.setISSN(output.getResearchOutputsJournals().getChrISSN());
			}
			else if (output.getResearchOutputsConferences() != null) {
				tempPublication.setPublicationType("Conference");
				tempPublication.setPublicationName(output.getResearchOutputsConferences().getChrConferenceName());
				tempPublication.setISBN(output.getResearchOutputsConferences().getChrISBN());
			}
			else if (output.getResearchOutputsBooks() != null) {
				tempPublication.setPublicationType("Book Chapter");
				tempPublication.setPublicationName(output.getResearchOutputsBooks().getChrBookName());
				tempPublication.setISBN(output.getResearchOutputsBooks().getChrISBN());
			}
		}
		catch(ObjectNotFoundException e) {
			LOGGER.error("Error retrieving either Journal, Conference or Book Information for Record: {}", output.getChrOutput6code());
		}
		tempPublication.setPublicationDate(output.getChrReportingYear());
		tempPublication.setPublicationCategory(output.getResearchOutputsLevel2().getChrOutput2Description());
		List<String> authorsList = new ArrayList<String>();

		for (ResearchOutputsDataAuthors auth : output.getResearchOutputsDataAuthorses()) {
			authorsList.add(auth.getChrStaffNumber());
		}
		tempPublication.setAuthors(authorsList.toArray(new String[0]));
		tempPublication.setPublicationTitle(output.getChrPublicationTitle());
		
		if (output.getForCodes1() != null) {
			Subject forSubject = new FORSubjectImpl(output.getForCodes1().getChrForObjectiveCode(),output.getForCodes1().getChrForDescription(),output.getChrForpercentage1());
			tempPublication.setForSubject1(forSubject);
		}
		if (output.getForCodes2() != null) {
			Subject forSubject = new FORSubjectImpl(output.getForCodes2().getChrForObjectiveCode(),output.getForCodes2().getChrForDescription(),output.getChrForpercentage2());
			tempPublication.setForSubject2(forSubject);
		}
		if (output.getForCodes3() != null) {
			Subject forSubject = new FORSubjectImpl(output.getForCodes3().getChrForObjectiveCode(),output.getForCodes3().getChrForDescription(),output.getChrForpercentage3());
			tempPublication.setForSubject3(forSubject);
		}
		
		return tempPublication;
	}
	
	/**
	 * Retrieve the first authors 
	 * 
	 * @param publicationCodes
	 * @return
	 */
	public String[] getFirstAuthorsUniIDs(String[] publicationCodes) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		//TODO this may need to be updated?
		Query query = session.createQuery("from ResearchOutputsDataAuthors where chrOutputInvestigatorCode like :publicationCode || '%'");
		//Query query = session.createQuery("from ResearchOutputsDataAuthors where chrOutputInvestigatorCode like :publicationCode");
		
		List<ResearchOutputsDataAuthors> outputAuthors = null;
		//String tempCode = null;
		String outputInvestigatorCode = null;
		
		//int index_1 = 0;
		//int index_2 = 0;
		
		List<String> uniIDs = new ArrayList<String>();
		String staffCode = null;
		
		for (String publicationCode : publicationCodes) {
			//TODO figure out how to make this a named parameter that actually returns results...
			query = session.createQuery("from ResearchOutputsDataAuthors where chrOutputInvestigatorCode like '" + publicationCode + "x%' and chrOrder = '01'");
			//query = session.createQuery("from ResearchOutputsDataAuthors where chrOutputInvestigatorCode like :publicationCode and chrOrder = '01'");
			//query.setParameter("publicationCode", publicationCode + "x%");
			//query.ex
			//query.setParameter("publicationCode", publicationCode);
			System.out.println(query.getQueryString());
			outputAuthors = query.list();
			System.out.println("Output Authors Size: " + outputAuthors.size());
			for (ResearchOutputsDataAuthors outputAuthor : outputAuthors) {
				outputInvestigatorCode = outputAuthor.getChrOutputInvestigatorCode();
				if (publicationCode != null && outputInvestigatorCode.indexOf(publicationCode) != -1) {
					staffCode = outputAuthor.getChrStaffNumber();
					uniIDs.add(staffCode);
				}
			}
		}
		
		transaction.commit();
		session.flush();
		session.close();
		
		return uniIDs.toArray(new String[0]);
	}
	
	/**
	 * Retrieve the first authors
	 * 
	 * @param publicationCodes
	 * @return
	 */
	public String[] getFirstAuthors(String[] publicationCodes) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		//Query query = session.createQuery("from ResearchOutputData1 where chrOutput6code like");
		
		
		Query query = session.createQuery("from ResearchOutputsData1 where chrOutput6code in :publicationCodes");
		query.setParameterList("publicationCodes", publicationCodes);
		
		List<ResearchOutputsData1> researchOutputs = query.list();
		
		List<String> firstAuthors = new ArrayList<String>();
		for (ResearchOutputsData1 researchOutput : researchOutputs) {
			if (researchOutput != null && researchOutput.getChrFirstNamedAuthor() != null) {
				System.out.println("chrOutput6code: " + researchOutput.getChrOutput6code());
				firstAuthors.add(researchOutput.getChrFirstNamedAuthor());
			}
		}
		
		transaction.commit();
		session.flush();
		session.close();
		
		return firstAuthors.toArray(new String[0]);
	}
}
