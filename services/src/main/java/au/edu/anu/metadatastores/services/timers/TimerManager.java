package au.edu.anu.metadatastores.services.timers;

import java.util.Timer;

import au.edu.anu.metadatastores.harvester.HarvestTimerManager;
import au.edu.anu.metadatastores.store.datacommons.DataCommonsTimerTask;

public class TimerManager {
	Timer timer = new Timer();
	DataCommonsTimerTask dctask = new DataCommonsTimerTask();
	
	private static TimerManager singleton_ = null;
	
	private TimerManager() {
		
	}
	
	public static TimerManager getInstance() {
		if (singleton_ == null) {
			singleton_ = new TimerManager();
		}
		return singleton_;
	}
	
	public void start() {
		timer.schedule(dctask, 10000, 300000);
		HarvestTimerManager.getInstance().start();
	}
	
	public void stop() {
		timer.cancel();
		HarvestTimerManager.getInstance().stop();
	}
}
