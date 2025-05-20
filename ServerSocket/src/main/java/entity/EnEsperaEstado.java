/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import entity.EstadoJuego;
import capaLogica.excepciones.ServerLogicException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Beto_
 */
public class EnEsperaEstado implements StateJuego{

    @Override
    public void manejarEstado(Juego juego) {
        //1. Agregamos los tableros si no han sido creados
        if(juego.getTableroJugador1() == null || juego.getTableroJugador2() == null){
            juego.setTableroJugador1(new Tablero(juego.getJugador1()));
            juego.setTableroJugador2(new Tablero(juego.getJugador2()));
        }
        
        //2. Verificación de las naves de ambos jugadores
        // Si ya estan colocadas se pasa al siguiente estado
        try{
            if (todasNavesColocadas(juego)) {
                juego.setEstado(new EnCursoEstado());
                juego.getInstance();
                System.out.println(juego.getJugadorEnTurno().getNombre() + " empieza.");
            }
        }catch(ServerLogicException sle){
            System.err.println("Error: " + sle.getMessage());
        }
    }
    
    private boolean todasNavesColocadas(Juego juego) throws ServerLogicException {        
        //1. Validación de ambos jugadores existentes
        if(juego.getJugador1() == null || juego.getJugador2() == null){
            return false;
        }
        
        //2. Extraemos la flotilla ingresada
        Tablero tableroJugador1 = juego.getTableroJugador1();
        Tablero tableroJugador2 = juego.getTableroJugador2();
        
        //3. Contamos las naves, tiene que coincidir con el requisito de las reglas del juego
        return (contarNaves(tableroJugador1.getNaves()) || contarNaves(tableroJugador2.getNaves()));
    }
    
    private boolean contarNaves(List<Nave> naves) throws ServerLogicException{
        if(naves.isEmpty() || naves.size() != 5){
            throw new ServerLogicException("11 Naves requeridas. Encontradas: " + naves.size());
        }
        
        int numBarcos =0;
        int numSubmarinos=0;
        int numCruceros=0;
        int numPortaAviones=0;
        for (Nave nave : naves) {
            if (nave instanceof PortaAviones) {
                numPortaAviones++;
            } else if (nave instanceof Crucero) {
                numCruceros++;
            } else if (nave instanceof Submarino) {
                numSubmarinos++;
            } else if (nave instanceof Barco) {
                numBarcos++;
            }
        }
        if (numPortaAviones == 1 && numCruceros == 1 && numSubmarinos == 1 && numBarcos == 2){
            return true;
        }else{
            throw new ServerLogicException("La cantidad de naves colocadas es incorrecta. ");
        }
    }
    
}
