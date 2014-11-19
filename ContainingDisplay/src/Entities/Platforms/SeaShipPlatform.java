/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Platforms;

import Controller.DisplayController;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Sander
 */
public class SeaShipPlatform extends Platform
{
    Node seaSideWayBox = new Node();
    Node hookEastBox = new Node();
    Node hookWestBox = new Node();
    Geometry seaCraneRails;
    
    public SeaShipPlatform(DisplayController.Quality quality, DisplayController main, AssetManager assetManager)
    {
        SeaSideWay(assetManager, main);
        BigShipRails(assetManager, main);
        HookEast(assetManager, main);
        HookWest(assetManager, main);
    }
    
    public void SeaSideWay(AssetManager assetManager, DisplayController main)
    {
        Box SeaSideWay = new Box(600,0.1f,50);
        Geometry seaSideWayGeom = new Geometry("Box", SeaSideWay);
        Material SeaWayMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        SeaWayMat.setColor("Color", ColorRGBA.Black);
        seaSideWayGeom.setMaterial(SeaWayMat);
        seaSideWayBox.attachChild(seaSideWayGeom);
        seaSideWayGeom.setLocalTranslation(600,0,0);
        main.getRootNode().attachChild(seaSideWayBox); 
    }
    
    public void HookEast(AssetManager assetManager, DisplayController main)
    {
        Box HookEast = new Box(50,0.1f,50);
        Geometry hookEastGeom = new Geometry("Box", HookEast);
        Material hookEastMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        hookEastMat.setColor("Color", ColorRGBA.Black);
        hookEastGeom.setMaterial(hookEastMat);
        hookEastBox.attachChild(hookEastGeom);
        hookEastGeom.setLocalTranslation(1150,0.1f,-100);
        main.getRootNode().attachChild(hookEastBox); 
    }
    
    public void HookWest(AssetManager assetManager, DisplayController main)
    {
        Box HookWest = new Box(50,0.1f,50);
        Geometry hookWestGeom = new Geometry("Box", HookWest);
        Material hookWestMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        hookWestMat.setColor("Color", ColorRGBA.Black);
        hookWestGeom.setMaterial(hookWestMat);
        hookWestBox.attachChild(hookWestGeom);
        hookWestGeom.setLocalTranslation(50,0.1f,-100);
        main.getRootNode().attachChild(hookWestBox); 
    }
    
    public void BigShipRails(AssetManager assetManager, DisplayController main)
     {
         Matrix3f m = new Matrix3f(0,0,1,0,0,0,0,0,0);
         
         for(int i = 1; i < 55; i++)
         {
            seaCraneRails = (Geometry)main.getAssetManager().loadModel("Models/rails/stripRails.j3o");
            main.getRootNode().attachChild(seaCraneRails);
            seaCraneRails.setLocalTranslation(100+18*i,0.1f,-30);
            seaCraneRails.setLocalRotation(m);
         }
     }
}
