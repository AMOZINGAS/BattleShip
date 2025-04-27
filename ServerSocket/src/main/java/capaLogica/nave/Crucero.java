/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaLogica.nave;

import capaLogica.ENUMs.Orientacion;

/**
 *
 * @author Beto_
 */
public class Crucero extends Nave{

    public Crucero() {
        super(3);
    }

    @Override
    public String getTipo() {
        return "Crucero";
    }
    
}
