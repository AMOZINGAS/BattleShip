/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author Beto_
 */
public class NaveConfigDTO {
    private String tipo;
    private CoordenadasDTO coordenadaInicial;
    private OrientacionENUM orientacion;

    public NaveConfigDTO(String tipo, CoordenadasDTO coordenadaInicial, OrientacionENUM orientacion) {
        this.tipo = tipo;
        this.coordenadaInicial = coordenadaInicial;
        this.orientacion = orientacion;
    }

    // Getters para los atributos
    public String getTipo() {
        return tipo;
    }

    public CoordenadasDTO getCoordenadaInicial() {
        return coordenadaInicial;
    }

    public OrientacionENUM getOrientacion() {
        return orientacion;
    }

    @Override
    public String toString() {
        return "NaveConfigDTO{" + "tipo=" + tipo + ", coordenadaInicial=" + coordenadaInicial + ", orientacion=" + orientacion + '}';
    }
}
