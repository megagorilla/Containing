/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Vehicles;
import Materials.TexturedMaterial;
import Entities.*;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
/**
 *
 * @author Sander
 */
public abstract class Vehicle extends CEntity{
    AssetManager assetManager;
    private Box box;
    private Geometry vehicleGeom;
    private Material material;

    public Vehicle() {
    }
    
    public Vehicle(Vector3f location,String name, ColorRGBA color, Vector3f size, AssetManager assetManager, TexturedMaterial material){
        super(location, name);
        this.assetManager = assetManager;
        box = new Box(size.x, size.y, size.z);
        vehicleGeom = new Geometry(name, box);
        
        vehicleGeom.setMaterial(material);
        attachChild(vehicleGeom);
    }
    
}
