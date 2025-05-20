/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author CISCO
 */
public abstract class NaveDTO extends JLabel{
    private int tamanio;
    private OrientacionENUM orientacion;
    private List<CasillaDTO> casillas;
    private CoordenadasDTO coordenadaInicial;
    private EstadoNaveENUM estado;
    private String nombre;
    
    public NaveDTO(int tamanio, String nombre, int orientacion){
        super(nombre, orientacion);
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.casillas = new ArrayList<>();
        this.estado = EstadoNaveENUM.SIN_DANOS;
        this.orientacion = OrientacionENUM.VERTICAL;
    }
    
    public NaveDTO(CoordenadasDTO coordenadaInicial){
        this.coordenadaInicial = coordenadaInicial;
        
    }
    
    /**
     * Método abstracto para identificar el tipo de nave
     * @return Un String describiendo el tipo de nave. Ej. "Barco".
     */
    public abstract String getTipo();
    
    public boolean estaHundida(){
        return estado == EstadoNaveENUM.HUNDIDA;
    }

    public CoordenadasDTO getCoordenadaInicial() {
        return coordenadaInicial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    

    public void setCoordenadaInicial(CoordenadasDTO coordenadaInicial) {
        this.coordenadaInicial = coordenadaInicial;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public OrientacionENUM getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(OrientacionENUM orientacion) {
        this.orientacion = orientacion;
    }

    public List<CasillaDTO> getCasillas() {
        return casillas;
    }

    public void setCasillas(List<CasillaDTO> casillas) {
        this.casillas = casillas;
    }

    public EstadoNaveENUM getEstado() {
        return estado;
    }

    public void setEstado(EstadoNaveENUM estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "NaveDTO{" + "coordenadaInicial=" + coordenadaInicial + '}';
    }
    
    
}
