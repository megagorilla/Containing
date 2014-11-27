/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities.vehicles;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Vehicle;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 *
 * @author Sander
 */
public class Truck extends Vehicle {

	/**
     * creates a Truck and loads the models
     * @param quality the quality for the model;
     */
    public Truck(ContainingClient.Quality quality) {
        super();
        String qualityPath = "Models/high/truck.j3o";
        switch (quality) {
            case HIGH:
                qualityPath = "Models/high/truck.j3o";
                break;
            case MEDIUM:
                qualityPath = "Models/medium/truck.j3o";
                break;
            case LOW:
                qualityPath = "Models/low/truck.j3o";
                break;
        }
        attachChild(ContainingClient.getMyAssetManager().loadModel(qualityPath));
        ContainingClient.getMyRootNode().attachChild(this);
    }
}
