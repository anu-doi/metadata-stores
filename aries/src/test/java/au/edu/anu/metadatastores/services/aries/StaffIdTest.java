package au.edu.anu.metadatastores.services.aries;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StaffIdTest {
	static final Logger LOGGER = LoggerFactory.getLogger(StaffIdTest.class);
	
	@Ignore
	@Test
	public void test() {
		StaffId staffService = StaffId.getSingleton();
		
		//ANUStaff[] staff = staffService.findStaffBySurname("Corsi");
		ANUStaff[] staff = staffService.findStaffBySurname("Roderick");
		for (ANUStaff member : staff) {
			LOGGER.info("Staff: {} {}", member.getGivenName(), member.getSurname());
			LOGGER.info("{}, {}", member.getForSubject1().getCode(), member.getForSubject1().getPercentage());
			LOGGER.info("{}, {}", member.getForSubject2().getCode(), member.getForSubject2().getPercentage());
			LOGGER.info("{}, {}", member.getForSubject3().getCode(), member.getForSubject3().getPercentage());
		}
		System.out.println("Done");
	}

	@Ignore
	@Test
	public void test2() {
		StaffId staffService = StaffId.getSingleton();
		
		ANUStaff[] staff = staffService.findStaffByGivenName("Marco");
		for (ANUStaff member : staff) {
			LOGGER.info("Staff: {} {}", member.getGivenName(), member.getSurname());
		}
		System.out.println("Done");
	}

	@Ignore
	@Test
	public void test3() {
		StaffId staffService = StaffId.getSingleton();
		
		ExternalStaff[] staff = staffService.findExternalStaffBySurname("Corsi");
		for (ExternalStaff member : staff) {
			LOGGER.info("Staff: {} {}", member.getGivenName(), member.getSurname());
		}
		System.out.println("Done");
	}

	@Ignore
	@Test
	public void test4() {
		StaffId staffService = StaffId.getSingleton();
		
		ExternalStaff[] staff = staffService.findExternalStaffByGivenName("Marco");
		for (ExternalStaff member : staff) {
			LOGGER.info("Staff: {} {}", member.getGivenName(), member.getSurname());
		}
		System.out.println("Done");
	}

	@Ignore
	@Test
	public void testActivities() {
		StaffId staffService = StaffId.getSingleton();
		
		//ANUActivity[] activities = staffService.getContracts("u9909577");
		ANUActivity[] activities = staffService.getContracts("u9708405");
		
		for (ANUActivity activity : activities) {
			LOGGER.info("ID: {}, Title: {}, Start: {}, End: {}", new Object[] {activity.getActivityId(), activity.getActivityTitle(), activity.getStartDate(), activity.getEndDate()});
			LOGGER.info("First Investigator: {}, Funds Provider: {}, Reference: {}", new Object[]{activity.getFirstInvestigatorId(), activity.getFundsProvider(), activity.getSchemeReference()});
			if (activity.getForSubject1() != null) {
				LOGGER.info("For Code 1: {} - {}, %: {}", activity.getForSubject1().getCode(), activity.getForSubject1().getDescription(), activity.getForSubject1().getPercentage());
			}
			if (activity.getForSubject2() != null) {
				LOGGER.info("For Code 2: {} - {}, %: {}", activity.getForSubject2().getCode(), activity.getForSubject2().getDescription(), activity.getForSubject2().getPercentage());
			}
			if (activity.getForSubject3() != null) {
				LOGGER.info("For Code 3: {} - {}, %: {}", activity.getForSubject3().getCode(), activity.getForSubject3().getDescription(), activity.getForSubject3().getPercentage());
			}
		}
	}

	@Ignore
	@Test
	public void testExternalStaffById() {
		StaffId staffService = StaffId.getSingleton();
		ExternalStaff staff = staffService.getExternalStaffInformation("E29292");
		LOGGER.info("ID: {}, Name: {} {}, Country: {}, Institution: {}", new Object[] {staff.getAriesStaffId(), staff.getGivenName(), staff.getSurname(), staff.getCountry(), staff.getInstitution()});
	}
}
