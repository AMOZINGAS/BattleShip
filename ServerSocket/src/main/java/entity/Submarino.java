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
public class Submarino extends Nave{

    public Submarino() {
        super(2);
    }

    @Override
    public String getTipo() {
        return "Submarino";
    }
    
}
