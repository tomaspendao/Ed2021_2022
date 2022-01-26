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
 * Classe utilizada para realizar testes à classe Warehouse.
 *
 * @author Tomás Pendão
 */
public class WarehouseTest {

    Warehouse armazem;

    /**
     * Constrói um local para a realização de testes.
     */
    @Before
    public void setUp() {
        System.out.println("Running set up");
        this.armazem = new Warehouse(500, 300, "armazém1");
    }

    /**
     * Apaga um local após a realização de testes.
     */
    @After
    public void tearDown() {
        System.out.println("Running tear down");
        this.armazem = null;
    }

    /**
     * Testa o método setCapacity da classe Warehouse.
     */
    @Test
    public void testSetCapacity() {
        System.out.println("setCapacity");
        this.armazem.setCapacity(400);
        assertEquals(400, this.armazem.getMaxCapacity(), 0);
    }

    /**
     * Testa o método setAvailableCapacity da classe Warehouse.
     */
    @Test
    public void testSetAvailableCapacity() {
        System.out.println("setAvailableCapacity");
        this.armazem.setAvailableCapacity(500);
        assertEquals(500, this.armazem.getAvailableCapacity(), 0);
    }

    /**
     * Testa o método printWarehouse da classe Warehouse.
     */
    @Test
    public void testPrintWarehouse() {
        System.out.println("printWarehouse");
        assertEquals("Nome: armazém1;Tipo: Armazém;Capacidade: 500.0;Stock: 300.0", this.armazem.printWarehouse());
    }

    /**
     * Testa o método export da classe Warehouse.
     */
    @Test
    public void testExport() {
        System.out.println("export");
        assertTrue(this.armazem.export());
    }

    /**
     * Testa o método getMaxCapacity da classe Warehouse.
     */
    @Test
    public void testGetMaxCapacity() {
        System.out.println("getMaxCapacity");
        assertEquals(500, this.armazem.getMaxCapacity(), 0);
    }

    /**
     * Testa o método getAvailableCapacity da classe Warehouse.
     */
    @Test
    public void testGetAvailableCapacity() {
        System.out.println("getAvailableCapacity");
        assertEquals(300, this.armazem.getAvailableCapacity(), 0);
    }

    /**
     * Testa o método importJSON da classe Warehouse.
     */
    @Test
    public void testImportJSON() {
        System.out.println("importJSON");
        this.armazem = Warehouse.importJSON();

        assertEquals(500, this.armazem.getMaxCapacity(), 0);
        assertEquals(300, this.armazem.getAvailableCapacity(), 0);
        assertEquals("armazém1", this.armazem.getName());
        assertEquals("Armazém", this.armazem.getType());
    }

}
