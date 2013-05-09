/*******************************************************************************
 * Australian National University Metadata Stores
 * Copyright (C) 2013  The Australian National University
 * 
 * This file is part of Australian National University Metadata Stores.
 * 
 * Australian National University Metadata Stores is free software: you
 * can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package au.edu.anu.metadatastores.store.datacommons;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.metadatastores.store.dublincore.DublinCoreItem;

/**
 * DataCommonsItem
 * 
 * The Australian National University
 * 
 * Entity class for the Data Commons system for Items
 * 
 * @author Genevieve Turner
 *
 */
/**
 * <p>DataCommonsItem<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Entity class for the Data Commons system for Items</p>
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue(value="DATA_COMMONS")
public class DataCommonsItem extends DublinCoreItem {
	private static final long serialVersionUID = 1L;
}
