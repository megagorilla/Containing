package nhl.containing.client;

import nhl.containing.client.entities.Platform;
import nhl.containing.client.entities.platforms.RiverShipPlatform;
import nhl.containing.client.entities.vehicles.AGV;
import nhl.containing.client.network.ConnectionManager;
import nhl.containing.client.scenery.SeaNode;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.util.SkyFactory;

import java.util.ArrayList;

import nhl.containing.client.ContainingClient.Quality;
import nhl.containing.client.entities.Container;
import nhl.containing.client.entities.Crane;
import nhl.containing.client.entities.cranes.StorageCrane;
import nhl.containing.client.entities.cranes.TrainCrane;
import nhl.containing.client.entities.cranes.TruckCrane;
import nhl.containing.client.entities.platforms.SeaShipPlatform;
import nhl.containing.client.entities.platforms.StoragePlatform;
import nhl.containing.client.entities.platforms.TrainPlatform;
import nhl.containing.client.entities.platforms.TruckPlatform;
import nhl.containing.client.entities.vehicles.AGV;
import nhl.containing.client.entities.vehicles.Train;
import nhl.containing.client.entities.vehicles.Truck;

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
    private Quality quality = Quality.HIGH;
    //private ContainingClient main = new ContainingClient();
    private static Node myRootNode;
    private static AssetManager myAssetManager;
    private static ViewPort myViewPort;
    MotionEvent motionControl;
    public static AGV agv;
    
    public static ArrayList<AGV> agvs = new ArrayList<AGV>();
    ArrayList<StorageCrane> StorageCranes = new ArrayList<StorageCrane>();
    ArrayList<TrainCrane> TrainCranes = new ArrayList<TrainCrane>();
    public static ArrayList<TruckCrane> TruckCranes = new ArrayList<TruckCrane>();
    ArrayList<Platform> Platforms = new ArrayList<Platform>();
    ArrayList<AGV> AGVs = new ArrayList<AGV>();
    ArrayList<Truck> Trucks = new ArrayList<Truck>();
    
    public AGV testAGV;
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
    Container test3;
    public static int containerPositie;

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
        myAssetManager = assetManager;
        myRootNode = rootNode;
        myViewPort = viewPort;
        rootNode.attachChild(SkyFactory.createSky(assetManager, "Scenes/BrightSky.dds", false));
        flyCam.setMoveSpeed(200);
        cam.setFrustumFar(5000);
        cam.onFrameChange();
        cam.setLocation(new Vector3f(300, 20, -300));

        for (int i = 0; i < 5; i++) {
            agvs.add(new AGV(Quality.HIGH));
        }

        ConnectionManager.init("localhost", 3000);

        sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);

        //SeaNode sea = new SeaNode();
        //this.getRootNode().attachChild(sea);
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
            Trucks.add(new Truck(quality));
            Trucks.get(i).setLocalTranslation(400, 0, -750 + 25 * i);
            Trucks.get(i).rotate(0, FastMath.HALF_PI, 0);
        }

        for (int i = 0; i < craneAmount; i++) {
            StorageCranes.add(new StorageCrane());
            StorageCranes.get(i).setLocalTranslation(0, 0, -760 + 40 * i);
        }
        
        for(int i = 0; i < trainCraneAmount; i++){
            TrainCranes.add(new TrainCrane());
            TrainCranes.get(i).setLocalTranslation(-327.5f, 0f, 100 + 100*i);
        }
        
        Train test = new Train(quality, 30);
        test.setLocalTranslation(-334, 0, 510);
        Container1 = new Container(quality);
        Container1.RotateContainer(0, FastMath.HALF_PI, 0);

        testAGV = new AGV(quality);
        testAGV.setLocalTranslation(380, 0, -750);
        testAGV.rotate(0, FastMath.HALF_PI, 0);
        
        AGV trainAGV = new AGV(quality);
        trainAGV.setLocalTranslation(-327.5f, 0, 0);  
    }

    @Override
    public void simpleUpdate(float tpf) {

        // if(Container1.getLocalTranslation().x == 380)
        // {
        // MoveTruckCraneAGVtoTruck();  
        //  }
        // else if(Container1.getLocalTranslation().x == 400)
        // {
        // MoveTruckCraneTrucktoAGV();
        // }
        //    System.out.println(TruckCranes.get(0).getLocalTranslation().x + "XWAARDE");

        MoveStorageCrane();
        //System.out.println(StorageCranes.get(0).getLocalTranslation().z + "z waarde");
        //System.out.println(StorageCranes.get(0).getLocalTranslation().x + "x waarde");
        //System.out.println(StorageCranes.get(0).getLocalTranslation().y + "y waarde");
        //System.out.println(Container1.getLocalTranslation().y + " Container");
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

    /**
     * Dit is de methode van het verplaatsen van een container, van een AGV naar
     * een truck Er wordt gekeken naar de posities van de objecten en aan de
     * hand daarvan wordt hun beweging bepaald. Bijvoorbeeld, als de haak van
     * een kraan op een bepaalde hoogte/laate is, begint de kraan te rijden en
     * gaat de container met hem mee, waardoor het lijkt of hij de container
     * daadwerkelijk pakt.
     */
    public void MoveTruckCraneAGVtoTruck() {
        if (TruckCranes.get(0).getGrabber().getLocalTranslation().y > 0 && TruckCranes.get(0).getLocalTranslation().x == 380.0f) {
            TruckCranes.get(0).MotionTruckCraneGrabber(down);
        }

        System.out.println(TruckCranes.get(0).getLocalTranslation().x + "XWAARDE");
        if (TruckCranes.get(0).getLocalTranslation().x > 399 && TruckCranes.get(0).getLocalTranslation().x != 380.0f && !down) {
            down = true;
            TruckCranes.get(0).down();
            System.out.println("GASDROP");
        }
        System.out.println(TruckCranes.get(0).getGrabber().getLocalTranslation().y + "YWAARDE");

        if (TruckCranes.get(0).getGrabber().getLocalTranslation().y < -4.5f && TruckCranes.get(0).getGrabber().getLocalTranslation().y > -5) {
            truckup = true;
            TruckCranes.get(0).Go();
        }

        if (truckup == false) {
            Container1.setLocalTranslation(380, 1, -750);
            TruckCranes.get(0).setLocalTranslation(380, 0, -750);
        } else if (truckup == true) {
            Container1.setLocalTranslation(TruckCranes.get(0).getLocalTranslation().x + TruckCranes.get(0).getGrabber().getLocalTranslation().z,
                    TruckCranes.get(0).getLocalTranslation().y + TruckCranes.get(0).getGrabber().getLocalTranslation().y + 6,
                    TruckCranes.get(0).getLocalTranslation().z + TruckCranes.get(0).getGrabber().getLocalTranslation().x);

        }

        System.out.println(TruckCranes.get(0).getGrabber().getLocalTranslation().x + " ZWAARDE");
        if (TruckCranes.get(0).getLocalTranslation().x < 401f && TruckCranes.get(0).getLocalTranslation().x > 399 && TruckCranes.get(0).getGrabber().getLocalTranslation().y < -4.4f) {
            onTarget = true;
            System.out.println("Los!");
            TruckCranes.get(0).GoBack();

        }

        if (onTarget == true) {
            Container1.setLocalTranslation(Trucks.get(0).getLocalTranslation().x,
                    Trucks.get(0).getLocalTranslation().y + 1.5f,
                    Trucks.get(0).getLocalTranslation().z);
        }
    }

    /**
     * Dit is de methode van het verplaatsen van een container, van een Truck
     * naar een AGV Er wordt gekeken naar de posities van de objecten en aan de
     * hand daarvan wordt hun beweging bepaald.
     */
    public void MoveTruckCraneTrucktoAGV() {
        //motiontruckcranegrabber() aangepast!!!!
        if (containerUp == false) {
            Container1.setLocalTranslation(400, 1, -750);
        } else if (containerUp == true) {
            Container1.setLocalTranslation(TruckCranes.get(0).getLocalTranslation().x + TruckCranes.get(0).getGrabber().getLocalTranslation().z,
                                      TruckCranes.get(0).getLocalTranslation().y + TruckCranes.get(0).getGrabber().getLocalTranslation().y + 6,
                                      TruckCranes.get(0).getLocalTranslation().z + TruckCranes.get(0).getGrabber().getLocalTranslation().x);
        }

        if (TruckCranes.get(0).getLocalTranslation().x == 380) {
            TruckCranes.get(0).CranetoTruck();
        }

        if (TruckCranes.get(0).getLocalTranslation().x > 399 && TruckCranes.get(0).getLocalTranslation().x < 400f && !down) {
            down = true;
            TruckCranes.get(0).down2();

        }
        if (TruckCranes.get(0).getGrabber().getLocalTranslation().y < -4.79f && TruckCranes.get(0).getGrabber().getLocalTranslation().y > -5) {
            containerUp = true;
        }
        if (TruckCranes.get(0).getLocalTranslation().x < 401f && TruckCranes.get(0).getLocalTranslation().x > 399 && TruckCranes.get(0).getGrabber().getLocalTranslation().y < -4.4f) {
            TruckCranes.get(0).GoBack();
        }
        if (TruckCranes.get(0).getLocalTranslation().x < 380 && TruckCranes.get(0).getLocalTranslation().x > 379 && down) {
            down = false;
            TruckCranes.get(0).down();
            System.out.println("DOWNBITCH");
        }

        if (TruckCranes.get(0).getLocalTranslation().x < 380 && TruckCranes.get(0).getGrabber().getLocalTranslation().y < -4.4f) {
            onTarget = true;

        }
        if (onTarget) {
            Container1.setLocalTranslation(testAGV.getLocalTranslation().x,
                    testAGV.getLocalTranslation().y + 1.5f,
                    testAGV.getLocalTranslation().z);
        }
    }

    public void MoveStorageCrane() {
        if (containerUp == false) {
            Container1.setLocalTranslation(290, 0, -768);
        }        
        if (StorageCranes.get(0).getLocalTranslation().x == 0) {
            StorageCranes.get(0).CraneMovement();
        }
        if (StorageCranes.get(0).getGrabber().getLocalTranslation().y < -22.9f && !containerUp) {
            containerUp = true;  
            StorageCranes.get(0).getGrabber().attachChild(Container1);
            Container1.RotateContainer(0, FastMath.HALF_PI, 0);
            Container1.setLocalTranslation(0, 23, 0);   
        }        
        System.out.println(Container1.getLocalTranslation().x + " Container");
        if(StorageCranes.get(0).getGrabber().getLocalTranslation().y < -22.9f && StorageCranes.get(0).getLocalTranslation().x < 215.1 && containerUp && !onStore)
        {
            onStore = true;            
            rootNode.attachChild(Container1);
            Container1.setLocalTranslation(215, 0, -768);
            Container1.RotateContainer(0, FastMath.HALF_PI, 0);
        }
       
    }
}
