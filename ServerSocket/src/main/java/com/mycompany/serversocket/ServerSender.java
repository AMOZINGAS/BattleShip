/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serversocket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import mensajes.ManejadorMensajes;
import mensajes.Mensajes;

/**
 *
 * @author Beto_
 */
public class ServerSender {
    private PrintWriter writer;

    public ServerSender(PrintWriter writer) {
        this.writer = writer;
    }

    public void enviarMensaje(Mensajes mensaje) {
        writer.println(ManejadorMensajes.toJson(mensaje));
    }

    public void close() {
        writer.close();
    }
}
