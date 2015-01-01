package nhl.containing.server.network;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class StorageCranePickupData extends AbstractMessage 
{
	public float x, y, z;
        public int i;

	public StorageCranePickupData(){}
	
	public StorageCranePickupData(float x, float y, float z, int i)
	{
		this.x = x;
		this.y = y;
		this.z = z;
                this.i = i;
	}
}
