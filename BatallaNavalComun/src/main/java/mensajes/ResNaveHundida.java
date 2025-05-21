/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import dtos.CoordenadasDTO;
import java.util.List;

/**
 *
 * @author Beto_
 */
public class ResNaveHundida extends Mensajes{
    private List<CoordenadasDTO> coordenadas;
    
    public ResNaveHundida(List<CoordenadasDTO> coordenadas) {
        super("NAVE_HUNDIDA");
        this.coordenadas = coordenadas;
    }

    public List<CoordenadasDTO> getCoordenadas() {
        return coordenadas;
    }
}
