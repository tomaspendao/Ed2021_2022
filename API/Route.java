/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package API;

import ADT.UnorderedListADT;
import Collections.DoubleLinkedList.DoubleLinkedUnorderedList;

/**
 * Classe utilizada para armazenar dados e comportamentos de uma rota.
 *
 * @author Tomás Pendão
 */
public class Route {

    /**
     * Rota a percorrer.
     */
    private UnorderedListADT<Place> rota;

    /**
     * Número de vezes em que foi preciso reabastecer.
     */
    private int amountOfRefills;

    /**
     * Distância total.
     */
    private float totalDistance;

    /**
     * Vendedor que irá percorrer a rota.
     */
    private Seller vendedor;

    /**
     * Stock do vendedor.
     */
    private float stock;

    private int failedClients;

    //e capaz de nao ser necessario
    private Place start;
    private Place target;

    /**
     * Construtor que cria uma instância de Route. A rota, número de
     * reabastecimentos, distância total, stock e destino são inicializados a 0
     * (zero) ou null. O ponto de partida é sempre a sede.
     *
     * @param vendedor Vendedor que irá percorrer a rota.
     */
    public Route(Seller vendedor) {
        this.rota = new DoubleLinkedUnorderedList<>();
        this.amountOfRefills = 0;
        this.totalDistance = 0;
        this.vendedor = vendedor;
        this.stock = 0;
        this.start = null;//sede
        this.target = null;
        this.failedClients = 0;
    }

    /**
     * Método utilizado para obter uma rota.
     *
     * @return Rota.
     */
    public UnorderedListADT<Place> getRota() {
        return rota;
    }

    /**
     * Método utilizado para estabelecer uma rota.
     *
     * @param rota Rota a estabelecer.
     */
    public void setRota(UnorderedListADT<Place> rota) {
        this.rota = rota;
    }

    /**
     * Método utilizado para obter o número de reabastecimentos.
     *
     * @return Número de reabastecimentos.
     */
    public int getAmountOfRefills() {
        return amountOfRefills;
    }

    /**
     * Método utilizado para estabelecer o número de reabastecimentos.
     *
     * @param amountOfRefills Número de reabastecimentos.
     */
    public void setAmountOfRefills(int amountOfRefills) {
        this.amountOfRefills = amountOfRefills;
    }

    /**
     * Método utilizado para incrementar o número de reabastecimentos em uma
     * unidade.
     */
    public void incrementAmountOfRefills() {
        this.amountOfRefills++;
    }

    /**
     * Método utilizado para obter a distância total.
     *
     * @return Distância total.
     */
    public float getTotalDistance() {
        return totalDistance;
    }

    /**
     * Método utilizado para estabelecer a distância total.
     *
     * @param totalDistance Distância total
     */
    public void setTotalDistance(float totalDistance) {
        this.totalDistance = totalDistance;
    }

    /**
     * Método utilizado para adicionar uma dada distância à distância total.
     *
     * @param plusDistance Distância a adicionar.
     */
    public void addTotalDistance(float plusDistance) {
        this.totalDistance = this.totalDistance + plusDistance;
    }

    /**
     * Método utilizado para obter o vendedor.
     *
     * @return Vendedor a executar a rota.
     */
    public Seller getVendedor() {
        return vendedor;
    }

    /**
     * Método utilizado para estabelecer o vendedor que irá percorrer a rota.
     *
     * @param vendedor Vendedor que irá percorrer a rota.
     */
    public void setVendedor(Seller vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * Método utilizado para obter o stock da rota.
     *
     * @return Stock da rota.
     */
    public float getStock() {
        return stock;
    }

    /**
     * Método utilizado para estabelecer o stock de uma rota.
     *
     * @param stock Stock a estabelecer.
     */
    public void setStock(float stock) {
        this.stock = stock;
    }

    /**
     * Método utilizado para adicionar um dado stock ao stock já presente na
     * rota.
     *
     * @param plusStock Stock a adicionar à rota.
     */
    public void addStock(float plusStock) {
        this.stock = this.stock + plusStock;
    }

    /**
     * Método utilizado para remover um dado stock da rota.
     *
     * @param minusStock Stock a remover da rota.
     */
    public void removeStock(float minusStock) {
        this.stock = this.stock - minusStock;
    }

    /**
     * Método utilizado para obter o local inicial da rota.
     *
     * @return Local inicial da rota.
     */
    public Place getStart() {
        return start;
    }

    /**
     * Método utilizado para estabelecer o local inicial da rota.
     *
     * @param start Local inicial da rota.
     */
    public void setStart(Place start) {
        this.start = start;
    }

    /**
     * Método utilizado para obter o destino da rota.
     *
     * @return Destino da rota.
     */
    public Place getTarget() {
        return target;
    }

    /**
     * Método utilizado para estabelecer o destino da rota.
     *
     * @param target Destino da rota.
     */
    public void setTarget(Place target) {
        this.target = target;
    }

    /**
     * Método utilizado para
     *
     * @return
     */
    public int getFailedClients() {
        return failedClients;
    }

    /**
     * Método utilizado para
     *
     * @param failedClients
     */
    public void setFailedClients(int failedClients) {
        this.failedClients = failedClients;
    }

    /**
     * Método utilizado para incrementar
     */
    public void incrementFailedClients() {
        this.failedClients++;
    }

    /**
     * Método utilizado para apresentar uma representação de uma rota.
     *
     * @return String com uma representação de uma rota.
     */
    @Override
    public String toString() {
        return "Route{" + "rota=" + rota + ", amountOfRefills=" + amountOfRefills + ", totalDistance="
                + totalDistance + ", vendedor=" + vendedor + ", stock=" + stock + ", failedClients=" + failedClients
                + ", start=" + start + ", target=" + target + '}';
    }
}
