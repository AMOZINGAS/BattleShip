/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class SinConfiguracion implements StateJuego{
    private static Juego juego;
    
    @Override
    public void manejarEstado(Juego juego) {
        this.juego = juego;
        juego.setEstado(new EnEsperaEstado());
        
    }
}
