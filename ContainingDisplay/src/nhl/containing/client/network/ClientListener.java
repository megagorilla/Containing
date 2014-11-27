package nhl.containing.client.network;

import nhl.containing.client.ContainingClient;

import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
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
		for(AGVData data : message.data)
		{
			MotionPath path = new MotionPath();
			for(Vector3f v : data.locations)
			{	path.addWayPoint(v);
				System.out.println(v);
			}
			path.setCurveTension(0.0f);
			//path.setCycle(true);
			path.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
			MotionEvent motionControl = new MotionEvent(ContainingClient.agvs.get(Integer.parseInt(message.msg)), path);
	        motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
	        motionControl.setRotation(new Quaternion().fromAngleNormalAxis(0, Vector3f.UNIT_Y));
	        motionControl.setInitialDuration(100f);
	        motionControl.setSpeed(2f);  
	        motionControl.play();
			System.out.println(System.currentTimeMillis());
		}
	}
}
