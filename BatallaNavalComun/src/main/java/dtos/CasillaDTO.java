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
    
    CoordenadasDTO coordenadasDTO;

    public CasillaDTO(CoordenadasDTO coordenadasDTO) {
        this.coordenadasDTO = coordenadasDTO;
    }
    
    

    public CoordenadasDTO getCoordenadasDTO() {
        return coordenadasDTO;
    }

    public void setCoordenadasDTO(CoordenadasDTO coordenadasDTO) {
        this.coordenadasDTO = coordenadasDTO;
    }
    
    
    
}
