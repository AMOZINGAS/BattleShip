/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvpJuego;

import java.util.Observable;

/**
 *
 * @author PC
 */
public class ModeloJuego extends Observable{
    
    private static ModeloJuego instance;
    
    private ModeloJuego() {

    }

    public static ModeloJuego getInstance() {
        return instance == null ? (instance = new ModeloJuego()) : instance;
    }
    
    public void actualizarDisparoEnemigo(){
        
        
        
    }
    
}
