package au.edu.anu.metadatastores.store.digitalcollections;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.metadatastores.store.dublincore.DublinCoreItem;

@Entity
@DiscriminatorValue(value="DIGITAL_COLLECTIONS")
public class DigitalCollectionsItem extends DublinCoreItem {
	private static final long serialVersionUID = 1L;
}
