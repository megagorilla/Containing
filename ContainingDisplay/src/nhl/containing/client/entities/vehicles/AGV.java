/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities.vehicles;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Vehicle;
import nhl.containing.client.materials.PlainMaterial;


/**
 * 
 * @author Sander
 */
public class AGV extends Vehicle
{
        /**
         * Creates the model for an AGV
         * @param qualtiy the quality for the model
         */
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
                Node subNodes = (Node) children.get(0);
                subNodes.getChild(0).setMaterial(new PlainMaterial(new ColorRGBA(1, 192f/255f, 203f/255f, 1f))); //mainBodyColor
                subNodes.getChild(1).setMaterial(new PlainMaterial(ColorRGBA.White)); //WheelColor1
                subNodes.getChild(2).setMaterial(new PlainMaterial(ColorRGBA.LightGray)); //wheelcolor2
                subNodes.getChild(3).setMaterial(new PlainMaterial(ColorRGBA.White)); //bodyColor2
		ContainingClient.getMyRootNode().attachChild(this);
	}

}
