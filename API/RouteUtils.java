/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package API;

import ADT.UnorderedListADT;
import Collections.LinkedList.GraphWeightList;
import Exceptions.ElementNotFoundException;
import java.util.Iterator;

/**
 * Classe que servirá se suporte a Route.
 *
 * @author Tomás Pendão, Daniel Pinto
 */
public class RouteUtils {

    /**
     * Método utilizado para gerar a rota para um vendedor.
     *
     * @param caminhos Grafo com os locais.
     * @param seller Vendedor que irá percorrer a rota.
     * @param locais Locais associaddos a uma empresa.
     * @param empresa Empresa associada à rota.
     * @return Uma rota percorrida pelo vendedor.
     */
    public static Route generateRoute(GraphWeightList<Place> caminhos, Seller seller, UnorderedListADT<Place> locais,
            Company empresa) {
        if (!caminhos.isConnected()) {
            System.err.println("Graph is not connected");
            return null;
        }

        Route rotaFinal = new Route(seller);
        Market mercado = new Market();

        //marcar o start como a sede
        rotaFinal.setStart(empresa.getLocais().first());
        rotaFinal.setTarget(empresa.findPlaceByName(seller.getMercados_a_visitar().first().toString()));
        rotaFinal.getRota().addToFront(locais.first()); //adicionar a sede por exemplo

        while (!seller.getMercados_a_visitar().isEmpty()) {
            mercado = empresa.checkIfMarketExists((String) seller.getMercados_a_visitar().first());

            System.out.println("\tPara o mercado " + mercado.getName());
            System.out.println("demand :" + mercado.getTotalDemand());

            if (mercado.getTotalDemand() > 0) {
                if (seller.getCapacidade() >= mercado.getTotalDemand()) {
                    System.out.println("\t\tTem capacidade para a demand toda");

                    if (rotaFinal.getStock() >= mercado.getTotalDemand()) {
                        System.out.println("\t\t\tTem stock para a demand toda");
                        RouteUtils.aux(caminhos, seller, empresa, rotaFinal);
                    } else {
                        float amountToReffil;
                        System.out.println("\t\t\tNão tem stock para a demand toda");
                        amountToReffil = mercado.getTotalDemand() - rotaFinal.getStock();

                        RouteUtils.refillRoute(caminhos, empresa.getWarehouses(), rotaFinal.getStart(), amountToReffil,
                                rotaFinal);
                        rotaFinal.incrementAmountOfRefills();
                        RouteUtils.aux(caminhos, seller, empresa, rotaFinal);
                    }
                } else {
                    System.out.println("\t\tNão tem capacidade para a demand toda");
                    int count = 0;

                    while (!mercado.getClients().isEmpty()) {
                        if (rotaFinal.getStock() >= (float) mercado.getClients().first()) {
                            System.out.println("\t\t\tTem stock");
                            RouteUtils.aux2(caminhos, seller, empresa, rotaFinal);
                        } else if (seller.getCapacidade() >= (float) mercado.getClients().first()) {
                            float amountToReffil;
                            System.out.println("\t\t\tNao tem stock mas tem capacidade para o cliente");
                            amountToReffil = (float) mercado.getClients().first() - rotaFinal.getStock();

                            RouteUtils.refillRoute(caminhos, empresa.getWarehouses(), rotaFinal.getStart(),
                                    amountToReffil, rotaFinal);
                            rotaFinal.incrementAmountOfRefills();
                            RouteUtils.aux2(caminhos, seller, empresa, rotaFinal);
                        } else {
                            mercado.getClients().enqueue(mercado.getClients().dequeue());
                            System.out.println("\t\t\tNao tem stock nem tem capacidade para o cliente");

                            count++;

                            rotaFinal.incrementFailedClients();

                            if (count >= mercado.getClients().size()) {
                                seller.getMercados_a_visitar().removeFirst();
                                break; //passar para o próximo mercado
                            }
                        }
                    }
                }
            } else {
                seller.getMercados_a_visitar().removeFirst();//passar para o próximo mercado
            }
        }

        return rotaFinal;
    }

    /**
     * Método utilizado para adicionar uma rota entre dois locais à rota total.
     *
     * @param iterator Iterator vem com os valores em vertice ou seja place
     * @param rota Rota entre dois locais.
     */
    private static void addPlacesToRotaFromIterator(Iterator iterator, Route rota) {
        iterator.next();//tirar o primeiro que já vai estar adicionado

        while (iterator.hasNext()) {
            rota.getRota().addToRear((Place) iterator.next());
        }
    }

