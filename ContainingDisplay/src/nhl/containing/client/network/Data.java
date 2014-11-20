/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.network;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;

/**
 *
 * @author Sander
 */
@Serializable
public class Data {
    	public String name;
	public Vector3f pos;
	public Quaternion rot;

    public Data() {
    }
        

	Data(String name, Vector3f location, Quaternion rotation)
	{
		this.name = name;
		this.pos = location;
		this.rot = rotation;
	}
}
