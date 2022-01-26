/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package API;

import Collections.DoubleLinkedList.DoubleLinkedUnorderedList;
import Collections.LinkedList.GraphWeightList;
import Models.Nodes.ArestaWeight;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe utilizada para realizar testes à classe Company.
 *
 * @author Tomás Pendão
 */
public class CompanyTest {

    Company empresa;
    DoubleLinkedUnorderedList<Seller> sellers = new DoubleLinkedUnorderedList<>();
    DoubleLinkedUnorderedList<Place> places = new DoubleLinkedUnorderedList<>();
    GraphWeightList<Place> map = new GraphWeightList<>();

    /**
     * Constrói uma empresa para a realização de testes.
     */
    public void setUp() {
        System.out.println("Running Set Up");
        this.empresa = new Company(sellers, places, map, "empresa");
    }

    /**
     * Apaga uma empresa após a realização de testes.
     */
    public void tearDown() {
        System.out.println("Running Tear Down");
        this.empresa = null;
    }

    /**
     * Testa o método addSeller da classe Company.
     */
    @Test
    public void testAddSeller() {
        this.setUp();
        System.out.println("addSeller");
        Seller seller1 = new Seller(10, 1, "Ricardo");
        this.empresa.addSeller(seller1);
        this.empresa.addSeller(seller1);
        this.empresa.addSeller(new Seller(10, 1, "José"));
        this.empresa.addSeller(new Seller(10, 2, "Mario"));
        assertEquals("Ricardo;Mario;", this.empresa.printSellers());
        this.tearDown();
    }

    /**
     * Testa o método editSeller da classe Company.
     */
    @Test
    public void testEditSeller() {
        this.setUp();
        System.out.println("editSeller");
        Seller seller1 = new Seller(10, 1, "Ricardo");
        this.empresa.addSeller(seller1);
        this.empresa.addSeller(seller1);
        this.empresa.addSeller(new Seller(10, 1, "José"));
        this.empresa.addSeller(new Seller(10, 2, "Mario"));

        assertEquals(10, this.sellers.first().getCapacidade(), 0);
        this.empresa.editSeller(1, 100);
        assertEquals(100, this.sellers.first().getCapacidade(), 0);
        this.tearDown();
    }

    /**
     * Testa o método addMarket da classe Company.
     */
    @Test
    public void testAddMarket() {
        this.setUp();
        System.out.println("addMarket");
        Market market1 = new Market("mercado1");
        this.empresa.addMarket(market1);
        this.empresa.addMarket(market1);
        assertEquals(2, this.places.size());//2 por causa da sede
        assertEquals("empresa", this.places.first().getName());
        assertEquals("Sede", this.places.first().getType());
        assertEquals("mercado1", this.places.last().getName());
        assertEquals("Mercado", this.places.last().getType());
        this.tearDown();
    }

    /**
     * Testa o método editMarket da classe Company.
     */
    @Test
    public void testEditMarket() {
        this.setUp();
        Market market1 = new Market("mercado1");
        this.empresa.addMarket(market1);
        System.out.println("editMarket");
        this.empresa.editMarket("mercado1", "mercado2");
        Market res = (Market) this.places.last();
        assertEquals("mercado2", res.getName());
        this.tearDown();
    }

    /**
     * Testa o método addWarehouse da classe Company.
     */
    @Test
    public void testAddWarehouse() {
        this.setUp();

        Warehouse warehouse = new Warehouse(200, 100, "Armazem1");

        System.out.println("addWarehouse");
        this.empresa.addWarehouse(warehouse);
        assertEquals(2, this.places.size(), 0);
        assertEquals("empresa", this.places.first().getName());
        assertEquals("Sede", this.places.first().getType());
        assertEquals("Armazem1", this.places.last().getName());
        assertEquals("Armazém", this.places.last().getType());
        this.tearDown();
    }

    /**
     * Testa o método editWarehouse da classe Company.
     */
    @Test
    public void testEditWarehouse() {
        this.setUp();
        Warehouse warehouse = new Warehouse(200, 100, "Armazem1");
        this.empresa.addWarehouse(warehouse);
        System.out.println("editWarehouse");
        this.empresa.editWarehouse("Armazem1", 150, 10);
        Warehouse res = (Warehouse) this.places.last();
        assertEquals(150, res.getMaxCapacity(), 0);
        assertEquals(10, res.getAvailableCapacity(), 0);
        this.tearDown();
    }

    /**
     * Testa o método addRoute da classe Company.
     */
    @Test
    public void testAddRoute() {
        this.setUp();
        Warehouse warehouse = new Warehouse(200, 100, "Armazem1");
        this.empresa.addWarehouse(warehouse);
        System.out.println("addRoute");
        this.empresa.addRoute("empresa", "Armazem1", 100);
        DoubleLinkedUnorderedList<ArestaWeight>[] path = this.map.getAdjList();
        assertEquals(0, path[0].first().getStart());
        assertEquals(1, path[0].first().getTarget());
        assertEquals(100, path[0].first().getWeight(), 0);
        this.tearDown();
    }

