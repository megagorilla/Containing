package Controller;

import Materials.TexturedMaterial;
import Entities.Cranes.LittleCrane;
import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingVolume;
import com.jme3.collision.CollisionResult;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import Entities.*;
import Entities.Cranes.*;
import Entities.Platforms.*;
import Entities.Vehicles.*;
import Materials.*;
import Scenery.SeaNode;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.util.SkyFactory;
import java.util.ArrayList;
/**
 * test
 * @author Yannick
 */
public class DisplayController extends SimpleApplication {
    DirectionalLight sun;
    
    public static void main(String[] args) 
    {
        DisplayController app = new DisplayController();
        app.start();    
    }
    
    @Override
    public void simpleInitApp(){
        rootNode.attachChild(SkyFactory.createSky(
            assetManager, "Textures/Sky/Bright/BrightSky.dds", false));
        
        ArrayList<Container> containers = new ArrayList<Container>();
        containers.add(new Container(ColorRGBA.randomColor(), assetManager));
        rootNode.attachChild(containers.get(0));
        
        sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun); 
        
        SeaNode sea = new SeaNode(this);
        
    }

    @Override
    public void simpleUpdate(float tpf) 
    {
        
    }

    public DirectionalLight getSun() {
        return sun;
    }
        
    

    
    
    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
