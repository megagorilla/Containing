/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server.pathfinding;

import com.jme3.math.Vector3f;
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
     */
    @Test
    public void testDijkstra() {
        System.out.println("dijkstra");
        String startName = "";
        Graph instance = new Graph(ShortestPath.GRAPH);
        instance.dijkstra("a1");
        List<Vector3f> list = instance.getLocations("a1");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printPath method, of class Graph.
     */
    @Test
    public void testPrintPath() {
        System.out.println("printPath");
        String startName = "a1";
        String endNameRight = "d4";
        String endNameWrong = "herpderp";
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
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getLocations method, of class Graph.
     */
    @Test
    public void testGetLocations() {
        System.out.println("getLocations");
        String endName = "";
        Graph instance = null;
        List expResult = null;
        List result = instance.getLocations(endName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printAllPaths method, of class Graph.
     */
    @Test
    public void testPrintAllPaths() {
        System.out.println("printAllPaths");
        Graph instance = null;
        instance.printAllPaths();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}