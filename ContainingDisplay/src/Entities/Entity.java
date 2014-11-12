/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.jme3.math.Vector3f;

/**
 *
 * @author Sander
 */
public abstract class Entity {
    Vector3f Location;
    String Name;

    public Entity() {
    }

    public Entity(Vector3f Location, String Name) {
        this.Location = Location;
        this.Name = Name;
    }
    
    
}
