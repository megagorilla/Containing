/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server.platformhandlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import nhl.containing.server.ContainingServer;
import nhl.containing.server.Ship;
import nhl.containing.server.ShipCrane;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.ContainerData;
import nhl.containing.server.network.SeaShipCraneData;
import nhl.containing.server.network.SeaShipSpawnData;
import nhl.containing.server.pathfinding.AGV;
import nhl.containing.server.pathfinding.AGVHandler;
import nhl.containing.server.util.ControlHandler;
import nhl.containing.server.util.ServerSpatial;
import nhl.containing.server.util.XMLFileReader.Container;

import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.MotionPathListener;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 *
 * @author Sander
 */
public class SeaShipPlatformHandler {
	
    Stack<Ship> shipsEnRoute = new Stack<Ship>();
    ArrayList<Ship> shipsInHarbor = new ArrayList<>();
    HashMap<Integer, ShipCrane> cranes = new HashMap<Integer, ShipCrane>();
    private static SeaShipPlatformHandler instance;
    ControlHandler c = ControlHandler.getInstance();

    public SeaShipPlatformHandler(ArrayList<Container> seaShipContainers) {
    	instance = this;
        initSeaShips(seaShipContainers);
    }
    
    public void update(float tpf)
	{
    	if(this.hasShips())
    		checkNeedForAGVs();
	}
    
    public static SeaShipPlatformHandler getInstance()
	{
		return instance;
	}
    
    private void initSeaShips(ArrayList<Container> seaShipContainers)
    {
    	boolean isFull;
        for(int i = 0;i<12;i++)
            cranes.put(i, new ShipCrane(i,true));
        ArrayList<Container> buffList = new ArrayList<>();
        while (seaShipContainers.size() > 0) {
            buffList.add(seaShipContainers.get(0));
            seaShipContainers.remove(0);
            isFull = false;
            while (seaShipContainers.size() > 0 && !isFull) {
                if (seaShipContainers.get(0).getPositie().equals(Vector3f.ZERO)) {
                    isFull = true;
                } else {
                    buffList.add(seaShipContainers.get(0));
                    seaShipContainers.remove(0);
                }
            }
            shipsEnRoute.push(new Ship(buffList));
            buffList.clear();

        }
        Collections.sort(shipsEnRoute, (a, b) -> (a.getArrivalDay() < b.getArrivalDay()) ? 1 : (a.getArrivalDay() > b.getArrivalDay()) ? -1 : 0);
    }

    /**
     *
     * @return the day the next ship should arrive
     */
    public int getDayOfNextShip() {
        return this.shipsEnRoute.peek().getArrivalDay();
    }
    
    /**
     * moves the first ship from shipsEnRoute to shipsInHarbor
     */
    public void nextShipArrives(){
        shipsInHarbor.add(shipsEnRoute.pop());
        List<Container> containers = shipsInHarbor.get(0).getContainerList();
        List<ContainerData> containerData = new ArrayList<>();
        
        for(Container c : containers)
            containerData.add(new ContainerData(c.getContainerNumber(), c.getPositie()));
        SeaShipSpawnData data = new SeaShipSpawnData(shipsInHarbor.get(0).getID(),containerData,false);
        ConnectionManager.sendCommand(data);
    }
    
    public void checkNeedForAGVs()
    {
    	for(ShipCrane crane : cranes.values())
    	{
    		if(!crane.isUnloading())
    		{	
    			ControlHandler.getInstance().requestAGVToSeaship();
    			crane.SetUnloading(true);
    		}
    	}
    }
    
    public int getShipsEnRouteSize(){
        return shipsEnRoute.size();
    }
    
    public void firstShipLeaves(){
        shipsInHarbor.remove(0);
    }
    
    public Ship getCurrentShip(){
        return shipsInHarbor.isEmpty() ? null : shipsInHarbor.get(0);
    }

    public boolean currentShipIsUnloading(){
        return shipsInHarbor.get(0).isUnloading();
    }
    
    public boolean hasShips(){
        return shipsInHarbor.size()>0;
    }

	public void handleAGV(int agvId)
	{
		for(int i = 0; i < 12; i++)
		{
			ShipCrane crane = cranes.get(i);
			if(crane.agv == null)
			{
				crane.agv = AGVHandler.getInstance().getAGV(agvId);
				List<Vector3f> list = new ArrayList<Vector3f>();
                list.add(new Vector3f(316.5f, 0.0f, 882.5f));
                list.add(new Vector3f(crane.currentRow * 13.4f, 0.0f, 882.5f));
				ControlHandler.getInstance().sendAGV(agvId, list, "seaShipLocation_" + crane.getID());
				setCrane(crane);
				return;
			}
		}
	}

	public ShipCrane getCrane(int i)
	{
		return cranes.get(i);
	}

	public Container popContainer(ShipCrane crane)
	{	
		return shipsInHarbor.get(0).pop(crane.currentRow);
	}

	public void setCrane(ShipCrane crane)
	{
		cranes.put(crane.getID(), crane);
	}

	public void unloadContainer(int craneID, int agvId, Container container)
	{
        SeaShipCraneData data = new SeaShipCraneData(agvId, container.getPositie(), craneID, container.getContainerNumber());
        ConnectionManager.sendCommand(data);
        MotionPath path = new MotionPath();
        path.addWayPoint(new Vector3f());
        path.addWayPoint(new Vector3f(1, 0, 0));
        path.addWayPoint(new Vector3f(100, 0, 0));
        ServerSpatial spatial = new ServerSpatial(null, Integer.toString(craneID));
        ContainingServer.getRoot().attachChild(spatial);
        path.addListener(new MotionPathListener(){
			@Override
			public void onWayPointReach(MotionEvent motionEvent, int wayPointIndex) {
				if(motionEvent.getPath().getNbWayPoints() == wayPointIndex + 2)
				{
					ShipCrane crane = getCrane(craneID);
					crane.SetUnloading(false);
					setCrane(crane);
					System.out.println("1234  " + crane.getID());
				}
				if(motionEvent.getPath().getNbWayPoints() == wayPointIndex + 1)
				{
					ShipCrane crane = getCrane(craneID);
					AGV agv = AGVHandler.getInstance().getAGV(crane.agv.agvId);
					agv.container = container;
					AGVHandler.getInstance().setAGV(agv.agvId, agv);
					List<Vector3f> list = new ArrayList<Vector3f>();
					list.add(new Vector3f(crane.currentRow * 13.4f, 0.0f, 882.5f));
					list.add(new Vector3f(-316.5f, 0.0f, 882.5f));
					ControlHandler.getInstance().sendAGV(agv.agvId, list, "c3");
					crane.agv = null;
					setCrane(crane);
				}
			}    
        });
        MotionEvent event = new MotionEvent(spatial, path);
        event.setDirectionType(MotionEvent.Direction.PathAndRotation);
        event.setRotation(new Quaternion().fromAngleNormalAxis(0, Vector3f.UNIT_Y));
        event.setInitialDuration(90f);
        event.setSpeed(ContainingServer.getSpeed());
        event.play();
	}

	public void updateCranePosition(int id)
	{
		ShipCrane crane = this.getCrane(id);
		int highest = 11;
		for(ShipCrane crane1 : cranes.values())
		{
			if(shipsInHarbor.get(0).getShipSize().x == crane1.currentRow)
				return;
			else if(crane1.currentRow > highest)
				highest = crane1.currentRow;
		}
		crane.currentRow = highest + 1;
		this.setCrane(crane);
	}
}
