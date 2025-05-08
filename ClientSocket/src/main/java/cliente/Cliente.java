/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import dtos.JuegoDTO;
import dtos.JugadorDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import mensajes.Mensajes;
import mensajes.ManejadorMensajes;
//import mensajes.ReqConfigurarPartida;
//import mensajes.ReqCrearPartida;
//import mensajes.ReqPasarTurno;
//import mensajes.ReqRegistroJugador;
//import mensajes.ReqResponderSolicitudInicio;
//import mensajes.ReqSolicitarInicio;
//import mensajes.ReqTomarFicha;
import mensajes.ReqUnirse;
//import menuMVC.ModeloMenu;
//import partidaMVC.ModeloJuego;

import dtos.JuegoDTO;
import dtos.JugadorDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import mensajes.ReqCrearPartida;

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

//    public void registrarJugador(JugadorDTO jugador) {
//        sendMessage(new ReqRegistroJugador(jugador));
//    }
//
//    public void configurarPartida(JuegoDTO configuracion) {
//        sendMessage(new ReqConfigurarPartida(configuracion));
//    }
//
//    public void solicitarInicio(JugadorDTO jugador) {
//        sendMessage(new ReqSolicitarInicio(jugador));
//    }
//
//    public void responderSolicitudInicio(Boolean res, JugadorDTO jugador) {
//        sendMessage(new ReqResponderSolicitudInicio(res, jugador));
//    }
//    
//    public void pasarTurno(){
//        sendMessage(new ReqPasarTurno());
//    }
//    
//    public void tomarFicha(JugadorDTO jugador){
//        sendMessage(new ReqTomarFicha(jugador));
//    }
    
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

