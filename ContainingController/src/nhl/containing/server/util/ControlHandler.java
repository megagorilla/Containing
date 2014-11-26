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
	public void sendAGV(String destination)
	{
		AGV agv = AGVHandler.getInstance().getFreeAGV();
		RouteController controller = new RouteController();
		List<Vector3f> list = controller.sendAGV(agv.currentLocation, destination);
		
		UpdateMessage message = new UpdateMessage("AGVData");
		message.addData(agv.agvId, list);
		ConnectionManager.sendCommand(message);
		
		MotionPath path = new MotionPath();
		for(Vector3f v : list)
			path.addWayPoint(v);
		path.setCurveTension(0.0f);
		path.addListener(new CMotionPathListener());
		
		ServerSpatial spatial = new ServerSpatial(agv);
        ContainingServer.getRoot().attachChild(spatial);

		MotionEvent motionControl = new MotionEvent(spatial, path);
        motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl.setRotation(new Quaternion().fromAngleNormalAxis(0, Vector3f.UNIT_Y));
        motionControl.setInitialDuration(100f);
        motionControl.setSpeed(5f);  
        motionControl.play();
	}
}
