package nhl.containing.client.entities.vehicles;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Container;
import nhl.containing.client.entities.Vehicle;
import nhl.containing.client.materials.PlainMaterial;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;

/**
 *
 * @author Sander
 */
public class Truck extends Vehicle
{
	Container c;
	
	/**
     * creates a Truck and loads the models
     * @param quality the quality for the model;
     */
    public Truck(ContainingClient.Quality quality) {
        super();
        String qualityPath = "Models/high/truck.j3o";
        switch (quality) {
            case HIGH:
                qualityPath = "Models/high/truck.j3o";
                break;
            case MEDIUM:
                qualityPath = "Models/medium/truck.j3o";
                break;
            case LOW:
                qualityPath = "Models/low/truck.j3o";
                break;
        }
        attachChild(ContainingClient.getMyAssetManager().loadModel(qualityPath));
        
        //Colour Changing
        Node subNodes = (Node) children.get(0);
        
        switch (quality) {
            case HIGH:
                subNodes.getChild(0).setMaterial(new PlainMaterial(new ColorRGBA(1, 20f / 255f, 147f / 255f, 1f))); //WindScreen Colour
                subNodes.getChild(3).setMaterial(new PlainMaterial(new ColorRGBA(1, 20f / 255f, 147f / 255f, 1f))); //mainBodyColour
                break;
            case MEDIUM:
                subNodes.getChild(0).setMaterial(new PlainMaterial(new ColorRGBA(1, 20f / 255f, 147f / 255f, 1f))); //WindScreen Colour
                subNodes.getChild(2).setMaterial(new PlainMaterial(new ColorRGBA(1, 20f / 255f, 147f / 255f, 1f))); //mainBodyColour
                break;
            case LOW:
                subNodes.getChild(1).setMaterial(new PlainMaterial(new ColorRGBA(1, 20f / 255f, 147f / 255f, 1f))); //mainBodyColour
                break;
        }
        
        ContainingClient.getMyRootNode().attachChild(this);
    }
    
    public void addContainer(Container c)
    {
    	this.c = c;
    	this.attachChild(c);
    }

	public Container getContainer()
	{
		return c;
	}
}
