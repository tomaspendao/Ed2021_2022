/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import ADT.CompanyADT;
import ADT.UnorderedListADT;
import Collections.DoubleLinkedList.DoubleLinkedUnorderedList;
import Collections.LinkedList.GraphWeightList;
import Exceptions.ElementNotFoundException;
import Models.Nodes.ArestaWeight;
import com.google.gson.stream.JsonWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * Classe que define uma empresa.
 * 
 * @author Tomás Pendão
 */
public class Company extends Place implements CompanyADT {

    /**
     * Lista para guardar vendedores.
     */
    private UnorderedListADT<Seller> vendedores;
    /**
     * Lista para guardar os locais (Sede,Mercado,Armazém).
     */
    private UnorderedListADT<Place> locais;
    /**
     * Grafo Pesado que guarda os caminhos possíveis entre os locais.
     */
    private GraphWeightList<Place> caminhos;

    /**
     * Construtor para criar uma empresa.
     *
     * @param vendedores lista de vendedores a adicionar
     * @param locais lista de locais a adicionar
     * @param caminhos grafo pesado de caminhos a adicionar
     * @param name nome da empresa
     */
    public Company(UnorderedListADT<Seller> vendedores, UnorderedListADT<Place> locais, GraphWeightList<Place> caminhos, String name) {
        super(name, "Sede");
        this.vendedores = vendedores;
        this.locais = locais;
        this.caminhos = caminhos;
        this.locais.addToFront(this);
        this.caminhos.addVertex(this);
    }

    /**
     * Adicionar um vendedor a empresa.
     * 
     * @param vendedor vendedor a adicionar.
     */
    @Override
    public void addSeller(Seller vendedor) {
        if (this.checkIfSellerExists(vendedor.getId()) == null) {
            this.vendedores.addToRear(vendedor);
            this.removeNotValidMarketsFromSeller(vendedor.getId());
        }
    }

    /**
     * Remove os Mercados que não existem da lista de mercados que um 
     * determindo vendedor tem que visitar.
     * 
     * @param id identificador do vendedor em questão.
     * @return retorna o número de mercados inválidos removidos.
     */
    private int removeNotValidMarketsFromSeller(int id) {
        int count = 0;
        Seller seller = this.checkIfSellerExists(id);
        Iterator marketIter = seller.getMercados_a_visitar().iterator();
        while (marketIter.hasNext()) {
            String marketString = (String) marketIter.next();
            if (this.checkIfMarketExists(marketString) == null) {
                seller.getMercados_a_visitar().remove(marketString);
                count++;
            }
        }
        return count;
    }

    /**
     * Editar um vendedor já existente na empresa.
     * 
     * @param id identificador do vendedor a editar.
     * @param capacity capacidade máxima para um vendedos transportar.
     * @return true se a edição for concluida com sucesso, false se não.
     */
    @Override
    public boolean editSeller(int id, float capacity) {
        Seller oldSeller = this.checkIfSellerExists(id);
        if (oldSeller == null) {
            return false;
        } else if (capacity >= 0) {
            oldSeller.editCapacity(capacity);
        }

        return false;
    }

    /**
     * Adiciona um mercado que existe na empresa a visitar a um vendedor
     * em especifico.
     * 
     * @param id identificador do vendedor em questão.
     * @param market nome de um mercado existente para adicionar a lista de
     * mercados a visitar pelo vendedor.
     * @return true se conseguir adicionar o mercado, false se não o conseguir
     */
    public boolean addMarketToSeller(int id, String market) {
        Seller oldSeller = this.checkIfSellerExists(id);
        if (oldSeller == null) {
            return false;
        } else if (this.checkIfMarketExists(market) != null) {
            oldSeller.addMarket(market);
        }
        return false;
    }

    /**
     * Verificar se um determinado mercado passado como parametro existe.
     * 
     * @param name nome de um mercado para verificar
     * @return retorna o mercado se existir e null se não existir
     */
    private Market checkIfMarketExists(String name) {
        try {
            Place place = this.findPlaceByName(name);
            if (place.getType().equals("Mercado")) {
                Market market = (Market) place;
                return market;
            }
        } catch (ElementNotFoundException ex) {
            System.err.println("ELEMENT NOT FOUND");
            return null;
        }
        return null;
    }

