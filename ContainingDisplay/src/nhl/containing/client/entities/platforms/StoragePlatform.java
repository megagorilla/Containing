/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities.platforms;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import nhl.containing.client.entities.Platform;

import com.jme3.scene.shape.Box;
import nhl.containing.client.ContainingClient;
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
        ContainingClient.getMyRootNode().attachChild(this); 
    }
    
    private void ContainerStocking()
    {
        for(int i = 0; i < 39; i++)
        {
        Box Lane = new Box(225, 0.2f, 10);
        Geometry laneGeom = new Geometry("Box", Lane);
        laneGeom.setMaterial(new PlainMaterial(ColorRGBA.Gray));
        attachChild(laneGeom);
        laneGeom.setLocalTranslation(0 ,0,-760+ 40*i);
        }
    }
    
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
    
    public void Road()
    {
        Box Road = new Box(WIDTH + ROADWIDTH, DEPTH, HEIGHT + ROADWIDTH);
        Geometry roadGeom = new Geometry("Box", Road);
        roadGeom.setMaterial(new PlainMaterial(ColorRGBA.Black));
        attachChild(roadGeom);
        roadGeom.setLocalTranslation(0,-(DEPTH+0.1f),0);
    }

}
