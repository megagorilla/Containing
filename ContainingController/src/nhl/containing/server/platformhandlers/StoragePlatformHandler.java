package nhl.containing.server.platformhandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nhl.containing.server.ContainingServer;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.StorageCranePickupData;
import nhl.containing.server.pathfinding.AGV;
import nhl.containing.server.pathfinding.AGVHandler;
import nhl.containing.server.util.ControlHandler;
import nhl.containing.server.util.ServerSpatial;

import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.MotionPathListener;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

public class StoragePlatformHandler {

	public final int maxStorageLocations = 39;
	private static StoragePlatformHandler instance;
	private HashMap<Integer, ParkingLocation> locations = new HashMap<Integer, ParkingLocation>();
	private HashMap<Integer, StorageUnit> storageUnits = new HashMap<Integer, StorageUnit>();

	public StoragePlatformHandler() {
		instance = this;
		init();
	}

	public static StoragePlatformHandler getInstance() {
		return instance;
	}

	public Storage getStorage(int i) {
		return storageUnits.get(i).storage;
	}

	private void init() {
		CreateParkingLots();
		CreateStorageUnits();
	}

	public void handleAGV(AGV agv) {
		ParkingLocation location = this.getFreeLocation();
		List<Vector3f> list = new ArrayList<Vector3f>();
		list.add(new Vector3f(303.5f, 0.0f, -778.5f));
		list.add(new Vector3f(303.5f, 0.0f, -760 + 40 * location.parkID));
		list.add(new Vector3f(267.5f, 0.0f, -760 + 40 * location.parkID));
		list.add(new Vector3f(location.location.x + 10, 0.0f, location.location.z));
		list.add(new Vector3f(location.location));
		ControlHandler.getInstance().sendAGV(agv.agvId, list, "storageLocation_" + location.id);

	}

	public void storageStack() {
	}

	public ParkingLocation getFreeLocation() {
		for (ParkingLocation location : locations.values()) {
			if (!location.hasAGV) {
				location.hasAGV = true;
				return location;
			}
		}
		return null;
	}

	public void update(float tpf) {
		for (StorageUnit storage : storageUnits.values()) {
			if (storage.isFree) {
				if (!storage.containersToOffload.isEmpty()) {
					storage.isFree = false;
					ConnectionManager.sendCommand(storage.containersToOffload.get(0));
					MotionPath path = new MotionPath();
					path.addWayPoint(new Vector3f());
					path.addWayPoint(new Vector3f(1, 0, 0));
					path.setCurveTension(0.0f);
					path.addListener(new MotionPathListener() {

						@Override
						public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) {
							if (motionControl.getPath().getNbWayPoints() == wayPointIndex + 1) {
								StorageUnit stor = storageUnits.get(storage.storage.getStorageId());
								stor.isFree = true;
								AGV agv = AGVHandler.getInstance().getAGV(storage.containersToOffload.get(0).agvId);
								agv.container = null;
								AGVHandler.getInstance().agvs.put(agv.agvId, agv);
								storage.containersToOffload.remove(0);
								storageUnits.put(stor.storage.getStorageId(), stor);
							}
						}
					});
					ServerSpatial spatial = new ServerSpatial(null, "1");
					ContainingServer.getRoot().attachChild(spatial);

					MotionEvent motionControl = new MotionEvent(spatial, path);
					motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
					motionControl.setRotation(new Quaternion().fromAngleNormalAxis(0, Vector3f.UNIT_Y));
					motionControl.setInitialDuration(30f);
					motionControl.setSpeed(ContainingServer.getSpeed());
					motionControl.play();
					storageUnits.put(storage.storage.getStorageId(), storage);
					break;
				}
			}

			if (!storage.isFree)
				System.out.println("1");
		}
	}

	public void addContainerToOffload(StorageCranePickupData c) {
		StorageUnit storage = this.storageUnits.get(c.craneID);
		storage.containersToOffload.add(c);
		this.storageUnits.put(storage.storage.getStorageId(), storage);
	}

	public ParkingLocation getLocation(int id) {
		return locations.get(id);
	}

	public void CreateParkingLots() {
		for (int i = 0; i < 39/* Parking lots */; i++) {
			for (int j = 0; j < 6/* Truck amount */; j++) {
				locations.put(i * 6 + j, new ParkingLocation(i * 6 + j, i, new Vector3f(267.5f - 22.5f, 0, (-768.2f + (20 / 6 + 0.3f) * j) + 40 * i)));
			}
		}
	}

	public void CreateStorageUnits() {
		for (int i = 0; i < maxStorageLocations; i++) {
			StorageUnit unit = new StorageUnit(new Storage(i), new Vector3f(110, 0, -760 + 40 * i));
			storageUnits.put(i, unit);
		}
	}

	public class ParkingLocation {

		public int id;
		public int parkID;
		public Vector3f location;
		public boolean hasAGV;
		public AGV agv;

		public ParkingLocation(int id, Integer parkID, Vector3f location) {
			this.id = id;
			this.parkID = (parkID == null) ? 0 : parkID;
			this.location = location;
			this.hasAGV = true;
		}
	}

	public class StorageUnit {

		public Storage storage;
		public Vector3f location;
		public boolean isFree;
		public ArrayList<StorageCranePickupData> containersToOffload = new ArrayList<StorageCranePickupData>();

		public StorageUnit(Storage storage, Vector3f location) {
			this.storage = storage;
			this.location = location;
			this.isFree = true;
		}
	}

	public void setParkingLocation(ParkingLocation location) {
		this.locations.put(location.id, location);
	}

	public String getContainerAmount() {
		int i = 0;
		for (StorageUnit storage : this.storageUnits.values()) {
			i += storage.storage.getContainerAmount();
		}
		return Integer.toString(i);
	}
}
