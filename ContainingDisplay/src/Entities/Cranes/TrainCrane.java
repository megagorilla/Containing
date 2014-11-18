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
public class TrainCrane extends Crane {

    public TrainCrane() {
        super();
        attachChild(DisplayController.getmyAssetManager().loadModel("Models/high/crane/traincrane/crane.j3o"));
        grabber.attachChild(DisplayController.getmyAssetManager().loadModel("Models/high/crane/traincrane/grabbingGear.j3o"));
        grabber.attachChild(DisplayController.getmyAssetManager().loadModel("Models/high/crane/traincrane/grabbingGearHolder.j3o"));
        grabber.attachChild(DisplayController.getmyAssetManager().loadModel("Models/high/crane/traincrane/hookLeft.j3o"));
        grabber.attachChild(DisplayController.getmyAssetManager().loadModel("Models/high/crane/traincrane/hookRight.j3o"));
        attachChild(grabber);
        DisplayController.getMyRootNode().attachChild(this);
    }
}
