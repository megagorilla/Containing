package nhl.containing.client.entities.vehicles;

import java.util.ArrayList;

import nhl.containing.client.ContainingClient;
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
    
    public Container getContainerAt(int ID){
        for(int i = 0;i<containers.size();i++)
        {
            if(containers.get(i).getID() == ID)
                return containers.get(i);
        }
        return null;
    }

    public int getSeaShipID() {
        return SeaShipID;
    }
    
    
}
