/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package entity;

import entity.Orientacion;

/**
 *
 * @author Beto_
 */
public interface INaveFactory {
    Nave crearBarco();
    Nave crearSubmarino();
    Nave crearCrucero();
    Nave crearPortaAviones();
}
