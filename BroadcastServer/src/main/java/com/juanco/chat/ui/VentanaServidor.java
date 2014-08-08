
package com.juanco.chat.ui;

import com.juanco.chat.controlador.Controlador;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Vista para la configuración y gestión del servidor.
 * 
 * @author Juan C. Orozco <juanco89@gmail.com>
 */
public class VentanaServidor extends javax.swing.JFrame {

    private static final String PUERTO_DEFAULT = "6655";
    
    public VentanaServidor(Controlador c) {
        initComponents();
        
        controlador = c;
        
        crearComponentesUI();
        
        setTitle("Servidor Broadcast");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controlador.detenerServidor();
                super.windowClosing(e);
            }
        });
    }

    private void crearComponentesUI() {
        Container contenedor = getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        
        Box contenedorConfig = Box.createHorizontalBox();
        contenedorConfig.add(new JLabel("Puerto: "));
        txPuerto = new JTextField(PUERTO_DEFAULT);
        txPuerto.setPreferredSize(new Dimension(this.getWidth() / 2, 30));
        txPuerto.setMinimumSize(txPuerto.getPreferredSize());
        txPuerto.setMaximumSize(txPuerto.getPreferredSize());
        txPuerto.setSelectionStart(0);
        txPuerto.setSelectionEnd(txPuerto.getText().length());
        contenedorConfig.add(txPuerto);
        contenedor.add(contenedorConfig);
        
        Box contenedorComandos = Box.createHorizontalBox();
        btIniciar = new JButton("Iniciar");
        btIniciar.addActionListener(accionIniciarServidor);
        contenedorComandos.add(btIniciar);
        contenedorComandos.add(Box.createHorizontalStrut(50));
        btDetener = new JButton("Detener");
        btDetener.addActionListener(accionDetenerServidor);
        contenedorComandos.add(btDetener);
        contenedor.add(contenedorComandos);
        
        txSalida = new JTextArea();
        txSalida.setBorder(BorderFactory.createEtchedBorder());
        contenedor.add(txSalida);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 409, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private JTextField txPuerto;
    private JTextArea txSalida;
    private JButton btIniciar;
    private JButton btDetener;
    
    private Controlador controlador;
    
    private final ActionListener accionIniciarServidor = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(!txPuerto.getText().matches("\\d{4}")) {
                txSalida.append("[x] Especifique correctamente el puerto de escucha.\n");
                return;
            }
            controlador.iniciarServidor(Integer.valueOf(txPuerto.getText()));
            btIniciar.enableInputMethods(false);
        }
    };
    
    private final ActionListener accionDetenerServidor = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            controlador.detenerServidor();
            btIniciar.enableInputMethods(true);
        }
    };
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
