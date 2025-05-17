/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import entity.Orientacion;
import entity.Disparo;
import capaLogica.excepciones.ServerLogicException;
import entity.Jugador;
import entity.Nave;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Beto_
 */
public class Tablero {
    private final Casilla[][] casillas;
    private List<Nave> naves;
    private Jugador jugador;
    private List<Disparo> disparosRecibidos;

    public Tablero(Jugador jugador) {
        this.jugador = jugador;
        this.naves = new ArrayList<>();
        this.disparosRecibidos = new ArrayList<>();
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
    
    public void colocarNave(Nave nave, Coordenada coordenadaInicial, Orientacion orientacion) throws ServerLogicException{
        //Obtenemos las coordenadas calculadas a partir de la coordenada inicial y la orientación
        List<Coordenada> coordenadas = calcularCoordenadas(coordenadaInicial, orientacion, nave.getTamanio());
        
        //Valida la colocación con las nuevas coordenadas
        validarColocacion(nave, coordenadas);
        
        //For para colocar nave en casillas ya validadas
        for (Coordenada coordenada : coordenadas) {
            Casilla casilla = obtenerCasilla(coordenada);
            casilla.ocupar(nave);
        }
        naves.add(nave);
        nave.setCasillas(obtenerCasillas(coordenadas));
        nave.setOrientacion(orientacion);
    }
    
    private void validarColocacion(Nave nave, List<Coordenada> coordenadas) throws ServerLogicException{
        if(nave == null){
            throw new ServerLogicException("Intento de colocar una nave nula");
        }
        
        if(coordenadas == null){
            throw new ServerLogicException("Intento de nave en coordenadas nulas");
        }
        
        if (coordenadas.size() != nave.getTamanio()) {
            throw new ServerLogicException("El número de coordenadas no coincide con el tamaño de la nave");
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
        
        // Verificamos que las coordenadas estén en linea recta
        if (!esColocacionValida(coordenadas)) {
            throw new ServerLogicException("Las coordenadas no forman una línea recta adyacente");
        }
    }
    
    private boolean esColocacionValida(List<Coordenada> coordenadas){
        if (coordenadas.size() <= 1) {
            return true; //En el caso que sea un barco, osea de tamaño 1
        }
        
        //Calculamos la orientación a partir de las dos primeras coordenadas
        int deltaX = coordenadas.get(1).getX() - coordenadas.get(0).getX();
        int deltaY = coordenadas.get(1).getY() - coordenadas.get(0).getY();
        
        //La orientación es horizontal si deltaY es 0, vertical si deltaX es 0.
        boolean esHorizontal = (deltaY == 0);
        boolean esVertical = (deltaX == 0);
        
        //Si no es ninguno de los dos, osea, no es linea recta
        if(!esHorizontal && !esVertical){
            return false;
        }
        
        for (int i = 0; i < coordenadas.size(); i++) {
            
            //Si es orientación horizontal y la coordenada X no es igual a la primera coordenada Y
            //Osea, que se esta llendo para abajo o arriba cuando no debería por ser horizontal
            if(esHorizontal && coordenadas.get(i).getY() != coordenadas.get(0).getY()){
                return false;
            }
            //Si es orientación vertical y la coordenada X no es igual a la primera coordenada Y
            //Osea, que se esta llendo para los lados cuando no debería por ser vertical
            if(esVertical && coordenadas.get(i).getX() != coordenadas.get(0).getX()){
                return false;
            }
            
            //Si el conteo ya arrebasó una iteración
            if(i > 0){
                //Se obtienen la coordenada X y Y anterior (previous)
                int prevX = coordenadas.get(i-1).getX();
                int prevY = coordenadas.get(i-1).getY();
                
                //Se obtienen la coordenada X y Y actuales (current)
                int currX = coordenadas.get(i).getX();
                int currY = coordenadas.get(i).getY();
                
                //Si es horizontal y hay una diferencia de una casilla
                if(esHorizontal && Math.abs(currX - prevX) != 1){
                    return false;
                }
                //Si es vertical y hay una diferencia de una casilla
                if(esVertical && Math.abs(currY - prevY) != 1){
                    return false;
                }
            }            
        }
        //Si pasó todos los filtros
        return true;
    }
    
    public void cambiarOrientacionNave(Nave nave, Orientacion orientacion, Coordenada coordenada) throws ServerLogicException{
        if (nave == null) {
            throw new ServerLogicException("Nave nula");
        }
        
        if(orientacion != nave.getOrientacion()){
            //Calcula las coordenadas de la nueva orientación y las valida
            List<Coordenada> coordenadas = calcularCoordenadas(coordenada, orientacion, nave.getTamanio());
            validarColocacion(nave, coordenadas);

            //Si la validació no lanzó ninguna excepción, elimina la nave y cambia la orientacion
            eliminarNave(nave);
            jugador.getFlotilla().remove(nave);
            nave.setOrientacion(orientacion);
            
            //Coloca la la nave en una nueva posición
            colocarNave(nave, coordenada, orientacion);
            jugador.getFlotilla().add(nave);
        }
    }
    
    private List<Coordenada> calcularCoordenadas(Coordenada coordenadaInicial, Orientacion orientacion, int tamanio){
        List<Coordenada> coordenadas = new ArrayList<>();
        int x = coordenadaInicial.getX();
        int y = coordenadaInicial.getY();
        
        for(int i = 0; i < tamanio; i++){
            if(orientacion == Orientacion.HORIZONTAL){
                coordenadas.add(new Coordenada(x+i, y));
            }else{
                coordenadas.add(new Coordenada(x, y+i));
            }
        }
        return coordenadas;
    }
    
    private void eliminarNave(Nave nave){
        //Se recorre el arreglo 2D que representa todas las casillas
        //del tablero
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //Si una casilla contiene la nave, se desocupa
                if(casillas[i][j].getNaveOcupante() == nave){
                    casillas[i][j].desocupar();
                }
            }
        }
        //Eliminamos la nave de la lista de naves del tablero y del jugador
        naves.remove(nave);
    }
    
