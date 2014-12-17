package nhl.containing.client.network;

import java.util.concurrent.Callable;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.ContainingClient.Quality;
import nhl.containing.client.entities.Container;
import nhl.containing.client.entities.cranes.TruckCrane;
import nhl.containing.client.entities.vehicles.AGV;
import nhl.containing.client.entities.vehicles.Truck;

import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.FastMath;
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
		if(m instanceof StorageCranePickupData)
		{
			this.handleStorageCraneMessage((StorageCranePickupData)m);
		}
	}
	
	private void handleStorageCraneMessage(StorageCranePickupData m) {
		// TODO Auto-generated method stub
		
	}

	private void handleTruckSpawnMessage(final TruckSpawnData m)
	{
		 ContainingClient.instance.enqueue(new Callable<Object>() 
		 {
            public Object call() throws Exception
            {
            	if(m.shouldDespawn)
            	{
            		Truck truck = ContainingClient.Trucks.get(m.truckID);
            		truck.removeFromParent();
            		ContainingClient.Trucks.remove(m.truckID);
            	}
            	else
            	{	            	
	            	Truck truck = new Truck(Quality.HIGH);
					Container container = new Container(Quality.HIGH);
					truck.addContainer(container);
					container.setLocalTranslation(0, 1.5f, 0);
					truck.setLocalTranslation(400, 0, -750 + 25 * m.truckID);
			        truck.rotate(0, FastMath.HALF_PI, 0);
			        ContainingClient.Trucks.put(m.truckID, truck);
            	}
            	return null;
            }
		 });
	}

	private void handleTruckCraneMessage(final TruckCraneData m) 
	{
		 ContainingClient.instance.enqueue(new Callable<Object>() 
		 {
            public Object call() throws Exception
            {
				TruckCrane crane = ContainingClient.TruckCranes.get(m.craneID);
				AGV agv = ContainingClient.agvs.get(m.agvID);
				crane.fromTruck(agv, ContainingClient.Trucks.get(m.truckID).getContainer());
				return null;
            }
		 });
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
        	        motionControl.setInitialDuration(10f); 
        	        motionControl.setSpeed(20f);  
        	        motionControl.play();
                    return null;
                 }
             });
			System.out.println(System.currentTimeMillis());
		}
	}
}
