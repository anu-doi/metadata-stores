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

import org.hibernate.Session;
import org.hibernate.Transaction;

import au.edu.anu.metadatastores.datamodel.aries.publications.ResearchOutputsData1;

/**
 * OutputId
 * 
 * The Australian National University
 * 
 * Class to provide research outputs information
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
/**
 * 
 * <p>OutputId<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Class to provide research outputs information</p>
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
public class OutputId {
	private static OutputId singleton_;
	
	/**
	 * Constructor
	 */
	private OutputId() {
		
	}
	
	/**
	 * Get the OutputId singleton
	 * 
	 * @return The OutputId
	 */
	public static synchronized OutputId getSingleton() {
		if (singleton_ == null) {
			singleton_ = new OutputId();
		}
		return singleton_;
	}
	
	/**
	 * Returns all the output 6 codes found in Aries
	 * 
	 * @return The output 6 codes
	 */
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
