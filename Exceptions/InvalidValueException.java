/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author Tomás Pendão
 */
public class InvalidValueException extends RuntimeException {
    /**
     * Sets up this exception with an appropriate message.
     * @param element the name of the element
     */
    public InvalidValueException(String value)
    {
        super(value + " is not a valid value.");
    }
}
