package nhl.containing.client.network;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class StorageCranePickupData extends AbstractMessage 
{
	public float x, y, z;

	public StorageCranePickupData(){}
	
	public StorageCranePickupData(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
}