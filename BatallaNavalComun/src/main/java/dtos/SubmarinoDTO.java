/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author CISCO
 */
public class SubmarinoDTO extends NaveDTO{
    
    public SubmarinoDTO(int tamanio, String tipo, int orientacion) {
        super(tamanio, tipo, orientacion);
    }
    public String getTipo() {
        return "Submarino";
    }
    
}
