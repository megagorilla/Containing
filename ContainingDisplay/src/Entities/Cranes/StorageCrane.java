/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Cranes;

import Controller.DisplayController;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

/**
 *
 * @author Yannick
 */
public class StorageCrane extends Crane {

    public StorageCrane() {
        super();
        attachChild(DisplayController.getmyAssetManager().loadModel("Models/high/crane/storagecrane/crane.j3o"));
        grabber.attachChild(DisplayController.getmyAssetManager().loadModel("Models/high/crane/storagecrane/grabbingGear.j3o"));
        grabber.attachChild(DisplayController.getmyAssetManager().loadModel("Models/high/crane/storagecrane/grabbingGearHolder.j3o"));
        grabber.attachChild(DisplayController.getmyAssetManager().loadModel("Models/high/crane/storagecrane/hookLeft.j3o"));
        grabber.attachChild(DisplayController.getmyAssetManager().loadModel("Models/high/crane/storagecrane/hookRight.j3o"));
        attachChild(grabber);
        DisplayController.getMyRootNode().attachChild(this);
    }
}
