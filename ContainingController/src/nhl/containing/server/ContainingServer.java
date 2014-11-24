package nhl.containing.server;

import java.util.ArrayList;

import nhl.containing.server.network.API;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.UpdateMessage;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.system.JmeContext;

public class ContainingServer extends SimpleApplication
{
	private long startTime = System.currentTimeMillis();
	float time = 0;
	private boolean hasSent;

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
		// System.in.read();
	}

	@Override
	public void simpleUpdate(float tpf)
	{
		if(!hasSent && ConnectionManager.hasConnections())
		{
			UpdateMessage m = new UpdateMessage("hai");
			ArrayList<Vector3f> list = new ArrayList<Vector3f>();
			list.add(new Vector3f());
			list.add(new Vector3f(100, 0, 0));
			list.add(new Vector3f(100, 0, 100));
			list.add(new Vector3f(200, 0, 100));
			list.add(new Vector3f(200, 0, 200));
			m.addData(0, list);
			ConnectionManager.sendCommand(m);
			System.out.println(System.currentTimeMillis());
			hasSent = true;
		}
	}
	
	@Override
	public void destroy()
	{
		ConnectionManager.stop();
		super.destroy();
	}
}
