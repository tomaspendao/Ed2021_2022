/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package API;

import Collections.LinkedList.LinkedQueue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

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
     * Testa o método addCilents da classe Market.
     */
    @Test
    public void testAddClients() {
        System.out.println("addClients");

        float demand = 0.0F;
        Market instance = new Market();
        instance.addClient(demand);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Testa o método removeClient da classe Market.
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
     * Testa o método printClients da classeMarket.
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

        Market instance = new Market();
        LinkedQueue expResult = null;
        LinkedQueue result = instance.getClients();
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Testa o método addClient da classe Market.
     */
    @Test
    public void testAddClient() {
        System.out.println("addClient");

        float demand = 0.0F;
        Market instance = new Market();
        instance.addClient(demand);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
