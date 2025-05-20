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
    private Juego juego;
    private static ModeloServer instance;

    private ModeloServer() {
        this.juego = Juego.getInstance();
    }

    public static ModeloServer getInstance() {
        return instance == null ? new ModeloServer() : instance;
    }

    public void crearPartida(Mensajes mensaje, ServerSender sender) {
        // lógica y respuesta en Juego
        juego.crearPartida();
        // Guía en consola
        System.out.println("Partida creada");
    }

    public void unirseAPartida(Mensajes mensaje, ServerSender sender) {
        // lógica y respuesta en Juego
        juego.unirse();
        // Guía en consola
        System.out.println("se uniooo!!!");
//        // lógica
//        System.out.println("Jugador unido");
//        // responder
//        Mensajes respuesta = new Mensajes("JUGADOR_UNIDO");
//        sender.enviarMensaje(respuesta);
    }
    
//        public void crearPartida(Juego juego, Mensajes mensaje){
//            
//            juego.crearPartida();
//            
//        }
//        
//        public void unirsePartida(Juego juego, Mensajes mensaje){
//            
//            juego.unirse();
//            
//        }
    
}
