/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package temp;

import ADT.MarketADT;
import Collections.LinkedList.LinkedQueue;


/**
 * Market representa uma implementação de MarketADT.
 *
 * @author Daniel Pinto
 */
public class Market implements MarketADT {

    /**
     * Fila utilizada para armazenar clientes de um mercado.
     */
    LinkedQueue clients;

    /**
     * Adiciona um cliente com uma dada procura ao fim da fila. Apenas deve ser
     * adicionado um cliente caso a procura que o mesmo crie seja superior a 0
     * (zero).
     *
     * @param demand Procura do cliente.
     */
    @Override
    public void addClients(float demand) {
        if (demand > 0) {
            clients.enqueue(demand);
        }
    }

    /**
     * Remove um cliente do inicio da fila quando a procura deste foi
     * satisfeita.
     *
     * @return true caso o cliente exista e seja removido, false caso contrário.
     */
    @Override
    public boolean removeClient() {
        return clients.dequeue() != null;
    }

    /**
     * Lista todos os clientes de um mercado.
     *
     * @return Lista de todos os clientes de um mercado.
     */
    @Override
    public String printClients() {
        return clients.toString();
    }

    /**
     * Exporta os dados de um mercado para formato JSON.
     *
     * @return true caso seja possível exportar para formato JSON, false caso
     * contrário.
     */
    @Override
    public boolean export() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
