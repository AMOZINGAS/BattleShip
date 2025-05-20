/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import dtos.JugadorDTO;
import dtos.NaveConfigDTO;
import dtos.NaveDTO;
import java.util.List;

/**
 *
 * @author PC
 */
public class ReqRegistrarJugadorConfig extends Mensajes{
    
    private final JugadorDTO jugador;
    List<NaveConfigDTO> configNaves; 
    
    public ReqRegistrarJugadorConfig(JugadorDTO jugador, List<NaveConfigDTO> configNaves){
        super("REGISTRAR_JUGADOR_CONFIGURACION");
        this.jugador = jugador;
        this.configNaves = configNaves;        
    }

    public JugadorDTO getJugador() {
        return jugador;
    }

    public List<NaveConfigDTO> getConfigNaves() {
        return configNaves;
    }
    
}
