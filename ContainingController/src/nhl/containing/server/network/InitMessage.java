package nhl.containing.server.network;

import nhl.containing.server.ContainingServer;

import com.jme3.network.serializing.Serializable;

@Serializable
public class InitMessage extends UpdateMessage {

	float speed;

	public InitMessage() {
		speed = ContainingServer.getSpeed();
	}
}
