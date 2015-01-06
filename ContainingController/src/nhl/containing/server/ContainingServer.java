package nhl.containing.server;

import java.util.ArrayList;
import java.util.Random;

import nhl.containing.server.network.API;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.pathfinding.AGVHandler;
import nhl.containing.server.pathfinding.RouteController;
import nhl.containing.server.platformhandlers.StoragePlatformHandler;
import nhl.containing.server.platformhandlers.TruckPlatformHandler;
import nhl.containing.server.util.ControlHandler;
import nhl.containing.server.util.XMLFileReader;
import nhl.containing.server.util.XMLFileReader.Container;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.system.JmeContext;
import java.util.Collections;
import java.util.Stack;
import nhl.containing.server.platformhandlers.BargePlatformHandler;
import nhl.containing.server.platformhandlers.SeaShipPlatformHandler;

public class ContainingServer extends SimpleApplication
{
	float time = 0;
	private boolean hasSent;
	private static Node staticRootNode;
        
        private int currentDay = 1;
        private float dayCounter = 0;
        private final float dayLength = 30f; //the time 1 gameday should be in seconds
        long startTime;
        
        ArrayList<Container> containers;
        BargePlatformHandler bargePlatformHandler;
        SeaShipPlatformHandler seaShipPlatformHandler;
        Stack<Stack<Container>> listoftrainContainers = new Stack<>();
        Stack<Container> listoftruckContainers = new Stack<>();

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
        startTime = System.currentTimeMillis();
		staticRootNode = this.getRootNode();
		new RouteController();
		new ControlHandler();
		new AGVHandler();
		new TruckPlatformHandler();
		new StoragePlatformHandler();
		AGVHandler.getInstance().init();
		ConnectionManager.initialize(3000);
		API.start(8080);
                
            initContainers();
            System.out.println("1");
            
	}
        
        /**
         * loads all the containers and puts them on stacks and sorts the stack
         */
        private void initContainers() {
        XMLFileReader xmlReader = new XMLFileReader();
        containers = xmlReader.getContainers("../XMLFILES/xml7.xml");
        ArrayList<Container> riverShipContainers = new ArrayList<>();
        ArrayList<Container> trainContainers = new ArrayList<>();
        ArrayList<Container> seaShipContainers = new ArrayList<>();
        for (Container c : containers) {
            switch (c.getArrival().getType()) {
                case BINNENSCHIP:
                    riverShipContainers.add(c);
                    break;
                case TREIN:
                    trainContainers.add(c);
                    break;
                case VRACHTAUTO:
                    listoftruckContainers.push(c);
                    break;
                case ZEESCHIP:
                    seaShipContainers.add(c);
                    break;
            }
        }
        
        boolean isFull;
        bargePlatformHandler = new BargePlatformHandler(riverShipContainers);
        seaShipPlatformHandler = new SeaShipPlatformHandler(seaShipContainers);
        
        while(trainContainers.size()>0){
            listoftrainContainers.add(new Stack<>());
            listoftrainContainers.get(listoftrainContainers.size()-1).push(trainContainers.get(0));
            trainContainers.remove(0);
            isFull = false;
            while(trainContainers.size()>0 && !isFull){
                if(trainContainers.get(0).getPositie().equals(Vector3f.ZERO)){
                    isFull = true;
                }else{
                    listoftrainContainers.get(listoftrainContainers.size()-1).push(trainContainers.get(0));
                    trainContainers.remove(0);
                }
            }
        }
        Collections.sort(listoftrainContainers, (a,b) -> ((a.get(0).getArrival().getDay()) < (b.get(0).getArrival().getDay())) ? 1 : ((a.get(0).getArrival().getDay()) > (b.get(0).getArrival().getDay())) ? -1 : 0);
        
        Collections.sort(listoftruckContainers, (a,b) -> ((a.getArrival().getDay()) < (b.getArrival().getDay())) ? 1 : ((a.getArrival().getDay()) > (b.getArrival().getDay())) ? -1 : 0);
        
        //forced garbage collection (reduces RAM usage from 700MB  to 60 MB in case of xml7)
        System.gc();
    }
        
	/**
	 * Updates the server every frame
	 */
        Random rand = new Random();
        
    	@Override
    	public void simpleUpdate(float tpf)
    	{
            dayCounter += tpf;
            if(dayCounter > dayLength){
                currentDay++;
                dayCounter = 0;
                System.out.println("time since start: " + ((System.currentTimeMillis()-startTime)/1000f) + " program day: " +currentDay);
            }
    		{
    			if(rand.nextInt(500) == 10) 
    				TruckPlatformHandler.getInstance().spawnTruck();
    			
    			TruckPlatformHandler.getInstance().update(tpf);
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
		API.stop();
		super.destroy();
	}
}
