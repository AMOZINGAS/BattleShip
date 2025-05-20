/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mvpMenu;

import dtos.BarcoDTO;
import dtos.CasillaDTO;
import dtos.CoordenadasDTO;
import dtos.CruceroDTO;
import dtos.JugadorDTO;
import dtos.MatrizDTO;
import dtos.NaveConfigDTO;
import dtos.NaveDTO;
import dtos.OrientacionENUM;
import dtos.PortaAvionesDTO;
import dtos.SubmarinoDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import static javax.swing.JList.VERTICAL;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import mensajes.ManejadorMensajes;
import mensajes.Mensajes;
import mensajes.ReqRegistrarJugadorConfig;
import mvpJuego.PresentadorJuego;
import mvp_strange.FrmSalaDeEspera;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class VistaConfiguracion extends javax.swing.JFrame implements Observer{

    /**
     * Creates new form ViewConfiguracion
     */
    
    private PresentadorMenu presentador;
    private List<NaveDTO> naves = new ArrayList<>();
    private MatrizDTO matriz;
    private JugadorDTO jugador = new JugadorDTO(); 
    private Color color = Color.RED;
    
    public VistaConfiguracion(PresentadorMenu presentador) {
        this.presentador = presentador;
        this.matriz = new MatrizDTO(jugador);//Agregar jugadorDTO
        this.presentador = presentador;
        
        initComponents();
        //Prueba Nave con DTOs
        NaveDTO barco1 = new BarcoDTO(1, "Barco 01", VERTICAL);
        barco1.setBounds(b1.getBounds());
        hacerArrastrable(barco1); // las hacemos que se puedan arrastrar con el raton 
        this.add(barco1);
        naves.add(barco1);
        
        NaveDTO barco2 = new BarcoDTO(1, "Barco 02", VERTICAL);
        barco2.setBounds(b2.getBounds());
        hacerArrastrable(barco2); // las hacemos que se puedan arrastrar con el raton 
        this.add(barco2);
        naves.add(barco2);
        
        NaveDTO barco3 = new BarcoDTO(1, "Barco 03", VERTICAL);
        barco3.setBounds(b3.getBounds());
        hacerArrastrable(barco3); // las hacemos que se puedan arrastrar con el raton 
        this.add(barco3);
        naves.add(barco3);
        
        NaveDTO crucero1 = new CruceroDTO(3, "Crucero 01", VERTICAL);
        crucero1.setBounds(c1.getBounds());
        hacerArrastrable(crucero1); // las hacemos que se puedan arrastrar con el raton 
        this.add(crucero1);
        naves.add(crucero1);
        
        NaveDTO crucero2 = new CruceroDTO(3, "Crucero 02", VERTICAL);
        crucero2.setBounds(c2.getBounds());
        hacerArrastrable(crucero2); // las hacemos que se puedan arrastrar con el raton 
        this.add(crucero2);
        naves.add(crucero2);
        
        NaveDTO portaAviones1 = new PortaAvionesDTO(4, "Porta Aviones 01", VERTICAL);
        portaAviones1.setBounds(p1.getBounds());
        hacerArrastrable(portaAviones1); // las hacemos que se puedan arrastrar con el raton 
        this.add(portaAviones1);
        naves.add(portaAviones1);
        
        NaveDTO portaAviones2 = new PortaAvionesDTO(4, "Porta Aviones 02", VERTICAL);
        portaAviones2.setBounds(p2.getBounds());
        hacerArrastrable(portaAviones2); // las hacemos que se puedan arrastrar con el raton 
        this.add(portaAviones2);
        naves.add(portaAviones2);
        
        NaveDTO subMarino1 = new SubmarinoDTO(2, "Submarino 01", VERTICAL);
        subMarino1.setBounds(s1.getBounds());
        hacerArrastrable(subMarino1); // las hacemos que se puedan arrastrar con el raton 
        this.add(subMarino1);
        naves.add(subMarino1);
        
        NaveDTO subMarino2 = new SubmarinoDTO(2, "Submarino 02", VERTICAL);
        subMarino2.setBounds(s2.getBounds());
        hacerArrastrable(subMarino2); // las hacemos que se puedan arrastrar con el raton 
        this.add(subMarino2);
        naves.add(subMarino2);
        
        NaveDTO subMarino3 = new SubmarinoDTO(2, "Submarino 03", VERTICAL);
        subMarino3.setBounds(s3.getBounds());
        hacerArrastrable(subMarino3); // las hacemos que se puedan arrastrar con el raton 
        this.add(subMarino3);
        naves.add(subMarino3);
        
        NaveDTO subMarino4 = new SubmarinoDTO(2, "Submarino 04", VERTICAL);
        subMarino4.setBounds(s4.getBounds());
        hacerArrastrable(subMarino4); // las hacemos que se puedan arrastrar con el raton 
        this.add(subMarino4);
        naves.add(subMarino4);
        
//        jugador.addNave(crucero);
//        jugador.addNave(barco1);
        
        iniciarTablero(); // creamos el tablero
        this.add(TableroJP, BorderLayout.CENTER); // añadimos el tablero al frame
        
        int anchoVentana = 10 * 40 + 50;
        int altoVentana = 10 * 40 + 70;
        TableroJP.setSize(anchoVentana, altoVentana);
    }
    
    public void iniciarTablero(){
        
        TableroJP.setLayout(new GridLayout(10, 10));
        
        for (int fila = 0; fila < 10; fila++) {
            for (int col = 0; col < 10; col++) {
                CasillaDTO casilla = new CasillaDTO(new CoordenadasDTO(fila, col)); // casilla extiende de jpanel
                
                casilla.setPreferredSize(new Dimension(20, 20)); // para el tama;o de la casilla
                casilla.setBackground(Color.WHITE); // para el color de la casilla 
                casilla.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // creamos lineas negras en las casillas
                
                int f = fila;
                int c = col;

                casilla.addMouseListener(new MouseAdapter() {
                    Timer timer;

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 1) {
                            NaveDTO nave = casilla.getNaveOcupante();
                            if (nave != null) {
                                rotarNave(nave);
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        timer = new Timer(600, evt -> {
                            NaveDTO nave = casilla.getNaveOcupante();
                            if (nave != null) {
                                devolverNave(nave);
                            }
                        });
                        timer.setRepeats(false);
                        timer.start();
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (timer != null && timer.isRunning()) {
                            timer.stop();
                        }
                    }
                });
                TableroJP.add(casilla);
                matriz.addCasilla(casilla); 
            }
        }
        
    }
    
    private void rotarNave(NaveDTO nave) {
        OrientacionENUM nuevaOrientacion = (nave.getOrientacion() == OrientacionENUM.HORIZONTAL)
            ? OrientacionENUM.VERTICAL : OrientacionENUM.HORIZONTAL;

        CasillaDTO origen = nave.getCasillas().get(0);
        int fila = origen.getCoordenada().getCoordenadasX();
        int col = origen.getCoordenada().getCoordenadasY();

        int dx = (nuevaOrientacion == OrientacionENUM.VERTICAL) ? 1 : 0;
        int dy = (nuevaOrientacion == OrientacionENUM.HORIZONTAL) ? 1 : 0;

        List<CasillaDTO> nuevasCasillas = new ArrayList<>();
        boolean valido = true;

        for (int i = 0; i < nave.getTamanio(); i++) {
            int f = fila + i * dx;
            int c = col + i * dy;

            if (f >= 10 || c >= 10) {
                valido = false;
                break;
            }

            CasillaDTO celda = (CasillaDTO) TableroJP.getComponent(f * 10 + c);
            if (celda.getNaveOcupante() != null && celda.getNaveOcupante() != nave) {
                valido = false;
                break;
            }

            nuevasCasillas.add(celda);
        }

        if (!valido) {
            JOptionPane.showMessageDialog(null, "No se puede rotar: colisión o fuera del tablero.");
            return;
        }

        // Limpiar casillas anteriores
        for (CasillaDTO c : nave.getCasillas()) {
            c.setBackground(Color.WHITE);
            c.setNaveOcupante(null);
        }

        // Asignar nuevas casillas
        for (CasillaDTO c : nuevasCasillas) {
            c.setBackground(color);
            c.setNaveOcupante(nave);
        }

        nave.setCasillas(nuevasCasillas);
        nave.setOrientacion(nuevaOrientacion);
    }
    
    private void devolverNave(NaveDTO nave) {
        for (CasillaDTO c : nave.getCasillas()) {
            c.setBackground(Color.WHITE);
            c.setNaveOcupante(null);
        }
        matriz.getNaves().remove(nave);
        nave.setCasillas(new ArrayList<>());
        
        switch (nave.getNombre()){
            case "Barco 01":
                nave.setBounds(b1.getBounds());
                break;
            case "Barco 02":
                nave.setBounds(b2.getBounds());
                break;
            case "Barco 03":
                nave.setBounds(b3.getBounds());
                break;
            case "Crucero 01":
                nave.setBounds(c1.getBounds());
                break;
            case "Crucero 02":
                nave.setBounds(c2.getBounds());
                break;
            case "Porta Aviones 01":
                nave.setBounds(p1.getBounds());
                break;
            case "Porta Aviones 02":
                nave.setBounds(p2.getBounds());
                break;
            case "Submarino 01":
                System.out.println("Submarino 1");
                nave.setBounds(s1.getBounds());
                break;
            case "Submarino 02":
                System.out.println("Submarino 2");
                nave.setBounds(s2.getBounds());
                break;
            case "Submarino 03":
                System.out.println("Submarino 3");
                nave.setBounds(s3.getBounds());
                break;
            case "Submarino 04":
                System.out.println("Submarino 4");
                nave.setBounds(s4.getBounds());
                break;
            default:
                System.out.println("ups, aprox linea 304");
            
        }
        nave.setVisible(true);
        removerNaveJugador(nave.getCoordenadaInicial(), nave);
        this.add(nave);
        this.repaint();
    }

    public void removerNaveJugador(CoordenadasDTO coordenada, NaveDTO naveRemove){
        
        System.out.println(jugador.getFlotilla());
        jugador.removeNave(new NaveConfigDTO(naveRemove.getTipo(), naveRemove.getCoordenadaInicial(), naveRemove.getOrientacion()));
        System.out.println(jugador.getFlotilla());
//        for(NaveDTO naveJugador: jugador.getFlotilla()){
//            if(naveRemove.getCoordenadaInicial() == naveJugador.getCoordenadaInicial()){
//                
//            }
//        }
        
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TableroJP = new javax.swing.JPanel();
        btnListo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        b1 = new javax.swing.JLabel();
        b3 = new javax.swing.JLabel();
        s3 = new javax.swing.JLabel();
        s4 = new javax.swing.JLabel();
        c2 = new javax.swing.JLabel();
        p2 = new javax.swing.JLabel();
        b2 = new javax.swing.JLabel();
        s1 = new javax.swing.JLabel();
        s2 = new javax.swing.JLabel();
        c1 = new javax.swing.JLabel();
        p1 = new javax.swing.JLabel();
        btnColor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Configuración de Naves");

        TableroJP.setBackground(new java.awt.Color(204, 204, 255));
        TableroJP.setLayout(new java.awt.BorderLayout());

        btnListo.setText("Listo");
        btnListo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListoActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre");

        b1.setFocusable(false);

        b3.setBackground(new java.awt.Color(255, 255, 255));

        btnColor.setText("Color");
        btnColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnListo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(s1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(s2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(s4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(s3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnColor))
                        .addGap(41, 41, 41)
                        .addComponent(TableroJP, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(s2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(s3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnColor))
                    .addComponent(TableroJP, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(btnListo)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnListoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListoActionPerformed
        // TODO add your handling code here:
        //Validación de naves
        boolean todasNavesColocadas = true;
        for (NaveDTO nave : naves) {
            if(nave.isVisible()){
                todasNavesColocadas = false;
            }
        }
        
        if(!todasNavesColocadas){
            JOptionPane.showMessageDialog(this, "Coloca todas las naves para continuar. ", "Configuración incompleta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //Validación del nombre
        if(txtNombre.getText() == null || txtNombre.getText().length() == 0){
            JOptionPane.showMessageDialog(this, "Elija un nombre para seguir. ", "Nombre incompleto", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String nombre = txtNombre.getText();
        if(nombre.length() < 5){
            JOptionPane.showMessageDialog(this, "El nombre debe ser mayor a 4 caracteres. ", "Nombre inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }
        jugador.setNombre(nombre);
        
        //Petición al servidor
        // 1. Recopilar la información del tablero
        List<NaveConfigDTO> configuracionNaves = new ArrayList<>();
        for (NaveDTO nave : naves) {
            // Asumo que NaveDTO tiene métodos para obtener posición y orientación
            CoordenadasDTO coordenada = nave.getCoordenadaInicial();
            OrientacionENUM orientacion = nave.getOrientacion();
            String tipoNave = nave.getTipo(); // Necesitas definir un tipo en NaveDTO

            if (coordenada != null) {
                configuracionNaves.add(new NaveConfigDTO(tipoNave, coordenada, orientacion));
            } else {
                // Esto no debería ocurrir si la validación de colocación es correcta
                System.err.println("Error: Nave sin coordenada inicial.");
                return;
            }
        }
        
        System.out.println("Tamaño naves: " + naves.size());
        System.out.println("Naves");
        for (NaveConfigDTO nave : configuracionNaves) {
            System.out.println(nave.toString());
        }
        
        //2. Creamos un mensaje para el servidor (en JSON)
//        ReqRegistrarJugadorConfig peticionRegistro = new ReqRegistrarJugadorConfig(new JugadorDTO(nombre, color.toString()), configuracionNaves);
//        String jsonPeticion = ManejadorMensajes.toJson(peticionRegistro);
        
        //3. Enviamos el mensaje al servidor
        presentador.registrarJugadorConfig(jugador, configuracionNaves);
        
        //4. Cambiamos a la siguiente pantalla (Sala de Espera)
    }//GEN-LAST:event_btnListoActionPerformed

    private void btnColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorActionPerformed
        // TODO add your handling code here:
        
        color = JColorChooser.showDialog(null, "Color", Color.black);
        
        
        if(color != null){
            pintar();
        }
        
    }//GEN-LAST:event_btnColorActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel TableroJP;
    private javax.swing.JLabel b1;
    private javax.swing.JLabel b2;
    private javax.swing.JLabel b3;
    private javax.swing.JButton btnColor;
    private javax.swing.JButton btnListo;
    private javax.swing.JLabel c1;
    private javax.swing.JLabel c2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel p1;
    private javax.swing.JLabel p2;
    private javax.swing.JLabel s1;
    private javax.swing.JLabel s2;
    private javax.swing.JLabel s3;
    private javax.swing.JLabel s4;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Mensajes) {
            String comando = (String) ((Mensajes) arg).getComando();
            
            if (comando.equals("PARTIDA_CREADA")) {
                this.setVisible(true);
            } else if(comando.equals("JUGADOR_UNIDO")) {
                this.setVisible(true);
            }else if (comando.equals("JUGADORES_LISTOS")){
                this.dispose();
            } else if(comando.equals("CONFIGURACION_RECIBIDA")){
                System.out.println("Se configuro correctamente");
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
                Point puntoFinal = SwingUtilities.convertPoint(label, e.getPoint(), TableroJP);
                Component comp = TableroJP.getComponentAt(puntoFinal);
                if (comp instanceof CasillaDTO) {
                    CasillaDTO casillaInicial = (CasillaDTO) comp;
                    NaveDTO nave = (NaveDTO) label;

                    CoordenadasDTO inicio = casillaInicial.getCoordenada();
                    int fila = inicio.getCoordenadasX();
                    int columna = inicio.getCoordenadasY();

                    // Determinar dirección
                    int dx = 0, dy = 0;
                    if (nave.getOrientacion() == OrientacionENUM.HORIZONTAL) {
                        dx = 0;
                        dy = 1;
                    } else { // VERTICAL
                        dx = 1;
                        dy = 0;
                    }

                    List<CasillaDTO> casillasOcupadas = new ArrayList<>();

                    // Verificar y recolectar casillas válidas
                    boolean valido = true;
                    for (int i = 0; i < nave.getTamanio(); i++) {
                        int f = fila + i * dx;
                        int c = columna + i * dy;
                        if (f >= 10 || c >= 10) {
                            valido = false;
                            break;
                        }
                        //los "10" son las filas y columnas, tmbn podemos poner una variable en algun lado para definirlas
                        CasillaDTO celda = (CasillaDTO) TableroJP.getComponent(f * 10 + c);
                        if (celda.getNaveOcupante() != null) {
                            valido = false;
                            break;
                        }

                        casillasOcupadas.add(celda);
                    }

                    // Si es válido, pintar y asignar nave
                    if (valido) {
                        for (CasillaDTO c : casillasOcupadas) {
                            c.setBackground(color);
                            c.setNaveOcupante(nave);
                        }
                        nave.setCasillas(casillasOcupadas);
                        nave.setCoordenadaInicial(new CoordenadasDTO(columna, fila));
                        matriz.getNaves().add(nave); // guarda la nave colocada en la lista del tablero
                        System.out.println("Nave colocada en: (" +columna + ", "+ fila +")" );
                        jugador.addNave(new NaveConfigDTO(nave.getTipo(), nave.getCoordenadaInicial(), nave.getOrientacion()));
                        label.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Colocación inválida: hay colisión o está fuera del tablero.");
                    }
                }
                
            }

        });
    }    
    
    public void pintar(){
        if(matriz.getNaves()!=null){
            for(NaveDTO nave: matriz.getNaves()){
                for(CasillaDTO casilla: nave.getCasillas()){
                    casilla.setBackground(color);
                }
            }
        }
        if(color != null){
            jugador.setColor(color.toString());
        }
    }
    
//    public class ColocacionNavesV2 extends javax.swing.JFrame {
//    
//    private static int TAM;
//    private static int filas;
//    private static int columnas;
//    
//
////    public ColocacionNavesV2() {
////        // para las naves
////        Nave1 = new BarcoDTO(1, "Barco 1", VERTICAL);
//////        Nave2 = new Nave(SUBMARINO, HORIZONTAL, "Nave 2");
//////        Nave3 = new Nave(PORTAVIONES, VERTICAL, "Nave 3");
//////        Nave4 = new Nave(CRUCERO, HORIZONTAL, "Nave 4");
////        Nave1.setBounds(10, 10, 60, 20);
//////        Nave2.setBounds(100, 10, 60, 20);
//////        Nave3.setBounds(190, 10, 60, 20);
//////        Nave4.setBounds(280, 10, 60, 20);
////        hacerArrastrable(Nave1); // las hacemos que se puedan arrastrar con el raton 
//////        hacerArrastrable(Nave2);
//////        hacerArrastrable(Nave3);
//////        hacerArrastrable(Nave4);
////
////        add(tablero, BorderLayout.CENTER); // añadimos el tablero al frame
//    }

    
//    public TableroDTO clonarTablero() {
//        TableroDTO tableroClon = new TableroDTO();
//        tableroClon.setLayout(new GridLayout(filas, columnas));
//
//        for (int fila = 0; fila < filas; fila++) {
//            for (int col = 0; col < columnas; col++) {
//                // Obtener la casilla original
//                CasillaDTO original = tablero.getCasilla(fila, col); // asegúrate que tienes este método en Tablero
//                CasillaDTO clon = new CasillaDTO(new CoordenadasDTO(fila, col));
//                clon.setPreferredSize(new Dimension(TAM, TAM));
//                clon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//
//                // Copiar estado visual
//                clon.setBackground(original.getBackground());
//
//                // Copiar referencia de nave si tiene
//                if (original.nave != null) {
//                    clon.setNave(original.nave); // referencia a la misma nave
//                }
//
//                tableroClon.add(clon);
//                tableroClon.addCasillas(clon);
//            }
//        }
//
//        // Copiar las naves colocadas (si quieres tener acceso a ellas)
//        for (NaveDTO nave : tablero.getNavesColocadas()) {
//            tableroClon.addNaves(nave);
//        }
//
//        return tableroClon;
//    }
}
