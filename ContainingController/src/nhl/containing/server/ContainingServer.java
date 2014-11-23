package nhl.containing.server;

import nhl.containing.server.network.API;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.UpdateMessage;

import com.jme3.app.SimpleApplication;
import com.jme3.system.JmeContext;

public class ContainingServer extends SimpleApplication
{
	private long startTime = System.currentTimeMillis();
	float time = 0;

	public static void main(String[] args)
	{
		ContainingServer app = new ContainingServer();
		app.start(JmeContext.Type.Headless);
	}

	@Override
	public void simpleInitApp()
	{
		// TODO code application logic here
		System.out.println("Test");
		// test whether the JME server launches successfully (on port 3000)
		if (!ConnectionManager.initialize(3000))
			System.err.println("Failed to start server!");
		else
			System.out.println("Successfully started server");
		// test command

		// start the API, listen on port 8080
		API.start(8080);
		// use read to keep the program active
		System.out.println("Type any character to quit");
		while (true)
		{
			for (int i = 0; i < 50; i++)
			{
				if (ConnectionManager.hasConnections())
					ConnectionManager.sendCommand(new UpdateMessage("0 10 " + i));
			}
		}
		// System.in.read();
	}

	@Override
	public void simpleUpdate(float tpf)
	{
		time++;
		if (time == 60)
			System.out.println((System.currentTimeMillis() - startTime));
	}
	
	@Override
	public void destroy()
	{
		ConnectionManager.stop();
		super.destroy();
	}
}
