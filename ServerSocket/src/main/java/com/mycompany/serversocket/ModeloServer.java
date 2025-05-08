/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serversocket;

import entity.Juego;
import entity.SinConfiguracion;
import java.util.Observable;
import mensajes.Mensajes;
import mensajes.ResCrearPartida;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class ModeloServer extends Observable{
    
        public void crearPartida(Juego juego, Mensajes mensaje){
            
            juego.crearPartida();
            
        }
        
        public void unirsePartida(Juego juego, Mensajes mensaje){
            
            juego.unirse();
            
        }
    
}
