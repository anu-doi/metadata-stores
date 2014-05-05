package au.edu.anu.metadatastores.services.aries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.aries.grants.ContractsGrantsInvestigators;
import au.edu.anu.metadatastores.datamodel.aries.grants.ContractsGrantsMain;

public class StaffIdTest {
	static final Logger LOGGER = LoggerFactory.getLogger(StaffIdTest.class);
	
	@Test
	public void findBySurnameTest() {
		StaffId staffService = StaffId.getSingleton();
		
		ANUStaff[] staff = staffService.findStaffBySurname("Roderick");
		boolean found = false;
		for (ANUStaff member : staff) {
			if (member.getAriesId() == 1) {
				found = true;
				assertEquals("Unexpected title", "Dr", member.getTitle());
				assertEquals("Unexpected given name", "Samuel", member.getGivenName());
				assertEquals("Unexpected surname", "Roderick", member.getSurname());
				assertEquals("Unexpected fax number", "6215 1112", member.getFax());
				assertEquals("Unexpected phone number", "6215 1111", member.getPhone());
				assertEquals("Unexpected email address", "samuel.roderick@anu.edu.au", member.getEmail());
				assertEquals("Unexpected university id", "t1234567", member.getUniId());
				Subject forSubject1 = member.getForSubject1();
				assertNotNull("Field of research subject 1 not found", forSubject1);
				assertEquals("Unexpected field of research code", "111706", forSubject1.getCode());
				assertEquals("Unexpected subject description", "Epidemiology", forSubject1.getDescription());
				assertEquals("Unexpected percentage", "60", forSubject1.getPercentage());
				Subject forSubject2 = member.getForSubject2();
				assertEquals("Unexpected field of research code", "111714", forSubject2.getCode());
				assertEquals("Unexpected subject description", "Mental Health", forSubject2.getDescription());
				assertEquals("Unexpected percentage", "30", forSubject2.getPercentage());
				Subject forSubject3 = member.getForSubject3();
				assertEquals("Unexpected field of research code", "110311", forSubject3.getCode());
				assertEquals("Unexpected subject description", "Medical Genetics (excl. Cancer Genetics)", forSubject3.getDescription());
				assertEquals("Unexpected percentage", "10", forSubject3.getPercentage());
			}
		}
		assertTrue("Expected staff member not found", found);
	}

	@Test
	public void findByGivenNameTest() {
		StaffId staffService = StaffId.getSingleton();
		
		ANUStaff[] staff = staffService.findStaffByGivenName("Samuel");
		boolean found = false;
		for (ANUStaff member : staff) {
			if (member.getAriesId() == 1) {
				found = true;
				assertEquals("Unexpected title", "Dr", member.getTitle());
				assertEquals("Unexpected given name", "Samuel", member.getGivenName());
				assertEquals("Unexpected surname", "Roderick", member.getSurname());
				assertEquals("Unexpected fax number", "6215 1112", member.getFax());
				assertEquals("Unexpected phone number", "6215 1111", member.getPhone());
				assertEquals("Unexpected email address", "samuel.roderick@anu.edu.au", member.getEmail());
				assertEquals("Unexpected university id", "t1234567", member.getUniId());
				Subject forSubject1 = member.getForSubject1();
				assertNotNull("Field of research subject 1 not found", forSubject1);
				assertEquals("Unexpected field of research code", "111706", forSubject1.getCode());
				assertEquals("Unexpected subject description", "Epidemiology", forSubject1.getDescription());
				assertEquals("Unexpected percentage", "60", forSubject1.getPercentage());
				Subject forSubject2 = member.getForSubject2();
				assertEquals("Unexpected field of research code", "111714", forSubject2.getCode());
				assertEquals("Unexpected subject description", "Mental Health", forSubject2.getDescription());
				assertEquals("Unexpected percentage", "30", forSubject2.getPercentage());
				Subject forSubject3 = member.getForSubject3();
				assertEquals("Unexpected field of research code", "110311", forSubject3.getCode());
				assertEquals("Unexpected subject description", "Medical Genetics (excl. Cancer Genetics)", forSubject3.getDescription());
				assertEquals("Unexpected percentage", "10", forSubject3.getPercentage());
			}
		}
		assertTrue("Expected staff member not found", found);
	}

