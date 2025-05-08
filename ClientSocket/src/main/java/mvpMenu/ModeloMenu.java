/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvpMenu;

import cliente.Cliente;
import java.util.Observable;
import mensajes.ResUnirse;
import mvpJuego.ModeloJuego;
import mensajes.Mensajes;

/**
 *
 * @author PC
 */
public class ModeloMenu extends Observable{
    
    private static ModeloMenu instance;
    private Cliente cliente;
    
    private ModeloMenu() {
        
    }

     private void conectar(int puerto) {
        try {
            this.cliente.connectToServer(puerto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
     
    public void crearPartida(int puerto) {
        conectar(puerto);
        if (cliente.isConnected()) {
            cliente.crearPartida();
        } else {
            System.out.println("No conectado");
        }
    }
    
    public void unirseAPartida(int puerto) {
        conectar(puerto);
        if (cliente.isConnected()) {
            cliente.unirse();
        }
    }
    
     public void notificar(Mensajes message) {

        System.out.println("Estoy obteniendo un: " + message.getComando());

        switch (message.getComando()) {
            case "PARTIDA_CREADA":
                setChanged();
                notifyObservers(message);
                break;
            case "PARTIDA_NO_CREADA":
                setChanged();
                notifyObservers(message);
                break;
            case "JUGADOR_UNIDO":
                setChanged();
                notifyObservers(message);
                break;
            case "JUGADOR_NO_UNIDO":
                setChanged();
                notifyObservers(message);
                break;
            default:
                System.out.println("no llego nada :(");
        }

    }
    
    public static ModeloMenu getInstance() {
        return instance == null ? (instance = new ModeloMenu()) : instance;
    }
    
    public void actualizarDisparoEnemigo(){
        
        
        
    }
}
