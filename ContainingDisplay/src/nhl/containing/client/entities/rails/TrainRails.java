/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities.rails;

import nhl.containing.client.ContainingClient;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 * 
 * @author Sander
 */
public class TrainRails extends Node
{

	/**
	 * Loads the model for the TrainRails and puts them in a node
	 * 
	 * @param location the location of the TrainRails
         * @param yAngleRot rotation for the Rails in radians
	 */
	public TrainRails(Vector3f location, Float yAngleRot)
	{
		attachChild(ContainingClient.getMyAssetManager().loadModel("Models/rails/trainRails.j3o"));
		this.setLocalTranslation(location);
		this.rotate(0, yAngleRot, 0);
		ContainingClient.getMyRootNode().attachChild(this);
	}
}