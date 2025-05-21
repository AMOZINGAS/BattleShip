/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serversocket;

import capaLogica.excepciones.ServerLogicException;
import dtos.CoordenadasDTO;
import dtos.DisparoDTO;
import dtos.JugadorDTO;
import dtos.NaveConfigDTO;
import dtos.OrientacionENUM;
import entity.Casilla;
import entity.Coordenada;
import entity.EnCursoEstado;
import entity.INaveFactory;
import entity.Juego;
import entity.Jugador;
import entity.Nave;
import entity.NaveFactory;
import entity.Orientacion;
import entity.Resultado;
import entity.SinConfiguracion;
import entity.Tablero;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;
import mensajes.Mensajes;
import mensajes.ReqDisparo;
import mensajes.ReqRegistrarJugadorConfig;
import mensajes.ResConfiguracionRecibida;
import mensajes.ResCrearPartida;
import mensajes.ResDisparo;
import mensajes.ResEspera;
import mensajes.ResJuegoIniciado;
import mensajes.ResNaveHundida;

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
    
    public void disparar(Juego juego, Mensajes mensaje){
        ReqDisparo req = (ReqDisparo) mensaje;
        DisparoDTO disparo = req.getDisparo();
        try{
            Coordenada coordenada = new Coordenada(disparo.getCoordenadas().getCoordenadasX(), disparo.getCoordenadas().getCoordenadasY());
            juego.procesarDisparo(juego.getJugadorEnTurno(), coordenada);
            if(juego.getDisparos().getLast().getResultado().equals(Resultado.AGUA)){
                setChanged();
                notifyObservers(new ResDisparo("AGUA", new JugadorDTO(juego.getJugadorEnTurno().getNombre(), juego.getJugadorEnTurno().getColor())));
                System.out.println("MS - notificando agua");
            }else{
                setChanged();
                notifyObservers(new ResDisparo("IMPACTO", new JugadorDTO(juego.getJugadorEnTurno().getNombre(), juego.getJugadorEnTurno().getColor())));
                System.out.println("MS - notificando impacto");
            }
            
            //Si la nave se hundió
            Tablero tableroObjetivo = (juego.getJugadorEnTurno() == juego.getJugador1()) ? juego.getTableroJugador2() : juego.getTableroJugador1();
            Nave nave = tableroObjetivo.obtenerNaveEnCasilla(coordenada);
            if(nave != null && nave.estaHundida()){
                setChanged();
                List<CoordenadasDTO> coords = new ArrayList<>();
                for (Casilla casilla : tableroObjetivo.obtenerNaveEnCasilla(coordenada).getCasillas()) {
                    coords.add(new CoordenadasDTO(casilla.getCoordenada().getX(), casilla.getCoordenada().getY()));
                }
                notifyObservers(new ResNaveHundida(coords));
            }
        }catch(ServerLogicException e){
            System.out.println("Error al disparar en el server :(");
        }
        
    }

    public void crearPartida(Juego juego, Mensajes mensaje){

        juego.crearPartida();

    }

    public void unirsePartida(Juego juego, Mensajes mensaje){

        juego.unirse();

    }    
}
