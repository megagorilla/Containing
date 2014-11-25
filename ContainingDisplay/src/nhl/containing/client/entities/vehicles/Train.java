/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities.vehicles;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Vehicle;

import com.jme3.scene.Node;
import java.util.ArrayList;
import static nhl.containing.client.ContainingClient.Quality.*;

/**
 *
 * @author Sander
 */
public class Train extends Vehicle {

    /**
     * Creates a train with wagons ans loads all the models
     *
     * @param quality the quality of the models (in ContainingClient.Quality)
     * @param nrOfWagons amount of wagons behind the train
     */
    public Train(ContainingClient.Quality quality, int nrOfWagons) {
        super();
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

        ArrayList<Node> wagonNodes = new ArrayList<Node>();
        for (int i = 0; i < nrOfWagons; i++) {
            wagonNodes.add(new Node());
            wagonNodes.get(i).attachChild(ContainingClient.getMyAssetManager().loadModel(qualityPath));
            wagonNodes.get(i).setLocalTranslation(0, 0, -13.2f - 18.4f * i);
            attachChild(wagonNodes.get(i));
        }

        ContainingClient.getMyRootNode().attachChild(this);
    }
}
