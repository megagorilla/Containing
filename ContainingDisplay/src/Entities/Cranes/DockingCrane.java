/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Cranes;

import com.jme3.scene.Node;
import Controller.*;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

/**
 *
 * @author Yannick
 */
public class DockingCrane extends Crane {

    public DockingCrane(DisplayController.Quality qualtiy, DisplayController main) {
        String craneBasePath = "Models/high/crane/dockingcrane/crane.j3o";
        String grabbingGearPath = "Models/high/crane/dockingcrane/grabbingGear.j3o";
        String grabbingGearHolderPath = "Models/high/crane/dockingcrane/grabbingGearHolder.j3o";
        String hookLeftPath = "Models/high/crane/dockingcrane/hookLeft.j3o";
        String hookRightPath = "Models/high/crane/dockingcrane/hookRight.j3o";

        switch (qualtiy) {
            case HIGH:
                craneBasePath = "Models/high/crane/dockingcrane/crane.j3o";
                grabbingGearPath = "Models/high/crane/dockingcrane/grabbingGear.j3o";
                grabbingGearHolderPath = "Models/high/crane/dockingcrane/grabbingGearHolder.j3o";
                hookLeftPath = "Models/high/crane/dockingcrane/hookLeft.j3o";
                hookRightPath = "Models/high/crane/dockingcrane/hookRight.j3o";
                break;
            case MEDIUM:
                craneBasePath = "Models/medium/crane/dockingcrane/crane.j3o";
                grabbingGearPath = "Models/medium/crane/dockingcrane/grabbingGear.j3o";
                grabbingGearHolderPath = "Models/medium/crane/dockingcrane/grabbingGearHolder.j3o";
                hookLeftPath = "Models/medium/crane/dockingcrane/hookLeft.j3o";
                hookRightPath = "Models/medium/crane/dockingcrane/hookRight.j3o";
                break;
            case LOW:
                craneBasePath = "Models/low/crane/dockingcrane/crane.j3o";
                grabbingGearPath = "Models/low/crane/dockingcrane/grabbingGear.j3o";
                grabbingGearHolderPath = "Models/low/crane/dockingcrane/grabbingGearHolder.j3o";
                hookLeftPath = "Models/low/crane/dockingcrane/hookLeft.j3o";
                hookRightPath = "Models/low/crane/dockingcrane/hookRight.j3o";
                break;
        }

        

        attachChild(DisplayController.getmyAssetManager().loadModel(craneBasePath));
        grabber.attachChild(DisplayController.getmyAssetManager().loadModel(grabbingGearPath));
        grabber.attachChild(DisplayController.getmyAssetManager().loadModel(grabbingGearHolderPath));
        grabber.attachChild(DisplayController.getmyAssetManager().loadModel(hookLeftPath));
        grabber.attachChild(DisplayController.getmyAssetManager().loadModel(hookRightPath));

        attachChild(grabber);
        DisplayController.getMyRootNode().attachChild(this);
    }
}
