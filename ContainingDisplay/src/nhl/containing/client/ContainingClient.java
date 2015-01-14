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
import nhl.containing.client.entities.vehicles.Train;
import nhl.containing.client.entities.vehicles.Truck;
import nhl.containing.client.network.ConnectionManager;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
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
    public boolean down;
    int truckAmount = 20;
    int craneAmount = 39;
    int trainCraneAmount = 4;
    Node rails;
    Container Container1;
    Container Container2;        
    public static Container test2;
    Container AGVtester;
    public static float speed;
    Vector3f direction = new Vector3f();
    AGV trainAGV;
    
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
        InitKeys();
        
        

		for (int i = 0; i < 39/*Parking lots*/; i++)
		{
			for(int j = 0; j < 6/*Truck amount*/; j++)
			{
				AGV agv = new AGV(quality);
                agv.setLocalTranslation(new Vector3f(267.5f - 22.5f, 0, (-768.2f + (20 / 6 + 0.3f)*j) + 40 * i));
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
//        SpaceShip s = new SpaceShip();
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

        trainAGV = new AGV(quality);
        trainAGV.setLocalTranslation(-327.5f, 0, 423.6f); 
                
        //18.4 verschil per wagon
        
    }

    
    private void InitKeys()
    {
    	inputManager.addMapping("camera 1", new KeyTrigger(KeyInput.KEY_1));
    	inputManager.addMapping("camera 2", new KeyTrigger(KeyInput.KEY_2));
    	inputManager.addMapping("camera 3", new KeyTrigger(KeyInput.KEY_3));
    	inputManager.addMapping("camera 4", new KeyTrigger(KeyInput.KEY_4));
    	inputManager.addMapping("camera 5", new KeyTrigger(KeyInput.KEY_5));
    	
    	inputManager.addListener(actionListener,"camera 1", "camera 2", "camera 3", "camera 4", "camera 5");
    }
    
    private ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean keyPressed, float tpf) {
        direction.set(cam.getDirection()).normalizeLocal();
          if(!keyPressed)
          {
        	  switch (name)
        	  {
        	  		default : name = "Storage";
        	  			cam.setLocation(new Vector3f(332.27817f, 44.62557f, -707.1006f));
        	  			cam.lookAt(StorageCranes.get(0).getLocalTranslation(), Vector3f.UNIT_Y);
	  				break;
        	  		case "camera 2": name = "Truck";
        	  			cam.setLocation(new Vector3f(432.1398f, 47.058403f, -872.5521f));
        	  			cam.lookAt(TruckCranes.get(0).getLocalTranslation(), Vector3f.UNIT_Y);
        	  			direction.multLocal(5 * tpf);
        	  		break;
        	  		case "camera 3": name = "Barge";
        	  		 	cam.setLocation(new Vector3f(285.32617f, 48.856247f, 732.0282f));
        	  		 	cam.lookAt(bargeCranes.get(0).getLocalTranslation(), Vector3f.UNIT_Y);
        	  		break;
        	  		case "camera 4": name = "Seaship";
        	  			cam.setLocation(new Vector3f(-289.3266f, 57.694035f, 745.97687f));
        	  			cam.lookAt(seaShipCranes.get(4).getLocalTranslation(), Vector3f.UNIT_Y);
        	  		break;
        	  		case "camera 5": name = "Train";
        	  			cam.setLocation(new Vector3f(-255.5176f, 14.202371f, 564.86224f));
        	  			cam.lookAt(TrainCranes.get(0).getLocalTranslation(), Vector3f.UNIT_Y);
        	  		break;
        	  		
        	  }
          }
        }
      };
      
    boolean hasSent;
    
    @Override
    public void simpleUpdate(float tpf) 
    {
        if(!hasSent)
        {   
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
    
    public static float getSpeed()
    {
    	return speed;
    }

	public static void setSpeed(float speed) {
		ContainingClient.speed = speed;
	}
}
