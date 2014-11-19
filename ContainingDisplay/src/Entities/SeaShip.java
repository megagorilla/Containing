/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Controller.DisplayController;
import com.jme3.scene.Node;

/**
 *
 * @author Sander
 */
public class SeaShip extends Node{

    public SeaShip(DisplayController.Quality quality, DisplayController main) {
        switch (quality){
            case HIGH:
                attachChild(DisplayController.getMyAssetManager().loadModel("Models/high/ship/seaship.j3o"));
                break;
            case MEDIUM:
                attachChild(DisplayController.getMyAssetManager().loadModel("Models/medium/ship/seaship.j3o"));
                break;
            case LOW:
                attachChild(DisplayController.getMyAssetManager().loadModel("Models/low/ship/seaship.j3o"));
                break;
        }
        
        DisplayController.getMyRootNode().attachChild(this);
    }
    
    
}
