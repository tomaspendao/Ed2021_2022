/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package ADT;

import API.Seller;
import java.util.Iterator;

/**
 * RouteADT define uma interface com comportamentos de uma rota.
 *
 * @author Tomás Pendão
 */
public interface RouteADT {
    
    //getProduct > 0 
    //genrerateRoute(){}
    //
    /**
     * Método utilizado para criar uma rota para um vendedor.
     * 
     * @param vendedor Vendedor para qual vai ser gerada a rota de entregas.
     * @param marketList Lista de mercados que o vendedor tem de visitar.
     * @return rota com o caminho.
     */
    public Iterator generateRoute(Seller vendedor,UnorderedListADT<String> marketList); //a,b,x abcdx s->a s->y->a     a->b a->x  |a|b|x| -> lista [a|b|x]

    /**
     * Metodo utilizado para reabastecer um vendedor.
     * 
     * @param vendedor Vendedor que vai ter de reabastecer num armazém.
     * @return rota com o caminho.
     */
    public Iterator refillRoute(Seller vendedor); //  stock>=soma  |a|b|c| ex: s-a -> 60  s-b 30 

}
