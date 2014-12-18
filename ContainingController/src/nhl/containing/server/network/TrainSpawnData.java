package nhl.containing.server.network;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class TrainSpawnData extends AbstractMessage
{
	public int[] trainIDs;
	public int[] containerIDs;
	public boolean shouldDespawn;
	
	public TrainSpawnData(){}

	/**
	 * Data to let the client spawn a train
	 * @param ids
	 * @param containerID
	 */
	public TrainSpawnData(int[] ids, int[] containerIDs, boolean shouldDespawn)
	{
		this.trainIDs = ids;
		this.containerIDs = containerIDs;
		this.shouldDespawn = shouldDespawn;
	}
}
