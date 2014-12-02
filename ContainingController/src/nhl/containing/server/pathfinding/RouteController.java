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

    public void createWayPoints() {
        wayPoints.put("", new Vector3f(0, 0, 0));
    }
    
    public List<Vector3f> sendAGV(String currentLocation, String destination)
    {
        dijkstra.dijkstra(currentLocation);
        List<Vector3f> list = dijkstra.getLocations(destination);
        return list;
    }    
}
