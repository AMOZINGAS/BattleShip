/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import dtos.CoordenadasDTO;
import entity.EstadoNave;
import entity.Orientacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Beto_
 */
public abstract class Nave {
    private int tamanio;
    private Orientacion orientacion;
    private List<Casilla> casillas;
    private EstadoNave estado;
    private Coordenada coordenadaInicial;
    
    public Nave(int tamanio){
        this.tamanio = tamanio;
        this.casillas = new ArrayList<>();
        this.estado = EstadoNave.SIN_DAÑOS;
        this.orientacion = Orientacion.VERTICAL;
        this.coordenadaInicial = coordenadaInicial;
    }
    
    /**
     * Método abstracto para identificar el tipo de nave
     * @return Un String describiendo el tipo de nave. Ej. "Barco".
     */
    public abstract String getTipo();
    
    public void recibirImpacto(){
        //Verifica si el impacto dió en la coordenada de la nave
        boolean hundida = true;
        
        //Verifica cada casilla de la nave
        for(Casilla casilla : casillas){
            if(!casilla.estaAtacada()){
                hundida = false;
                break;
            }
        }
        
        //Si todas las casillas de la nave fueron atacadas, se hunde
        if(hundida){
            estado = EstadoNave.HUNDIDA;
        }else{ 
            //Si alguna de las casillas de la nave no ha sido atacada, solo esta averiada
            estado = EstadoNave.AVERIADA;
        }
    }
    
    public boolean estaHundida(){
        return estado == EstadoNave.HUNDIDA;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public Orientacion getOrientacion() {
        return orientacion;
    }

    public Coordenada getCoordenadaInicial() {
        return coordenadaInicial;
    }

    public void setCoordenadaInicial(Coordenada coordenadaInicial) {
        this.coordenadaInicial = coordenadaInicial;
    }

    
    
    public void setOrientacion(Orientacion orientacion) {
        this.orientacion = orientacion;
    }

    public List<Casilla> getCasillas() {
        return casillas;
    }

    public void setCasillas(List<Casilla> casillas) {
        this.casillas = casillas;
    }

    public EstadoNave getEstado() {
        return estado;
    }

    public void setEstado(EstadoNave estado) {
        this.estado = estado;
    }
    
}
