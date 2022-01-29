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

    public void setAmountOfRefills(int amountOfRefills) {
        this.amountOfRefills = amountOfRefills;
    }

    public void incrementAmountOfRefills() {
        this.amountOfRefills++;
    }

    public float getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(float totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void addTotalDistance(float plusDistance) {
        this.totalDistance = this.totalDistance + plusDistance;
    }

    public Seller getVendedor() {
        return vendedor;
    }

    public void setVendedor(Seller vendedor) {
        this.vendedor = vendedor;
    }

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }

    public void addStock(float plusStock) {
        this.stock = this.stock + plusStock;
    }

    public void removeStock(float minusStock) {
        this.stock = this.stock - minusStock;
    }

    public Place getStart() {
        return start;
    }

    public void setStart(Place start) {
        this.start = start;
    }

    public Place getTarget() {
        return target;
    }

    public void setTarget(Place target) {
        this.target = target;
    }

    public int getFailedClients() {
        return failedClients;
    }

    public void setFailedClients(int failedClients) {
        this.failedClients = failedClients;
    }

    public void incrementFailedClients() {
        this.failedClients++;
    }
}