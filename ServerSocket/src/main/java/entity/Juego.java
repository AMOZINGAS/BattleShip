/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import entity.Disparo;
import capaLogica.excepciones.ServerLogicException;
import java.util.ArrayList;
import java.util.List;
import mensajes.ResUnirse;
import entity.EnEsperaEstado;
import java.util.Observable;

/**
 *
 * @author Beto_
 */
public class Juego extends Observable{
    
    private static Juego instance;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Tablero> tableros;
    private Jugador jugadorEnTurno;
    private StateJuego estado;
    private List<Disparo> disparos;
    private final int temporizador = 30;
    private GestorTurnos gestorTurnos;

    public Juego() {
//        this.jugadores = new ArrayList<>();
//        this.tableros = new ArrayList<>();
        this.estado = new EnEsperaEstado();
//        this.gestorTurnos = new GestorTurnos(this);
//        this.disparos = new ArrayList<>();
    }
    
    
     public static Juego getInstance() {
        if (instance == null) {
            instance = new Juego();
            System.out.println("Nueva instancia de Juego creada.");
        }
        return instance;
    }
    
    
    public void procesarDisparo(Jugador lanzador, Coordenada coordenada) throws ServerLogicException{
        if (estado instanceof EnCursoEstado) {
        ((EnCursoEstado) estado).manejarDisparo(this, lanzador, coordenada);
        } else {
            throw new ServerLogicException("No se puede procesar el disparo en el estado actual del juego");
        }
    }
    
    
    
    public boolean esGameOver(){
        return tableros.get(0).estanTodasNavesHundidas() || tableros.get(1).estanTodasNavesHundidas();
    }
    
    public Jugador getGanador() throws ServerLogicException{
        if(esGameOver() && estado instanceof FinalizadoEstado){
            return (tableros.get(0).estanTodasNavesHundidas()) ? jugadores.get(0) : jugadores.get(1);
        }else{
            throw new ServerLogicException("El juego aun no ha finalizado");
        }
    }
    
    public void gestionarTurno(){
        gestorTurnos.reducirTiempo();
    }

    public Jugador getJugador1() {
        return jugadores.get(0);
    }

    public synchronized void unirse() {
        System.out.println(this.getEstado());
        if (this.getEstado() instanceof EnEsperaEstado) {
            setChanged();
            ResUnirse res = new ResUnirse("JUGADOR_UNIDO");
            notifyObservers(res);
        } else {
            setChanged();
            notifyObservers(new ResUnirse("JUGADOR_NO_UNIDO"));
        }
    }
    
    public void setJugador1(Jugador jugador1) {
        this.jugadores.set(0, jugador1);
    } 
        
    public Jugador getJugador2() {
        return jugadores.get(1);
    }
    
     public void setJugador2(Jugador jugador2) {
        this.jugadores.set(1, jugador2);
    }

    public Jugador getJugadorEnTurno() {
        return jugadorEnTurno;
    }

    public void setJugadorEnTurno(Jugador jugadorEnTurno) {
        this.jugadorEnTurno = jugadorEnTurno;
    }

    public StateJuego getEstado() {
        return estado;
    }

    public void setEstado(StateJuego estado) {
        this.estado = estado;
    }

    public Tablero getTableroJugador1() {
        return tableros.get(0);
    }

    public void setTableroJugador1(Tablero tableroJugador1) {
        this.tableros.set(0, tableroJugador1);
    }

    public Tablero getTableroJugador2() {
        return tableros.get(1);
    }

    public void setTableroJugador2(Tablero tableroJugador2) {
        this.tableros.set(1, tableroJugador2);
    }

    public List<Disparo> getDisparos() {
        return disparos;
    }

    public GestorTurnos getGestorTurnos() {
        return gestorTurnos;
    }

    public void setGestorTurnos(GestorTurnos gestorTurnos) {
        this.gestorTurnos = gestorTurnos;
    }
}
