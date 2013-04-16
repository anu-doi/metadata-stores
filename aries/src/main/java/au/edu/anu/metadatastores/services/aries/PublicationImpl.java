package au.edu.anu.metadatastores.services.aries;

public class PublicationImpl implements Publication {
	private String ariesId;
	private String[] authors;
	private String publicationTitle;
	private String publicationName;
	private String publicationType;
	private String publicationDate;
	private String publicationCategory;
	private String publicationWebsite;
	private Subject forSubject1;
	private Subject forSubject2;
	private Subject forSubject3;
	private String isbn;
	private String issn;

	public String getAriesId() {
		return ariesId;
	}
	
	public void setAriesId(String ariesId) {
		this.ariesId = ariesId;
	}
	
	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	public String getPublicationTitle() {
		return publicationTitle;
	}

	public void setPublicationTitle(String publicationTitle) {
		this.publicationTitle = publicationTitle;
	}
	
	public String getPublicationName() {
		return publicationName;
	}
	
	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}

	public String getPublicationType() {
		return publicationType;
	}

	public void setPublicationType(String publicationType) {
		this.publicationType = publicationType;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	public String getPublicationCategory() {
		return publicationCategory;
	}
	
	public void setPublicationCategory(String publicationCategory) {
		this.publicationCategory = publicationCategory;
	}

	public String getPublicationWebsite() {
		return publicationWebsite;
	}

	public void setPublicationWebsite(String publicationWebsite) {
		this.publicationWebsite = publicationWebsite;
	}

	public Subject getForSubject1() {
		return forSubject1;
	}

	public void setForSubject1(Subject forSubject1) {
		this.forSubject1 = forSubject1;
	}

	public Subject getForSubject2() {
		return forSubject2;
	}

	public void setForSubject2(Subject forSubject2) {
		this.forSubject2 = forSubject2;
	}

	public Subject getForSubject3() {
		return forSubject3;
	}

	public void setForSubject3(Subject forSubject3) {
		this.forSubject3 = forSubject3;
	}

	public String getISSN() {
		return issn;
	}

	public void setISSN(String issn) {
		this.issn = issn;
	}

	public String getISBN() {
		return isbn;
	}

	public void setISBN(String isbn) {
		this.isbn = isbn;
	}
}
