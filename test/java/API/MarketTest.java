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
        this.mercado.addClient(10);
        
        //System.out.println(this.mercado.getTotalDemand());
        
        assertEquals(1, this.mercado.getClients().size());
        assertEquals((float)10.0, this.mercado.getClients().first());
    }

    /**
     * Testa o método removeClient da classe Market.
     */
    @Test
    public void testRemoveClient() {
        System.out.println("removeClient");

        this.mercado.addClient(20);
        this.mercado.addClient(40);
        this.mercado.removeClient();
        assertEquals((float)40, (float)this.mercado.getClients().first(),0);
    }

    /**
     * Testa o método printClients da classeMarket.
     */
    @Test
    public void testPrintClients() {
        System.out.println("printClients");

        this.mercado.addClient(20);
        assertEquals("20.0 ", this.mercado.printClients());
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
        this.mercado.addClient(20);
        assertEquals(1, this.mercado.getClients().size());
    }

    /**
     * Testa o método addClient da classe Market.
     */
    @Test
    public void testAddClient() {
        System.out.println("addClient");

        this.mercado.addClient(20);
        assertEquals(20, (float)this.mercado.getClients().first(),0);
    }

    /**
     * Testa o método importJSON da classe Market.
     */
    @Test
    public void testImportJSON() {
        System.out.println("importJSON");

        
        assertTrue(this.mercado.exportJSON());
    }
    
    /**
     * Testa o método getTotalDemand da classe Market.
     */
    @Test
    public void testGetTotalDemand() {
        System.out.println("setNome");
        this.mercado.addClient(10);
        this.mercado.addClient(20);
        this.mercado.addClient(70);
        assertEquals(100.0, this.mercado.getTotalDemand(),0);
    }
}