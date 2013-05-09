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

package au.edu.anu.metadatastores.datamodel.aries.grants;

// Generated 10/01/2013 2:39:04 PM by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>ContractsGrantsDepartments</p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Entity class for the <i>contracts_grants_departments</i> table</p>
 * 
 * @author Rainbow Cai
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name = "contracts_grants_departments")
public class ContractsGrantsDepartments implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private ContractsGrantsDepartmentsId id;

	/**
	 * Constructor
	 */
	public ContractsGrantsDepartments() {
	}

	/**
	 * Constructor
	 * 
	 * @param id The contract department id
	 */
	public ContractsGrantsDepartments(ContractsGrantsDepartmentsId id) {
		this.id = id;
	}

	/**
	 * Get the id
	 * 
	 * @return The id
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "chrPrimary", column = @Column(name = "chrPrimary", length = 45)),
			@AttributeOverride(name = "chrDepartmentCode", column = @Column(name = "chrDepartmentCode", length = 45)),
			@AttributeOverride(name = "chrContractDepartmentCode", column = @Column(name = "chrContractDepartmentCode", length = 45)),
			@AttributeOverride(name = "chrContractCode", column = @Column(name = "chrContractCode", length = 45)) })
	public ContractsGrantsDepartmentsId getId() {
		return this.id;
	}

	/**
	 * Set the id
	 * 
	 * @param id The id
	 */
	public void setId(ContractsGrantsDepartmentsId id) {
		this.id = id;
	}

}
