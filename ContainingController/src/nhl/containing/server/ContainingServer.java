package nhl.containing.server;

import java.util.List;

import nhl.containing.server.network.API;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.UpdateMessage;
import nhl.containing.server.pathfinding.CMotionPathListener;
import nhl.containing.server.pathfinding.RouteController;

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
		ConnectionManager.initialize(3000);
		API.start(8080);
		
        System.out.println("1");
	}

	@Override
	public void simpleUpdate(float tpf)
	{
		if(!hasSent)
		{

//			UpdateMessage m = new UpdateMessage("hai");
//			List<Vector3f> list = route.sendAGV("d2", "b3");
//			m.addData(0, list);
//			ConnectionManager.sendCommand(m);
//			System.out.println(System.currentTimeMillis());
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
