/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesDominio;

import javax.swing.JPanel;

/**
 *
 * @author David Campa
 */
public class Casilla extends JPanel {
    public boolean atacada;
    public Coordenadas coordenadas;
    public Nave nave;
    
    public Coordenadas getCoordenadas(){
        return coordenadas;
    }
}
