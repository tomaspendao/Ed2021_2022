/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package API;

import Exceptions.InvalidValueException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomás Pendão
 */
public class PlaceTest {

    Place place1;
    Place place2;
    Place place3;
   
    
    @Before
    public void setUp() {
        System.out.println("Running set up");
        this.place1 = new Place("Local1", "Armazém") {};
        this.place2 = new Place("Local2", "Sede") {};
        this.place3 = new Place("Local3", "Mercado") {};
        
    }

    @After
    public void tearDown() {
        System.out.println("Running tear down");
        this.place1 = null;
        this.place2 = null;
        this.place3 = null;
        
    }

    /**
     * Test of getName method, of class Place.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        assertEquals("Local1", this.place1.getName());
        assertEquals("Local2", this.place2.getName());
        assertEquals("Local3", this.place3.getName());
       
    }

    /**
     * Test of getType method, of class Place.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        assertEquals("Armazém", this.place1.getType());
        assertEquals("Sede", this.place2.getType());
        assertEquals("Mercado", this.place3.getType());
    }
}
