/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Materials;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

/**
 *
 * @author Sander
 */
public class TexturedMaterial extends Material{
    
    
    public TexturedMaterial() {
    }

    public TexturedMaterial(int textureNumber, AssetManager assetManager) {
        super(assetManager, "Common/MatDefs/Light/Lighting.j3md"); 
        Texture diffuseTexture = assetManager.loadTexture("Textures/texturePack/"+textureNumber+".JPG");
        diffuseTexture.setWrap(WrapMode.Repeat);
        Texture normalTexture = assetManager.loadTexture("Textures/texturePack/"+textureNumber+"_norm.JPG");
        normalTexture.setWrap(WrapMode.Repeat);
        this.setTexture("DiffuseMap",diffuseTexture);
        this.setTexture("NormalMap", normalTexture);
        this.setColor("Specular", ColorRGBA.White);
        this.setColor("Diffuse",  ColorRGBA.White);
        this.setFloat("Shininess", 0f);
        this.setBoolean("UseMaterialColors",true);  
    }
    
    public void setColor(ColorRGBA color){
        this.setColor("Specular", color);
        this.setColor("Diffuse",  color);
    }

    
    
}
