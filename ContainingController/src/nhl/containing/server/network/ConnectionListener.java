package nhl.containing.server.network;

import com.jme3.network.HostedConnection;
import com.jme3.network.Server;

public class ConnectionListener implements com.jme3.network.ConnectionListener {

	public ConnectionListener() {

	}

	public void connectionAdded(Server s, HostedConnection c) {
		ConnectionManager.sendCommand(c.getId(), new InitMessage());
	}

	public void connectionRemoved(Server s, HostedConnection c) {

	}
}
