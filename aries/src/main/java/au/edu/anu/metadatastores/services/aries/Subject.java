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
 * <p>Subject<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Interface for subjects</p>
 * 
 * @author Genevieve Turner
 *
 */
public interface Subject {
	/**
	 * Get the subject code
	 * 
	 * @return The code
	 */
	public String getCode();
	
	/**
	 * Set the subject code
	 * 
	 * @param code The code
	 */
	public void setCode(String code);
	
	/**
	 * Get the subject description
	 * 
	 * @return The description
	 */
	public String getDescription();
	
	/**
	 * Set the subject description
	 * 
	 * @param description
	 */
	public void setDescription(String description);
	
	/**
	 * Get the subject research percentage
	 * 
	 * @return The percentage
	 */
	public String getPercentage();
	
	/**
	 * Set the subject research percentage
	 * 
	 * @param percentage The percentage
	 */
	public void setPercentage(String percentage);
}
