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

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TimerAppListener
 * 
 * The Australian National University
 * 
 * Class that starts and stops timers when the web service is started and stopped
 * 
 * @author Genevieve Turner
 *
 */
public class TimerAppListener implements ServletContextListener {
	static final Logger LOGGER = LoggerFactory.getLogger(TimerAppListener.class);

	/**
	 * Stops the timers
	 * 
	 * @param contextEvent The event that has occurred
	 */
	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		TimerManager.getInstance().stop();
		LOGGER.info("Timers Stopped");
	}

	/**
	 * Starts the timers
	 * 
	 * @param contextEvent The event that has occurred
	 */
	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		TimerManager.getInstance().start();
		LOGGER.info("Timers Started");
	}

}
