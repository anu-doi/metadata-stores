package au.edu.anu.metadatastores.store.dublincore;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.ItemAttribute;
import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;

@MappedSuperclass
public class DublinCoreItem extends Item {
	private static final long serialVersionUID = 1L;
	
	private List<ItemAttribute> titles = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> creators = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> subjects = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> descriptions = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> publishers = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> contributors = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> dates = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> types = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> formats = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> identifiers = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> sources = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> languages = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> relations = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> coverage = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> rights = new ArrayList<ItemAttribute>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.TITLE + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getTitles() {
		return titles;
	}
	
	public void setTitles(List<ItemAttribute> titles) {
		this.titles = titles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.CREATOR + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getCreators() {
		return creators;
	}
	
	public void setCreators(List<ItemAttribute> creators) {
		this.creators = creators;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.SUBJECT + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getSubjects() {
		return subjects;
	}
	
	public void setSubjects(List<ItemAttribute> subjects) {
		this.subjects = subjects;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.DESCRIPTION + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getDescriptions() {
		return descriptions;
	}
	
	public void setDescriptions(List<ItemAttribute> descriptions) {
		this.descriptions = descriptions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.PUBLISHER + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getPublishers() {
		return publishers;
	}
	
	public void setPublishers(List<ItemAttribute> publishers) {
		this.publishers = publishers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.CONTRIBUTOR + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getContributors() {
		return contributors;
	}
	
	public void setContributors(List<ItemAttribute> contributors) {
		this.contributors = contributors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.DATE + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getDates() {
		return dates;
	}
	
	public void setDates(List<ItemAttribute> dates) {
		this.dates = dates;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.TYPE + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getTypes() {
		return types;
	}
	
	public void setTypes(List<ItemAttribute> types) {
		this.types = types;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.FORMAT + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getFormats() {
		return formats;
	}
	
	public void setFormats(List<ItemAttribute> formats) {
		this.formats = formats;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.IDENTIFIER + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getIdentifiers() {
		return identifiers;
	}
	
	public void setIdentifiers(List<ItemAttribute> identifiers) {
		this.identifiers = identifiers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.SOURCE + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getSources() {
		return sources;
	}
	
	public void setSources(List<ItemAttribute> sources) {
		this.sources = sources;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.LANGUAGE + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getLanguages() {
		return languages;
	}

	public void setLanguages(List<ItemAttribute> languages) {
		this.languages = languages;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.RELATION + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getRelations() {
		return relations;
	}
	
	public void setRelations(List<ItemAttribute> relations) {
		this.relations = relations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.COVERAGE + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getCoverage() {
		return coverage;
	}
	
	public void setCoverage(List<ItemAttribute> coverage) {
		this.coverage = coverage;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.RIGHTS + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ItemAttribute> getRights() {
		return rights;
	}
	
	public void setRights(List<ItemAttribute> rights) {
		this.rights = rights;
	}
}
