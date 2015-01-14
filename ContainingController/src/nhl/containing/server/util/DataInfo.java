package nhl.containing.server.util;

import nhl.containing.server.platformhandlers.StoragePlatformHandler;
import nhl.containing.server.platformhandlers.TruckPlatformHandler;

/**
 * Data for the Application (TODO: Fill this data)
 * 
 * @author Arjen
 *
 */
public class DataInfo {

	String[] data = new String[6];

	public void fillData(String train, String truck, String seaship, String ship, String storage, String others) {
		data[0] = train;
		data[1] = TruckPlatformHandler.getInstance().getContainerAmount();
		data[2] = seaship;
		data[3] = ship;
		data[4] = StoragePlatformHandler.getInstance().getContainerAmount();
		data[5] = others;
	}
}
