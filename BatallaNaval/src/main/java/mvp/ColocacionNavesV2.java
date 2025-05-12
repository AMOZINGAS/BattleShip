/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import clasesDominio.Casilla;
import clasesDominio.Coordenadas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author CISCO
 */
public class ColocacionNavesV2 extends javax.swing.JFrame {

    private static final int TAM = 15; // Tamaño de cada casilla en píxeles
    private static final int filas = 30;
    private static final int columnas = 30;

    public ColocacionNavesV2() {
        setTitle("Coloca tus naves");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Tablero = crearTablero();
        add(Tablero, BorderLayout.CENTER);

        int anchoVentana = columnas * TAM + 50;
        int altoVentana = filas * TAM + 70;
        setSize(anchoVentana, altoVentana);
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    private JPanel crearTablero() {
        JPanel panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(filas, columnas));

        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                Casilla celda = new Casilla(new Coordenadas(fila, col));
                
                celda.setPreferredSize(new Dimension(TAM, TAM));
                celda.setBackground(Color.WHITE);
                celda.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                
                int f = fila;
                int c = col;

                celda.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) { System.out.println("Clic en Fila: " + f + ", Columna: " + c);}});
                    panelTablero.add(celda);}
        }
        return panelTablero;
    }
    
// Variables declaration - do not modify                     
    private javax.swing.JLabel Nave1;
    private javax.swing.JLabel Nave2;
    private javax.swing.JPanel Tablero;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration                   
}
