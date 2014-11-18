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
 * @author Sander
 */
public class TruckCrane extends Crane {

    public TruckCrane() {
        super();
        attachChild(DisplayController.getMyAssetManager().loadModel("Models/high/crane/truckcrane/crane.j3o"));
        grabber.attachChild(DisplayController.getMyAssetManager().loadModel("Models/high/crane/truckcrane/grabbingGear.j3o"));
        grabber.attachChild(DisplayController.getMyAssetManager().loadModel("Models/high/crane/truckcrane/hookLeft.j3o"));
        grabber.attachChild(DisplayController.getMyAssetManager().loadModel("Models/high/crane/truckcrane/hookRight.j3o"));
        attachChild(grabber);
        DisplayController.getMyRootNode().attachChild(this);
    }
}
