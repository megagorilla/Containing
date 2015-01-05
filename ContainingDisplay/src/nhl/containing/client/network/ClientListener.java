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
import java.util.ArrayList;
import nhl.containing.client.entities.vehicles.Barge;
import nhl.containing.client.entities.vehicles.SeaShip;

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
                if(m instanceof SeaShipSpawnData){
                    this.handleSeaShipSpawnMessage((SeaShipSpawnData)m);
                }
                if(m instanceof BargeSpawnData){
                    this.handleBargeSpawnMessage((BargeSpawnData)m);
                }
                if(m instanceof SeaShipCraneData){
                    this.handleSeaShipCraneMessage((SeaShipCraneData)m);
                }
                if(m instanceof BargeCraneData){
                    this.handleBargeCraneMessage((BargeCraneData)m);
                }
	   }

        private void handleBargeCraneMessage(final BargeCraneData m) {
        ContainingClient.instance.enqueue(new Callable<Object>() {
            public Object call() throws Exception {
                ContainingClient.bargeCranes.get(m.craneID).getContainerFrom(m.location, m.containerID, m.dayLength);
                return null;
            }
        });
    }
        
    private void handleSeaShipCraneMessage(final SeaShipCraneData m) {
        ContainingClient.instance.enqueue(new Callable<Object>() {
            public Object call() throws Exception {
                ContainingClient.seaShipCranes.get(m.craneID).getContainerFrom(m.location, m.containerID, m.dayLength);
                return null;
            }
        });
    }

private void handleBargeSpawnMessage(final BargeSpawnData m) {
        ContainingClient.instance.enqueue(new Callable<Object>() {
            public Object call() throws Exception {
                if (m.shouldDespawn) {
                    ArrayList<Barge> ships = ContainingClient.barges;
                    Barge ship = null;
                    for (Barge s : ships) {
                        if (s.getbargeID() == m.BargeID) {
                            ship = s;
                        }
                    }
                    if (ship != null) {
                        ship.removeFromParent();
                        ContainingClient.barges.remove(m);
                    }
                } else {
                    Barge ship = new Barge(ContainingClient.quality, m.BargeID);
                    for (ContainerData c : m.containers) {
                        Container container = new Container(ContainingClient.quality, c.containerID);
                        container.setLocalTranslation(Container.width * c.Location.y-3,
                                Container.height * c.Location.z, Container.length * c.Location.x - 30);
                        ship.addContainer(container);
                    }
                    ship.setLocalTranslation(400, 0, 200f * ContainingClient.barges.size() + 600);
                    ContainingClient.barges.add(ship);
                }
                return null;
            }
        });
    }
        
        private void handleSeaShipSpawnMessage(final SeaShipSpawnData m) {
        ContainingClient.instance.enqueue(new Callable<Object>() {
            public Object call() throws Exception {
                if (m.shouldDespawn) {
                    ArrayList<SeaShip> ships = ContainingClient.seaShips;
                    SeaShip ship = null;
                    for(SeaShip s : ships){
                        if(s.getSeaShipID() == m.seaShipID){
                            ship = s;
                        }
                    }
                    if(ship != null){
                        ship.removeFromParent();
                        ContainingClient.seaShips.remove(m);
                    }
                } else {
                    SeaShip ship = new SeaShip(ContainingClient.quality, m.seaShipID);
                    

                    for(ContainerData c : m.containers){
                        Container container = new Container(ContainingClient.quality, c.containerID);
                        container.setLocalTranslation(Container.width*c.Location.z-20,
                                Container.height*c.Location.y, Container.length*c.Location.x);
                        
                        ship.addContainer(container);
                    }
                    
                    ship.setLocalTranslation(800f*ContainingClient.seaShips.size(), 0, 930);
                    ship.rotate(0, FastMath.HALF_PI, 0);
                    ContainingClient.seaShips.add(ship);
                }
                return null;
            }
        });
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
	            	Truck truck = new Truck(ContainingClient.quality);
					Container container = new Container(ContainingClient.quality,0);//TODO change to containerData
					truck.addContainer(container);
					container.setLocalTranslation(0, 1.5f, 0);
					truck.setLocalTranslation(400, 0, -750 + 25 * m.truckID);
			        truck.rotate(0, FastMath.HALF_PI, 0);
			        ContainingClient.Trucks.add(truck);
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
				crane.fromTruck(agv, ContainingClient.Trucks.get(m.containerID).getContainer());
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
