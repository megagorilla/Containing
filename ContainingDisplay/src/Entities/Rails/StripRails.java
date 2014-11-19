/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Rails;

import Controller.DisplayController;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 *
 * @author Sander
 */
public class StripRails extends Node {

    /**
     * Loads the model for the StripRails and puts them in a node
     * @param location where the striprails is located
     * @param yAngleRot the rotation for the y angle
     */
    public StripRails(Vector3f location, Float yAngleRot) {
        attachChild(DisplayController.getMyAssetManager().loadModel("Models/rails/stripRails.j3o"));
        this.setLocalTranslation(location);
        this.rotate(0, yAngleRot, 0);
        DisplayController.getMyRootNode().attachChild(this);
    }
}
