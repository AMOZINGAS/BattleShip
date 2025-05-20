/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvpJuego;

import dtos.JugadorDTO;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class PresentadorJuego {
    
    private static ModeloJuego modeloJuego;
    
    public PresentadorJuego(ModeloJuego modeloJuego){
        
        this.modeloJuego = ModeloJuego.getInstance();
    }
    
    public String getNombre(){
        
        return modeloJuego.getNombre();   
        
    }
    
    public JugadorDTO getJugador(){
        return modeloJuego.getJugador();
    }
    
}
