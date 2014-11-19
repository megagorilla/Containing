/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.materials;

import nhl.containing.client.ContainingClient;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

/**
 * 
 * @author Sander
 */
public class TexturedMaterial extends Material
{

	public TexturedMaterial()
	{
	}

	public TexturedMaterial(int textureNumber)
	{
		super(ContainingClient.getMyAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
		Texture diffuseTexture = ContainingClient.getMyAssetManager().loadTexture("Textures/texturePack/" + textureNumber + ".JPG");
		diffuseTexture.setWrap(WrapMode.Repeat);
		Texture normalTexture = ContainingClient.getMyAssetManager().loadTexture("Textures/texturePack/" + textureNumber + "_norm.JPG");
		normalTexture.setWrap(WrapMode.Repeat);
		this.setTexture("DiffuseMap", diffuseTexture);
		this.setTexture("NormalMap", normalTexture);
		this.setColor("Specular", ColorRGBA.White);
		this.setColor("Diffuse", ColorRGBA.White);
		this.setFloat("Shininess", 0f);
		this.setBoolean("UseMaterialColors", true);
	}

	public void setColor(ColorRGBA color)
	{
		this.setColor("Specular", color);
		this.setColor("Diffuse", color);
	}

}
