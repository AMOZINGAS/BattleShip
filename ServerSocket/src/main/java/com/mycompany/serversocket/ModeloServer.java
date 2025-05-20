/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serversocket;

import capaLogica.excepciones.ServerLogicException;
import dtos.JugadorDTO;
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
import entity.SinConfiguracion;
import entity.Tablero;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;
import mensajes.Mensajes;
import mensajes.ReqRegistrarJugadorConfig;
import mensajes.ResConfiguracionRecibida;
import mensajes.ResCrearPartida;
import mensajes.ResEspera;
import mensajes.ResJuegoIniciado;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class ModeloServer extends Observable{

    public void configurarJugador(Juego juego, Mensajes mensaje){
    
        ReqRegistrarJugadorConfig req = (ReqRegistrarJugadorConfig) mensaje;
        JugadorDTO jugadorDTO = req.getJugador();
        List<NaveConfigDTO> navesDTO = req.getConfigNaves();
        juego.registrarJugador(jugadorDTO, navesDTO);
    }
        
    

//    public void jugadorListoParaJugar(Jugador jugador, Juego juego){
//        System.out.println("Controlador: Jugador " + jugador.getNombre() + " esta listo.");
//        jugadoresListos.put(jugador, true);
//
//    }
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

    public void crearPartida(Juego juego, Mensajes mensaje){

        juego.crearPartida();

    }

    public void unirsePartida(Juego juego, Mensajes mensaje){

        juego.unirse();

    }
    
}
