/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serversocket;

import capaLogica.excepciones.ServerLogicException;
import dtos.JugadorDTO;
import dtos.NaveConfigDTO;
import dtos.OrientacionENUM;
import entity.Barco;
import entity.Casilla;
import entity.Color;
import entity.Coordenada;
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
import entity.Orientacion;
import entity.Tablero;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import mensajes.Mensajes;
import mensajes.ReqRegistrarJugadorConfig;
import mensajes.ResConfiguracionRecibida;
import mensajes.ResCrearPartida;
import mensajes.ResEspera;
import mensajes.ResJuegoIniciado;
import mensajes.ResTurno;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class Controlador implements Observer {

    private static Controlador instance;
    ManejadorCliente clientHandler;
    ModeloServer modelo = new ModeloServer();
    Server server = Server.getInstance();
    Juego juego = Juego.getInstance();
    private Map<Jugador, Boolean> jugadoresListos = new ConcurrentHashMap<>();

    private Controlador() {
    }

    public void crearPartida(Mensajes mensaje, ManejadorCliente aThis) {
        this.clientHandler = aThis;
        modelo.crearPartida(juego, mensaje);
    }

    public static synchronized Controlador getInstance() {
        if (instance == null) {
            instance = new Controlador();
        }
        return instance;
    }

    public void registrarJugador(Mensajes mensaje, ManejadorCliente aThis){
        this.clientHandler = aThis;
        modelo.configurarJugador(juego, mensaje);
    }
    
    
    public void unirse(Mensajes mensaje, ManejadorCliente aThis) {
        this.clientHandler = aThis;
        modelo.unirsePartida(juego, mensaje);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Mensajes) {
            Mensajes mensaje = (Mensajes) arg;

            if(mensaje.getComando().equals("PARTIDA_CREADA")){
                System.out.println("Empezando cambio de view");
                server.broadcastMessage(mensaje, clientHandler);
            }
            if(mensaje.getComando().equals("JUGADOR_UNIDO")) {
                System.out.println("se unioooo!!");
                server.broadcastMessage(mensaje, clientHandler);
            }
            if(mensaje.getComando().equals("JUGADORES_LISTOS")) {
                System.out.println("todos ready pa la party....");
                server.broadcastMessage(mensaje, clientHandler);
            }
            if(mensaje.getComando().equals("CONFIGURACION_RECIBIDA")){
                System.out.println("Jugador configurado ._.");
                server.broadcastMessage(mensaje, clientHandler);
            }
            else {
                System.out.println("Controlador esta recibiendo esto: " + mensaje.getComando());
                server.broadcastMessage(mensaje, clientHandler);
            }

        }
    }
    
    
    
//    private ManejadorCliente obtenerManejadorCliente(Jugador jugador) {
//    for (ManejadorCliente cliente : server.getClientes()) {
//        if (cliente.getJugador() != null && cliente.getJugador().equals(jugador)) {
//            return cliente;
//        }
//    }
//    return null;
//}
}
