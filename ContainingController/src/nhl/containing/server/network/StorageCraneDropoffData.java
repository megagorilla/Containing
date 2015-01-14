package nhl.containing.server.network;

import com.jme3.math.Vector3f;
import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class StorageCraneDropoffData extends AbstractMessage {

	public Vector3f containerLoc;
	public int craneID;
	public int i, agvId;

	public StorageCraneDropoffData() {
	}

	public StorageCraneDropoffData(Vector3f containerLoc, int craneID, int i, int agvId) {
		this.containerLoc = containerLoc;
		this.craneID = craneID;
		this.i = i;
		this.agvId = agvId;
	}
}
