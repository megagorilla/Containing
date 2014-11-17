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
    
    public AGV(DisplayController main) {
        super();
        agvNode = (Node)main.getAssetManager().loadModel("Models/high/agv/agv.j3o");
        attachChild(agvNode);
        main.getRootNode().attachChild(this);
    }
    
    
    
}
