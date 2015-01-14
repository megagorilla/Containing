package nhl.containing.server.network;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class TrainSpawnData extends AbstractMessage {

	public int trainID;
	public int[] containerIDs;
	public boolean shouldDespawn;

	public TrainSpawnData() {
	}

	/**
	 * Data to let the client spawn a train
	 * 
	 * @param ids
	 * @param containerID
	 */
	public TrainSpawnData(int id, int[] containerIDs, boolean shouldDespawn) {
		this.trainID = id;
		this.containerIDs = containerIDs;
		this.shouldDespawn = shouldDespawn;
	}
}
