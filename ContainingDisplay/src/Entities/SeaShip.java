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
public class SeaShip extends CEntity{

    public SeaShip(DisplayController.Quality quality, DisplayController main) {
        switch (quality){
            case HIGH:
                attachChild(main.getAssetManager().loadModel("Models/high/ship/seaship.j3o"));
                break;
            case MEDIUM:
                attachChild(main.getAssetManager().loadModel("Models/medium/ship/seaship.j3o"));
                break;
            case LOW:
                attachChild(main.getAssetManager().loadModel("Models/low/ship/seaship.j3o"));
                break;
        }
        
        main.getRootNode().attachChild(this);
    }
    
    
}
