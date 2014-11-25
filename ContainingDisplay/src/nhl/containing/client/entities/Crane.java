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

/**
 * 
 * @author Yannick
 */
public abstract class Crane extends Node
{
	protected Node grabber = new Node();        
        private MotionPath path;
        private MotionPath path2;
        private MotionEvent motionControl;
        private MotionEvent motionControl2;
        private boolean up = true;
        private boolean move = false;
        

	public Crane()
	{
            
            //MotionY();
            //MotionX();
	}

	public void moveHookTo(Vector3f location)
	{
		Vector3f craneLocation = new Vector3f(this.getLocalTranslation().x, this.getLocalTranslation().y, location.z);
		Vector3f grabberLocation = new Vector3f(0, location.y, location.z);
		grabber.setLocalTranslation(grabberLocation);
		this.setLocalTranslation(craneLocation);
	}

    public void MotionY()
    {        
            path = new MotionPath();
            path.addWayPoint(new Vector3f(0,25,0));
            path.addWayPoint(new Vector3f(0,25,-8));
            path.addWayPoint(new Vector3f(0,2.5f,-8));
            path.addWayPoint(new Vector3f(0,25,-8));
           
            path.setCurveTension(0);
            path.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
            motionControl = new MotionEvent(grabber, path);
            motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
            motionControl.setInitialDuration(10f);
            motionControl.setSpeed(0.5f);
            motionControl.play();        
    }

    public Node getGrabber() {
        return grabber;
    }
    
    
        
        
        
}
