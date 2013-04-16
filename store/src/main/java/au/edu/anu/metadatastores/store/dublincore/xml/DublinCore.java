package au.edu.anu.metadatastores.store.dublincore.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="dc", namespace=DublinCoreConstants.OAI_DC)
public class DublinCore {
	private List<String> titles = new ArrayList<String>();
	private List<String> creators = new ArrayList<String>();
	private List<String> subjects = new ArrayList<String>();
	private List<String> descriptions = new ArrayList<String>();
	private List<String> publishers = new ArrayList<String>();
	private List<String> contributors = new ArrayList<String>();
	private List<String> dates = new ArrayList<String>();
	private List<String> types = new ArrayList<String>();
	private List<String> formats = new ArrayList<String>();
	private List<String> identifiers = new ArrayList<String>();
	private List<String> sources = new ArrayList<String>();
	private List<String> languages = new ArrayList<String>();
	private List<String> relations = new ArrayList<String>();
	private List<String> coverage = new ArrayList<String>();
	private List<String> rights = new ArrayList<String>();
	
	@XmlElement(name="title", namespace=DublinCoreConstants.DC)
	public List<String> getTitles() {
		return titles;
	}
	
	public void setTitles(List<String> titles) {
		this.titles = titles;
	}

	@XmlElement(name="creator", namespace=DublinCoreConstants.DC)
	public List<String> getCreators() {
		return creators;
	}
	
	public void setCreators(List<String> creators) {
		this.creators = creators;
	}

	@XmlElement(name="subject", namespace=DublinCoreConstants.DC)
	public List<String> getSubjects() {
		return subjects;
	}
	
	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	@XmlElement(name="description", namespace=DublinCoreConstants.DC)
	public List<String> getDescriptions() {
		return descriptions;
	}
	
	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

	@XmlElement(name="publisher", namespace=DublinCoreConstants.DC)
	public List<String> getPublishers() {
		return publishers;
	}
	
	public void setPublishers(List<String> publishers) {
		this.publishers = publishers;
	}

	@XmlElement(name="contributor", namespace=DublinCoreConstants.DC)
	public List<String> getContributors() {
		return contributors;
	}
	
	public void setContributors(List<String> contributors) {
		this.contributors = contributors;
	}

	@XmlElement(name="date", namespace=DublinCoreConstants.DC)
	public List<String> getDates() {
		return dates;
	}
	
	public void setDates(List<String> dates) {
		this.dates = dates;
	}

	@XmlElement(name="type", namespace=DublinCoreConstants.DC)
	public List<String> getTypes() {
		return types;
	}
	
	public void setTypes(List<String> types) {
		this.types = types;
	}

	@XmlElement(name="format", namespace=DublinCoreConstants.DC)
	public List<String> getFormats() {
		return formats;
	}
	
	public void setFormats(List<String> formats) {
		this.formats = formats;
	}

	@XmlElement(name="identifier", namespace=DublinCoreConstants.DC)
	public List<String> getIdentifiers() {
		return identifiers;
	}
	
	public void setIdentifiers(List<String> identifiers) {
		this.identifiers = identifiers;
	}

	@XmlElement(name="source", namespace=DublinCoreConstants.DC)
	public List<String> getSources() {
		return sources;
	}
	
	public void setSources(List<String> sources) {
		this.sources = sources;
	}

	@XmlElement(name="language", namespace=DublinCoreConstants.DC)
	public List<String> getLanguages() {
		return languages;
	}
	
	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	@XmlElement(name="relation", namespace=DublinCoreConstants.DC)
	public List<String> getRelations() {
		return relations;
	}
	
	public void setRelations(List<String> relations) {
		this.relations = relations;
	}

	@XmlElement(name="coverage", namespace=DublinCoreConstants.DC)
	public List<String> getCoverage() {
		return coverage;
	}
	
	public void setCoverage(List<String> coverage) {
		this.coverage = coverage;
	}

	@XmlElement(name="rights", namespace=DublinCoreConstants.DC)
	public List<String> getRights() {
		return rights;
	}
	
	public void setRights(List<String> rights) {
		this.rights = rights;
	}
}
