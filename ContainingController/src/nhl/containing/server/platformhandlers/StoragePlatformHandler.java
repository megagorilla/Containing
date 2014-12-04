package nhl.containing.server.platformhandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nhl.containing.server.pathfinding.AGV;
import nhl.containing.server.util.ControlHandler;

import com.jme3.math.Vector3f;

public class StoragePlatformHandler {
	private static StoragePlatformHandler instance;
	private HashMap<Integer, ParkingLocation> locations = new HashMap<Integer, ParkingLocation>();
	
	public StoragePlatformHandler() {
		instance = this;
		init();
	}
	
	public static StoragePlatformHandler getInstance()
	{
		return instance;
	}
	
	private void init() {
		for (int i = 0; i < 39/*Parking lots*/; i++) {
			for(int j = 0; j < 6/*Truck amount*/; j++)
			{
				locations.put(i*6+j, new ParkingLocation(i*6+j, i, new Vector3f(267.5f - 22.5f, 0, (-768.2f + (20 / 6 + 0.3f)*j) + 40 * i)));
			}
		}
	}
	
	public void handleAGV(AGV agv)
	{
		ParkingLocation location = this.getLocation();
		List<Vector3f> list = new ArrayList<Vector3f>();
		list.add(new Vector3f(303.5f, 0.0f, -778.5f));
		list.add(new Vector3f(303.5f, 0.0f, -760 + 40 * location.parkID));
		//267.5f - 535*a ,0,-760+ 40
		list.add(new Vector3f(267.5f, 0.0f, -760 + 40 * location.parkID));
		list.add(new Vector3f(location.location.x + 10, 0.0f, location.location.z));
		list.add(new Vector3f(location.location));
		ControlHandler.getInstance().sendAGV(agv.agvId, list);
		location.hasAGV = true;
		locations.put(location.id, location);
	}
	
	public ParkingLocation getLocation() {
		
		return locations.get(17);
	}
	
	public class ParkingLocation{
		public int id;
		public int parkID;
		public Vector3f location;
		public boolean hasAGV;
		
		public ParkingLocation(int id, int parkID, Vector3f location)
		{
			this.id = id;
			this.parkID = parkID;
			this.location = location;
			this.hasAGV = true;
		}
	}
}
