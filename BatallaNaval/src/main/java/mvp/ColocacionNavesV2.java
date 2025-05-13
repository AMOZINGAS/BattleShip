package mvp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import clasesDominio.Casilla;
import clasesDominio.Coordenada;
import clasesDominio.Nave;
import clasesDominio.Orientacion;
import static clasesDominio.Orientacion.*;
import clasesDominio.Tablero;
import static clasesDominio.TipoNave.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author CISCO
 */
public class ColocacionNavesV2 extends javax.swing.JFrame {

    private static final int TAM = 20; // Tamaño de cada casilla en píxeles
    private static final int filas = 30;
    private static final int columnas = 30;

    public ColocacionNavesV2() {
        setTitle("Coloca tus naves");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // para las naves
        panelConNaves = new JPanel(); //creamos un panel para poder poner ahi las naves que son jlabels
        panelConNaves.setPreferredSize(new Dimension(400, 80)); // altura fija para que se vea el panel, si no, no se ve
        Nave1 = new Nave(BARCO, VERTICAL, "Nave 1");
        Nave2 = new Nave(SUBMARINO, HORIZONTAL, "Nave 2");
        Nave3 = new Nave(PORTAVIONES, VERTICAL, "Nave 3");
        Nave4 = new Nave(CRUCERO, HORIZONTAL, "Nave 4");
        Nave1.setBounds(10, 10, 60, 20);
        Nave2.setBounds(100, 10, 60, 20);
        Nave3.setBounds(190, 10, 60, 20);
        Nave4.setBounds(280, 10, 60, 20);
        hacerArrastrable(Nave1); // las hacemos que se puedan arrastrar con el raton 
        hacerArrastrable(Nave2);
        hacerArrastrable(Nave3);
        hacerArrastrable(Nave4);
        panelConNaves.add(Nave1); // añadimos las naves al panel
        panelConNaves.add(Nave2);
        panelConNaves.add(Nave3);
        panelConNaves.add(Nave4);
        
        add(panelConNaves, BorderLayout.NORTH); //añadimos el panel al frame ColocacionNavesV2
        
        inicializarTablero(); // creamos el tablero
        add(tablero, BorderLayout.CENTER); // añadimos el tablero al frame

        // para el tamaño de la ventana
        int anchoVentana = columnas * TAM + 50;
        int altoVentana = filas * TAM + 70;
        setSize(anchoVentana, altoVentana);
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    // metodo para crear o inicializar el Tablero
    private void inicializarTablero() {
        tablero = new Tablero();
        tablero.setLayout(new GridLayout(filas, columnas));

        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                Casilla celda = new Casilla(new Coordenada(fila, col)); // casilla extiende de jpanel
                
                celda.setPreferredSize(new Dimension(TAM, TAM)); // para el tama;o de la casilla
                celda.setBackground(Color.WHITE); // para el color de la casilla 
                celda.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // creamos lineas negras en las casillas
                
                int f = fila;
                int c = col;

                celda.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) { System.out.println("Clic en Fila: " + f + ", Columna: " + c);}});
                    tablero.add(celda);
                    tablero.addCasillas(celda); 
            }
        }
    }

    // metodo para hacer que los JLabels de las naves se puedan arrastrar
    private void hacerArrastrable(JLabel label) {
        Point puntoInicial = new Point();

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                puntoInicial.setLocation(e.getPoint());
            }
        });
        
        label.addMouseMotionListener(new MouseMotionAdapter() { // para que los jlabels de las nave se puedan arrastrar
            @Override
            public void mouseDragged(MouseEvent e) {
                Point puntoActual = label.getLocation();
                int x = puntoActual.x + e.getX() - puntoInicial.x;
                int y = puntoActual.y + e.getY() - puntoInicial.y;
                label.setLocation(x, y);
            }
        });
        
       
        label.addMouseListener(new MouseAdapter() { // para que se pueda soltar un barco en la casilla
            @Override
            public void mousePressed(MouseEvent e) {
                puntoInicial.setLocation(e.getPoint());
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                Point puntoFinal = SwingUtilities.convertPoint(label, e.getPoint(), tablero);
                Component comp = tablero.getComponentAt(puntoFinal);
                if (comp instanceof Casilla) {
                    Casilla casillaInicial = (Casilla) comp;
                    Nave nave = (Nave) label;

                    Coordenada inicio = casillaInicial.getCoordenadas();
                    int fila = inicio.getFila();
                    int columna = inicio.getColumna();

                    // Determinar dirección
                    int dx = 0, dy = 0;
                    if (nave.getOrientacion() == Orientacion.HORIZONTAL) {
                        dx = 0;
                        dy = 1;
                    } else { // VERTICAL
                        dx = 1;
                        dy = 0;
                    }

                    List<Casilla> casillasOcupadas = new ArrayList<>();

                    // Verificar y recolectar casillas válidas
                    boolean valido = true;
                    for (int i = 0; i < nave.getCantidadCasillas(); i++) {
                        int f = fila + i * dx;
                        int c = columna + i * dy;
                        if (f >= filas || c >= columnas) {
                            valido = false;
                            break;
                        }

                        Casilla celda = (Casilla) tablero.getComponent(f * columnas + c);
                        if (celda.nave != null) {
                            valido = false;
                            break;
                        }

                        casillasOcupadas.add(celda);
                    }

                    // Si es válido, pintar y asignar nave
                    if (valido) {
                        for (Casilla c : casillasOcupadas) {
                            c.setBackground(Color.BLACK);
                            c.setNave(nave);
                            nave.addCasillas(c);
                        }
                    tablero.addNaves(nave); // 👈guarda la nave colocada en la lista del tablero


                        // Opcional: mover JLabel de la nave fuera del panel de selección
                        label.setVisible(false); // o remove(label) si no lo necesitas más
                    } else {
                        JOptionPane.showMessageDialog(null, "Colocación inválida: hay colisión o está fuera del tablero.");
                    }
                }
            }

        });
    }

    private Nave Nave1;
    private Nave Nave2;
    private Nave Nave3;
    private Nave Nave4;
    private Tablero tablero;
    private javax.swing.JPanel panelConNaves;
}
