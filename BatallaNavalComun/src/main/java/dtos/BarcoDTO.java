/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import javax.swing.JLabel;

/**
 *
 * @author CISCO
 */
public class BarcoDTO extends NaveDTO{

    public BarcoDTO(int tamanio, String tipo, int orientacion) {
        super(tamanio, tipo, orientacion);
    }
    public String getTipo() {
        return "Barco";
    }
}
