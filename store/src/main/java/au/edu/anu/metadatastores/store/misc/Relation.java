package au.edu.anu.metadatastores.store.misc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="relation")
public class Relation {
	private Long iid;
	private String itemTitle;
	private String relationValue;
	private Long relatedIid;
	private String relatedItemTitle;

	public Relation() {
		
	}
	
	public Relation(Long iid, String relationValue, Long relatedIid) {
		this.iid = iid;
		this.relationValue = relationValue;
		this.relatedIid = relatedIid;
	}
	
	@XmlElement(name="iid")
	public Long getIid() {
		return iid;
	}
	
	public void setIid(Long iid) {
		this.iid = iid;
	}
	
	@XmlElement(name="title")
	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	
	@XmlElement(name="relation-value")
	public String getRelationValue() {
		return relationValue;
	}
	
	public void setRelationValue(String relationValue) {
		this.relationValue = relationValue;
	}
	
	@XmlElement(name="related-iid")
	public Long getRelatedIid() {
		return relatedIid;
	}
	
	public void setRelatedIid(Long relatedIid) {
		this.relatedIid = relatedIid;
	}

	@XmlElement(name="related-title")
	public String getRelatedItemTitle() {
		return relatedItemTitle;
	}

	public void setRelatedItemTitle(String relatedItemTitle) {
		this.relatedItemTitle = relatedItemTitle;
	}
}
