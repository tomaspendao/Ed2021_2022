/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package API;

import Exceptions.InvalidValueException;

/**
 * Classe abstrata que irá armazenar informação sobre um local.
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
     * Construtor para instanciar um local com um nome e um tipo. Um local é
     * apenas criado se cumprir com os requesito do tipo de local.
     *
     * @param name Nome do local.
     * @param type Tipo do local (Mercado, Sede ou Armazém).
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
     * Construtor vazio.
     */
    public Place() {
    }

    /**
     * Método utilizado para retornar uma string que representa o nome do local.
     *
     * @return Uma string com o nome do local.
     */
    public String getName() {
        return this.nome;
    }

    /**
     * Método utilizado para retornar uma string que representa o tipo do local.
     *
     * @return Uma string com o tipo do local.
     */
    public String getType() {
        return this.tipo;
    }

    /**
     * Método utilizado para verificar se o tipo adicionado é válido de acordo
     * com os tipo permitidos.
     *
     * @param type Tipo a verificar.
     * @return true se for um tipo válido, falso se for um tipo inválido.
     */
    private boolean checkType(String type) {
        return type.equals("Armazém") || type.equals("Mercado") || type.equals("Sede");
    }
}
