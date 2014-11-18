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
public class StripRails extends Rails{
    
    public StripRails(Vector3f location,DisplayController main) {
        super();
        attachChild(main.getAssetManager().loadModel("Models/rails/stripRails.j3o"));
        this.setLocalTranslation(location);
        main.getRootNode().attachChild(this);
    }
}
