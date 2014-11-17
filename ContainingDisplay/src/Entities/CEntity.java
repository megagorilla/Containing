/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 *
 * @author Sander
 */
public abstract class CEntity extends Node{
    Vector3f Location;
    String Name;

    public CEntity() {
    }

    public CEntity(Vector3f Location, String Name) {
        this.Location = Location;
        this.Name = Name;
    }
    
    
}
