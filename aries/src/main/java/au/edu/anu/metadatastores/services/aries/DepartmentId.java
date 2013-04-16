package au.edu.anu.metadatastores.services.aries;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import au.edu.anu.metadatastores.datamodel.aries.grants.ContractsGrantsDepartments;
import au.edu.anu.metadatastores.datamodel.aries.grants.Departments;

public class DepartmentId {
	private static DepartmentId singleton_;
	
	private DepartmentId() {
		
	}
	
	public static synchronized DepartmentId getSingleton() {
		if (singleton_ == null) {
			singleton_ = new DepartmentId();
		}
		return singleton_;
	}
	
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
