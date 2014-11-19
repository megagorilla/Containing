/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Platforms;

import Controller.DisplayController;
import Materials.PlainMaterial;
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
    
    public TruckPlatform()
    {
        Ground();
        ParkingSpace();
    }
    
    private void Ground()
    {
        Box Ground = new Box(40, 5f, 725);
        Geometry groundGeom = new Geometry("Box", Ground);
        groundGeom.setMaterial(new PlainMaterial(ColorRGBA.DarkGray));
        groundBox.attachChild(groundGeom);
        groundGeom.setLocalTranslation(10,-5,700);
        DisplayController.getMyRootNode().attachChild(groundBox);
    }
    
     private void ParkingSpace()
    {
        for(int i = 0; i < 20; i++)
        {
        Box ParkingSpace = new Box(10,0.1f,5);
        Geometry parkingGeom = new Geometry("Box", ParkingSpace);
        parkingGeom.setMaterial(new PlainMaterial(ColorRGBA.Gray));
        parkingBox.attachChild(parkingGeom);
        parkingGeom.setLocalTranslation(-5, 0.01f, 0 + 25*i);
        
        
        }
        attachChild(parkingBox);
        DisplayController.getMyRootNode().attachChild(this);
        
    }
}
