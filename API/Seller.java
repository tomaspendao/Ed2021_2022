 /**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package API;

import ADT.RouteADT;
import ADT.SellerADT;
import ADT.UnorderedListADT;
import Collections.DoubleLinkedList.DoubleLinkedUnorderedList;
import Collections.LinkedList.GraphWeightList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * Seller representa uma implementação de SellerADT.
 *
 * @author Daniel Pinto
 */
public class Seller implements SellerADT {

    /**
     * Lista de mercados associado a um determinado vendedor.
     */
    private DoubleLinkedUnorderedList<String> mercados_a_visitar;

    private static int idStatic = 0;
    
    /**
     * Representa a capacidad que um dado vendedor consegue transportar em kg
     * (kilogramas).
     */
    private float capacidade;

    /**
     * Representa a identificação de um vendedor.
     */
    private int id;
    

    /**
     * Representa o nome de um vendedor.
     */
    private String nome;

    /**
     * Construtor vazio
     */
    public Seller() {
    }

    /**
     * Construtor utilizado para criar uma instância de Seller com uma
     * capacidade, id, nome e stock definido.
     *
     * @param capacidade Capacidade que o vendedor consegue transportar.
     * @param id ID do vendedor.
     * @param nome Nome do vendedor.
     */
    public Seller(float capacidade, String nome) {
        this.capacidade = capacidade;
        this.id = this.idStatic++;
        this.nome = nome;
        this.mercados_a_visitar = new DoubleLinkedUnorderedList<>();
    }
    
    public Seller(float capacidade,int id, String nome) {
        this.capacidade = capacidade;
        this.id = id;
        this.nome = nome;
        this.mercados_a_visitar = new DoubleLinkedUnorderedList<>();
    }

    /**
     * Adiciona um mercado com um determinado nome a um vendedor.
     *
     * @param name Nome do mercado
     *
     * TODO verificar por repetidos
     */
    @Override
    public void addMarket(String name) {
        mercados_a_visitar.addToRear(name);
    }

    /**
     * Método utilizado para editar a capacidade que um vendedor pode
     * transportar. Apenas será alterada para um valor válido,superior a 0
     * (zero).
     *
     * @param newCapacity Nova capacidade que o vendedor consegue transportar.
     * @return true caso a nova capacidade seja definida com sucesso, false caso
     * contrário.
     */
    @Override
    public boolean editCapacity(float newCapacity) {
        if (newCapacity > 0) {
            this.capacidade = newCapacity;

            return true;
        }

        return false;
    }

    /**
     * Método utilizado para representar informações relativas a um vendedor.
     */
    @Override
    public void printSeller() {
        mercados_a_visitar.toString();

        System.out.println("Capacidade: " + this.getCapacidade());
        System.out.println("ID: " + this.getId());
        System.out.println("Nome: " + this.getNome());
    }

    /**
     * Exporta os dados de um vendedor para formato JSON.
     *
     * @return true caso seja possível exportar para formato JSON, false caso
     * contrário.
     */
    @Override
    public boolean exportJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(this);

        File file = new File("exportJSON/vendedor/Seller_" + this.getNome() + ".json");
        file.getParentFile().mkdirs();
        
        try ( FileWriter writer = new FileWriter(file)) {
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
    public boolean importJSON() {

        return true;
    }

    /**
     * Método utilizado para obter uma lista de mercados a visitar pelo
     * vendedor.
     *
     * @return Mercados a visitar pelo vendedor.
     */
    public DoubleLinkedUnorderedList getMercados_a_visitar() {
        return mercados_a_visitar;
    }

    /**
     * Método utilizado para obter a capacidade máxima que um vendedor consegue
     * transportar.
     *
     * @return Caapcidade máxima em kg (kilogramas) que um vendedor consegue
     * transportar.
     */
    public float getCapacidade() {
        return capacidade;
    }

    /**
     * Método utilizado para obter a identificação de um vendedor.
     *
     * @return Identificação de um vendedor.
     */
    public int getId() {
        return id;
    }

    /**
     * Método utilizado para obter o nome de um vendedor.
     *
     * @return Nome de um vendedor.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método utilizado para estabelecer a identificação de um vendedor.
     *
     * @param id Identificação de um vendedor.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método utilizado para estabelecer o nome de um vendedor.
     *
     * @param nome Nome de um vendedor.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}