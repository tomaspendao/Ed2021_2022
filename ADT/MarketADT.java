/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package ADT;

/**
 * MarketADT define uma interface com comportamentos de um mercado.
 *
 * @author Daniel Pinto
 */
public interface MarketADT {

    /**
     * Adiciona um cliente com uma dada procura. Apenas deve ser adicionado um
     * cliente caso a procura que o mesmo crie seja superior a 0 (zero).
     *
     * @param demand Procura do cliente.
     */
    public void addClient(float demand);

    /**
     * Remove um cliente quando a procura deste foi satisfeita.
     *
     * @return true caso o cliente exista e seja removido, false caso contrário.
     */
    public boolean removeClient();

    /**
     * Lista todos os clientes de um mercado.
     *
     * @return Lista de todos os clientes de um mercado.
     */
    public String printClients();

    /**
     * Exporta os dados de um mercado para formato JSON.
     *
     * @return true caso seja possível exportar para formato JSON, false caso
     * contrário.
     */
    public boolean exportJSON();

    /**
     * Importa os dados de um mercado de formato JSON.
     *
     * @return true caso seja possível importar de formato JSON, false caso
     * contrário.
     */
    public boolean importJSON();
}
