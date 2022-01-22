/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package API;

import ADT.MarketADT;
import Collections.LinkedList.LinkedQueue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Market representa uma implementação de MarketADT.
 *
 * @author Daniel Pinto
 */
public class Market extends Place implements MarketADT {

    /**
     * Fila utilizada para armazenar clientes de um mercado.
     */
    private LinkedQueue<Float> clients;
    
    
    //adicionar metadata tipo entrou com que saiu com que ou uma cena assim no armazem tb

    /**
     * Construtor vazio que cria uma instância de mercado.
     */
    public Market() {
    }

    /**
     * Construtor utilizado para a criação de uma instância de mercado com um
     * determinado nome.
     *
     * @param name Nome do mercado.
     */
    public Market(String name) {
        super(name, "Mercado");
        this.clients = new LinkedQueue<>();
    }

    /**
     * Adiciona um cliente com uma dada procura ao fim da fila. Apenas deve ser
     * adicionado um cliente caso a procura que o mesmo crie seja superior a 0
     * (zero).
     *
     * @param demand Procura do cliente.
     */
    @Override
    public void addClient(float demand) {
        if (demand > 0) {
            clients.enqueue(demand);
        }
    }

    /**
     * Remove um cliente do inicio da fila quando a procura deste foi
     * satisfeita.
     *
     * @return true caso o cliente exista e seja removido, false caso contrário.
     */
    @Override
    public boolean removeClient() {
        return clients.dequeue() != null;
    }

    /**
     * Lista todos os clientes de um mercado.
     *
     * @return Lista de todos os clientes de um mercado.
     */
    @Override
    public String printClients() {
        return clients.toString();
    }

    /**
     * Exporta os dados de um mercado para formato JSON.
     *
     * @return true caso seja possível exportar para formato JSON, false caso
     * contrário.
     */
    @Override
    public boolean exportJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(this);

        File file = new File("exportJSON/mercado/Market_" + this.getName() + ".json");
        file.getParentFile().mkdirs();
        
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(json);

        return true;
    }

    /**
     * Importa os dados de um mercado de formato JSON.
     *
     * @return true caso seja possível importar de formato JSON, false caso
     * contrário.
     */
    @Override
    public boolean importJSON(String filepath) {
        JsonObject jsonObject = new JsonObject();
        
        try {
            jsonObject = new JsonParser().parse(new FileReader(filepath)).getAsJsonObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Seller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }

    /**
     * Método utilizado para obter uma lista dos clientes de um mercado.
     *
     * @return Lista dos clientes de um mercado.
     */
    public LinkedQueue getClients() {
        return clients;
    }

    public float getTotalDemand() {
        float res = 0;
        float[] temp = new float[this.clients.size()];

        System.out.println(this.clients.toString());
        
        for (int i = 0; i < temp.length; i++) {
            temp[i] = (float) clients.dequeue();
        }

        for (int i = 0; i < temp.length; i++) {
            res += temp[i];
        }

        for (int i = 0; i < temp.length; i++) {
            clients.enqueue(temp[i]);
        }
        System.out.println(this.clients.toString());

        return res;
    }
}
