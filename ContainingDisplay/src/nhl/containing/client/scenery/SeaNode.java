package nhl.containing.client.scenery;

import nhl.containing.client.ContainingClient;

import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.water.SimpleWaterProcessor;

/**
 * @author Sander
 */
public class SeaNode extends Node
{
	public SeaNode()
	{
		// create processor
		SimpleWaterProcessor waterProcessor = new SimpleWaterProcessor(ContainingClient.getMyAssetManager());
		waterProcessor.setReflectionScene(ContainingClient.getMyRootNode());
		ContainingClient.getMyViewPort().addProcessor(waterProcessor);

		// create water quad
		Spatial waterPlane = (Spatial) ContainingClient.getMyAssetManager().loadModel("Models/WaterTest/WaterTest.mesh.xml");
		waterPlane.setMaterial(waterProcessor.getMaterial());
		waterPlane.setLocalScale(2000);
		this.setLocalTranslation(0, -20, 0);

		attachChild(waterPlane);
	}
}
