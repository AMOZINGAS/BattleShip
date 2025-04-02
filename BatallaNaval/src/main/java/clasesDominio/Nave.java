/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesDominio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David Campa
 */
public class Nave {
    public int size;
    public Orientacion orientacion;
    public List<Casilla> casillas;
    public EstadoNave estado;
    
    public void setCoordenadas(){
        
    }
    
    public void cambiarOrientacion(){
        
    }
    
    public int getSize(){
        return size;
    }
    
    public Orientacion getOrientacion(){
        return orientacion;
    }
    
    /**
    * De las casillas que está ocupando la nave, este metodo regresa una lista de la coordenadas de esas casillas
    * @return Una lista de coordenadas
    * @author David
    */
    public List<Coordenadas> getCoordenadas() {
        List<Coordenadas> coordenadas = new ArrayList<>();
        for (Casilla c : casillas) {
            coordenadas.add(c.getCoordenadas()); 
        }
        return coordenadas; 
    }

    
}
