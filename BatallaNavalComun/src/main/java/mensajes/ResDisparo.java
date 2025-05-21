/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import dtos.CoordenadasDTO;
import dtos.JugadorDTO;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class ResDisparo extends Mensajes{
    private JugadorDTO jugadorEnTurno;
    private CoordenadasDTO coordenada;
    public ResDisparo(String comando, JugadorDTO jugadorEnTurno, CoordenadasDTO coordenada) {
        super(comando);
        this.coordenada = coordenada;
        this.jugadorEnTurno = jugadorEnTurno;
    }

    public JugadorDTO getJugadorEnTurno() {
        return jugadorEnTurno;
    }    
    
    public CoordenadasDTO getCoordendadDelAtaque(){
        return this.coordenada;
    }
    
}
