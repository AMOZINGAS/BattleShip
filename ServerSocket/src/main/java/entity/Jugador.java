/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import entity.Color;
import entity.Disparo;
import capaLogica.excepciones.ServerLogicException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Beto_
 */
public class Jugador {
    private String nombre;
    private List<Nave> flotilla;
    private List<Disparo> disparos;
    private Color color;
//    private Tablero tableroPropio;
//    private Tablero tableroDisparos;

    public Jugador(String nombre, Color color) {
        this.nombre = nombre;
        this.flotilla = new ArrayList<>();
        this.disparos = new ArrayList<>();
        this.color = color;
//        this.tableroPropio = new Tablero(this);
//        this.tableroDisparos = new Tablero(null);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setFlotilla(List<Nave> flotilla){
        this.flotilla = flotilla;
    }

    public List<Nave> getFlotilla() {
        return flotilla;
    }

    public void agregarNave(Nave nave){
        this.flotilla.add(nave);
    }

    public List<Disparo> getDisparos() {
        return disparos;
    }

    public void agregarDisparo(Disparo disparo) {
//        this.tableroDisparos.registrarDisparo(disparo);
        this.disparos.add(disparo);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

//    public Tablero getTableroPropio() {
//        return tableroPropio;
//    }
//
//    public Tablero getTableroDisparos() {
//        return tableroDisparos;
//    }
}
