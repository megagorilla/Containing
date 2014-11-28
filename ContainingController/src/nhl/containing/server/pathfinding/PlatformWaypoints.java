package nhl.containing.server.pathfinding;

import java.awt.List;
import java.util.HashMap;

import com.jme3.math.Vector3f;

import nhl.containing.server.pathfinding.Graph.WayPoint;

public class PlatformWaypoints {
	
	//private HashMap<String, Vector3f> truckPlatformWaypoints;
	private WayPoint[] truckPlatformWaypoints;
	
	//380, 0.01f, -750 + 25 * i
	public PlatformWaypoints(){
		truckPlatformWaypoints = new WayPoint[20];
	}
	
	public void agvTruckParking(){
		for (int i = 0; i < truckPlatformWaypoints.length; i++) {
			truckPlatformWaypoints[i] = new WayPoint("", new Vector3f(380f, 0f, -750f + 25f * i));
		}
	}
}
