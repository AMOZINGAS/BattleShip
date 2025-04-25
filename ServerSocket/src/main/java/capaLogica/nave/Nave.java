/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaLogica.nave;

import capaLogica.ENUMs.EstadoNave;
import capaLogica.ENUMs.Orientacion;
import capaLogica.tablero.Casilla;
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
    
    public Nave(int tamanio){
        this.tamanio = tamanio;
        this.casillas = new ArrayList<>();
        this.estado = EstadoNave.SIN_DAÑOS;
        this.orientacion = Orientacion.VERTICAL;
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
            
            //***Implementar Lógica de verificar GameOver!!
            
        }else{ 
            //Si alguna de las casillas de la nave no ha sido atacada, solo esta averiada
            estado = EstadoNave.AVERIADA;
        }
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
