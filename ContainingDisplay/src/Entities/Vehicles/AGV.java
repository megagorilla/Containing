/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Vehicles;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;

/**
 *
 * @author Sander
 */
public class AGV extends Vehicle{
    Node agvNode;
    
    public AGV(AssetManager assetManager) {
        super();
        agvNode = (Node)assetManager.loadModel("Models/high/agv/agv.j3o");
        agvNode.scale(5);
        attachChild(agvNode);
    }
    
    
    
}