    /**
     * Método utilizado para gerar uma rota para um vendedor reabastecer num
     * armazém.
     *
     * @param caminhos Grafo com os locais.
     * @param armazens Armazéns associados a uma empresa.
     * @param start Ponto de partida.
     * @param need Quantidade a reabastecer.
     * @param rotaFinal Rota à qual vai ser adicionada a rota gerada pelo
     * reabastecimento.
     */
    public static void refillRoute(GraphWeightList<Place> caminhos, UnorderedListADT<Warehouse> armazens, Place start,
            float need, Route rotaFinal) {
        try {
            Warehouse warehouseToGo = null;
            float shortestTrip = Float.MAX_VALUE;
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
            Iterator<Place> iter3Copy = caminhos.iteratorShortestPath(start, warehouseToGo);

            rotaFinal.addTotalDistance(caminhos.getTripWeight(iter3Copy));
            RouteUtils.addPlacesToRotaFromIterator(iter3, rotaFinal);
            warehouseToGo.setAvailableCapacity(warehouseToGo.getAvailableCapacity() - need);
            rotaFinal.addStock(need);
            rotaFinal.setStart(warehouseToGo);
        } catch (ElementNotFoundException ex) {
            System.err.println("Graph is not connected");
        }
    }

    /**
     * Método auxiliar para executar a rota gerada no generateRoute, no caso de
     * o vendedor ter capacidade para satisfazer todos os clientes de um
     * mercado.
     *
     * @param caminhos Grafo com os locais.
     * @param seller Vendedor associado à rota.
     * @param empresa Empresa associada à rota.
     * @param rotaFinal Rota que o vendedor terá que percorrer.
     */
    private static void aux(GraphWeightList<Place> caminhos, Seller seller, Company empresa, Route rotaFinal) {
        try {
            boolean flag = true;

            while (flag == true && seller.getMercados_a_visitar().isEmpty()) {
                if (!(seller.getMercados_a_visitar().isEmpty())
                        && empresa.checkIfMarketExists(seller.getMercados_a_visitar().first()).getTotalDemand() <= 0) {
                    seller.getMercados_a_visitar().removeFirst();
                } else {
                    flag = false;
                }
            }

            rotaFinal.setTarget(empresa.findPlaceByName(seller.getMercados_a_visitar().first().toString()));

            Iterator<Place> iter = caminhos.iteratorShortestPath(rotaFinal.getStart(), rotaFinal.getTarget());
            Iterator<Place> iterCopy = caminhos.iteratorShortestPath(rotaFinal.getStart(), rotaFinal.getTarget());

            rotaFinal.addTotalDistance(caminhos.getTripWeight(iter));
            RouteUtils.addPlacesToRotaFromIterator(iterCopy, rotaFinal);
            rotaFinal.setStart(rotaFinal.getTarget());

            String marketString = seller.getMercados_a_visitar().first();

            empresa.checkIfMarketExists(marketString).removeAllClients();
            seller.getMercados_a_visitar().removeFirst();

            if (!seller.getMercados_a_visitar().isEmpty()) {
                rotaFinal.setTarget(empresa.findPlaceByName(seller.getMercados_a_visitar().first().toString()));
            }
        } catch (ElementNotFoundException ex) {
            System.err.println("Graph is not connected");
        }
    }

    /**
     * Método auxiliar para executar a rota gerada no generateRoute, no caso de
     * o vendedor não ter capacidade para satisfazer todos os clientes de um
     * mercado.
     *
     * @param caminhos Grafo com os locais.
     * @param seller Vendedor associado à rota.
     * @param empresa Empresa associada à rota.
     * @param rotaFinal Rota que o vendedor terá que percorrer.
     */
    private static void aux2(GraphWeightList<Place> caminhos, Seller seller, Company empresa, Route rotaFinal) {
        try {
            boolean flag = true;

            while (flag == true && seller.getMercados_a_visitar().isEmpty()) {
                if (!(seller.getMercados_a_visitar().isEmpty())
                        && empresa.checkIfMarketExists(seller.getMercados_a_visitar().first()).getTotalDemand() <= 0) {
                    seller.getMercados_a_visitar().removeFirst();
                } else {
                    flag = false;
                }
            }
            try {
                rotaFinal.setTarget(empresa.findPlaceByName(seller.getMercados_a_visitar().first().toString()));

                Iterator<Place> iter = caminhos.iteratorShortestPath(rotaFinal.getStart(), rotaFinal.getTarget());
                Iterator<Place> iterCopy = caminhos.iteratorShortestPath(rotaFinal.getStart(), rotaFinal.getTarget());

                rotaFinal.addTotalDistance(caminhos.getTripWeight(iter));
                RouteUtils.addPlacesToRotaFromIterator(iterCopy, rotaFinal);

                String first = (String) seller.getMercados_a_visitar().first();
                Market market = empresa.checkIfMarketExists(first);

                market.removeClient();

                if (market.getClients().isEmpty()) {
                    rotaFinal.setStart(rotaFinal.getTarget());
                    seller.getMercados_a_visitar().removeFirst();
                    rotaFinal.setTarget(empresa.findPlaceByName(seller.getMercados_a_visitar().first().toString()));
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.err.println("No path found!");
            }
        } catch (ElementNotFoundException ex) {
            System.err.println("Graph is not connected");
        }
    }
}
