package nhl.containing.client;

import java.util.ArrayList;
import java.util.HashMap;

import nhl.containing.client.entities.Container;
import nhl.containing.client.entities.Platform;
import nhl.containing.client.entities.cranes.DockingCrane;
import nhl.containing.client.entities.cranes.StorageCrane;
import nhl.containing.client.entities.cranes.TrainCrane;
import nhl.containing.client.entities.cranes.TruckCrane;
import nhl.containing.client.entities.platforms.RiverShipPlatform;
import nhl.containing.client.entities.platforms.SeaShipPlatform;
import nhl.containing.client.entities.platforms.StoragePlatform;
import nhl.containing.client.entities.platforms.TrainPlatform;
import nhl.containing.client.entities.platforms.TruckPlatform;
import nhl.containing.client.entities.vehicles.AGV;
import nhl.containing.client.entities.vehicles.Barge;
import nhl.containing.client.entities.vehicles.SeaShip;
import nhl.containing.client.entities.vehicles.SpaceShip;
import nhl.containing.client.entities.vehicles.Train;
import nhl.containing.client.entities.vehicles.Truck;
import nhl.containing.client.network.ConnectionManager;
import nhl.containing.client.scenery.SeaNode;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.util.SkyFactory;
/**
 * test
 *
 * @author Yannick
 */
public class ContainingClient extends SimpleApplication {

    private DirectionalLight sun;

    public enum Quality {

        LOW, MEDIUM, HIGH
    };
    public static Quality quality = Quality.LOW;
    //private ContainingClient main = new ContainingClient();
    private static Node myRootNode;
    private static AssetManager myAssetManager;
    private static ViewPort myViewPort;
    MotionEvent motionControl;
    public static AGV agv;
    
    public static ArrayList<AGV> agvs = new ArrayList<AGV>();
    public static ArrayList<StorageCrane> StorageCranes = new ArrayList<StorageCrane>();
    public static ArrayList<TrainCrane> TrainCranes = new ArrayList<TrainCrane>();
    public static ArrayList<TruckCrane> TruckCranes = new ArrayList<TruckCrane>();
    ArrayList<Platform> Platforms = new ArrayList<Platform>();
    ArrayList<AGV> AGVs = new ArrayList<AGV>();
    public static HashMap<Integer, Truck> Trucks = new HashMap<Integer, Truck>();
    public static ArrayList<SeaShip> seaShips = new ArrayList<SeaShip>();
    public static ArrayList<Barge> barges = new ArrayList<Barge>();
    public static ArrayList<DockingCrane> seaShipCranes = new ArrayList<DockingCrane>();
    public static ArrayList<DockingCrane> bargeCranes = new ArrayList<DockingCrane>();
    public static ContainingClient instance;
    public boolean truckup = false;
    public boolean containerUp = false;
    private boolean onTarget = false;
    private boolean onStore = false;
    public boolean down;
    int truckAmount = 20;
    int craneAmount = 39;
    int trainCraneAmount = 4;
    Node rails;
    Container Container1;
    Container Container2;        public static Container test2;
    Container AGVtester;
    
    AGV trainAGV;
    
    public static Container test2;
    Container test3;
        public boolean hai = false;
        
        public TruckCrane crane;
        public AGV agv123;
        
    public static int containerPositie;
	public static Train train;

    public static void main(String[] args) {
        ContainingClient app = new ContainingClient();
        app.start();
    }

