/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities.vehicles;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Vehicle;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 * 
 * @author Sander
 */
public class Truck extends Vehicle
{
	Node truckNode;

	public Truck(ContainingClient.Quality quality)
	{
		super();
		String qualityPath = "Models/high/truck.j3o";
		switch (quality)
		{
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
		truckNode = (Node) ContainingClient.getMyAssetManager().loadModel(qualityPath);
		attachChild(truckNode);
		ContainingClient.getMyRootNode().attachChild(this);
	}

	public void moveTruck()
	{
		truckNode.setLocalTranslation(1, 0, 0);
	}

	public Vector3f getTruckPosition()
	{
		return this.getLocalTranslation();
	}

}
