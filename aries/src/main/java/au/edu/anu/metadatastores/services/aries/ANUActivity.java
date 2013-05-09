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

/**
 * 
 * <p>ANUActivity<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Interface for ANU activity information i.e. grants</p>
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
public interface ANUActivity {
	/**
	 * Get the activity id
	 * 
	 * @return The activity id
	 */
	public String getActivityId();
	
	/**
	 * Set the activity id
	 * 
	 * @param anuActivityId The activity id
	 */
	public void setActivityId(String anuActivityId);
	
	/**
	 * Get the activity title
	 * 
	 * @return The activity title
	 */
	public String getActivityTitle();
	
	/**
	 * Set the activity title
	 * 
	 * @param title The activity title
	 */
	public void setActivityTitle(String title);
	
	/**
	 * Get the staff id of the first investigator
	 * 
	 * @return The staff id of the first investigator
	 */
	public String getFirstInvestigatorId();
	
	/**
	 * Set the staff id of the first investigator
	 * 
	 * @param id The staff id of the first investigator
	 */
	public void setFirstInvestigatorId(String id);
	
	/**
	 * Get the investigators for this activity
	 * 
	 * @return The investigators involved in this activity
	 */
	public String[] getInvestigators();
	
	/**
	 * Set the investigators for this activity
	 * 
	 * @param investigators The investigators involved in this activity
	 */
	public void setInvestigators(String[] investigators);
	
	/**
	 * Get the activity start date
	 * 
	 * @return The activity start date
	 */
	public String getStartDate();
	
	/**
	 * Set the activity start date
	 * 
	 * @param startDate The activity start date
	 */
	public void setStartDate(String startDate);
	
	/**
	 * Get the activity end date
	 * 
	 * @return The activity end date
	 */
	public String getEndDate();
	
	/**
	 * Set the activity end date
	 * 
	 * @param endDate The end date
	 */
	public void setEndDate(String endDate);
	
	/**
	 * Get the activity status
	 * 
	 * @return The status
	 */
	public String getStatus();
	
	/**
	 * Set the activity status
	 * 
	 * @param status The status
	 */
	public void setStatus(String status);
	
	/**
	 * Get the first field of research subject
	 * 
	 * @return The field of research
	 */
	public Subject getForSubject1();
	
	/**
	 * Set the first field of research subject
	 * 
	 * @param forSubject1 The field of research
	 */
	public void setForSubject1(Subject forSubject1);

	/**
	 * Get the second field of research
	 * 
	 * @return The field of research
	 */
	public Subject getForSubject2();
	
	/**
	 * Set the second field of research
	 * 
	 * @param forSubject2 The field of research
	 */
	public void setForSubject2(Subject forSubject2);
	
	/**
	 * Get the third field of research
	 * 
	 * @return The field of research
	 */
	public Subject getForSubject3();
	
	/**
	 * Set the third field of  research
	 * 
	 * @param forSubject3 The field of research
	 */
	public void setForSubject3(Subject forSubject3);
	
	/**
	 * Get the funds provider
	 * 
	 * @return The funds provider
	 */
	public String getFundsProvider();
	
	/**
	 * Set the funds provider
	 * 
	 * @param fundsProvider The funds provider
	 */
	public void setFundsProvider(String fundsProvider);
	
	/**
	 * Get the funds provider reference number
	 * 
	 * @return The reference number
	 */
	public String getSchemeReference();
	
	/**
	 * Set the funds provider reference number
	 * 
	 * @param schemeReference The reference number
	 */
	public void setSchemeReference(String schemeReference);
}
