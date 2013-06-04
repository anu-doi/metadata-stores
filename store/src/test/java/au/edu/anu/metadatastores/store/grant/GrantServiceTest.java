package au.edu.anu.metadatastores.store.grant;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.store.grants.Grant;
import au.edu.anu.metadatastores.store.grants.GrantItem;
import au.edu.anu.metadatastores.store.grants.GrantService;
import au.edu.anu.metadatastores.store.misc.Subject;
import au.edu.anu.metadatastores.store.people.Person;

public class GrantServiceTest {
	static final Logger LOGGER = LoggerFactory.getLogger(GrantServiceTest.class);
	
	GrantService grantService_;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		grantService_ = GrantService.getSingleton();
	}

	/*@Test
	public void test() {
		//List<Grant> grants = grantService_.fetchGrantsForPerson("u9909577");
		List<Grant> grants = grantService_.fetchGrantsForPerson("u8808483");
		
		for (Grant grant : grants) {
			printGrant(grant);
		}
		LOGGER.info("Done");
	}*/

	@Test
	public void saveTest() {
		//List<Grant> grants = grantService_.fetchGrantsForPerson("u8808483");
		List<Grant> grants = grantService_.fetchGrantsForPerson("u4485658");
		
		for (Grant grant : grants) {
			GrantItem item = grantService_.saveGrant(grant);
		}
		LOGGER.info("Done");
	}
	
	/*@Test
	public void getGrantTest() {
	//	Grant grant = grantService_.getGrant("CON25338");
	//	Grant grant = grantService_.getGrant("CON9056");
	//	Grant grant = grantService_.getGrant("CON11074");

		Grant grant = grantService_.getGrant("CON21454");
		
		printGrant(grant);
		
		LOGGER.info("Done");
	}*/
	
	/*@Test
	public void getGrantsForPerson() {
		//List<Grant> grants = grantService_.getGrantsForPerson("u9909577");
		List<Grant> grants = grantService_.getGrantsForPerson("u8808483");
		
		if (grants != null) {
			LOGGER.info("Number of grants: {}", grants.size());
			for (Grant grant : grants) {
				printGrant(grant);
			}
		}
		else {
			LOGGER.info("No Grants Found");
		}
	}*/
	
/*	@Test
	public void getGrantTitleTest() {
		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put(StoreAttributes.TITLE, "Control");
		List<Grant> grants = grantService_.queryGrantsByAttributes(attributes);
		//List<Grant> grants = grantService_.queryGrantTitle("Control");
		LOGGER.info("Number of grants found: {}", grants.size());
		for (Grant grant : grants) {
			printGrant(grant);
		}
	}*/
	
	private void printGrant(Grant grant) {
		LOGGER.info("Code: {}, Title: {}, Start Date: {}, End Date: {}", new Object[]{grant.getContractCode(), grant.getTitle(), grant.getStartDate(), grant.getEndDate()});
		LOGGER.info("Description: {}", grant.getDescription());
		if (grant.getFirstInvestigator() != null) {
			LOGGER.info("First Investigator: {}, {} {}", grant.getFirstInvestigator().getExtId(), grant.getFirstInvestigator().getGivenName(), grant.getFirstInvestigator().getSurname());
		}
		for (Person person : grant.getAssociatedPeople()) {
			LOGGER.info("Associated Person: {}, {} {}", person.getExtId(), person.getGivenName(), person.getSurname());
		}
		for (Subject subject : grant.getAnzforSubjects()) {
			LOGGER.info("Subject: {}, {}, {}", new Object[] {subject.getCode(), subject.getValue(), subject.getPercentage()});
		}
	}
}
