/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serversocket;

import entity.Juego;
import entity.StateJuego;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import mensajes.Mensajes;

/**
 *
 * @author PC
 */
public class Controlador implements Observer {

    private static Controlador instance;
    ManejadorCliente clientHandler;
    Server server = Server.getInstance();
    Juego juego = Juego.getInstance();
    // blackboard vFinal
    private Queue<String> accionesPendientes = new LinkedList<>();

    private Controlador() {
    }

    public void crearPartida(Mensajes mensaje, ManejadorCliente aThis) {
        this.clientHandler = aThis;
        juego.getEstado().manejarEstado(juego);
    }

//    public void triggerConfiguracionPartida(ManejadorCliente aThis, Mensajes mensaje) {
//        this.clientHandler = aThis;
//        accionesPendientes.add("crearFichasNumericas");
//        accionesPendientes.add("crearComodines");
//        accionesPendientes.add("crearMazo");
//
//        ejecutarSiguienteAccion(mensaje);
//    }

    public static synchronized Controlador getInstance() {
        if (instance == null) {
            instance = new Controlador();
        }
        return instance;
    }

//    public void registrarJugador(Mensajes mensaje, ManejadorCliente aThis) {
//        this.clientHandler = aThis;
//        realizarAccion("registrarJugador", mensaje);
//    }
//
//    public void realizarDisparo(Mensajes mensaje, ManejadorCliente aThis){
//        this.clientHandler = aThis;
//        realizarAccion("realizarDisparo", mensaje);
//    }
//    
    public void unirse(Mensajes mensaje, ManejadorCliente aThis) {
        this.clientHandler = aThis;
        juego.unirse();
    }
//
//    public void solicitarInicio(Mensajes mensaje, ManejadorCliente aThis) {
//        this.clientHandler = aThis;
//        realizarAccion("solicitarInicio", mensaje);
//    }
//
//    public void responderSolicitudInicio(Mensajes mensaje, ManejadorCliente aThis) {
//        this.clientHandler = aThis;
//        realizarAccion("responderSolicitudInicio", mensaje);
//    }
//
//    public void triggerIniciarPartida(Mensajes mensaje) {
//        accionesPendientes.add("repartirFichas");
//        accionesPendientes.add("asignarTurnos");
//        accionesPendientes.add("empezarPartida");
//        
//        ejecutarSiguienteAccion(mensaje);
//    }
//
//    public void pasarTurno(Mensajes mensaje, ManejadorCliente aThis) {
//        realizarAccion("pasarTurno", mensaje);
//    }
//    
//    public void tomarFicha(Mensajes mensaje, ManejadorCliente aThis){
//        this.clientHandler = aThis;
//        realizarAccion("tomarFicha", mensaje);
//    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Mensajes) {
            Mensajes mensaje = (Mensajes) arg;
//            if (mensaje.getComando().equals("CONFIGURAR_PARTIDA")) {
//                if (!juego.estaConfigurado()) {
//                    ejecutarSiguienteAccion(mensaje);
//                } else {
//                    System.out.println("El juego ya esta configurado");
//                }
//            } 
//            
//            
//            if(mensaje.getComando().equals("INICIAR_PARTIDA")) {
//                if(!juego.partidaEstaEmpezada()) {
//                    ejecutarSiguienteAccion(mensaje);
//                } else {
//                    System.out.println("Juego ya esta empezado!!!!!");
//                }
//            }
//            
            if(mensaje.getComando().equals("JUGADOR_UNIDO")) {
                System.out.println("se unioooo!!");
                unirse(mensaje, clientHandler);
            }
            
            
            else {
                System.out.println("Controlador esta recibiendo esto: " + mensaje.getComando());
                server.broadcastMessage(mensaje, clientHandler);
            }

        }
    }
}
