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
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 * 
 * @author Sander
 */
public class SeaShipPlatform extends Platform
{

	public SeaShipPlatform()
	{
		SeaSideWay();
		BigShipRails();
		HookEast();
		HookWest();
		ContainingClient.getMyRootNode().attachChild(this);
	}

	private void SeaSideWay()
	{
		Box SeaSideWay = new Box(300, 5f, 25);
		Geometry seaSideWayGeom = new Geometry("Box", SeaSideWay);
		seaSideWayGeom.setMaterial(new PlainMaterial(ColorRGBA.DarkGray));
		attachChild(seaSideWayGeom);
		seaSideWayGeom.setLocalTranslation(0, -5, 900);
	}

	private void HookEast()
	{
		Box HookEast = new Box(25, 5f, 25);
		Geometry hookEastGeom = new Geometry("Box", HookEast);
		hookEastGeom.setMaterial(new PlainMaterial(ColorRGBA.DarkGray));
		attachChild(hookEastGeom);
		hookEastGeom.setLocalTranslation(-275, -5f, 850);
	}

	private void HookWest()
	{
		Box HookWest = new Box(25, 5f, 25);
		Geometry hookWestGeom = new Geometry("Box", HookWest);
		hookWestGeom.setMaterial(new PlainMaterial(ColorRGBA.DarkGray));
		attachChild(hookWestGeom);
		hookWestGeom.setLocalTranslation(275, -5f, 850);
	}

	private void BigShipRails()
	{
		for (int i = 1; i < 28; i++)
		{
			attachChild(new CraneRails(new Vector3f(-249 + 18 * i, 0f, 880), 0.87f, FastMath.HALF_PI));
		}
	}
}
