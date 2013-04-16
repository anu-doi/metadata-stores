package au.edu.anu.metadatastores.store.publication;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Filter;

import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.ItemAttribute;
import au.edu.anu.metadatastores.datamodel.store.ext.StoreAttributes;

@Entity
@DiscriminatorValue(value="PUBLICATION")
public class PublicationItem extends Item {
	private static final long serialVersionUID = 1L;
	
	private List<ItemAttribute> ariesIds = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> types = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> titles = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> years = new ArrayList<ItemAttribute>();
	//TODO authors
	private List<ItemAttribute> publicationNames = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> categories = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> isbns = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> issns = new ArrayList<ItemAttribute>();
	private List<ItemAttribute> anzforSubjects = new ArrayList<ItemAttribute>() ;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.ARIES_ID + "'")
	public List<ItemAttribute> getAriesIds() {
		return ariesIds;
	}

	public void setAriesIds(List<ItemAttribute> ariesIds) {
		this.ariesIds = ariesIds;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.TYPE + "'")
	public List<ItemAttribute> getTypes() {
		return types;
	}

	public void setTypes(List<ItemAttribute> types) {
		this.types = types;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.TITLE + "'")
	public List<ItemAttribute> getPublicationTitles() {
		return titles;
	}

	public void setPublicationTitles(List<ItemAttribute> titles) {
		this.titles = titles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.YEAR + "'")
	public List<ItemAttribute> getYears() {
		return years;
	}

	public void setYears(List<ItemAttribute> years) {
		this.years = years;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.PUBLICATION_NAME + "'")
	public List<ItemAttribute> getPublicationNames() {
		return publicationNames;
	}

	public void setPublicationNames(List<ItemAttribute> publicationNames) {
		this.publicationNames = publicationNames;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.CATEGORY + "'")
	public List<ItemAttribute> getCategories() {
		return categories;
	}

	public void setCategories(List<ItemAttribute> categories) {
		this.categories = categories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.ISBN + "'")
	public List<ItemAttribute> getIsbns() {
		return isbns;
	}

	public void setIsbns(List<ItemAttribute> isbns) {
		this.isbns = isbns;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.ISSN + "'")
	public List<ItemAttribute> getIssns() {
		return issns;
	}

	public void setIssns(List<ItemAttribute> issns) {
		this.issns = issns;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@Filter(name="attributes", condition="attr_type = '"+ StoreAttributes.FOR_SUBJECT + "'")
	public List<ItemAttribute> getAnzforSubjects() {
		return anzforSubjects;
	}

	public void setAnzforSubjects(List<ItemAttribute> anzforSubjects) {
		this.anzforSubjects = anzforSubjects;
	}
}
