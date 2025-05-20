/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import dtos.JugadorDTO;

/**
 *
 * @author Beto_
 */
public class ResConfiguracionRecibida extends Mensajes{
    private JugadorDTO jugador;
    
    public ResConfiguracionRecibida(String comando, JugadorDTO jugador) {
        super(comando);
        this.jugador = jugador;
    }
    
    public JugadorDTO getJugador(){
        
        return this.jugador;
    }
}
