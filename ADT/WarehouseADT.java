/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package ADT;

/**
 * WarehouseADT define uma interface com comportamentos de um aramzém.
 *
 * @author Tomás Pendão
 */
public interface WarehouseADT {

    /**
     * Definir uma capacidade máxima de mercadoria que um armazém pode ter.
     *
     * @param maxCapacity capacidade máxima
     */
    public void setCapacity(float maxCapacity);

    /**
     * Definir a quantidade atual de mercadoria disponivel que um armazém tem.
     *
     * @param capacity quantidade atual
     */
    public void setAvailableCapacity(float capacity);

    /**
     * Lista a informação relativa a um armazém.
     *
     * @return Lista um armazém.
     */
    public String printWarehouse();

    /**
     * Exporta os dados de um armazém para formato JSON.
     *
     * @return true caso seja possível exportar para formato JSON, false caso
     * contrário.
     */
    public boolean export();
}
