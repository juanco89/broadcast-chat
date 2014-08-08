
package com.juanco.chat.ui;

import java.awt.Container;
import java.awt.Dimension;
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
    
    public VentanaServidor() {
        initComponents();
        
        crearComponentesUI();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
        contenedorComandos.add(btIniciar);
        contenedorComandos.add(Box.createHorizontalStrut(50));
        btDetener = new JButton("Detener");
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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
