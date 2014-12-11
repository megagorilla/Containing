package nhl.containing.client.entities.rails;

import nhl.containing.client.ContainingClient;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 * 
 * @author Sander
 */
public class CraneRails extends Node
{

	/**
	 * Loads the model for the CraneRails and puts them in a node
	 * 
	 * @param location x 42 for DockingCrane
	 * @param width ~0.87f for DockingCrane
         * @param yAngleRot rotation for the craneRails in radians
	 */
	public CraneRails(Vector3f location, float width, Float yAngleRot)
	{
		attachChild(ContainingClient.getMyAssetManager().loadModel("Models/rails/craneRails.j3o"));
		this.setLocalTranslation(location);
		this.scale(width, 1, 1);
		this.rotate(0, yAngleRot, 0);
		ContainingClient.getMyRootNode().attachChild(this);
	}

}
