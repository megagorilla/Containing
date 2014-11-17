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
TexturedMaterial trainMat;
DirectionalLight sun;

    static Vector3f Direction = new Vector3f();
    Boolean right = true;
    Boolean left = false;
    LittleCrane supplyCrane;
    
    public static void main(String[] args) 
    {
        DisplayController app = new DisplayController();
        app.start();    
    }
    
    @Override
    public void simpleInitApp() 
        viewPort.setBackgroundColor(ColorRGBA.White);
        rootNode.attachChild(SkyFactory.createSky(
            assetManager, "Textures/Sky/Bright/BrightSky.dds", false));
        flyCam.setMoveSpeed(100);
        

        
        AGV agv = new AGV(assetManager);
        rootNode.attachChild(agv);
        agv.setLocalTranslation(0, 10, 0);
        
        ArrayList<Container> containers = new ArrayList();
        
        for(int i = 0;i<10;i++){
            containers.add(new Container(ColorRGBA.randomColor(), assetManager));
            rootNode.attachChild(containers.get(i));
            containers.get(i).setLocalTranslation(0, 16+(14.2f*i), 0);
        }
        
        
        sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)).normalizeLocal());
        sun.setColor(ColorRGBA.White.clone().multLocal(2));
        rootNode.addLight(sun);
        
        SeaNode sea = new SeaNode(this);
        sea.setLocalTranslation(0, -100, 0);
        rootNode.attachChild(sea);
        
        
    }

    @Override
    public void simpleUpdate(float tpf) 
    {
        supplyCrane.moveLoader(new Vector3f(supplyCrane.getLoaderPosition().x + 0.001f, (supplyCrane.getLoaderPosition().y), (supplyCrane.getLoaderPosition().z)));
    }
        

    public DirectionalLight getSun() {
        return sun;
    }

    
    
    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
