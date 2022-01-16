/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Exceptions.InvalidValueException;

/**
 * Local
 *
 * @author Tomás Pendão
 */
abstract public class Place {

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

    private boolean checkType(String type) {
        return type.equals("Armazém") || type.equals("Mercado") || type.equals("Sede");
    }
}
