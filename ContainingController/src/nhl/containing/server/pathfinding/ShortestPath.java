package nhl.containing.server.pathfinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import com.jme3.math.Vector3f;
import java.util.List;

/**
 * Defines all nodes for the graph
 * @author Fre-Meine
 *
 */
public class ShortestPath {

	private static final float WIDTH = 300.0f;
	private static final float HEIGHT = 775.0f;
	private static final float DEPTH = 0.0f;
	private static final float truckPlatform = 525.0f;
	private static final float sideLane = 50.0f;
	private static final float laneOne = 3.5f;
	private static final float laneTwo = 16.5f;
	public static final Graph.Edge[] GRAPH = {
        
    	/**
    	 * Graph Nodes
    	 */
        //Lane One
        new Graph.Edge(new Graph.WayPoint("a1", new Vector3f(WIDTH + laneOne, DEPTH, -HEIGHT - laneOne)),
        new Graph.WayPoint("a4", new Vector3f(WIDTH + laneOne, DEPTH, -HEIGHT + truckPlatform))),
        new Graph.Edge(new Graph.WayPoint("a4", new Vector3f(WIDTH + laneOne, DEPTH, -sideLane)),
        new Graph.WayPoint("b1", new Vector3f(WIDTH + laneOne, DEPTH, sideLane))),
        new Graph.Edge(new Graph.WayPoint("b1", new Vector3f(WIDTH + laneOne, DEPTH, sideLane)),
        new Graph.WayPoint("b4", new Vector3f(WIDTH + laneOne, DEPTH, HEIGHT + laneOne))),
        new Graph.Edge(new Graph.WayPoint("b4", new Vector3f(WIDTH + laneOne, DEPTH, HEIGHT + laneOne)),
        new Graph.WayPoint("c4", new Vector3f(-WIDTH - laneOne, DEPTH, HEIGHT + laneOne))),
        new Graph.Edge(new Graph.WayPoint("c4", new Vector3f(-WIDTH - laneOne, DEPTH, HEIGHT + laneOne)),
        new Graph.WayPoint("d4", new Vector3f(-WIDTH - laneOne, DEPTH, -HEIGHT - laneOne))),
        new Graph.Edge(new Graph.WayPoint("d4", new Vector3f(-WIDTH - laneOne, DEPTH, -HEIGHT - laneOne)),
        new Graph.WayPoint("a1", new Vector3f(WIDTH + laneOne, DEPTH, -HEIGHT - laneOne))),
        //Lane Two
        new Graph.Edge(new Graph.WayPoint("a1.2", new Vector3f(WIDTH + laneTwo, DEPTH, -HEIGHT - laneTwo)),
        new Graph.WayPoint("d4.2", new Vector3f(-WIDTH - laneTwo, DEPTH, -HEIGHT - laneTwo))),
        new Graph.Edge(new Graph.WayPoint("d4.2", new Vector3f(-WIDTH - laneTwo, DEPTH, -HEIGHT - laneTwo)),
        new Graph.WayPoint("c4.2", new Vector3f(-WIDTH - laneTwo, DEPTH, HEIGHT + laneTwo))),
        new Graph.Edge(new Graph.WayPoint("c4.2", new Vector3f(-WIDTH - laneTwo, DEPTH, HEIGHT + laneTwo)),
        new Graph.WayPoint("b4.2", new Vector3f(WIDTH + laneTwo, DEPTH, HEIGHT + laneTwo))),
        new Graph.Edge(new Graph.WayPoint("b4.2", new Vector3f(WIDTH + laneTwo, DEPTH, HEIGHT + laneTwo)),
        new Graph.WayPoint("b1.2", new Vector3f(WIDTH + laneTwo, DEPTH, sideLane))),
        new Graph.Edge(new Graph.WayPoint("b1.2", new Vector3f(WIDTH + laneTwo, DEPTH, sideLane)),
        new Graph.WayPoint("a4.2", new Vector3f(WIDTH + laneTwo, DEPTH, -HEIGHT + truckPlatform))),
        new Graph.Edge(new Graph.WayPoint("a4.2", new Vector3f(WIDTH + laneTwo, DEPTH, -HEIGHT + truckPlatform)),
        new Graph.WayPoint("a1.2", new Vector3f(WIDTH + laneTwo, DEPTH, -HEIGHT - laneTwo))),
        //Lane changes
    	new Graph.Edge(new Graph.WayPoint("a1", new Vector3f(WIDTH + laneOne, DEPTH, -HEIGHT - laneOne)),
		new Graph.WayPoint("a1.2", new Vector3f(WIDTH + laneTwo, DEPTH, -HEIGHT - laneTwo))),
        new Graph.Edge(new Graph.WayPoint("a1.2", new Vector3f(WIDTH + laneTwo, DEPTH, -HEIGHT - laneTwo)),
        new Graph.WayPoint("a1", new Vector3f(WIDTH + laneOne, DEPTH, -HEIGHT - laneOne))),
        new Graph.Edge(new Graph.WayPoint("a4", new Vector3f(WIDTH + laneOne, DEPTH, -HEIGHT + truckPlatform)),
        new Graph.WayPoint("a4.2", new Vector3f(WIDTH + laneTwo, DEPTH, -HEIGHT + truckPlatform))),
        new Graph.Edge(new Graph.WayPoint("a4.2", new Vector3f(WIDTH + laneTwo, DEPTH, -HEIGHT + truckPlatform)),
        new Graph.WayPoint("a4", new Vector3f(WIDTH + laneOne, DEPTH, -HEIGHT + truckPlatform))),
        new Graph.Edge(new Graph.WayPoint("b4", new Vector3f(WIDTH + laneOne, DEPTH, HEIGHT + laneOne)),
        new Graph.WayPoint("b4.2", new Vector3f(WIDTH + laneTwo, DEPTH, HEIGHT + laneTwo))),
        new Graph.Edge(new Graph.WayPoint("b4.2", new Vector3f(WIDTH + laneTwo, DEPTH, HEIGHT + laneTwo)),
        new Graph.WayPoint("b4", new Vector3f(WIDTH + laneOne, DEPTH, HEIGHT + laneOne))),
        new Graph.Edge(new Graph.WayPoint("c4", new Vector3f(-WIDTH - laneOne, DEPTH, HEIGHT + laneOne)),
        new Graph.WayPoint("c4.2", new Vector3f(-WIDTH - laneTwo, DEPTH, HEIGHT + laneTwo))),
        new Graph.Edge(new Graph.WayPoint("c4.2", new Vector3f(-WIDTH - laneTwo, DEPTH, HEIGHT + laneTwo)),
        new Graph.WayPoint("c4", new Vector3f(-WIDTH - laneOne, DEPTH, HEIGHT + laneOne))),
        new Graph.Edge(new Graph.WayPoint("d4", new Vector3f(-WIDTH - laneOne, DEPTH, -HEIGHT - laneOne)),
	    new Graph.WayPoint("d4.2", new Vector3f(-WIDTH - laneTwo, DEPTH, -HEIGHT - laneTwo))),
	    new Graph.Edge(new Graph.WayPoint("d4.2", new Vector3f(-WIDTH - laneTwo, DEPTH, -HEIGHT - laneTwo)),
	    new Graph.WayPoint("d4", new Vector3f(-WIDTH - laneOne, DEPTH, -HEIGHT - laneOne))),   
        //truck platform
        new Graph.Edge(new Graph.WayPoint("a1", new Vector3f(WIDTH + laneOne, DEPTH, -HEIGHT - laneOne)),
        new Graph.WayPoint("a2", new Vector3f(WIDTH + sideLane + laneOne, DEPTH,-HEIGHT - laneOne))),
        new Graph.Edge(new Graph.WayPoint("a2", new Vector3f(WIDTH + sideLane + laneOne, DEPTH, -HEIGHT - laneOne)),
        new Graph.WayPoint("a3", new Vector3f(WIDTH + sideLane + laneOne, DEPTH, -HEIGHT + truckPlatform))),
        new Graph.Edge(new Graph.WayPoint("a3", new Vector3f(WIDTH + sideLane + laneOne, DEPTH, -HEIGHT + truckPlatform)),
        new Graph.WayPoint("a4", new Vector3f(WIDTH + laneOne, DEPTH, -HEIGHT + truckPlatform))),
        new Graph.Edge(new Graph.WayPoint("a3", new Vector3f(WIDTH + sideLane + laneOne, DEPTH, -HEIGHT + truckPlatform)),
        new Graph.WayPoint("a4.2", new Vector3f(WIDTH + laneTwo, DEPTH, -HEIGHT + truckPlatform))),
        //river ship platform
        new Graph.Edge(new Graph.WayPoint("b1", new Vector3f(WIDTH + laneOne, DEPTH, sideLane)),
        new Graph.WayPoint("b2", new Vector3f(WIDTH + 50, DEPTH, 50))),
        new Graph.Edge(new Graph.WayPoint("b2", new Vector3f(WIDTH + 50, DEPTH, 50)),
        new Graph.WayPoint("b3", new Vector3f(WIDTH + 50, DEPTH, HEIGHT))),
        new Graph.Edge(new Graph.WayPoint("b3", new Vector3f(WIDTH + 50, DEPTH, HEIGHT)),
        new Graph.WayPoint("b4", new Vector3f(WIDTH + laneOne, DEPTH, HEIGHT + laneOne))),
        //sea ship platform
        new Graph.Edge(new Graph.WayPoint("b4", new Vector3f(WIDTH, DEPTH, HEIGHT)),
        new Graph.WayPoint("c2", new Vector3f(WIDTH+laneTwo, DEPTH, HEIGHT + 107.5f))),
        new Graph.Edge(new Graph.WayPoint("c2", new Vector3f(WIDTH+laneTwo, DEPTH, HEIGHT + 107.5f)),
        new Graph.WayPoint("c3", new Vector3f(-WIDTH-laneTwo, DEPTH, HEIGHT + 107.5f))),
        new Graph.Edge(new Graph.WayPoint("c3", new Vector3f(-WIDTH -laneTwo, DEPTH, HEIGHT + 107.5f)),
        new Graph.WayPoint("c4.2", new Vector3f(-WIDTH -laneTwo, DEPTH, HEIGHT + laneTwo))),
        //train platform
        new Graph.Edge(new Graph.WayPoint("c4", new Vector3f(-WIDTH - laneOne, DEPTH, HEIGHT + laneOne)),
        new Graph.WayPoint("d2", new Vector3f(-WIDTH - 50, DEPTH, HEIGHT))),
        new Graph.Edge(new Graph.WayPoint("d2", new Vector3f(-WIDTH - 50, DEPTH, HEIGHT)),
        new Graph.WayPoint("d3", new Vector3f(-WIDTH - 50, DEPTH, -HEIGHT))),
        new Graph.Edge(new Graph.WayPoint("d3", new Vector3f(-WIDTH - 50, DEPTH, -HEIGHT)),
        new Graph.WayPoint("d4", new Vector3f(-WIDTH - laneOne, DEPTH, -HEIGHT - laneOne))),
        };

