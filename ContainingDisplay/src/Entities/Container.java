/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import Materials.*;
/**
 *
 * @author Yannick
 */
public class Container extends CEntity{
    Node containerNode;

    
    public Container(ColorRGBA color, AssetManager assetManager)
    {
         containerNode = (Node)assetManager.loadModel("Models/high/container/container.j3o");
         containerNode.setMaterial(new PlainMaterial(color, assetManager));
         attachChild(containerNode);
    }
}

