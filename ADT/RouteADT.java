/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package ADT;

/**
 * RouteADT define uma interface com comportamentos de uma rota.
 *
 * @author Tomás Pendão
 */
public interface RouteADT {

    /**
     * Método utilizado para criar uma rota.
     */
    public void generateRoute();

    public void refillRoute();

    /**
     * Exporta os dados de um mercado para formato JSON.
     *
     * @return true caso seja possível exportar para formato JSON, false caso
     * contrário.
     */
    public boolean export();
}
