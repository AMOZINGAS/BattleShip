/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaLogica.excepciones;

/**
 *
 * @author Beto_
 */
public class ServerLogicException extends Exception{

    public ServerLogicException() {
    }

    public ServerLogicException(String message) {
        super(message);
    }

    public ServerLogicException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
