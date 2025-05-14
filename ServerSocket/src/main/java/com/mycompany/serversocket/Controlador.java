/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serversocket;

import dtos.JugadorDTO;
import entity.Barco;
import entity.Casilla;
import entity.Color;
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
import mensajes.Mensajes;
import mensajes.ReqRegistrarJugador;
import mensajes.ResCrearPartida;

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
    private Queue<String> accionesPendientes = new LinkedList<>();

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
        
        ReqRegistrarJugador req = (ReqRegistrarJugador) mensaje;
        JugadorDTO jugadorClient = req.getJugador();
        INaveFactory factory = new NaveFactory();
        List<Nave> flotilla = new ArrayList<>();
        
        flotilla.add(factory.crearBarco());
        flotilla.add(factory.crearBarco());
        flotilla.add(factory.crearBarco());
        flotilla.add(factory.crearSubmarino());
        flotilla.add(factory.crearSubmarino());
        flotilla.add(factory.crearSubmarino());
        flotilla.add(factory.crearSubmarino());
        flotilla.add(factory.crearCrucero());
        flotilla.add(factory.crearCrucero());
        flotilla.add(factory.crearPortaAviones());
        flotilla.add(factory.crearPortaAviones());
        Jugador jugador = new Jugador(jugadorClient.getNombre(), Color.ROJO);
        jugador.setFlotilla(flotilla);
        juego.addJugador(jugador);
        
        
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
            }else {
                System.out.println("Controlador esta recibiendo esto: " + mensaje.getComando());
                server.broadcastMessage(mensaje, clientHandler);
            }

        }
    }
}
