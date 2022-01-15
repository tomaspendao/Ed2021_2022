/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package ADT;

/**
 * 
 */
public interface CompanyADT {

    public boolean addSeller();

    public boolean editSeller();

    public boolean addMarket();

    public boolean editMarket();

    public boolean addWarehouse();

    public boolean editWarehouse();

    public boolean addRoute();

    public boolean editRoute();

    public String printSellers();

    public String printMarket();

    public String printWarehouse();
    
    public boolean export();
}