	//@Ignore
	@Test
	public void findExternalBySurname() {
		StaffId staffService = StaffId.getSingleton();
		
		ExternalStaff[] staff = staffService.findExternalStaffBySurname("Simpson");
		boolean found = false;
		for (ExternalStaff member : staff) {
			if (member.getAriesStaffId().equals("e1234")) {
				found = true;
				assertEquals("Unexpected given name", "Homer", member.getGivenName());
				assertEquals("Unexpected surname", "Simpson", member.getSurname());
				assertEquals("Unexpected country", "America", member.getCountry());
				assertEquals("Unexpected institution", "University of Doh", member.getInstitution());
			}
			
		}
		assertTrue("Expected external staff member not found", found);
	}

	@Test
	public void findExternalByGivenName() {
		StaffId staffService = StaffId.getSingleton();
		
		ExternalStaff[] staff = staffService.findExternalStaffByGivenName("Homer");
		boolean found = false;
		for (ExternalStaff member : staff) {
			if (member.getAriesStaffId().equals("e1234")) {
				found = true;
				assertEquals("Unexpected given name", "Homer", member.getGivenName());
				assertEquals("Unexpected surname", "Simpson", member.getSurname());
				assertEquals("Unexpected country", "America", member.getCountry());
				assertEquals("Unexpected institution", "University of Doh", member.getInstitution());
			}
			
		}
		assertTrue("Expected external staff member not found", found);
	}

	@Test
	public void testActivities() {
		StaffId staffService = StaffId.getSingleton();
		ANUActivity[] activities = staffService.getContracts("t1234567");
		for (ANUActivity activity : activities) {
			if ("CON12345".equals(activity.getActivityId())) {
				assertEquals("Unexpected title", "Investigation into Test Methods", activity.getActivityTitle());
				assertEquals("Unexpected first investigator", "t1234567", activity.getFirstInvestigatorId());
				assertEquals("Unexpected start date", "2010-07-01 00:00:00.0", activity.getStartDate());
				assertEquals("Unexpected end date", "2012-04-01 00:00:00.0", activity.getEndDate());
				assertEquals("Unexpected scheme reference", "DP1000000", activity.getSchemeReference());
				assertEquals("Unexpected funds provider", "Australian Research Council (ARC)", activity.getFundsProvider());
				
				Subject forSubject1 = activity.getForSubject1();
				assertNotNull("Field of research subject 1 not found", forSubject1);
				assertEquals("Unexpected field of research code", "111706", forSubject1.getCode());
				assertEquals("Unexpected subject description", "Epidemiology", forSubject1.getDescription());
				assertEquals("Unexpected percentage", "50", forSubject1.getPercentage());
				Subject forSubject2 = activity.getForSubject2();
				assertEquals("Unexpected field of research code", "111714", forSubject2.getCode());
				assertEquals("Unexpected subject description", "Mental Health", forSubject2.getDescription());
				assertEquals("Unexpected percentage", "30", forSubject2.getPercentage());
				Subject forSubject3 = activity.getForSubject3();
				assertEquals("Unexpected field of research code", "110311", forSubject3.getCode());
				assertEquals("Unexpected subject description", "Medical Genetics (excl. Cancer Genetics)", forSubject3.getDescription());
				assertEquals("Unexpected percentage", "20", forSubject3.getPercentage());
			}
		}
		
	}

	@Test
	public void testExternalStaffById() {
		StaffId staffService = StaffId.getSingleton();
		ExternalStaff staff = staffService.getExternalStaffInformation("e1234");
	
		assertEquals("Unexpected given name", "Homer", staff.getGivenName());
		assertEquals("Unexpected surname", "Simpson", staff.getSurname());
		assertEquals("Unexpected country", "America", staff.getCountry());
		assertEquals("Unexpected institution", "University of Doh", staff.getInstitution());
	}
	
	@Test
	public void testContractMain() {
		Session session = AriesHibernateUtil.getSessionFactory().openSession();
		
		try {
			Query query = session.createQuery("from ContractsGrantsMain where chrContractCode = 'CON12345'");
			@SuppressWarnings("unchecked")
			List<ContractsGrantsMain> contracts = query.list();
			assertNotNull("contracts are null", contracts);
			assertTrue("No contracts found", contracts.size() > 0);
			for (ContractsGrantsMain contract : contracts) {
				List<ContractsGrantsInvestigators> investigators = contract.getContractsGrantsInvestigators();
				assertNotNull("Investigators are null", investigators);
				assertEquals("unexepcted number of investigators", 1, investigators.size());
			}
			
		}
		finally {
			session.close();
		}
	}
}
