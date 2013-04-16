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

public class HarvestTimerManager {
	static final Logger LOGGER = LoggerFactory.getLogger(HarvestTimerManager.class);
	private Timer timer = new Timer();
	private static Map<String, HarvestTimerTask> harvestTimers = new HashMap<String, HarvestTimerTask>();
	private static HarvestTimerManager singleton_ = null;
	
	private HarvestTimerManager() {
		
	}
	
	public static HarvestTimerManager getInstance() {
		if (singleton_ == null) {
			singleton_ = new HarvestTimerManager();
		}
		return singleton_;
	}
	
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
	
	public void stop() {
		timer.cancel();
	}
	
	public void stopSpecificTask(Location location) {
		HarvestTimerTask task = harvestTimers.get(location.getSystem());
		task.cancel();
	}
	
	public void rescheduleTask(Location location) {
		HarvestTimerTask task = harvestTimers.get(location.getSystem());
		task.cancel();
		
		scheduleTask(location, task);
	}
	
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