	public ShortestPath() {
	}
}

/**
 * Connects the waypoints together by name and x,z values in the Vector3f
 * 
 * @author Fre-Meine
 *
 */
class Graph {

	private final Map<String, Vertex> graph; // mapping of vertex names to
												// Vertex objects, built from a
												// set of Edges
	static List<Vector3f> list = new ArrayList<Vector3f>();

	/**
	 * One edge of the graph (only used by Graph constructor)
	 */
	public static class Edge {

		public final String v1, v2;
		public final Vector3f l1, l2;
		public final int dist;

		public Edge(Graph.WayPoint v1, Graph.WayPoint v2) {
			this.v1 = v1.name;
			this.v2 = v2.name;
			this.l1 = v1.location;
			this.l2 = v2.location;
			if (v1.location.x != v2.location.x) {
				this.dist = (int) Math.abs(v2.location.x - v1.location.x);
			}
			else {
				this.dist = (int) Math.abs(v2.location.z - v1.location.z);
			}
		}
	}

	/**
	 * Creates waypoints for the simulation by using Vector3f
	 * 
	 * @author Fre-Meine
	 *
	 */
	public static class WayPoint {

		public Vector3f location;
		public String name;

		public WayPoint(String name, Vector3f location) {
			this.name = name;
			this.location = location;
		}
	}

