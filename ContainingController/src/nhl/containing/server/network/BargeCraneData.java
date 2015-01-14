/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server.network;

import com.jme3.math.Vector3f;
import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;
import nhl.containing.server.ContainingServer;

/**
 *
 * @author Sander
 */
@Serializable
public class BargeCraneData extends AbstractMessage {

	int agvId;
	int craneID;
	int containerID;
	Vector3f location;
	float dayLength;

	public BargeCraneData() {
	}

	public BargeCraneData(int agvId, Vector3f location, int craneID, int containerID) {
		this.agvId = agvId;
		this.location = location;
		this.craneID = craneID;
		this.containerID = containerID;
		this.dayLength = ContainingServer.getDayLength();
	}
}
