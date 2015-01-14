package nhl.containing.server.pathfinding;

import java.util.List;

import com.jme3.math.Vector3f;

/**
 * Controller managing the shortest routes
 * 
 * @author Fre-Meine
 *
 */
public class RouteController extends ShortestPath {

	private Graph dijkstra;

	public RouteController() {
		dijkstra = new Graph(ShortestPath.GRAPH);
	}

	/**
	 * Returns a list of Vectors
	 */
	public List<Vector3f> sendAGV(String currentLocation, String destination) {
		dijkstra.dijkstra(currentLocation);
		List<Vector3f> list = dijkstra.getLocations(destination);
		return list;
	}
}
