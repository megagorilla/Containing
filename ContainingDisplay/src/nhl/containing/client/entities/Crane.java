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
        protected Node grabber2 = new Node();   
        private MotionPath path;
        private MotionPath path2;
        private MotionEvent motionControl;
        private MotionEvent motionControl2;
        private boolean up = true;
        private boolean move = false;
        

	public Crane()
	{            
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
            path.addWayPoint(new Vector3f(8,25,0));
            path.addWayPoint(new Vector3f(8,2.5f,0));
            path.addWayPoint(new Vector3f(8,25,0));
            path.addWayPoint(new Vector3f(0,25,0));
           
            path.setCurveTension(0);
            path.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
            motionControl = new MotionEvent(grabber, path);
            motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
            motionControl.setInitialDuration(10f);
            motionControl.setSpeed(0.5f);
            motionControl.play();        
    }

    public void MotionTruckCraneGrabber()
    {
        path2 = new MotionPath();
        path2.addWayPoint(new Vector3f(0,1,0));
        path2.addWayPoint(new Vector3f(0,-5,0));
        path2.addWayPoint(new Vector3f(0,1,0));
        path2.addWayPoint(new Vector3f(0,1,20));
        path2.addWayPoint(new Vector3f(0,-5,20));
        path2.addWayPoint(new Vector3f(0,1,20));
        path2.addWayPoint(new Vector3f(0,1,0));
        
        path2.setCurveTension(0);
        path2.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
        motionControl = new MotionEvent(grabber2, path2);
        motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl.setInitialDuration(10f);
        motionControl.setSpeed(1f);
        motionControl.play();        
    }
    
    public Node getGrabber() {
        return grabber;
    }
    
    
        
        
        
}
