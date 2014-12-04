package nhl.containing.server.pathfinding;

import com.jme3.math.Vector3f;
import java.util.HashMap;
import java.util.List;

/**
 * Controller managing the shortest routes
 * @author Fre-Meine
 *
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

    /**
     * Fills the HashMap with a String and Vector3f
     */
    public void createWayPoints() {
        wayPoints.put("", new Vector3f(0, 0, 0));
    }
    
    /**
     * Returns a list of Vectors
     */
    public List<Vector3f> sendAGV(String currentLocation, String destination)
    {
        dijkstra.dijkstra(currentLocation);
        List<Vector3f> list = dijkstra.getLocations(destination);
        return list;
    }    
}
