/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.network;

import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Network;
import com.jme3.network.serializing.Serializer;

/**
 * 
 * @author snyx
 */
public final class ConnectionManager
{
	private static Client client;

	private ConnectionManager()
	{
	}

	public static boolean init(String host, int port)
	{
		try
		{
			client = Network.connectToServer(host, port);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return false;
		}
		Serializer.registerClass(UpdateMessage.class);
		client.addMessageListener(new MessageListener<Object>()
		{
			public void messageReceived(Object source, Message m)
			{
				System.out.println(((UpdateMessage) m).getMsg());
			}
		});
		client.start();
		return true;
	}

	public static void stop()
	{
		client.close();
	}

	public static void sendCommand(UpdateMessage msg)
	{
		client.send(msg);
	}
}
