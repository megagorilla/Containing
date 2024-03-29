package nhl.containing.server.network;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class TruckSpawnData extends AbstractMessage {

	public int truckID;
	public int containerID;
	public boolean shouldDespawn;

	public TruckSpawnData() {
	}

	/**
	 * Data to let the client spawn a truck
	 * 
	 * @param truckID
	 * @param containerID
	 */
	public TruckSpawnData(int truckID, int containerID, boolean shouldDespawn) {
		this.truckID = truckID;
		this.containerID = containerID;
		this.shouldDespawn = shouldDespawn;
	}
}
