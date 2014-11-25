/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities.vehicles;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Vehicle;

import com.jme3.scene.Node;
import java.util.ArrayList;
import static nhl.containing.client.ContainingClient.Quality.HIGH;
import static nhl.containing.client.ContainingClient.Quality.LOW;
import static nhl.containing.client.ContainingClient.Quality.MEDIUM;

/**
 *
 * @author Sander
 */
public class Train extends Vehicle {

    ArrayList<Node> wagonNodes = new ArrayList<Node>();
    ContainingClient.Quality quality;
    int nrOfWagons;

    public Train(ContainingClient.Quality quality, int nrOfWagons) {
        super();
        this.quality = quality;
        this.nrOfWagons = nrOfWagons;
        String qualityPath = "Models/high/train/train.j3o";
        switch (quality) {
            case HIGH:
                qualityPath = "Models/high/train/train.j3o";
                break;
            case MEDIUM:
                qualityPath = "Models/medium/train/train.j3o";
                break;
            case LOW:
                qualityPath = "Models/low/train/train.j3o";
                break;
        }
        attachChild(ContainingClient.getMyAssetManager().loadModel(qualityPath));
        
        Wagon();
        ContainingClient.getMyRootNode().attachChild(this);
    }

    private void Wagon() {
        String qualityPath = "Models/high/train/wagon.j3o";
        switch (quality) {
            case HIGH:
                qualityPath = "Models/high/train/wagon.j3o";
                break;
            case MEDIUM:
                qualityPath = "Models/medium/train/wagon.j3o";
                break;
            case LOW:
                qualityPath = "Models/low/train/wagon.j3o";
                break;
        }
        
        for (int i = 0; i < nrOfWagons; i++) {
            wagonNodes.add(new Node());
            wagonNodes.get(i).attachChild(ContainingClient.getMyAssetManager().loadModel(qualityPath));
            wagonNodes.get(i).setLocalTranslation(0, 0, -13.2f - 18.4f * i);
            attachChild(wagonNodes.get(i));
        }
    }
}
