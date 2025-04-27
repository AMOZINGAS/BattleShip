/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaLogica.juego;

import capaLogica.disparo.Disparo;
import capaLogica.excepciones.ServerLogicException;
import capaLogica.jugador.Jugador;
import capaLogica.tablero.Coordenada;
import capaLogica.tablero.Tablero;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Beto_
 */
public class Juego {
    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tableroJugador1;
    private Tablero tableroJugador2;
    private Jugador jugadorEnTurno;
    private StateJuego estado;
    private List<Disparo> disparos;
    private final int temporizador = 30;
    private GestorTurnos gestorTurnos;

    public Juego(Jugador jugador1, Jugador jugador2) {
        this(jugador1, jugador2, null, null);
    }
    public Juego(Jugador jugador1, Jugador jugador2, Tablero tablero1, Tablero tablero2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.tableroJugador1 = tablero1;
        this.tableroJugador2 = tablero2;
        this.estado = new EnEsperaEstado();
        this.gestorTurnos = new GestorTurnos(this);
        this.jugadorEnTurno = jugador1;
        this.disparos = new ArrayList<>();
    }
    
    public void iniciarJuego(){
        estado.manejarEstado(this);
        if(this.estado instanceof EnCursoEstado){
            gestorTurnos.iniciarTurno();
        }
    }
    
    public void procesarDisparo(Jugador lanzador, Coordenada coordenada) throws ServerLogicException{
        if (estado instanceof EnCursoEstado) {
        ((EnCursoEstado) estado).manejarDisparo(this, lanzador, coordenada);
        } else {
            throw new ServerLogicException("No se puede procesar el disparo en el estado actual del juego");
        }
    }
    
    public boolean esGameOver(){
        return tableroJugador1.estanTodasNavesHundidas() || tableroJugador2.estanTodasNavesHundidas();
    }
    
    public Jugador getGanador() throws ServerLogicException{
        if(esGameOver() && estado instanceof FinalizadoEstado){
            return (tableroJugador1.estanTodasNavesHundidas()) ? jugador2 : jugador1;
        }else{
            throw new ServerLogicException("El juego aun no ha finalizado");
        }
    }
    
    public void gestionarTurno(){
        gestorTurnos.reducirTiempo();
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    } 
        
    public Jugador getJugador2() {
        return jugador2;
    }
    
     public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
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
        return tableroJugador1;
    }

    public void setTableroJugador1(Tablero tableroJugador1) {
        this.tableroJugador1 = tableroJugador1;
    }

    public Tablero getTableroJugador2() {
        return tableroJugador2;
    }

    public void setTableroJugador2(Tablero tableroJugador2) {
        this.tableroJugador2 = tableroJugador2;
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
