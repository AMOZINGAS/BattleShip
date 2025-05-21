/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import mensajes.Mensajes;
import mensajes.ResConfiguracionRecibida;
import mensajes.ResCrearPartida;
import mensajes.ResDisparo;
import mensajes.ResJuegoIniciado;
import mensajes.ResJugadoresListosConfigurado;
import mensajes.ResNaveHundida;
import mensajes.ResTurnoF;
import mensajes.ResRegistrarJugador;
import mensajes.ResTurno;
import mensajes.ResTurnoT;
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
        
        if(mensaje instanceof ResJugadoresListosConfigurado){
            ResJugadoresListosConfigurado res = (ResJugadoresListosConfigurado) mensaje;
            modeloMenu.notificar(res);
            modeloJuego.notificar(res);
            return;
        }
        
        if(mensaje instanceof ResConfiguracionRecibida){
            ResConfiguracionRecibida res = (ResConfiguracionRecibida) mensaje;
            modeloMenu.notificar(res);
            modeloJuego.notificar(res);
            return;
        }
        
        if(mensaje instanceof ResTurno){
            ResTurno res = (ResTurno) mensaje;
            modeloMenu.notificar(res);
            modeloJuego.notificar(res);
            return;
        }
        
        if(mensaje instanceof ResDisparo){
            ResDisparo res = (ResDisparo) mensaje;
            modeloJuego.notificar(res);
            return;
        }
        
        if(mensaje instanceof ResNaveHundida){
            ResNaveHundida res = (ResNaveHundida) mensaje;
            modeloMenu.notificar(res);
            modeloJuego.notificar(res);
            return;
        }
        
        if(mensaje instanceof ResTurnoF){
            ResTurnoF res = (ResTurnoF) mensaje;
            modeloJuego.notificar(mensaje);
            return;
        }
        
        if(mensaje instanceof ResTurnoT){
            ResTurnoT res = (ResTurnoT) mensaje;
            modeloJuego.notificar(mensaje);
            return;
        }
        
        System.out.println("ManejadorRespuesta - No se encontró el mensaje");
    }
}

