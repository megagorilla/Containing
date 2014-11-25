/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities.vehicles;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Vehicle;

import com.jme3.scene.Node;

/**
 * 
 * @author Sander
 */
public class AGV extends Vehicle
{

	public AGV(ContainingClient.Quality qualtiy)
	{
		super();
		String qualityPath = "Models/high/agv/agv.j3o";
		switch (qualtiy)
		{
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
		ContainingClient.getMyRootNode().attachChild(this);
	}

}
