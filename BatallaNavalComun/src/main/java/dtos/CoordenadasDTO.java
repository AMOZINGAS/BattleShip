/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author CISCO
 */
public class CoordenadasDTO {
    
    int x;
    int y;
    
    public CoordenadasDTO(int x, int y){
        
        this.x = x;
        this.y = y;
        
    }
    
    public void setCoordenadasX(int x){
        
        this.x = x;
        
    }
    
    public void setCoordenadasY(int y){
        
        this.y = y;
        
    }
    
    public int getCoordenadasX(){
        
        return this.x;
        
    }
    
    public int getCoordenadasY(){
        
        return this.y;
        
    }
    
}
