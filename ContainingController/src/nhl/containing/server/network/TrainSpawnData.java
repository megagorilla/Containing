package nhl.containing.server.network;

import com.jme3.network.AbstractMessage;

public class TrainSpawnData extends AbstractMessage
{
	public int trainID;
	public int containerID;
	public boolean shouldDespawn;
	
	public TrainSpawnData(){}

	/**
	 * Data to let the client spawn a train
	 * @param trainID
	 * @param containerID
	 */
	public TrainSpawnData(int trainID, int containerID, boolean shouldDespawn)
	{
		this.trainID = trainID;
		this.containerID = containerID;
		this.shouldDespawn = shouldDespawn;
	}
}
