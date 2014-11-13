package Controller;

import Entities.Container;
import Entities.Cranes.LittleCrane;
import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingVolume;
import com.jme3.collision.CollisionResult;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;


/**
 * test
 * @author Yannick
 */
public class DisplayController extends SimpleApplication {

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
    {
        
        flyCam.setMoveSpeed(30);
        Container container = new Container(Direction, "Container", assetManager);
        rootNode.attachChild(container);
        
        supplyCrane = new LittleCrane(Direction, "Crane", assetManager);
        rootNode.attachChild(supplyCrane);        
        
    }

    @Override
    public void simpleUpdate(float tpf) 
    {
        supplyCrane.moveLoader(new Vector3f(supplyCrane.getLoaderPosition().x + 0.001f, (supplyCrane.getLoaderPosition().y), (supplyCrane.getLoaderPosition().z)));
    }
        

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
