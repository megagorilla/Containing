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
public class RiverShip extends Vehicle {

    /**
     * creates an RiverShip and loads the model.
     * Rivership is the same as SeaShip, but made smaller (0,5)
     * @param quality the quality of the model
     */
    public RiverShip(ContainingClient.Quality quality) {
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
        this.scale(0.5f);
        ContainingClient.getMyRootNode().attachChild(this);
    }
}
