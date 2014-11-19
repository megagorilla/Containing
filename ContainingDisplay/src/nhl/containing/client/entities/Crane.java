/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 * 
 * @author Yannick
 */
public abstract class Crane extends Node
{
	protected Node grabber = new Node();

	public Crane()
	{
	}

	public void moveHookTo(Vector3f location)
	{
		Vector3f craneLocation = new Vector3f(this.getLocalTranslation().x, this.getLocalTranslation().y, location.z);
		Vector3f grabberLocation = new Vector3f(location.x, location.y, 0);
		grabber.setLocalTranslation(grabberLocation);
		this.setLocalTranslation(craneLocation);
	}
}
