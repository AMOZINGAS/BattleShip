package com.mycompany.serversocket;

import entity.Juego;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import mensajes.Mensajes;
import mensajes.ResCrearPartida;
import mensajes.ResUnirse;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class Server {
    private static Server instance;
    private ServerSocket serverSocket;
    private ExecutorService pool;
    private boolean running;
    private static final int PORT = 3500;
    private List<ServerConnection> clients;

    private Server() {
        pool = Executors.newFixedThreadPool(10);
        clients = new CopyOnWriteArrayList<>();
    }

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            running = true;
            System.out.println("Servidor iniciado en puerto " + PORT);

            while (running) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado: " + clientSocket.getInetAddress());

                ServerConnection connection = new ServerConnection(clientSocket, this);
                clients.add(connection);
                pool.execute(connection);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(Mensajes mensaje, ServerConnection sender) {
        for (ServerConnection client : clients) {
            if (mensaje instanceof ResUnirse) {
                if (client == sender) {
                    client.getSender().enviarMensaje(mensaje);
                }
            } else {
                client.getSender().enviarMensaje(mensaje);
            }
        }
    }

    public void removeClient(ServerConnection client) {
        clients.remove(client);
    }

    public static void main(String[] args) {
        Juego juego = Juego.getInstance();
        Controlador controlador = Controlador.getInstance();
        juego.addObserver(controlador); //<- controlador cannot be converter to Observer

        Server server = Server.getInstance();
        server.startServer();
    }
    
//    private static Server instance;
//    private ServerSocket serverSocket;
//    private ExecutorService pool;
//    private boolean running;
//    private static final int PORT = 3500;
//    private List<ManejadorCliente> clients;
//
//    private Server(){
//        System.out.println("no");
//        pool = Executors.newFixedThreadPool(2);
//        clients = new CopyOnWriteArrayList<>();
//    }
//
//    public static Server getInstance() {
//        if (instance == null) {
//            instance = new Server();
//        }
//        return instance;
//    }
//
//    public void startServer() {
//        try {
//            serverSocket = new ServerSocket(PORT);
//            running = true;
//            System.out.println("Servidor iniciado en puerto " + PORT);
//
//            while (running) {
//                Socket clientSocket = serverSocket.accept();
//                System.out.println("Nuevo cliente conectado: " + clientSocket.getInetAddress());
//                ManejadorCliente handler = new ManejadorCliente(clientSocket, this);
//                clients.add(handler);
//                pool.execute(handler);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void broadcastMessage(Mensajes mensaje, ManejadorCliente sender) {
//
//        if (mensaje instanceof ResUnirse) {
//            for (ManejadorCliente client : clients) {
//                if (client == sender) {
//                    client.sendMessage(mensaje);
//                }
//            }
//        } else if (mensaje instanceof ResCrearPartida) {
//            for (ManejadorCliente client : clients) {
//                client.sendMessage(mensaje);
//            }
//        }
//    }
//
//    public void addClient(ManejadorCliente client) {
//        clients.add(client);
//    }
//
//    public void removeClient(ManejadorCliente client) {
//        clients.remove(client);
//    }
//
//    public static void main(String[] args) {
//        
//        Juego juego = Juego.getInstance();
//        Controlador controlador = Controlador.getInstance();
//        juego.addObserver(controlador);
//
//        Server server = Server.getInstance();
//        server.startServer();
//    }

}
