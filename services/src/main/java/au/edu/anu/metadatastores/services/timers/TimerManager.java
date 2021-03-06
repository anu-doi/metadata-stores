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

package au.edu.anu.metadatastores.services.timers;

import java.util.Timer;

import au.edu.anu.metadatastores.harvester.HarvestTimerManager;
import au.edu.anu.metadatastores.store.datacommons.DataCommonsTimerTask;
import au.edu.anu.metadatastores.store.digitalcollections.DigitalCollectionsTimerTask;

/**
 * <p>TimerManager<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Class that controls the timers used in the application</p>
 * 
 * @author Genevieve Turner
 *
 */
public class TimerManager {
	Timer timer = new Timer();
	DataCommonsTimerTask dctask = new DataCommonsTimerTask();
	DigitalCollectionsTimerTask digTask = new DigitalCollectionsTimerTask();
	int processInterval = 300000;
	
	private static TimerManager singleton_ = null;
	
	/**
	 * Constructor
	 */
	private TimerManager() {
		
	}
	
	/**
	 * Returns the TimerManager instance
	 * 
	 * @return The TimerManager instance
	 */
	public static TimerManager getInstance() {
		if (singleton_ == null) {
			singleton_ = new TimerManager();
		}
		return singleton_;
	}
	
	/**
	 * Set the timers
	 */
	public void start() {
		timer.schedule(dctask, 10000, processInterval);
		timer.schedule(digTask, 10000, processInterval);
		HarvestTimerManager.getInstance().start();
	}
	
	/**
	 * Stop the timers
	 */
	public void stop() {
		timer.cancel();
		HarvestTimerManager.getInstance().stop();
	}
}
