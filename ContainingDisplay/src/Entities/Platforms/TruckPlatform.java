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
public class TruckPlatform extends Platform
{
    Node groundBox = new Node();
    Node parkingBox = new Node();
    
    public TruckPlatform(DisplayController.Quality quality, DisplayController main, AssetManager assetManager)
    {
        Ground(assetManager, main);
        ParkingSpace(assetManager, main);
    }
    
    public void Ground(AssetManager assetManager, DisplayController main)
    {
        Box Ground = new Box(40, 0.1f, 725);
        Geometry groundGeom = new Geometry("Box", Ground);
        Material groundMat =  new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        groundMat.setColor("Color", ColorRGBA.DarkGray);
        groundGeom.setMaterial(groundMat);
        groundBox.attachChild(groundGeom);
        groundGeom.setLocalTranslation(10,0,700);
        main.getRootNode().attachChild(groundBox);
    }
    
     public void ParkingSpace(AssetManager assetManager, DisplayController main)
    {
        for(int i = 0; i < 56; i++)
        {
        Box ParkingSpace = new Box(10,0.1f,5);
        Geometry parkingGeom = new Geometry("Box", ParkingSpace);
        Material parkingMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        parkingMat.setColor("Color", ColorRGBA.Gray);
        parkingGeom.setMaterial(parkingMat);
        parkingBox.attachChild(parkingGeom);
        parkingGeom.setLocalTranslation(-5, 0.001f, 0 + 25*i);
        main.getRootNode().attachChild(parkingBox);
        }
        
    }
}
