/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package API;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomás Pendão
 */
public class WarehouseTest {
    
    Warehouse armazem;
    
    @Before
    public void setUp() {
        System.out.println("Running set up");
        this.armazem = new Warehouse(500, 300, "armazém1");
    }
    
    @After
    public void tearDown() {
        System.out.println("Running tear down");
        this.armazem = null;
    }

    /**
     * Test of setCapacity method, of class Warehouse.
     */
    @Test
    public void testSetCapacity() {
        System.out.println("setCapacity");
        this.armazem.setCapacity(400);
        assertEquals(400, this.armazem.getMaxCapacity(),0);
    }

    /**
     * Test of setAvailableCapacity method, of class Warehouse.
     */
    @Test
    public void testSetAvailableCapacity() {
        System.out.println("setAvailableCapacity");
        this.armazem.setAvailableCapacity(500);
        assertEquals(500, this.armazem.getAvailableCapacity(),0);
    }

    /**
     * Test of printWarehouse method, of class Warehouse.
     */
    @Test
    public void testPrintWarehouse() {
        System.out.println("printWarehouse");
        assertEquals("Nome: armazém1;Tipo: Armazém;Capacidade: 500.0;Stock: 300.0", this.armazem.printWarehouse());
    }

    /**
     * Test of export method, of class Warehouse.
     */
    @Test
    public void testExport() {
        System.out.println("export");
        assertTrue(this.armazem.export());
    }

    /**
     * Test of getMaxCapacity method, of class Warehouse.
     */
    @Test
    public void testGetMaxCapacity() {
        System.out.println("getMaxCapacity");
        assertEquals(500, this.armazem.getMaxCapacity(),0);
    }

    /**
     * Test of getAvailableCapacity method, of class Warehouse.
     */
    @Test
    public void testGetAvailableCapacity() {
        System.out.println("getAvailableCapacity");
        assertEquals(300, this.armazem.getAvailableCapacity(),0);
    }

    /**
     * Test of importJSON method, of class Warehouse.
     */
    @Test
    public void testImportJSON() {
        System.out.println("importJSON");
        this.armazem = Warehouse.importJSON();
        
        assertEquals(500, this.armazem.getMaxCapacity(),0);
        assertEquals(300, this.armazem.getAvailableCapacity(),0);
        assertEquals("armazém1", this.armazem.getName());
        assertEquals("Armazém", this.armazem.getType());
    }
    
}
