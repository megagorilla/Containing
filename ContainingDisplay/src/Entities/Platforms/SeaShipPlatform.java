/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Platforms;

import Controller.DisplayController;
import Entities.Rails.CraneRails;
import Entities.Rails.StripRails;
import Materials.PlainMaterial;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Sander
 */
public class SeaShipPlatform extends Platform
{
    
    public SeaShipPlatform()
    {
        SeaSideWay();
        BigShipRails();
        HookEast();
        HookWest();
        DisplayController.getMyRootNode().attachChild(this);
    }
    
    private void SeaSideWay()
    {
        Box SeaSideWay = new Box(600,5f,50);
        Geometry seaSideWayGeom = new Geometry("Box", SeaSideWay);
        seaSideWayGeom.setMaterial(new PlainMaterial(ColorRGBA.Black));
        attachChild(seaSideWayGeom);
        seaSideWayGeom.setLocalTranslation(600,-5,0);
    }
    
    private void HookEast()
    {
        Box HookEast = new Box(50,5f,50);
        Geometry hookEastGeom = new Geometry("Box", HookEast);
        hookEastGeom.setMaterial(new PlainMaterial(ColorRGBA.Black));
        attachChild(hookEastGeom);
        hookEastGeom.setLocalTranslation(1150,-5f,-100);
    }
    
    private void HookWest()
    {
        Box HookWest = new Box(50,5f,50);
        Geometry hookWestGeom = new Geometry("Box", HookWest);
        hookWestGeom.setMaterial(new PlainMaterial(ColorRGBA.Black));
        attachChild(hookWestGeom);
        hookWestGeom.setLocalTranslation(50,-5f,-100);
    }
    
    private void BigShipRails()
     {
         for(int i = 1; i < 55; i++)
         {
            attachChild(new CraneRails(new Vector3f(100+18*i,0f,30),0.87f, FastMath.HALF_PI));
         }
     }
}
