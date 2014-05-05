package au.edu.anu.metadatastores.store.people;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.ItemAttribute;
import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;
import au.edu.anu.metadatastores.services.store.StoreHibernateUtil;
import au.edu.anu.metadatastores.store.misc.Subject;

public class PersonServiceTest {
	static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceTest.class);

	PersonService personService_;
	
	@Before
	public void setUp() {
		personService_ = PersonService.getSingleton();
	}

	@Ignore
	@Test
	public void test() {
		Person person = personService_.fetchPersonInformation("u5125986");
		assertEquals("Genevieve", person.getGivenName());
		assertEquals("Turner", person.getSurname());
		System.out.println("Done");
	}

	@Ignore
	@Test
	public void queryItemTest() {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		
		Query query =session.createQuery("from ItemAttribute where attrType = :attrType and attrValue = :attrValue");
		query.setParameter("attrType", StoreAttributes.GIVEN_NAME);
		query.setParameter("attrValue", "Test");
		@SuppressWarnings("unchecked")
		List<ItemAttribute> attributes = query.list();
		LOGGER.info("Number of rows of attributes found: {}", attributes.size());
		
		for (ItemAttribute attribute : attributes) {
			Person person = personService_.getPerson(attribute.getItem(), false);
			LOGGER.info("Name: {} {}", new Object[] {person.getGivenName(), person.getSurname()});
		}
		
		session.close();
		
		System.out.println("Done");
	}

	@Ignore
	@Test
	public void getUidTest() {
		Person person = personService_.getPerson("t1234569");
		
		if (person == null) {
			fail("No Person Found");
		}
		LOGGER.info("Name: {} {}", new Object[] {person.getGivenName(), person.getSurname()});
		System.out.println("Done");
	}
	
	@Ignore
	@Test
	public void updateItemTest() {
		Person person = new Person();
		person.setExtId("t1234569");
		person.setUid("t1234569");
		person.setGivenName("Some");
		person.setSurname("Person");
		person.setEmail("some.person@anu.edu.au");
		person.setAriesId("1234");
		person.setOrganisationalUnit("ITS");
		person.setPhoneNumbers(Arrays.asList(new String[]{"54323", "52562"}));
		person.setFaxNumbers(Arrays.asList(new String[]{"51230", "54352"}));
		person.setJobTitle("Random Job Title");
		person.setStaffType("Academic Staff");
		//person.setNlaId("http://nla.gov.au/1235");
		
		List<Subject> subjects = new ArrayList<Subject>();
		Subject subject = new Subject("10",null,"16%");
		subjects.add(subject);
		
		subject = new Subject("20", "LANGUAGE, COMMUNICATION AND CULTURE","84%");
		subjects.add(subject);
		
		person.setAnzforSubjects(subjects);
		personService_.savePerson(person);
		Person person2 = personService_.getPerson("t1234569");
		printPersonInfo(Arrays.asList(person2));
		System.out.println("Done saving");
	}
	
	@Ignore
	@Test
	public void updatePersonTest() {
		
		//Person person = personService_.fetchPersonInformation("u9909577");
		Person person = personService_.fetchPersonInformation("u9613353");
		//Person person = personService_.fetchPersonInformation("u5125986");
		//Person person = personService_.fetchPersonInformation("u4034284");
		//Person person = personService_.fetchPersonInformation("u3882913");
		
		personService_.savePerson(person);
		
		System.out.println("Done Updating");
	}

	@Ignore
	@Test
	public void updateMultiplePeopleTest() {
		//String[] ids = new String[] {"u5125986","u4464261","u9909577","u4346971","u4039549","u4254847","u9802772","u4269066","u4016705",
		//		"u9413939","u9708405","u4026567","u4548830","u8100493","u8100341","u4495610","u9614809","u8400184",
		//		"u3872330","u4240521","u3171954","u4014066","u4002313","u9810300","u9210598","u4021832","u8406985",
		//		"u4038535","u4050249","u4635999","u9909944","u4244719","u4199004","u4052332","u4269078","u3932860",
		//		"u9407331","u9802669","u8600328","u9504681","u8904435","u4026213","u8406201","u9609912","u4487551",
		//		"u9410731","u3548500","u8002356","u3928684","u9714433","u4485658","u3481417","u8712402","u8800157",
		//		"u4791152","u4507277","u9814043","u4750648","u3789056","u4162881","u3551013","u4408050","u4881278",
		//		"u9610698","u4047421"};
		String[] ids = new String[] {"u4014066","u9909577","u3171954","u4750648","u3789056","u4791152","u4507277","u9814043","u4162881"};
		for (String id : ids) {
			Person person = personService_.fetchPersonInformation(id);
			personService_.savePerson(person);
		}
	}
	
	@Ignore
	@Test
	public void updateExternalPersonTest() {
		Person person = personService_.fetchExternalUser("E29292");
		personService_.savePerson(person);
		System.out.println("Done");
	}
	
	@Ignore
	@Test
	public void updateExternalPeopleTest() {
		String[] ids = new String[] {"e26568","e54817","e26432","e26562","e27865","e22700","e42112","e26450","e33807","e26659","e29893","e21985",
"e54996","e33521","e26630","e26566","e26638","e26656","e6599","e26468","e7404","e27881","e26544","e27903",
"e26586","e47942","e26676","e11580","e42060","e26645","e55218","e40587","e26134","e26463","e29351","e33804",
"e3200","e26660","e26546","e54997","e29696","e15996","e4758","e10899","e26472","e52349","e52347","e26427",
"e46878","e15994","e40140","e48170","e26584","e26158","e26679","e54818","e36005","e47371","e29199","e37970",
"e26480","e38385","e23808","e23679","e22696","e26160","e26539","e3084","e26494","e26642","e19550","e52485",
"e33833","e26440","e46765","e26633","e26628","e17341","e33831","e26426","e31000","e26538","e26504","e55220",
"e17774","e29697","e40586","e5295","e28263","e23708","e26542","e13056","e26618","e55219","e55215","e24349",
"e26658","e2918","e6046","e26635","e26487","e42894","e26678","e26614","e47969","e26430","e26429","e18621"};
		for (String id : ids) {
			Person person = personService_.fetchExternalUser(id);
			personService_.savePerson(person);
		}
	}

	@Ignore
	@Test
	public void partialUpdate() {
		String uid = "t1234569";
		
		Person beforePerson = personService_.getPerson(uid);

		Map<String, List<String>> values = new HashMap<String, List<String>>();
		
		Person afterPerson = personService_.updateSomeAttributes(uid, values);
		
		assertEquals(afterPerson.getUid(), beforePerson.getUid());
		assertEquals(afterPerson.getGivenName(), beforePerson.getGivenName());
		
		values.put(Person.GIVEN_NAME, Arrays.asList("Some"));
		values.put(Person.PHONE, Arrays.asList("54323","52386"));
		values.put(Person.NLA_ID, Arrays.asList("http://nla.gov.au/1234"));
		//person.setPhoneNumbers(Arrays.asList(new String[]{"51235", "52562"}));
		afterPerson = personService_.updateSomeAttributes(uid, values);
		
		assertEquals(afterPerson.getUid(), beforePerson.getUid());
		assertNotSame(afterPerson.getGivenName(), beforePerson.getGivenName());
		
		LOGGER.info("Name 1: {}, Name 2: {}", beforePerson.getGivenName(), afterPerson.getGivenName());
		LOGGER.info("Name 1: {}, Name 2: {}", beforePerson.getSurname(), afterPerson.getSurname());
		LOGGER.info("Phone Numbers 1: {}", beforePerson.getPhoneNumbers());
		LOGGER.info("Phone Numbers 2: {}", afterPerson.getPhoneNumbers());
		LOGGER.info("Fax Numbers 1: {}", beforePerson.getFaxNumbers());
		LOGGER.info("Fax Numbers 2: {}", afterPerson.getFaxNumbers());
	}
	
	@Ignore
	@Test
	public void testFetchProfile() {
		List<Person> people = personService_.getBasicPeople(Arrays.asList("u5125986"), false);
		printPersonInfo(people);
		LOGGER.info("Done");
	}
	
	@Ignore
	@Test
	public void testSomething() {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		session.enableFetchProfile("person-with-attributes");
		LOGGER.info("Retrieving PersonItem");
		PersonItem personItem = (PersonItem) session.get(PersonItem.class, new Long(6));
		
		LOGGER.info("Retrieved PersonItem");
		LOGGER.info("Id: {}, Ext ID: {}", personItem.getIid(), personItem.getExtId());
		LOGGER.info("Retrieving Item Attributes");
		Set<ItemAttribute> itemAttributes = personItem.getItemAttributes();
		LOGGER.info("Retrieved Item Attributes");
		for (ItemAttribute itemAttribute : itemAttributes) {
			LOGGER.info("Type: {}, Value: {}", itemAttribute.getAttrType(), itemAttribute.getAttrValue());
		}
		session.close();
	}
	
	@Ignore
	@Test
	public void testQueryMap() {
		Map<String, String> values = new HashMap<String, String>();
		values.put(StoreAttributes.EMAIL, "genevieve.turner@anu.edu.au");
		List<Person> people = personService_.queryPersonByAttributes(values);
		printPersonInfo(people);
		
		values.clear();
		values.put(StoreAttributes.GIVEN_NAME, "Genevieve");
		values.put(StoreAttributes.SURNAME, "Turner");
		people = personService_.queryPersonByAttributes(values);
		printPersonInfo(people);
	}
	
	private void printPersonInfo(List<Person> people) {
		LOGGER.info("List of people:");
		if (people != null) {
			for (Person person : people) {
				LOGGER.info("Name: {}, Email: {}, Given Name: {}, Surname: {}, UID: {}", new Object[]{person.getDisplayName(), person.getEmail(), person.getGivenName(), person.getSurname(), person.getUid()});
			}
		}
	}
	
	@Ignore
	@Test
	public void getAriesPeople() {
		Date startDate = new Date();
		List<Person> people = personService_.getCurrentAriesPeople();
		printPersonInfo(people);
		Date endDate = new Date();
		long diff = endDate.getTime() - startDate.getTime();
		double sec = diff / 1000;
		double min = sec/60;
		LOGGER.info("Milliseconds: {}, Seconds: {}, Minutes: {}", diff, sec, min);
		LOGGER.info("Done");
	}
	
	@Ignore
	@Test
	public void updateKnownPeopleByName() {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		
		try {
			Query query = session.createQuery("from PersonItem");
			List<PersonItem> items = query.list();
			for (PersonItem item : items) {
				personService_.createPersonForAries(item.getExtId());
			}
		}
		finally {
			session.close();
		}
	}
	
	@Test
	public void createNewPeople() {
		Person person = new Person();
		String uid = "t1111111";
		person.setExtId(uid);
		person.setUid(uid);
		person.setDescription("This is the biography for Random Person");
		person.setEmail(uid + "@mailinator.com");
		person.setDisplayName("Random A Person");
		person.setGivenName("Random");
		person.setSurname("Person");
		person.setOrganisationalUnit("Information Technology Services");
		person.setStaffType("staff");
		person.setPreferredName("Random Person");
		person.setJobTitle("Test Analyst");
		LOGGER.info("Saving: {}", uid);
		personService_.savePerson(person);
		LOGGER.info("After saving: {}", uid);
		

		uid = "t1111112";
		person = new Person();
		person.setExtId(uid);
		person.setUid(uid);
		person.setDescription("This is the biography for Homer Simpson");
		person.setEmail(uid + "@mailinator.com");
		person.setDisplayName("Homer Simpon");
		person.setGivenName("Homer");
		person.setSurname("Simpson");
		person.setOrganisationalUnit("Information Technology Services");
		person.setStaffType("staff");
		person.setPreferredName("Homer Simpson");
		person.setJobTitle("Test Lead");
		LOGGER.info("Saving: {}", uid);
		personService_.savePerson(person);
		LOGGER.info("After saving: {}", uid);
		
		uid = "t1111113";
		person = new Person();
		person.setExtId(uid);
		person.setUid(uid);
		person.setDescription("This is the biography for Lisa Simpson");
		person.setEmail(uid + "@mailinator.com");
		person.setDisplayName("Lisa Simpson");
		person.setGivenName("Lisa");
		person.setSurname("Simpson");
		person.setOrganisationalUnit("Information Technology Services");
		person.setStaffType("staff");
		person.setPreferredName("Lisa Simpson");
		person.setJobTitle("Software Developer");
		LOGGER.info("Saving: {}", uid);
		personService_.savePerson(person);
		LOGGER.info("After saving: {}", uid);
		
		uid = "t1111114";
		person = new Person();
		person.setExtId(uid);
		person.setUid(uid);
		person.setDescription("This is the biography for Marge Simpson");
		person.setEmail(uid + "@mailinator.com");
		person.setDisplayName("Marge Simpson");
		person.setGivenName("Marge");
		person.setSurname("Simpson");
		person.setOrganisationalUnit("Information Technology Services");
		person.setStaffType("staff");
		person.setPreferredName("Marge Simpson");
		person.setJobTitle("Development Team Lead");
		LOGGER.info("Saving: {}", uid);
		personService_.savePerson(person);
		LOGGER.info("After saving: {}", uid);
		
	}
	
	
}
