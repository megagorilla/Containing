package nhl.containing.server;

import nhl.containing.server.network.API;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.pathfinding.AGVHandler;
import nhl.containing.server.pathfinding.RouteController;
import nhl.containing.server.platformhandlers.StoragePlatformHandler;
import nhl.containing.server.platformhandlers.TruckPlatformHandler;
import nhl.containing.server.util.ControlHandler;

import com.jme3.app.SimpleApplication;
import com.jme3.scene.Node;
import com.jme3.system.JmeContext;
import java.util.ArrayList;
import nhl.containing.server.util.XMLFileReader;
import nhl.containing.server.util.XMLFileReader.*;

public class ContainingServer extends SimpleApplication
{
	float time = 0;
	private boolean hasSent;
	private static Node staticRootNode;
        
        ArrayList<Container> containers;
        ArrayList<Container> seaShipContainers = new ArrayList<>();
        ArrayList<Container> riverShipContainers = new ArrayList<>();
        ArrayList<Container> trainContainers = new ArrayList<>();
        ArrayList<Container> truckContainers = new ArrayList<>();

	/**
	 * Starts the app headless (no display)
	 * @param args
	 */
	public static void main(String[] args)
	{
		ContainingServer app = new ContainingServer();
		app.start(JmeContext.Type.Headless);
	}
	
	/**
	 * Initializes all the variables and classes.
	 */
	@Override
	public void simpleInitApp()
	{
		staticRootNode = this.getRootNode();
		new RouteController();
		new ControlHandler();
		new AGVHandler();
		new TruckPlatformHandler();
		new StoragePlatformHandler();
		AGVHandler.getInstance().init();
		ConnectionManager.initialize(3000);
		API.start(8080);
                
                XMLFileReader instance = new XMLFileReader();
		containers = instance.getContainers("../XMLFILES/xml7.xml");
                for(Container c : containers){
                    switch (c.getArrival().getType()){
                        case BINNENSCHIP:
                            riverShipContainers.add(c);
                            break;
                        case TREIN:
                            trainContainers.add(c);
                            break;
                        case VRACHTAUTO:
                            truckContainers.add(c);
                            break;
                        case ZEESCHIP:
                            seaShipContainers.add(c);
                            break;
                    }
                }
                System.gc();
        System.out.println("1");
	}

	/**
	 * Updates the server every frame
	 */
	@Override
	public void simpleUpdate(float tpf)
	{
		if(!hasSent && ConnectionManager.hasConnections())
		{
			StoragePlatformHandler.getInstance().handleAGV(AGVHandler.getInstance().getAGV(0));
			hasSent = true;
		}
	}
	
	/**
	 * @return {@link #staticRootNode}
	 */
	public static Node getRoot()
	{
		return staticRootNode;
	}
	
	/**
	 * Destroys the server properly so that the connection is closed
	 */
	@Override
	public void destroy()
	{
		ConnectionManager.stop();
		super.destroy();
	}
}
