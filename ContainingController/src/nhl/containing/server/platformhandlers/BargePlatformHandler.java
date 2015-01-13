/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server.platformhandlers;

import com.jme3.math.Vector3f;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import nhl.containing.server.ContainingServer;
import nhl.containing.server.Ship;
import nhl.containing.server.ShipCrane;
import nhl.containing.server.network.BargeSpawnData;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.ContainerData;
import nhl.containing.server.network.SeaShipSpawnData;
import nhl.containing.server.util.XMLFileReader.*;

/**
 *
 * @author Sander
 */
public class BargePlatformHandler {
    Stack<Ship> shipsEnRoute = new Stack<Ship>();
    ArrayList<Ship> shipsInHarbor = new ArrayList<>();
    ArrayList<ShipCrane> cranes = new ArrayList<>();
    
    private final float durationFastest = 4;
    private final float durationMedium = 40;
    private final float durationSlowest = 120;
    
    public BargePlatformHandler(ArrayList<Container> BargeContainers) {
        boolean isFull;
        ArrayList<Container> buffList = new ArrayList<>();
        for(int i = 0;i<4;i++)
            cranes.add(new ShipCrane(Vector3f.ZERO, i, false));
        while (BargeContainers.size() > 0) {
            buffList.add(BargeContainers.get(0));
            BargeContainers.remove(0);
            isFull = false;
            while (BargeContainers.size() > 0 && !isFull) {
                if (BargeContainers.get(0).getPositie().equals(Vector3f.ZERO)) {
                    isFull = true;
                } else {
                    buffList.add(BargeContainers.get(0));
                    BargeContainers.remove(0);
                }
            }
            shipsEnRoute.push(new Ship(buffList));
            buffList.clear();

        }
        Collections.sort(shipsEnRoute, (a, b) -> (a.getArrivalDay() < b.getArrivalDay()) ? 1 : (a.getArrivalDay() > b.getArrivalDay()) ? -1 : 0);
    }
    
    public void update(){
    	float currentTime = ContainingServer.timeSinceStart;
    	for(int i = 0; i<cranes.size();i++){
//    		if(cranes.get(i).getTimeStartedUnloading()>0 &&(((currentTime - cranes.get(i).getTimeStartedUnloading())>durationFastest && ContainingServer.getDayLength() < 10f ) ||
//    				((currentTime - cranes.get(i).getTimeStartedUnloading())>durationMedium && (ContainingServer.getDayLength() >= 10f && ContainingServer.getDayLength() < 30f) ) ||
//    				((currentTime - cranes.get(i).getTimeStartedUnloading())>durationSlowest && ContainingServer.getDayLength() >= 30f ))){
//    			cranes.get(i).SetUnloading(false);
//    			Container container = cranes.get(i).getContainer(); //TODO connect this container to the AGV
//    			cranes.get(i).setTimeStartedUnloading(0f);
//    		}
    	}
    }
    
    /**
     * 
     * @return the day the next ship should arrive
     */
    public int getDayOfNextShip(){
        return shipsEnRoute.peek().getArrivalDay();
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
       BargeSpawnData data = new BargeSpawnData(shipsInHarbor.get(0).getID(),containerData,false);
        ConnectionManager.sendCommand(data);
    }
    
    public void Unload(){
        for(ShipCrane crane: cranes){
            if(!crane.isUnloading()){
                Vector3f shipSize = shipsInHarbor.get(0).getShipSize();
                for(int i = 0;i < shipSize.x;i++){
                    for(int j = 0; j< shipSize.z;j++){
                        if(shipsInHarbor.get(0).containsContainers(j, i)){
                            Container container = shipsInHarbor.get(0).pop(j, i);
//                            crane.startUnloading(container);
                            break;
                        }
                    }
                    if(crane.isUnloading())
                        break;
                }
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
        return shipsInHarbor.get(0);
    }
    
     public boolean currentShipIsUnloading(){
        return shipsInHarbor.get(0).isUnloading();
    }
    
    public boolean hasShips(){
        return shipsInHarbor.size()>0;
    }
}