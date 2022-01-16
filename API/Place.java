/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

/**
 * Local
 * 
 * @author Tomás Pendão
 */
public class Place {
    
    /**
     * Nome do local.
     */
    private String name;
    
    /**
     * Tipo do local.
     */
    private String type;

    /**
     * Construtor para instanciar um local
     * 
     * @param name nome do local
     * @param type tipo do local (Mercado, Sede, Armazém)
     */
    public Place(String name, String type) {
        this.name = name;
        this.type = type;
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
        return name;
    }
    
    /**
     * Retornar uma string que representa o tipo do local
     * 
     * @return uma String com o tipo do local
     */
    public String getType() {
        return type;
    }
}
