/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package ADT;

import API.*;

/**
 * CompanyADT define uma interface com comportamentos de uma empresa.
 *
 * @author
 */
public interface CompanyADT {

    /**
     * Método utilizado para adicionar um vendedor a uma empresa.
     *
     * @param vendedor Vendedor a adicionar a uma empresa.
     */
    public void addSeller(Seller vendedor);

    /**
     * Método utilizado para editar um vendedor de uma empresa.
     *
     * @param id ID do vendedor a alterar.
     * @param capacity Nova capacidade máxima do vendedor.
     * @return
     */
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