    /**
     * Testa o método editRoute da classe Company.
     */
    @Test
    public void testEditRoute() {
        this.setUp();
        Warehouse warehouse = new Warehouse(200, 100, "Armazem1");
        this.empresa.addWarehouse(warehouse);
        this.empresa.addRoute("empresa", "Armazem1", 100);
        DoubleLinkedUnorderedList<ArestaWeight>[] path = this.map.getAdjList();
        System.out.println("editRoute");

        this.empresa.editRoute("empresa", "Armazem1", 12);
        assertEquals(0, path[0].first().getStart());
        assertEquals(1, path[0].first().getTarget());
        assertEquals(12, path[0].first().getWeight(), 0);

        this.tearDown();
    }

    /**
     * Testa o método printSellers da classe Company.
     */
    @Test
    public void testPrintSellers() {
        this.setUp();
        System.out.println("printSellers");
        Seller seller1 = new Seller(10, 1, "Ricardo");
        this.empresa.addSeller(seller1);
        this.empresa.addSeller(seller1);
        this.empresa.addSeller(new Seller(10, 1, "José"));
        this.empresa.addSeller(new Seller(10, 2, "Mario"));
        assertEquals("Ricardo;Mario;", this.empresa.printSellers());
        this.tearDown();
    }

    /**
     * Testa o método printMarket da classe Company.
     */
    @Test
    public void testPrintMarket() {
        this.setUp();
        System.out.println("printMarket");
        Market market1 = new Market("mercado1");
        this.empresa.addMarket(market1);
        assertEquals("mercado1;", this.empresa.printMarket());
        this.tearDown();
    }

    /**
     * Testa o método printWarehouse da classe Company.
     */
    @Test
    public void testPrintWarehouses() {
        this.setUp();
        Warehouse warehouse = new Warehouse(200, 100, "Armazem1");
        this.empresa.addWarehouse(warehouse);
        System.out.println("printWarehouse");
        assertEquals("Armazem1;", this.empresa.printWarehouses());
        this.tearDown();
    }

    /**
     * Testa o método export da classe Company.
     */
    @Test
    public void testExport() {
        this.setUp();

        Market market1 = new Market("mercadinho1");
        market1.addClient(10);
        market1.addClient(40);
        market1.addClient(50);
        this.empresa.addMarket(market1);
        this.empresa.addMarket(new Market("mercado2"));
        this.empresa.addMarket(new Market("mercado2"));//não é para aparecer, done;
        this.empresa.addMarket(new Market("mercado1"));

        Seller seller = new Seller(100, 1, "Rui Maria");
        seller.addMarket("mercadinho1");
        seller.addMarket("mercadinho1");//não é para aparecer, done;
        seller.addMarket("mercado1");
        seller.addMarket("mercadofake");//não é para aparecer, done;
        this.empresa.addSeller(seller);
        this.empresa.addSeller(seller);//não é para aparecer, done;       

        this.empresa.addSeller(new Seller(10, 2, "José Carlos"));
        this.empresa.addWarehouse(new Warehouse(500, 200, "armazem1"));
        this.empresa.addWarehouse(new Warehouse(500, 200, "armazem1"));//não é para aparecer, done;
        this.empresa.addWarehouse(new Warehouse(502, 220, "armazem2"));
        System.out.println(this.empresa.printWarehouses());
        this.empresa.addRoute("empresa", "armazem1", 30);
        this.empresa.addRoute("empresa", "armazem1", 30);//não é para aparecer, done;
        this.empresa.addRoute("armazem1", "armazem2", 30);
        this.empresa.addRoute("armazem1", "armazem2", 50);
        DoubleLinkedUnorderedList<ArestaWeight>[] paths = this.map.getAdjList();
        System.out.println(paths[0].toString());
        System.err.println(this.places.isEmpty());

        System.out.println("export");
        this.empresa.export();

        this.tearDown();
    }

    /**
     * Testa o método addMarketToSeller da classe Company.
     */
    @Test
    public void testAddMarketToSeller() {
        this.setUp();

        Seller seller1 = new Seller(10, 1, "Ricardo");
        this.empresa.addSeller(seller1);
        this.empresa.addSeller(seller1);
        this.empresa.addSeller(new Seller(10, 1, "José"));
        this.empresa.addSeller(new Seller(10, 2, "Mario"));

        Market market1 = new Market("mercado1");
        this.empresa.addMarket(market1);

        System.out.println("addMarketToSeller");
        this.empresa.addMarketToSeller(1, "mercado1");
        assertEquals("mercado1", this.sellers.first().getMercados_a_visitar().first());
        this.tearDown();
    }

    /**
     * Testa o método import da classe Company.
     */
    @Test
    public void testImport() {
        this.setUp();
        Company empresa = Company.importCompany("Company_empresaCR.json");
        empresa.export();

        //System.out.println(this.empresa.getLocais().first().getName());
        //System.out.println(this.empresa.getLocais().first().getType());
        this.tearDown();
    }

}