	/**
	 * One vertex of the graph, complete with mappings to neighbouring vertices
	 */
	public static class Vertex implements Comparable<Vertex> {

		public final String name;
		public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
		public Vertex previous = null;
		public Vector3f loc;
		public final Map<Vertex, Integer> neighbours = new HashMap<>();

		public Vertex(String name, Vector3f loc) {
			this.name = name;
			this.loc = loc;
		}

		/**
		 * Prints shortest route, if it is reachable or not. If it is not
		 * reachable the graph is incomplete.
		 */
		private void printPath() {
			if (this == this.previous) {
				System.out.printf("%s (%s)", this.name, this.loc);
			}
			else if (this.previous == null) {
				System.out.printf("%s(unreached)", this.name);
			}
			else {
				this.previous.printPath();
				System.out.printf(" -> %s(%s)", this.name, this.loc);
			}
		}

		private void getLocations() {
			if (this == this.previous) {
				list.add(this.loc);
			}
			else if (this.previous == null) {
				System.out.printf("%s(unreached)", this.name);
			}
			else {
				this.previous.getLocations();
				list.add(this.loc);
			}
		}

		/**
         * 
         */
		@Override
		public int compareTo(Vertex other) {
			return Integer.compare(dist, other.dist);
		}
	}

	/**
	 * Builds a graph from a set of edges
	 */
	public Graph(Edge[] edges) {
		graph = new HashMap<>(edges.length);

		// one pass to find all vertices
		for (Edge e : edges) {
			if (!graph.containsKey(e.v1)) {
				graph.put(e.v1, new Vertex(e.v1, e.l1));
			}
			if (!graph.containsKey(e.v2)) {
				graph.put(e.v2, new Vertex(e.v2, e.l2));
			}
		}

		// another pass to set neighbouring vertices
		for (Edge e : edges) {
			graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
		}
	}

