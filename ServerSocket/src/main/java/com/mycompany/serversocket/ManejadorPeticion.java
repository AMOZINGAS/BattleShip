/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serversocket;


import capaLogica.excepciones.ServerLogicException;
import com.google.gson.Gson;
import dtos.JuegoDTO;
import dtos.JugadorDTO;
import dtos.NaveConfigDTO;
import dtos.OrientacionENUM;
import entity.Coordenada;
import entity.INaveFactory;
import entity.Juego;
import entity.Jugador;
import entity.Nave;
import entity.NaveFactory;
import entity.Orientacion;
import entity.Tablero;
import java.util.ArrayList;
import java.util.List;
import mensajes.Mensajes;
import mensajes.ReqDisparo;
import mensajes.ReqRegistrarJugadorConfig;
import mensajes.ReqUnirse;
import mensajes.ResConfiguracionRecibida;


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
                break;
            case "REGISTRAR_JUGADOR_CONFIGURACION":
                handleRegistrarJugadorConfig((ReqRegistrarJugadorConfig)(mensaje));
                break;
            default:
                System.out.println("Comando desconocido: " + mensaje.getComando());
        }
    }
    
    private void handleCrearPartida(Mensajes mensaje) {
        controlador.crearPartida(mensaje, clientHandler);
    }
    
    private void handleUnirse(ReqUnirse mensaje) {
        controlador.unirse(mensaje, clientHandler);
    }
    
    private void handleRegistrarJugadorConfig(ReqRegistrarJugadorConfig mensaje) {
        controlador.registrarJugador(mensaje, clientHandler);
    }
    
}
