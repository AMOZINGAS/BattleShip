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
public class ResJugadoresListosConfigurado extends Mensajes{
    
    private List<JugadorDTO> jugadores;
    public ResJugadoresListosConfigurado(String comando, List<JugadorDTO> jugadores){
        super(comando);
        this.jugadores = jugadores;
    }

    
    
    
}
