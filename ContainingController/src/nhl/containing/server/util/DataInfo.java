package nhl.containing.server.util;

/**
 * Data for the Application (TODO: Fill this data)
 * @author Arjen
 *
 */
public class DataInfo
{
	String[] data = new String[6];
	
	public void fillData(String train, String truck, String seaship, String ship, String storage, String others)
	{
		data[0] = train;
		data[1] = truck;
		data[2] = seaship;
		data[3] = ship;
		data[4] = storage;
		data[5] = others;
	}
}
