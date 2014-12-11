package nhl.containing.server;

import nhl.containing.server.network.API;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.pathfinding.AGVHandler;
import nhl.containing.server.pathfinding.RouteController;
import nhl.containing.server.platformhandlers.StoragePlatformHandler;
import nhl.containing.server.platformhandlers.TruckPlatformHandler;
import nhl.containing.server.util.ControlHandler;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
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
                
            XMLFileReader xmlReader = new XMLFileReader();
            containers = xmlReader.getContainers("../XMLFILES/xml7.xml");
            for (Container c : containers) {
                switch (c.getArrival().getType()) {
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
            //forced garbage collection (reduces RAM usage from 700MB  to 60 MB in case of xml7)
            System.gc();
            System.out.println("1");
	}

        /**
         * retrieves arraylist for the next seaship and removes the containers
         * from the list of all the seashipcontainers
         * @return An arraylist of containers that fit in 1 seaship
         */
        public ArrayList<Container> getNextSeaShip() {
        ArrayList<Container> containters = new ArrayList<>();
        containters.add(seaShipContainers.get(0));
        seaShipContainers.remove(0);
        boolean shipFull = false;
        while (!shipFull && seaShipContainers.size()>0) {
            if (seaShipContainers.get(0).getPositie().equals(new Vector3f(0.0f, 0.0f, 0.0f))) {
                shipFull = true;
            } else {
                containters.add(seaShipContainers.get(0));
                seaShipContainers.remove(0);
            }
        }
        return containters;
    }
        /**
         * retrieves arraylist for the next train and removes the containers
         * from the list of all the trainContainers
         * @return An arraylist of containers that fit in 1 train
         */
        public ArrayList<Container> getNextTrain() {
            ArrayList<Container> containters = new ArrayList<>();
            containters.add(trainContainers.get(0));
            trainContainers.remove(0);
            boolean trainFull = false;
            while (!trainFull && trainContainers.size()>0) {
                if (trainContainers.get(0).getPositie().equals(new Vector3f(0.0f, 0.0f, 0.0f))) {
                    trainFull = true;
                }else{
                    containters.add(trainContainers.get(0));
                    trainContainers.remove(0);
                }
            }
            return containters;
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
