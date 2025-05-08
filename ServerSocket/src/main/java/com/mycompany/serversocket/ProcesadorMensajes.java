/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serversocket;

import mensajes.ManejadorMensajes;
import mensajes.Mensajes;
import mensajes.ManejadorMensajes;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class ProcesadorMensajes {

    private ManejadorCliente manejadorCliente;
    private ManejadorPeticion manejadorComando;

    public ProcesadorMensajes(ManejadorCliente clientHandler) {
        this.manejadorCliente = clientHandler;
        this.manejadorComando = new ManejadorPeticion(clientHandler);
    }

    public void procesarMensaje(String inputLine) {
        try {
            System.out.println(inputLine);
            Mensajes mensaje = ManejadorMensajes.fromJson(inputLine);
            
            System.out.println("SErver: se recibio la peticion " + mensaje.getComando());
            manejadorComando.manejarComando(mensaje);

        } catch (Exception e) {
            System.err.println("Error procesando mensaje: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
