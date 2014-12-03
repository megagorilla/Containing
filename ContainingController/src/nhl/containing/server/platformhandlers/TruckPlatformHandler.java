package nhl.containing.server.platformhandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nhl.containing.server.pathfinding.AGV;
import nhl.containing.server.util.ControlHandler;

import com.jme3.math.Vector3f;

/**
 * Handles AGV's that arrive at the 'a2' position
 * @author Arjen
 */
public class TruckPlatformHandler
{
	private static TruckPlatformHandler instance;
	private HashMap<Integer, TruckLocation> locations = new HashMap<Integer, TruckLocation>();
	
	/**
	 * Initializes instance and the truckLocations (DO NOT CALL THIS OUTSIDE OF THE INIT METHOD OF THE SERVER)
	 */
	public TruckPlatformHandler()
	{
		instance = this;
		init();
	}
	
	/**
	 * Returns the instance of {@link TruckPlatformHandler}
	 * @return {@link #instance}
	 */
	public static TruckPlatformHandler getInstance()
	{
		return instance;
	}
	
	private void init()
	{
		for(int i = 0; i < 20; i++)
		{
			locations.put(i, new TruckLocation(i, new Vector3f(380f, 0f, -750f + 25f * i)));
		}
	}
	
	/**
	 * Handles the agv when it arrives at location 'a2'
	 * @param agv
	 */
	public void handleAGV(AGV agv)
	{
		TruckLocation location = this.getTruckLocation();
		List<Vector3f> list = new ArrayList<Vector3f>();
		list.add(new Vector3f(353.5f, 0, -778.5f));
		list.add(new Vector3f(353.5f, 0, location.location.z));
		list.add(new Vector3f(location.location));
		ControlHandler.getInstance().sendAGV(agv.agvId, list);
		location.hasAGV = true;
		locations.put(location.id, location);
	}
	
	/**
	 * FOR TESTING PURPOSES ONLY!
	 * @param agv
	 * @param i
	 */
	public void handleAGV(AGV agv, int i)
	{
		//TruckLocation location = this.getTruckLocation();
		TruckLocation location = this.locations.get(i);
		List<Vector3f> list = new ArrayList<Vector3f>();
		list.add(new Vector3f(353.5f, 0, -778.5f));
		list.add(new Vector3f(353.5f, 0, location.location.z));
		list.add(new Vector3f(location.location));
		ControlHandler.getInstance().sendAGV(agv.agvId, list);
		location.hasAGV = true;
		locations.put(location.id, location);
	}
	
	/**
	 * Handles agv with certain ID when it arrives at the 'a3' location
	 * @param agv
	 * @param i
	 */
	public void sendAGVToStorage(AGV agv, int i)
	{
		TruckLocation location = locations.get(i);
		List<Vector3f> list = new ArrayList<Vector3f>();
		list.add(new Vector3f(location.location));
		list.add(new Vector3f(353.5f, 0, location.location.z));
		list.add(new Vector3f(353.5f, 0, -250));
		ControlHandler.getInstance().sendAGV(agv.agvId, list, "a3");
		location.hasAGV = false;
		if(location.hasContainer)
			location.hasContainer = false;
		else
			location.hasContainer = true;
		locations.put(i, location);
	}
	
	/**
	 * gets a truckLocation that requires an AGV and has a container
	 * @return {@link TruckLocation}
	 */
	private TruckLocation getTruckLocation()
	{
		for(TruckLocation location : locations.values())
		{
			if(!location.hasAGV && location.hasContainer)
				return location;
		}
		return locations.get(0);
	}
	
	/**
	 * creates an trucklocation object with various variables.
	 * @author Arjen
	 */
	public class TruckLocation
	{
		public int id;
		public Vector3f location;
		public boolean hasAGV;
		public boolean hasContainer;
		
		public TruckLocation(int id, Vector3f location)
		{
			this.id = id;
			this.location = location;
			this.hasAGV = false;
			this.hasContainer = false;
		}
	}
}
