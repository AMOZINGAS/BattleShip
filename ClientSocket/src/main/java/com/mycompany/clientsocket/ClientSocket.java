/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clientsocket;

import cliente.Cliente;
import mvpJuego.ModeloJuego;
import mvpJuego.PresentadorJuego;
import mvpJuego.VistaJuego;
import mvpMenu.ModeloMenu;
import mvpMenu.PresentadorMenu;
import mvpMenu.VistaConfiguracion;
import mvpMenu.VistaMenu;
import mvp_strange.FrmSalaDeEspera;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class ClientSocket {

    public static void main(String[] args){
        Cliente cliente = new Cliente();

        ModeloMenu modeloMenu = ModeloMenu.getInstance();
        ModeloJuego modeloJuego = ModeloJuego.getInstance();
        
        modeloMenu.setCliente(cliente);
        modeloJuego.setCliente(cliente);
        
        PresentadorJuego controlJuego = new PresentadorJuego(modeloJuego);
        PresentadorMenu controladorMenu = new PresentadorMenu(modeloMenu);

        VistaMenu vistaMenu = new VistaMenu(controladorMenu);
        VistaConfiguracion vistaConf = new VistaConfiguracion(controladorMenu);
        FrmSalaDeEspera vistaEspera = new FrmSalaDeEspera(controladorMenu);
        VistaJuego vistJuego = new VistaJuego(controlJuego, controladorMenu);
        
        modeloMenu.addObserver(vistaMenu);
        modeloMenu.addObserver(vistaConf);
        modeloMenu.addObserver(vistaEspera);
        
        modeloJuego.addObserver(vistJuego);

        java.awt.EventQueue.invokeLater(() -> {
            vistaMenu.setVisible(true);
        });
    }
}
