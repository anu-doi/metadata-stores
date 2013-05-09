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

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.harvester.Location;

/**
 * <p>HarvestTimerManager<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Manager class that creates timers to that the oai pmh feeds are harvested</p>
 * 
 * @author Genevieve Turner
 *
 */
public class HarvestTimerManager {
	static final Logger LOGGER = LoggerFactory.getLogger(HarvestTimerManager.class);
	private Timer timer = new Timer();
	private static Map<String, HarvestTimerTask> harvestTimers = new HashMap<String, HarvestTimerTask>();
	private static HarvestTimerManager singleton_ = null;
	
	/**
	 * Content
	 */
	private HarvestTimerManager() {
		
	}
	
	/**
	 * Returns the instance of the HarvestTimerManager
	 * 
	 * @return The HarvestTimerManager object
	 */
	public static HarvestTimerManager getInstance() {
		if (singleton_ == null) {
			singleton_ = new HarvestTimerManager();
		}
		return singleton_;
	}
	
	/**
	 * Start the timers
	 */
	public void start() {
		List<Location> locations = HarvestContentService.getSingleton().getHarvestLocations();
		for (Location location : locations) {
			HarvestTimerTask timerTask = harvestTimers.get(location.getSystem());
			if (!harvestTimers.containsKey(location.getSystem())) {
				timerTask = new HarvestTimerTask(location);
				harvestTimers.put(location.getSystem(), timerTask);
			}
			scheduleTask(location, timerTask);
		}
	}
	
	/**
	 * Stop the timers
	 */
	public void stop() {
		timer.cancel();
	}
	
	/**
	 * Stop the timers for a specific location
	 * 
	 * @param location The location to stop the timers for
	 */
	public void stopSpecificTask(Location location) {
		HarvestTimerTask task = harvestTimers.get(location.getSystem());
		task.cancel();
	}
	
	/**
	 * Reschedule the timers for the given location
	 * 
	 * @param location The location to reschedule the timers for
	 */
	public void rescheduleTask(Location location) {
		HarvestTimerTask task = harvestTimers.get(location.getSystem());
		task.cancel();
		
		scheduleTask(location, task);
	}
	
	/**
	 * Schedule the execute of the timers
	 * 
	 * @param location The location to get the schedule details from
	 * @param task The timer task to schedule
	 */
	private void scheduleTask(Location location, HarvestTimerTask task) {
		Date date = location.getOrigHarvestDate();
		Long frequency = location.getHarvestFreq();
		if (date != null && frequency != null) {
			LOGGER.debug("Date: {}, Frequency: {}", date, frequency);
			//date.
			Date nextDate = ensureAfterCurrentDateTime(date, frequency);
			timer.scheduleAtFixedRate(task, nextDate, frequency);
		}
	}
	
	/**
	 * Get the closest time to harvest the content for
	 * 
	 * @param date The date to use as a reference for the scheduling
	 * @param frequency The frequency of the harvest
	 * @return The next date/time after the current date/time for which the data should be harvested
	 */
	private Date ensureAfterCurrentDateTime(Date date, Long frequency) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Date now = new Date();
		
		while (cal.getTimeInMillis() < now.getTime()) {
			cal.add(Calendar.MILLISECOND, frequency.intValue());
		}
		
		return cal.getTime();
	}
}
