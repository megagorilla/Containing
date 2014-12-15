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
public class BargePlatformHandler {
    Stack<Stack<XMLFileReader.Container>[][]> listofBargeConatiners = new Stack<>();

    public BargePlatformHandler(ArrayList<XMLFileReader.Container> riverShipContainers) {
        boolean isFull;
        while(riverShipContainers.size()>0){
            listofBargeConatiners.add((Stack<XMLFileReader.Container>[][]) new Stack[13][3]);
           for(int i = 0; i<13;i++){
               for(int j = 0; j< 3;j++){
                   listofBargeConatiners.get(listofBargeConatiners.size()-1)[i][j] = new Stack<>();
               }
           }
           int x = (int)riverShipContainers.get(0).getPositie().x;
           int z = (int)riverShipContainers.get(0).getPositie().z;
            listofBargeConatiners.get(listofBargeConatiners.size()-1)[x][z].push(riverShipContainers.get(0));
            riverShipContainers.remove(0);
            isFull = false;
            while(riverShipContainers.size()>0 && !isFull){
                if(riverShipContainers.get(0).getPositie().equals(Vector3f.ZERO)){
                    isFull = true;
                }else{
                    x = (int)riverShipContainers.get(0).getPositie().x;
                    z = (int)riverShipContainers.get(0).getPositie().z;
                    listofBargeConatiners.get(listofBargeConatiners.size()-1)[x][z].push(riverShipContainers.get(0));
                    riverShipContainers.remove(0);
                }
            }
        }
        Collections.sort(listofBargeConatiners, (a,b) -> ((a[0][0].get(0).getArrival().getDay()) < (b[0][0].get(0).getArrival().getDay())) ? 1 : ((a[0][0].get(0).getArrival().getDay()) > (b[0][0].get(0).getArrival().getDay())) ? -1 : 0);
    }
    
    public int getDayOfNextShip(){
        return listofBargeConatiners.peek()[0][0].peek().getArrival().getDay();
    }
}
