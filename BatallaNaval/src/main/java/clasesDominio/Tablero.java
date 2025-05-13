/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesDominio;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Esta clase tablero es la que se espera que se replique con los sockets para que sea juego online
 * @author David Campa
 */
public class Tablero extends JPanel{
    public List<Casilla> casillas; // relacion 1..100
    public List<Nave> naves;
    public Jugador jugador;
    
    public Tablero(){
        super();
        
        casillas = new ArrayList<>();
        naves = new ArrayList<>();
    }
    
    public void setCasillas(List<Casilla> casillas){
        this.casillas = casillas;
    }
    
    public void setNaves(List<Nave> naves){
        this.naves = naves;
    }
    
    
    public void addCasillas(Casilla casilla){
        casillas.add(casilla);
    }
    
    public void addNaves(Nave nave){
        naves.add(nave);
    } 
    
}
