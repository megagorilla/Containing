package Controller;

import Materials.TexturedMaterial;
import Entities.Cranes.StorageCrane;
import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingVolume;
import com.jme3.collision.CollisionResult;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import Entities.*;
import Entities.Cranes.*;
import Entities.Platforms.*;
import Entities.Rails.*;
import Entities.Vehicles.*;
import Materials.*;
import Scenery.SeaNode;
import com.jme3.asset.AssetManager;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.util.SkyFactory;
import java.util.ArrayList;
import Connectivity.ConnectionManager;
import Connectivity.UpdateMessage;
/**
 * test
 * @author Yannick
 */
public class DisplayController extends SimpleApplication {
    private DirectionalLight sun;
    public enum Quality {LOW,MEDIUM,HIGH};
    private Quality quality = Quality.HIGH;
    private static Node myRootNode;
    private static AssetManager myAssetManager;
    private static ViewPort myViewPort;
    
    Node rails;
    public static void main(String[] args) 
    {
        DisplayController app = new DisplayController();
        app.start();
    }
    
    @Override
    public void simpleInitApp(){
        myAssetManager = assetManager;
        myRootNode = rootNode;
        myViewPort = viewPort;
        rootNode.attachChild(SkyFactory.createSky(
            assetManager, "Textures/Sky/Bright/BrightSky.dds", false));
        flyCam.setMoveSpeed(10);
        
        Crane crane = new DockingCrane(quality);
        rails = new CraneRails(new Vector3f(42, 0, 0), 0.87f, 0f);
        sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
        
        SeaNode sea = new SeaNode();
        
    }

    @Override
    public void simpleUpdate(float tpf) 
    {
        
    }

    /**
     * returns the static version of the assetmanager
     * Usage: DisplayController.getMyAssetManager()
     * @return 
     */
    public static AssetManager getMyAssetManager() {
        return myAssetManager;
    }

    /**
     * returns the static version of the RootNode
     * Usage: DisplayController.getMyRootNode()
     * @return 
     */
    public static Node getMyRootNode() {
        return myRootNode;
    }

    /**
     * returns the static version of the ViewPort
     * Usage: DisplayController.getMyViewPort()
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
        //TODO: add render code
    }
}
