/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package API;

import ADT.UnorderedListADT;
import Collections.LinkedList.GraphWeightList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel Pinto
 */
public class RouteUtilsTest {
    
    public RouteUtilsTest() {
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
     * Test of generateRoute method, of class RouteUtils.
     */
    @Test
    public void testGenerateRoute() {
        System.out.println("generateRoute");
        GraphWeightList<Place> caminhos = null;
        Seller seller = null;
        UnorderedListADT<Place> locais = null;
        Company empresa = null;
        Route expResult = null;
        Route result = RouteUtils.generateRoute(caminhos, seller, locais, empresa);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of refillRoute method, of class RouteUtils.
     */
    @Test
    public void testRefillRoute() {
        System.out.println("refillRoute");
        GraphWeightList<Place> caminhos = null;
        UnorderedListADT<Warehouse> armazens = null;
        Place start = null;
        float need = 0.0F;
        Route rotaFinal = null;
        RouteUtils.refillRoute(caminhos, armazens, start, need, rotaFinal);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
