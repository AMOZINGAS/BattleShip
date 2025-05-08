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
//            case "REALIZAR_DISPARO":
//                handleRealizarDisparo((ReqDisparo) mensaje);
//                break;
//            case "REGISTRAR_JUGADOR":
//                handleRegistrarJugador((ReqRegistroJugador) mensaje);
//                break;
            case "UNIRSE":
                handleUnirse((ReqUnirse) mensaje);
//                break;
//            case "CONFIGURAR_PARTIDA":
//                handleConfigurarPartida((ReqConfigurarPartida) mensaje);
//                break;
//            case "SOLICITAR_INICIO":
//                handleSolicitarInicio((ReqSolicitarInicio) mensaje);
//                break;
//            case "RESPONDER_SOLICITUD_INICIO":
//                handleResponderSolicitudInicio((ReqResponderSolicitudInicio) mensaje);
//                break;
//            case "PASAR_TURNO":
//                handlePasarTurno((ReqPasarTurno) mensaje);
//                break;
//            case "TOMAR_FICHA":
//                handleTomarFicha((ReqTomarFicha) mensaje);
            default:

        }
    }
    
//    public void handleTomarFicha(Mensajes mensaje) {
//        controlador.tomarFicha(mensaje, clientHandler);
//    }
//    
//    public void handleRealizarDisparo(Mensajes mensaje){
//        
//        controlador.realizarDisparo(mensaje, clientHandler);
//        
//    }
//    
//    public void handlePasarTurno(Mensajes mensaje) {
//        controlador.pasarTurno(mensaje, clientHandler);
//    }
//    
//    private void handleResponderSolicitudInicio(Mensajes mensaje) {
//        controlador.responderSolicitudInicio(mensaje, clientHandler);
//    }
//    
//    private void handleSolicitarInicio(Mensajes mensaje) {
//        controlador.solicitarInicio(mensaje, clientHandler);
//    }
    
    private void handleCrearPartida(Mensajes mensaje) {
        controlador.crearPartida(mensaje, clientHandler);
    }

//    private void handleConfigurarPartida(ReqConfigurarPartida reqConfigurarPartida) {
//        controlador.triggerConfiguracionPartida(clientHandler, reqConfigurarPartida);
//    }
//    
//    private void handleRegistrarJugador(ReqRegistroJugador mensaje) {
//        controlador.registrarJugador(mensaje, clientHandler);
//    }
    
    private void handleUnirse(ReqUnirse mensaje) {
        controlador.unirse(mensaje, clientHandler);
    }

    
}
