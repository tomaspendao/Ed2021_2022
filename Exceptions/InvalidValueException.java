/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package Exceptions;

/**
 * Classe para armazenar as várias exceções que podem ocorrer com o
 * funcionamento da API.
 *
 * @author Tomás Pendão
 */
public class InvalidValueException extends RuntimeException {

    /**
     * Sets up this exception with an appropriate message.
     *
     * @param value Valor que não é correto.
     */
    public InvalidValueException(String value) {
        super(value + " is not a valid value.");
    }
}
