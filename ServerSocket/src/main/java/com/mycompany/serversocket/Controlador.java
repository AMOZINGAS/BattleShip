/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serversocket;

import dtos.JugadorDTO;
import entity.Barco;
import entity.Casilla;
import entity.Color;
import entity.EnCursoEstado;
import entity.INaveFactory;
import entity.Juego;
import entity.Jugador;
import entity.NaveFactory;
import entity.SinConfiguracion;
import entity.StateJuego;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import entity.Nave;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import mensajes.Mensajes;
import mensajes.ReqRegistrarJugadorConfig;
import mensajes.ResCrearPartida;
import mensajes.ResEspera;
import mensajes.ResJuegoIniciado;
import mensajes.ResTurno;
import mensajes.ResUnirse;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class Controlador implements Observer{
    private static Controlador instance;
    private ModeloServer modelo;
    private Server server;

    private Controlador() {
        modelo = ModeloServer.getInstance();
        server = Server.getInstance();
    }

    public static Controlador getInstance() {
        return instance == null ? new Controlador() : instance;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Mensajes) {
            Mensajes mensaje = (Mensajes) arg;

            if(mensaje.getComando().equals("PARTIDA_CREADA")){
                System.out.println("Empezando cambio de view");
                server.broadcastMessage(mensaje, null);
            }
            if(mensaje.getComando().equals("JUGADOR_UNIDO")) {
                System.out.println("se unioooo!!");
                server.broadcastMessage(mensaje, null);
            }
            if(mensaje.getComando().equals("ESPERANDO_OPONTENTE")) {
                System.out.println("esperandin....");
                server.broadcastMessage(mensaje, null);
            }
            else {
                System.out.println("Controlador esta recibiendo esto: " + mensaje.getComando());
                server.broadcastMessage(mensaje, null);
            }

        }
    }

    public void crearPartida(Mensajes mensaje, ServerSender sender) {
        modelo.crearPartida(mensaje, sender);
    }

    public void unirseAPartida(Mensajes mensaje, ServerSender sender) {
        modelo.unirseAPartida(mensaje, sender);
    }

//    private static Controlador instance;
//    ManejadorCliente clientHandler;
//    ModeloServer modelo = new ModeloServer();
//    Server server = Server.getInstance();
//    Juego juego = Juego.getInstance();
//    private Map<Jugador, Boolean> jugadoresListos = new ConcurrentHashMap<>();
//    private Queue<String> accionesPendientes = new LinkedList<>();
//
//    private Controlador() {
//    }
//
//    public void crearPartida(Mensajes mensaje, ManejadorCliente aThis) {
//        this.clientHandler = aThis;
//        modelo.crearPartida(juego, mensaje);
//    }
//
//    public static synchronized Controlador getInstance() {
//        if (instance == null) {
//            instance = new Controlador();
//        }
//        return instance;
//    }
//
//    public void jugadorListoParaJugar(Jugador jugador, ManejadorCliente aThis){
//        System.out.println("Controlador: Jugador " + jugador.getNombre() + " esta listo.");
//        jugadoresListos.put(jugador, true);
//        
//        if (estanTodosJugadoresListos()) {
//            System.out.println("Controlador: Ambos jugadores están listos. Iniciando el juego.");
//            juego.setEstado(new EnCursoEstado());
//            juego.iniciarPartida();
//            if (juego.getJugadorEnTurno() != null) {
//                clientHandler.sendMessage(new ResTurno(juego.getJugadorEnTurno().getNombre()));
//            }
//        } else {
//            clientHandler.sendMessage(new ResEspera("ESPERANDO_OPONENTE"));
//        }
//    }
//    
//    
//    public void unirse(Mensajes mensaje, ManejadorCliente aThis) {
//        this.clientHandler = aThis;
//        modelo.unirsePartida(juego, mensaje);
//    }
//    
//    @Override
//    public void update(Observable o, Object arg) {
//        if (arg instanceof Mensajes) {
//            Mensajes mensaje = (Mensajes) arg;
//
//            if(mensaje.getComando().equals("PARTIDA_CREADA")){
//                System.out.println("Empezando cambio de view");
//                server.broadcastMessage(mensaje, clientHandler);
//            }
//            if(mensaje.getComando().equals("JUGADOR_UNIDO")) {
//                System.out.println("se unioooo!!");
//                server.broadcastMessage(mensaje, clientHandler);
//            }
//            if(mensaje.getComando().equals("ESPERANDO_OPONTENTE")) {
//                System.out.println("esperandin....");
//                server.broadcastMessage(mensaje, clientHandler);
//            }
//            else {
//                System.out.println("Controlador esta recibiendo esto: " + mensaje.getComando());
//                server.broadcastMessage(mensaje, clientHandler);
//            }
//
//        }
//    }
//    
//    private boolean estanTodosJugadoresListos() {
//        if(jugadoresListos.size() <= 1){
//            return false;
//        }
//        
//        for (boolean listo : jugadoresListos.values()) {
//            if (!listo) {
//                return false; //Si al menos un jugador no está listo, es false
//            }
//        }
//        return true; //Todos los jugadores están listos
//    }
    
//    private ManejadorCliente obtenerManejadorCliente(Jugador jugador) {
//    for (ManejadorCliente cliente : server.getClientes()) {
//        if (cliente.getJugador() != null && cliente.getJugador().equals(jugador)) {
//            return cliente;
//        }
//    }
//    return null;
//}
}
