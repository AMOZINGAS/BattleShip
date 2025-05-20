/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import javax.swing.JPanel;

/**
 *
 * @author CISCO
 */
public class CasillaDTO extends JPanel {
    private boolean atacada;
    private CoordenadasDTO coordenada;
    private NaveDTO naveOcupante;

    public CasillaDTO(CoordenadasDTO coordenadas) {
        this.coordenada = coordenadas;
        atacada =false;
    }
    
    public void ocupar(NaveDTO nave) {
        this.naveOcupante = nave;
    }

    public void desocupar() {
        this.naveOcupante = null;
    }

    public boolean estaOcupada() {
        return naveOcupante != null;
    }
    
    //Get y sets
    public boolean estaAtacada() {
        return atacada;
    }

    public CoordenadasDTO getCoordenada() {
        return coordenada;
    }

    public NaveDTO getNaveOcupante() {
        return naveOcupante;
    }

    public void setAtacada(boolean atacada) {
        this.atacada = atacada;
    }

    public void setCoordenada(CoordenadasDTO coordenada) {
        this.coordenada = coordenada;
    }

    public void setNaveOcupante(NaveDTO naveOcupante) {
        this.naveOcupante = naveOcupante;
    }    
}
