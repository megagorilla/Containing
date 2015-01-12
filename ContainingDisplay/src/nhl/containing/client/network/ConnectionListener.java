package nhl.containing.client.network;

import com.jme3.network.Client;
import com.jme3.network.HostedConnection;
import com.jme3.network.Server;

public class ConnectionListener implements com.jme3.network.ClientStateListener {

	@Override
	public void clientConnected(Client arg0) {
		// TODO Auto-generated method stub
		System.out.println("Succefully connected to the server.");
	}

	@Override
	public void clientDisconnected(Client arg0, DisconnectInfo arg1) {
		// TODO Auto-generated method stub
		System.out.println("Kicked because: " + arg1.reason);
	}

	
}
