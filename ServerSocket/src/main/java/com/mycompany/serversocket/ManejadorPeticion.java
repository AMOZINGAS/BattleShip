/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serversocket;


import capaLogica.excepciones.ServerLogicException;
import com.google.gson.Gson;
import dtos.JuegoDTO;
import dtos.JugadorDTO;
import dtos.NaveConfigDTO;
import dtos.OrientacionENUM;
import entity.Coordenada;
import entity.INaveFactory;
import entity.Juego;
import entity.Jugador;
import entity.Nave;
import entity.NaveFactory;
import entity.Orientacion;
import entity.Tablero;
import java.util.ArrayList;
import java.util.List;
import mensajes.Mensajes;
import mensajes.ReqDisparo;
import mensajes.ReqRegistrarJugadorConfig;
import mensajes.ReqUnirse;
import mensajes.ResConfiguracionRecibida;


/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class ManejadorPeticion {

    private ManejadorCliente clientHandler;
    private Controlador controlador;

    public ManejadorPeticion(ManejadorCliente clientHandler) {
        this.clientHandler = clientHandler;
        this.controlador = Controlador.getInstance();
    }

    public void manejarComando(Mensajes mensaje) {
        if (mensaje == null) {
            System.out.println("mensaje nulo");
            return;
        }

        switch (mensaje.getComando()) {
            case "CREAR_PARTIDA":
                handleCrearPartida(mensaje);
                break;
            case "UNIRSE":
                handleUnirse((ReqUnirse) mensaje);
                break;
            case "REGISTRAR_JUGADOR_CONFIGURACION":
                handleRegistrarJugadorConfig((ReqRegistrarJugadorConfig)(mensaje));
                break;
            default:
                System.out.println("Comando desconocido: " + mensaje.getComando());
        }
    }
    
    private void handleCrearPartida(Mensajes mensaje) {
        controlador.crearPartida(mensaje, clientHandler);
    }
    
    private void handleUnirse(ReqUnirse mensaje) {
        controlador.unirse(mensaje, clientHandler);
    }
    
    private void handleRegistrarJugadorConfig(ReqRegistrarJugadorConfig mensaje) {
        Jugador jugador = new Jugador(mensaje.getJugador().getNombre(), mensaje.getJugador().getColor());
        List<NaveConfigDTO> configuracionNaves = mensaje.getConfigNaves();

        System.out.println("Servidor: Recibida configuración de " + jugador.getNombre());
        System.out.println("Jugador: " + jugador);
        System.out.println("Naves tamaño: " + configuracionNaves.size());
    
    //1. Crear objetos Nave basados en la información recibida.
        if (jugador != null) {
            Tablero tablero = jugador.getTableroPropio();

            if (tablero != null) {
                List<Nave> flotilla = new ArrayList<>();
                INaveFactory factory = new NaveFactory();
                try {
                    int i=0;
                    for (NaveConfigDTO config : configuracionNaves) {
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
                            continue; // Saltar a la siguiente nave si el tipo no coincide
                        }
                        Coordenada coordenadaInicial = new Coordenada(config.getCoordenadaInicial().getCoordenadasX(), config.getCoordenadaInicial().getCoordenadasY());
                        jugador.agregarNave(nave);
                        System.out.println(jugador.getFlotilla().get(i).getTipo() + " Añadido. ");
                        tablero.colocarNave(nave, coordenadaInicial, config.getOrientacion() == OrientacionENUM.HORIZONTAL ? Orientacion.HORIZONTAL : Orientacion.VERTICAL);
                        jugador.agregarNave(nave);
                        i++;
                    }
                    System.out.println("Servidor: Naves de " + jugador.getNombre() + " colocadas en el tablero.");
                    System.out.println("Cantidad de naves creadas: " + jugador.getFlotilla().size());
                    controlador.jugadorListoParaJugar(jugador, clientHandler);
                    clientHandler.sendMessage(new ResConfiguracionRecibida("CONFIGURACION_RECIBIDA"));

                } catch (ServerLogicException e) {
                    System.err.println("Error al colocar naves de " + jugador.getNombre() + ": " + e.getMessage());
    //                clientHandler.sendMessage(new ResError("ERROR_CONFIGURACION", e.getMessage()));
                }
            } else {
                System.err.println("Error: No se encontró el tablero para el jugador " + jugador.getNombre());
    //            clientHandler.sendMessage(new ResError("ERROR_CONFIGURACION", "No se encontró el tablero del jugador."));
            }
        } else {
            System.err.println("Error: No se encontró al jugador asociado a la conexión.");
    //        clientHandler.sendMessage(new ResError("ERROR_CONFIGURACION", "Jugador no identificado."));
        }
    }
    
    //2. Colocar las naves en el Tablero del Jugador.
    
    //3. Almacenar la configuración del jugador.
    
}
