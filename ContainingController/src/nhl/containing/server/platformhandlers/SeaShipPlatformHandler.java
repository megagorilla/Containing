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
public class SeaShipPlatformHandler {

    Stack<Ship> shipsEnRoute = new Stack<Ship>();

    public SeaShipPlatformHandler(ArrayList<Container> seaShipContainers) {
        boolean isFull;
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

}
