/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import dtos.CoordenadasDTO;
import dtos.DisparoDTO;
import dtos.JugadorDTO;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class ReqDisparo extends Mensajes {
    
    private final DisparoDTO disparo;
    
    public ReqDisparo(DisparoDTO disparo) {
        super("REALIZAR_DISPARO");
        this.disparo = disparo;
    }  

    public DisparoDTO getDisparo() {
        return disparo;
    }
    
}
