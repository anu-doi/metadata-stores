package au.edu.anu.metadatastores.store.publication;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import au.edu.anu.metadatastores.store.misc.Subject;
import au.edu.anu.metadatastores.store.people.Person;

@XmlRootElement(name="publication")
public class Publication {
	private String ariesId;
	private String type;
	private String title;
	private String year;
	private String firstAuthor;
	private List<Person> authors = new ArrayList<Person>();
	private String publicationName;
	private String category;
	private String ISBN;
	private String ISSN;
	private List<Subject> anzforSubjects = new ArrayList<Subject>();
	
	@XmlElement(name="aries-id")
	public String getAriesId() {
		return ariesId;
	}
	
	public void setAriesId(String ariesId) {
		this.ariesId = ariesId;
	}
	
	@XmlElement(name="type")
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name="title")
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement(name="publication-year")
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}

	@XmlElement(name="first-author")
	public String getFirstAuthor() {
		return firstAuthor;
	}
	
	public void setFirstAuthor(String firstAuthor) {
		this.firstAuthor = firstAuthor;
	}

	@XmlElement(name="publication-name")
	public String getPublicationName() {
		return publicationName;
	}
	
	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}

	@XmlElement(name="author")
	public List<Person> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Person> authors) {
		this.authors = authors;
	}

	@XmlElement(name="category")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@XmlElement(name="isbn")
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	@XmlElement(name="issn")
	public String getISSN() {
		return ISSN;
	}

	public void setISSN(String iSSN) {
		ISSN = iSSN;
	}
	
	@XmlElement(name="for-subject")
	public List<Subject> getAnzforSubjects() {
		return anzforSubjects;
	}
	
	public void setAnzforSubjects(List<Subject> anzforSubjects) {
		this.anzforSubjects = anzforSubjects;
	}
}
