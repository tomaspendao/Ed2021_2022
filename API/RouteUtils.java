/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package API;

import ADT.UnorderedListADT;
import Collections.LinkedList.GraphWeightList;
import Collections.LinkedList.LinkedQueue;
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
        Market mercado = new Market();

        //marcar o start como a sede
        rotaFinal.setStart(empresa.getLocais().first());

        //marcar o target 
        rotaFinal.setTarget(empresa.findPlaceByName(seller.getMercados_a_visitar().first().toString()));

        rotaFinal.getRota().addToFront(locais.first()); //adicionar a sede por exemplo

        while (!seller.getMercados_a_visitar().isEmpty()) {
            mercado = empresa.checkIfMarketExists((String) seller.getMercados_a_visitar().first());

            if (seller.getCapacidade() >= mercado.getTotalDemand()) {
                if (rotaFinal.getStock() >= mercado.getTotalDemand()) {
                    RouteUtils.aux(caminhos, seller, empresa, rotaFinal);
                } else {
                    float amountToReffil;

                    amountToReffil = mercado.getTotalDemand() - rotaFinal.getStock();

                    RouteUtils.refillRoute(caminhos, empresa.getWarehouses(), rotaFinal.getStart(), amountToReffil, rotaFinal);
                    rotaFinal.incrementAmountOfRefills();
                    RouteUtils.aux(caminhos, seller, empresa, rotaFinal);
                }
            } else {
                while (!mercado.getClients().isEmpty()) {
                    int count = 0;

                    if (rotaFinal.getStock() >= (float) mercado.getClients().first()) {
                        RouteUtils.aux2(caminhos, seller, empresa, rotaFinal);
                    } else if (seller.getCapacidade() >= (float) mercado.getClients().first()) {
                        float amountToReffil;

                        amountToReffil = (float) mercado.getClients().first() - rotaFinal.getStock();

                        RouteUtils.refillRoute(caminhos, empresa.getWarehouses(), rotaFinal.getStart(), amountToReffil, rotaFinal);
                        rotaFinal.incrementAmountOfRefills();
                        RouteUtils.aux2(caminhos, seller, empresa, rotaFinal);
                    } else {
                        mercado.getClients().enqueue(mercado.getClients().dequeue());

                        count++;
                        rotaFinal.incrementFailedClients();

                        if (count == mercado.getClients().size()) {
                            break; //passar para o próximo mercado
                        }
                    }
                }
            }
        }

        //um for para percorrer cada mercado que o vendedor vai ter que ir a (targets) FEITO
        //verificar se tem capacidade maxima para satisfazer um mercado todo de uma vez FEITO
        //se sim continua FEITO
        //se não vai ter que ver quantos clientes do mercado pode satisfazer e reabastecer connforme o necessario FEITO
        //verificar se tem stock suficiente para satisfazer um mercado     FEITO
        //se sim tem que ir ao mercado (gerando uma rota (generateshortestpath(start,target) ) ) e satisfazer os clientes FEITO
        //adicionar o peso dessa rota ao peso total da rota tipo assim: rotaFinal.addTotalDistance(caminhos.getTripWeight); FEITO
        //adicionar a rota tipo os places a rotaFinal tipo: rotaFinal.getRota().addToRear( FEITO
        //arranjar uma maneira de por todos os places que vem como iteratot) 
        //no fim vai ter que definir o start como o target FEITO 
        //e o target como o proximo mercado a visitar FEITO
        //se não vai ter que fazer um refillRoute FEITO
        //incrementar os refills FEITO 
        //adicionar o peso dessa rota ao peso total da rota tipo assim: rotaFinal.addTotalDistance(caminhos.getTripWeight); FEITO
        //adicionar a rota tipo os places a rotaFinal tipo: rotaFinal.getRota().addToRear( FEITO
        //arranjar uma maneira de por todos os places que vem como iteratot) 
        return rotaFinal;
    }

    //iterator vem com os valores em vertice ou seja place
    private static void addPlacesToRotaFromIterator(Iterator iterator, Route rota) {
        iterator.next();//tirar o primeiro que já vai estar adicionado

        while (iterator.hasNext()) {
            rota.getRota().addToRear((Place) iterator.next());
        }
    }

    public static void refillRoute(GraphWeightList<Place> caminhos, UnorderedListADT<Warehouse> armazens, Place start,
            float need, Route rotaFinal) {
        Warehouse warehouseToGo = null;
        float shortestTrip = 0;

        Iterator<Warehouse> iter = armazens.iterator();

        while (iter.hasNext()) {
            Warehouse warehouse = iter.next();

            if (warehouse.getAvailableCapacity() >= need) {
                Iterator<Place> iter2 = caminhos.iteratorShortestPath(start, warehouse);
                float temp = caminhos.getTripWeight(iter2);

                if (temp <= shortestTrip) {
                    shortestTrip = temp;
                    warehouseToGo = warehouse;
                }
            }
        }

        if (warehouseToGo == null) {
                while (iter.hasNext()) {
                    Warehouse warehouse = iter.next();
                    Iterator<Place> iter2 = caminhos.iteratorShortestPath(start, warehouse);
                    float temp = caminhos.getTripWeight(iter2);

                    if (temp <= shortestTrip) {
                        shortestTrip = temp;
                        warehouseToGo = warehouse;
                    }
                }

                Iterator<Place> iter3 = caminhos.iteratorShortestPath(start, warehouseToGo);

                rotaFinal.addTotalDistance(caminhos.getTripWeight(iter3));
                RouteUtils.addPlacesToRotaFromIterator(iter3, rotaFinal);
                rotaFinal.addStock(warehouseToGo.getAvailableCapacity());
                warehouseToGo.setAvailableCapacity(0);
                rotaFinal.setStart(warehouseToGo);
                RouteUtils.refillRoute(caminhos, armazens, rotaFinal.getStart(), need, rotaFinal);
        }

        Iterator<Place> iter3 = caminhos.iteratorShortestPath(start, warehouseToGo);

        rotaFinal.addTotalDistance(caminhos.getTripWeight(iter3));
        RouteUtils.addPlacesToRotaFromIterator(iter3, rotaFinal);
        warehouseToGo.setAvailableCapacity(warehouseToGo.getAvailableCapacity() - need);
        rotaFinal.addStock(need);
        rotaFinal.setStart(rotaFinal.getTarget());

        //criar uma rota do start para um armazem FEITO
        //ver quais os armazens que têm stock para responder a necessidade(need) de um armazém que um venndeor tem que respeitar (ciclo for) FEITO
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

    private static void aux(GraphWeightList<Place> caminhos, Seller seller, Company empresa, Route rotaFinal) {
        Iterator<Place> iter = caminhos.iteratorShortestPath(rotaFinal.getStart(), rotaFinal.getTarget());

        rotaFinal.addTotalDistance(caminhos.getTripWeight(iter));
        RouteUtils.addPlacesToRotaFromIterator(iter, rotaFinal);
        rotaFinal.setStart(rotaFinal.getTarget());
        seller.getMercados_a_visitar().removeFirst();

        if (!seller.getMercados_a_visitar().isEmpty()) {
            rotaFinal.setTarget(empresa.findPlaceByName(seller.getMercados_a_visitar().first().toString()));
        }
    }

    private static void aux2(GraphWeightList<Place> caminhos, Seller seller, Company empresa, Route rotaFinal) {
        Iterator<Place> iter = caminhos.iteratorShortestPath(rotaFinal.getStart(), rotaFinal.getTarget());

        rotaFinal.addTotalDistance(caminhos.getTripWeight(iter));
        RouteUtils.addPlacesToRotaFromIterator(iter, rotaFinal);

        String first = (String) seller.getMercados_a_visitar().first();
        Market market = empresa.checkIfMarketExists(first);

        market.removeClient();

        if (market.getClients().isEmpty()) {
            rotaFinal.setStart(rotaFinal.getTarget());
            seller.getMercados_a_visitar().removeFirst();
            rotaFinal.setTarget(empresa.findPlaceByName(seller.getMercados_a_visitar().first().toString()));
        }
    }

}
