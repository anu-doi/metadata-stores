package au.edu.anu.metadatastores.store.datacommons;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.metadatastores.store.dublincore.DublinCoreItem;

@Entity
@DiscriminatorValue(value="DATA_COMMONS")
public class DataCommonsItem extends DublinCoreItem {
	private static final long serialVersionUID = 1L;
}
