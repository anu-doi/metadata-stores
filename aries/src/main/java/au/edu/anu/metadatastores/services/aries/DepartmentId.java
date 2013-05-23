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

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import au.edu.anu.metadatastores.datamodel.aries.grants.ContractsGrantsDepartments;
import au.edu.anu.metadatastores.datamodel.aries.grants.Departments;

/**
 * 
 * <p>DepartmentId<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Retrieves information about departments</p>
 * 
 * @author Genevieve Turner
 *
 */
public class DepartmentId {
	private static DepartmentId singleton_;
	
	/**
	 * Constructor
	 */
	private DepartmentId() {
		
	}
	
	/**
	 * Get a singleton of DepartmentId
	 * 
	 * @return The DepartmentId instance
	 */
	public static synchronized DepartmentId getSingleton() {
		if (singleton_ == null) {
			singleton_ = new DepartmentId();
		}
		return singleton_;
	}
	
	/**
	 * Get a list of department ids
	 * 
	 * @return The department ids
	 */
	public String[] getDepartmentIDs() {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		List<ContractsGrantsDepartments> departments = session.createQuery("from ContractsGrantsDepartments").list();
		
		List<String> departmentIds = new ArrayList<String>();
		
		for (ContractsGrantsDepartments department : departments) {
			if (department != null && department.getId() != null && !departmentIds.contains(department.getId().getChrDepartmentCode())) {
				departmentIds.add(department.getId().getChrDepartmentCode());
			}
		}
		
		transaction.commit();
		session.flush();
		session.close();
		
		return departmentIds.toArray(new String[0]);
	}
	
	/**
	 * Get an array of department names given the department ids
	 * 
	 * @param departmentIDs The department ids to get the name of
	 * @return The array of department names
	 */
	public String[] getDepartmentNames(String[] departmentIDs) {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("from Departments where trim(chrTier3code) in :departmentIDs");
		query.setParameterList("departmentIDs", departmentIDs);
		
		List<Departments> departments = query.list();
		
		String[] departmentNames = new String[departmentIDs.length];
		boolean foundDepartment = false;
		String departmentName = null;
		for (int i = 0; i < departmentIDs.length; i++) {
			for (Departments department : departments) {
				if (department != null) {
					department.getId().getChrTier3code().trim();
					if (department.getId().getChrTier3code().trim().equals(departmentIDs[i])) {
						departmentName = department.getId().getChrTier3name();
						foundDepartment = true;
						break;
					}
				}
			}
			
			if (foundDepartment) {
				departmentNames[i] = departmentName;
			}
			else {
				departmentNames[i] = "No name found for this department ID";
			}
			foundDepartment = false;
		}
		
		transaction.commit();
		session.flush();
		session.close();
		
		return departmentNames;
	}
}
