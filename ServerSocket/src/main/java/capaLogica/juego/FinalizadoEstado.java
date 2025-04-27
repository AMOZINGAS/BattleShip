/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaLogica.juego;

import capaLogica.excepciones.ServerLogicException;
import capaLogica.jugador.Jugador;

/**
 *
 * @author Beto_
 */
public class FinalizadoEstado implements StateJuego{

    @Override
    public void manejarEstado(Juego juego) {
        //1. Determinamos al ganador
        Jugador ganador = null;
        try {
            ganador = juego.getGanador();
        } catch (ServerLogicException e) {
            System.out.println("Error: El juego no ha terminado cuando se debería haber terminado");
            e.printStackTrace();
        }

        // 2. Mostrar los resultados del juego
        if (ganador != null) {
            System.out.println("El juego ha terminado. El ganador es: " + ganador.getNombre() + " !");
            //Tmbn otros detalles si es necesario
        } else {
            //Según yo no debería pasar, pero en alguna aislada posibilidad
            System.out.println("El juego ha terminado en un empate");
        }
    }
}
