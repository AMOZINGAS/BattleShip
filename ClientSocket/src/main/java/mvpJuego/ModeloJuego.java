/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvpJuego;

import cliente.Cliente;
import dtos.CasillaDTO;
import dtos.CoordenadasDTO;
import dtos.DisparoDTO;
import dtos.JugadorDTO;
import dtos.NaveDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.Timer;
import mensajes.Mensajes;
import mensajes.ResConfiguracionRecibida;
import mensajes.ResJugadoresListosConfigurado;
import mensajes.ResTurno;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class ModeloJuego extends Observable{
    
    private List<JugadorDTO> jugadores;
    private int indiceJugadorActual;
    private Random random;
    private static ModeloJuego instance;
    private JugadorDTO jugador;
    private Cliente cliente;
    

    public static ModeloJuego getInstance() {
        return instance == null ? (instance = new ModeloJuego()) : instance;
    }
    
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    
    public void obtenerJugador(String nombre){
        
        
        
    }
    
    public void actualizarDisparoEnemigo(){
        
        //?
        
    }
    
    
//    public void pasarTurno() {
//         if(cliente.isConnected()) {
//            cliente.pasarTurno();
//        }
//    }
//    
//    public void tomarFicha() {
//        if(cliente.isConnected()) {
//            cliente.disparar(this.jugador);
//        }
//    }
    
    public JugadorDTO getJugador(){
        return this.jugador;
    }
    
    public void pasarTurno() {
         if(cliente.isConnected()) {
            cliente.pasarTurno();
        }
    }
    
    public void notificar(Mensajes message) {

        System.out.println("Estoy obteniendo un: " + message.getComando());

        switch (message.getComando()) {
            case "JUGADORES_LISTOS":
                setChanged();
                notifyObservers(message);
                break;
            case "CONFIGURACION_RECIBIDA":
                ResConfiguracionRecibida res = (ResConfiguracionRecibida) message;
                this.jugador = res.getJugador();
                setChanged();
                notifyObservers(message);
                break;
            case "AGUA":
                setChanged();
                notifyObservers(message);
                break;
            case "IMPACTO":
                setChanged();
                notifyObservers(message);
                break;
            case "NAVE_HUNDIDA":
                setChanged();
                notifyObservers(message);
                break;
            case "TURNO":
//                ResTurno tur = (ResTurno) message;
                setChanged();
                notifyObservers(message);
                break;
            case "SI_TURNO":
                setChanged();
                notifyObservers(message);
                break;
            case "NO_TURNO":
                setChanged();
                notifyObservers(message);
                break;
            default:
                System.out.println("no llego nada :(");
        }

    }
    
    public String getNombre(){
        
        return jugador.getNombre();
        
    }
    
}
