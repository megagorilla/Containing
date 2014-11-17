/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
/**
 *
 * @author Yannick
 */
public class Container extends CEntity{
    
    Vector3f Location;
    //String Name;
    Material containerMat;
    Box containerBox;
    
    public Container(Vector3f Location, String Name, AssetManager assetManager)
    {
        super(Location, Name);
        for(int i = 0; i < 6; i++)
        {
        containerBox = new Box(2.695f,2.348f,13.555f);
        Geometry Container = new Geometry("Container", containerBox);
        containerMat = new CMaterials(ColorRGBA.randomColor(), assetManager);
        Container.setMaterial(containerMat);
        //Container.setLocalTranslation(-16, 0, 0);
        attachChild(Container);
        Container.setLocalTranslation(-16 + 6.3f*i, 0, 0);
        }
    }
}
