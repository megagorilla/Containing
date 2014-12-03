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
import nhl.containing.client.entities.cranes.TruckCrane;
import nhl.containing.client.entities.platforms.SeaShipPlatform;
import nhl.containing.client.entities.platforms.StoragePlatform;
import nhl.containing.client.entities.platforms.TrainPlatform;
import nhl.containing.client.entities.platforms.TruckPlatform;
import nhl.containing.client.entities.vehicles.AGV;
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
        public static ArrayList<TruckCrane> TruckCranes = new ArrayList<TruckCrane>();
        ArrayList<Platform> Platforms = new ArrayList<Platform>();
        ArrayList<AGV> AGVs = new ArrayList<AGV>();
        ArrayList<Truck> Trucks = new ArrayList<Truck>();
        private MotionPath path;
        
        public AGV testAGV;
        
        private boolean up = false;
        private boolean right = false;
        public boolean truckup = false;
        public boolean containerUp = false;
        //public boolean containerBack = false;
        private boolean onTarget = false;
        public boolean down;
        Node rails;
        Container test2;
        Container test3;
        
        public static int containerPositie;
    
    public static void main(String[] args) {
        ContainingClient app = new ContainingClient();
        app.start();
    }
    
    @Override
    public void simpleInitApp() {
        myAssetManager = assetManager;
        myRootNode = rootNode;
        myViewPort = viewPort;
        rootNode.attachChild(SkyFactory.createSky(assetManager, "Scenes/BrightSky.dds", false));
		flyCam.setMoveSpeed(200);
        cam.setFrustumFar(5000);
        cam.onFrameChange();
                cam.setLocation(new Vector3f(300,20,-300));
        
        for(int i = 0; i < 5; i++)
        	agvs.add(new AGV(Quality.HIGH));

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
                
        
        for (int i = 0; i < 20; i++) 
                {
                    TruckCranes.add(new TruckCrane(i));
                    TruckCranes.get(i).setLocalTranslation(380, 0, -750 + 25*i);
                    TruckCranes.get(i).rotate(0, FastMath.HALF_PI, 0);
                    //TruckCranes.get(i).MotionTruckCraneGrabber(down);
                }                
               
                
                for(int i = 0; i < 20; i++)
                {
                    Trucks.add(new Truck(quality));
                    Trucks.get(i).setLocalTranslation(400, 0, -750+25*i);
                    Trucks.get(i).rotate(0, FastMath.HALF_PI, 0);
        }
        
        for (int i = 0; i < 39; i++) 
        {
            StorageCranes.add(new StorageCrane());
            StorageCranes.get(i).setLocalTranslation(0, 0, -760 + 40 * i);
            StorageCranes.get(i).rotate(0, FastMath.HALF_PI, 0);
            StorageCranes.get(i).MotionY();
        }
                
                
                
        
            test2 = new Container(quality);
            test2.rotate(0, FastMath.HALF_PI, 0);
            
//            test3 = new Container(quality);
//            test3.rotate(0,FastMath.HALF_PI, 0);
            
            testAGV = new AGV(quality);
            testAGV.setLocalTranslation(380, 0, -750);
            testAGV.rotate(0,FastMath.HALF_PI, 0);
            
            //MoveTruckCraneTrucktoAGV();
               
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        
     // if(test2.getLocalTranslation().x == 380)
     // {
        MoveTruckCraneAGVtoTruck();  
    //  }
     // else if(test2.getLocalTranslation().x == 400)
     // {
        //MoveTruckCraneTrucktoAGV();
     // }
    //    System.out.println(TruckCranes.get(0).getLocalTranslation().x + "XWAARDE");
        
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
    
    public void MoveTruckCraneAGVtoTruck()
    {
        if(TruckCranes.get(0).getGrabber().getLocalTranslation().y > 0 && TruckCranes.get(0).getLocalTranslation().x == 380.0f)
        {
            TruckCranes.get(0).MotionTruckCraneGrabber(down);
        }
        
        System.out.println(TruckCranes.get(0).getLocalTranslation().x + "XWAARDE");
        if(TruckCranes.get(0).getLocalTranslation().x > 399 && TruckCranes.get(0).getLocalTranslation().x != 380.0f && !down)
        {
            down = true;
            TruckCranes.get(0).down();
            System.out.println("GASDROP");
        }
            System.out.println(TruckCranes.get(0).getGrabber().getLocalTranslation().y + "YWAARDE");

             if(TruckCranes.get(0).getGrabber().getLocalTranslation().y < -4.5f && TruckCranes.get(0).getGrabber().getLocalTranslation().y > -5)
             {
                 truckup = true;
                 TruckCranes.get(0).Go();
             }
             
             if(truckup == false)
             {
                 test2.setLocalTranslation(380, 1, -750 );                 
                 TruckCranes.get(0).setLocalTranslation(380, 0,-750);
             }
             else  if(truckup == true)
             {
                test2.setLocalTranslation(TruckCranes.get(0).getLocalTranslation().x + TruckCranes.get(0).getGrabber().getLocalTranslation().z,
                                          TruckCranes.get(0).getLocalTranslation().y + TruckCranes.get(0).getGrabber().getLocalTranslation().y+6,
                                          TruckCranes.get(0).getLocalTranslation().z + TruckCranes.get(0).getGrabber().getLocalTranslation().x);
               
             }
             
             System.out.println(TruckCranes.get(0).getGrabber().getLocalTranslation().x + " ZWAARDE");
             if(TruckCranes.get(0).getLocalTranslation().x < 401f && TruckCranes.get(0).getLocalTranslation().x > 399 && TruckCranes.get(0).getGrabber().getLocalTranslation().y < -4.4f)
             {
                 onTarget = true;
                 System.out.println("Los!");
                 TruckCranes.get(0).GoBack();
                 
             }
             
             if(onTarget == true)
             {
                 test2.setLocalTranslation(Trucks.get(0).getLocalTranslation().x, 
                                           Trucks.get(0).getLocalTranslation().y+1.5f, 
                                           Trucks.get(0).getLocalTranslation().z);             }
    }
    
    public void MoveTruckCraneTrucktoAGV()
    {
        //motiontruckcranegrabber() aangepast!!!!
        if(containerUp == false)
        {
            test2.setLocalTranslation(400, 1, -750 );
        }
        else if(containerUp == true)
        {
            test2.setLocalTranslation(TruckCranes.get(0).getLocalTranslation().x + TruckCranes.get(0).getGrabber().getLocalTranslation().z,
                                      TruckCranes.get(0).getLocalTranslation().y + TruckCranes.get(0).getGrabber().getLocalTranslation().y+6,
                                      TruckCranes.get(0).getLocalTranslation().z + TruckCranes.get(0).getGrabber().getLocalTranslation().x);
        }
         
         if(TruckCranes.get(0).getLocalTranslation().x == 380)
         {
            TruckCranes.get(0).CranetoTruck();
            TruckCranes.get(0).MotionTruckCraneGrabber2(down);
         }
         
         if(TruckCranes.get(0).getLocalTranslation().x > 399 && TruckCranes.get(0).getLocalTranslation().x < 400f && !down)
         {
            down = true;
            TruckCranes.get(0).down2();
            
         }
         if(TruckCranes.get(0).getGrabber().getLocalTranslation().y < -4.5f && TruckCranes.get(0).getGrabber().getLocalTranslation().y > -5)
         {
             containerUp = true;
         }
         if(TruckCranes.get(0).getLocalTranslation().x < 401f && TruckCranes.get(0).getLocalTranslation().x > 399 && TruckCranes.get(0).getGrabber().getLocalTranslation().y < -4.4f)
         {
            TruckCranes.get(0).GoBack(); 
         }
         if(TruckCranes.get(0).getLocalTranslation().x < 380 && TruckCranes.get(0).getLocalTranslation().x > 379 && down)
         {
             down = false;
             TruckCranes.get(0).down();
             System.out.println("DOWNBITCH");
         }
         
         if(TruckCranes.get(0).getLocalTranslation().x < 380 && TruckCranes.get(0).getGrabber().getLocalTranslation().y < -4.4f)
         {  
             onTarget = true;
             
         }
         if(onTarget)
         {
             test2.setLocalTranslation(testAGV.getLocalTranslation().x,
                                       testAGV.getLocalTranslation().y+1.5f,
                                       testAGV.getLocalTranslation().z);
         }
    }
}
