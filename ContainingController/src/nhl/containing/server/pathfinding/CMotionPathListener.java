package nhl.containing.server.pathfinding;

import nhl.containing.server.platformhandlers.StoragePlatformHandler;
import nhl.containing.server.platformhandlers.TruckPlatformHandler;
import nhl.containing.server.util.ControlHandler;
import nhl.containing.server.util.ServerSpatial;

import com.jme3.cinematic.MotionPathListener;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.Vector3f;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.StorageCranePickupData;
import nhl.containing.server.platformhandlers.Storage;

/**
 * The motionpath Listener for the server, this makes it so that the server can keep track of objects when they are moving and when they stopped moving
 * @author Arjen
 */
public class CMotionPathListener implements MotionPathListener
{
	@Override
	public void onWayPointReach(MotionEvent motionControl, int wayPointIndex)
	{
		if (motionControl.getPath().getNbWayPoints() == wayPointIndex + 1)
		{
			System.out.println(motionControl.getSpatial().getName() + " has finished moving. ");
		} 
		else
		{
			System.out.println(motionControl.getSpatial().getName() + " has reached way point " + wayPointIndex);
		}
		
		if(motionControl.getSpatial() instanceof ServerSpatial && motionControl.getPath().getNbWayPoints() == wayPointIndex + 1)
		{
			ServerSpatial spatial = (ServerSpatial)motionControl.getSpatial();
			AGV agv = spatial.agv;
			agv.currentLocation = spatial.destination;
			AGVHandler.getInstance().setAGV(agv.agvId, agv);
			motionControl.stop();
			switch(AGVHandler.getInstance().getAGV(spatial.agv.agvId).currentLocation)
			{
				case "a2":
					handleAGVTruckPlatform(spatial.agv.agvId);
					return;
				case "a3":
					handleAGVToStorage(spatial.agv);
					return;
				case "a1":
					StoragePlatformHandler.getInstance().handleAGV(AGVHandler.getInstance().getAGV(spatial.agv.agvId));
					return;
			}
			
			if(AGVHandler.getInstance().getAGV(spatial.agv.agvId).currentLocation.startsWith("truckLocation_"))
			{
				String[] l = AGVHandler.getInstance().getAGV(spatial.agv.agvId).currentLocation.split("_");
				int i = Integer.parseInt(l[1]);
				if(l.length == 2)
				{
					if(!AGVHandler.getInstance().getAGV(spatial.agv.agvId).getLoaded())
					{
						TruckPlatformHandler.getInstance().getContainerFromTruck(spatial.agv.agvId, i);
					}
					else
					{
						TruckPlatformHandler.getInstance().getContainerFromAGV(spatial.agv.agvId, i);
					}
					System.out.println("TruckPlatform Arrival: " + i);
				}
				else if(l.length == 3)
				{	
					TruckPlatformHandler.getInstance().sendAGVToStorage(AGVHandler.getInstance().getAGV(spatial.agv.agvId), i);
					System.out.println("TruckPlatform Departure: " + i);
				}
			}
			else if(AGVHandler.getInstance().getAGV(spatial.agv.agvId).currentLocation.startsWith("storageLocation_"))
			{
				int i = Integer.parseInt(AGVHandler.getInstance().getAGV(spatial.agv.agvId).currentLocation.split("_")[1]);
                Storage storage = StoragePlatformHandler.getInstance().getStorage((int)Math.floor(i / 6));
                Vector3f vec = storage.PushContainer(spatial.agv.getContainer());
                
                StorageCranePickupData data = new StorageCranePickupData(storage.getStorageId(), vec.x, vec.y, vec.z, i, spatial.agv.agvId);
                StoragePlatformHandler.getInstance().addContainerToOffload(data);
                spatial.agv.isMoving = false;
                AGVHandler.getInstance().setAGV(agv.agvId, agv);
				System.out.println("DESTINATION REACHED: " + i);
			}
		}
	}
	
	/**
	 * Handles the AGV when it has reached location 'a3'
	 * @param agv
	 */
	private void handleAGVToStorage(AGV agv) 
	{
		ControlHandler.getInstance().sendAGV("a1", agv.agvId, "a3");
	}

	/**
	 * Handles the AGV when it has reached location 'a2'
	 * @param id
	 */
	private void handleAGVTruckPlatform(int id)
	{
		AGV agv = AGVHandler.getInstance().getAGV(id);
		TruckPlatformHandler.getInstance().handleAGV(agv);
	}
}
