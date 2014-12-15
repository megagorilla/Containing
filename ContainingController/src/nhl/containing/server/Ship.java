/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server;

import java.util.ArrayList;
import java.util.Stack;
import nhl.containing.server.util.XMLFileReader.*;

/**
 *
 * @author Sander
 */
public class Ship {

    int arrivalDay;
    Stack<Container>[][] containers;

    public Ship(ArrayList<Container> containers) {
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
        
        for(Container c : containers){
            int x = (int) c.getPositie().x;
            int z = (int) c.getPositie().z;
            this.containers[x][z].push(c);
        }
        
    }

    public int getArrivalDay() {
        return arrivalDay;
    }
    
    

}
