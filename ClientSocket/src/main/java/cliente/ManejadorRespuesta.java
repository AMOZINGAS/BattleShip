/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import mensajes.Mensajes;
import mensajes.ResConfiguracionRecibida;
import mensajes.ResCrearPartida;
import mensajes.ResRegistrarJugador;
import mensajes.ResUnirse;
import mvpJuego.ModeloJuego;
import mvpMenu.ModeloMenu;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class ManejadorRespuesta {

    private ModeloMenu modeloMenu;
    private ModeloJuego modeloJuego;

    ManejadorRespuesta() {
        modeloMenu = ModeloMenu.getInstance();
        modeloJuego = ModeloJuego.getInstance();
    }

    protected void handleResponse(Mensajes mensaje) {
        if (mensaje instanceof ResCrearPartida) {
            ResCrearPartida res = (ResCrearPartida) mensaje;
            modeloMenu.notificar(res);
            return;
        }
        
        if (mensaje instanceof ResUnirse) {
            ResUnirse res = (ResUnirse) mensaje;
            modeloMenu.notificar(res);
            return;
        }
        
        if(mensaje instanceof ResRegistrarJugador){
            ResRegistrarJugador res = (ResRegistrarJugador) mensaje;
            modeloMenu.notificar(res);
            return;
        }
        
        if(mensaje instanceof ResConfiguracionRecibida){
            ResConfiguracionRecibida res = (ResConfiguracionRecibida) mensaje;
            modeloMenu.notificar(res);
            return;
        }
        
        System.out.println("ManejadorRespuesta - No se encontró el mensaje");
    }
}

