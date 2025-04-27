/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaLogica.tablero;

import capaLogica.nave.Nave;

/**
 *
 * @author Beto_
 */
public class Casilla {
    private boolean atacada;
    private Coordenada coordenada;
    private Nave naveOcupante;

    public Casilla(Coordenada coordenadas) {
        this.coordenada = coordenadas;
        atacada =false;
    }
    
    public void ocupar(Nave nave) {
        this.naveOcupante = nave;
        //implementar logica de nave
    }

    public void desocupar() {
        this.naveOcupante = null;
        //imlpementar logica de nave
    }

    public boolean estaOcupada() {
        return naveOcupante != null;
    }
    
    //Get y sets
    public boolean estaAtacada() {
        return atacada;
    }

    public void atacar() {
        atacada = true;
        if(estaOcupada()){
            naveOcupante.recibirImpacto();
            if(naveOcupante.estaHundida()){
                System.out.println(naveOcupante.getTipo() + " hundido!!");
            }else{
                System.out.println(naveOcupante.getTipo() + "averiado!");
            }
        }
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public Nave getNaveOcupante() {
        return naveOcupante;
    }
    
    @Override
    public String toString() {
        return "Casilla{" + "atacada=" + atacada + ", coordenada=" + coordenada + ", naveOcupada=" + naveOcupante + '}';
    }
}
