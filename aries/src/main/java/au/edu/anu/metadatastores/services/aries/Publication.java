package au.edu.anu.metadatastores.services.aries;

public interface Publication {
	public String getAriesId();
	public void setAriesId(String ariesId);
	
	//Update this for muliple authors
	public String[] getAuthors();
	public void setAuthors(String[] author);
	
	/**
	 * Retrieves the title of the publication
	 * 
	 * @return The title of the publication
	 */
	public String getPublicationTitle();
	public void setPublicationTitle(String publicationTitle);
	
	/**
	 * Retrieves the name of journal, conference or book in which the publication occurred
	 * 
	 * @return The name of the publication
	 */
	public String getPublicationName();
	public void setPublicationName(String publicationName);
	
	public String getPublicationType();
	public void setPublicationType(String publicationType);
	
	public String getPublicationDate();
	public void setPublicationDate(String publicationDate);
	
	public String getPublicationCategory();
	public void setPublicationCategory(String publicationCategory);
	
	public String getPublicationWebsite();
	public void setPublicationWebsite(String publicationWebsite);
	
	public String getISSN();
	public void setISSN(String issn);
	
	public String getISBN();
	public void setISBN(String isbn);
	
	public Subject getForSubject1();
	public void setForSubject1(Subject forSubject1);

	public Subject getForSubject2();
	public void setForSubject2(Subject forSubject2);
	
	public Subject getForSubject3();
	public void setForSubject3(Subject forSubject3);
}
