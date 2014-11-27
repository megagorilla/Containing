package nhl.containing.client;

import nhl.containing.client.entities.Platform;
import nhl.containing.client.entities.platforms.RiverShipPlatform;
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
import com.sun.org.apache.bcel.internal.generic.FASTORE;
import java.util.ArrayList;
import nhl.containing.client.ContainingClient.Quality;
import nhl.containing.client.entities.Container;
import nhl.containing.client.entities.Crane;
import nhl.containing.client.entities.cranes.StorageCrane;
import nhl.containing.client.entities.cranes.TruckCrane;
import nhl.containing.client.entities.platforms.StoragePlatform;
import nhl.containing.client.entities.platforms.SeaShipPlatform;
import nhl.containing.client.entities.platforms.TrainPlatform;
import nhl.containing.client.entities.platforms.TruckPlatform;
import nhl.containing.client.entities.vehicles.AGV;
import nhl.containing.client.entities.vehicles.Truck;

/**
 * test
 * 
 * @author Yannick
 */
public class ContainingClient extends SimpleApplication
{
	private DirectionalLight sun;

	public enum Quality
	{
		LOW, MEDIUM, HIGH
	};

	private Quality quality = Quality.HIGH;
        //private ContainingClient main = new ContainingClient();
	private static Node myRootNode;
	private static AssetManager myAssetManager;
	private static ViewPort myViewPort;
        
        ArrayList<TruckCrane> TruckCranes = new ArrayList<TruckCrane>();
        ArrayList<StorageCrane> StorageCranes = new ArrayList<StorageCrane>();
        ArrayList<Platform> Platforms = new ArrayList<Platform>();
        ArrayList<AGV> AGVs = new ArrayList<AGV>();
        ArrayList<Truck> Trucks = new ArrayList<Truck>();
        
        private MotionPath path;
        private MotionEvent motionControl;
        Container test;
        Container test2;
        
        private boolean up = false;
        private boolean right = false;
        private boolean truckup = false;

	Node rails;

	public static void main(String[] args)
	{
		ContainingClient app = new ContainingClient();
		app.start();
	}

	@Override
	public void simpleInitApp()
	{
		myAssetManager = assetManager;
		myRootNode = rootNode;
		myViewPort = viewPort;
		rootNode.attachChild(SkyFactory.createSky(assetManager, "Textures/Sky/Bright/BrightSky.dds", false));
		flyCam.setMoveSpeed(300);
		cam.setFrustumFar(5000);
		cam.onFrameChange();
                cam.setLocation(new Vector3f(300,20,-300));

		sun = new DirectionalLight();
		sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)).normalizeLocal());
		sun.setColor(ColorRGBA.White);
		rootNode.addLight(sun);
                
		SeaNode sea = new SeaNode(); 
                
                Platforms.add(new StoragePlatform());
                Platforms.add(new SeaShipPlatform());
                Platforms.add(new TrainPlatform());                        
                Platforms.add(new TruckPlatform());                
		Platforms.add(new RiverShipPlatform());
                
                
                for(int i = 0; i < 20; i++)
                {
                    TruckCranes.add(new TruckCrane());
                    TruckCranes.get(i).setLocalTranslation(380, 0, -750 + 25*i);
                    TruckCranes.get(i).rotate(0, FastMath.HALF_PI, 0);
                    TruckCranes.get(i).MotionTruckCraneGrabber();
                }
                
                for(int i = 0; i < 20; i++)
                {
                    AGVs.add(new AGV(quality));
                    AGVs.get(i).setLocalTranslation(380, 0, -750+25*i);
                    AGVs.get(i).rotate(0, FastMath.HALF_PI, 0);
                }
                
                for(int i = 0; i < 20; i++)
                {
                    Trucks.add(new Truck(quality));
                    Trucks.get(i).setLocalTranslation(400, 0, -750+25*i);
                    Trucks.get(i).rotate(0, FastMath.HALF_PI, 0);
                }
                
                for(int i = 0; i < 39; i++)
                {
                    StorageCranes.add(new StorageCrane());
                    StorageCranes.get(i).setLocalTranslation(0, 0, -760 + 40*i);
                    StorageCranes.get(i).rotate(0, FastMath.HALF_PI, 0);
                    StorageCranes.get(i).MotionY();
                }
                
                
                //for(int i=0;i<21;i++)
                //{
                    test2 = new Container(quality);
                    test2.setLocalTranslation(380, 1, -750); //+25*i
                    test2.rotate(0, FastMath.HALF_PI, 0);
                //}
                
                //test2 = new Container(quality);
                //test2.setLocalTranslation(0, 1, -8);
                //test2.rotate(0, FastMath.HALF_PI, 0);
	}

	@Override
	public void simpleUpdate(float tpf)
	{      
             /*if(up == false)
             {
                 test2.setLocalTranslation(                   
                 0,
                 0,
                 -8);
             }
             //System.out.println(StorageCranes.get(19).getGrabber().getLocalTranslation().y);

             if(StorageCranes.get(19).getGrabber().getLocalTranslation().y < 2.7f && StorageCranes.get(19).getGrabber().getLocalTranslation().y > 1.0f)
             {
                 up = true;
                 right = false;
                 System.out.println("l");
             }             
             if(up)
             {             
                test.setLocalTranslation(
                     StorageCranes.get(19).getGrabber().getLocalTranslation().x-8, 
                     StorageCranes.get(19).getGrabber().getLocalTranslation().y-2, 
                     StorageCranes.get(19).getGrabber().getLocalTranslation().z-8);
             }*/
             
             if(TruckCranes.get(0).getGrabber().getLocalTranslation().y < -4.5f && TruckCranes.get(0).getGrabber().getLocalTranslation().y > -5)
             {
                 truckup = true;
                 System.out.println("jaaaaaa");
             }
             
             /*if(truckup == false)
             {
                 test2.setLocalTranslation(                   
                 0,
                 0,
                 -8);
             }*/
             if(truckup == true)
             {
                test2.setLocalTranslation(TruckCranes.get(0).getGrabber().getLocalTranslation().x,
                                          TruckCranes.get(0).getGrabber().getLocalTranslation().y,
                                          TruckCranes.get(0).getGrabber().getLocalTranslation().z);
             }
	}

	/**
	 * returns the static version of the assetmanager Usage: DisplayController.getMyAssetManager()
	 * 
	 * @return
	 */
	public static AssetManager getMyAssetManager()
	{
		return myAssetManager;
	}

	/**
	 * returns the static version of the RootNode Usage: DisplayController.getMyRootNode()
	 * 
	 * @return
	 */
	public static Node getMyRootNode()
	{
		return myRootNode;
	}

	/**
	 * returns the static version of the ViewPort Usage: DisplayController.getMyViewPort()
	 * 
	 * @return
	 */
	public static ViewPort getMyViewPort()
	{
		return myViewPort;
	}

	public DirectionalLight getSun()
	{
		return sun;
	}

	@Override
	public void simpleRender(RenderManager rm)
	{
		// TODO: add render code
	}
}
