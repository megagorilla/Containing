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
public class Train extends Vehicle
{
	Node trainNode;
	Node wagonNode;
	ContainingClient.Quality quality;
	int nrOfWagons;

	public Train(ContainingClient.Quality quality, int nrOfWagons)
	{
		super();
		this.quality = quality;
		this.nrOfWagons = nrOfWagons;
		String qualityPath = "";
		switch (quality)
		{
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
		trainNode = (Node) ContainingClient.getMyAssetManager().loadModel(qualityPath);
		attachChild(trainNode);
		ContainingClient.getMyRootNode().attachChild(this);

		// trainNode.setLocalTranslation(0, 0, 0);
		Wagon();
	}

	public void Wagon()
	{
		for (int i = 0; i < nrOfWagons; i++)
		{
			String qualityPath = "";
			switch (quality)
			{
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
			wagonNode = (Node) ContainingClient.getMyAssetManager().loadModel(qualityPath);
			attachChild(wagonNode);

			wagonNode.setLocalTranslation(0, 0, -13.2f - 18.4f * i);
		}
		ContainingClient.getMyRootNode().attachChild(this);
	}

}
