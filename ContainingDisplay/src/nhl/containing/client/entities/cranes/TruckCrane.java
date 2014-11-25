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
public class TruckCrane extends Crane {

    /**
     * creates the TruckCrane and loads all the models
     */
    public TruckCrane() {
        super();
        attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/truckcrane/crane.j3o"));
        grabber.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/truckcrane/grabbingGear.j3o"));
        grabber.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/truckcrane/hookLeft.j3o"));
        grabber.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/truckcrane/hookRight.j3o"));
        attachChild(grabber);
        ContainingClient.getMyRootNode().attachChild(this);
    }
}
