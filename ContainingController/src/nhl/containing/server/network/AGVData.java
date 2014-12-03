package nhl.containing.server.network;

import java.util.ArrayList;
import java.util.List;

import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;

@Serializable
public class AGVData
{
	public int id;
	public List<Vector3f> locations = new ArrayList<Vector3f>();
	
	/**
	 * Empty constructor for serializer
	 */
	public AGVData(){}

	/**
	 * Data for the AGV to move from 1 location to the next with a list of vectors
	 * @param id
	 * @param list
	 */
	public AGVData(int id, List<Vector3f> list)
	{
		this.id = id;
		this.locations = list;
	}
}
