/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.materials.PlainMaterial;

import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Node;

/**
 * 
 * @author Yannick
 */
public class Container extends Node
{
	Node containerNode;

	public Container(ContainingClient.Quality qualtiy)
	{
		String modelPath = "Models/high/container/container.j3o";
		switch (qualtiy)
		{
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
		try
		{
			containerNode = (Node) ContainingClient.getMyAssetManager().loadModel(modelPath);
		}
		catch (Exception e)
		{
			containerNode = (Node) ContainingClient.getMyAssetManager().loadModel("Models/medium/container/container.j3o");
		}

		containerNode.setMaterial(new PlainMaterial(ColorRGBA.randomColor()));
		attachChild(containerNode);
		ContainingClient.getMyRootNode().attachChild(this);
	}

}
