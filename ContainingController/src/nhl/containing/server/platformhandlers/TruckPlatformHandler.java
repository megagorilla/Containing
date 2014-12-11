package nhl.containing.server.platformhandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nhl.containing.server.ContainingServer;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.TruckCraneData;
import nhl.containing.server.network.TruckSpawnData;
import nhl.containing.server.pathfinding.AGV;
import nhl.containing.server.pathfinding.AGVHandler;
import nhl.containing.server.pathfinding.CMotionPathListener;
import nhl.containing.server.util.ControlHandler;
import nhl.containing.server.util.ServerSpatial;

import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.Quaternion;
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
		location.needsAGV = false;
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
		location.needsAGV = false;
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
		location.needsAGV = false;
		locations.put(i, location);
	}
	
	public void spawnTruck()
	{
		TruckLocation location = getFreeTruckLocation();
		location.isAvailable = false;
		location.needsAGV = true;
		locations.put(location.id, location);
		TruckSpawnData data = new TruckSpawnData(location.id, 0);
		ConnectionManager.sendCommand(data);
	}
	
	public void checkRequirements()
	{
		for(TruckLocation location : locations.values())
		{
			if(location.needsAGV)
				ControlHandler.getInstance().requestAGVToTrucks(location.id);
		}
	}
	
	/**
	 * gets a truckLocation that requires an AGV and has a container
	 * @return {@link TruckLocation}
	 */
	private TruckLocation getTruckLocation()
	{
		for(TruckLocation location : locations.values())
		{
			if(location.needsAGV)
				return location;
		}
		return locations.get(0);
	}
	
	private TruckLocation getFreeTruckLocation()
	{
		for(TruckLocation location : locations.values())
		{
			if(location.isAvailable)
				return location;
		}
		return null;
	}
	
	/**
	 * creates an trucklocation object with various variables.
	 * @author Arjen
	 */
	public class TruckLocation
	{
		public int id;
		public Vector3f location;
		public boolean needsAGV;
		public boolean isAvailable;
		
		public TruckLocation(int id, Vector3f location)
		{
			this.id = id;
			this.location = location;
			this.needsAGV = false;
			this.isAvailable = true;
		}
	}

	public void getContainerFromTruck(int agvId, int craneId) 
	{
		List<Vector3f> list = new ArrayList<Vector3f>();
		list.add(new Vector3f());
		list.add(new Vector3f(1, 0, 0));
		ConnectionManager.sendCommand(new TruckCraneData(agvId, craneId, 0));
		MotionPath path = new MotionPath();
		for(Vector3f v : list)
			path.addWayPoint(v);
		path.setCurveTension(0.0f);
		path.addListener(new CMotionPathListener());
		
		ServerSpatial spatial = new ServerSpatial(AGVHandler.getInstance().getAGV(agvId), "truckLocation_" + String.valueOf(craneId) + "_loaded");
        ContainingServer.getRoot().attachChild(spatial);

		MotionEvent motionControl = new MotionEvent(spatial, path);
        motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl.setRotation(new Quaternion().fromAngleNormalAxis(0, Vector3f.UNIT_Y));
        motionControl.setInitialDuration(30f);
        motionControl.setSpeed(1f);  
        motionControl.play();
	}
}
