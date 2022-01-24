/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Exceptions.InvalidValueException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Local
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
     * Construtor para instanciar um local
     *
     * @param name nome do local
     * @param type tipo do local (Mercado, Sede, Armazém)
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
     * Retornar uma string que representa o nome do local
     *
     * @return uma String com o nome do local
     */
    public String getName() {
        return this.nome;
    }

    /**
     * Retornar uma string que representa o tipo do local
     *
     * @return uma String com o tipo do local
     */
    public String getType() {
        return this.tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Verifixar se o tipo adicionado é válido de acordo com os tipo permitidos
     * 
     * @param type tipo a verificar
     * @return true se for um tipo válido, falso se for um tipo inválido
     */
    private boolean checkType(String type) {
        return type.equals("Armazém") || type.equals("Mercado") || type.equals("Sede");
    }
    
    public boolean exportJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(this);
        
        File file;
        
        if(this.getType().equals("Mercado")){
            file = new File("exportJSON/Local/Market_" + this.getName() + ".json");
        } else if(this.getType().equals("Armazém")) {
            file = new File("exportJSON/Local/Warehouse_" + this.getName() + ".json");
        } else {
            file = new File("exportJSON/Local/Place_" + this.getName() + ".json");
        }

        
        
        file.getParentFile().mkdirs();
        
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(json);

        return true;
    }
}
