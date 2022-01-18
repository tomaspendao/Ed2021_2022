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
 *
 * @author Tomás Pendão
 */
public class Company extends Place implements CompanyADT {

    private UnorderedListADT<Seller> vendedores;
    private UnorderedListADT<Place> locais;
    //UnorderedListADT<Warehouse> armazens;
    //UnorderedListADT<Market> mercados;
    private GraphWeightList<Place> caminhos;

    public Company(UnorderedListADT<Seller> vendedores, UnorderedListADT<Place> locais, GraphWeightList<Place> caminhos, String name) {
        super(name, "Sede");
        this.vendedores = vendedores;
        this.locais = locais;
        this.caminhos = caminhos;
        this.locais.addToFront(this);
        this.caminhos.addVertex(this);
    }

    public Company() {
    }

    @Override
    public void addSeller(Seller vendedor) {
        if (this.checkIfSellerExists(vendedor.getId()) == null) {
            this.vendedores.addToRear(vendedor);
            this.removeNotValidMarketsFromSeller(vendedor.getId());
        }
    }

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

    public boolean addMarketToSeller(int id, String market) {
        Seller oldSeller = this.checkIfSellerExists(id);
        if (oldSeller == null) {
            return false;
        } else if (this.checkIfMarketExists(market) != null) {
            oldSeller.addMarket(market);
        }
        return false;
    }

    
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

    @Override
    public void addMarket(Market market) {
        if (checkIfMarketExists(market.getName()) == null) {
            this.locais.addToRear(market);
            this.caminhos.addVertex(market);
        }
    }

    @Override
    public boolean editMarket(String market, float demand) {
        Market mak = checkIfMarketExists(market);
        if (mak != null) {
            mak.addClient(demand);
        }
        return false;
    }

    @Override
    public void addWarehouse(Warehouse warehouse) {
        if (checkIfWarehouseExists(warehouse.getName()) == null) {
            this.locais.addToRear(warehouse);
            this.caminhos.addVertex(warehouse);
        }
    }

    @Override
    public boolean editWarehouse(String warehouse, float capacity, float stock) {
        Warehouse ware = checkIfWarehouseExists(warehouse);
        if (ware != null) {
            ware.setAvailableCapacity(stock);
            ware.setCapacity(capacity);
        }
        return false;
    }

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

    @Override
    public void addRoute(String start, String dest, float weight) {
        this.caminhos.addEdge(findPlaceByName(start), findPlaceByName(dest), weight);
    }

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

    @Override
    public void editRoute(String start, String dest, float weight) {
        this.addRoute(start, dest, weight);
    }
    
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

    @Override
    public Company importCompany() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
