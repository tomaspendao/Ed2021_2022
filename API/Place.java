/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package API;

import Exceptions.InvalidValueException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe que irá armazenar informação e comportamentos de um local.
 *
 * @author Tomás Pendão
 */
public class Place {

    /**
     * Nome do local.
     */
    private String nome;

    /**
     * Tipo do local.
     */
    private String tipo;

    /**
     * Construtor para instanciar um local.
     *
     * @param name Nome do local.
     * @param type Tipo do local (Mercado, Sede, Armazém).
     */
    public Place(String name, String type) {
        this.nome = name;

        if (checkType(type)) {
            this.tipo = type;
        } else {
            throw new InvalidValueException(type);
        }
    }

    /**
     * Construtor vazio
     */
    public Place() {
    }

    /**
     * Retornar uma string que representa o nome do local.
     *
     * @return String com o nome do local.
     */
    public String getName() {
        return this.nome;
    }

    /**
     * Retornar uma string que representa o tipo do local.
     *
     * @return String com o tipo do local.
     */
    public String getType() {
        return this.tipo;
    }

    /**
     * Método utilizado para estabelecer o nome do local.
     *
     * @param nome Novo nome do local.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Verifixar se o tipo adicionado é válido de acordo com os tipo permitidos.
     *
     * @param type Tipo a verificar.
     * @return true se for um tipo válido, falso se for um tipo inválido.
     */
    private boolean checkType(String type) {
        return type.equals("Armazém") || type.equals("Mercado") || type.equals("Sede");
    }

    /**
     * Exporta os dados de um local para formato JSON.
     *
     * @return true caso seja possível exportar para formato JSON, false caso
     * contrário.
     */
    public boolean exportJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);
        File file;

        if (this.getType().equals("Mercado")) {
            file = new File("exportJSON/Local/Market_" + this.getName() + ".json");
        } else if (this.getType().equals("Armazém")) {
            file = new File("exportJSON/Local/Warehouse_" + this.getName() + ".json");
        } else {
            file = new File("exportJSON/Local/Place_" + this.getName() + ".json");
        }

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
     * Método para apresentar uma representação de um local.
     *
     * @return String com a epresentação de um local.
     */
    @Override
    public String toString() {
        return "Place{" + "nome=" + nome + '}';
    }
}