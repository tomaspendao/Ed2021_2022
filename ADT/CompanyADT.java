/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package ADT;

import API.*;

/**
 *
 */
public interface CompanyADT {

    public void addSeller(Seller vendedor);

    public boolean editSeller();

    public void addMarket(Market market);

    public boolean editMarket();

    public void addWarehouse(Warehouse warehouse);

    public boolean editWarehouse();

    public void addRoute(String start, String dest, float weight);

    public boolean editRoute();

    public String printSellers();

    public String printMarket();

    public String printWarehouses();

    public boolean export();
}
