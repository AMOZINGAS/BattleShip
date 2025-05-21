/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import dtos.JugadorDTO;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class ResDisparo extends Mensajes{
    private JugadorDTO jugadorEnTurno;
    
    public ResDisparo(String comando, JugadorDTO jugadorEnTurno) {
        super(comando);
        this.jugadorEnTurno = jugadorEnTurno;
    }

    public JugadorDTO getJugadorEnTurno() {
        return jugadorEnTurno;
    }    
    
}
