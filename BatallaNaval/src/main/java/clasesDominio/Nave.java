/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesDominio;

import static clasesDominio.EstadoNave.INTACTA;
import static clasesDominio.TipoNave.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author David Campa
 */
public class Nave extends JLabel{
    public int cantidadCasillas;
    public Orientacion orientacion;
    public List<Casilla> casillas;
    public EstadoNave estado;
    public TipoNave tipo;
    
    public Nave (TipoNave tipo, Orientacion orientacion, String string){
        super(string);
        casillas = new ArrayList<>();
        this.tipo = tipo;
        estado = INTACTA;
        this.orientacion = orientacion;
        
        switch(tipo){
            case BARCO -> cantidadCasillas = 1;
            case SUBMARINO -> cantidadCasillas = 2;
            case PORTAVIONES -> cantidadCasillas = 3;
            case CRUCERO -> cantidadCasillas = 4;
        }
    }
    
    // este metodo no es para el funcionamiento del juego, es mas que nada para añadir la informacion de cuales son las casillas en las que está la nave
    public void addCasillas(Casilla casilla){
        casillas.add(casilla);
    }
    
    public List<Casilla> getCasillas(){
        return casillas;
    }
    
    // este metodo es mas que nada informativo por si se requiere en el futuro para verificar si un barco ya esta en alguna casilla
    // se espera que se use en el tablero al momento de poner un barco para verificar y que no choque con otro
    public boolean elBarcoEstaEnEstaCasilla(Casilla casilla){
        if(casillas.contains(casilla)){
            return true;
        } else {
            return false;
        }
    }
    
    public int getCantidadCasillas(){
        return cantidadCasillas;
    }
    
    public Orientacion getOrientacion(){
        return orientacion;
    }
    
    public EstadoNave getEstado(){
        return estado;
    }
    
    public void setEstado(EstadoNave estado){
        this.estado = estado;
    }
    
    /**
    * De las casillas que está ocupando la nave, este metodo regresa una lista de la coordenadas de esas casillas
    * @return Una lista de coordenadas
    * @author David
    */
    public List<Coordenada> getCoordenadas() {
        List<Coordenada> coordenadas = new ArrayList<>();
        for (Casilla c : casillas) {
            coordenadas.add(c.getCoordenadas()); 
        }
        return coordenadas; 
    }
}
