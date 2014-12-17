package nhl.containing.server.util;

import java.util.ArrayList;
import java.util.List;

import nhl.containing.server.ContainingServer;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.UpdateMessage;
import nhl.containing.server.pathfinding.AGV;
import nhl.containing.server.pathfinding.AGVHandler;
import nhl.containing.server.pathfinding.CMotionPathListener;
import nhl.containing.server.pathfinding.RouteController;
import nhl.containing.server.platformhandlers.StoragePlatformHandler;
import nhl.containing.server.platformhandlers.StoragePlatformHandler.ParkingLocation;

import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 * Controls the AGVs and sends the info to the client
 * @author Arjen
 */
public class ControlHandler
{
	/**
	 * Instance of this class
	 */
	private static ControlHandler instance;
	
	/**
	 * Retrieves the instance of this class
	 * @return {@link #instance}
	 */
	public static ControlHandler getInstance()
	{
		return instance;
	}
	
	/**
	 * Initializes the instance
	 */
	public ControlHandler()
	{
		instance = this;
	}
	
	/**
	 * Sends the agv to a certain location on the graph and creates a motionpath for the server to keep track of that object.
	 * (TO BE REMOVED, FOR TESTING ONLY)
	 * @param destination
	 * @param id
	 * @param cl
	 */
	public void sendAGV(String destination, int id, String cl)
	{
		AGV agv = AGVHandler.getInstance().getAGV(id);
		RouteController controller = new RouteController();
		agv.currentLocation = cl;
		List<Vector3f> list = controller.sendAGV(agv.currentLocation, destination);
		
		UpdateMessage message = new UpdateMessage(Integer.toString(id));
		message.addData(agv.agvId, list, 0, 0);
		ConnectionManager.sendCommand(message);
		
		MotionPath path = new MotionPath();
		for(Vector3f v : list)
			path.addWayPoint(v);
		path.setCurveTension(0.0f);
		path.addListener(new CMotionPathListener());
		
		ServerSpatial spatial = new ServerSpatial(agv, destination);
        ContainingServer.getRoot().attachChild(spatial);

		MotionEvent motionControl = new MotionEvent(spatial, path);
        motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl.setRotation(new Quaternion().fromAngleNormalAxis(0, Vector3f.UNIT_Y));
        motionControl.setInitialDuration(100f);
        motionControl.setSpeed(8f);  
        motionControl.play();
	}
	
	/**
	 * Sends the agv to a certain location on the graph and creates a motionpath for the server to keep track of that object.
	 * @param id
	 * @param list
	 */
	public void sendAGV(int id, List<Vector3f> list)
	{
		AGV agv = AGVHandler.getInstance().getAGV(id);
		UpdateMessage message = new UpdateMessage(Integer.toString(id));
		message.addData(id, list, 0, 0);
		ConnectionManager.sendCommand(message);
		
		MotionPath path = new MotionPath();
		for(Vector3f v : list)
			path.addWayPoint(v);
		path.setCurveTension(0.0f);
		path.addListener(new CMotionPathListener());
		
		ServerSpatial spatial = new ServerSpatial(agv, "truckLocation_" + Integer.toString(id));
        ContainingServer.getRoot().attachChild(spatial);

		MotionEvent motionControl = new MotionEvent(spatial, path);
        motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl.setRotation(new Quaternion().fromAngleNormalAxis(0, Vector3f.UNIT_Y));
        motionControl.setInitialDuration(100f);
        motionControl.setSpeed(8f);  
        motionControl.play();
	}
	
	/**
 	 * Sends the agv to a certain location on the graph and creates a motionpath for the server to keep track of that object.
	 * @param id
	 * @param list
	 * @param destination
	 */
	public void sendAGV(int id, List<Vector3f> list, String destination)
	{
		AGV agv = AGVHandler.getInstance().getAGV(id);
		UpdateMessage message = new UpdateMessage(Integer.toString(id));
		message.addData(id, list, 0, 0);
		ConnectionManager.sendCommand(message);
		
		MotionPath path = new MotionPath();
		for(Vector3f v : list)
			path.addWayPoint(v);
		path.setCurveTension(0.0f);
		path.addListener(new CMotionPathListener());
		
		ServerSpatial spatial = new ServerSpatial(agv, destination);
        ContainingServer.getRoot().attachChild(spatial);

		MotionEvent motionControl = new MotionEvent(spatial, path);
        motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl.setRotation(new Quaternion().fromAngleNormalAxis(0, Vector3f.UNIT_Y));
        motionControl.setInitialDuration(100f);
        motionControl.setSpeed(12f);  
        motionControl.play();
	}
	
	public void requestAGVToTrucks(int id)
	{
		AGV agv = AGVHandler.getInstance().getFreeAGV();
		List<Vector3f> list = new ArrayList<Vector3f>();
		int i = Integer.parseInt(agv.currentLocation.split("_")[1]);
		ParkingLocation location = StoragePlatformHandler.getInstance().getLocation(i);
		location.hasAGV = false;
		StoragePlatformHandler.getInstance().setParkingLocation(location);
		list.add(location.location);
		list.add(new Vector3f(location.location.x + 10, 0.0f, location.location.z));
		list.add(new Vector3f(267.5f, 0.0f, -760 + 40 * location.parkID));
		list.add(new Vector3f(303.5f, 0.0f, -760 + 40 * location.parkID));
		list.add(new Vector3f(303.5f, 0.0f, -778.5f));
		list.add(new Vector3f(353.5f, 0.0f, -778.5f));
		this.sendAGV(agv.agvId, list, "a2");
	}
}
