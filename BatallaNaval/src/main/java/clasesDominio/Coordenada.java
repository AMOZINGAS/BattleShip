/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesDominio;

/**
 *
 * @author David Campa
 */
public class Coordenada {
    public int x;
    public int y;
    
    public Coordenada(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getFila(){
        return x;
    }
    
    public int getColumna(){
        return y;
    }
    
}
