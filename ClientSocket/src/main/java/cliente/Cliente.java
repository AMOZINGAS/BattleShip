/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import dtos.DisparoDTO;
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
import mensajes.ReqDisparo;
import mensajes.ReqRegistrarJugadorConfig;

/**
 *
 * @author Amos Heli Olguin
 */
public class Cliente {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean connected;
    private MessageListener messageListener;
    private ManejadorRespuesta responseManager = new ManejadorRespuesta();

    public Cliente() {
        connected = false;
    }

    public boolean isConnected() {
        return connected;
    }

    public void connectToServer(int puerto) {
        try {
            socket = new Socket("localhost", puerto);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            connected = true;
            System.out.println("Conectado al servidor");
            messageListener = new MessageListener();
            new Thread(messageListener).start();
        } catch(UnknownHostException e){
            System.out.println("No se encontro el host con ese puerto");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No se pudo conectar al servidor");
        }
    }

    public void desconectar() throws IOException {
        if (isConnected()) {
            socket.close();
            System.out.println("Desconectado del servidor.");
        }
    }

    private void sendMessage(Mensajes mensaje) {
        if (connected && out != null) {
            System.out.println("voy a mandarle al server esto: " + mensaje.getComando());
            String jsonMessage = ManejadorMensajes.toJson(mensaje);
            out.println(jsonMessage);
        }
    }

    public void crearPartida() {
        ReqCrearPartida peticion = new ReqCrearPartida();
        sendMessage(peticion);
    }

    public void unirse() {
        sendMessage(new ReqUnirse());
    }
    
    public void registrarJugadorConfig(JugadorDTO jugador, List<NaveConfigDTO> flotilla){
        sendMessage(new ReqRegistrarJugadorConfig(jugador, flotilla));
    }
    
    public void disparar(DisparoDTO disparo){
        sendMessage(new ReqDisparo(disparo));
    }

    private class MessageListener implements Runnable {
        private boolean running = true;

        @Override
        public void run() {
            try {
                String message;
                while (running && (message = in.readLine()) != null) {
                    Mensajes mensaje = ManejadorMensajes.fromJson(message);
                    responseManager.handleResponse(mensaje);
                }
            } catch (IOException e) {
                System.out.println("Desconectado del servidor");
                connected = false;
            }
        }

        public void stop() {
            running = false;
        }

    }
}

