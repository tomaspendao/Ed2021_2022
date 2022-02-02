/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package API;

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

    /**
     * Constrói um vendedor para a realização de testes.
     */
    @Before
    public void setUp() {
        System.out.println("Running set up");
        this.vendedor = new Seller(10, "qwe");
    }

    /**
     * Apaga um vendedor após a realização de testes.
     */
    @After
    public void tearDown() {
        System.out.println("Running tear down");
        this.vendedor = null;
    }

    /**
     * Testa o método addMarket da classe Seller.
     */
    @Test
    public void testAddMarket() {
        System.out.println("addMarket");
        this.vendedor.addMarket("merc1");
        assertEquals("merc1", this.vendedor.getMercados_a_visitar().first());
    }

    /**
     * Testa o método editCapacity da classe Seller.
     */
    @Test
    public void testEditCapacity() {
        System.out.println("editCapacity");
        this.vendedor.editCapacity(100);
        assertEquals(100, this.vendedor.getCapacidade(), 0);
    }

    /**
     * Testa o método printSeller da classe Seller.
     */
    @Test
    public void testPrintSeller() {
        System.out.println("printSeller");
        this.vendedor.printSeller(); //todo
    }

    /**
     * Testa o método exportJSON da classe Seller.
     */
    @Test
    public void testExportJSON() {
        System.out.println("export");
        this.vendedor.addMarket("mercado");
        System.out.println(this.vendedor.getMercados_a_visitar().size());
        assertTrue(this.vendedor.exportJSON());
    }

    /**
     * Testa o método getMercados_a_visitar da classe Seller.
     */
    @Test
    public void testGetMercados_a_visitar() {
        System.out.println("getMercados_a_visitar");
        assertEquals(0, this.vendedor.getMercados_a_visitar().size());
    }

    /**
     * Testa o método getCapacity da classe Seller.
     */
    @Test
    public void testGetCapacidade() {
        System.out.println("getCapacidade");
        assertEquals(10, this.vendedor.getCapacidade(), 0);
    }

    /**
     * Testa o método getId da classe Seller.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        assertEquals(0, this.vendedor.getId());
    }

    /**
     * Testa o método getNome da classe Seller.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        assertEquals("qwe", this.vendedor.getNome());
    }

    /**
     * Testa o método setId da classe Seller.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        this.vendedor.setId(120);
        assertEquals(120, this.vendedor.getId());
    }

    /**
     * Testa o método setNome da classe Seller.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        this.vendedor.setNome("Ze manel");
        assertEquals("Ze manel", this.vendedor.getNome());
    }

}