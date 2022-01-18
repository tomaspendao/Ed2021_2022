/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package API;

import Collections.LinkedList.LinkedQueue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe utilizada para realizar testes à classe Market.
 *
 * @author Daniel Pinto
 */
public class MarketTest {

    Market mercado;

    /**
     * Constrói um mercado para a realização de testes.
     */
    @Before
    public void setUp() {
        System.out.println("Running set up");

        this.mercado = new Market("mercado1");
    }

    /**
     * Apaga um mercado após a realização de testes.
     */
    @After
    public void tearDown() {
        System.out.println("Running tear down");

        this.mercado = null;
    }

    /**
     * Testa o método removeClient da classe Market.
     */
    @Test
    public void testRemoveClient() {
        System.out.println("removeClient");

        mercado.addClient(20);
        mercado.addClient(10);

        mercado.removeClient();
    }

    /**
     * Testa o método printClients da classeMarket.
     */
    @Test
    public void testPrintClients() {
        System.out.println("printClients");

        mercado.addClient(10);

        mercado.printClients();
    }

    /**
     * Testa o método exportJSON da classe Market.
     */
    @Test
    public void testExportJSON() {
        System.out.println("export");

        assertTrue(this.mercado.exportJSON());
    }

    /**
     * Testa o método getClients da classe Market.
     */
    @Test
    public void testGetClients() {
        System.out.println("getClients");

        mercado.addClient(10);
        
        LinkedQueue expResult = null;
        LinkedQueue result = mercado.getClients();
        assertEquals(expResult, result);
    }

    /**
     * Testa o método addClient da classe Market.
     */
    @Test
    public void testAddClient() {
        System.out.println("addClient");

        mercado.addClient(10);
        assertEquals(10, this.mercado.getClients());
    }

    /**
     * Testa o método importJSON da classe Market.
     */
    @Test
    public void testImportJSON() {
        System.out.println("importJSON");

        Market instance = new Market();
        boolean expResult = false;
        boolean result = instance.importJSON();
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
