/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

/**
 *
 * @author Beto_
 */
public class ResTurno extends Mensajes{
    private String jugadorEnTurno;

    public ResTurno(String jugadorEnTurno) {
        super("TURNO");
        this.jugadorEnTurno = jugadorEnTurno;
    }

    public String getJugadorEnTurno() {
        return jugadorEnTurno;
    }
}
