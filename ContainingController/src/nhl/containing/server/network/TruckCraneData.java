package nhl.containing.server.network;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class TruckCraneData extends AbstractMessage
{
	public int craneID;
	public int agvID;
	public int truckID;
	public boolean fromAGV;
	
	public TruckCraneData(){}

	/**
	 * Data for the AGV to move from 1 location to the next with a list of vectors
	 * @param id
	 * @param list
	 */
	public TruckCraneData(int agvID, int craneID, int truckID, boolean fromAGV)
	{
		this.agvID = agvID;
		this.craneID = craneID;
		this.truckID = truckID;
		this.fromAGV = fromAGV;
	}
}