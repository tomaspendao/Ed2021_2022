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
 * Classe utilizada para realizar testes à classe Seller.
 *
 * @author Daniel Pinto
 */
public class SellerTest {

    Seller vendedor;

    public SellerTest() {
    }

    /**
     * Constrói um mercado para a realização de testes.
     */
    @Before
    public void setUp() {
        System.out.println("Running set up");

        this.vendedor = new Seller(10, 1, "qwe");
    }

    /**
     * Apaga um mercado após a realização de testes.
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

        String name = "";
        Seller instance = new Seller();
        instance.addMarket(name);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Testa o método editCapacity da classe Seller.
     */
    @Test
    public void testEditCapacity() {
        System.out.println("editCapacity");

        float newCapacity = 0.0F;
        Seller instance = new Seller();
        boolean expResult = false;
        boolean result = instance.editCapacity(newCapacity);
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Testa o método printSeller da classe Seller.
     */
    @Test
    public void testPrintSeller() {
        System.out.println("printSeller");

        Seller instance = new Seller();
        instance.printSeller();

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Testa o método exportJSON da classe Seller.
     */
    @Test
    public void testExportJSON() {
        System.out.println("export");

        assertTrue(this.vendedor.exportJSON());
    }

    /**
     * Testa o método getMercados_a_visitar da classe Seller.
     */
    @Test
    public void testGetMercados_a_visitar() {
        System.out.println("getMercados_a_visitar");

        Seller instance = new Seller();
        MyLinkedList expResult = null;
        //MyLinkedList result = instance.getMercados_a_visitar();
        //assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Testa o método getCapacidade da classe Seller.
     */
    @Test
    public void testGetCapacidade() {
        System.out.println("getCapacidade");

        Seller instance = new Seller();
        float expResult = 0.0F;
        float result = instance.getCapacidade();
        assertEquals(expResult, result, 0.0);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Testa o método getId da classe Seller.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");

        Seller instance = new Seller();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Testa o método getNome da classe Seller.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");

        Seller instance = new Seller();
        String expResult = "";
        String result = instance.getNome();
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Testa o método setId da classe Seller.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");

        int id = 0;
        Seller instance = new Seller();
        instance.setId(id);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Testa o método setNome da classe Seller.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");

        String nome = "";
        Seller instance = new Seller();
        instance.setNome(nome);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Testa o método importJSON da classe Seller.
     */
    @Test
    public void testImportJSON() {
        System.out.println("importJSON");

        Seller instance = new Seller();
        boolean expResult = false;
        boolean result = instance.importJSON();
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