    /**
     * Verificar se um determinado vendedor passado como parametro existe.
     * 
     * @param id identificador de um vendedor para verificar
     * @return retorna o vendedor se existir e null se não existir
     */
    private Seller checkIfSellerExists(int id) {
        Iterator sellerIter = this.vendedores.iterator();

        while (sellerIter.hasNext()) {
            Seller sellerValue = (Seller) sellerIter.next();
            if (sellerValue.getId() == id) {
                return sellerValue;
            }
        }
        return null;
    }

    /**
     * Adicionar um mercado a empresa.
     * 
     * @param market mercado a ser adicionado.
     */
    @Override
    public void addMarket(Market market) {
        if (checkIfMarketExists(market.getName()) == null) {
            this.locais.addToRear(market);
            this.caminhos.addVertex(market);
        }
    }

    /**
     * Editar um mercado já existente na empresa adicionando um cliente a esse 
     * mercado.
     * 
     * @param market nome do mercado(identificador) a ser adicionado.
     * @param demand cliente a ser adicionado.
     * @return true se a edição for concluida com sucesso, false se não.
     */
    @Override
    public boolean editMarket(String market, float demand) {
        Market mak = checkIfMarketExists(market);
        if (mak != null) {
            mak.addClient(demand);
        }
        return false;
    }

    /**
     * Adicionar um armazém a empresa.
     * 
     * @param warehouse armazém a ser adicionado.
     */
    @Override
    public void addWarehouse(Warehouse warehouse) {
        if (checkIfWarehouseExists(warehouse.getName()) == null) {
            this.locais.addToRear(warehouse);
            this.caminhos.addVertex(warehouse);
        }
    }

    /**
     * Editar um armazém já existente na empresa.
     * 
     * @param warehouse nome do armazém(identificador) a ser adicionado.
     * @param capacity valor a ser adicionado como nova capacidade máxima.
     * @param stock valor a ser adicionado como novo stock.
     * @return true se a edição for concluida com sucesso, false se não.
     */
    @Override
    public boolean editWarehouse(String warehouse, float capacity, float stock) {
        Warehouse ware = checkIfWarehouseExists(warehouse);
        if (ware != null) {
            ware.setAvailableCapacity(stock);
            ware.setCapacity(capacity);
        }
        return false;
    }

    /**
     * Verificar se um determinado aramzém passado como parametro existe.
     * 
     * @param name nome de um armazém para verificar
     * @return retorna o aramazém se existir e null se não existir
     */
    private Warehouse checkIfWarehouseExists(String name) {
        try {
            Place place = this.findPlaceByName(name);
            if (place.getType().equals("Armazém")) {
                Warehouse warehouse = (Warehouse) place;
                return warehouse;
            }
        } catch (ElementNotFoundException ex) {
            System.err.println("ELEMENT NOT FOUND");
            return null;
        }
        return null;
    }

    /**
     * Adiciona uma rota entre dois locais na empresa.
     * 
     * @param start nome do local(identificador) do inicio da rota.
     * @param dest nome do local(identificador) do fim da rota.
     * @param weight valor da distância do start ao dest.
     */
    @Override
    public void addRoute(String start, String dest, float weight) {
        this.caminhos.addEdge(findPlaceByName(start), findPlaceByName(dest), weight);
    }

    /**
     * Encontrar um local (Sede,armazém,mercado) atraves do nome do mesmo
     * 
     * @param name nome do local a encontrar
     * @return retorna o local encontrado, se não existir manda uma exceção
     * ElementNotFoundException
     */
    private Place findPlaceByName(String name) {
        Iterator iter = this.locais.iterator();
        while (iter.hasNext()) {
            Place value = (Place) iter.next();
            if (value.getName().equals(name)) {
                return value;
            }
        }
        throw new ElementNotFoundException(name);
    }

    /**
     * Editar uma rota na empresa (Se a rota não existir será criada uma).
     *
     * @param start nome do local(identificador) do inicio da rota.
     * @param dest nome do local(identificador) do fim da rota.
     * @param weight valor da distância do start ao dest.
     */
    @Override
    public void editRoute(String start, String dest, float weight) {
        this.addRoute(start, dest, weight);
    }
    
