/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mvp;

import clasesDominio.Casilla;
import clasesDominio.Jugador;
import clasesDominio.Nave;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author crazy
 */
public class JuegoV2 extends javax.swing.JFrame {
    
    private static final int TAM = 20; // Tamaño de cada casilla en píxeles
    private static final int filas = 30;
    private static final int columnas = 30;
    
    public JuegoV2() {
        setTitle("Batalla Naval en Curso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2)); // Dos columnas: un tablero en cada una

        Tablero1 = crearTablero("Tablero enemigo", 1);
        Tablero2 = crearTablero("Tablero tuyo", 2);

        add(Tablero1);
        add(Tablero2);

        int anchoVentana = columnas * TAM * 2 + 50;
        int altoVentana = filas * TAM + 70;
        setSize(anchoVentana, altoVentana);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel crearTablero(String nombre, int numeroTablero) {
        JPanel panelTablero = new JPanel();
        panelTablero.setBorder(BorderFactory.createTitledBorder(nombre));
        panelTablero.setLayout(new GridLayout(filas, columnas));

        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                JPanel celda = new JPanel();
                celda.setPreferredSize(new Dimension(TAM, TAM));
                celda.setBackground(Color.WHITE);
                celda.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                int f = fila;
                int c = col;

                celda.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("Clic en tablero " + numeroTablero + " - Fila: " + f + ", Columna: " + c);
                    }
                });

                panelTablero.add(celda);
            }
        }
        return panelTablero;
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel Tablero1;
    private javax.swing.JPanel Tablero2;
    // End of variables declaration                   
}
