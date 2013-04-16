package au.edu.anu.metadatastores.harvester;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.harvester.Location;

public class HarvestTimerTask extends TimerTask {
	static final Logger LOGGER = LoggerFactory.getLogger(HarvestTimerTask.class);
	
	Harvest harvest = new Harvest();
	private Location harvestLocation = null;
	
	public HarvestTimerTask(Location harvestLocation) {
		this.harvestLocation = harvestLocation;
	}

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

	public Location getLocation() {
		return harvestLocation;
	}
}
