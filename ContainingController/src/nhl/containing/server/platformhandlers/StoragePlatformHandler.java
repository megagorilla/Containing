package nhl.containing.server.platformhandlers;

import java.awt.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nhl.containing.server.pathfinding.AGV;
import nhl.containing.server.util.ControlHandler;

import com.jme3.math.Vector3f;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.StorageCranePickupData;

public class StoragePlatformHandler {
	private static StoragePlatformHandler instance;
	private HashMap<Integer, ParkingLocation> locations = new HashMap<Integer, ParkingLocation>();
	private HashMap<Integer, StorageUnit> storageUnits = new HashMap<Integer, StorageUnit>();
	
	public StoragePlatformHandler() {
		instance = this;
		init();
	}
	
	public static StoragePlatformHandler getInstance()
	{
		return instance;
	}
        
        public Storage getStorage(int i)
        {
            return storageUnits.get(i).storage;
        }
	
	private void init() {
		CreateParkingLots();
		CreateStorageUnits();
	}
	
	public void handleAGV(AGV agv)
	{
		ParkingLocation location = this.getFreeLocation();
		List<Vector3f> list = new ArrayList<Vector3f>();
		list.add(new Vector3f(303.5f, 0.0f, -778.5f));
		list.add(new Vector3f(303.5f, 0.0f, -760 + 40 * location.parkID));
		//267.5f - 535*a ,0,-760+ 40
		list.add(new Vector3f(267.5f, 0.0f, -760 + 40 * location.parkID));
		list.add(new Vector3f(location.location.x + 10, 0.0f, location.location.z));
		list.add(new Vector3f(location.location));
		ControlHandler.getInstance().sendAGV(agv.agvId, list, "storageLocation_" + location.id);
                
	}
	
	public void storageStack() {
		Container c = new Container();
		//c.getDeparture();
	}
	
	public ParkingLocation getFreeLocation() 
	{
		for(ParkingLocation location : locations.values())
		{
			if(!location.hasAGV)
			{
				location.hasAGV = true;
				return location;
			}
		}
		return null;
	}
	
	public ParkingLocation getLocation(int id)
	{
		return locations.get(id);
	}
	
	public void CreateParkingLots() {
		for (int i = 0; i < 39/*Parking lots*/; i++) {
			for(int j = 0; j < 6/*Truck amount*/; j++)
			{
				locations.put(i*6+j, new ParkingLocation(i*6+j, i, new Vector3f(267.5f - 22.5f, 0, (-768.2f + (20 / 6 + 0.3f)*j) + 40 * i)));
			}
		}
	}
	
	public void CreateStorageUnits() {
		for (int i = 0; i < 39; i++) {
			StorageUnit unit = new StorageUnit(new Storage(i),new Vector3f(110 ,0,-760+ 40*i));
			storageUnits.put(i, unit);
		}
	}
	
	public class ParkingLocation
	{
		public int id;
		public int parkID;
		public Vector3f location;
		public boolean hasAGV;
		
		public ParkingLocation(int id, Integer parkID, Vector3f location)
		{
			this.id = id;
			this.parkID = (parkID==null)?0:parkID;
			this.location = location;
			this.hasAGV = true;
		}
	}
	
	public class StorageUnit
	{
		public Storage storage;
		public Vector3f location;
		
		
		public StorageUnit(Storage storage, Vector3f location ) {
			this.storage = storage;
			this.location = location;
		}	
	}

	public void setParkingLocation(ParkingLocation location) {
		this.locations.put(location.id, location);
	}
}

