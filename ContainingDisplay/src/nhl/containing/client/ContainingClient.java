package nhl.containing.client;

import nhl.containing.client.entities.Platform;
import nhl.containing.client.entities.platforms.BinnenvaartschipPlatform;
import nhl.containing.client.entities.platforms.TrainPlatform;
import nhl.containing.client.entities.vehicles.AGV;
import nhl.containing.client.network.ConnectionManager;
import nhl.containing.client.scenery.SeaNode;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
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
    // private Quality quality = Quality.HIGH; TODO: Currently not used
    private static Node myRootNode;
    private static AssetManager myAssetManager;
    private static ViewPort myViewPort;
	MotionEvent motionControl;
	public static AGV agv;
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
        flyCam.setMoveSpeed(100);
        cam.setFrustumFar(5000);
        cam.onFrameChange();
		
		agv = new AGV(Quality.HIGH);
		
		ConnectionManager.init("localhost", 3000);

        //Platform test = new BinnenvaartschipPlatform();
		//Platform test1 = new TrainPlatform();
		//test1.setLocalTranslation(100, 0, 0);
        sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);        
		
        SeaNode sea = new SeaNode();

        //this.getRootNode().attachChild(test);
        //this.getRootNode().attachChild(sea);
		//this.getRootNode().attachChild(sea);
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
