package nhl.containing.client.entities;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.materials.PlainMaterial;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;

/**
 *
 * @author Yannick
 */
public class Container extends Node {

    Node containerNode = new Node();
    int ID;
    public static final float length = 13.4f;
    public static final float width = 2.55f;
    public static final float height = 2.9f;
    public Container(ContainingClient.Quality qualtiy, int ID) {
        this.ID = ID;
        String modelPath = "Models/high/container/container.j3o";
        switch (qualtiy) {
            case LOW:
                modelPath = "Models/low/container/container.j3o";
                break;
            case MEDIUM:
                modelPath = "Models/medium/container/container.j3o";
                break;
            case HIGH:
                modelPath = "Models/high/container/container.j3o";
                break;
        }
        containerNode.attachChild(ContainingClient.getMyAssetManager().loadModel(modelPath));
        containerNode.setMaterial(new PlainMaterial(ColorRGBA.randomColor()));
        attachChild(containerNode);
        ContainingClient.getMyRootNode().attachChild(this);
    }
    
    public void RotateContainer(float x, float y, float z){
        containerNode.rotate(x, y, z);
    }

    public int getID() {
        return ID;
    }
    
    
}
