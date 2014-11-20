/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.network;

import com.jme3.math.Vector3f;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Network;
import com.jme3.network.serializing.Serializer;
import nhl.containing.client.ContainingClient;

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
            Serializer.registerClass(UpdateMessage.class);
            Serializer.registerClass(Data.class);
		try
		{
			client = Network.connectToServer(host, port);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return false;
		}
		
		client.addMessageListener(new MessageListener<Object>()
		{
			public void messageReceived(Object source, Message m)
			{
                            if(m instanceof UpdateMessage)
                            {
                                UpdateMessage m1 = (UpdateMessage) m;
                                ContainingClient.test2 = m1.data.iterator().next().pos;
                                System.out.println("1");
                            }
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
