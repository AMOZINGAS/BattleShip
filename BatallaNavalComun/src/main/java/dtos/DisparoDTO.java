/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author CISCO
 */
public class DisparoDTO {
    
    CoordenadasDTO coordenadas;
    ResultadoENUM resultado;
    
    public DisparoDTO(){
        
        this.coordenadas = coordenadas;
        this.resultado = resultado;
        
    }
    
    public void setCoordenadas(CoordenadasDTO coordenadas){
        
        this.coordenadas = coordenadas;
        
    }
    
    public void setResultado(ResultadoENUM resultado){
        
        this.resultado = resultado;
        
    }

    public CoordenadasDTO getCoordenadas() {
        return coordenadas;
    }

    public ResultadoENUM getResultado() {
        return resultado;
    }
    
    
    
    
    
}
