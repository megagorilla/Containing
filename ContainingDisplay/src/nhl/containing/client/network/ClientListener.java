package nhl.containing.client.network;

import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;

public class ClientListener implements MessageListener<Client>
{
	@Override
	public void messageReceived(Client source, Message m)
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
