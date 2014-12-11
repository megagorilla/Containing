/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server.util;

import com.jme3.math.Vector3f;
import java.util.ArrayList;
import nhl.containing.server.util.XMLFileReader.*;
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
public class XMLFileReaderTest {

    public XMLFileReaderTest() {
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
     * Test of getContainers method, of class XMLFileReader.
     */
    @Test
    public void testGetContainers() {
        System.out.println("getContainers");
        XMLFileReader instance = new XMLFileReader();
        int expResult = 19965;
        ArrayList<Container> result = instance.getContainers("../XMLFILES/xmltest.xml");
        assertEquals(expResult, result.get(0).getContainerNumber());
    }
}