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
public class ReqDisparo extends Mensajes {
    
    private JugadorDTO jugador;
    private CoordenadasDTO coordenadas;
    
    public ReqDisparo(JugadorDTO jugador, CoordenadasDTO coordenadas) {
        super("REALIZAR_DISPARO");
        this.jugador = jugador;
        this.coordenadas = coordenadas;
    }
    
    public JugadorDTO getJugador() {
        return jugador;
    }

    public CoordenadasDTO getCoordenadas(){
        return coordenadas;
    }
    
    
}
