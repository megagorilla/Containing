package nhl.containing.server.network;

import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;

public class ServerListener implements MessageListener<HostedConnection>
{
	@Override
	public void messageReceived(HostedConnection source, Message m)
	{
		if(m instanceof UpdateMessage)
		{
			this.handleUpdateMessage((UpdateMessage)m);
		}
	}
	
	private void handleUpdateMessage(UpdateMessage message)
	{
		
	}

}
