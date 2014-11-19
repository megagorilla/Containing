/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server.pathfinding;

/**
 * 
 * @author Fré-Meine
 */
public class RouteController
{

	private static AGV agv;
	private static Graph dijkstra;

	@SuppressWarnings("unused")
	private static ShortestPath s;

	private static RouteController r;

	public RouteController()
	{
		agv = new AGV(1, "d3", "c2", 40, false);
		s = new ShortestPath();
		dijkstra = new Graph(ShortestPath.GRAPH);

	}

	public static void main(String[] args)
	{
		r = new RouteController();
		r.sendAGV(agv.getCurrentLocation(), "c2");
	}

	public void sendAGV(String currentLocation, String destination)
	{
		dijkstra.dijkstra(currentLocation);
		dijkstra.printPath(destination);
	}
}
