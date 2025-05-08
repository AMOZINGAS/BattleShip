/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import mensajes.Mensajes;
//import mensajes.ResConfigurarPartida;
import mensajes.ResCrearPartida;
//import mensajes.ResIniciarPartida;
//import mensajes.ResPasarTurno;
//import mensajes.ResRegistroJugador;
//import mensajes.ResSolicitarInicio;
import mensajes.ResUnirse;
import mvpJuego.ModeloJuego;
import mvpMenu.ModeloMenu;
//import menuMVC.ModeloMenu;
//import partidaMVC.ModeloJuego;

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
        }

//        if (mensaje instanceof ResRegistroJugador) {
//            if (mensaje.getComando().equals("JUGADOR_REGISTRADO")) {
//                modeloMenu.actualizarJugadores(mensaje);
//                modeloJuego.actualizarJugadores(mensaje);
//            } else if (mensaje.getComando().equals("JUGADOR_NUEVO")) {
//                modeloJuego.actualizarJugadorNuevo(mensaje);
//            }
//        }

//        if (mensaje instanceof ResConfigurarPartida) {
//            ResConfigurarPartida res = (ResConfigurarPartida) mensaje;
//            modeloMenu.notificar(res);
//        }

        if (mensaje instanceof ResUnirse) {
            ResUnirse res = (ResUnirse) mensaje;
            modeloMenu.notificar(res);
        }

//        if (mensaje instanceof ResSolicitarInicio) {
//            ResSolicitarInicio res = (ResSolicitarInicio) mensaje;
//            modeloJuego.notificar(res);
//        }

//        if (mensaje instanceof ResIniciarPartida) {
//            ResIniciarPartida res = (ResIniciarPartida) mensaje;
//            if (res.getComando().equals("PARTIDA_INICIADA")) {
//                modeloJuego.actualizarData(res.getJugadores());
//                modeloJuego.actualizarMazoJugador();
//                modeloJuego.verificarTurno();
//                modeloJuego.actualizarTablero(res.getTableroDTO());
//            }
//        }

//        if (mensaje instanceof ResPasarTurno) {
//            ResPasarTurno res = (ResPasarTurno) mensaje;
////            modeloJuego.actualizarData(res.getJugadores());
//            modeloJuego.avanzarTurno();
//            modeloJuego.actualizarData(res.getJugadores());
//            modeloJuego.actualizarMazoJugador();
//            modeloJuego.verificarTurno();
//            modeloJuego.actualizarTablero(res.getTableroDTO());
//        }
    }

}

