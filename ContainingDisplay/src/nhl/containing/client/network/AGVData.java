package nhl.containing.client.network;

import java.util.ArrayList;
import java.util.List;

import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;

@Serializable
public class AGVData
{
	public int id;
	public List<Vector3f> locations = new ArrayList<Vector3f>();
	
	public AGVData(){}

	public AGVData(int id, List<Vector3f> list)
	{
		this.id = id;
		this.locations = list;
	}
}
