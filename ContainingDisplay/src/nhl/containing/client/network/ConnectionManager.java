/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.network;

import nhl.containing.client.network.TruckSpawnData;
import com.jme3.network.Client;
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
        Serializer.registerClass(UpdateMessage.class);
        Serializer.registerClass(AGVData.class);
        Serializer.registerClass(TruckCraneData.class);
        Serializer.registerClass(TruckSpawnData.class);
        Serializer.registerClass(TrainCraneData.class);
        Serializer.registerClass(TrainSpawnData.class);
		try
		{
			client = Network.connectToServer(host, port);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return false;
		}
		client.addMessageListener(new ClientListener());
		client.addClientStateListener(new ConnectionListener());
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
