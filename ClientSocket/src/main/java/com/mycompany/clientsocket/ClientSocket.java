/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clientsocket;

import cliente.Cliente;
import mvpJuego.VistaJuego;
import mvpMenu.ModeloMenu;
import mvpMenu.PresentadorMenu;
import mvpMenu.VistaConfiguracion;
import mvpMenu.VistaMenu;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class ClientSocket {

    public static void main(String[] args){
        Cliente cliente = new Cliente();

        ModeloMenu modeloMenu = ModeloMenu.getInstance();
        modeloMenu.setCliente(cliente);
        PresentadorMenu controladorMenu = new PresentadorMenu(modeloMenu);

        VistaMenu vistaMenu = new VistaMenu(controladorMenu);
        VistaConfiguracion vistaConf = new VistaConfiguracion(controladorMenu);

        modeloMenu.addObserver(vistaMenu);
        modeloMenu.addObserver(vistaConf);

        java.awt.EventQueue.invokeLater(() -> {
            vistaMenu.setVisible(true);
        });
    }
}
