package nhl.containing.client;

import nhl.containing.client.entities.Platform;
import nhl.containing.client.entities.platforms.BinnenvaartschipPlatform;
import nhl.containing.client.entities.vehicles.AGV;
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
import java.util.ArrayList;
import nhl.containing.client.ContainingClient.Quality;
import nhl.containing.client.entities.cranes.StorageCrane;
import nhl.containing.client.entities.cranes.TruckCrane;
import nhl.containing.client.entities.platforms.OpslagPlatform;
import nhl.containing.client.entities.platforms.SeaShipPlatform;
import nhl.containing.client.entities.platforms.TrainPlatform;
import nhl.containing.client.entities.platforms.TruckPlatform;

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
    private static Node myRootNode;
    private static AssetManager myAssetManager;
    private static ViewPort myViewPort;
    MotionEvent motionControl;
    public static AGV agv;
    public static ArrayList<AGV> agvs = new ArrayList<AGV>();
    ArrayList<StorageCrane> StorageCranes = new ArrayList<StorageCrane>();
    ArrayList<TruckCrane> TruckCranes = new ArrayList<TruckCrane>();
    ArrayList<Platform> Platforms = new ArrayList<Platform>();
    Node rails;
    
    public static void main(String[] args) {
        ContainingClient app = new ContainingClient();
        app.start();
    }
    
    @Override
    public void simpleInitApp() {
        myAssetManager = assetManager;
        myRootNode = rootNode;
        myViewPort = viewPort;
        rootNode.attachChild(SkyFactory.createSky(assetManager, "Textures/Sky/Bright/BrightSky.dds", false));
        flyCam.setMoveSpeed(200);
        cam.setFrustumFar(5000);
        cam.onFrameChange();
        
        for(int i = 0; i < 5; i++)
        	agvs.add(new AGV(Quality.HIGH));
        
        ConnectionManager.init("localhost", 3000);

        sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);        
        
        //SeaNode sea = new SeaNode();
        //this.getRootNode().attachChild(sea);
        
        Platforms.add(new OpslagPlatform());
        Platforms.add(new SeaShipPlatform());
        Platforms.add(new TrainPlatform());        
        Platforms.add(new TruckPlatform());        
        Platforms.add(new BinnenvaartschipPlatform());
        
        
        for (int i = 0; i < 20; i++) {
            TruckCranes.add(new TruckCrane());
            TruckCranes.get(i).setLocalTranslation(360, 0, -750 + 25 * i);
            TruckCranes.get(i).rotate(0, FastMath.HALF_PI, 0);
        }
        
        for (int i = 0; i < 39; i++) {
            StorageCranes.add(new StorageCrane());
            StorageCranes.get(i).setLocalTranslation(0, 0, -760 + 40 * i);
            StorageCranes.get(i).rotate(0, FastMath.HALF_PI, 0);
        }
    }
    
    @Override
    public void simpleUpdate(float tpf) {
    }

    /**
     * returns the static version of the assetmanager Usage:
     * DisplayController.getMyAssetManager()
     *
     * @return
     */
    public static AssetManager getMyAssetManager() {
        return myAssetManager;
    }

    /**
     * returns the static version of the RootNode Usage:
     * DisplayController.getMyRootNode()
     *
     * @return
     */
    public static Node getMyRootNode() {
        return myRootNode;
    }

    /**
     * returns the static version of the ViewPort Usage:
     * DisplayController.getMyViewPort()
     *
     * @return
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
}
