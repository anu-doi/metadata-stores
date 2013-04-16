package au.edu.anu.metadatastores.services.store;

import java.util.List;
import java.util.Map;

import au.edu.anu.metadatastores.store.grants.Grant;
import au.edu.anu.metadatastores.store.grants.GrantService;
import au.edu.anu.metadatastores.store.people.Person;
import au.edu.anu.metadatastores.store.people.PersonService;
import au.edu.anu.metadatastores.store.publication.Publication;
import au.edu.anu.metadatastores.store.publication.PublicationService;

public class StoreService {
	private static StoreService singleton_;
	private PersonService personService_ = PersonService.getSingleton();
	private PublicationService publicationService_ = PublicationService.getSingleton();
	private GrantService grantService_ = GrantService.getSingleton();
	
	private StoreService() {
		
	}
	
	public static synchronized StoreService getSingleton() {
		if (singleton_ == null) {
			singleton_ = new StoreService();
		}
		return singleton_;
	}
	
	public Person getPersonInformation(String uid) {
		return personService_.getPerson(uid);
	}
	
	public List<Publication> getPersonPublications(String uid) {
		return publicationService_.getPersonsPublications(uid);
	}
	
	public List<Grant> getPersonGrants(String uid) {
		return grantService_.getGrantsForPerson(uid); 
	}
	
	public Person updatePerson(String uid, Map<String, List<String>> values) {
		return personService_.updateSomeAttributes(uid, values);
	}
	
	public List<Publication> getPublicationsByYear(String year) {
		return publicationService_.getPublicationsByYear(year);
	}
	
	public List<Person> getPersonByAttributes(Map<String, String> attributes) {
		return personService_.queryPersonByAttributes(attributes);
	}
}
