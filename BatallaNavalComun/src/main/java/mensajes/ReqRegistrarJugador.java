/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import dtos.JugadorDTO;

/**
 *
 * @author PC
 */
public class ReqRegistrarJugador extends Mensajes{
    
    private final JugadorDTO jugador;
    
    public ReqRegistrarJugador(JugadorDTO jugador){
        super("REGISTRAR_JUGADOR");
        this.jugador = jugador;
        
    }

    public JugadorDTO getJugador() {
        return jugador;
    }
    
}
