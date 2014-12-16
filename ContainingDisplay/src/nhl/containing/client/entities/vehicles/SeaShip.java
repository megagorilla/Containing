package nhl.containing.client.entities.vehicles;

import com.jme3.scene.Node;
import java.util.ArrayList;
import nhl.containing.client.ContainingClient;

import static nhl.containing.client.ContainingClient.Quality.*;
import nhl.containing.client.entities.Container;
import nhl.containing.client.entities.Vehicle;

/**
 *
 * @author Sander
 */
public class SeaShip extends Vehicle {
    ArrayList<Container> containers = new ArrayList<Container>();
    int SeaShipID;
   /**
     * creates an SeaShip and loads the model.
     * @param quality the quality of the model
     */
    public SeaShip(ContainingClient.Quality quality, int ID) {
        super();
        SeaShipID = ID;
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
    
    public  void addContainer(Container c){
        this.containers.add(c);
        this.attachChild(containers.get(containers.size()-1));
    }

    public int getSeaShipID() {
        return SeaShipID;
    }
    
    
}