    /**
     * Imprime os vendedores da empresa.
     *
     * @return String com os vendedores da empresa.
     */
    @Override
    public String printSellers() {
        String str = "";

        Iterator iter = this.vendedores.iterator();
        while (iter.hasNext()) {
            Seller temp = (Seller) iter.next();
            str = str + temp.getNome() + ";";
        }

        return str;
    }

    /**
     * Imprime os mercados da empresa.
     *
     * @return String com os mercados da empresa.
     */
    @Override
    public String printMarket() {
        String str = "";

        Iterator iter = this.locais.iterator();
        while (iter.hasNext()) {
            Place temp = (Place) iter.next();
            if (temp.getType().equals("Mercado")) {
                str = str + temp.getName() + ";";
            }
        }

        return str;
    }

    /**
     * Imprime os armazéns da empresa.
     * 
     * @return String com os armazéns da empresa.
     */
    @Override
    public String printWarehouses() {
        String str = "";

        Iterator iter = this.locais.iterator();
        while (iter.hasNext()) {
            Place temp = (Place) iter.next();
            if (temp.getType().equals("Armazém")) {
                Warehouse newTemp = (Warehouse) temp;
                str = str + newTemp.getName() + ";";
            }
        }

        return str;
    }

    /**
     * Exportar para um ficheiro json a empresa.
     *
     * @return true se a edição for concluida com sucesso, false se não.
     */
    @Override
    public boolean export() {
        try (JsonWriter writer = new JsonWriter(new FileWriter("Company_" + this.getName() + ".json"))) {
            writer.setIndent("  ");
            writer.beginObject();

            //vendedores
            writer.name("vendedores");
            writer.beginArray();
            Iterator sellersIter = this.vendedores.iterator();
            while (sellersIter.hasNext()) {
                Seller value = (Seller) sellersIter.next();
                writer.beginObject();
                writer.name("id").value(value.getId());
                writer.name("nome").value(value.getNome());
                writer.name("capacidade").value(value.getCapacidade());
                writer.name("mercados_a_visitar");
                writer.beginArray();
                Iterator marketIter = value.getMercados_a_visitar().iterator();
                while (marketIter.hasNext()) {
                    String marketString = (String) marketIter.next();
                    writer.value(marketString);
                }
                writer.endArray();
                writer.endObject();
            }
            writer.endArray();
            //vendedores

            //locais
            writer.name("locais");
            writer.beginArray();
            Iterator locaisIter = this.locais.iterator();
            while (locaisIter.hasNext()) {
                Place value = (Place) locaisIter.next();
                writer.beginObject();
                writer.name("nome").value(value.getName());
                writer.name("tipo").value(value.getType());
                switch (value.getType()) {
                    case "Armazém":
                        Warehouse ware = (Warehouse) value;
                        writer.name("capacidade").value(ware.getMaxCapacity());
                        writer.name("stock").value(ware.getAvailableCapacity());
                        break;
                    case "Mercado":
                        Market merc = (Market) value;
                        writer.name("clientes");
                        writer.beginArray();
                        if (merc.getClients().isEmpty() == false) {
                            String[] clientIter = merc.getClients().toString().split(" ");
                            for (int i = 0; i < clientIter.length; i++) {
                                writer.value(Float.parseFloat(clientIter[i]));
                            }
                        }
                        writer.endArray();
                        break;
                    default:
                        break;
                }
                writer.endObject();
            }
            writer.endArray();
            //locais

            //caminhos
            writer.name("caminhos");
            writer.beginArray();
            DoubleLinkedUnorderedList<ArestaWeight>[] paths = this.caminhos.getAdjList();
            for (int i = 0; i < this.caminhos.size(); i++) {
                if (paths[i].size() > 0) {
                    System.out.println(paths[i].size());
                    Iterator pathsIter = paths[i].iterator();
                    while (pathsIter.hasNext()) {
                        writer.beginObject();
                        ArestaWeight value = (ArestaWeight) pathsIter.next();
                        writer.name("de").value(this.caminhos.getVertice(value.getStart()).getName());
                        writer.name("para").value(this.caminhos.getVertice(value.getTarget()).getName());
                        writer.name("distancia").value(value.getWeight());
                        writer.endObject();
                    }
                }
            }
            writer.endArray();
            //caminhos

            writer.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Fazer import de um JSON para a criação de uma empresa.
     *
     * @return retorna uma empresa gerada a partir de um JSON.
     */
    @Override
    public Company importCompany() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
