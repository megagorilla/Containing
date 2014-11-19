/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Platforms;

import Controller.DisplayController;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Sander
 */
public class binnenvaartschipPlatform 
{
    Node sideWayBox = new Node();
    Node hookNorthBox = new Node();
    Node hookSouthBox = new Node();
    Geometry craneRails;
    
    public binnenvaartschipPlatform(DisplayController.Quality quality, DisplayController main, AssetManager assetManager)
    {
        SideWay(assetManager, main);
        HookNorth(assetManager, main);
        HookSouth(assetManager, main);
        littleShipRails(assetManager, main);
    }
    
    public void SideWay(AssetManager assetManager, DisplayController main)
    {        
        Box SideWay = new Box(20,0.1f,725);
        Geometry sideWayGeom = new Geometry("Box", SideWay);
        Material WayMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        WayMat.setColor("Color", ColorRGBA.Black);
        sideWayGeom.setMaterial(WayMat);
        sideWayBox.attachChild(sideWayGeom);
        sideWayGeom.setLocalTranslation(0,0,-725);
        main.getRootNode().attachChild(sideWayBox);        
    }
    
    public void HookNorth(AssetManager assetManager, DisplayController main)
    {
        Box HookNorth = new Box(30,0.1f,20);
        Geometry hookNorthGeom = new Geometry("Box", HookNorth);
        Material hookNorthMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        hookNorthMat.setColor("Color", ColorRGBA.Black);
        hookNorthGeom.setMaterial(hookNorthMat);
        hookNorthBox.attachChild(hookNorthGeom);
        hookNorthGeom.setLocalTranslation(-50,0.1f,-1430);
        main.getRootNode().attachChild(hookNorthBox); 
    }
    
    public void HookSouth(AssetManager assetManager, DisplayController main)
    {
        Box HookSouth = new Box(30,0.1f,20);
        Geometry hookSouthGeom = new Geometry("Box", HookSouth);
        Material hookSouthMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        hookSouthMat.setColor("Color", ColorRGBA.Black);
        hookSouthGeom.setMaterial(hookSouthMat);
        hookSouthBox.attachChild(hookSouthGeom);
        hookSouthGeom.setLocalTranslation(-50,0.1f,-20);
        main.getRootNode().attachChild(hookSouthBox); 
    }
    
     public void littleShipRails(AssetManager assetManager, DisplayController main)
     {
         for(int i = 1; i < 76; i++)
         {
            craneRails = (Geometry)main.getAssetManager().loadModel("Models/rails/craneRails.j3o");
            main.getRootNode().attachChild(craneRails);
            craneRails.setLocalTranslation(-7,0.1f, -40-18*i);
         }
     }
}
