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
 * <p>ANUActivityImpl<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Implementation class for anu activity information</p>
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
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

	/**
	 * Get the activity title
	 * 
	 * @return The activity title
	 */
	public String getActivityTitle() {
		return activityTitle;
	}

	/**
	 * Set the activity title
	 * 
	 * @param activityTitle The activity title
	 */
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	/**
	 * Get the staff id of the first investigator
	 * 
	 * @return The staff id of the first investigator
	 */
	public String getFirstInvestigatorId() {
		return firstInvestigatorId;
	}

	/**
	 * Set the staff id of the first investigator
	 * 
	 * @param firstInvestigatorId The staff id of the first investigator
	 */
	public void setFirstInvestigatorId(String firstInvestigatorId) {
		this.firstInvestigatorId = firstInvestigatorId;
	}

	/**
	 * Get the investigators for this activity
	 * 
	 * @return The investigators involved in this activity
	 */
	public String[] getInvestigators() {
		return investigators;
	}

	/**
	 * Set the investigators for this activity
	 * 
	 * @param investigators The investigators involved in this activity
	 */
	public void setInvestigators(String[] investigators) {
		this.investigators = investigators;
	}

	/**
	 * Get the activity start date
	 * 
	 * @return The activity start date
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * Set the activity start date
	 * 
	 * @param startDate The activity start date
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * Get the activity end date
	 * 
	 * @return The activity end date
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * Set the activity end date
	 * 
	 * @param endDate The end date
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * Get the activity status
	 * 
	 * @return The status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Set the activity status
	 * 
	 * @param status The status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Get the first field of research subject
	 * 
	 * @return The field of research
	 */
	public Subject getForSubject1() {
		return forSubject1;
	}

	/**
	 * Set the first field of research subject
	 * 
	 * @param forSubject1 The field of research
	 */
	public void setForSubject1(Subject forSubject1) {
		this.forSubject1 = forSubject1;
	}

	/**
	 * Get the second field of research
	 * 
	 * @return The field of research
	 */
	public Subject getForSubject2() {
		return forSubject2;
	}

	/**
	 * Set the second field of research
	 * 
	 * @param forSubject2 The field of research
	 */
	public void setForSubject2(Subject forSubject2) {
		this.forSubject2 = forSubject2;
	}

	/**
	 * Get the third field of research
	 * 
	 * @return The field of research
	 */
	public Subject getForSubject3() {
		return forSubject3;
	}

	/**
	 * Set the third field of  research
	 * 
	 * @param forSubject3 The field of research
	 */
	public void setForSubject3(Subject forSubject3) {
		this.forSubject3 = forSubject3;
	}

	/**
	 * Get the funds provider
	 * 
	 * @return The funds provider
	 */
	public String getFundsProvider() {
		return fundsProvider;
	}

	/**
	 * Set the funds provider
	 * 
	 * @param fundsProvider The funds provider
	 */
	public void setFundsProvider(String fundsProvider) {
		this.fundsProvider = fundsProvider;
	}

	/**
	 * Get the funds provider reference number
	 * 
	 * @return The reference number
	 */
	public String getSchemeReference() {
		return schemeReference;
	}

	/**
	 * Set the funds provider reference number
	 * 
	 * @param schemeReference The reference number
	 */
	public void setSchemeReference(String schemeReference) {
		this.schemeReference = schemeReference;
	}
}
