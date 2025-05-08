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
public class NaveFactory implements INaveFactory{

    @Override
    public Nave crearBarco() {
        return new Barco();
    }

    @Override
    public Nave crearSubmarino() {
        return new Submarino();
    }

    @Override
    public Nave crearCrucero() {
        return new Crucero();
    }

    @Override
    public Nave crearPortaAviones() {
        return new PortaAviones();
    }
    
}
