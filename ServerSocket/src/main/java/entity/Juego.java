/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import entity.Disparo;
import capaLogica.excepciones.ServerLogicException;
import dtos.JugadorDTO;
import dtos.NaveConfigDTO;
import dtos.OrientacionENUM;
import java.util.ArrayList;
import java.util.List;
import mensajes.ResUnirse;
import entity.EnEsperaEstado;
import java.util.Map;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import mensajes.ReqRegistrarJugadorConfig;
import mensajes.ResConfiguracionRecibida;
import mensajes.ResCrearPartida;
import mensajes.ResJuegoIniciado;
import mensajes.ResJugadoresListosConfigurado;
import mensajes.ResRegistrarJugador;
import mensajes.ResTurno;

/**
 *
 * @author Beto_
 */
public class Juego extends Observable{
    
    private static Juego instance;
    private List<Jugador> jugadores;
    private List<JugadorDTO> jugadoresDTO;
    private ArrayList<Tablero> tableros;
    private Jugador jugadorEnTurno;
    private StateJuego estado;
    private List<Disparo> disparos;
    private final int temporizador = 30;
    private GestorTurnos gestorTurnos;
    private Map<Jugador, Boolean> jugadoresListos = new ConcurrentHashMap<>();

    public Juego() {
        this.jugadores = new ArrayList<>();
        this.jugadoresDTO = new ArrayList<>();
//        this.tableros = new ArrayList<>();
        this.estado = new SinConfiguracion();
//        this.gestorTurnos = new GestorTurnos(this);
//        this.disparos = new ArrayList<>();
    }
    
    
     public static Juego getInstance() {
        if (instance == null) {
            instance = new Juego();
            System.out.println("Nueva instancia de Juego creada.");
        }
        return instance;
    }
     
     public void jugadorListo(Jugador jugador){
        System.out.println("Controlador: Jugador " + jugador.getNombre() + " esta listo.");
        jugadoresListos.put(jugador, true);

    }
    private boolean isJugadoresListos() {
        if(jugadoresListos.size() <= 1){
            return false;
        }

        for (boolean listo : jugadoresListos.values()) {
            if (!listo) {
                return false; //Si al menos un jugador no está listo, es false
            }
        }
        return true; //Todos los jugadores están listos
    }
     
    
     public void registrarJugador(JugadorDTO jugadorDTO, List<NaveConfigDTO> configuracionNaves){
        if(this.jugadores.size()<2){
            
            Jugador jugador = new Jugador(jugadorDTO.getNombre(), jugadorDTO.getColor());
            if(jugadores.isEmpty()){
                
                jugador.setId(1);
                jugadorDTO.setId(1);
                
            }
            jugador.setId(2);
            jugadorDTO.setId(2);

            System.out.println("Servidor: Recibida configuración de " + jugador.getNombre());
            System.out.println("Jugador: " + jugador);
            System.out.println("Naves tamaño: " + configuracionNaves.size());

        //1. Crear objetos Nave basados en la información recibida.
            if (jugador != null) {
                Tablero tablero = jugador.getTableroPropio();

                if (tablero != null) {
                    INaveFactory factory = new NaveFactory();
                    try {
                        int i=0;
                        for (NaveConfigDTO config : configuracionNaves) {
                            Nave nave = null;
                            String tipoNave = config.getTipo().toLowerCase();

                            if (tipoNave.contains("barco")) {
                                nave = factory.crearBarco();
                            } else if (tipoNave.contains("submarino")) {
                                nave = factory.crearSubmarino();
                            } else if (tipoNave.contains("crucero")) {
                                nave = factory.crearCrucero();
                            } else if (tipoNave.contains("portaaviones")) {
                                nave = factory.crearPortaAviones();
                            } else {
                                System.err.println("Tipo de nave desconocido: " + config.getTipo());
                                continue; //Saltar a la siguiente nave si el tipo no coincide
                            }
                            //2. Colocar las naves en el Tablero del Jugador.
                            Coordenada coordenadaInicial = new Coordenada(config.getCoordenadaInicial().getCoordenadasX(), config.getCoordenadaInicial().getCoordenadasY());
                            tablero.colocarNave(nave, coordenadaInicial, config.getOrientacion() == OrientacionENUM.HORIZONTAL ? Orientacion.HORIZONTAL : Orientacion.VERTICAL);

                            //3. Almacenar la configuración del jugador.
                            jugador.agregarNave(nave);
                            System.out.println(jugador.getFlotilla().get(i).getTipo() + " Añadido. ");
                            i++;
                        }

                        this.jugadores.add(jugador);
                        this.jugadoresDTO.add(jugadorDTO);
                        jugadorListo(jugador);
                        System.out.println("Servidor: Naves de " + jugador.getNombre() + " colocadas en el tablero.");
                        System.out.println("Cantidad de naves creadas: " + jugador.getFlotilla().size());
                        
                        setChanged();
                        notifyObservers(new ResConfiguracionRecibida("CONFIGURACION_RECIBIDA", jugadorDTO));

                        if(isJugadoresListos()){
                            setChanged();
                            notifyObservers(new ResJugadoresListosConfigurado("JUGADORES_LISTOS", jugadoresDTO));
                        }
                        
                    } catch (ServerLogicException e) {
                        System.err.println("Error al colocar naves de " + jugador.getNombre() + ": " + e.getMessage());
        //                clientHandler.sendMessage(new ResError("ERROR_CONFIGURACION", e.getMessage()));
                    }
                } else {
                    System.err.println("Error: No se encontró el tablero para el jugador " + jugador.getNombre());
        //            clientHandler.sendMessage(new ResError("ERROR_CONFIGURACION", "No se encontró el tablero del jugador."));
                }
            } else {
                System.err.println("Error: No se encontró al jugador asociado a la conexión.");
        //        clientHandler.sendMessage(new ResError("ERROR_CONFIGURACION", "Jugador no identificado."));
            }
            
        }
         
     }
    
