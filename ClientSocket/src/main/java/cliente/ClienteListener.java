/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import mensajes.ManejadorMensajes;
import mensajes.Mensajes;

/**
 * Escucha los mensajes al implementar el Runnable
 * Lo que hace que no se detenga su ejecución ya que 
 * esta activamente escuchando respuestas del servidor
 * @author Beto_
 */
public class ClienteListener implements Runnable{
    private final ClienteConnection connection;
    private ManejadorRespuesta manejadorRespuesta;
    private boolean running = true;

    public ClienteListener(ClienteConnection connection, ManejadorRespuesta manejadorRespuesta) {
        this.connection = connection;
        this.manejadorRespuesta = manejadorRespuesta;
    }
    
    /**
     * Escucha activa. Convierte el Json a un Mensaje
     * y lo manda al manejador para ser procesada
     * segun su mensaje
     */
    @Override
    public void run() {
        try {
            String message;
            while (running && (message = connection.getIn().readLine()) != null) {
                Mensajes mensaje = ManejadorMensajes.fromJson(message);
                System.out.println("CLIENTE: Estoy recibiendo: " + mensaje.getComando());
                manejadorRespuesta.handleResponse(mensaje);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    public void stop() {
        running = false;
    }
}
