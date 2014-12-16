/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import nhl.containing.server.util.XMLFileReader.*;

/**
 *
 * @author Sander
 */
public class Ship {

    int arrivalDay;
    int containerAmount = 0;
    private static final AtomicInteger count = new AtomicInteger(0); 
    private final int ID;
    Stack<Container>[][] containers;

    public Ship(ArrayList<Container> containers) {
        this.ID = count.incrementAndGet();
        if (containers.isEmpty()) {
            arrivalDay = 0;
        } else {
            int highestX = 0;
            int highestZ = 0;
            for (Container c : containers) {
                if ((int) c.getPositie().x > highestX) {
                    highestX = (int) c.getPositie().x;
                }
                if ((int) c.getPositie().z > highestZ) {
                    highestZ = (int) c.getPositie().z;
                }
            }
            highestX++;
            highestZ++;
            this.containers = (Stack<Container>[][]) new Stack[highestX][highestZ];

            this.arrivalDay = containers.get(0).getArrival().getDay();

            for (int i = 0; i < highestX; i++) {
                for (int j = 0; j < highestZ; j++) {
                    this.containers[i][j] = new Stack<>();
                }
            }

            for (Container c : containers) {
                int x = (int) c.getPositie().x;
                int z = (int) c.getPositie().z;
                this.containers[x][z].push(c);
                containerAmount++;
            }
        }
    }

    /**
     *
     * @return returns the day the ship arrives
     */
    public int getArrivalDay() {
        return arrivalDay;
    }

    /**
     *
     * @param x the x location of the container on the ship
     * @param z the z location of the container on the ship
     * @return returns the top container from given x and z location
     */
    public Container pop(int x, int z) {
        containerAmount--;
        return containers[x][z].pop();
    }

    /**
     *
     * @param x the x location of the container on the ship
     * @param z the z location of the container on the ship
     * @return returns a boolean value (true if location has containers, false
     * if not)
     */
    public boolean containsContainers(int x, int z) {
        return !containers[x][z].isEmpty();
    }

    /**
     * returs a boolean, true if ship is empty, false if not
     *
     * @return true if ship is empty, false if not
     */
    public boolean isEmpty() {
        return containerAmount==0;
    }

    public int getContainerAmount() {
        return containerAmount;
    }

    public int getID() {
        return ID;
    }
    
    public List<Container> getContainerList(){
        List<Container> containers = new ArrayList<Container>();
        for(Stack<Container>[] substack: this.containers){
            for(Stack<Container> stack : substack){
                for(Container c: stack){
                    containers.add(c);
                }
            }
        }
        return containers;
    }
    
}
