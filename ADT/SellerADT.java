/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package ADT;

/**
 * SellerADT define uma interface com comportamentos de um vendedor.
 *
 * @author Daniel Pinto
 */
public interface SellerADT {

    /**
     * Adiciona um mercado com um determinado nome a um vendedor.
     *
     * @param name Nome do mercado
     */
    public void addMarket(String name);

    /**
     * Método utilizado para editar a capacidade que um vendedor pode
     * transportar. Apenas será alterada para um valor válido,superior a 0
     * (zero).
     *
     * @param newCapacity Nova capacidade que o vendedor consegue transportar.
     * @return true caso a nova capacidade seja definida com sucesso, false caso
     * contrário.
     */
    public boolean editCapacity(float newCapacity);

    /**
     * Método utilizado para representar informações relativas a um vendedor.
     */
    public void printSeller();

    /**
     * Exporta os dados de um vendedor para formato JSON.
     *
     * @return true caso seja possível exportar para formato JSON, false caso
     * contrário.
     */
    public boolean export();
}