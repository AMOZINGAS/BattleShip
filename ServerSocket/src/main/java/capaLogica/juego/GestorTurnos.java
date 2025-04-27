/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaLogica.juego;

import capaLogica.jugador.Jugador;

/**
 *
 * @author Beto_
 */
public class GestorTurnos {
    private Juego juego;
    private Jugador jugadorEnTurno;
    private int tiempoRestante = 30;
    private boolean turnoActivo = false;
    
    public GestorTurnos(Juego juego){
        this.juego = juego;
        //Igual lo podemos cambiar a aleatorio o dejarlo así 
        this.jugadorEnTurno = juego.getJugador1();
    }
    
    
    public void iniciarTurno(){
        turnoActivo = true;
        tiempoRestante = 30;
        //*Pendiente, manejo del tiempo
    }
    
    public void terminarTurno(){
        turnoActivo = false;
        tiempoRestante= 0;
        cambiarTurno();
    }
    
    public void cambiarTurno(){
        jugadorEnTurno = (jugadorEnTurno == juego.getJugador1()) ? juego.getJugador2() : juego.getJugador1();
        juego.setJugadorEnTurno(jugadorEnTurno);
        iniciarTurno();
    }
    
    public void reducirTiempo() {
        if (turnoActivo) {
            tiempoRestante--;
            if (tiempoRestante <= 0) {
                terminarTurno();
            }
        }
    }

    public Jugador getJugadorEnTurno() {
        return jugadorEnTurno;
    }

    public void setJugadorEnTurno(Jugador jugadorEnTurno) {
        this.jugadorEnTurno = jugadorEnTurno;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public boolean isTurnoActivo() {
        return turnoActivo;
    }

    public void setTurnoActivo(boolean turnoActivo) {
        this.turnoActivo = turnoActivo;
    }    
}
