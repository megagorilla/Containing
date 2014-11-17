/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import Entities.*;
import Materials.PlainMaterial;
import com.jme3.math.ColorRGBA;

/**
 *
 * @author Sander
 */
public class Container extends CEntity{
    Node containerNode;
    
    public Container(AssetManager assetManager) {
        super();
        containerNode = (Node)assetManager.loadModel("Models/high/container/container.j3o");
        containerNode.scale(5);
        attachChild(containerNode);
    }
    
    public Container(ColorRGBA color, AssetManager assetManager){
        super();
        containerNode = (Node)assetManager.loadModel("Models/high/container/container.j3o");
        containerNode.scale(5);
        containerNode.setMaterial(new PlainMaterial(color, assetManager));
        attachChild(containerNode);
    }
    
    
    
}
