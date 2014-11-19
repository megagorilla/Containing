/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities;

import nhl.containing.client.materials.TexturedMaterial;

import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 * 
 * @author Sander
 */
public abstract class Vehicle extends Node
{
	AssetManager assetManager;
	private Box box;
	private Geometry vehicleGeom;

	// private Material material; TODO: Not being used? removal imminent

	public Vehicle()
	{
	}

	public Vehicle(ColorRGBA color, Vector3f size, AssetManager assetManager, TexturedMaterial material)
	{
		this.assetManager = assetManager;
		box = new Box(size.x, size.y, size.z);
		vehicleGeom = new Geometry(name, box);

		vehicleGeom.setMaterial(material);
		attachChild(vehicleGeom);
	}

}
