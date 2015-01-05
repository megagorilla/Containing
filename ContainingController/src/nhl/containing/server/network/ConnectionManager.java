package nhl.containing.server.network;

import nhl.containing.server.network.TruckCraneData;

import com.jme3.network.AbstractMessage;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;

public final class ConnectionManager
{
	static Server server;

	private ConnectionManager()
	{
	}

	/**
	 * Create and start the server.
	 * 
	 * @param port
	 * @return true if succesful, false otherwise
	 */
	public static boolean initialize(int port)
	{
		Serializer.registerClass(UpdateMessage.class);
		Serializer.registerClass(AGVData.class);
        Serializer.registerClass(TruckCraneData.class);
        Serializer.registerClass(TruckSpawnData.class);
        
        Serializer.registerClass(SeaShipCraneData.class);
        Serializer.registerClass(BargeCraneData.class);
        
        Serializer.registerClass(SeaShipSpawnData.class);
        Serializer.registerClass(BargeSpawnData.class);
        Serializer.registerClass(ContainerData.class);
		try
		{
			server = Network.createServer(port);
		}
		catch (Exception e)
		{
			System.err.println(e);
			return false;
		}
		server.start();
		return true;
	}

	/**
	 * Stops the server.
	 */
	public static void stop()
	{
		server.close();
	}

	/**
	 * Send a message to the specified client.
	 * 
	 * @param connID
	 * @param msg
	 */
	public static void sendCommand(int connID, UpdateMessage msg)
	{
		server.getConnection(connID).send(msg);
	}

	/**
	 * Broadcast a command to all connected clients.
	 * 
	 * @param msg
	 */
	public static void sendCommand(AbstractMessage msg)
	{
		server.broadcast(msg);
	}

	/**
	 * Returns true if 1 or more clients are connected
	 * @return server.hasConnections()
	 */
	public static boolean hasConnections()
	{
		return server.hasConnections();
	}
}
