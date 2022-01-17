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

    public boolean editSeller(int id, float capacity);

    public void addMarket(Market market);

    public boolean editMarket(String market, float demand);

    public void addWarehouse(Warehouse warehouse);

    public boolean editWarehouse(String warehouse, float capacity, float stock);

    public void addRoute(String start, String dest, float weight);

    public void editRoute(String start, String dest, float weight);

    public String printSellers();

    public String printMarket();

    public String printWarehouses();

    public boolean export();
}
