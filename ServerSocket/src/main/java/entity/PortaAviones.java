/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import entity.Orientacion;

/**
 *
 * @author Beto_
 */
public class PortaAviones extends Nave{

    public PortaAviones() {
        super(4);
    }

    @Override
    public String getTipo() {
        return "PortaAviones";
    }
    
}
