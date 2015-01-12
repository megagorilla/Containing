package nhl.containing.client.network;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class TrainCraneData extends AbstractMessage {
	public int craneID;
	public int agvID;
	public int containerID;
	
	public TrainCraneData(){}

	/**
	 * Data for the AGV to move from 1 location to the next with a list of vectors
	 * @param id
	 * @param list
	 */
	public TrainCraneData(int agvID, int craneID, int containerID)
	{
		this.agvID = agvID;
		this.craneID = craneID;
		this.containerID = containerID;
	}
}