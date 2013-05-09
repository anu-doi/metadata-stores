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

package au.edu.anu.metadatastores.harvester;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.harvester.Location;

/**
 * <p>HarvestTimerTask<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>TimerTask for harvesting content</p>
 * 
 * @author Genevieve Turner
 *
 */
public class HarvestTimerTask extends TimerTask {
	static final Logger LOGGER = LoggerFactory.getLogger(HarvestTimerTask.class);
	
	Harvest harvest = new Harvest();
	private Location harvestLocation = null;
	
	/**
	 * Constructor
	 * 
	 * @param harvestLocation The location with which this task is associated
	 */
	public HarvestTimerTask(Location harvestLocation) {
		this.harvestLocation = harvestLocation;
	}

	/**
	 * Run class
	 */
	@Override
	public void run() {
		if (harvestLocation != null) {
			try {
				harvest.harvest(harvestLocation);
			}
			catch (Exception e) {
				LOGGER.error("Error harvesting data", e);
			}
		}
	}

	/**
	 * Get the location associated with this timer
	 * 
	 * @return The location
	 */
	public Location getLocation() {
		return harvestLocation;
	}
}
