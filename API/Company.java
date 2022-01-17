/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import ADT.CompanyADT;
import ADT.UnorderedListADT;
import Collections.Array.ArrayUnorderedList;
import Collections.DoubleLinkedList.DoubleLinkedUnorderedList;
import Collections.LinkedList.GraphWeightList;
import Exceptions.ElementNotFoundException;
import Models.Nodes.ArestaWeight;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        super(name,"Sede");
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
        this.vendedores.addToRear(vendedor);
    }

    @Override
    public boolean editSeller() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addMarket(Market market) {
        this.locais.addToRear(market);
        this.caminhos.addVertex(market);
    }

    @Override
    public boolean editMarket() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addWarehouse(Warehouse warehouse) {
        this.locais.addToRear(warehouse);
        this.caminhos.addVertex(warehouse);
    }

    @Override
    public boolean editWarehouse() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new ElementNotFoundException("place");
    }

    @Override
    public boolean editRoute() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String printSellers() {
        String str = "";

        Iterator iter = this.vendedores.iterator();
        while (iter.hasNext()) {
            Seller temp = (Seller) iter.next();
            str = str + temp;
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
                str = str + temp;
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
                str = str + newTemp.printWarehouse();
            }
        }

        return str;
    }

    @Override
    public boolean export() {
        try (JsonWriter writer = new JsonWriter(new FileWriter("test.json"))) {
            writer.setIndent("  ");
            writer.beginObject();

            //vendedores
            writer.name("vendedores");
            writer.beginArray();
            Iterator sellersIter = this.vendedores.iterator();
            while (sellersIter.hasNext()) {
                Seller value = (Seller) sellersIter.next();
                writer.beginObject();
                //writer.name("id").value(value.getId());
                //writer.name("nome").value(value.getName());
                //writer.name("capacidade").value(value.getCapacity());
                writer.name("mercados_a_visitar");
                writer.beginArray();
                //imprimir os markets
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
                        Iterator clientIter = this.vendedores.iterator();
                        while (clientIter.hasNext()) {
                            float need = (float) clientIter.next();
                            writer.value(need);
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
}
