/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities.vehicles;

import com.jme3.animation.LoopMode;
import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.Vector3f;
import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Vehicle;

/**
 *
 * @author Sander
 */
public class SpaceShip extends Vehicle {

    public SpaceShip() {
        this.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/shuttle/shuttle.j3o"));
        ContainingClient.getMyRootNode().attachChild(this);
        this.scale(100);
        startMotionPath();
    }

    private void startMotionPath() {
        MotionPath path = new MotionPath();

        path.addWayPoint(new Vector3f(1000, 1000, -1000));
        path.addWayPoint(new Vector3f(1000, 1000, 1000));
        path.addWayPoint(new Vector3f(-1000, 1000, 1000));
        path.addWayPoint(new Vector3f(-1000, 1000, -1000));
        path.addWayPoint(new Vector3f(1000, 1000, -1000));
        
        path.setCycle(true);
        path.setCurveTension(1);
        MotionEvent motionControl = new MotionEvent(this, path);
        motionControl.setDirectionType(MotionEvent.Direction.Path);
        motionControl.setInitialDuration(10f);
        motionControl.setSpeed(1f);
        motionControl.setLoopMode(LoopMode.Loop);
        motionControl.play();
    }
}
