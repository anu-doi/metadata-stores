package au.edu.anu.metadatastores.services.aries;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AriesServiceTest {
	static final Logger LOGGER = LoggerFactory.getLogger(AriesServiceTest.class);

	AriesService ariesService = AriesService.getSingleton();
	
	@Ignore
	@Test
	public void test() {
		LOGGER.info("Starting Test");
	}
	
	@Ignore
	@Test
	public void testContracts() {
		ANUActivity[] activities = ariesService.getContracts("u4464261");
		LOGGER.info("Number of activities: {}", activities.length);
		for (int i = 0; i < activities.length; i++) {
			LOGGER.info("Activity ID: {}, Investigator Name: {}", activities[i].getActivityId(), activities[i].getFirstInvestigatorId());
		}
	}

	@Ignore
	@Test
	public void testPublications() {
		//Publication[] publications = ariesService.getPublications("u4464261");
		Publication[] publications = ariesService.getPublications("u9708405");
		LOGGER.info("Number of publications: {}", publications.length);
		for (Publication publication : publications) {
			LOGGER.info("Publication: {}, Type: {}, Date: {}, Website: {}", new Object[]{publication.getPublicationTitle(), publication.getPublicationType(), publication.getPublicationDate(), publication.getPublicationWebsite()});
			if (publication.getForSubject1() != null) {
				LOGGER.info("For Code 1: {} - {}, %: {}", publication.getForSubject1().getCode(), publication.getForSubject1().getDescription(), publication.getForSubject1().getPercentage());
			}
			if (publication.getForSubject2() != null) {
				LOGGER.info("For Code 2: {} - {}, %: {}", publication.getForSubject2().getCode(), publication.getForSubject2().getDescription(), publication.getForSubject2().getPercentage());
			}
			if (publication.getForSubject3() != null) {
				LOGGER.info("For Code 3: {} - {}, %: {}", publication.getForSubject3().getCode(), publication.getForSubject3().getDescription(), publication.getForSubject3().getPercentage());
			}
			LOGGER.info("ISBN: {}, ISSN: {}", publication.getISBN(), publication.getISSN());
		}
	}
	
	@Ignore
	@Test
	public void testFundingAuthorityURL() {
		String url = ariesService.getFundingAuthorityURLforANUActivity("CON18259");
		LOGGER.info("Funding Authority: {}", url);
	}

	@Ignore
	@Test
	public void testAllContractInvestigatorCode() {
		String[] investigatorCodes = ariesService.getAllContractInvestigatorCode();
		LOGGER.info("Number of investigator codes: {}", investigatorCodes.length);
		for (int i = 0; i < investigatorCodes.length && i < 10; i++) {
			LOGGER.info("Investigator Code: {}", investigatorCodes[i]);
		}
	}

	@Ignore
	@Test
	public void testAllOutput6Codes() {
		String[] output6Codes = ariesService.getAllOutput6Codes();
		LOGGER.info("Number of output 6 codes: {}", output6Codes.length);
		for (int i = 0; i < output6Codes.length && i < 10; i++) {
			LOGGER.info("Output 6 Code: {}", output6Codes[i]);
		}
	}

	@Test
	public void testInvestigatorUniIDs() {
		String[] uniIds = ariesService.getInvestigatorsUniIDs("CON18271");
		for (int i = 0; i < uniIds.length; i++) {
			LOGGER.info("Uni ID: {}", uniIds[i]);
		}
	}
	
	@Ignore
	@Test
	public void testActivityDescription() {
		String activityDescription = ariesService.getANUActivityDesc("CON23784");
		LOGGER.info("Activity Description: {}", activityDescription);
	}

	@Ignore
	@Test
	public void testInvestigatorsFromGrants() {
		String[] investigators = ariesService.getInvestigators(new String[] {"CON18259", "CON18271"});
		LOGGER.info("Number of investigators: {}", investigators.length);
		for (int i = 0; i < investigators.length && i < 10; i++) {
			LOGGER.info("Investigator: {}", investigators[i]);
		}
	}

	@Ignore
	@Test
	public void testGrantDescriptions() {
		String[] grantDescriptions = ariesService.getGrantDescriptions(new String[] {"CON18259", "CON18271"});
		LOGGER.info("Number of grants: {}", grantDescriptions.length);
		for (int i = 0; i < grantDescriptions.length && i < 10; i++) {
			LOGGER.info("Grant Description: {}", grantDescriptions[i]);
		}
	}

	@Ignore
	@Test
	public void testGrantNames() {
		String[] grantNames = ariesService.getGrantNames(new String[] {"CON18259", "CON18271", "CON23838"});
		LOGGER.info("Number of grants: {}", grantNames.length);
		for (int i = 0; i < grantNames.length && i < 10; i++) {
			LOGGER.info("Grant Name: {}", grantNames[i]);
		}
	}

	@Ignore
	@Test
	public void testPublicationCodes() {
		String[] publicationCodes = ariesService.getAllPublicationCodes();
		LOGGER.info("Number of publication codes: {}", publicationCodes.length);
		for (int i = 0; i < publicationCodes.length && i < 100; i++) {
			LOGGER.info("Publication Code: {}", publicationCodes[i]);
		}
	}

	@Ignore
	@Test
	public void testFirstAuthors() {
		//String[] firstAuthors = ariesService.getFirstAuthors(new String[] {"a240288xPUB101", "a154704xPUB1"});
		String[] firstAuthors = ariesService.getFirstAuthors(new String[] {"a154704xPUB1", "a240288xPUB1"});
		LOGGER.info("Number of authors: {}", firstAuthors.length);
		for (int i = 0; i < firstAuthors.length; i++) {
			LOGGER.info("First Author: {}", firstAuthors[i]);
		}
	}
	
	//@Ignore
	@Test
	public void testFirstAuthorsUniIDs() {
		String[] firstAuthorsUniIDs = ariesService.getFirstAuthorsUniIDs(new String[] {"a240288xPUB1", "a154704xPUB1"});
		//String[] firstAuthorsUniIDs = ariesService.getFirstAuthorsUniIDs(new String[] {"PUB101", "PUB1"});
		LOGGER.info("Number of authors: {}", firstAuthorsUniIDs.length);
		for (int i = 0; i < firstAuthorsUniIDs.length; i++) {
			LOGGER.info("First Author Uni ID: {}", firstAuthorsUniIDs[i]);
		}
	}
	
	@Ignore
	@Test
	public void testDepartmentCodes() {
		String[] departmentCodes = ariesService.getDepartmentCodes();
		LOGGER.info("Number of departments: {}", departmentCodes.length);
		for (int i = 0; i < departmentCodes.length && i < 10; i++) {
			LOGGER.info("Department Code: {}", departmentCodes[i]);
		}
	}
	
	@Ignore
	@Test
	public void testDeparmtentNames() {
		String[] departmentNames = ariesService.getDepartmentNames(new String[] {"529", "275"});
		for (int i = 0; i < departmentNames.length; i++) {
			LOGGER.info("Department Name: {}", departmentNames[i]);
		}
	}
	
	@Ignore
	@Test
	public void testContractCodes() {
		String[] contractCodes = ariesService.getAllContractCodes();
		LOGGER.info("Number of contract codes: {}", contractCodes.length);
		for (int i = 0; i < contractCodes.length && i < 10; i++) {
			LOGGER.info("Contract Code: {}", contractCodes[i]);
		}
	}
	
	@Ignore
	@Test
	public void  testStaffInformation() {
		//ANUStaff staff = ariesService.getStaffInformation("u4464261");
		ANUStaff staff = ariesService.getStaffInformation("T1241");
		LOGGER.info("Staff Name: {} {}, For Code: {}, Department: {}", new Object[] {staff.getGivenName(), staff.getSurname(), staff.getForSubject1().getCode(), staff.getDepartmentName()});
	}

	@Ignore
	@Test
	public void testExternalStaff() {
		ExternalStaff staff = ariesService.getExternalStaffInformation("E40140");
		//E40140
		LOGGER.info("Staff Name: {} {}, Country: {}, Institution: {}", new Object[] {staff.getGivenName(), staff.getSurname()});
	}

	@Ignore
	@Test
	public void testGetPeoplesPublications() {
		Publication[] publications = ariesService.getPublications("u3171954");
		Publication pub = null;
		for (int i = 0; i < publications.length; i++){
			pub = publications[i];
			LOGGER.info("ID: {}, Title: {}", pub.getAriesId(), pub.getPublicationTitle());
		}
	}
}
