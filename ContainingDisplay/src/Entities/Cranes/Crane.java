/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Cranes;
import Entities.*;
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
public abstract class Crane extends Node
{
    Node grabber = new Node();

    public Crane() {
    }
 
    public void moveHookTo(Vector3f location){
        Vector3f craneLocation = new Vector3f(this.getLocalTranslation().x, 
                this.getLocalTranslation().y, location.z);
        Vector3f grabberLocation = new Vector3f(location.x, location.y, 0);
        grabber.setLocalTranslation(grabberLocation);
        this.setLocalTranslation(craneLocation);
    }
}
