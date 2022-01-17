/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package API;

import Collections.LinkedList.LinkedQueue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel Pinto
 */
public class MarketTest {
    
    Market mercado;
    
    @Before
    public void setUp() {
         System.out.println("Running set up");
        this.mercado = new Market("mercado1");
    }
    
    @After
    public void tearDown() {
        System.out.println("Running tear down");
        this.mercado=null;
    }

    /**
     * Test of addClients method, of class Market.
     */
    @Test
    public void testAddClients() {
        System.out.println("addClients");
        float demand = 0.0F;
        Market instance = new Market();
        instance.addClients(demand);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeClient method, of class Market.
     */
    @Test
    public void testRemoveClient() {
        System.out.println("removeClient");
        Market instance = new Market();
        boolean expResult = false;
        boolean result = instance.removeClient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printClients method, of class Market.
     */
    @Test
    public void testPrintClients() {
        System.out.println("printClients");
        Market instance = new Market();
        String expResult = "";
        String result = instance.printClients();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of export method, of class Market.
     */
    @Test
    public void testExport() {
       System.out.println("export");
        assertTrue(this.mercado.export());
    }

    /**
     * Test of getClients method, of class Market.
     */
    @Test
    public void testGetClients() {
        System.out.println("getClients");
        Market instance = new Market();
        LinkedQueue expResult = null;
        LinkedQueue result = instance.getClients();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
