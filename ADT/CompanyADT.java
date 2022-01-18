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
 * @author tomaspendao
 */
public interface CompanyADT {

    /**
     * Adicionar um vendedor a empresa
     * 
     * @param vendedor vendedor a adicionar
     */
    public void addSeller(Seller vendedor);

    /**
     * Editar um vendedor já existente na empresa
     * 
     * @param id identificador do vendedor a editar
     * @param capacity capacidade máxima para um vendedos transportar
     * @return true se a edição for concluida com sucesso, false se não
     */
    public boolean editSeller(int id, float capacity);

    /**
     * Adicionar um mercado a empresa
     * 
     * @param market mercado a ser adicionado
     */
    public void addMarket(Market market);

    /**
     * Editar um mercado já existente na empresa adicionando um cliente a esse 
     * mercado
     * 
     * @param market nome do mercado(identificador) a ser adicionado
     * @param demand cliente a ser adicionado
     * @return true se a edição for concluida com sucesso, false se não
     */
    public boolean editMarket(String market, float demand);

    /**
     * Adicionar um armazém a empresa
     * 
     * @param warehouse armazém a ser adicionado
     */
    public void addWarehouse(Warehouse warehouse);

    /**
     * Editar um armazém já existente na empresa 
     * 
     * @param warehouse nome do armazém(identificador) a ser adicionado
     * @param capacity valor a ser adicionado como nova capacidade máxima
     * @param stock valor a ser adicionado como novo stock
     * @return true se a edição for concluida com sucesso, false se não
     */
    public boolean editWarehouse(String warehouse, float capacity, float stock);

    /**
     * Adiciona uma rota entre dois locais na empresa
     * 
     * @param start nome do local(identificador) do inicio da rota
     * @param dest nome do local(identificador) do fim da rota
     * @param weight valor da distância do start ao dest
     */
    public void addRoute(String start, String dest, float weight);

    /**
     * Editar uma rota na empresa (Se a rota não existir será criada uma)
     *
     * @param start nome do local(identificador) do inicio da rota
     * @param dest nome do local(identificador) do fim da rota
     * @param weight valor da distância do start ao dest
     */
    public void editRoute(String start, String dest, float weight);

    /**
     * Imprime os vendedores da empresa
     *
     * @return String com os vendedores da empresa
     */
    public String printSellers();

    /**
     * Imprime os mercados da empresa
     *
     * @return String com os mercados da empresa
     */
    public String printMarket();

    /**
     * Imprime os armazéns da empresa
     * 
     * @return String com os armazéns da empresa
     */
    public String printWarehouses();

    /**
     * Exportar para um ficheiro json a empresa
     *
     * @return true se a edição for concluida com sucesso, false se não
     */
    public boolean export();
    
    /**
     * Fazer import de um JSON para a criação de uma empresa
     *
     * @return retorna uma empresa gerada a partir de um JSON
     */
    public Company importCompany();
}
