/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server.pathfinding;

import com.jme3.math.Vector3f;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sander
 */
public class GraphTest {
    
    public GraphTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of dijkstra method, of class Graph.
     * not testable
     */
//    @Test
//    public void testDijkstra() {
//        System.out.println("dijkstra");
//        String startName = "a1";
//        Graph instance = new Graph(ShortestPath.GRAPH);
//        instance.dijkstra(startName);
//        
//        String startName2 = "";
//        Graph instance2 = new Graph(ShortestPath.GRAPH);
//        instance2.dijkstra(startName2);
//    }

    /**
     * Test of printPath method, of class Graph.
     */
    @Test
    public void testPrintPath() {
        System.out.println("printPath");
        String startName = "a1";
        String endNameRight = "a2";
        String endNameWrong = "wrongAtPringPath";
        
        Graph instance = new Graph(ShortestPath.GRAPH);
        instance.dijkstra(startName);
        List<Vector3f> list = instance.getLocations(endNameRight);
        if(list == null)
        {
            fail("The list is empty while endName is righ");
        }
        
        instance = new Graph(ShortestPath.GRAPH);
        instance.dijkstra(startName);
        List<Vector3f> list2 = instance.getLocations(endNameWrong);
        if(list2 != null){
            fail("The list is not  empty while endName is wrong");
        }
        
    }

    /**
     * Test of getLocations method, of class Graph.
     */
    @Test
    public void testGetLocations() {
        System.out.println("getLocations");
        String endNameRight = "d4";
        String endNameWrong = "wrongAtGetLocations";
        
        Graph instance = new Graph(ShortestPath.GRAPH);
        instance.dijkstra("a1");
        
        List expResultRight = new ArrayList();
        expResultRight.add(new Vector3f(303.5f, 0.0f, -778.5f));
        expResultRight.add(new Vector3f(316.5f, 0.0f, -791.5f));
        expResultRight.add(new Vector3f(-316.5f, 0.0f, -791.5f));
        expResultRight.add(new Vector3f(-303.5f, 0.0f, -778.5f));
        
        List result = instance.getLocations(endNameRight);
        assertEquals(expResultRight, result);
        
        Graph instance2 = new Graph(ShortestPath.GRAPH);
        instance.dijkstra("a1");
        List result2 = instance2.getLocations(endNameWrong);
        assertNull(result2);
    }

    /**
     * Test of printAllPaths method, of class Graph.
     * Not Used and not testable
     */
//    @Test
//    public void testPrintAllPaths() {
//        System.out.println("printAllPaths");
//        Graph instance =  new Graph(ShortestPath.GRAPH);
//        instance.printAllPaths();
//        
//    }
}