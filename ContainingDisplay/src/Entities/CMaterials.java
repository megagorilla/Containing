/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.MaterialDef;
import com.jme3.math.ColorRGBA;

/**
 *
 * @author Sander
 */
public class CMaterials extends Material{
    
    public CMaterials() {
    }

    public CMaterials(ColorRGBA color, AssetManager assetManager) {
        super(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        this.setColor("Color", color);
    }
    
}
