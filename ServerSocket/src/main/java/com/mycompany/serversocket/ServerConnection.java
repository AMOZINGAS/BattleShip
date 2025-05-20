/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serversocket;

import entity.Jugador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Beto_
 */
public class ServerConnection extends Thread{
    private Socket socket;
    private ServerListener listener;
    private ServerSender sender;
    private Server server;
    private Jugador jugador;

    public ServerConnection(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.jugador = new Jugador("N/A", "N/A");

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        this.sender = new ServerSender(writer);
        this.listener = new ServerListener(reader, this);
    }

    public ServerSender getSender() {
        return sender;
    }

    @Override
    public void run() {
        listener.run();
    }

    public void close() {
        try {
            listener.close();
            sender.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server getServer() {
        return server;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
