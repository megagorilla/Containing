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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initialize method, of class ConnectionManager.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        int port = 3000;
        boolean expResult = false;
        boolean result = ConnectionManager.initialize(port);
        assertTrue(result);
    }

    /**
     * Test of stop method, of class ConnectionManager.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        ConnectionManager.stop();
    }

    /**
     * Test of sendCommand method, of class ConnectionManager.
     */
    @Test
    public void testSendCommand_int_UpdateMessage() {
        System.out.println("sendCommand");
        int connID = 0;
        UpdateMessage msg = new UpdateMessage("TestMessage");
        ConnectionManager.sendCommand(connID, msg);
    }

    /**
     * Test of sendCommand method, of class ConnectionManager.
     */
    @Test
    public void testSendCommand_UpdateMessage() {
        System.out.println("sendCommand");
        UpdateMessage msg = null;
        ConnectionManager.sendCommand(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}