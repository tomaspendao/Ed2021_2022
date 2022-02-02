/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package API;

import ADT.MarketADT;
import Collections.LinkedList.LinkedQueue;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
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
     * Remove todos os clientes fila quando a procura destes foi satisfeita.
     */
    public void removeAllClients() {
        while (this.clients.isEmpty() == false) {
            this.removeClient();
        }
    }

    /**
     * Lista todos os clientes de um mercado.
     *
     */
    @Override
    public void printClients() {
        System.out.println(clients.toString());
    }

    /**
     * Exporta os dados de um mercado para formato JSON.
     *
     * @return true caso seja possível exportar para formato JSON, false caso
     * contrário.
     */
    @Override
    public boolean exportJSON() {
        File file = new File("exportJSON/mercado/Market_" + this.getName() + ".json");
        file.getParentFile().mkdirs();
        LinkedQueue<Float> temp = new LinkedQueue();

        while (!(this.getClients().isEmpty())) {
            temp.enqueue(this.getClients().dequeue());
        }

        try ( JsonWriter writer = new JsonWriter(new FileWriter(file))) {
            writer.setIndent(" ");
            writer.beginObject();
            writer.name("nome").value(this.getName());
            writer.name("tipo").value(this.getType());
            writer.name("clientes");
            writer.beginArray();

            while (!temp.isEmpty()) {
                float aux = temp.dequeue();

                this.getClients().enqueue(aux);

                writer.value(aux);
            }

            writer.endArray();

            writer.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Importa os dados de um mercado de formato JSON.
     *
     * @param filepath Caminho do ficheiro.
     * @return true caso seja possível importar de formato JSON, false caso
     * contrário.
     */
    public static Market importJSON(String filepath) {
        JsonObject jsonObject = new JsonObject();

        try {
            jsonObject = new JsonParser().parse(new FileReader(filepath)).getAsJsonObject(); //receber como argumento
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
        }

        Market marketRes = new Market(jsonObject.get("nome").getAsString());

        JsonArray clientesJSONArray = jsonObject.get("clientes").getAsJsonArray();

        for (int i = 0; i < clientesJSONArray.size(); i++) {
            marketRes.addClient(clientesJSONArray.get(i).getAsFloat());
        }

        return marketRes;

    }

    /**
     * Método utilizado para obter uma lista dos clientes de um mercado.
     *
     * @return Lista dos clientes de um mercado.
     */
    public LinkedQueue<Float> getClients() {
        return clients;
    }

    /**
     * Método utilizado para obter o total de procura num dado mercado.
     *
     * @return Procura num dado mercado.
     */
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
