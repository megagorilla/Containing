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
        //agv = new AGV(500, "d2", "b1", 40, false);
        r = new RouteController();
        r.fillParkingLots();
    }

    public void createWayPoints() {
        wayPoints.put("", new Vector3f(0, 0, 0));
    }

    public void sendAGV1(String currentLocation, String destination) {
        dijkstra.dijkstra(currentLocation);
        List<Vector3f> list = dijkstra.getLocations(destination);
    }
    
    public List<Vector3f> sendAGV(String currentLocation, String destination)
    {
        dijkstra.dijkstra(currentLocation);
        List<Vector3f> list = dijkstra.getLocations(destination);
        return list;
    }

    public void fillParkingLots() {
        for (int i = 0; i < s.parkinglotSize; i++) {
            truckParking[i] = new AGV(i, "", "", 0, false);
            trainParking[i] = new AGV(i + s.parkinglotSize, "", "", 0, false);
            smallShipParking[i] = new AGV(i + s.parkinglotSize * 2, "", "", 0, false);
            bigShipParking[i] = new AGV(i + s.parkinglotSize * 3, "", "", 0, false);
        }
//        for (int i = 0; i < s.parkinglotSize; i++) {
//            System.out.println(truckParking[i].getAgvId());
//        }
//        for (int i = 0; i < s.parkinglotSize; i++) {
//            System.out.println(trainParking[i].getAgvId());
//        }
//        for (int i = 0; i < s.parkinglotSize; i++) {
//            System.out.println(smallShipParking[i].getAgvId());
//        }
//        for (int i = 0; i < s.parkinglotSize; i++) {
//            System.out.println(bigShipParking[i].getAgvId());
//        }
    }
}
