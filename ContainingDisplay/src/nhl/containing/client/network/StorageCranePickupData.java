package nhl.containing.client.network;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class StorageCranePickupData extends AbstractMessage 
{
	public int craneID;
	public float x, y, z;
    public int i, agvId;
    
	public StorageCranePickupData(){}
	
	public StorageCranePickupData(int craneID, float x, float y, float z, int i, int agvId)
	{
		this.craneID = craneID;
		this.x = x;
		this.y = y;
		this.z = z;
        this.i = i;
        this.agvId = agvId;
	}
}