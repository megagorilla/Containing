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
import nhl.containing.server.util.XMLFileReader;

/**
 *
 * @author Sander
 */
public class SeaShipPlatformHandler {
    Stack<Stack<XMLFileReader.Container>[][]> listofSeaShipContainers = new Stack<>();

    public SeaShipPlatformHandler(ArrayList<XMLFileReader.Container> seaShipContainers) {
        boolean isFull;
        while(seaShipContainers.size()>0){
           listofSeaShipContainers.add((Stack<XMLFileReader.Container>[][]) new Stack[21][6]);
           for(int i = 0; i<21;i++){
               for(int j = 0; j< 6;j++){
                   listofSeaShipContainers.get(listofSeaShipContainers.size()-1)[i][j] = new Stack<>();
               }
           }
           int x = (int)seaShipContainers.get(0).getPositie().x;
           int z = (int)seaShipContainers.get(0).getPositie().z;
           listofSeaShipContainers.get(listofSeaShipContainers.size()-1)[x][z].push(seaShipContainers.get(0));
            seaShipContainers.remove(0);
            isFull = false;
            while(seaShipContainers.size()>0 && !isFull){
                if(seaShipContainers.get(0).getPositie().equals(Vector3f.ZERO)){
                    isFull = true;
                }else{
                    x = (int)seaShipContainers.get(0).getPositie().x;
                    z = (int)seaShipContainers.get(0).getPositie().z;
                    listofSeaShipContainers.get(listofSeaShipContainers.size()-1)[x][z].push(seaShipContainers.get(0));
                    seaShipContainers.remove(0);
                }
            }
        }
        Collections.sort(listofSeaShipContainers, (a,b) -> ((a[0][0].get(0).getArrival().getDay()) < (b[0][0].get(0).getArrival().getDay())) ? 1 : ((a[0][0].get(0).getArrival().getDay()) > (b[0][0].get(0).getArrival().getDay())) ? -1 : 0);
    }
    
    public int getDayOfNextShip(){
        return listofSeaShipContainers.peek()[0][0].peek().getArrival().getDay();
    }
    
}
