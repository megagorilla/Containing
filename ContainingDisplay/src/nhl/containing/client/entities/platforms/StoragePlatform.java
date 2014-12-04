package nhl.containing.client.entities.platforms;

import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import nhl.containing.client.entities.Platform;

import com.jme3.scene.shape.Box;
import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.rails.StripRails;
import nhl.containing.client.materials.PlainMaterial;

/**
 * 
 * @author Sander
 */
public class StoragePlatform extends Platform
{    
    public static final int WIDTH = 300;
    public static final int DEPTH = 5;
    public static final int HEIGHT = 775;
    private static final int parkinglots = 39;
    public static final int ROADWIDTH = 20;
    
    public StoragePlatform()
    {
        Box Opslag = new Box(WIDTH,DEPTH,HEIGHT);
        Geometry opslagGeom = new Geometry("Box", Opslag);
        opslagGeom.setMaterial(new PlainMaterial(ColorRGBA.DarkGray));
        attachChild(opslagGeom);
        opslagGeom.setLocalTranslation(0,-DEPTH,0);
        
        ContainerStocking();        
        AGVParking();
        Road();
        Rails();
        ContainingClient.getMyRootNode().attachChild(this); 
    }
    /**
     * 39 lanes for the storage of containers
     */
    private void ContainerStocking()
    {
        for(int i = 0; i < parkinglots; i++)
        {
        Box Lane = new Box(225, 0.2f, 10);
        Geometry laneGeom = new Geometry("Box", Lane);
        laneGeom.setMaterial(new PlainMaterial(ColorRGBA.Gray));
        attachChild(laneGeom);
        laneGeom.setLocalTranslation(0 ,0,-760+ 40*i);
        }
    }
    
    /**
     * parking lots for the AGV's, next to the storagelanes.
     */
    private void AGVParking()
    {
        for (int a = 0; a < 2; a++)
        {
        for(int i = 0; i < parkinglots; i++)
        {
            Box Lane = new Box(30, 0.2f, 10);
            Geometry laneGeom = new Geometry("Box", Lane);
            laneGeom.setMaterial(new PlainMaterial(ColorRGBA.White));
            attachChild(laneGeom);
            laneGeom.setLocalTranslation(267.5f - 535*a ,0,-760+ 40*i);
        }
        }
    }
    
    /**
     * road for the AGV's to drive on
     */
    private void Road()
    {
        Box Road = new Box(WIDTH + ROADWIDTH, DEPTH, HEIGHT + ROADWIDTH);
        Geometry roadGeom = new Geometry("Box", Road);
        roadGeom.setMaterial(new PlainMaterial(ColorRGBA.Black));
        attachChild(roadGeom);
        roadGeom.setLocalTranslation(0,-(DEPTH+0.1f),0);
    }
    
    /**
     * rail units between the parking lanes.
     */
    private void Rails(){
        for(int i = 0; i<39;i++){
            for(int j = 0;j<32;j++){
                attachChild(new StripRails(new Vector3f(288.25f -18.3f*j ,0,-762.5f+ 40*i), FastMath.HALF_PI));
            }
            attachChild(new StripRails(new Vector3f(-288.05f ,0,-762.5f+ 40*i), FastMath.HALF_PI));
        }
        
    }

}
