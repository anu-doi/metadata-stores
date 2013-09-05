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

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.aries.publications.ResearchOutputsData1;
import au.edu.anu.metadatastores.datamodel.aries.publications.ResearchOutputsDataAuthors;

/**
 * <p>PublicationId<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Class to retrieve publication information</p>
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
public class PublicationId {
	static final Logger LOGGER = LoggerFactory.getLogger(PublicationId.class);
	
	private static PublicationId singleton_;
	
	/**
	 * Constructor
	 */
	private PublicationId() {
		
	}
	
	/**
	 * Get the PublicationId singleton
	 * 
	 * @return The PublicationId object
	 */
	public static synchronized PublicationId getSingleton() {
		if (singleton_ == null) {
			singleton_ = new PublicationId();
		}
		return singleton_;
	}
	
	/**
	 * Retrieve all the publication codes from Aries
	 * 
	 * @return An array of publication codes
	 */
	public String[] getAllPublicationCodes() {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			List<ResearchOutputsData1> researchOutputs = session.createQuery("from ResearchOutputsData1").list();
			List<String> outputCodes = new ArrayList<String>();
			
			for (ResearchOutputsData1 researchOutput : researchOutputs) {
				if (researchOutput != null) {
					outputCodes.add(researchOutput.getChrOutput6code());
				}
			}
			
			return outputCodes.toArray(new String[0]);
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Get a single publication with the given aries publication id
	 * 
	 * @param publicationId The publication id
	 * @return The publication
	 */
	public Publication getSinglePublication(String publicationId) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		try {
			Query query = session.createQuery("from ResearchOutputsData1 where chrOutput6code = :publicationId");
			query.setParameter("publicationId", publicationId);
			
			ResearchOutputsData1 researchOutput = (ResearchOutputsData1) query.uniqueResult();
			Publication tempPublication = getPublication(researchOutput);
			
			return tempPublication;
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Retrieve all the publications for a given year
	 * 
	 * @param year The year to retrieve publications for
	 * @return The list of publications
	 */
	public Publication[] getPublicationsForYear(String year) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		try {
			Query query = session.createQuery("from ResearchOutputsData1 where chrReportingYear = :year");
			query.setParameter("year", year);
			
			Publication tempPublication = null;
			List<Publication> publications = new ArrayList<Publication>();
			
			@SuppressWarnings("unchecked")
			List<ResearchOutputsData1> researchOutputs = query.list();
			for (ResearchOutputsData1 output : researchOutputs) {
				tempPublication = getPublication(output);
				publications.add(tempPublication);
			}
			
			return publications.toArray(new Publication[0]);
		}
		finally {
			session.close();
		}
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
	 * @param publicationCodes The publication codes
	 * @return The university ids of the first authors
	 */
	public String[] getFirstAuthorsUniIDs(String[] publicationCodes) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();

		try {
			Query query = session.createQuery("from ResearchOutputsDataAuthors where chrOutputInvestigatorCode like :publicationCode and chrOrder = '01'");
			
			List<ResearchOutputsDataAuthors> outputAuthors = null;
			
			String outputInvestigatorCode = null;
			
			List<String> uniIDs = new ArrayList<String>();
			String staffCode = null;
			
			for (String publicationCode : publicationCodes) {
				query.setParameter("publicationCode", publicationCode + "x%");
				
				//@SuppressWarnings("unchecked")
				outputAuthors = query.list();
				for (ResearchOutputsDataAuthors outputAuthor : outputAuthors) {
					outputInvestigatorCode = outputAuthor.getChrOutputInvestigatorCode();
					if (publicationCode != null && outputInvestigatorCode.indexOf(publicationCode) != -1) {
						staffCode = outputAuthor.getChrStaffNumber();
						uniIDs.add(staffCode);
					}
				}
			}
			
			return uniIDs.toArray(new String[0]);
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Retrieve the first authors
	 * 
	 * @param publicationCodes The publication codes
	 * @return The first named authors
	 */
	public String[] getFirstAuthors(String[] publicationCodes) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		try {
			Query query = session.createQuery("from ResearchOutputsData1 where chrOutput6code in :publicationCodes");
			query.setParameterList("publicationCodes", publicationCodes);
			
			@SuppressWarnings("unchecked")
			List<ResearchOutputsData1> researchOutputs = query.list();
			
			List<String> firstAuthors = new ArrayList<String>();
			for (ResearchOutputsData1 researchOutput : researchOutputs) {
				if (researchOutput != null && researchOutput.getChrFirstNamedAuthor() != null) {
					System.out.println("chrOutput6code: " + researchOutput.getChrOutput6code());
					firstAuthors.add(researchOutput.getChrFirstNamedAuthor());
				}
			}
			
			return firstAuthors.toArray(new String[0]);
		}
		finally {
			session.close();
		}
	}
}