	/**
	 * Runs dijkstra using a specified source vertex
	 */
	public void dijkstra(String startName) {
		if (!graph.containsKey(startName)) {
			System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
			return;
		}
		final Vertex source = graph.get(startName);
		NavigableSet<Vertex> q = new TreeSet<>();

		// set-up vertices
		for (Vertex v : graph.values()) {
			v.previous = v == source ? source : null;
			v.dist = v == source ? 0 : Integer.MAX_VALUE;
			q.add(v);
		}

		dijkstra(q);
	}

	/**
	 * Implementation of dijkstra's algorithm using a binary heap.
	 */
	private void dijkstra(final NavigableSet<Vertex> q) {
		Vertex u, v;
		while (!q.isEmpty()) {

			u = q.pollFirst(); // vertex with shortest distance (first iteration
								// will return source)
			if (u.dist == Integer.MAX_VALUE) {
				break; // we can ignore u (and any other remaining vertices)
						// since they are unreachable
			}
			// look at distances to each neighbour
			for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
				v = a.getKey(); // the neighbour in this iteration

				final int alternateDist = u.dist + a.getValue();
				if (alternateDist < v.dist) { // shorter path to neighbour found
					q.remove(v);
					v.dist = alternateDist;
					v.previous = u;
					q.add(v);
				}
			}
		}
	}

	/**
	 * Prints a path from the source to the specified vertex
	 */
	public void printPath(String endName) {
		if (!graph.containsKey(endName)) {
			System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
			return;
		}

		graph.get(endName).printPath();
		System.out.println();
	}

	public List<Vector3f> getLocations(String endName) {
		if (!graph.containsKey(endName)) {
			System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
			return null;
		}
		list.clear();
		graph.get(endName).getLocations();
		return list;
	}

	/**
	 * Prints the path from the source to every vertex (output order is not
	 * guaranteed)
	 */
	public void printAllPaths() {
		for (Vertex v : graph.values()) {
			v.printPath();
			System.out.println();
		}
	}
}