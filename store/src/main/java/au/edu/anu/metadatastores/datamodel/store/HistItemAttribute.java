package au.edu.anu.metadatastores.datamodel.store;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="hist_item_attribute")
public class HistItemAttribute {
	private HistItemAttributeId id;
	private Item item;
	private Long parentAid;
	private String attrType;
	private String attrValue;
	private Boolean userUpdated;
	private Date lastModified;
	
	public HistItemAttribute() {
		
	}
	
	public HistItemAttribute(ItemAttribute attr, Date histDate) {
		HistItemAttributeId id = new HistItemAttributeId(attr.getAid(), histDate);
		this.id = id;
		this.item = attr.getItem();
		if (attr.getItemAttribute() != null) {
			this.parentAid = attr.getItemAttribute().getAid();
		}
		this.attrType = attr.getAttrType();
		this.attrValue = attr.getAttrValue();
		this.userUpdated = attr.getUserUpdated();
		this.lastModified = attr.getLastModified();
	}

	@Id
	public HistItemAttributeId getId() {
		return id;
	}
	
	public void setId(HistItemAttributeId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iid", nullable = false)
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	@Column(name="parent_aid")
	public Long getParentAid() {
		return parentAid;
	}
	
	public void setParentAid(Long parentAid) {
		this.parentAid = parentAid;
	}

	@Column(name = "attr_type", nullable = false)
	public String getAttrType() {
		return attrType;
	}
	
	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	@Column(name = "attr_value", nullable = false)
	public String getAttrValue() {
		return attrValue;
	}
	
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	@Column(name = "user_updated")
	public Boolean getUserUpdated() {
		return userUpdated;
	}
	
	public void setUserUpdated(Boolean userUpdated) {
		this.userUpdated = userUpdated;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified", length = 35)
	public Date getLastModified() {
		return lastModified;
	}
	
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
}
