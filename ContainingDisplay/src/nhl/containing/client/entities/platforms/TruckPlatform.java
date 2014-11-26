/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities.platforms;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Platform;
import nhl.containing.client.materials.PlainMaterial;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import nhl.containing.client.entities.cranes.StorageCrane;

/**
 * 
 * @author Sander
 */
public class TruckPlatform extends Platform
{
	Node groundBox = new Node();
	Node parkingBox = new Node();

	public TruckPlatform()
	{
		Ground();
		ParkingSpace();
		ContainingClient.getMyRootNode().attachChild(this);
	}

	private void Ground()
	{
		Box Ground = new Box(50, 5f, 300);
		Geometry groundGeom = new Geometry("Box", Ground);
		groundGeom.setMaterial(new PlainMaterial(ColorRGBA.DarkGray));
		groundBox.attachChild(groundGeom);
		groundGeom.setLocalTranslation(400, -5, -525);
		ContainingClient.getMyRootNode().attachChild(groundBox);
	}

	private void ParkingSpace()
	{
		for (int i = 0; i < 20; i++)
		{
			Box ParkingSpace = new Box(10, 0.1f, 5);
			Geometry parkingGeom = new Geometry("Box", ParkingSpace);
			parkingGeom.setMaterial(new PlainMaterial(ColorRGBA.Gray));
			parkingBox.attachChild(parkingGeom);
			parkingGeom.setLocalTranslation(360, 0.01f, -750 + 25 * i);

		}
		attachChild(parkingBox);

	}
        
      
}