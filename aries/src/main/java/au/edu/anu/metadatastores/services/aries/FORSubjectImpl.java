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
 * <p>FORSubjectImpl<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Field of research class</p>
 * 
 * @author Genevieve Turner
 *
 */
public class FORSubjectImpl implements Subject {
	private String code;
	private String description;
	private String percentage;
	
	/**
	 * Constructor
	 */
	public FORSubjectImpl() {
		
	}
	
	/**
	 * Constructor
	 * 
	 * @param code Field of research code
	 * @param description Field of research description
	 * @param percentage Percentage of the research this field of research has been used
	 */
	public FORSubjectImpl(String code, String description, String percentage) {
		this.code = code;
		this.description = description;
		this.percentage = percentage;
	}

	/**
	 * Get the field of research code
	 * 
	 * @return The code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Set the field of research code
	 * 
	 * @param code The code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Get the field of research description
	 * 
	 * @return The description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the field of research description
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the field of research, research percentage
	 * 
	 * @return The percentage
	 */
	public String getPercentage() {
		return percentage;
	}

	/**
	 * Set the field of research, research percentage
	 * 
	 * @param percentage The percentage
	 */
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
}
