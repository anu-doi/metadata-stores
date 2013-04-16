package au.edu.anu.metadatastores.services.timers;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerAppListener implements ServletContextListener {
	static final Logger LOGGER = LoggerFactory.getLogger(TimerAppListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		TimerManager.getInstance().stop();
		LOGGER.info("Timers Stopped");
	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		TimerManager.getInstance().start();
		LOGGER.info("Timers Started");
	}

}
