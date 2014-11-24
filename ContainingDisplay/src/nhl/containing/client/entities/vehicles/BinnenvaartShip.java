/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities.vehicles;

import nhl.containing.client.ContainingClient;
import static nhl.containing.client.ContainingClient.Quality.HIGH;
import static nhl.containing.client.ContainingClient.Quality.LOW;
import static nhl.containing.client.ContainingClient.Quality.MEDIUM;
import nhl.containing.client.entities.Vehicle;

/**
 *
 * @author Sander
 */
public class BinnenvaartShip extends Vehicle {

    public BinnenvaartShip(ContainingClient.Quality quality) {
        String qualityPath = "Models/high/ship/seaship.j3o";
        switch (quality) {
            case HIGH:
                qualityPath = "Models/high/ship/seaship.j3o";
                break;
            case MEDIUM:
                qualityPath = "Models/medium/ship/seaship.j3o";
                break;
            case LOW:
                qualityPath = "Models/low/ship/seaship.j3o";
                break;
        }
        attachChild(ContainingClient.getMyAssetManager().loadModel(qualityPath));
        this.scale(0.5f);
        ContainingClient.getMyRootNode().attachChild(this);
    }
}
