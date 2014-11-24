/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities;

import nhl.containing.client.ContainingClient;

import com.jme3.scene.Node;

/**
 * 
 * @author Sander
 */
public class SeaShip extends Node
{

	public SeaShip(ContainingClient.Quality quality, ContainingClient main)
	{
		switch (quality)
		{
			case HIGH:
				attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/ship/seaship.j3o"));
				break;
			case MEDIUM:
				attachChild(ContainingClient.getMyAssetManager().loadModel("Models/medium/ship/seaship.j3o"));
				break;
			case LOW:
				attachChild(ContainingClient.getMyAssetManager().loadModel("Models/low/ship/seaship.j3o"));
				break;
		}

		ContainingClient.getMyRootNode().attachChild(this);
	}

}
