/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package API;

import ADT.UnorderedListADT;
import Collections.LinkedList.GraphWeightList;
import java.util.Iterator;

/**
 * Classe que servirá se suporte a Route.
 *
 * @author Tomás Pendão, Daniel Pinto
 */
public class RouteUtils {
    
    public static Route generateRoute(GraphWeightList<Place> caminhos, Seller seller, UnorderedListADT<Place> locais,
            Company empresa) {
        Route rotaFinal = new Route(seller);
        Seller vendedor = new Seller();
        Market mercado = new Market();

        //marcar o start como a sede
        rotaFinal.setStart(empresa.getLocais().first());

        //marcar o target 
        rotaFinal.setTarget(empresa.findPlaceByName(seller.getMercados_a_visitar().first().toString()));
        
        rotaFinal.getRota().addToFront(locais.first()); //adicionar a sede por exemplo

        for (int i = 0; i < vendedor.getMercados_a_visitar().size(); i++) {
            if (vendedor.getCapacidade() >= mercado.getTotalDemand()) {
                if (vendedor.getStock() >= mercado.getTotalDemand()) {
                    //generateshortestpath(start,target
                    //rotaFinal.addTotalDistance(caminhos.getTripWeight(iterator));
                    //rotaFinal.getRota().addToRear(locais);
                    rotaFinal.setStart(empresa.findPlaceByName(seller.getMercados_a_visitar().first().toString()));
                }
            } else {
                float amountToReffil = 0;
                
                amountToReffil = mercado.getTotalDemand() - vendedor.getStock();
                
                rotaFinal.setAmountOfRefills(rotaFinal.getAmountOfRefills() + 1);
            }
        }

        //um for para percorrer cada mercado que o vendedor vai ter que ir a (targets)
        //verificar se tem capacidade maxima para satisfazer um mercado todo de uma vez
        //se sim continua
        //se não vai ter que ver quantos clientes do mercado pode satisfazer e reabastecer connforme o necessario
        //verificar se tem stock suficiente para satisfazer um mercado
        //se sim tem que ir ao mercado (gerando uma rota (generateshortestpath(start,target) ) ) e satisfazer os clientes
        //adicionar o peso dessa rota ao peso total da rota tipo assim: rotaFinal.addTotalDistance(caminhos.getTripWeight);
        //adicionar a rota tipo os places a rotaFinal tipo: rotaFinal.getRota().addToRear(
        //arranjar uma maneira de por todos os places que vem como iteratot)
        //no fim vai ter que definir o start como o target
        //e o target como o proximo mercado a visitar
        //se não vai ter que fazer um refillRoute
        //incrementar os refills
        //adicionar o peso dessa rota ao peso total da rota tipo assim: rotaFinal.addTotalDistance(caminhos.getTripWeight);
        //adicionar a rota tipo os places a rotaFinal tipo: rotaFinal.getRota().addToRear(//arranjar uma maneira de por todos os places que vem como iteratot)
        return rotaFinal;
    }

    //iterator vem com os valores em vertice ou seja place
    private void addPlacesToRotaFromIterator(Iterator iterator, Route rota) {
        iterator.next();//tirar o primeiro que já vai estar adicionado
        while (iterator.hasNext()) {
            rota.getRota().addToRear((Place) iterator.next());
        }
    }
    
    public static void refillRoute(GraphWeightList<Place> caminhos, UnorderedListADT<Place> armazens, Place start, float need) {
        Warehouse warehouseToGo;
        float shortestTrip;

        //criar uma rota do start para um armazem
        //ver quais os armazens que têm stock para responder a necessidade(need) de um armazém que um venndeor tem que respeitar (ciclo for)
        //guardar em uma lista ou em uma stack os armazens que correpsondem as necessidades
        //percorrer essa stack/lista e remover armazem a armazem numa variavel temporaria
        //gerar uma rota atraves do generateshortestpath(start,tempArmazem), guardar o iterador
        //Fazer getTripWeight(iterador),guardar valor, e comparar com o shortestTrip
        //se for menor shortestTrip = getTripWeight(iterador) e warehouseToGo = tempArmazem
        //quanndo encontrar o mais perto
        //fazer addPlaceToRotaFromIterator com o iterador
        //se nenhum corresponder vai ao mais perto e e assim sucessivamente de modo a que consiga respitar a procura (se a stack/lista for vazia basicamente)
        //ou seja:
        //percorrer a lista de armazens e remover armazem a armazem numa variavel temporaria
        //gerar uma rota atraves do generateshortestpath(start,tempArmazem), guardar o iterador
        //Fazer getTripWeight(iterador),guardar valor, e comparar com o shortestTrip
        //se for menor shortestTrip = getTripWeight(iterador) e warehouseToGo = tempArmazem
        //quanndo encontrar o mais perto
        //sempre que fizer uma vez fazer addPlaceToRotaFromIterator com o iterador
        //fazer as vezes suficientes ate o stock for igual ao need basicamente
    }
}
