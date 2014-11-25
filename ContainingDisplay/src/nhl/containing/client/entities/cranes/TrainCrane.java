/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities.cranes;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Crane;

/**
 *
 * @author Sander
 */
public class TrainCrane extends Crane {

    /**
     * creates the TrainCrane and loads the models
     */
    public TrainCrane() {
        super();
        attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/traincrane/crane.j3o"));
        grabber.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/traincrane/grabbingGear.j3o"));
        grabber.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/traincrane/grabbingGearHolder.j3o"));
        grabber.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/traincrane/hookLeft.j3o"));
        grabber.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/traincrane/hookRight.j3o"));
        attachChild(grabber);
        ContainingClient.getMyRootNode().attachChild(this);
    }
}
