/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities;

import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.cranes.TruckCrane;

/**
 *
 * @author Yannick
 */
public abstract class Crane extends Node {

     protected Node grabber = new Node();
     protected Node grabber2 = new Node();
    public Crane() {
    }

    public void moveHookTo(Vector3f location) {
        Vector3f craneLocation = new Vector3f(this.getLocalTranslation().x, this.getLocalTranslation().y, location.z);
        Vector3f grabberLocation = new Vector3f(0, location.y, location.z);
        grabber.setLocalTranslation(grabberLocation);
        this.setLocalTranslation(craneLocation);
    }

    

    

    public Node getGrabber() {
        return grabber;
    }
}
