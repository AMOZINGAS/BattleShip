/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import entity.Resultado;

/**
 *
 * @author Beto_
 */
public class Disparo {
    private Coordenada coordenada;
    private Resultado resultado;

    public Disparo(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public Disparo(Coordenada coordenada, Resultado resultado) {
        this.coordenada = coordenada;
        this.resultado = resultado;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }
    
}
