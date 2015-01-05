package nhl.containing.client.entities.vehicles;

import com.jme3.scene.Node;
import java.util.ArrayList;
import nhl.containing.client.ContainingClient;
import static nhl.containing.client.ContainingClient.Quality.HIGH;
import static nhl.containing.client.ContainingClient.Quality.LOW;
import static nhl.containing.client.ContainingClient.Quality.MEDIUM;
import nhl.containing.client.entities.Container;
import nhl.containing.client.entities.Vehicle;

/**
 *
 * @author Sander
 */
public class Barge extends Vehicle {
    ArrayList<Container> containers = new ArrayList<Container>();
    int bargeID;
    
    /**
     * creates an RiverShip and loads the model.
     * Rivership is the same as SeaShip, but made smaller (0,5)
     * @param quality the quality of the model
     */
    public Barge(ContainingClient.Quality quality, int ID) {
        super();
        Node ship = new Node();
        bargeID = ID;
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
        ship.attachChild(ContainingClient.getMyAssetManager().loadModel(qualityPath));
        ship.scale(0.2f);
        attachChild(ship);
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

    public int getbargeID() {
        return bargeID;
    }
}
