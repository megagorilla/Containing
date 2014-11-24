package nhl.containing.server.network;

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
		try
		{
			server = Network.createServer(port);
		}
		catch (Exception e)
		{
			System.err.println(e);
			return false;
		}
		server.addMessageListener(new ServerListener(), UpdateMessage.class);
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
	public static void sendCommand(UpdateMessage msg)
	{
		server.broadcast(msg);
	}

	public static boolean hasConnections()
	{
		return server.hasConnections();
	}
}
