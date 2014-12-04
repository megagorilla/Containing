package nhl.containing.client.entities.vehicles;

import nhl.containing.client.ContainingClient;

import static nhl.containing.client.ContainingClient.Quality.*;
import nhl.containing.client.entities.Vehicle;

/**
 *
 * @author Sander
 */
public class SeaShip extends Vehicle {

    /**
     * creates an SeaShip and loads the model.
     * @param quality the quality of the model
     */
    public SeaShip(ContainingClient.Quality quality) {
        super();
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
        ContainingClient.getMyRootNode().attachChild(this);
    }
}
