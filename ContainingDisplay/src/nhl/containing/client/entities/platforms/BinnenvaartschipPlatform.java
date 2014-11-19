/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities.platforms;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Platform;
import nhl.containing.client.entities.rails.CraneRails;
import nhl.containing.client.materials.PlainMaterial;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 * 
 * @author Sander
 */
public class BinnenvaartschipPlatform extends Platform
{
	Node sideWayBox = new Node();
	Node hookNorthBox = new Node();
	Node hookSouthBox = new Node();
	Geometry craneRails;

	public BinnenvaartschipPlatform()
	{
		SideWay();
		HookNorth();
		HookSouth();
		littleShipRails();
	}

	private void SideWay()
	{
		Box SideWay = new Box(20, 0.1f, 725);
		Geometry sideWayGeom = new Geometry("Box", SideWay);
		sideWayGeom.setMaterial(new PlainMaterial(ColorRGBA.Black));
		sideWayBox.attachChild(sideWayGeom);
		sideWayGeom.setLocalTranslation(0, 0, -725);
		ContainingClient.getMyRootNode().attachChild(sideWayBox);
	}

	private void HookNorth()
	{
		Box HookNorth = new Box(30, 0.1f, 20);
		Geometry hookNorthGeom = new Geometry("Box", HookNorth);
		hookNorthGeom.setMaterial(new PlainMaterial(ColorRGBA.Black));
		hookNorthBox.attachChild(hookNorthGeom);
		hookNorthGeom.setLocalTranslation(-50, 0.1f, -1430);
		ContainingClient.getMyRootNode().attachChild(hookNorthBox);
	}

	private void HookSouth()
	{
		Box HookSouth = new Box(30, 0.1f, 20);
		Geometry hookSouthGeom = new Geometry("Box", HookSouth);
		hookSouthGeom.setMaterial(new PlainMaterial(ColorRGBA.Black));
		hookSouthBox.attachChild(hookSouthGeom);
		hookSouthGeom.setLocalTranslation(-50, 0.1f, -20);
		ContainingClient.getMyRootNode().attachChild(hookSouthBox);
	}

	private void littleShipRails()
	{
		for (int i = 1; i < 76; i++)
		{
			attachChild(new CraneRails(new Vector3f(-7, 0.1f, -40 - 18 * i), 1f, 0f));
		}
	}
}
