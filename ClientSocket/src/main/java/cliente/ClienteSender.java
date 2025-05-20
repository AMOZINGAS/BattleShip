/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import java.io.PrintWriter;
import mensajes.ManejadorMensajes;
import mensajes.Mensajes;

/**
 * Envía mensajes al servidor a través de la
 * conexión del cliente
 * @author Beto_
 */
public class ClienteSender {
    private final ClienteConnection connection;

    public ClienteSender(ClienteConnection connection) {
        this.connection = connection;
    }

    public void sendMessage(Mensajes mensaje) {
        if (connection.isConnected()) {
            String json = ManejadorMensajes.toJson(mensaje);
            connection.getOut().println(json);
            System.out.println("Enviado: " + mensaje.getComando());
        }
    }
}
