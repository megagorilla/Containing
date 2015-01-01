/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.network;

//import nhl.containing.server.network.TruckSpawnData;
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
        Serializer.registerClass(StorageCranePickupData.class);
        Serializer.registerClass(SeaShipCraneData.class);
        
        Serializer.registerClass(SeaShipSpawnData.class);
        Serializer.registerClass(BargeSpawnData.class);
        Serializer.registerClass(ContainerData.class);
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