    public boolean estanTodasNavesHundidas(){
        for(Nave nave : naves){
            if(!nave.estaHundida()){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Regresa el resultado del disparo en el tablero enemigo
     * @param coordenada La coordenada disparada
     * @return True si impactó una nave, False en caso contrario
     * @throws ServerLogicException Si ocurre un error recibiendo un disparo en el tablero
     */
    public boolean recibirDisparo(Coordenada coordenada) throws ServerLogicException{
        if(coordenada == null){
            throw new ServerLogicException("Intento de disparo en coordenada nula");
        }
        
        if(coordenadaFueraDeTablero(coordenada)){
            throw new ServerLogicException("Intento de disparo en coordenada fuera del tablero");
        }
        
        //Obtenemos la casilla después de los filtros
        Casilla casilla = obtenerCasilla(coordenada);
        
        if(casilla.estaAtacada()){
            throw new ServerLogicException("Intento de disparo en casilla atacada");
        }
        
        //True si esta había nave, false si no, osea agua o impacto
        return casilla.estaOcupada();
    }
    
    public Nave obtenerNaveEnCasilla(Coordenada coordenada) {
        return casillas[coordenada.getX()][coordenada.getY()].getNaveOcupante();
    }
    
    private boolean coordenadaFueraDeTablero(Coordenada coordenada) {
        boolean fuera = coordenada.getX() < 0 || coordenada.getX() >= 10 || coordenada.getY() < 0 || coordenada.getY() >= 10;
        System.out.println("Fuera: " + coordenada.getX() + ", " + coordenada.getY());
        return fuera;
    }

    public Casilla obtenerCasilla(Coordenada coordenada) {
        return casillas[coordenada.getX()][coordenada.getY()];
    }
    
    private List<Casilla> obtenerCasillas(List<Coordenada> coordenadas) {
        List<Casilla> casillas = new ArrayList<>();
        for (Coordenada coordenada : coordenadas) {
            casillas.add(obtenerCasilla(coordenada));
        }
        if(!casillas.isEmpty()){
            return casillas;
        }else{
            return null;
        }
    }

    public Jugador getJugador() {
        return jugador;
    }

    public List<Disparo> getDisparosRecibidos() {
        return disparosRecibidos;
    }
    
    public void registrarDisparo(Disparo disparo){
        this.disparosRecibidos.add(disparo);
    }

    public List<Nave> getNaves() {
        return naves;
    }

    public void setNaves(List<Nave> naves) {
        this.naves = naves;
    }
}
