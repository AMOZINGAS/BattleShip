/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serversocket;

import capaLogica.excepciones.ServerLogicException;
import dtos.NaveConfigDTO;
import dtos.OrientacionENUM;
import entity.Coordenada;
import entity.EnCursoEstado;
import entity.INaveFactory;
import entity.Juego;
import entity.Jugador;
import entity.Nave;
import entity.NaveFactory;
import entity.Orientacion;
import entity.Tablero;
import java.util.ArrayList;
import java.util.List;
import mensajes.ManejadorMensajes;
import mensajes.Mensajes;
import mensajes.ManejadorMensajes;
import mensajes.ReqCrearPartida;
import mensajes.ReqDisparo;
import mensajes.ReqRegistrarJugadorConfig;
import mensajes.ReqUnirse;
import mensajes.ResCrearPartida;
import mensajes.ResDisparo;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class ProcesadorMensajes {
    private static ProcesadorMensajes instance;
    private Juego juego;
    private Controlador controlador;

    private ProcesadorMensajes() {
        this.juego = Juego.getInstance();
        this.controlador = Controlador.getInstance();
    }

    public static ProcesadorMensajes getInstance() {
        if (instance == null) {
            instance = new ProcesadorMensajes();
        }
        return instance;
    }

    public void procesarMensaje(Mensajes mensaje, ServerConnection connection) {
        if (mensaje instanceof ReqCrearPartida) {
            manejarCrearPartida((ReqCrearPartida) mensaje, connection);
        } 
        else if (mensaje instanceof ReqUnirse) {
            manejarUnirsePartida((ReqUnirse) mensaje, connection);
        } 
        else if (mensaje instanceof ReqRegistrarJugadorConfig) {
            manejarJugadorColocarNaves((ReqRegistrarJugadorConfig) mensaje, connection);
        } 
        else if (mensaje instanceof ReqDisparo) {
//            manejarDisparo((ReqDisparo) mensaje, connection);
        } 
        else {
            System.out.println("Comando desconocido recibido: " + mensaje);
        }
    }

    private void manejarCrearPartida(ReqCrearPartida req, ServerConnection connection) {
        juego.crearPartida();
        System.out.println("PM - Partida creada: ");
    }

    private void manejarUnirsePartida(ReqUnirse req, ServerConnection connection) {
        juego.unirse();
        System.out.println("PM - Jugador unido: ");
        
    }

    private void manejarJugadorColocarNaves(ReqRegistrarJugadorConfig req, ServerConnection connection) {
        connection.setJugador(new Jugador(req.getJugador().getNombre(), req.getJugador().getColor()));
        Jugador jugador = connection.getJugador();
        
        if (jugador == null) {
            System.out.println("Jugador no registrado.");
            return;
        }
        Tablero tablero = jugador.getTableroPropio();
        
        if (tablero != null) {
        List<Nave> flotilla = new ArrayList<>();
        INaveFactory factory = new NaveFactory();
        try {
            int i=0;
            for (NaveConfigDTO config : req.getConfigNaves()) {
                Nave nave = null;
                String tipoNave = config.getTipo().toLowerCase();

                if (tipoNave.contains("barco")) {
                    nave = factory.crearBarco();
                } else if (tipoNave.contains("submarino")) {
                    nave = factory.crearSubmarino();
                } else if (tipoNave.contains("crucero")) {
                    nave = factory.crearCrucero();
                } else if (tipoNave.contains("portaaviones")) {
                    nave = factory.crearPortaAviones();
                } else {
                    System.err.println("Tipo de nave desconocido: " + config.getTipo());
                    continue; //Saltar a la siguiente nave si el tipo no coincide
                }
                //2. Colocar las naves en el Tablero del Jugador.
                Coordenada coordenadaInicial = new Coordenada(config.getCoordenadaInicial().getCoordenadasX(), config.getCoordenadaInicial().getCoordenadasY());
                tablero.colocarNave(nave, coordenadaInicial, config.getOrientacion() == OrientacionENUM.HORIZONTAL ? Orientacion.HORIZONTAL : Orientacion.VERTICAL);

                //3. Almacenar la configuración del jugador.
                jugador.agregarNave(nave);
                System.out.println(jugador.getFlotilla().get(i).getTipo() + " Añadido. ");
                i++;
            }
        }catch(ServerLogicException e){
            System.err.println("Error al colocar naves de " + jugador.getNombre() + ": " + e.getMessage());
//                clientHandler.sendMessage(new ResError("ERROR_CONFIGURACION", e.getMessage()));
        }

            System.out.println("PM - Naves de " + jugador.getNombre() + " colocadas en el tablero.");
            System.out.println("PM - Cantidad de naves creadas: " + jugador.getFlotilla().size());
//            controlador.jugadorListoParaJugar(jugador);

        // Checar cambio de estado
        juego.getEstado().manejarEstado(juego);
        
        }   
    }

//    private void manejarDisparo(ResDisparo req, ServerConnection connection) {
//        Jugador lanzador = connection.getJugador();
//        if (lanzador == null) {
//            System.out.println("Jugador no registrado.");
//            return;
//        }
//
//        try {
//            EnCursoEstado estado = (EnCursoEstado) juego.getEstado();
//            estado.manejarDisparo(juego, lanzador, req.getCoordenada);
//        } catch (ClassCastException cce) {
//            System.out.println("El juego no está en curso.");
//        } catch (ServerLogicException e) {
//            e.printStackTrace();
//        }
//    }
    
//    private ManejadorCliente manejadorCliente;
//    private ManejadorPeticion manejadorComando;
//
//    public ProcesadorMensajes(ManejadorCliente clientHandler) {
//        this.manejadorCliente = clientHandler;
//        this.manejadorComando = new ManejadorPeticion(clientHandler);
//    }
//
//    public void procesarMensaje(String inputLine) {
//        try {
//            System.out.println(inputLine);
//            Mensajes mensaje = ManejadorMensajes.fromJson(inputLine);
//            
//            System.out.println("SErver: se recibio la peticion " + mensaje.getComando());
//            manejadorComando.manejarComando(mensaje);
//
//        } catch (Exception e) {
//            System.err.println("Error procesando mensaje: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

}
