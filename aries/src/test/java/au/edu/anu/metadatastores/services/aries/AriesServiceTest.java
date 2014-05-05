package au.edu.anu.metadatastores.services.aries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AriesServiceTest {
	static final Logger LOGGER = LoggerFactory.getLogger(AriesServiceTest.class);

	AriesService ariesService = AriesService.getSingleton();

	@Test
	public void testContracts() {
		ANUActivity[] activities = ariesService.getContracts("t1234567");
		assertNotNull(activities);
		assertEquals("Unexpected number of activities", 1, activities.length);
		ANUActivity activity = activities[0];
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

	@Test
	public void testPublications() {
		Publication[] publications = ariesService.getPublications("t1234567");
		assertNotNull("Unexpected publications value", publications);
		assertEquals("Unexpected number of publications", 3, publications.length);
		boolean unexpectedValue = false;
		for (Publication pub : publications) {
			if (!"f1234xPUB100".equals(pub.getAriesId()) && !"f1234xPUB101".equals(pub.getAriesId()) && !"f1234xPUB102".equals(pub.getAriesId())) {
				unexpectedValue = true;
			}
		}
		
		assertFalse(unexpectedValue);
	}

	@Test
	public void testFundingAuthorityURL() {
		String url = ariesService.getFundingAuthorityURLforANUActivity("CON12345");
		assertEquals("Unexpected funding url", "http://ARC_ANDS_path/CON12345", url);
	}

	@Test
	public void testAllContractInvestigatorCode() {
		String[] investigatorCodes = ariesService.getAllContractInvestigatorCode();
		assertNotNull("Null investigator codes", investigatorCodes);
		assertEquals("Unexpected Number of investigator codes", 1, investigatorCodes.length);
		assertEquals("Unexpected investigator code", "CON12345xxt1234567x", investigatorCodes[0]);
	}

	@Test
	public void testAllOutput6Codes() {
		String[] output6Codes = ariesService.getAllOutput6Codes();
		assertNotNull(output6Codes);
		assertEquals("Unexpected number of output 6 codes", 3, output6Codes.length);
		List<String> expectedValues = Arrays.asList("f1234xPUB100","f1234xPUB101","f1234xPUB102");
		boolean unexpectedValue = false;
		for (String code : output6Codes) {
			if (!expectedValues.contains(code)) {
				unexpectedValue = true;
			}
		}
		assertFalse(unexpectedValue);
	}

	@Test
	public void testInvestigatorUniIDs() {
		String[] uniIds = ariesService.getInvestigatorsUniIDs("CON12345");
		assertNotNull("No investigators found", uniIds);
		assertEquals("Unexpected number of investigators", 1, uniIds.length);
		assertEquals("Unexpected investigator", "t1234567", uniIds[0]);
	}

	@Test
	public void testActivityDescription() {
		String activityDescription = ariesService.getANUActivityDesc("CON12345");
		assertEquals("Unexpected activity description", "Investigation into Test Methods", activityDescription);
	}

	@Test
	public void testInvestigatorsFromGrants() {
		String[] investigators = ariesService.getInvestigators(new String[] {"CON12345"});
		assertNotNull("Expected investigators", investigators);
		assertEquals("Unexpected number of investigators", 1, investigators.length);
		assertEquals("Unexpected investigator id", "t1234567", investigators[0]);
	}

	@Test
	public void testGrantDescriptions() {
		String[] grantDescriptions = ariesService.getGrantDescriptions(new String[] {"CON12345"});
		assertNotNull("No grant descriptions", grantDescriptions);
		assertEquals("Unexpected number of grant descriptions", 1, grantDescriptions.length);
		assertEquals("Unexpected grant description", "Investigation into Test Methods", grantDescriptions[0]);
	}

	@Test
	public void testGrantNames() {
		String[] grantNames = ariesService.getGrantNames(new String[] {"CON12345"});
		assertNotNull("No Grant Names", grantNames);
		assertEquals("Unexpected number of grant names", 1, grantNames.length);
		assertEquals("Unexpected grant name", "DP1000000", grantNames[0]);
	}

	@Test
	public void testPublicationCodes() {
		String[] publicationCodes = ariesService.getAllPublicationCodes();
		assertNotNull("No publication codes found", publicationCodes);
		assertEquals("Unexpected number of publication codes", 3, publicationCodes.length);
		List<String> publications = Arrays.asList("f1234xPUB100","f1234xPUB101","f1234xPUB102");
		boolean unexpectedValue = false;
		for (String code : publicationCodes) {
			 if (!publications.contains(code)) {
				 unexpectedValue = true;
			 }
		}
		assertFalse("Unexpected publication code", unexpectedValue);
	}

	@Test
	public void testFirstAuthors() {
		String[] firstAuthors = ariesService.getFirstAuthors(new String[] {"f1234xPUB100"});
		assertNotNull("No first authors found", firstAuthors);
		assertEquals("Unexpected number of first authors", 1, firstAuthors.length);
		assertEquals("Unexpected first author", "t1234567", firstAuthors[0]);
	}

	@Test
	public void testFirstAuthorsUniIDs() {
		String[] firstAuthorsUniIDs = ariesService.getFirstAuthorsUniIDs(new String[] {"f1234xPUB100"});
		assertNotNull("No first authors found", firstAuthorsUniIDs);
		assertEquals("Unexpected number of first authors", 1, firstAuthorsUniIDs.length);
		assertEquals("Unexpected author university id", "t1234567", firstAuthorsUniIDs[0]);
	}
	
	@Test
	public void testDepartmentCodes() {
		String[] departmentCodes = ariesService.getDepartmentCodes();
		assertNotNull("Department codes null", departmentCodes);
		assertEquals("Unexpected number of department codes", 1, departmentCodes.length);
		assertEquals("Unexpected department code", "100", departmentCodes[0]);
	}
	
	@Test
	public void testDeparmtentNames() {
		String[] departmentNames = ariesService.getDepartmentNames(new String[] {"200"});
		assertNotNull("Department names null", departmentNames);
		assertEquals("Unexpected number of department names", 1, departmentNames.length);
		assertEquals("Unexpected department name", "School of Testing", departmentNames[0]);
	}
	
	@Test
	public void testContractCodes() {
		String[] contractCodes = ariesService.getAllContractCodes();
		assertNotNull("Unexpected  value for contractCodes", contractCodes);
		assertEquals("Unexpected number of contract codes", 1, contractCodes.length);
		assertEquals("Unexpected contract code", "CON12345", contractCodes[0]);
	}
	
	@Test
	public void  testStaffInformation() {
		ANUStaff staff = ariesService.getStaffInformation("t1234567");
		
		assertEquals("Unexpected title", "Dr", staff.getTitle());
		assertEquals("Unexpected given name", "Samuel", staff.getGivenName());
		assertEquals("Unexpected surname", "Roderick", staff.getSurname());
		assertEquals("Unexpected fax number", "6215 1112", staff.getFax());
		assertEquals("Unexpected phone number", "6215 1111", staff.getPhone());
		assertEquals("Unexpected email address", "samuel.roderick@anu.edu.au", staff.getEmail());
		assertEquals("Unexpected university id", "t1234567", staff.getUniId());
		Subject forSubject1 = staff.getForSubject1();
		assertNotNull("Field of research subject 1 not found", forSubject1);
		assertEquals("Unexpected field of research code", "111706", forSubject1.getCode());
		assertEquals("Unexpected subject description", "Epidemiology", forSubject1.getDescription());
		assertEquals("Unexpected percentage", "60", forSubject1.getPercentage());
		Subject forSubject2 = staff.getForSubject2();
		assertEquals("Unexpected field of research code", "111714", forSubject2.getCode());
		assertEquals("Unexpected subject description", "Mental Health", forSubject2.getDescription());
		assertEquals("Unexpected percentage", "30", forSubject2.getPercentage());
		Subject forSubject3 = staff.getForSubject3();
		assertEquals("Unexpected field of research code", "110311", forSubject3.getCode());
		assertEquals("Unexpected subject description", "Medical Genetics (excl. Cancer Genetics)", forSubject3.getDescription());
		assertEquals("Unexpected percentage", "10", forSubject3.getPercentage());
		
	}

	@Test
	public void testExternalStaff() {
		ExternalStaff staff = ariesService.getExternalStaffInformation("e1234");
		
		assertEquals("Unexpected given name", "Homer", staff.getGivenName());
		assertEquals("Unexpected surname", "Simpson", staff.getSurname());
		assertEquals("Unexpected country", "America", staff.getCountry());
		assertEquals("Unexpected institution", "University of Doh", staff.getInstitution());
	}
}
