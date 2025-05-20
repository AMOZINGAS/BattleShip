/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serversocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import mensajes.ManejadorMensajes;
import mensajes.Mensajes;

/**
 *
 * @author Beto_
 */
public class ServerListener implements Runnable{
    private BufferedReader reader;
    private ServerConnection connection;

    public ServerListener(BufferedReader reader, ServerConnection connection) {
        this.reader = reader;
        this.connection = connection;
    }

    @Override
    public void run() {
        String mensaje;
        try {
            while ((mensaje = reader.readLine()) != null) {
                System.out.println("Mensaje recibido: " + mensaje);
                Mensajes recibido = ManejadorMensajes.fromJson(mensaje);
                System.out.println("Servidor: Estoy recibiendo: " + recibido.getComando());
                ProcesadorMensajes.getInstance().procesarMensaje(recibido, connection); //<- cannot find symbol .getInstance
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.getServer().removeClient(connection);
            connection.close();
        }
    }

    public void close() throws IOException {
        reader.close();
    }
}
