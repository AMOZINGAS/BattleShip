/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CISCO
 */
public class JugadorDTO {
    
    private String nombre;
    private List<NaveDTO> flotilla = new ArrayList<>();
    private List<DisparoDTO> disparos = new ArrayList<>();
    private Color color;
//    private Tablero tableroPropio;
//    private Tablero tableroDisparos;

    public JugadorDTO(String nombre, Color color) {
        this.nombre = nombre;
        this.color = color;
//        this.tableroPropio = new Tablero(this);
//        this.tableroDisparos = new Tablero(null);
    }

    public JugadorDTO(){
    }
    
    public void addNave(NaveDTO naveDTO){
        flotilla.add(naveDTO);
        
    }
    public void removeNave(NaveDTO naveDTO){
        flotilla.remove(naveDTO);
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<NaveDTO> getFlotilla() {
        return flotilla;
    }

    public void setFlotilla(List<NaveDTO> flotilla) {
        this.flotilla = flotilla;
    }

    public List<DisparoDTO> getDisparos() {
        return disparos;
    }

    public void setDisparos(List<DisparoDTO> disparos) {
        this.disparos = disparos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
    
}
