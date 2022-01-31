/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package API;

import ADT.CompanyADT;
import ADT.UnorderedListADT;
import Collections.Array.ArrayUnorderedList;
import Collections.DoubleLinkedList.DoubleLinkedUnorderedList;
import Collections.LinkedList.GraphWeightList;
import Exceptions.ElementNotFoundException;
import Models.Nodes.ArestaWeight;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * Lista para guardar os locais (Sede, Mercado, Armazém).
     */
    private UnorderedListADT<Place> locais;

    private UnorderedListADT<Warehouse> armazens = new ArrayUnorderedList<>();//new
    private UnorderedListADT<Market> mercados = new ArrayUnorderedList<>();//new

    /**
     * Grafo Pesado que guarda os caminhos possíveis entre os locais.
     */
    private GraphWeightList<Place> caminhos;

    /**
     * Construtor para criar uma empresa.
     *
     * @param vendedores Lista de vendedores a adicionar
     * @param locais Lista de locais a adicionar
     * @param caminhos Grafo pesado de caminhos a adicionar
     * @param name Nome da empresa
     */
    public Company(UnorderedListADT<Seller> vendedores, UnorderedListADT<Place> locais, GraphWeightList<Place> caminhos,
            String name) {
        super(name, "Sede");
        this.vendedores = vendedores;
        this.locais = locais;
        this.caminhos = caminhos;
        this.locais.addToFront(this);
        this.caminhos.addVertex(this);
    }

    /**
     * Construtor para criar uma empresa com apenas um dado nome. Os restantes
     * dados serão inicializados como vazios.
     *
     * @param name Nome da empresa.
     */
    public Company(String name) {
        super(name, "Sede");
        this.vendedores = new DoubleLinkedUnorderedList<>();
        this.locais = new DoubleLinkedUnorderedList<>();
        this.armazens = new DoubleLinkedUnorderedList<>();//new
        this.mercados = new DoubleLinkedUnorderedList<>();//new
        this.caminhos = new GraphWeightList<>();
        this.locais.addToFront(this);
        this.caminhos.addVertex(this);
    }

    /**
     * Adicionar um vendedor a empresa.
     *
     * @param vendedor Vendedor a adicionar.
     */
    @Override
    public void addSeller(Seller vendedor) {
        if (this.checkIfSellerExists(vendedor.getId()) == null) {
            this.vendedores.addToRear(vendedor);
            this.removeNotValidMarketsFromSeller(vendedor.getId());
        } else {
            this.editSeller(vendedor.getId(), vendedor.getCapacidade());
        }
    }

    /**
     * Remove os Mercados que não existem da lista de mercados que um determindo
     * vendedor tem que visitar.
     *
     * @param id Identificador do vendedor em questão.
     * @return Retorna o número de mercados inválidos removidos.
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
     * @param id Identificador do vendedor a editar.
     * @param capacity Capacidade máxima para um vendedos transportar.
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
     * Adiciona um mercado que existe na empresa a visitar a um vendedor em
     * especifico.
     *
     * @param id Identificador do vendedor em questão.
     * @param market Nome de um mercado existente para adicionar a lista de
     * mercados a visitar pelo vendedor.
     * @return true se conseguir adicionar o mercado, false se não o conseguir.F
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
     * Verificar se um determinado mercado passado como parâmetro existe.
     *
     * @param name Nome de um mercado para verificar.
     * @return Retorna o mercado se existir e null se não existir.
     */
    protected Market checkIfMarketExists(String name) {
        try {
            Place place = this.findPlaceByName(name);

            if (place.getType().equals("Mercado")) {
                Market market = (Market) place;

                return market;
            }
        } catch (ElementNotFoundException ex) {
            //System.err.println("ELEMENT NOT FOUND");
        }

        return null;
    }

    /**
     * Verificar se um determinado vendedor passado como parametro existe.
     *
     * @param id Identificador de um vendedor para verificar.
     * @return Retorna o vendedor se existir e null se não existir.
     */
    protected Seller checkIfSellerExists(int id) {
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
     * @param market Mercado a ser adicionado.
     */
    @Override
    public void addMarket(Market market) {
        if (checkIfMarketExists(market.getName()) == null) {
            this.locais.addToRear(market);
            this.mercados.addToRear(market);//new
            this.caminhos.addVertex(market);
        } else if (market.getClients().isEmpty() != true) {
            this.editMarket(market.getName(), market.getName());
        }
    }

    /**
     * Editar um mercado já existente na empresa adicionando um cliente a esse
     * mercado.
     *
     * @param market Nome do mercado(identificador) a ser adicionado.
     * @param newName
     * @return true se a edição for concluida com sucesso, false se não.
     */
    public boolean editMarket(String market, String newName) {
        Market mak = checkIfMarketExists(market);

        if (mak != null) {
            mak.setNome(newName);
            //mak.addClient(demand);
        }

        return false;
    }

    /**
     * Adicionar um armazém a empresa.
     *
     * @param warehouse Armazém a ser adicionado.
     */
    @Override
    public void addWarehouse(Warehouse warehouse) {
        if (checkIfWarehouseExists(warehouse.getName()) == null) {
            this.locais.addToRear(warehouse);
            this.armazens.addToRear(warehouse);//new
            this.caminhos.addVertex(warehouse);
        } else {
            this.editWarehouse(warehouse.getName(), warehouse.getMaxCapacity(), warehouse.getAvailableCapacity());
        }
    }

    /**
     * Editar um armazém já existente na empresa.
     *
     * @param warehouse Nome do armazém(identificador) a ser adicionado.
     * @param capacity Valor a ser adicionado como nova capacidade máxima.
     * @param stock Valor a ser adicionado como novo stock.
     * @return true se a edição for concluida com sucesso, false se não.
     */
    @Override
    public boolean editWarehouse(String warehouse, float capacity, float stock) {
        Warehouse ware = checkIfWarehouseExists(warehouse);

        if (ware != null) {
            ware.setCapacity(capacity);
            ware.setAvailableCapacity(stock);
        }

        return false;
    }

    /**
     * Verificar se um determinado aramzém passado como parametro existe.
     *
     * @param name Nome de um armazém para verificar
     * @return Retorna o aramazém se existir e null se não existir
     */
    private Warehouse checkIfWarehouseExists(String name) {
        try {
            Place place = this.findPlaceByName(name);

            if (place.getType().equals("Armazém")) {
                Warehouse warehouse = (Warehouse) place;

                return warehouse;
            }
        } catch (ElementNotFoundException ex) {
            //System.err.println("ELEMENT NOT FOUND");
            return null;
        }

        return null;
    }

    /**
     * Adiciona uma rota entre dois locais na empresa.
     *
     * @param start Nome do local(identificador) do inicio da rota.
     * @param dest Nome do local(identificador) do fim da rota.
     * @param weight Valor da distância do start ao dest.
     */
    @Override
    public void addRoute(String start, String dest, float weight) {
        this.caminhos.addEdge(findPlaceByName(start), findPlaceByName(dest), weight);
    }

    /**
     * Encontrar um local (Sede,armazém,mercado) atraves do nome do mesmo
     *
     * @param name Nome do local a encontrar
     * @return Retorna o local encontrado, se não existir manda uma exceção
     * ElementNotFoundException
     */
    public Place findPlaceByName(String name) {
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
     * @param start Nome do local(identificador) do inicio da rota.
     * @param dest Nome do local(identificador) do fim da rota.
     * @param weight Valor da distância do start ao dest.
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

    public String printMarketByTotalDemand() {
        UnorderedListADT<Market> byTotalDemand;
        float max = 0;
        Iterator iter = this.getMarkets().iterator();

        while (iter.hasNext()) {
            Market temp = (Market) iter.next();

            if (temp.getTotalDemand() > 0) {

                max = temp.getTotalDemand();
            }
        }

        return null;
    }

    /**
     * Imprime os armazéns da empresa.
     *
     * @return String com os armazéns da empresa.
     */
    @Override
    public String printWarehouses() {
        String str = "";
        Iterator iter = this.armazens.iterator();

        while (iter.hasNext()) {
            Warehouse temp = (Warehouse) iter.next();

            //if (temp.getType().equals("Armazém")) {
            Warehouse newTemp = (Warehouse) temp;
            str = str + newTemp.getName() + ";";
            //}
        }

        return str;
    }

    public String printWarehousesByStock() {//crescenste
        UnorderedListADT<String> byStock = new DoubleLinkedUnorderedList<>();
        String str = "";
        while (byStock.size() < this.armazens.size()) {
            Iterator<Warehouse> iterator = this.armazens.iterator();
            float maxStock = -1;
            Warehouse bestWare = null;
            while (iterator.hasNext()) {
                Warehouse next = iterator.next();
                if (next.getAvailableCapacity() >= maxStock) {
                    if (!(byStock.contains(next.getName()))) {
                        bestWare = next;
                    }
                }
            }
            byStock.addToRear(bestWare.getName());
            str = bestWare.getName() + "\n" + str;
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
        File file = new File("exportJSON/empresa/Company_" + this.getName() + ".json");
        file.getParentFile().mkdirs();

        try ( JsonWriter writer = new JsonWriter(new FileWriter(file))) {
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
                    //System.out.println(paths[i].size());
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
     * @param filepath Nome do ficheiro com caminho se necessário para fazer o
     * import.
     * @return Retorna uma empresa gerada a partir de um JSON.
     */
    public static Company importCompany(String filepath) {
        UnorderedListADT<Seller> vendedoresRes = new DoubleLinkedUnorderedList<>();
        UnorderedListADT<Place> locaisRes = new DoubleLinkedUnorderedList<>();
        GraphWeightList<Place> caminhosRes = new GraphWeightList<>();
        JsonObject jsonObject = new JsonObject();

        try {
            jsonObject = new JsonParser().parse(new FileReader(filepath)).getAsJsonObject(); //receber como argumento
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Array de locais
        JsonArray locaisJSONArray = jsonObject.get("locais").getAsJsonArray();

        String nameDaEmpresa = "default";

        try {
            nameDaEmpresa = getSedeName(locaisJSONArray);
        } catch (ElementNotFoundException ex) {
            nameDaEmpresa = "EmpresaNameDefault";
        }

        Company res = new Company(vendedoresRes, locaisRes, caminhosRes, nameDaEmpresa);

        for (int i = 0; i < locaisJSONArray.size(); i++) {
            //um local
            switch (locaisJSONArray.get(i).getAsJsonObject().get("tipo").getAsString()) {
                case "Mercado":
                    Market local1 = new Market(locaisJSONArray.get(i).getAsJsonObject().get("nome").getAsString());
                    JsonArray arrayDeDemands = locaisJSONArray.get(i).getAsJsonObject().get("clientes").getAsJsonArray();

                    for (int j = 0; j < arrayDeDemands.size(); j++) {
                        local1.addClient(arrayDeDemands.get(j).getAsFloat());
                    }

                    res.addMarket(local1);
                    break;
                case "Armazém":
                    Warehouse local2 = new Warehouse(locaisJSONArray.get(i).getAsJsonObject().get("capacidade").getAsFloat(),
                            locaisJSONArray.get(i).getAsJsonObject().get("stock").getAsFloat(),
                            locaisJSONArray.get(i).getAsJsonObject().get("nome").getAsString());
                    res.addWarehouse(local2);
                    break;
                default:
                    System.err.println("Local " + locaisJSONArray.get(i).getAsJsonObject().get("nome").getAsString()
                            + " invalido");
                    break;
                //throw new AssertionError();
            }
        }

        //Array de vendedores
        JsonArray vendedoresJSONArray = jsonObject.get("vendedores").getAsJsonArray();
        //iterar vendedores
        for (int i = 0; i < vendedoresJSONArray.size(); i++) {
            //um vendedor
            JsonObject vendedorJSONObject = (JsonObject) vendedoresJSONArray.get(i);
            Seller seller = new Seller(vendedorJSONObject.get("capacidade").getAsFloat(), vendedorJSONObject.get("id").getAsInt(), vendedorJSONObject.get("nome").getAsString());

            //Array de mercados a visitar
            JsonArray mercados_VisitarJSONArray = vendedorJSONObject.get("mercados_a_visitar").getAsJsonArray();
            for (int j = 0; j < mercados_VisitarJSONArray.size(); j++) {
                seller.addMarket(mercados_VisitarJSONArray.get(j).getAsString());
            }

            res.addSeller(seller);
        }

        //Array de caminhos
        JsonArray caminhosJSONArray = jsonObject.get("caminhos").getAsJsonArray();

        for (int i = 0; i < caminhosJSONArray.size(); i++) {
            //um caminho
            JsonObject path = caminhosJSONArray.get(i).getAsJsonObject();
            res.addRoute(path.get("de").getAsString(), path.get("para").getAsString(), path.get("distancia").getAsFloat());
        }

        System.out.println("-----Import Concluido-----");

        return res;
    }

    /**
     * Obter o nome da sede/empresa a partir de um jsonArray, tem como objectivo
     * fazer suporte ao metodo importCompany.
     *
     * @param array JsonArray que vai percorrer até encontrar um local com tipo
     * sede.
     * @return Retorna a string do nome da empresa, se não encontrar vai retorna
     * uma exceção.
     */
    private static String getSedeName(JsonArray array) {
        String str = "";

        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getAsJsonObject().get("tipo").getAsString().equals("Sede")) {
                str = array.get(i).getAsJsonObject().get("nome").getAsString();

                return str;
            }
        }

        throw new ElementNotFoundException("Sede");
    }

    /**
     * Método utilizado para obter uma lista de vendedores.
     *
     * @return Lista de vendedores.
     */
    public UnorderedListADT<Seller> getVendedores() {
        return vendedores;
    }

    /**
     * Método utilizado para obter uma lista de locais.
     *
     * @return Lista de locais.
     */
    public UnorderedListADT<Place> getLocais() {
        return locais;
    }

    /**
     * Método utilizado para obter uma lista dos caminhos.
     *
     * @return Lista dos caminhos.
     */
    public GraphWeightList<Place> getCaminhos() {
        return caminhos;
    }

    /**
     * Método utilizado para obter uma lista de armazéns.
     *
     * @return Lista de armazéns.
     */
    public UnorderedListADT<Warehouse> getWarehouses() {
        return this.armazens;
    }

    /**
     * Método utilizado para obter uma lista de mercados.
     *
     * @return Lista de mercados.
     */
    public UnorderedListADT<Market> getMarkets() {
        return this.mercados;
    }
}
