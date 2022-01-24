/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package API;

import Collections.LinkedList.MyLinkedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe utilizada para realizar testes à classe Seller
 *
 * @author Daniel Pinto
 */
public class SellerTest {

    Seller vendedor;

    public SellerTest() {
    }

    @Before
    public void setUp() {
        System.out.println("Running set up");
        this.vendedor = new Seller(10, "qwe");
    }

    @After
    public void tearDown() {
        System.out.println("Running tear down");
        this.vendedor = null;
    }

    /**
     * Test of addMarket method, of class Seller.
     */
    @Test
    public void testAddMarket() {
        System.out.println("addMarket");
        this.vendedor.addMarket("merc1");
        assertEquals("merc1", this.vendedor.getMercados_a_visitar().first());
    }

    /**
     * Test of editCapacity method, of class Seller.
     */
    @Test
    public void testEditCapacity() {
        System.out.println("editCapacity");
        this.vendedor.editCapacity(100);
        assertEquals(100, this.vendedor.getCapacidade(),0);
    }

    /**
     * Test of printSeller method, of class Seller.
     */
    @Test
    public void testPrintSeller() {
        System.out.println("printSeller");
        this.vendedor.printSeller(); //todo
    }

    /**
     * Test of export method, of class Seller.
     */
    @Test
    public void testExportJSON() {
        System.out.println("export");
        this.vendedor.addMarket("mercado");
        System.out.println(this.vendedor.getMercados_a_visitar().size());
        assertTrue(this.vendedor.exportJSON());
    }

    /**
     * Test of getMercados_a_visitar method, of class Seller.
     */
    @Test
    public void testGetMercados_a_visitar() {
        System.out.println("getMercados_a_visitar");
        assertEquals(0,this.vendedor.getMercados_a_visitar().size());
    }

    /**
     * Test of getCapacidade method, of class Seller.
     */
    @Test
    public void testGetCapacidade() {
        System.out.println("getCapacidade");
        assertEquals(10, this.vendedor.getCapacidade(),0);
    }

    /**
     * Test of getId method, of class Seller.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        assertEquals(0, this.vendedor.getId());
    }

    /**
     * Test of getNome method, of class Seller.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        assertEquals("qwe", this.vendedor.getNome());
    }

    /**
     * Test of setId method, of class Seller.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        this.vendedor.setId(120);
        assertEquals(120, this.vendedor.getId());
    }

    /**
     * Test of setNome method, of class Seller.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        this.vendedor.setNome("Ze manel");
        assertEquals("Ze manel", this.vendedor.getNome());
    }

}