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
    private LinkedQueue clients;

    /**
     * Nome do mercado.
     */
    private String name;
    /**
     * Tipo de localização.
     */
    private final String type = "Mercado";

    /**
     * Construtor vazio que cria uma instância de mercado.
     */
    public Market() {
    }

    /**
     * Construtor utilizado para a criação de uma instância de mercado com um
     * determinado nome.
     *
     * @param name Nome do mercado.
     */
    public Market(String name) {
        this.name = name;
    }

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

    /**
     * Método utilizado para obter uma lista dos clientes de um mercado.
     *
     * @return Lista dos clientes de um mercado.
     */
    public LinkedQueue getClients() {
        return clients;
    }

    /**
     * Método utilizado para obter o nome de um mercado.
     *
     * @return Nome de um mercado.
     */
    public String getName() {
        return name;
    }

    /**
     * Método utilizado para obter o tipo de localização.
     *
     * @return Tipo de localização.
     */
    public String getType() {
        return type;
    }

    /**
     * Método utilizado para establecer o nome de um mercado.
     *
     * @param name Nome a estabelecer de um mercado.
     */
    public void setName(String name) {
        this.name = name;
    }

}
