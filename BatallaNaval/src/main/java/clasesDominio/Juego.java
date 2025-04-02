/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesDominio;

import java.util.List;

/**
 *
 * @author David Campa
 */
public class Juego {
    public List<Tablero> tableros;  // relación 1..2
    public Jugador turnoActual;
    public EstadoJuego estado;
    public int temporizador;
    
    public void gestionarTurno(){
        
    }
    
}
