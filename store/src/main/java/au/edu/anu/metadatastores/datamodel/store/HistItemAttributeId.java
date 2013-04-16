package au.edu.anu.metadatastores.datamodel.store;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class HistItemAttributeId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long aid;
	private Date histDatetime;

	public HistItemAttributeId() {
		
	}
	
	public HistItemAttributeId(Long aid, Date histDatetime) {
		this.aid = aid;
		this.histDatetime = histDatetime;
	}

	@Column(name="aid")
	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	@Column(name="hist_datetime")
	public Date getHistDatetime() {
		return histDatetime;
	}

	public void setHistDatetime(Date histDatetime) {
		this.histDatetime = histDatetime;
	}
	
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof HistItemAttributeId))
			return false;
		HistItemAttributeId castOther = (HistItemAttributeId) other;
		
		return (this.getAid().equals(castOther.getAid())
				&& ((this.getHistDatetime() == castOther.getHistDatetime()) || (this.getHistDatetime() != null 
					&& this.getHistDatetime().equals(castOther.getHistDatetime()))));
	}
	
	public int hashCode() {
		int result = 17;
		
		result = 37 * result + (int) this.getAid().intValue();
		result = 37 + result
				+ (getHistDatetime() == null ? 0 : this.getHistDatetime().hashCode());
		return result;
	}
}
