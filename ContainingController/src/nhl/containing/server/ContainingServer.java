package nhl.containing.server;

import java.util.List;

import nhl.containing.server.network.API;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.UpdateMessage;
import nhl.containing.server.pathfinding.AGVHandler;
import nhl.containing.server.pathfinding.CMotionPathListener;
import nhl.containing.server.pathfinding.PlatformWaypoints;
import nhl.containing.server.pathfinding.RouteController;
import nhl.containing.server.util.ControlHandler;

import com.jme3.app.SimpleApplication;
import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.system.JmeContext;

public class ContainingServer extends SimpleApplication
{
	float time = 0;
	private boolean hasSent;
	private RouteController route;
	private static Node staticRootNode;
	private PlatformWaypoints tParking;

	public static void main(String[] args)
	{
		ContainingServer app = new ContainingServer();
		app.start(JmeContext.Type.Headless);
	}

	@Override
	public void simpleInitApp()
	{
		staticRootNode = this.getRootNode();
		route = new RouteController();
		new ControlHandler();
		new AGVHandler();
		tParking = new PlatformWaypoints();
		tParking.agvTruckParking();
		AGVHandler.getInstance().init();
		ConnectionManager.initialize(3000);
		API.start(8080);
		
        System.out.println("1");
	}

	@Override
	public void simpleUpdate(float tpf)
	{
		if(!hasSent && ConnectionManager.hasConnections())
		{
			//dest - start
			ControlHandler.getInstance().sendAGV("a2", 0, "a4");
//			ControlHandler.getInstance().sendAGV("a4", 1, "");
//			ControlHandler.getInstance().sendAGV("a3", 2, "");
//			ControlHandler.getInstance().sendAGV("c2", 3, "");
//			ControlHandler.getInstance().sendAGV("b4", 4, "");
			hasSent = true;
		}
	}
	
	public static Node getRoot()
	{
		return staticRootNode;
	}
	
	@Override
	public void destroy()
	{
		ConnectionManager.stop();
		super.destroy();
	}
}
