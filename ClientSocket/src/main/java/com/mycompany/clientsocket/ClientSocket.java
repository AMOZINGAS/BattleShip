/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clientsocket;

import cliente.Cliente;
import mvpJuego.VistaJuego;
import mvpMenu.ModeloMenu;
import mvpMenu.PresentadorMenu;
import mvpMenu.VistaMenu;

/**
 *
 * @author PC
 */
public class ClientSocket {

    public static void main(String[] args){
        Cliente cliente = new Cliente();

        // Modelo y Controlador de menuMVC
    //        ModeloMenu modeloMenu = ModeloMenu.getInstance(cliente);
    //        ControladorMenu controladorMenu = new ControladorMenu(modeloMenu);
        ModeloMenu modeloMenu = ModeloMenu.getInstance();
        modeloMenu.setCliente(cliente);
        PresentadorMenu controladorMenu = new PresentadorMenu(modeloMenu);

        // Modelo y Controlador de JuegoMVC
        //ModeloJuego modeloJuego = ModeloJuego.getInstance();
        //ControladorJuego controladorJuego = new ControladorJuego(modeloJuego);
        //Crear vistas y agregar controladores
        VistaMenu vistaUnirseCrear = new VistaMenu(controladorMenu);

        //VistaJuego vistaJuego = new VistaJuego(controladorJuego);
        //Se agregan los observadores
        modeloMenu.addObserver(vistaUnirseCrear);

        //modeloJuego.addObserver(vistaJuego);
        //Mostramos la primera vista
        java.awt.EventQueue.invokeLater(() -> {
            vistaUnirseCrear.setVisible(true);
        });
    }
}
