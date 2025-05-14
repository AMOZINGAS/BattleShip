/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import dtos.JugadorDTO;
import java.util.List;

/**
 *
 * @author PC
 */
public class ResRegistrarJugador extends Mensajes{
    
    private final List<JugadorDTO> jugadores;
    private final String nombre;
    
    public ResRegistrarJugador(String comando, List<JugadorDTO> jugadores, String nombre){
        super(comando);
        this.jugadores = jugadores;
        this.nombre = nombre;
    }

    public List<JugadorDTO> getJugadores() {
        return jugadores;
    }

    public String getNombre() {
        return nombre;
    }
}