    /**
     * Hier voegen we de objecten toe aan de "map" 20 truck kranen, 20 trucks,
     * 39 opslagkranen en alle platformen
     */
    @Override
    public void simpleInitApp() {
    	instance = this;
        myAssetManager = assetManager;
        myRootNode = rootNode;
        myViewPort = viewPort;
        rootNode.attachChild(SkyFactory.createSky(assetManager, "Scenes/BrightSky.dds", false));
        flyCam.setMoveSpeed(200);
        cam.setFrustumFar(5000);
        cam.onFrameChange();
        cam.setLocation(new Vector3f(300, 20, -300));

		for (int i = 0; i < 39/*Parking lots*/; i++)
		{
			for(int j = 0; j < 6/*Truck amount*/; j++)
			{
				AGV agv = new AGV(quality);
				agv.setLocalTranslation(new Vector3f(245, 0, (-768.2f + (3.633333f)*j) + 40 * i));
				agv.rotate(0, FastMath.HALF_PI, 0);
				agvs.add(agv);
			}
		}
        for(int i = 0;i<12;i++){
            seaShipCranes.add(new DockingCrane(quality, true, i));
        }
        for(int i = 0;i<4;i++){
            bargeCranes.add(new DockingCrane(quality, false,i));
        }
        ConnectionManager.init("localhost", 3000);

        sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
        SpaceShip s = new SpaceShip();
//        SeaNode sea = new SeaNode();
//        this.getRootNode().attachChild(sea);
        Platforms.add(new StoragePlatform());
        Platforms.add(new SeaShipPlatform());
        Platforms.add(new TrainPlatform());
        Platforms.add(new TruckPlatform());
        Platforms.add(new RiverShipPlatform());


        for (int i = 0; i < truckAmount; i++) {
            TruckCranes.add(new TruckCrane(i));
            TruckCranes.get(i).setLocalTranslation(380, 0, -750 + 25 * i);
            TruckCranes.get(i).rotate(0, FastMath.HALF_PI, 0);
        }


        for (int i = 0; i < truckAmount; i++) {
//            Trucks.add(new Truck(quality));
//            Trucks.get(i).setLocalTranslation(400, 0, -750 + 25 * i);
//            Trucks.get(i).rotate(0, FastMath.HALF_PI, 0);
        }

        for (int i = 0; i < craneAmount; i++) {
            StorageCranes.add(new StorageCrane());
            StorageCranes.get(i).setLocalTranslation(0, 0, -760 + 40 * i);
        }
        
        for(int i = 0; i < trainCraneAmount; i++){
            TrainCranes.add(new TrainCrane());
            //TrainCranes.get(i).setLocalTranslation(-327.5f, 0f, 100 + 100*i);
            TrainCranes.get(i).setLocalTranslation(-327.5f, 0f, 497.2f - 18.4f * i);
        }
        //Container1.setLocalTranslation(245,1.2f,-751.7f);
        
        Container2 = new Container(quality, 0);
        //110.8
        
        Container2.RotateContainer(0, FastMath.HALF_PI, 0);
        Container2.setLocalTranslation(-245,1.2f,-711.7f);

        Container3 = new Container(quality);
        Container3.RotateContainer(0, FastMath.HALF_PI, 0);
        Container3.setLocalTranslation(0, 1.2f, -671.7f);

        trainAGV = new AGV(quality);
        trainAGV.setLocalTranslation(-327.5f, 0, 423.6f); 
        
        AGVtester = new Container(quality);
        AGVtester.setLocalTranslation(-334, 1.2f, 423.6f);
        
        //18.4 verschil per wagon
    }

    boolean hasSent;
    
    @Override
    public void simpleUpdate(float tpf) 
    {
        if(!hasSent)
        {   
//            this.StorageCranes.get(0).StoreRight(Container1, rootNode, new Vector3f(14, 0, 5), 5, StorageCranes.get(0));
//            this.StorageCranes.get(1).StoreLeft(Container2, rootNode, new Vector3f(14,0,5), 5, StorageCranes.get(1));
            //this.StorageCranes.get(2).LoadAGV(Container3, rootNode, new Vector3f(14, 0, 5), 5, StorageCranes.get(2));
            this.TrainCranes.get(0).loadContainer(AGVtester, trainAGV, 4);
        }
    }

    /**
     * returns the static version of the assetmanager Usage:
     * DisplayController.getMyAssetManager()
     *
     * @return a static assetmanager
     */
    public static AssetManager getMyAssetManager() {
        return myAssetManager;
    }

    /**
     * returns the static version of the RootNode Usage:
     * DisplayController.getMyRootNode()
     *
     * @return a static rootnode
     */
    public static Node getMyRootNode() {
        return myRootNode;
    }

    /**
     * returns the static version of the ViewPort Usage:
     * DisplayController.getMyViewPort()
     *
     * @return a static vieuwport
     */
    public static ViewPort getMyViewPort() {
        return myViewPort;
    }

    public DirectionalLight getSun() {
        return sun;
    }

    @Override
    public void simpleRender(RenderManager rm) {
        // TODO: add render code
    }
    // * gaat de container met hem mee, waardoor het lijkt of hij de container
    // * daadwerkelijk pakt.
        
    // * hand daarvan wordt hun beweging bepaald.
   

    public void MoveStorageCrane() {
        if (containerUp == false) {
            Container1.setLocalTranslation(290, 0, -768);
        }        
        if (StorageCranes.get(0).getLocalTranslation().x == 0) {
            //StorageCranes.get(0).CraneMovement();
        }
        if (StorageCranes.get(0).getGrabber().getLocalTranslation().y < -22.9f && !containerUp) {
            containerUp = true;  
            StorageCranes.get(0).getGrabber().attachChild(Container1);
            Container1.RotateContainer(0, FastMath.HALF_PI, 0);
            Container1.setLocalTranslation(0, 23, 0);   
        }        
        //System.out.println(Container1.getLocalTranslation().x + " Container");
        if(StorageCranes.get(0).getGrabber().getLocalTranslation().y < -22.9f && StorageCranes.get(0).getLocalTranslation().x < 215.1 && containerUp && !onStore)
        {
            onStore = true;            
            rootNode.attachChild(Container1);
            Container1.setLocalTranslation(215, 0, -768);
            Container1.RotateContainer(0, FastMath.HALF_PI, 0);
        }
       
    }
    
    public static float getSpeed()
    {
    	return 10f;
    }
}
