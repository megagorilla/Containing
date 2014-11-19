/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.materials;

import nhl.containing.client.ContainingClient;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;

/**
 * 
 * @author Sander
 */
public class PlainMaterial extends Material
{

	public PlainMaterial(ColorRGBA color)
	{
		super(ContainingClient.getMyAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
		this.setColor("Diffuse", color);
		this.setColor("Ambient", color);
		this.setColor("Specular", color);
		this.setBoolean("UseMaterialColors", true);
	}

}
