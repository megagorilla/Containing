package nhl.containing.client.materials;

import nhl.containing.client.ContainingClient;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;

/**
 *
 * @author Sander
 */
public class PlainMaterial extends Material {

    /**
     * Creates a material with the color given in the parameter
     *
     * @param color the color of the material
     */
    public PlainMaterial(ColorRGBA color) {
        super(ContainingClient.getMyAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        initMaterial(color);
    }

    /**
     * Creates a material with a random color
     */
    public PlainMaterial() {
        super(ContainingClient.getMyAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        initMaterial(ColorRGBA.randomColor());
    } 
    
    private void initMaterial(ColorRGBA color){
        this.setColor("Diffuse", color);
        this.setColor("Ambient", color);
        this.setColor("Specular", color);
        this.setBoolean("UseMaterialColors", true);
    }
}
