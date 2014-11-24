/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server.pathfinding;

import com.jme3.math.Vector3f;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Fré-Meine
 */
public class RouteController extends ShortestPath {

    private static AGV agv;
    private static Graph dijkstra;
    private static RouteController r;
    private static ShortestPath s;
    private static HashMap<String, Vector3f> wayPoints;

    public RouteController() {
        dijkstra = new Graph(ShortestPath.GRAPH);
        wayPoints = new HashMap<>();
        s = new ShortestPath();
    }

    public static void main(String[] args) {
        agv = new AGV(500, "a1", "b1", 40, false);
        r = new RouteController();
        r.sendAGV(agv.getCurrentLocation(), "c2");
        r.fillParkingLots();
        System.out.println(truckParkingS);
    }

    public void createWayPoints() {
        wayPoints.put("", new Vector3f(0, 0, 0));
    }

    public void sendAGV(String currentLocation, String destination) {
        dijkstra.dijkstra(currentLocation);
        dijkstra.printPath(destination);
    }

    public void fillParkingLots() {
        for (int i = 0; i < s.truckParking.length; i++) {
            truckParking[i] = new AGV(i, "", "", 0, false);
            trainParking[i] = new AGV(i + 25, "", "", 0, false);
            smallShipParking[i] = new AGV(i + 50, "", "", 0, false);
            bigShipParking[i] = new AGV(i + 75, "", "", 0, false);
        }
//        for (int i = 0; i < s.truckParking.length; i++) {
//            System.out.println(truckParking[i].getAgvId());
//        }
//        for (int i = 0; i < s.trainParking.length; i++) {
//            System.out.println(trainParking[i].getAgvId());
//        }
//        for (int i = 0; i < s.smallShipParking.length; i++) {
//            System.out.println(smallShipParking[i].getAgvId());
//        }
//        for (int i = 0; i < s.bigShipParking.length; i++) {
//            System.out.println(bigShipParking[i].getAgvId());
//        }
    }
}
