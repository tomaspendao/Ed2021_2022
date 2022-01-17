/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package API;

import ADT.UnorderedListADT;
import Collections.DoubleLinkedList.DoubleLinkedUnorderedList;
import Collections.LinkedList.GraphWeightList;
import Models.Nodes.ArestaWeight;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomás Pendão
 */
public class CompanyTest {
    
    Company empresa;
    DoubleLinkedUnorderedList<Seller> sellers = new DoubleLinkedUnorderedList<>();
    DoubleLinkedUnorderedList<Place> places = new DoubleLinkedUnorderedList<>();
    GraphWeightList<Place> map = new GraphWeightList<>();
       
    public void setUp() {
        this.empresa = new Company(sellers,places,map,"empresa");
    }
    
    public void tearDown() {
        this.empresa = null;
    }

    /**
     * Test of addSeller method, of class Company.
     */
    /*@Test
    public void testAddSeller() {
        System.out.println("addSeller");
        Seller vendedor = null;
        Company instance = new Company();
        instance.addSeller(vendedor);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }*/

    /**
     * Test of editSeller method, of class Company.
     */
    /*@Test
    public void testEditSeller() {
        System.out.println("editSeller");
        Company instance = new Company();
        boolean expResult = false;
        boolean result = instance.editSeller();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }*/

    /**
     * Test of addMarket method, of class Company.
     */
    /*@Test
    public void testAddMarket() {
        System.out.println("addMarket");
        Market market = null;
        Company instance = new Company();
        instance.addMarket(market);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }*/

    /**
     * Test of editMarket method, of class Company.
     */
    /*@Test
    public void testEditMarket() {
        System.out.println("editMarket");
        Company instance = new Company();
        boolean expResult = false;
        boolean result = instance.editMarket();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }*/

    /**
     * Test of addWarehouse method, of class Company.
     */
    /*@Test
    public void testAddWarehouse() {
        System.out.println("addWarehouse");
        Warehouse warehouse = null;
        Company instance = new Company();
        instance.addWarehouse(warehouse);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }*/

    /**
     * Test of editWarehouse method, of class Company.
     */
    /*@Test
    public void testEditWarehouse() {
        System.out.println("editWarehouse");
        Company instance = new Company();
        boolean expResult = false;
        boolean result = instance.editWarehouse();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }*/

    /**
     * Test of addRoute method, of class Company.
     */
    /*@Test
    public void testAddRoute() {
        System.out.println("addRoute");
        Place start = null;
        Place dest = null;
        float weight = 0.0F;
        Company instance = new Company();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }*/

    /**
     * Test of editRoute method, of class Company.
     */
    /*@Test
    public void testEditRoute() {
        System.out.println("editRoute");
        Company instance = new Company();
        boolean expResult = false;
        boolean result = instance.editRoute();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }*/

    /**
     * Test of printSellers method, of class Company.
     */
    /*@Test
    public void testPrintSellers() {
        System.out.println("printSellers");
        Company instance = new Company();
        String expResult = "";
        String result = instance.printSellers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }*/

    /**
     * Test of printMarket method, of class Company.
     */
    /*@Test
    public void testPrintMarket() {
        System.out.println("printMarket");
        Company instance = new Company();
        String expResult = "";
        String result = instance.printMarket();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }*/

    /**
     * Test of printWarehouse method, of class Company.
     */
    /*@Test
    public void testPrintWarehouses() {
        System.out.println("printWarehouse");
        Company instance = new Company();
        String expResult = "";
        String result = instance.printWarehouses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of export method, of class Company.
     */
    @Test
    public void testExport() {
        this.setUp();
        
        this.empresa.addWarehouse(new Warehouse(500, 200, "armazem1"));
        this.empresa.addWarehouse(new Warehouse(502, 220, "armazem2"));
        System.out.println(this.empresa.printWarehouses());
        this.empresa.addRoute("empresa", "armazem1", 30);
        this.empresa.addRoute("armazem1", "armazem2", 30);
        this.empresa.addRoute("armazem1", "armazem2", 50);
        DoubleLinkedUnorderedList<ArestaWeight>[] paths = this.map.getAdjList();
        System.out.println(paths[0].toString());
        System.err.println(this.places.isEmpty());
        
        //System.out.println(this.empresa.locais.first());
        
        System.out.println("export");
        this.empresa.export();
        
        this.tearDown();
    }
    
}
