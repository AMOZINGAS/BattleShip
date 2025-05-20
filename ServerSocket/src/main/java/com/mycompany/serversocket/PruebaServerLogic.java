/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.serversocket;

import entity.Color;
import entity.Orientacion;
import capaLogica.excepciones.ServerLogicException;
import entity.EnCursoEstado;
import entity.FinalizadoEstado;
import entity.Juego;
import entity.Jugador;
import entity.Nave;
import entity.NaveFactory;
import entity.Casilla;
import entity.Coordenada;
import entity.Tablero;
import java.util.Scanner;

/**
 *
 * @author Beto_
 */
public class PruebaServerLogic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        try{
//            // 1.Cremoas a los jugadores
//            Jugador jugador1 = new Jugador("Jugador 1",Color.ROJO);
//            Jugador jugador2 = new Jugador("Jugador 2", Color.AZUL);
//
//            //2.Creamos el juego
//            Juego juego = new Juego(jugador1, jugador2, new Tablero(jugador1), new Tablero(jugador2));
//
//            //3. Colocamos las naves de los jugadores
//            //Nomas por la prueba se crean directo las naves en consola,en la GUI será una por una pudiendo rotarlas
//            Tablero tableroJugador1 = juego.getTableroJugador1();
//            Tablero tableroJugador2 = juego.getTableroJugador2();
//
//            //Creamos naves para el jugador 1
//            NaveFactory naveFactory = new NaveFactory();
//            Nave portaAviones1 = naveFactory.crearPortaAviones();
//            Nave crucero1 = naveFactory.crearCrucero();
//            Nave submarino1 = naveFactory.crearSubmarino();
//            Nave barco1_1 = naveFactory.crearBarco();
//            Nave barco2_1 = naveFactory.crearBarco();
//
//            //Creamos naves para el jugador 2
//            Nave portaAviones2 = naveFactory.crearPortaAviones();
//            Nave crucero2 = naveFactory.crearCrucero();
//            Nave submarino2 = naveFactory.crearSubmarino();
//            Nave barco1_2 = naveFactory.crearBarco();
//            Nave barco2_2 = naveFactory.crearBarco();
//
//            jugador1.agregarNave(portaAviones1);
//            jugador1.agregarNave(crucero1);
//            jugador1.agregarNave(submarino1);
//            jugador1.agregarNave(barco1_1);
//            jugador1.agregarNave(barco2_1);
//
//            jugador2.agregarNave(portaAviones2);
//            jugador2.agregarNave(crucero2);
//            jugador2.agregarNave(submarino2);
//            jugador2.agregarNave(barco1_2);
//            jugador2.agregarNave(barco2_2);
//
//            //Colocamos naves en el tablero del jugador 1
//            tableroJugador1.colocarNave(portaAviones1, new Coordenada(0, 0), Orientacion.VERTICAL);
//            tableroJugador1.colocarNave(crucero1, new Coordenada(2, 0), Orientacion.VERTICAL);
//            tableroJugador1.colocarNave(submarino1, new Coordenada(4, 0), Orientacion.VERTICAL);
//            tableroJugador1.colocarNave(barco1_1, new Coordenada(6, 0), Orientacion.VERTICAL);
//            tableroJugador1.colocarNave(barco2_1, new Coordenada(8, 0), Orientacion.VERTICAL);
//
//            //Colocamos las naves en el tablero del jugador 2
//            tableroJugador2.colocarNave(portaAviones2, new Coordenada(0, 5), Orientacion.VERTICAL);
//            tableroJugador2.colocarNave(crucero2, new Coordenada(2, 5), Orientacion.VERTICAL);
//            tableroJugador2.colocarNave(submarino2, new Coordenada(4, 5), Orientacion.VERTICAL);
//            tableroJugador2.colocarNave(barco1_2, new Coordenada(6, 5), Orientacion.VERTICAL);
//            tableroJugador2.colocarNave(barco2_2, new Coordenada(8, 5), Orientacion.VERTICAL);
//
//            juego.setTableroJugador1(tableroJugador1);
//            juego.setTableroJugador2(tableroJugador2);
//
//            //4. Iniciar el juego
//            juego.iniciarJuego();
//
//            //5. Jugar el juego en la consola
//            Scanner scanner = new Scanner(System.in);
//            while (juego.getEstado() instanceof EnCursoEstado) {
//                System.out.println("\n" + juego.getJugadorEnTurno().getNombre() + ", es tu turno.");
//                System.out.println("Tablero del enemigo:");
//                imprimirTablero(juego.getJugadorEnTurno() == jugador1 ? tableroJugador2 : tableroJugador1);
//
//                System.out.print("Ingresa la coordenada X del disparo: ");
//                int x = scanner.nextInt();
//                System.out.print("Ingresa la coordenada Y del disparo: ");
//                int y = scanner.nextInt();
//                Coordenada coordenada = new Coordenada(x, y);
//
//                try {
//                    juego.procesarDisparo(juego.getJugadorEnTurno(), coordenada);
//                } catch (ServerLogicException e) {
//                    System.out.println("Error: " + e.getMessage());
//                }
//            }
//
//            // 6. Mostrar el resultado del juego
//            System.out.println("\nEl juego ha terminado.");
//            System.out.println("Ganador: " + juego.getGanador().getNombre());
//            System.out.println("Tablero final del Jugador 1:");
//            imprimirTablero(tableroJugador1);
//            System.out.println("Tablero final del Jugador 2:");
//            imprimirTablero(tableroJugador2);
//
//        } catch (ServerLogicException e) {
//            System.out.println("Error del juego: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    //Método para imprimir el tablero en la consola
//    private static void imprimirTablero(Tablero tablero) {
//        System.out.print("  ");
//        for (int i = 0; i < 10; i++) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < 10; i++) {
//            System.out.print(i + " ");
//            for (int j = 0; j < 10; j++) {
//                Casilla casilla = tablero.obtenerCasilla(new Coordenada(j, i));
//                if (casilla.estaAtacada()) {
//                    if (casilla.estaOcupada()) {
//                        System.out.print("X "); // Impacto en nave
//                    } else {
//                        System.out.print("O "); // Agua
//                    }
//                } else {
//                    if (casilla.estaOcupada()) {
//                        System.out.print("N "); // Nave sin tocar
//                    }
//                    else{
//                         System.out.print("- "); // Vacía
//                    }
//                }
//            }
//            System.out.println();
//        }
//    }
    }
}
