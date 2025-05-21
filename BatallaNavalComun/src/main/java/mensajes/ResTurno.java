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
public class ResTurno extends Mensajes{
        private JugadorDTO jugadorEnTurno;

    public ResTurno(JugadorDTO jugadorEnTurno) {
        super("TURNO");
        this.jugadorEnTurno = jugadorEnTurno;
    }

    public JugadorDTO getJugadorEnTurno() {
        return jugadorEnTurno;
    }
}
