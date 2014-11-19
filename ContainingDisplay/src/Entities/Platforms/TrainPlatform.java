/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Platforms;

import Controller.DisplayController;
import Entities.Rails.*;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import java.util.ArrayList;
import Entities.Rails.*;
import Materials.PlainMaterial;
import com.jme3.math.Vector3f;

/**
 *
 * @author Sander
 */
public class TrainPlatform extends Platform {

    ArrayList<TrainRails> TrainRailsLst = new ArrayList(); 
    ArrayList<CraneRails> CraneRailsLst = new ArrayList();
    
    
    Node groundBox = new Node();
    Node parkingBox = new Node();

    public TrainPlatform() 
    {
        super();
        for(int i = 0; i < 30; i++)
        {
            TrainRailsLst.add(new TrainRails(new Vector3f(7,0f, 100-11.25f*i), 0f));
            attachChild(TrainRailsLst.get(TrainRailsLst.size()-1));
        }
        
        Ground();
        ParkingSpace();
        CraneRails();
    }
    
    private void Ground()
    {
        Box Ground = new Box(20, 5f, 300);
        Geometry groundGeom = new Geometry("Box", Ground);
        groundGeom.setMaterial(new PlainMaterial(ColorRGBA.DarkGray));
        groundGeom.setLocalTranslation(10,-5,0);
        attachChild(groundGeom);
        DisplayController.getMyRootNode().attachChild(this);
    }
    
    private void CraneRails()
    {
        for(int i = 0; i < 15; i++)
        {
            CraneRailsLst.add(new CraneRails(new Vector3f(15,0f, 100-18.3f*i), 1f, 0f));
            attachChild(CraneRailsLst.get(CraneRailsLst.size()-1));
        }
    }
    
    private void ParkingSpace()
    {
        Box ParkingSpace = new Box(3,0.1f,200);
        Geometry parkingGeom = new Geometry("Box", ParkingSpace);
        parkingGeom.setMaterial(new PlainMaterial(ColorRGBA.Gray));
        parkingGeom.setLocalTranslation(15, 0.01f,0);
        attachChild(parkingGeom);
        DisplayController.getMyRootNode().attachChild(this);
    }
}
