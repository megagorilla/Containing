/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Vehicles;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import Controller.*;

/**
 *
 * @author Sander
 */
public class AGV extends Vehicle{
    Node agvNode;
    
    public AGV(DisplayController.Quality qualtiy,DisplayController main) {
        super();
        String qualityPath = "Models/high/agv/agv.j3o";
        switch (qualtiy){
            case HIGH:
                qualityPath = "Models/high/agv/agv.j3o";
                break;
            case MEDIUM:
                qualityPath = "Models/medium/agv/agv.j3o";
                break;
            case LOW:
                qualityPath = "Models/low/agv/agv.j3o";
                break;
        }
        agvNode = (Node)DisplayController.getmyAssetManager().loadModel(qualityPath);
        attachChild(agvNode);
        DisplayController.getMyRootNode().attachChild(this);
    }
    
    
    
}
