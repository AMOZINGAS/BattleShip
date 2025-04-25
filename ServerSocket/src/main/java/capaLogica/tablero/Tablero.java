/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaLogica.tablero;

import capaLogica.excepciones.ServerLogicException;
import capaLogica.jugador.Jugador;
import capaLogica.nave.Nave;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Beto_
 */
public class Tablero {
    private Casilla[][] casillas;
    private List<Nave> naves;
    private Jugador jugador;

    public Tablero(Jugador jugador) {
        this.jugador = jugador;
        this.naves = new ArrayList<>();
        this.casillas = new Casilla[10][10];
        inicializarCasillas();
    }
    
    private void inicializarCasillas(){
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                casillas[fila][columna] = new Casilla(new Coordenada(fila, columna));
            }
        }
    }
    
    public void colocarNave(Nave nave, List<Coordenada> coordenadas) throws ServerLogicException{
        if(nave == null){
            throw new ServerLogicException("Intento de colocar una nave nula");
        }
        
        if(coordenadas == null){
            throw new ServerLogicException("Intento de nave en coordenadas nulas");
        }
        
        //For para poder filtrar coordenas o casillas válidas
        for (Coordenada coordenada : coordenadas) {
            if(coordenadaFueraDeTablero(coordenada)){
                throw new ServerLogicException("Intento de colocar de nave en coordenada fuera del tablero");
            }
            
            if(obtenerCasilla(coordenada).estaOcupada()){
                throw new ServerLogicException("Intento de colocar nave en casilla ocupada");
            }
        }
        
        //For para colocar nave en casillas ya validadas
        for (Coordenada coordenada : coordenadas) {
            Casilla casilla = obtenerCasilla(coordenada);
            casilla.ocupar(nave);
        }
        
        
    }
    
    private boolean coordenadaFueraDeTablero(Coordenada coordenada) {
        return coordenada.getX() < 0 || coordenada.getX() >= 10 || coordenada.getY() < 0 || coordenada.getY() >= 10;
    }

    private Casilla obtenerCasilla(Coordenada coordenada) {
        return casillas[coordenada.getX()][coordenada.getY()];
    }
    
    
}
