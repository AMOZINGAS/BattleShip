/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serversocket;


import com.google.gson.Gson;
import dtos.JuegoDTO;
import dtos.JugadorDTO;
import mensajes.Mensajes;
import mensajes.ReqDisparo;
import mensajes.ReqUnirse;


/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class ManejadorPeticion {

    private ManejadorCliente clientHandler;
    private Controlador controlador;

    public ManejadorPeticion(ManejadorCliente clientHandler) {
        this.clientHandler = clientHandler;
        this.controlador = Controlador.getInstance();
    }

    public void manejarComando(Mensajes mensaje) {
        if (mensaje == null) {
            System.out.println("mensaje nulo");
            return;
        }

        switch (mensaje.getComando()) {
            case "CREAR_PARTIDA":
                handleCrearPartida(mensaje);
                break;
            case "UNIRSE":
                handleUnirse((ReqUnirse) mensaje);
            default:

        }
    }
    
    private void handleCrearPartida(Mensajes mensaje) {
        controlador.crearPartida(mensaje, clientHandler);
    }

    
    private void handleUnirse(ReqUnirse mensaje) {
        controlador.unirse(mensaje, clientHandler);
    }

    
}
