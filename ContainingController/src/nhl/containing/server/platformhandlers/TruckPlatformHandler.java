package nhl.containing.server.platformhandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nhl.containing.server.pathfinding.AGV;
import nhl.containing.server.util.ControlHandler;

import com.jme3.math.Vector3f;

public class TruckPlatformHandler
{
	private static TruckPlatformHandler instance;
	private HashMap<Integer, TruckLocation> locations = new HashMap<Integer, TruckLocation>();
	
	public TruckPlatformHandler()
	{
		instance = this;
		init();
	}
	
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
	 * FOR TESTING PURPOSES ONLY
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
	
	private TruckLocation getTruckLocation()
	{
		for(TruckLocation location : locations.values())
		{
			if(!location.hasAGV && location.hasContainer)
				return location;
		}
		return locations.get(0);
	}
	
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
