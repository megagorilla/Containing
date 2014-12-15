/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server.platformhandlers;

import com.jme3.math.Vector3f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import nhl.containing.server.Ship;
import nhl.containing.server.util.XMLFileReader.*;

/**
 *
 * @author Sander
 */
public class BargePlatformHandler {
    Stack<Ship> ships = new Stack<Ship>();
    
    public BargePlatformHandler(ArrayList<Container> BargeContainers) {
        boolean isFull;
        ArrayList<Container> buffList = new ArrayList<>();
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
            ships.push(new Ship(buffList));
            buffList.clear();

        }
        Collections.sort(ships, (a, b) -> (a.getArrivalDay() < b.getArrivalDay()) ? 1 : (a.getArrivalDay() > b.getArrivalDay()) ? -1 : 0);
    }
    
    /**
     * 
     * @return the day the next ship should arrive
     */
    public int getDayOfNextShip(){
        return ships.peek().getArrivalDay();
    }
}