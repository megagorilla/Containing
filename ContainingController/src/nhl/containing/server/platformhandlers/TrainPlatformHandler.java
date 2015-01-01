package nhl.containing.server.platformhandlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import nhl.containing.server.ContainingServer;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.TrainCraneData;
import nhl.containing.server.network.TrainSpawnData;
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
 * Handles AGV's loading and unloading trains
 * @author rogier
 */
public class TrainPlatformHandler
{
	private static TrainPlatformHandler instance;
	private HashMap<Integer, TrainLocation> locations = new HashMap<Integer, TrainLocation>();
	private final float baseX = 380f, 
			baseY = 0f, 
			baseZ = -750f, 
			containerOffset = 25f;
	
	/**
	 * Initializes instance and the truckLocations (DO NOT CALL THIS OUTSIDE OF THE INIT METHOD OF THE SERVER)
	 */
	public TrainPlatformHandler()
	{
		instance = this;
		init();
	}
	
	/**
	 * Returns the instance of {@link TruckPlatformHandler}
	 * @return {@link #instance}
	 */
	public static TrainPlatformHandler getInstance()
	{
		return instance;
	}
	
	private void init()
	{
		for(int i = 0; i < 20; i++)
		{
			locations.put(i, new TrainLocation(i, new Vector3f(baseX, baseY, baseZ + containerOffset * i)));
		}
	}
	
	/**
	 * Handles the agv when it arrives at location 'a2'
	 * @param agv
	 */
	public void handleAGV(AGV agv)
	{
		TrainLocation location = this.getTrainLocation();
		List<Vector3f> list = new ArrayList<Vector3f>();
		list.add(new Vector3f(353.5f, 0, -778.5f)); //TODO: proper coords
		list.add(new Vector3f(353.5f, 0, location.location.z));
		list.add(new Vector3f(location.location));
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
		TrainLocation location = this.locations.get(i);
		List<Vector3f> list = new ArrayList<Vector3f>();
		list.add(new Vector3f(353.5f, 0, -778.5f)); //TODO: proper coords
		list.add(new Vector3f(353.5f, 0, location.location.z));
		list.add(new Vector3f(location.location));
		ControlHandler.getInstance().sendAGV(agv.agvId, list, ""); //TODO: string
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
		TrainLocation location = locations.get(i);
		List<Vector3f> list = new ArrayList<Vector3f>();
		list.add(new Vector3f(location.location));
		list.add(new Vector3f(353.5f, 0, location.location.z)); //TODO: proper coords
		list.add(new Vector3f(353.5f, 0, -250));
		ControlHandler.getInstance().sendAGV(agv.agvId, list, "a3"); // TODO: proper destination string
		location.needsAGV = false;
		locations.put(i, location);
	}
	
	public void spawnTrain(int carts)
	{
		TrainLocation[] cartLocations = new TrainLocation[carts];
		for(int i = carts; i > 0; i--)
		{
			TrainLocation location = getFreeTrainLocation();
			location.isAvailable = false;
			location.needsAGV = true;
			cartLocations[i] = location;
			locations.put(location.id, location);
		}
		TrainSpawnData data = new TrainSpawnData(
				Arrays.stream(cartLocations).mapToInt(loc -> loc.id).toArray(), 
				new int[carts], false);
		ConnectionManager.sendCommand(data);
	}
	
	public void checkRequirements()
	{
		for(TrainLocation location : locations.values())
		{
			if(location.needsAGV)
				ControlHandler.getInstance().requestAGVToTrain(location.id);
		}
	}
	
	/**
	 * gets a trainLocation that requires an AGV and has a container
	 * @return {@link TrainLocation}
	 */
	private TrainLocation getTrainLocation()
	{
		for(TrainLocation location : locations.values())
		{
			if(location.needsAGV)
				return location;
		}
		return locations.get(0);
	}
	
	private TrainLocation getFreeTrainLocation()
	{
		for(TrainLocation location : locations.values())
		{
			if(location.isAvailable)
				return location;
		}
		return null;
	}
	
	/**
	 * creates an train location object with various variables.
	 * @author Arjen
	 */
	public class TrainLocation
	{
		public int id;
		public Vector3f location;
		public boolean needsAGV;
		public boolean isAvailable;
		
		public TrainLocation(int id, Vector3f location)
		{
			this.id = id;
			this.location = location;
			this.needsAGV = false;
			this.isAvailable = true;
		}
	}

	public void getContainerFromTrain(int agvId, int craneId) 
	{
		List<Vector3f> list = new ArrayList<Vector3f>();
		list.add(new Vector3f());
		list.add(new Vector3f(1, 0, 0));
		ConnectionManager.sendCommand(new TrainCraneData(agvId, craneId, 0));
		MotionPath path = new MotionPath();
		for(Vector3f v : list)
			path.addWayPoint(v);
		path.setCurveTension(0.0f);
		path.addListener(new CMotionPathListener());
		
		ServerSpatial spatial = new ServerSpatial(AGVHandler.getInstance().getAGV(agvId), "trainLocation_" + String.valueOf(craneId) + "_loaded");
        ContainingServer.getRoot().attachChild(spatial);

		MotionEvent motionControl = new MotionEvent(spatial, path);
        motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl.setRotation(new Quaternion().fromAngleNormalAxis(0, Vector3f.UNIT_Y));
        motionControl.setInitialDuration(30f);
        motionControl.setSpeed(1f);
        motionControl.play();
	}
}
