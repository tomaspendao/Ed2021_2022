/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package ADT;

import API.Place;
import Collections.LinkedList.GraphWeightList;
import java.util.Iterator;

/**
 * RouteADT define uma interface com comportamentos de uma rota.
 *
 * @author Tomás Pendão
 */
public interface RouteADT {

    /**
     * Método utilizado para criar uma rota para um vendedor.
     *
     * @param caminhos Grafo com os locais.
     * @return rota com o caminho.
     */
    public Iterator generateRoute(GraphWeightList<Place> caminhos); //a,b,x abcdx s->a s->y->a     a->b a->x  |a|b|x| -> lista [a|b|x]

    /**
     * Metodo utilizado para reabastecer um vendedor.
     *
     * @param caminhos Grafo com os locais.
     * @param locais Locais da rota.
     * @return rota com o caminho.
     */
    public Iterator refillRoute(GraphWeightList<Place> caminhos, UnorderedListADT<Place> locais); //  stock>=soma  |a|b|c| ex: s-a -> 60  s-b 30 
}
