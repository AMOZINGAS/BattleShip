/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import dtos.JugadorDTO;
import dtos.NaveConfigDTO;
import mensajes.Mensajes;
import mensajes.ManejadorMensajes;
import mensajes.ReqUnirse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.List;
import mensajes.ReqCrearPartida;
import mensajes.ReqRegistrarJugadorConfig;

/**
 * Esta clase sería el orquestador, conecta, envía y escucha
 * Es el Main del Cliente, manejando Connection, Listener y Sender
 * @author Amos Heli Olguin
 */
public class Cliente {
    private final ClienteConnection connection;
    private final ClienteSender sender;
    private ClienteListener listener;
    private final ManejadorRespuesta manejadorRespuesta;

    public Cliente() {
        connection = new ClienteConnection();
        sender = new ClienteSender(connection);
        manejadorRespuesta = new ManejadorRespuesta();
    }

    public boolean connectToServer(String host, int puerto) {
        boolean success = connection.connect(host, puerto);
        if (success) {
            listener = new ClienteListener(connection, manejadorRespuesta);
            new Thread(listener).start();
        }
        return success;
    }

    public void disconnect() {
        if (listener != null) listener.stop();
        connection.disconnect();
    }

    public boolean isConnected() {
        return connection.isConnected();
    }

    public void crearPartida() {
        sender.sendMessage(new ReqCrearPartida());
    }

    public void unirse() {
        sender.sendMessage(new ReqUnirse());
    }

    public void registrarJugadorConfig(JugadorDTO jugador, List<NaveConfigDTO> flotilla) {
        sender.sendMessage(new ReqRegistrarJugadorConfig(jugador, flotilla));
    }
//    private Socket socket;
//    private PrintWriter out;
//    private BufferedReader in;
//    private boolean connected;
//    private MessageListener messageListener;
//    private ManejadorRespuesta responseManager = new ManejadorRespuesta();
//
//    public Cliente() {
//        connected = false;
//    }
//
//    public boolean isConnected() {
//        return connected;
//    }
//
//    public void connectToServer(int puerto) {
//        try {
//            socket = new Socket("localhost", puerto);
//            out = new PrintWriter(socket.getOutputStream(), true);
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            connected = true;
//            System.out.println("Conectado al servidor");
//            messageListener = new MessageListener();
//            new Thread(messageListener).start();
//        } catch(UnknownHostException e){
//            System.out.println("No se encontro el host con ese puerto");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("No se pudo conectar al servidor");
//        }
//    }
//
//    public void desconectar() throws IOException {
//        if (isConnected()) {
//            socket.close();
//            System.out.println("Desconectado del servidor.");
//        }
//    }
//
//    private void sendMessage(Mensajes mensaje) {
//        if (connected && out != null) {
//            System.out.println("voy a mandarle al server esto: " + mensaje.getComando());
//            String jsonMessage = ManejadorMensajes.toJson(mensaje);
//            out.println(jsonMessage);
//        }
//    }
//
//    public void crearPartida() {
//        ReqCrearPartida peticion = new ReqCrearPartida();
//        sendMessage(peticion);
//    }
//
//    public void unirse() {
//        sendMessage(new ReqUnirse());
//    }
//    
//    public void registrarJugadorConfig(JugadorDTO jugador, List<NaveConfigDTO> flotilla){
//        sendMessage(new ReqRegistrarJugadorConfig(jugador, flotilla));
//    }
//
//    private class MessageListener implements Runnable {
//        private boolean running = true;
//
//        @Override
//        public void run() {
//            try {
//                String message;
//                while (running && (message = in.readLine()) != null) {
//                    Mensajes mensaje = ManejadorMensajes.fromJson(message);
//                    responseManager.handleResponse(mensaje);
//                }
//            } catch (IOException e) {
//                System.out.println("Desconectado del servidor");
//                connected = false;
//            }
//        }
//
//        public void stop() {
//            running = false;
//        }
//
//    }
}

