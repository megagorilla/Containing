package nhl.containing.client.entities.vehicles;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.ContainingClient.Quality;
import nhl.containing.client.entities.Container;
import nhl.containing.client.entities.Vehicle;
import nhl.containing.client.materials.PlainMaterial;

import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 * @author Sander
 */
public class AGV extends Vehicle {

	private Container container;
	
    /**
     * Creates the model for an AGV
     *
     * @param qualtiy the quality for the model
     */
    public AGV(ContainingClient.Quality qualtiy) {
        super();
        String qualityPath = "Models/high/agv/agv.j3o";
        switch (qualtiy) {
            case HIGH:
                qualityPath = "Models/high/agv/agv.j3o";
                break;
            case MEDIUM:
                qualityPath = "Models/medium/agv/agv.j3o";
                break;
            case LOW:
                qualityPath = "Models/low/agv/agv.j3o";
                break;
        }
        attachChild(ContainingClient.getMyAssetManager().loadModel(qualityPath));

        //ColourChaning Code
        Node subNodes = (Node) children.get(0);
        subNodes.getChild(0).setMaterial(new PlainMaterial(new ColorRGBA(1, 192f / 255f, 203f / 255f, 1f))); //mainBodyColour
        subNodes.getChild(1).setMaterial(new PlainMaterial(ColorRGBA.White)); //WheelColour1
        if (qualtiy != Quality.LOW) {
            subNodes.getChild(2).setMaterial(new PlainMaterial(ColorRGBA.LightGray)); //wheelcolour2
            if (qualtiy == Quality.HIGH) {
                subNodes.getChild(3).setMaterial(new PlainMaterial(ColorRGBA.White)); //bodyColour2
            }
        }
        ContainingClient.getMyRootNode().attachChild(this);
    }
    
    public void setContainer(Container c)
    {
    	container = c;
    	container.setLocalTranslation(new Vector3f(0, 1.2f, 0));
    	this.attachChild(container);
    }
    
    public Container getContainer()
    {
    	return this.container;
    }
    
    public void removeContainer()
    {
    	container = null;
    	this.detachChild(container);
    }
}