    public void procesarDisparo(Jugador lanzador, Coordenada coordenada) throws ServerLogicException{
        if (estado instanceof EnCursoEstado) {
        ((EnCursoEstado) estado).manejarDisparo(this, lanzador, coordenada);
        } else {
            throw new ServerLogicException("No se puede procesar el disparo en el estado actual del juego");
        }
    }
    
    
    
    public boolean esGameOver(){
        return tableros.get(0).estanTodasNavesHundidas() || tableros.get(1).estanTodasNavesHundidas();
    }
    
    public Jugador getGanador() throws ServerLogicException{
        if(esGameOver() && estado instanceof FinalizadoEstado){
            return (tableros.get(0).estanTodasNavesHundidas()) ? jugadores.get(0) : jugadores.get(1);
        }else{
            throw new ServerLogicException("El juego aun no ha finalizado");
        }
    }
    
    public void gestionarTurno(){
        gestorTurnos.reducirTiempo();
    }

    public Jugador getJugador1() {
        return jugadores.get(0);
    }

    public synchronized void unirse() {
        System.out.println(this.getEstado());
        if (this.getEstado() instanceof EnEsperaEstado) {
            setChanged();
            ResUnirse res = new ResUnirse("JUGADOR_UNIDO");
//            this.addJugador(new Jugador("", Color.ROJO));
            notifyObservers(res);
        } else {
            setChanged();
            notifyObservers(new ResUnirse("JUGADOR_NO_UNIDO"));
        }
    }
    
    public synchronized void crearPartida(){
        
        if (instance.getEstado() instanceof SinConfiguracion) {
            instance.getEstado().manejarEstado(instance);
            setChanged();
            notifyObservers(new ResCrearPartida("PARTIDA_CREADA"));
        } else {
            setChanged();
            notifyObservers(new ResCrearPartida("PARTIDA_NO_CREADA"));
        }
        
    }
    
    public synchronized void iniciarPartida(){
        if (this.estado instanceof EnEsperaEstado && jugadores.size() == 2) {
            this.estado = new EnCursoEstado();
            this.jugadorEnTurno = determinarJugadorInicial();
            setChanged();
            notifyObservers(new ResJuegoIniciado("JUEGO_INICIADO"));
            setChanged();
            notifyObservers(new ResTurno(jugadorEnTurno.getNombre()));
            System.out.println("Juego: Partida iniciada. Turno de " + jugadorEnTurno.getNombre());
        } else {
            System.out.println("Juego: No se puede iniciar la partida. Estado: " + this.estado + ", Jugadores: " + jugadores.size());
            //Notificar observadores?
        }
    }
    
    public synchronized Jugador determinarJugadorInicial() {
        if (!jugadores.isEmpty()) {
            Random random = new Random();
            jugadorEnTurno = jugadores.get(random.nextInt(jugadores.size()));
            return jugadorEnTurno;
        }
        return null;
    }
    
    public void addJugador(Jugador jugador){
        jugadores.add(jugador);
    }
    
//    public void setJugador1(Jugador jugador1) {
//        this.jugadores.set(0, jugador1);
//    } 
        
    public Jugador getJugador2() {
        return jugadores.get(1);
    }
    
     public void setJugador2(Jugador jugador2) {
        this.jugadores.set(1, jugador2);
    }

    public Jugador getJugadorEnTurno() {
        return jugadorEnTurno;
    }

    public void setJugadorEnTurno(Jugador jugadorEnTurno) {
        this.jugadorEnTurno = jugadorEnTurno;
    }

    public StateJuego getEstado() {
        return estado;
    }

    public void setEstado(StateJuego estado) {
        this.estado = estado;
    }

    public Tablero getTableroJugador1() {
        return tableros.get(0);
    }

    public void setTableroJugador1(Tablero tableroJugador1) {
        this.tableros.set(0, tableroJugador1);
    }

    public Tablero getTableroJugador2() {
        return tableros.get(1);
    }

    public void setTableroJugador2(Tablero tableroJugador2) {
        this.tableros.set(1, tableroJugador2);
    }

    public List<Disparo> getDisparos() {
        return disparos;
    }

    public GestorTurnos getGestorTurnos() {
        return gestorTurnos;
    }

    public void setGestorTurnos(GestorTurnos gestorTurnos) {
        this.gestorTurnos = gestorTurnos;
    }
}
