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
 * Classe utilizada para realizar testes à classe Place.
 *
 * @author Tomás Pendão
 */
public class PlaceTest {

    Place place1;
    Place place2;
    Place place3;

    /**
     * Constrói um local para a realização de testes.
     */
    @Before
    public void setUp() {
        System.out.println("Running set up");
        this.place1 = new Place("Local1", "Armazém") {
        };
        this.place2 = new Place("Local2", "Sede") {
        };
        this.place3 = new Place("Local3", "Mercado") {
        };

    }

    /**
     * Apaga um local após a realização de testes.
     */
    @After
    public void tearDown() {
        System.out.println("Running tear down");
        this.place1 = null;
        this.place2 = null;
        this.place3 = null;

    }

    /**
     * Testa o método getName da classe Place.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        assertEquals("Local1", this.place1.getName());
        assertEquals("Local2", this.place2.getName());
        assertEquals("Local3", this.place3.getName());

    }

    /**
     * Testa o método getType da classe Place.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        assertEquals("Armazém", this.place1.getType());
        assertEquals("Sede", this.place2.getType());
        assertEquals("Mercado", this.place3.getType());
    }
}
