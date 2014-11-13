package Controller;

import Materials.TexturedMaterial;
import com.jme3.app.SimpleApplication;
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
import com.jme3.util.SkyFactory;
/**
 * test
 * @author normenhansen
 */
public class DisplayController extends SimpleApplication {
Train train1;
TexturedMaterial trainMat;
DirectionalLight sun;

    public static void main(String[] args) {
        DisplayController app = new DisplayController();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        viewPort.setBackgroundColor(ColorRGBA.White);
        rootNode.attachChild(SkyFactory.createSky(
            assetManager, "Textures/Sky/Bright/BrightSky.dds", false));
        flyCam.setMoveSpeed(100);
        
        trainMat = new TexturedMaterial(176 ,assetManager);
        train1 = new Train(new Vector3f(10, 10, 10),"Treintjeeee" , assetManager, trainMat);
        rootNode.attachChild(train1);
            /** A white, directional light source */ 
        sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)).normalizeLocal());
        sun.setColor(ColorRGBA.White.clone().multLocal(2));
        rootNode.addLight(sun);
        
        SeaNode sea = new SeaNode(this);
        sea.setLocalTranslation(0, -100, 0);
        rootNode.attachChild(sea);
    }

    @Override
    public void simpleUpdate(float tpf) {
        train1.rotate(0f, 0.003f, 0f);
    }

    public DirectionalLight getSun() {
        return sun;
    }

    
    
    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
