package au.edu.anu.metadatastores.store.grant;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.store.grants.Grant;
import au.edu.anu.metadatastores.store.grants.GrantService;
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
/*
	@Test
	public void test() {
		List<Grant> grants = grantService_.fetchGrantsForPerson("u9909577");
		for (Grant grant : grants) {
			LOGGER.info("Contract: {}, Start: {}, End: {}, Title: {}", new Object[] {grant.getContractCode(), grant.getStartDate(), grant.getEndDate(), grant.getTitle()});
			for (Person person : grant.getAssociatedPeople()) {
				LOGGER.info("Investigator: {}", person.getExtId());
			}
		}
		LOGGER.info("Done");
	}
*/
	/*@Test
	public void saveTest() {
		List<Grant> grants = grantService_.fetchGrantsForPerson("u9909577");
		for (Grant grant : grants) {
			grantService_.saveGrant(grant);
		}
		LOGGER.info("Done");
	}*/
	
	@Test
	public void getGrantTest() {
	//	Grant grant = grantService_.getGrant("CON25338");
	//	Grant grant = grantService_.getGrant("CON9056");
	//	Grant grant = grantService_.getGrant("CON11074");

		Grant grant = grantService_.getGrant("CON21454");
		
		printGrant(grant);
		
		LOGGER.info("Done");
	}
	
/*	@Test
	public void getGrantsForPerson() {
		List<Grant> grants = grantService_.getGrantsForPerson("u9909577");
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
	
	private void printGrant(Grant grant) {
		LOGGER.info("Code: {}, Title: {}, Start Date: {}, End Date: {}", new Object[]{grant.getContractCode(), grant.getTitle(), grant.getStartDate(), grant.getEndDate()});
		if (grant.getFirstInvestigator() != null) {
			LOGGER.info("First Investigator: {} {}", grant.getFirstInvestigator().getGivenName(), grant.getFirstInvestigator().getSurname());
		}
		for (Person person : grant.getAssociatedPeople()) {
			LOGGER.info("Assocated Person: {} {}", person.getGivenName(), person.getSurname());
		}
	}
}
