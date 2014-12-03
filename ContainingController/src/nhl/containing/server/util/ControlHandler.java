package nhl.containing.server.util;

import java.util.List;

import nhl.containing.server.ContainingServer;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.UpdateMessage;
import nhl.containing.server.pathfinding.AGV;
import nhl.containing.server.pathfinding.AGVHandler;
import nhl.containing.server.pathfinding.CMotionPathListener;
import nhl.containing.server.pathfinding.RouteController;

import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

public class ControlHandler
{
	private static ControlHandler instance;
	
	public static ControlHandler getInstance()
	{
		return instance;
	}
	
	public ControlHandler()
	{
		instance = this;
	}
	
	public void sendAGV(String destination, int id, String cl)
	{
		AGV agv = AGVHandler.getInstance().getFreeAGV();
		RouteController controller = new RouteController();
		agv.currentLocation = cl;
		List<Vector3f> list = controller.sendAGV(agv.currentLocation, destination);
		
		UpdateMessage message = new UpdateMessage(Integer.toString(id));
		message.addData(agv.agvId, list);
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
	
	public void sendAGV(int id, List<Vector3f> list)
	{
		AGV agv = AGVHandler.getInstance().getAGV(id);
		UpdateMessage message = new UpdateMessage(Integer.toString(id));
		message.addData(id, list);
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
	
	public void sendAGV(int id, List<Vector3f> list, String destination)
	{
		AGV agv = AGVHandler.getInstance().getAGV(id);
		UpdateMessage message = new UpdateMessage(Integer.toString(id));
		message.addData(id, list);
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
}
