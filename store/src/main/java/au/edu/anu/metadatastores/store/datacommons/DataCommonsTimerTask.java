package au.edu.anu.metadatastores.store.datacommons;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataCommonsTimerTask extends TimerTask {
	static final Logger LOGGER = LoggerFactory.getLogger(DataCommonsTimerTask.class);
	private boolean isRunning = false;
	private DataCommonsService dcService = DataCommonsService.getSingleton();
	
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
