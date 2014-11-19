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
public class TrainPlatform extends Platform {

    Geometry trainRailsNode;   
    Geometry craneRailsNode;
    Node groundBox = new Node();
    Node parkingBox = new Node();

    public TrainPlatform(DisplayController.Quality quality, DisplayController main, AssetManager assetManager) 
    {
        for(int i = 0; i < 275; i++)
        {
            trainRailsNode = (Geometry)main.getAssetManager().loadModel("Models/rails/trainRails.j3o");
            attachChild(trainRailsNode);
            main.getRootNode().attachChild(this);
            trainRailsNode.setLocalTranslation(-10,0.1f, -3050+11*i);
        }
        Ground(assetManager, main);
        ParkingSpace(assetManager, main);
        CraneRails(quality, main, assetManager);
    }
    
    public void Ground(AssetManager assetManager, DisplayController main)
    {
        Box Ground = new Box(50, 0.1f, 1550);
        Geometry groundGeom = new Geometry("Box", Ground);
        Material groundMat =  new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        groundMat.setColor("Color", ColorRGBA.DarkGray);
        groundGeom.setMaterial(groundMat);
        groundBox.attachChild(groundGeom);
        groundGeom.setLocalTranslation(25,0,-1550);
        main.getRootNode().attachChild(groundBox);
    }
    
    public void CraneRails(DisplayController.Quality quality, DisplayController main, AssetManager assetManager)
    {
        for(int i = 0; i < 162; i++)
        {
            craneRailsNode = (Geometry)main.getAssetManager().loadModel("Models/rails/craneRails.j3o");
            attachChild(craneRailsNode);
            main.getRootNode().attachChild(this);
            craneRailsNode.setLocalTranslation(15,0.1f, -100-18*i);
        }
    }
    
    public void ParkingSpace(AssetManager assetManager, DisplayController main)
    {
        Box ParkingSpace = new Box(10,0.1f,1450);
        Geometry parkingGeom = new Geometry("Box", ParkingSpace);
        Material parkingMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        parkingMat.setColor("Color", ColorRGBA.Gray);
        parkingGeom.setMaterial(parkingMat);
        parkingBox.attachChild(parkingGeom);
        parkingGeom.setLocalTranslation(40, 0.001f, -1550);
        main.getRootNode().attachChild(parkingBox);        
    }
}
