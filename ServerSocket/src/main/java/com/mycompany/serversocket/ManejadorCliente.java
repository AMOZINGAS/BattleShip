package com.mycompany.serversocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import mensajes.ManejadorMensajes;
import mensajes.Mensajes;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class ManejadorCliente { //implements Runnable {

//    private Socket clientSocket;
//    private BufferedReader in;
//    private PrintWriter out;
//    private Server server;
//    private boolean running;
//    private ProcesadorMensajes procesadorMensaje;
//
//    public ManejadorCliente(Socket socket, Server server) {
//        this.clientSocket = socket;
//        this.server = server;
//        this.running = true;
//        this.procesadorMensaje = new ProcesadorMensajes(this);
//        try {
//            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            out = new PrintWriter(clientSocket.getOutputStream(), true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void run() {
//        try {
//            while (running && !clientSocket.isClosed()) {
//                String inputLine = in.readLine();
//                if (inputLine == null) {
//                    break;
//                }
//                System.out.println(inputLine);
//                procesadorMensaje.procesarMensaje(inputLine);
//            }
//        } catch (IOException e) {
//            System.out.println("Error: Cliente desconectado");
//        } finally {
//            disconnect();
//        }
//    }
//
//    public void sendMessage(Mensajes mensaje) {
//
//        String jsonMessage = ManejadorMensajes.toJson(mensaje);
//
//        if (out != null && !clientSocket.isClosed()) {
//            out.println(jsonMessage);
//        }
//    }
//
//    private void disconnect() {
//        running = false;
//        server.removeClient(this);
//        try {
//            if (clientSocket != null && !clientSocket.isClosed()) {
//                clientSocket.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Server getServer() {
//        return server;
//    }

}
