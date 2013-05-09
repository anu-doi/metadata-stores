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

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>DataCommonsTimerTask<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Timer task that processes the data commons harvest content</p>
 * 
 * @author Genevieve Turner
 *
 */
public class DataCommonsTimerTask extends TimerTask {
	static final Logger LOGGER = LoggerFactory.getLogger(DataCommonsTimerTask.class);
	private boolean isRunning = false;
	private DataCommonsService dcService = DataCommonsService.getSingleton();
	
	/**
	 * Execute the timer task
	 * 
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		if (!isRunning) {
			isRunning = true;
			try {
				dcService.processHarvestContent();
			}
			catch (Exception e) {
				LOGGER.error("Exception processing harvest content", e);
			}
			isRunning = false;
		}
	}

}
