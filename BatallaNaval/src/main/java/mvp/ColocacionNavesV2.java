package mvp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import clasesDominio.Casilla;
import clasesDominio.Coordenada;
import clasesDominio.Nave;
import static clasesDominio.Orientacion.VERTICAL;
import static clasesDominio.TipoNave.SUBMARINO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

        // para las naves
        panelConNaves = new JPanel(); //creamos un panel para poder poner ahi las naves que son jlabels
        panelConNaves.setPreferredSize(new Dimension(400, 80)); // altura fija para que se vea el panel, si no, no se ve
        Nave1 = new JLabel("Nave 1");
        Nave2 = new JLabel("Nave 2");
        Nave3 = new JLabel("Nave 3");
        Nave4 = new JLabel("Nave 4");
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
        
        // para el tablero
        inicializarTablero(); // creamos el tablero
        add(Tablero, BorderLayout.CENTER); // añadimos el tablero al frame

        // para el tamaño de la ventana
        int anchoVentana = columnas * TAM + 50;
        int altoVentana = filas * TAM + 70;
        setSize(anchoVentana, altoVentana);
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    // metodo para crear o inicializar el atributo tablero
    private void inicializarTablero() {
        Tablero = new JPanel();
        Tablero.setLayout(new GridLayout(filas, columnas));

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
                    Tablero.add(celda);}
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
                Point puntoFinal = SwingUtilities.convertPoint(label, e.getPoint(), Tablero);
                Component comp = Tablero.getComponentAt(puntoFinal);
                if (comp instanceof Casilla) {
                    Casilla casilla = (Casilla) comp;
                    casilla.setBackground(Color.BLACK);
                    casilla.setNave(new Nave(SUBMARINO, VERTICAL));
                }
            }
        });
    }

    private javax.swing.JLabel Nave1;
    private javax.swing.JLabel Nave2;
    private javax.swing.JLabel Nave3;
    private javax.swing.JLabel Nave4;
    private javax.swing.JPanel Tablero;
    private javax.swing.JPanel panelConNaves;
}
