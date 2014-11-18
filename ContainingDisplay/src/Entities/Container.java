/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


import Controller.DisplayController;
import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import Materials.*;
/**
 *
 * @author Yannick
 */
public class Container extends Node{
    Node containerNode;

    
    public Container(DisplayController.Quality qualtiy,ColorRGBA color, DisplayController main)
    {
        String modelPath = "Models/high/container/container.j3o";
        switch (qualtiy){
            case LOW:
                modelPath = "Models/low/container/container.j3o";
                break;
            case MEDIUM:
                modelPath = "Models/medium/container/container.j3o";
                break;
            case HIGH:
                modelPath = "Models/high/container/container.j3o";
                break;
        }
         try{
            containerNode = (Node)DisplayController.getmyAssetManager().loadModel(modelPath);
         }catch(Exception e){
            containerNode = (Node)DisplayController.getmyAssetManager().loadModel("Models/medium/container/container.j3o");
         }
         
         containerNode.setMaterial(new PlainMaterial(color, DisplayController.getmyAssetManager()));
         attachChild(containerNode);
         DisplayController.getMyRootNode().attachChild(this);
    }
    
    
}

