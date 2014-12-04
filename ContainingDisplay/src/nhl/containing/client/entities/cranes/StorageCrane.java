package nhl.containing.client.entities.cranes;

import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.scene.Node;
import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Crane;
import nhl.containing.client.materials.PlainMaterial;

/**
 * 
 * @author Yannick
 */
public class StorageCrane extends Crane
{
        
    /**
     * creates the storageCrane and loads the models for the grabber and the crane
     */
	public StorageCrane()
	{
		super();
		attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/storagecrane/crane.j3o"));
                Node grabber3 = new Node();
		grabber3.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/storagecrane/grabbingGear.j3o"));
		grabber3.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/storagecrane/grabbingGearHolder.j3o"));
		grabber3.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/storagecrane/hookLeft.j3o"));
		grabber3.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/storagecrane/hookRight.j3o"));
                grabber3.setLocalTranslation(0, -26, 0);
                grabber.attachChild(grabber3);
		attachChild(grabber);
                
                //set colour of the crane
                Node subNodes = (Node) children.get(0);
                subNodes.getChild(0).setMaterial(new PlainMaterial(new ColorRGBA(1, 20f / 255f, 147f / 255f, 1f))); //mainBodyColour
                subNodes.getChild(1).setMaterial(new PlainMaterial(ColorRGBA.White)); //wheelColour
                        
		ContainingClient.getMyRootNode().attachChild(this);
	}
}
