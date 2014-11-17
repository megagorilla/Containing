/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Cranes;

import Entities.CMaterials;
import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingVolume;
import com.jme3.collision.CollisionResults;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Yannick
 */
public class LittleCrane extends Crane
{
    Material craneMaterial;
    Material loaderMat;
    Box cranePoleL;
    Box cranePoleR;
    Box craneHead;
    Box loader;
    Geometry Loader;
    Geometry CranePoleL;
    Geometry CranePoleR;
    Geometry CraneHead;
    
    public LittleCrane(Vector3f Location, String Name, AssetManager assetManager)
    {
        super();
        cranePoleL = new Box(0.5f,16,1);
        cranePoleR = new Box(0.5f,16,1);
        craneHead = new Box(20,0.5f,1);
        loader = new Box(0.3f,0.3f,0.3f);
        
        craneMaterial = new CMaterials(ColorRGBA.Blue, assetManager);
        loaderMat = new CMaterials(ColorRGBA.White, assetManager);
        
        CranePoleL = new Geometry("CranePoleL", cranePoleL);        
        CranePoleL.setMaterial(craneMaterial);
        CranePoleL.setLocalTranslation(-20, 0, 0);
        attachChild(CranePoleL);
        
        CranePoleR = new Geometry("CranePoleR", cranePoleR);        
        CranePoleR.setMaterial(craneMaterial);
        CranePoleR.setLocalTranslation(20, 0, 0);
        attachChild(CranePoleR);
        
        CraneHead = new Geometry("CraneHead", craneHead);
        CraneHead.setMaterial(craneMaterial);
        CraneHead.setLocalTranslation(0, 15, 0);
        attachChild(CraneHead);
        
        Loader = new Geometry("Loader", loader);
        Loader.setMaterial(loaderMat);
        Loader.setLocalTranslation(5, 1, 0);
        attachChild(Loader);
    }
    //20 breed
    //16 hoog
    
    public void moveLoader(Vector3f position)
    {
        Loader.setLocalTranslation(position);
    }
    
    public Vector3f getLoaderPosition()
    {
        return Loader.getLocalTranslation();
    }
    
}
