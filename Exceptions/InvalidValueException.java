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
     * Lança uma exceção fom uma mensagem apropriada. Neste caso quando um dado
     * valor não é válido.
     *
     * @param value Valor que não é válido para uma determinada situação.
     */
    public InvalidValueException(String value) {
        super(value + " is not a valid value.");
    }
}
