/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author CISCO
 */
public class MatrizDTO extends JPanel{
    ArrayList<CasillaDTO> casillas = new ArrayList();
    ArrayList<NaveDTO> naves = new ArrayList();
    JugadorDTO jugador;

    public MatrizDTO(JugadorDTO jugador) {
    this.jugador = this.jugador;
    
    }

    public ArrayList<CasillaDTO> getCasillas() {
        return casillas;
    }

    public ArrayList<NaveDTO> getNaves() {
        return naves;
    }

//    public JugadorDTO getJugador() {
//        return jugador;
//    }

    public void addCasilla(CasillaDTO casilla){
        casillas.add(casilla);
        
    }
    
    public void setCasillas(ArrayList<CasillaDTO> casillas) {
        this.casillas = casillas;
    }

    public void setNaves(ArrayList<NaveDTO> naves) {
        this.naves = naves;
    }

//    public void setJugador(JugadorDTO jugador) {
//        this.jugador = jugador;
//    }
//    
    
    
}
