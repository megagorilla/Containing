package nhl.containing.client.network;

import java.util.concurrent.Callable;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.ContainingClient.Quality;
import nhl.containing.client.entities.Container;
import nhl.containing.client.entities.cranes.TruckCrane;
import nhl.containing.client.entities.vehicles.Truck;

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
		if(m instanceof TruckCraneData)
		{
			this.handleTruckCraneMessage((TruckCraneData)m);
		}
		if(m instanceof TruckSpawnData)
		{
			this.handleTruckSpawnMessage((TruckSpawnData)m);
		}
	}
	
	private void handleTruckSpawnMessage(TruckSpawnData m)
	{
		Truck truck = new Truck(Quality.HIGH);
		Container container = new Container(Quality.HIGH);
		truck.attachChild(container);
		container.setLocalTranslation(0, 1.5f, 0);
		truck.setLocalTranslation(400, 0, -750 + 25 * m.truckID);
	}

	private void handleTruckCraneMessage(TruckCraneData m) 
	{
		TruckCrane crane = ContainingClient.TruckCranes.get(m.craneID);
		ContainingClient.agvs.get(m.agvID);
		crane.fromTruck(ContainingClient.agvs.get(m.agvID), ContainingClient.test2);
	}

	private void handleUpdateMessage(final UpdateMessage message)
	{
		for(final AGVData data : message.data)
		{
			 ContainingClient.instance.enqueue(new Callable<Object>() 
			 {
                 public Object call() throws Exception
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
        	        motionControl.setSpeed(8f);  
        	        motionControl.play();
                     return null;
                 }
             });
			System.out.println(System.currentTimeMillis());
		}
	}
}
