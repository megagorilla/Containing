package nhl.containing.server.pathfinding;

import nhl.containing.server.platformhandlers.StoragePlatformHandler;
import nhl.containing.server.platformhandlers.TruckPlatformHandler;
import nhl.containing.server.util.ControlHandler;
import nhl.containing.server.util.ServerSpatial;

import com.jme3.cinematic.MotionPathListener;
import com.jme3.cinematic.events.MotionEvent;

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
					TruckPlatformHandler.getInstance().getContainerFromTruck(spatial.agv.agvId, i);
				else if(l.length == 3)
					TruckPlatformHandler.getInstance().sendAGVToStorage(AGVHandler.getInstance().getAGV(spatial.agv.agvId), i);
			}
			else if(AGVHandler.getInstance().getAGV(spatial.agv.agvId).currentLocation.startsWith("storageLocation_"))
			{
				int i = Integer.parseInt(AGVHandler.getInstance().getAGV(spatial.agv.agvId).currentLocation.split("_")[1]);
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
