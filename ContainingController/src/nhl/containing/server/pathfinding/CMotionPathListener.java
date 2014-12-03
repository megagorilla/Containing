package nhl.containing.server.pathfinding;

import java.awt.Color;

import nhl.containing.server.platformhandlers.TruckPlatformHandler;
import nhl.containing.server.util.ControlHandler;
import nhl.containing.server.util.ServerSpatial;

import com.jme3.cinematic.MotionPathListener;
import com.jme3.cinematic.events.MotionEvent;

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
			}
			
			if(AGVHandler.getInstance().getAGV(spatial.agv.agvId).currentLocation.startsWith("truckLocation_"))
			{
				int i = Integer.parseInt(AGVHandler.getInstance().getAGV(spatial.agv.agvId).currentLocation.split("_")[1]);
				TruckPlatformHandler.getInstance().sendAGVToStorage(AGVHandler.getInstance().getAGV(spatial.agv.agvId), i);
			}
		}
	}
	
	private void handleAGVToStorage(AGV agv) 
	{
		ControlHandler.getInstance().sendAGV("a1", agv.agvId, "a3");
	}

	private void handleAGVTruckPlatform(int id)
	{
		AGV agv = AGVHandler.getInstance().getAGV(id);
		TruckPlatformHandler.getInstance().handleAGV(agv);
	}
}
