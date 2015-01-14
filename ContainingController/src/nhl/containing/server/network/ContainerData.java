/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server.network;

import com.jme3.math.Vector3f;
import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 *
 * @author Sander
 */
@Serializable
public class ContainerData extends AbstractMessage {

	public int containerID;
	public Vector3f Location;

	public ContainerData() {
	}

	public ContainerData(int containerID, Vector3f Location) {
		this.containerID = containerID;
		this.Location = Location;
	}

}
