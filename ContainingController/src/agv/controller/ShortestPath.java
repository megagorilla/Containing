/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agv.controller;

import java.util.*;

public class ShortestPath {

    //1550 * 600
    //775 - 300
    private static final Graph.Edge[] GRAPH = {
        new Graph.Edge("a1", "a2", 50),
        new Graph.Edge("a1", "a4", 770),
        new Graph.Edge("a2", "a3", 770),
        new Graph.Edge("a3", "a4", 50),
        new Graph.Edge("a4", "o3", 5),
        new Graph.Edge("o3", "b1", 5),
        new Graph.Edge("b1", "b2", 50),
        new Graph.Edge("b1", "b4", 770),
        new Graph.Edge("b2", "b3", 770),
        new Graph.Edge("b3", "b4", 50),
        new Graph.Edge("b4", "c1", 5),
        new Graph.Edge("c1", "c2", 50),
        new Graph.Edge("c1", "o4", 145),
        new Graph.Edge("o4", "c4", 145),
        new Graph.Edge("c2", "c3", 290),
        new Graph.Edge("c3", "c4", 50),
        new Graph.Edge("c4", "d1", 5),
        new Graph.Edge("d1", "d2", 50),
        new Graph.Edge("d1", "o5", 770),
        new Graph.Edge("o5", "d4", 770),
        new Graph.Edge("d2", "d3", 1545),
        new Graph.Edge("d3", "d4", 50),
        new Graph.Edge("d4", "o2", 150),
        new Graph.Edge("o2", "a1", 150),
        new Graph.Edge("o1", "o2", 775),
        new Graph.Edge("o1", "o3", 150),
        new Graph.Edge("o1", "o4", 775),
        new Graph.Edge("o1", "o5", 150)};
    private static final String START = "d3";
    private static final String END = "c2";

    public static void main(String[] args) {
        Graph g = new Graph(GRAPH);
        g.dijkstra(START);
        g.printPath(END);
        //g.printAllPaths();
    }
}

class Graph {

    private final Map<String, Vertex> graph; // mapping of vertex names to Vertex objects, built from a set of Edges

    /**
     * One edge of the graph (only used by Graph constructor)
     */
    public static class Edge {

        public final String v1, v2;
        public final int dist;

        public Edge(String v1, String v2, int dist) {
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }
    }

    /**
     * One vertex of the graph, complete with mappings to neighbouring vertices
     */
    public static class Vertex implements Comparable<Vertex> {

        public final String name;
        public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
        public Vertex previous = null;
        public final Map<Vertex, Integer> neighbours = new HashMap<>();

        public Vertex(String name) {
            this.name = name;
        }

        private void printPath() {
            if (this == this.previous) {
                System.out.printf("%s", this.name);
            } else if (this.previous == null) {
                System.out.printf("%s(unreached)", this.name);
            } else {
                this.previous.printPath();
                System.out.printf(" -> %s(%d)", this.name, this.dist);
            }
        }

        public int compareTo(Vertex other) {
            return Integer.compare(dist, other.dist);
        }
    }

    /**
     * Builds a graph from a set of edges
     */
    public Graph(Edge[] edges) {
        graph = new HashMap<>(edges.length);

        //one pass to find all vertices
        for (Edge e : edges) {
            if (!graph.containsKey(e.v1)) {
                graph.put(e.v1, new Vertex(e.v1));
            }
            if (!graph.containsKey(e.v2)) {
                graph.put(e.v2, new Vertex(e.v2));
            }
        }

        //another pass to set neighbouring vertices
        for (Edge e : edges) {
            graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
            //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
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

            u = q.pollFirst(); // vertex with shortest distance (first iteration will return source)
            if (u.dist == Integer.MAX_VALUE) {
                break; // we can ignore u (and any other remaining vertices) since they are unreachable
            }
            //look at distances to each neighbour
            for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
                v = a.getKey(); //the neighbour in this iteration

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