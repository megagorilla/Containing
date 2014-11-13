/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Vehicles;

import Materials.TexturedMaterial;
import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

/**
 *
 * @author Sander
 */
public class Train extends Vehicle{

    public Train(Vector3f location, String name, AssetManager assetManager, TexturedMaterial material) {
        super(location, name,ColorRGBA.Yellow, new Vector3f(10, 10, 10), assetManager, material);
    }
    
}
