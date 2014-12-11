/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server.network;

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
public class ConnectionManagerTest {
    boolean result;
    
    public ConnectionManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        int port = 3000;
        result = ConnectionManager.initialize(port);
    }
    
    @After
    public void tearDown() {
        ConnectionManager.stop();
    }

    /**
     * Test of initialize method, of class ConnectionManager.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        assertTrue(result);
    }



    /**
     * Test of sendCommand method, of class ConnectionManager.
     */
    @Test
    public void testSendCommand_UpdateMessage() {
        System.out.println("sendCommand");
        UpdateMessage msg = null;
        ConnectionManager.sendCommand(msg);
    }

    /**
     * Test of hasConnections method, of class ConnectionManager.
     */
    @Test
    public void testHasConnections() {
        System.out.println("hasConnections");
        boolean expResult = false;
        boolean result = ConnectionManager.hasConnections();
        assertEquals(expResult, result);
    }
}