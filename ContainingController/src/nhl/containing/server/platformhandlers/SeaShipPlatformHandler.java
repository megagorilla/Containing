/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server.platformhandlers;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import nhl.containing.server.Ship;
import nhl.containing.server.ShipCrane;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.ContainerData;
import nhl.containing.server.network.SeaShipSpawnData;
import nhl.containing.server.pathfinding.AGV;
import nhl.containing.server.util.ControlHandler;
import nhl.containing.server.util.XMLFileReader.Container;

import com.jme3.math.Vector3f;

/**
 *
 * @author Sander
 */
public class SeaShipPlatformHandler {
	
    Stack<Ship> shipsEnRoute = new Stack<Ship>();
    ArrayList<Ship> shipsInHarbor = new ArrayList<>();
    ArrayList<ShipCrane> cranes = new ArrayList<>();
    private static SeaShipPlatformHandler instance;
    private int agvAmount = 0;
    ControlHandler c = new ControlHandler();

    public SeaShipPlatformHandler(ArrayList<Container> seaShipContainers) {
    	instance = this;
        initSeaShips(seaShipContainers);
        for(Ship ship : shipsEnRoute)
        {
        	System.out.println(ship.getContainerAmount());
        }
    }
    
    public void update(float tpf)
	{
    	for(ShipCrane crane : cranes)
    	{
    		if(!crane.isUnloading())
    			this.unload(crane.getID());
    	}
    	RequestAGVToSeaship();
    	while(!c.agvShipQueue.isEmpty()){
    		SendAGVToSeaShipCrane(c.agvShipQueue.remove(0));
    	}
	}
    
    public static SeaShipPlatformHandler getInstance()
	{
		return instance;
	}
    
    private void initSeaShips(ArrayList<Container> seaShipContainers)
    {
    	boolean isFull;
        for(int i = 0;i<12;i++)
            cranes.add(new ShipCrane(Vector3f.ZERO, i,true));
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
    
	public void unload(int craneId) 
	{
		ShipCrane crane = cranes.get(craneId);
//		AGV agv = this.getAGV(craneId);
//		crane.startUnloading();
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
    public void nextShipArrives(){ //Might need rename
        shipsInHarbor.add(shipsEnRoute.pop());
        List<Container> containers = shipsInHarbor.get(0).getContainerList();
        List<ContainerData> containerData = new ArrayList<>();
        
        for(Container c : containers)
            containerData.add(new ContainerData(c.getContainerNumber(), c.getPositie()));
        SeaShipSpawnData data = new SeaShipSpawnData(shipsInHarbor.get(0).getID(),containerData,false);
        ConnectionManager.sendCommand(data);
    }
    
    public void RequestAGVToSeaship(){
    	if(getCurrentShip() != null){
    		if(agvAmount < 12){
				for(int i = 0; i < this.getCurrentShip().getContainerAmount() && i < 4; i++){
					ControlHandler.getInstance().requestAGVToSeaship();
					agvAmount++;
				}
    		}
    	}
    }
    
    public void SendAGVToSeaShipCrane(AGV agv){
    	for(ShipCrane c : cranes){
    		if(c.isUnloading() == true && c.getRequestedAGV() == false){
    			System.out.println(c.getID() + ": " + agv.agvId);
    			
    			c.setRequestedAGV(true);
    			c.SetUnloading(false);
    			Vector3f tempCraneLoc = c.getLocation();
    			List<Vector3f> list = new ArrayList<Vector3f>();
    			list.add(new Vector3f(316.5f, 0.0f, 882.5f));
    			list.add(new Vector3f(tempCraneLoc.x, 0.0f, 882.5f));
    			ControlHandler.getInstance().sendAGV(agv.agvId, list, "shipCrane_" + c.getID());
    			return;
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
    
}
