/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import entity.Resultado;
import entity.Disparo;
import capaLogica.excepciones.ServerLogicException;

/**
 *
 * @author Beto_
 */
public class EnCursoEstado implements StateJuego{

    @Override
    public void manejarEstado(Juego juego) {
        //Verificar si acabó el juego para delegar el siguiente estado
        if(juego.esGameOver()){
            juego.setEstado(new FinalizadoEstado());
        }
    }
    
    public void manejarDisparo(Juego juego, Jugador lanzador, Coordenada coordenada) throws ServerLogicException{
        //1. Validar el disparo
        if (coordenada == null) {
            throw new ServerLogicException("Intento de disparo en coordenada nula");
        }
        if (coordenadaFueraDeTablero(coordenada)) {
            throw new ServerLogicException("Intento de disparo en coordenada fuera del tablero");
        }
        if (lanzador != juego.getJugadorEnTurno()) {
            throw new ServerLogicException("Intento de disparo de jugador no en turno");
        }
        
        //2. Procesar el disparo
        System.out.println("Esperando resultado del disparo...");
        Tablero tableroObjetivo = (lanzador == juego.getJugador1()) ? juego.getTableroJugador2() : juego.getTableroJugador1();
        Disparo disparo = new Disparo(coordenada);
        Casilla casillaImpactada = tableroObjetivo.obtenerCasilla(coordenada);
        boolean impacto = tableroObjetivo.recibirDisparo(coordenada);
        casillaImpactada.atacar();

        if (impacto) {
            disparo.setResultado(Resultado.IMPACTO);
            System.out.println("El disparo a impactado!");
        } else {
            disparo.setResultado(Resultado.AGUA);
            System.out.println("El disparo dio al agua.");
            cambiarTurno(juego);
        }
        
        //3. Registramos el disparo en el tablero enemigo y en el jugador en turno (dentro de su metodo lo asigna a su tablero de disparos)
        lanzador.agregarDisparo(disparo);
        tableroObjetivo.registrarDisparo(disparo);
        juego.getDisparos().add(disparo); //este esta opcional pero nos sirve para el resumen de partida tmbn

        //4. Verificar si el juego ha terminado
        this.manejarEstado(juego);
    }
    
    private void cambiarTurno(Juego juego) {
        juego.setJugadorEnTurno((juego.getJugadorEnTurno() == juego.getJugador1()) ? juego.getJugador2() : juego.getJugador1());
        juego.getGestorTurnos().terminarTurno();
    }
    
    private boolean coordenadaFueraDeTablero(Coordenada coordenada) {
        return coordenada.getX() < 0 || coordenada.getX() >= 10 || coordenada.getY() < 0 || coordenada.getY() >= 10;
    }
}
